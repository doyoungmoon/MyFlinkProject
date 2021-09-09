package Main.my;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;


import java.util.Properties;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/8/5 15:38
 */
public class CSV2Kafka {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);


        DataStreamSource<String> csvSource = env.
                readTextFile("C:\\Users\\86188\\Desktop\\ldcode.json");

        Properties prop = new Properties();
        prop.put("bootstrap.servers", "zyVM:9092");
        prop.put("kafka.zookeeper.connect", "zyVM:2181");

        csvSource.addSink(new FlinkKafkaProducer<String>("alldata", new SimpleStringSchema(), prop));

//        csvSource.addSink(new FlinkKafkaProducer<String>("default-topic",new MytopicSplitSchema(),prop,FlinkKafkaProducer.Semantic.AT_LEAST_ONCE));

//        封装成pojo
       /* SingleOutputStreamOperator<BenefitInsured> bijson = csvSource.flatMap(new FlatMapFunction<String, BenefitInsured>() {
            @Override
            public void flatMap(String s, Collector<BenefitInsured> collector) throws Exception {
                String[] split = s.split(",");
                System.out.println(split);

                BenefitInsured bi = new BenefitInsured();
                bi.setList_id(split[0]);
                bi.setItem_id(split[1]);
                collector.collect(bi);
            }
        });*/

        /*SingleOutputStreamOperator<BenefitInsured> biJson = csvSource.map(new MapFunction<String, BenefitInsured>() {
            @Override
            public BenefitInsured map(String s) throws Exception {
                String[] split = s.split(",");
                System.out.println(split);
                BenefitInsured bi = new BenefitInsured();
                bi.setList_id(split[0]);
                bi.setItem_id(split[1]);
                return bi;
            }
        });*/

//输出到hbase表
       /* bijson.addSink(new FlinkKafkaProducer("test", new SerializationSchema<BenefitInsured>() {
            @Override
            public byte[] serialize(BenefitInsured o) {
                return JSON.toJSON(o).toString().getBytes();
            }
        }
                , prop));*/

        env.execute("flink2kafka");
    }

}
