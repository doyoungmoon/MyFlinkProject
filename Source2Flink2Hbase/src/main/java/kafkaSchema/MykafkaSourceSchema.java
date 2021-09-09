package kafkaSchema;

import com.alibaba.fastjson.JSON;
import entity.BenefitInsured;
import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;

import java.io.IOException;

/**
 * @author zy
 * @version 1.0
 * @description:
 *将每条json封装成pojo,生成source中的schema
 * @date 2021/8/9 11:26
 */
public class MykafkaSourceSchema extends AbstractDeserializationSchema<BenefitInsured> {

    @Override
    public BenefitInsured deserialize(byte[] js) throws IOException {
//        JSON.parseObject(message,ContractExtend.class);
        BenefitInsured bi = JSON.parseObject(js, BenefitInsured.class);
        return bi;
    }


//    @Override
//    public TypeInformation<BenefitInsured> getProducedType() {
//        return TypeInformation.of(BenefitInsured.class);
//    }
}
