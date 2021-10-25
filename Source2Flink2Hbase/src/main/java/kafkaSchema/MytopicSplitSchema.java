package kafkaSchema;

import jdk.internal.util.xml.impl.ReaderUTF8;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;
import utils.JsonParse;

import javax.annotation.Nullable;

/**
 * @author zy
 * @version 1.0
 * @description: 接受总topic的kafka消息，解析消息字符串，得到表名，并根据表名发到对应的topic中
 * @date 2021/8/12 9:52
 */
public class MytopicSplitSchema implements KafkaSerializationSchema<String> {

    @Override
    public ProducerRecord<byte[], byte[]> serialize(String s, @Nullable Long aLong) {

        //解析json字符串，得到表名；
        String tablename = JsonParse.getTableName(s);


        System.out.println("-----------------" + tablename + "-------------------------------");
        System.out.println(s);
//
        return new ProducerRecord(tablename, s.getBytes());
//        if (tablename.equals("ldcode")) {
//            return new ProducerRecord(tablename, s.getBytes());
//        } else {
//            return new ProducerRecord("another", s.getBytes());
//        }

    }
}
