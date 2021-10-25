package operator;

import bean.TableA;
import bean.TableB;
import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.util.Collector;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/10/25 10:58
 */
public class Cogroup {

    private static final String[] TYPE = {"a", "b", "c", "d"};

    public static void main(String[] args) throws Exception {
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        EnvironmentSettings environmentSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
//        env.setParallelism(1);
//
//
//        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, environmentSettings);
//
//        //默认值为0   表示FlinkSQL中的状态永久保存
//        System.out.println(tableEnv.getConfig().getIdleStateRetention());
//
//        //执行FLinkSQL状态保留10秒
////        tableEnv.getConfig().setIdleStateRetention(Duration.ofSeconds(10));
//
//
//        //2.读取端口数据创建流
//        SingleOutputStreamOperator<TableA> aDS = env.socketTextStream("localhost", 8888)
//                .map(line -> {
//                    String[] split = line.split(",");
//
//                    return new TableA(split[0], split[1]);
//                });
//        SingleOutputStreamOperator<TableB> bDS = env.socketTextStream("localhost", 9999)
//                .map(line -> {
//                    String[] split = line.split(",");
//
//                    return new TableB(split[0], split[1]);
//                });
//
//        aDS.coGroup(bDS).where(aTable->aTable.getId()).equalTo(bTable->bTable.getId())
//                .window()
//    }


        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //添加自定义数据源,每秒发出一笔订单信息{商品名称,商品数量}
        DataStreamSource<Tuple2<String, Integer>> orderSource1 = env.addSource(new SourceFunction<Tuple2<String, Integer>>() {
            private volatile boolean isRunning = true;
            private final Random random = new Random();

            @Override
            public void run(SourceFunction.SourceContext<Tuple2<String, Integer>> ctx) throws Exception {
                while (isRunning) {
                    TimeUnit.SECONDS.sleep(1);
                    Tuple2<String, Integer> tuple2 = Tuple2.of(TYPE[random.nextInt(TYPE.length)], random.nextInt(10));
                    System.out.println(new Date() + ",orderSource1提交元素:" + tuple2);
                    ctx.collect(tuple2);
                }
            }

            @Override
            public void cancel() {
                isRunning = false;
            }

        }, "orderSource1");

        DataStreamSource<Tuple2<String, Integer>> orderSource2 = env.addSource(new SourceFunction<Tuple2<String, Integer>>() {
            private volatile boolean isRunning = true;
            private final Random random = new Random();

            @Override
            public void run(SourceContext<Tuple2<String, Integer>> ctx) throws Exception {
                while (isRunning) {
                    TimeUnit.SECONDS.sleep(1);
                    Tuple2<String, Integer> tuple2 = Tuple2.of(TYPE[random.nextInt(TYPE.length)], random.nextInt(10));
                    System.out.println(new Date() + ",orderSource2提交元素:" + tuple2);
                    ctx.collect(tuple2);
                }
            }

            @Override
            public void cancel() {
                isRunning = false;
            }

        }, "orderSource2");

        orderSource1.coGroup(orderSource2)
                .where(new KeySelector<Tuple2<String, Integer>, String>() {//指定第一个输入的分区字段
                    @Override
                    public String getKey(Tuple2<String, Integer> value) throws Exception {
                        return value.f0;
                    }
                }).equalTo(new KeySelector<Tuple2<String, Integer>, String>() {//指定第二个输入的分区字段。这俩分区字段要相同。
            @Override
            public String getKey(Tuple2<String, Integer> value) throws Exception {
                return value.f0;
            }
        })
                .window(TumblingProcessingTimeWindows.of(Time.seconds(4)))
                .apply(new CoGroupFunction<Tuple2<String, Integer>, Tuple2<String, Integer>, Object>() {
                    public void coGroup(Iterable<Tuple2<String, Integer>> first, Iterable<Tuple2<String, Integer>> second, Collector<Object> out) throws Exception {
//                        cogroup算子会把一个窗口内的所有数据（2个流的所有数据）：
//                        1、按照key进行分组，相同key的流1的数据和流2的数据会同时进入到这个算子中
//                        2、这个窗口中，key都相同，流1的数据被相同的迭代器iterable收集1起来，流2的数据被另一个迭代器收集起来。
//                        3、结果就是：同一窗口内的，比如[0,1)窗口中的流1、流2的数据，key相同的会同时进入到cogroup方法中。

        /*       数据源：
                        2提交元素:(c,6)
                        1提交元素:(c,9)
                        2提交元素:(c,1)
                        1提交元素:(a,6)
                        1提交元素:(b,3)
                        2提交元素:(a,0)
                        2提交元素:(c,4)
                        1提交元素:(c,7)
                 结果：
                    6> DataStream first: a=>2
                       DataStream second: a=>3
                    2> DataStream first: b=>6
                       DataStream second:
                    5> DataStream first: d=>9
                       DataStream second:
                    4> DataStream first:
                       DataStream second: c=>3 c=>8
   */

                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("DataStream first:\n");
                        for (Tuple2<String, Integer> value : first) {
                            stringBuffer.append(value.f0 + "=>" + value.f1 + "\n");
                        }
                        stringBuffer.append("DataStream second:\n");
                        for (Tuple2<String, Integer> value : second) {
                            stringBuffer.append(value.f0 + "=>" + value.f1 + "\n");
                        }

                        out.collect(stringBuffer.toString());
                    }
                }).print();

//                .apply(new CoGroupFunction<Tuple2<String, Integer>, Tuple2<String, Integer>, Tuple2<String, Integer>>() {
//            @Override
//            public void coGroup(Iterable<Tuple2<String, Integer>> first, Iterable<Tuple2<String, Integer>> second, Collector<Tuple2<String, Integer>> out) throws Exception {
//                for (Tuple2<String, Integer> firstElement : first) {//第一个输入
//
//                    out.collect(firstElement);
//                }
//                for (Tuple2<String, Integer> secondElement : second) {//第二个输入
//                    out.collect(secondElement);
//                }
//            }
//        }).print();
        env.execute("Flink Streaming Java API Skeleton");
    }

}
