



import com.ververica.cdc.connectors.mysql.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.DebeziumSourceFunction;
import com.ververica.cdc.debezium.StringDebeziumDeserializationSchema;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author zy
 * @version 1.0
 * @description:读取mysql数据，并输出，其中使用了状态后端，保存运行状态在Hadoop中。用于故障时重启。
 * @date 2021/8/30 16:45
 */
public class FlinkCDC {
    public static void main(String[] args) throws Exception {
        DebeziumSourceFunction<String> sourceFunction = MySqlSource.<String>builder()
                .hostname("zyVM")
                .port(3306)
                .databaseList("test") // monitor all tables under inventory database
                .username("root")
                .password("123456")
                .startupOptions(StartupOptions.earliest())
                .deserializer(new StringDebeziumDeserializationSchema())
                .build();


        /*SourceFunction<String> sourceFunction = MySQLSource.<String>builder()
                .hostname("zyVM")
                .port(3306)
                .databaseList("test") // monitor all tables under inventory database
                .username("root")
                .password("123456")

                .deserializer(new StringDebeziumDeserializationSchema()) // converts SourceRecord to String
                .build();*/

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //2.Flink-CDC 将读取 binlog 的位置信息以状态的方式保存在 CK,如果想要做到断点续传,需要从 Checkpoint 或者 Savepoint 启动程序
        //2.1 开启 Checkpoint,每隔 5 秒钟做一次 CK
        env.enableCheckpointing(5000L);
        //2.2 指定 CK 的一致性语义
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        //2.3 设置任务关闭的时候保留最后一次 CK 数据
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        //2.4 指定从 CK 自动重启策略
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, 2000L));
        //2.5 设置状态后端
        env.setStateBackend(new FsStateBackend("hdfs://zyVM:9000/flinkCDC"));
        //2.6 设置访问 HDFS 的用户名
        System.setProperty("HADOOP_USER_NAME", "root");

        env.addSource(sourceFunction).print().setParallelism(1); // use parallelism 1 for sink to keep message ordering

        env.execute();
    }
}

