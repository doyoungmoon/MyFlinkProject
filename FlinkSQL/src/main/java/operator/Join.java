package operator;

import bean.TableA;
import bean.TableB;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/10/22 9:41
 */
public class Join {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings environmentSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        env.setParallelism(1);


        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, environmentSettings);

        //默认值为0   表示FlinkSQL中的状态永久保存
        System.out.println(tableEnv.getConfig().getIdleStateRetention());

        //执行FLinkSQL状态保留10秒
//        tableEnv.getConfig().setIdleStateRetention(Duration.ofSeconds(10));


        //2.读取端口数据创建流
        SingleOutputStreamOperator<TableA> aDS = env.socketTextStream("localhost", 8888)
                .map(line -> {
                    String[] split = line.split(",");

                    return new TableA(split[0], split[1]);
                });
        SingleOutputStreamOperator<TableB> bDS = env.socketTextStream("localhost", 9999)
                .map(line -> {
                    String[] split = line.split(",");

                    return new TableB(split[0], split[1]);
                });


        aDS.join(bDS)
                .where(aTable->aTable.getId()).equalTo(bTable->bTable.getId())
//                .where(new KeySelector<TableA, String>() {
//                    @Override
//                    public String getKey(TableA tableA) throws Exception {
//                        return tableA.getId();
//                    }
//                })
//                .equalTo(new KeySelector<TableB, String>() {
//                    @Override
//                    public String getKey(TableB tableB) throws Exception {
//                        return tableB.getId();
//                    }
//                })
               .window(TumblingProcessingTimeWindows.of(Time.seconds(20)))
                .apply(new JoinFunction<TableA, TableB, String>() {
                    @Override
                    public String join(TableA tableA, TableB tableB) throws Exception {
                        return tableA.getId() + "--" + tableA.getName() + "---" + tableB.getId() + "--" + tableB.getClassId();
                    }
//               打印的结果是，同一时间窗口内的，相同的key会进入到这个join方法中，然后a流中的一条数据会与b流中的每条数据进行关联（笛卡尔积进行关联配对），若在本窗口内没有关联到，
//                    则数据会丢失，即一个窗口的数据不能与其他窗口的数据进行关联，关联配对的数据会进入到joinFunction中进行处理。
                }).print().setParallelism(1);
        env.execute();
    }


}