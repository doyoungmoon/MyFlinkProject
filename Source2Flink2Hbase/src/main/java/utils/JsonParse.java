package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import scala.Tuple2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/8/11 11:40
 */
public class JsonParse {
    //得到json中meta数组

    //     "meta_data":[
//        {
//            "name":{
//                "string":"INFA_SEQUENCE"
//            },
//            "value":{
//                "string":"2,PWX_GENERIC,1,,2,3,D4083BEB3630F6000000000000083BEB3630F50000000100025C9500083F710070000100000000000100000000,00,000001EAD535E4B1"
//            },
//            "type":null
//        }
//        ]
     static Map<String, String> metaMap = new HashMap<>();

    public static Map<String, String> getMetaMap(String json) {
        metaMap.clear();
        JSONArray metaArr = JSON.parseObject(json).getJSONArray("meta_data");
//        Map<String, String> metaMap = new HashMap<>();


        for (Object obj : metaArr) {//遍历metaArr中的每个值，值也是json，需解析
            JSONObject js = JSON.parseObject(String.valueOf(obj));//得到meta中的一个值，并解析成json

            JSONObject name = js.getJSONObject("name");
            String key = name.getString("string");//得到name属性对应的值，即fielName

            //开始解析出字段值，需要注意字段值可能为空，需要做判断
            JSONObject val = js.getJSONObject("value");

            String value;
            if (val != null) {
                value = val.getString("string");
            } else {
                value = "";
            }

            metaMap.put(key, value);
        }
        return metaMap;
    }


//    "columns": {
//        "array": [{
//            "name": {
//                "string": "GRPCONTNO"  //表中的列*
//            },
//            "value": {
//                "string": "2020310018000034796" //更新或插入后列对应的值*
//            },
//            "isPresent": {
//                "boolean": true
//            },
//            "beforeImage": {
//                "string": "2020310018000034796"  //更新或插入前列对应的值*
//            },
//            "isPresentBeforeImage": {
//                "boolean": true
//            }
//        }
//        ]
static Map<String, Tuple2<String, String>> clmsMap = new HashMap<>();
    public static Map<String, Tuple2<String, String>> getClmsMap(String json) {
        clmsMap.clear();
        JSONArray columnsArr = JSON.parseObject(json).getJSONObject("columns").getJSONArray("array");
//        Map<String, Tuple2<String, String>> clmsMap = new HashMap<>();
        for (Object obj : columnsArr) {
            JSONObject js = JSON.parseObject(String.valueOf(obj));
            String fieldName = js.getJSONObject("name").getString("string").toLowerCase();

            JSONObject val = js.getJSONObject("value");
            JSONObject beforeVal = js.getJSONObject("beforeImage");

            String value;  //更新后的值
            String beforeValue;//更新前的值

            if (val != null) {
                value = val.getString("string");
            } else {
                value = " ";
            }

            if (beforeVal != null) {
                beforeValue = beforeVal.getString("string");
            } else {
                beforeValue = " ";
            }

            clmsMap.put(fieldName, new Tuple2<>(value, beforeValue));
        }

        return clmsMap;
    }

    public static String getTableName(String json) {
        String table = getMetaMap(json).get("INFA_TABLE_NAME");
        String tableName = table.split("_")[1].toLowerCase();
        return tableName;
    }

    //得到json中字段数组
   /* private JSONArray getClmsArray() {
        JSONArray clms = JSON.parseObject(json).getJSONObject("columns").getJSONArray("array");

//        JSONObject jsonobj = JSON.parseObject(json);
//        JSONObject columnsArr = jsonobj.getJSONObject("columns");
//        JSONArray clms = columnsArr.getJSONArray("array");//得到字段的数组
        return clms;
    }*/

    //得到表名
   /* public String getTableName() {
        JSONObject tablenNameKV = getMetaArr().getJSONObject(1);//得到表明这个键值对
        String tableName = tablenNameKV.getJSONObject("value").get("string").toString().split("_")[1].toLowerCase();
        return tableName;
    }

    //得到操作类型
    public String getOPType() {
        JSONObject opKV = getMetaArr().getJSONObject(2);//得到操作类型键值对
        String opType = opKV.getJSONObject("value").get("string").toString().split("_")[0];
        return opType;
    }

    //得到时间戳
    public String getEventTime() {
        JSONObject timestampKV = getMetaArr().getJSONObject(7);//得到时间键值对
        String eventTime = (String) timestampKV.getJSONObject("value").get("string");
        return eventTime;
    }*/

