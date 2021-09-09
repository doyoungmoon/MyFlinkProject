package Main;

import kafkaSchema.MytopicSplitSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * @author zy
 * @version 1.0
 * @description: 将总的topic中的数据根据表名分发到不同的topic中
 * @date 2021/8/12 11:02
 */
public class Kafka2Flink {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "zyVM:9092");
        prop.put("kafka.zookeeper.connect", "zyVM:2181");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);
        DataStreamSource<String> alldataSource = env.addSource(new FlinkKafkaConsumer<String>("alldata", new SimpleStringSchema(), prop));

//kafka中一个总的topic，根据json中提取的表名，将json进行分流，相同表名的json发送到同一topic中
        alldataSource.addSink(new FlinkKafkaProducer<String>("another", new MytopicSplitSchema(), prop, FlinkKafkaProducer.Semantic.AT_LEAST_ONCE));
        env.execute("topicSplit");

    }

}
