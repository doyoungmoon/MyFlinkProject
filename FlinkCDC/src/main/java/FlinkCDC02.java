/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/8/30 16:45
 */



import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import com.ververica.cdc.connectors.mysql.MySqlSource;
import schema.MyDebeziumDeserializationSchema;

/**
 * @description:读取mysql数据，利用自定义反序列化类来解析mysql的数据
 * @author zy
 * @date:  15:03
 */
public class FlinkCDC02 {
    public static void main(String[] args) throws Exception {
        SourceFunction<String> sourceFunction = MySqlSource.<String>builder()
                .hostname("zyVM")
                .port(3306)
                .databaseList("test") // monitor all tables under inventory database
                .username("root")
                .password("123456")
                .deserializer(new MyDebeziumDeserializationSchema()) // converts SourceRecord to String
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env
                .addSource(sourceFunction)
                .print().setParallelism(1); // use parallelism 1 for sink to keep message ordering

        env.execute();
    }
}