    //得到第num个字段名，json中的字段名顺序应该与entity类中的字段名顺序相同
  /*  public String getFieldName(int num) {
        String field = (String) getClmsArray().getJSONObject(num - 1).getJSONObject("name").get("string");
        return field;
    }

    //    得到第num个字段对应的值
    public String getValue(int num) {
//        String value = (String) getClmsArray().getJSONObject(num - 1).getJSONObject("value").get("string");
        JSONObject value = getClmsArray().getJSONObject(num - 1).getJSONObject("value");

        return (null != value) ? (String) value.get("string") : "";
//        return value;
    }
*/
  /*  public static void main(String[] args) {
        String myjson = "{\"meta_data\":[{\"name\":{\"string\":\"INFA_SEQUENCE\"},\"value\":{\"string\":\"2,PWX_GENERIC,1,,2,3,D4083BEB3630F6000000000000083BEB3630F50000000100025C9500083F710070000100000000000100000000,00,000001EAD535E4B1\"},\"type\":null},{\"name\":{\"string\":\"INFA_TABLE_NAME\"},\"value\":{\"string\":\"d8oracapt.lpedorit_LPEDORITEM\"},\"type\":null},{\"name\":{\"string\":\"INFA_OP_TYPE\"},\"value\":{\"string\":\"INSERT_EVENT\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXRESTART1\"},\"value\":{\"string\":\"1Ag76zYw9gAAAAAAAAg76zYw9QAAAAEAAlyVAAg/cQBwAAEAAAAAAAEAAAAA\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXRESTART2\"},\"value\":{\"string\":\"AAAB6tU15LE=\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXUOW\"},\"value\":{\"string\":\"MFgwMDBCLjAxQy4wMDBGNUJDNA==\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXUSER\"},\"value\":{\"string\":\"GGS\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXTIMESTAMP\"},\"value\":{\"string\":\"202108111057170000000000\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXACTION\"},\"value\":{\"string\":\"I\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXROWID\"},\"value\":null,\"type\":null}],\"columns\":{\"array\":[{\"name\":{\"string\":\"EDORACCEPTNO\"},\"value\":{\"string\":\"20210811000138\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORNO\"},\"value\":{\"string\":\"20210811000138\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORAPPNO\"},\"value\":{\"string\":\"20210811000138\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORTYPE\"},\"value\":{\"string\":\"XT\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"DISPLAYTYPE\"},\"value\":{\"string\":\"1\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"GRPCONTNO\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"CONTNO\"},\"value\":{\"string\":\"2020E91120918337\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"INSUREDNO\"},\"value\":{\"string\":\"000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"POLNO\"},\"value\":{\"string\":\"000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MANAGECOM\"},\"value\":{\"string\":\"86110000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORVALIDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORAPPDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORSTATE\"},\"value\":{\"string\":\"3\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWOPERATOR\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWTIME\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"CHGPREM\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"CHGAMNT\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"GETMONEY\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"GETINTEREST\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"OPERATOR\"},\"value\":{\"string\":\"BQ0004\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MAKEDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MAKETIME\"},\"value\":{\"string\":\"10:57:13\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MODIFYDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MODIFYTIME\"},\"value\":{\"string\":\"10:57:13\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORREASONNO\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORREASON\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"REASON\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"REASONCODE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVEGRADE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVESTATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVEOPERATOR\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVEDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVETIME\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}}]}}";


        JSONObject jsonobj = JSON.parseObject(myjson);
        JSONArray metaArr = jsonobj.getJSONArray("meta_data");

        JSONObject tablenNameKV = metaArr.getJSONObject(1);//得到表名这个键值对
        String tableName = tablenNameKV.getJSONObject("value").get("string").toString().split("_")[1];


        JSONObject opKV = metaArr.getJSONObject(2);//得到操作类型键值对
        String opType = opKV.getJSONObject("value").get("string").toString().split("_")[0];//得到操作类型
//        System.out.println(opType);

        JSONObject timestampKV = metaArr.getJSONObject(7);//得到时间键值对
        String timestamp = (String) timestampKV.getJSONObject("value").get("string");


        JSONObject columnsArr = jsonobj.getJSONObject("columns");
        JSONArray clms = columnsArr.getJSONArray("array");//得到字段的数组

        String field1 = (String) clms.getJSONObject(1).getJSONObject("name").get("string");
        JSONObject value = clms.getJSONObject(1).getJSONObject("value");
        System.out.println(field1);
        System.out.println(value);
        String test=(null != value) ? (String) value.get("string") :"" ;
        System.out.println(test);
//        System.out.println(value.get("string"));
//        return (null != value) ? "" : (String) value.get("string");



    }*/


}
