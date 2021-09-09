package sink;

import entity.BenefitInsured;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/8/9 13:48
 */
public class MyHbaseSink extends RichSinkFunction<BenefitInsured> {
    //    public static org.apache.hadoop.conf.Configuration conf; // 管理Hbase的配置信息

    Configuration conf; // 管理Hbase的配置信息
    Connection connection; // 管理Hbase连接
    String tableName = "test";

    @Override
    public void invoke(BenefitInsured bi, Context context) throws Exception {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(bi.getList_id().getBytes());
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("item"), Bytes.toBytes(bi.getItem_id()));
        table.put(put);
        table.close();
    }

    @Override
    public void open(org.apache.flink.configuration.Configuration parameters) throws Exception {
        conf = HBaseConfiguration.create();

        conf.set("hbase.zookeeper.quorum", "192.168.211.128");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        connection = ConnectionFactory.createConnection(conf);
    }


}
