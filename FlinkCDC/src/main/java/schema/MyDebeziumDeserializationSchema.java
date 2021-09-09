package schema;

import com.alibaba.fastjson.JSONObject;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import io.debezium.data.Envelope;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/8/31 20:20
 */

//Debezium数据格式
/*
SourceRecord{
  sourcePartition={server=mysql_binlog_source}, sourceOffset={file=mysql-bin.000003, pos=674, row=1, snapshot=true}
  }
  ConnectRecord{
    topic='mysql_binlog_source.test.test1', kafkaPartition=null, key=null, keySchema=null, value=Struct{after=Struct{name=sss},
    source=Struct{version=1.2.1.Final,connector=mysql,name=mysql_binlog_source,ts_ms=0,snapshot=true,db=test,table=test1,server_id=0,file=mysql-bin.000003,pos=674,row=0},
    op=c,ts_ms=1629992301979
    },
    valueSchema=Schema{mysql_binlog_source.test.test1.Envelope:STRUCT},
    timestamp=null,
    headers=ConnectHeaders(headers=)
  }
*/

public class MyDebeziumDeserializationSchema implements DebeziumDeserializationSchema<String> {

    @Override
    public void deserialize(SourceRecord sourceRecord, Collector collector) throws Exception {
        String topic = sourceRecord.topic();
        String[] arr = topic.split("\\.");
        String db = arr[1];
        String tableName = arr[2];
        //获取操作类型 READ DELETE UPDATE CREATE
        Envelope.Operation operation =
                Envelope.operationFor(sourceRecord);
        //获取值信息并转换为 Struct 类型
        Struct value = (Struct) sourceRecord.value();
        //获取变化后的数据
        Struct after = value.getStruct("after");
        //创建 JSON 对象用于存储数据信息
        JSONObject data = new JSONObject();
        for (Field field : after.schema().fields()) {
            Object o = after.get(field);
            data.put(field.name(), o);
        }
        //创建 JSON 对象用于封装最终返回值数据信息
        JSONObject result = new JSONObject();
        result.put("operation", operation.toString().toLowerCase());
        result.put("data", data);
        result.put("database", db);
        result.put("table", tableName);
        System.out.println(result);
        //发送数据至下游
        collector.collect(result.toJSONString());
    }

    @Override
    public TypeInformation getProducedType() {
        return TypeInformation.of(String.class);

    }
}
