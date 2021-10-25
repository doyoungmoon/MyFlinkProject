package operator;

import bean.TableA;
import bean.TableB;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/10/21 11:18
 */
public class Flink_SQL_join {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings environmentSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        env.setParallelism(1);


        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env,environmentSettings);

        //默认值为0   表示FlinkSQL中的状态永久保存
        System.out.println(tableEnv.getConfig().getIdleStateRetention());

        //执行FLinkSQL状态保留10秒
//        tableEnv.getConfig().setIdleStateRetention(Duration.ofSeconds(10));


        //2.读取端口数据创建流
        SingleOutputStreamOperator<TableA> aDS = env.socketTextStream("localhost", 8888)
                .map(line -> {
                    String[] split = line.split(",");
                    System.out.println(split.toString());
                    return new TableA(split[0], split[1]);
                });
        SingleOutputStreamOperator<TableB> bDS = env.socketTextStream("localhost", 9999)
                .map(line -> {
                    String[] split = line.split(",");
                    System.out.println(split.toString());
                    return new TableB(split[0],split[1]);
                });

        // 3. 将流转化为动态表
        tableEnv.createTemporaryView("tabA", aDS);
        tableEnv.createTemporaryView("tabB", bDS);
        // 4. 双流join
        tableEnv.sqlQuery("select * from tabA a left join tabB b on a.id = b.id and a.name>b.classId").execute().print();

        env.execute();

    }
}
