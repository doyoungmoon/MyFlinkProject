package Main.my;


import entity.BenefitInsured;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import sink.MyHbaseSink;
import kafkaSchema.MykafkaSourceSchema;

import java.util.Properties;

import static org.apache.hadoop.hbase.HBaseConfiguration.create;

/**
 * @author zy
 * @version 1.0
 * @description:
 * 消费kafka发来的消息（消息类型为pojo），写入到hbase中
 * @date 2021/8/6 14:56
 */
public class Kafka2Hbase {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        Properties prop = new Properties();
        prop.put("bootstrap.servers", "zyVM:9092");
        prop.put("kafka.zookeeper.connect", "zyVM:2181");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        Connection connection; // 管理Hbase连接
//       Admin admin; // 管理Hbase数据库的信息
        DataStreamSource<BenefitInsured> kafkaSource = env.addSource(new FlinkKafkaConsumer<BenefitInsured>("test", new MykafkaSourceSchema(), prop));
        kafkaSource.addSink(new MyHbaseSink());
        env.execute("tohbase");

    }
}
