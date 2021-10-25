

import com.google.common.util.concurrent.AtomicDouble;
import lombok.Data;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright (c) 2019 bigdata ALL Rights Reserved
 * Project: learning
 * Package: com.bigdata.agg
 * Version: 1.0
 *
 * @author qingzhi.wu 2021/3/1 21:53
 */
public class Main {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        ArrayList<String> datas = new ArrayList<>();
        datas.add("1000,网上商城,200.0");
        datas.add("1500,线下门店,100.0");
        datas.add("1300,美团配送,200.0");
        datas.add("1600,网上商城,100.0");
        DataStreamSource<String> dataStreamSource = env.fromCollection(datas);
        SingleOutputStreamOperator<String> source = dataStreamSource.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<String>(Time.of(1, TimeUnit.SECONDS)) {
            @Override
            public long extractTimestamp(String s) {
                return Long.parseLong(s.split(",")[0]);
            }
        });
        SingleOutputStreamOperator<TestData> testDataStream = source.map(new MapFunction<String, TestData>() {
            @Override
            public TestData map(String line) throws Exception {
                long timestamp = Long.parseLong(line.split(",")[0]);
                String channel = line.split(",")[1];
                double amt = Double.parseDouble(line.split(",")[2]);
                return new TestData(timestamp, channel, amt);
            }
        });
        testDataStream.keyBy(new KeySelector<TestData, String>() {
            @Override
            public String getKey(TestData testData) throws Exception {
                return testData.channel;
            }
        }).window(TumblingEventTimeWindows.of(Time.of(1, TimeUnit.SECONDS)))
                .aggregate(new AggregateFunction<TestData, Acc, String>() {
                    @Override
                    public Acc createAccumulator() {
                        Acc acc = new Acc();
                        acc.init();
                        return acc;
                    }

                    @Override
                    public Acc add(TestData testData, Acc acc) {
                        acc.count.getAndAdd(1);
                        acc.sum.getAndAdd(testData.amt);
                        return acc;
                    }

                    @Override
                    public String getResult(Acc acc) {
                        return acc.toString();
                    }

                    @Override
                    public Acc merge(Acc acc, Acc acc1) {
                        acc.count.addAndGet(acc1.count.get());
                        acc.sum.addAndGet(acc1.count.get());

                        return acc;
                    }
                }, new WindowFunction<String, String, String, TimeWindow>() {
                    @Override
                    public void apply(String key, TimeWindow timeWindow, Iterable<String> iterable, Collector<String> out) throws Exception {
                        Iterator<String> iterator = iterable.iterator();
                        while (iterator.hasNext()) {
                            String value = iterator.next();
                            out.collect(key + "," + value);
                        }
                    }
                }).print();


        env.execute("Aggregate test");
    }

    @Data
    private static class Acc {
        AtomicDouble sum;
        AtomicLong count;

        public void init() {
            sum = new AtomicDouble(0);
            count = new AtomicLong(0);
        }

        public Acc() {
        }

        @Override
        public String toString() {
            return sum + "," + count;
        }
    }

    @Data
    private static class TestData {
        long timestamp;
        String channel;
        Double amt;

        public TestData() {
        }

        public TestData(long timestamp, String channel, Double amt) {
            this.timestamp = timestamp;
            this.channel = channel;
            this.amt = amt;
        }
    }
}

