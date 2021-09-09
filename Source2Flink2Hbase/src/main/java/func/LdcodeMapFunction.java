package func;

import entity.Ldcode;
import org.apache.flink.api.common.functions.RichMapFunction;
import utils.JsonParse;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/8/12 13:53
 */
public class LdcodeMapFunction extends RichMapFunction<String, Ldcode> {

    @Override
    public Ldcode map(String s) throws Exception {
        //进来的是json，解析json与pojo的字段对应，并封装

        Ldcode ldcode = new Ldcode();

//        给ldcode对象赋值
        //        codetype;
        //        code;
        //        codename;
        //        codealias;

        ldcode.setCodetype(JsonParse.getClmsMap(s).get("codetype")._1);
        ldcode.setCode(JsonParse.getClmsMap(s).get("code")._1);//"0"
        ldcode.setCodename(JsonParse.getClmsMap(s).get("codename")._1);
        ldcode.setCodealias(JsonParse.getClmsMap(s).get("codealias")._1);
//        ldcode.setCodetype(jsonParse.getValue(1));//capital_lock_send
//        ldcode.setCode(jsonParse.getValue(2));//"0"
//        ldcode.setCodename(jsonParse.getValue(3));//"资金手动报盘锁开关"
//        ldcode.setCodealias(jsonParse.getValue(4));//"2021-08-12 13:47:33"
//
//        System.out.println(jsonParse.getValue(1));
//        System.out.println(jsonParse.getValue(2));
//        System.out.println(jsonParse.getValue(3));
//        System.out.println(jsonParse.getValue(4));
//        System.out.println("-----------------");
//        ldcode ldcode = JSON.parseObject(s, ldcode.class);
        return ldcode;
    }
}
