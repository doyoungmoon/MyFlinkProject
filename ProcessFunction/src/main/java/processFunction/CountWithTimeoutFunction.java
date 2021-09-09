package processFunction;

import bean.CountWithTimestamp;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/6/29 14:32
 */
public class CountWithTimeoutFunction

        extends KeyedProcessFunction<Tuple, Tuple2<String, String>, Tuple2<String, Long>> {

    /**
     * 由这个处理函数负责维护的状态
     */
    private ValueState<CountWithTimestamp> state;

    // 首先获得由这个处理函数（process function）维护的状态
    // 通过 RuntimeContext 访问Flink的keyed state
    @Override
    public void open(Configuration parameters) throws Exception {
        state = getRuntimeContext().getState(new ValueStateDescriptor<>("myState", CountWithTimestamp.class));
    }

    // 对于在输入流中接收到的每一个事件，此函数就会被调用以处理该事件
    // 对于每个记录，KeyedProcessFunction递增计数器并设置最后修改时间戳
    @Override
    public void processElement(
            Tuple2<String, String> value,
            Context ctx,
            Collector<Tuple2<String, Long>> out) throws Exception {

        // 获取当前的计数
        CountWithTimestamp current = state.value();
        if (current == null) {
            current = new CountWithTimestamp();
            current.key = value.f0;
        }

        // 更新状态计数值
        current.count++;

        // 设置该状态的时间戳为记录的分配的事件时间时间时间戳
        if (ctx != null) {
            current.lastModified = ctx.timestamp();
        }

        // 将状态写回
        state.update(current);

        // 从当前事件时间开始安排下一个计时器60秒,60s到后则调用onTimer()方法，
        // 并将下面的‘current.lastModified + 60000’作为参数传给timestamp。
        ctx.timerService().registerEventTimeTimer(current.lastModified + 60000);
    }

    // 如果一分钟内没有进一步的更新，则发出 key/count对
    @Override
    public void onTimer(
            long timestamp,
            OnTimerContext ctx,
            Collector<Tuple2<String, Long>> out) throws Exception {

        // 获取调度此计时器的key的状态
        CountWithTimestamp result = state.value();

        // 检查这是一个过时的计时器还是最新的计时器
        if (timestamp == result.lastModified + 60000) {//相等时，则说明在过去的60000s内，当前key的状态没更新。
            // 超时时发出状态
            out.collect(new Tuple2<String, Long>(result.key, result.count));
        }
    }
}

