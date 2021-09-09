package Main;

import entity.Ldcode;

import func.LdcodeMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import sink.LdcodeSink;

import java.util.Properties;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/8/12 13:36
 */
public class Flink2Hbase {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        Properties prop = new Properties();
        prop.put("bootstrap.servers", "zyVM:9092");
        prop.put("kafka.zookeeper.connect", "zyVM:2181");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        Connection connection; // 管理Hbase连接

        DataStreamSource<String> ldcodeSource = env.addSource(new FlinkKafkaConsumer<String>("ldcode", new SimpleStringSchema(), prop));
        ldcodeSource
                .map(new LdcodeMapFunction())//封装成pojo
                .addSink(new LdcodeSink());//sink到hbase
        env.execute("write2Hbase");
    }
}
