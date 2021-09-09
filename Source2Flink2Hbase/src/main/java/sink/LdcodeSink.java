package sink;

import entity.Ldcode;

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
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
 * @date 2021/8/12 13:58
 */
public class LdcodeSink extends RichSinkFunction<Ldcode> {
    Configuration conf; // 管理Hbase的配置信息
    Connection connection; // 管理Hbase连接
    String tableName = "ldcode";

    @Override
    public void invoke(Ldcode ldcode, Context context) throws Exception {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put((ldcode.getCodetype()+"_"+ldcode.getCode()).getBytes());

        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("codename"), Bytes.toBytes(ldcode.getCodename()));
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("codealias"), Bytes.toBytes(ldcode.getCodealias()));

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
