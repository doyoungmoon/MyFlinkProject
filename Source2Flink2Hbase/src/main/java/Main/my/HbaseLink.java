package Main.my;

import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/7/20 13:05
 */
public class HbaseLink {


    public static Configuration conf; // 管理Hbase的配置信息
    public static Connection connection; // 管理Hbase连接
    public static Admin admin; // 管理Hbase数据库的信息


    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("shit");
        init();
        String colF[] = {"score"};
        createTable("stu41", colF);
        insertData("stu41", "zhangsan", "score", "English", "69");
        insertData("stu41", "lisi", "score", "English", "69");
        getData("stu41", "zhangsan", "score", "English");
        close();
    }

    public static void init() {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.211.128");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
//        configuration.set("hbase.master", "192.168.211.128:16000");
        try {
            connection = ConnectionFactory.createConnection(conf);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createTable(String myTableName, String[] colFamily) throws IOException {
        TableName tableName = TableName.valueOf(myTableName);
        if (admin.tableExists(tableName)) {
            System.out.println("Table exists");
        } else {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for (String str : colFamily) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(str);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
        }
    }

    // 添加单元格数据
    /*
     * @param tableName 表名
     * @param rowKey 行键
     * @param colFamily 列族
     * @param col 列限定符
     * @param val 数据
     * @thorws Exception
     * */
    public static void insertData(String tableName, String rowKey, String colFamily, String col, String val) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes());
        put.addColumn(colFamily.getBytes(), col.getBytes(), val.getBytes());
        table.put(put);
        table.close();
    }

    //浏览数据
    /*
     * @param tableName 表名
     * @param rowKey 行
     * @param colFamily 列族
     * @param col 列限定符
     * @throw IOException
     * */
    public static void getData(String tableName, String rowKey, String colFamily, String col) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        get.addColumn(colFamily.getBytes(), col.getBytes());
        Result result = table.get(get);
        System.out.println(new String(result.getValue(colFamily.getBytes(), col == null ? null : col.getBytes())));
        table.close();
    }


    // 操作数据库之后，关闭连接
    public static void close() {
        try {
            if (admin != null) {
                admin.close(); // 退出用户
            }
            if (null != connection) {
                connection.close(); // 关闭连接
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //删除表
    public static void deleteTable(String tableName) {

        try {
            TableName tablename = TableName.valueOf(tableName);
            admin = connection.getAdmin();
            admin.disableTable(tablename);
            admin.deleteTable(tablename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


