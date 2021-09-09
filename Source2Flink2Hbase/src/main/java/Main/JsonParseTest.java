package Main;

import org.apache.kafka.common.protocol.types.Field;
import scala.Tuple2;
import utils.JsonParse;

/**
 * @author zy
 * @version 1.0
 * @description: 测试解析昆仑的json是否成功的类
 * @date 2021/8/11 16:04
 */
public class JsonParseTest {
    private Object masterpolno;

    public static void main(String[] args) {
        //update
//        String json = "{\"meta_data\":[{\"name\":{\"string\":\"INFA_SEQUENCE\"},\"value\":{\"string\":\"2,PWX_GENERIC,1,,2,3,D4083BEB507BCD000000000000083BEB507BCC0000000B00025CB4000068DF0084000100000000000100000000,00,000001EAD5D2EBE9\"},\"type\":null},{\"name\":{\"string\":\"INFA_TABLE_NAME\"},\"value\":{\"string\":\"d8oracapt.lcpol_LCPOL\"},\"type\":null},{\"name\":{\"string\":\"INFA_OP_TYPE\"},\"value\":{\"string\":\"UPDATE_EVENT\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXRESTART1\"},\"value\":{\"string\":\"1Ag761B7zQAAAAAAAAg761B7zAAAAAsAAly0AABo3wCEAAEAAAAAAAEAAAAA\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXRESTART2\"},\"value\":{\"string\":\"AAAB6tXS6+k=\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXUOW\"},\"value\":{\"string\":\"MFgwMDBCLjAxOC4wMDBGNjY2OQ==\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXUSER\"},\"value\":{\"string\":\"GGS\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXTIMESTAMP\"},\"value\":{\"string\":\"202108121347430000000000\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXACTION\"},\"value\":{\"string\":\"U\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXROWID\"},\"value\":null,\"type\":null}],\"columns\":{\"array\":[{\"name\":{\"string\":\"GRPCONTNO\"},\"value\":{\"string\":\"00000000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"00000000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"GRPPOLNO\"},\"value\":{\"string\":\"00000000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"00000000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"CONTNO\"},\"value\":{\"string\":\"2021E43081090572\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"2021E43081090572\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"POLNO\"},\"value\":{\"string\":\"21018733686\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"21018733686\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PROPOSALNO\"},\"value\":{\"string\":\"21018733686\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"21018733686\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PRTNO\"},\"value\":{\"string\":\"2021E43081090572\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"2021E43081090572\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"CONTTYPE\"},\"value\":{\"string\":\"1\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"1\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"POLTYPEFLAG\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"MAINPOLNO\"},\"value\":{\"string\":\"21018733686\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"21018733686\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"MASTERPOLNO\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"KINDCODE\"},\"value\":{\"string\":\"H\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"H\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"RISKCODE\"},\"value\":{\"string\":\"I168\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"I168\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"RISKVERSION\"},\"value\":{\"string\":\"2002\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"2002\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"MANAGECOM\"},\"value\":{\"string\":\"86110000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"86110000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"AGENTCOM\"},\"value\":{\"string\":\"1100010950\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"1100010950\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"AGENTTYPE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"AGENTCODE\"},\"value\":{\"string\":\"110010000004\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"110010000004\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"AGENTGROUP\"},\"value\":{\"string\":\"11001000003\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"11001000003\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"AGENTCODE1\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"SALECHNL\"},\"value\":{\"string\":\"09\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"09\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"HANDLER\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"INSUREDNO\"},\"value\":{\"string\":\"008704584\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"008704584\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"INSUREDNAME\"},\"value\":{\"string\":\"张云清\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"张云清\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"INSUREDSEX\"},\"value\":{\"string\":\"1\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"1\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"INSUREDBIRTHDAY\"},\"value\":{\"string\":\"199004020000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"199004020000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"INSUREDAPPAGE\"},\"value\":{\"string\":\"31\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"31\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"INSUREDPEOPLES\"},\"value\":{\"string\":\"1\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"1\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"OCCUPATIONTYPE\"},\"value\":{\"string\":\"1\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"1\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"APPNTNO\"},\"value\":{\"string\":\"008704584\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"008704584\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"APPNTNAME\"},\"value\":{\"string\":\"张云清\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"张云清\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"CVALIDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"202108110000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"SIGNCOM\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"SIGNDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"SIGNTIME\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"FIRSTPAYDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PAYENDDATE\"},\"value\":{\"string\":\"205108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"205108110000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PAYTODATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"GETSTARTDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"202108110000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"ENDDATE\"},\"value\":{\"string\":\"209608110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"209608110000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"ACCIENDDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"GETYEARFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"GETYEAR\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PAYENDYEARFLAG\"},\"value\":{\"string\":\"Y\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"Y\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PAYENDYEAR\"},\"value\":{\"string\":\"30\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"30\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"INSUYEARFLAG\"},\"value\":{\"string\":\"A\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"A\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"INSUYEAR\"},\"value\":{\"string\":\"106\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"106\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"ACCIYEARFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"ACCIYEAR\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"GETSTARTTYPE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"SPECIFYVALIDATE\"},\"value\":{\"string\":\"Y\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"Y\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PAYMODE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PAYLOCATION\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PAYINTV\"},\"value\":{\"string\":\"12\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"12\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PAYYEARS\"},\"value\":{\"string\":\"30\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"30\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"YEARS\"},\"value\":{\"string\":\"75\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"75\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"MANAGEFEERATE\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"FLOATRATE\"},\"value\":{\"string\":\"1.0000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"1.0000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PREMTOAMNT\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"MULT\"},\"value\":{\"string\":\"0.00000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0.00000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"STANDPREM\"},\"value\":{\"string\":\"5508.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"5508.00\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PREM\"},\"value\":{\"string\":\"5508.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"5508.00\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"SUMPREM\"},\"value\":{\"string\":\"5508.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"5508.00\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"AMNT\"},\"value\":{\"string\":\"300000.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"300000.00\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"RISKAMNT\"},\"value\":{\"string\":\"300000.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"300000.00\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"LEAVINGMONEY\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0.00\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"ENDORSETIMES\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"CLAIMTIMES\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"LIVETIMES\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"RENEWCOUNT\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"LASTGETDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"LASTLOANDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"LASTREGETDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"LASTEDORDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"LASTREVDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"202108110000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"RNEWFLAG\"},\"value\":{\"string\":\"-2\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"-2\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"STOPFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"EXPIRYFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"AUTOPAYFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"INTERESTDIFFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"SUBFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"BNFFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"HEALTHCHECKFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"IMPARTFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"REINSUREFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"AGENTPAYFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"AGENTGETFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"LIVEGETMODE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"DEADGETMODE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"BONUSGETMODE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"BONUSMAN\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"DEADFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"SMOKEFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"REMARK\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"APPROVEFLAG\"},\"value\":{\"string\":\"9\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"9\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"APPROVECODE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"APPROVEDATE\"},\"value\":{\"string\":\"202108100000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"202108100000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"APPROVETIME\"},\"value\":{\"string\":\"20:08:20\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"20:08:20\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"UWFLAG\"},\"value\":{\"string\":\"1\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"1\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"UWCODE\"},\"value\":{\"string\":\"000965\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"000965\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"UWDATE\"},\"value\":{\"string\":\"202108120000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"202108120000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"UWTIME\"},\"value\":{\"string\":\"13:47:09\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"13:47:09\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"POLAPPLYDATE\"},\"value\":{\"string\":\"202108100000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"202108100000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"APPFLAG\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"POLSTATE\"},\"value\":{\"string\":\"00019999\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"00019999\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"STANDBYFLAG1\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"STANDBYFLAG2\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"STANDBYFLAG3\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"OPERATOR\"},\"value\":{\"string\":\"wx\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"wx\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"MAKEDATE\"},\"value\":{\"string\":\"202108100000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"202108100000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"MAKETIME\"},\"value\":{\"string\":\"20:08:20\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"20:08:20\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"MODIFYDATE\"},\"value\":{\"string\":\"202108100000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"202108100000000000000000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"MODIFYTIME\"},\"value\":{\"string\":\"20:08:20\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"20:08:20\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"WAITPERIOD\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PAYRULECODE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"ASCRIPTIONRULECODE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"SALECHNLDETAIL\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"RISKSEQNO\"},\"value\":{\"string\":\"1\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"1\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"COPYS\"},\"value\":{\"string\":\"1.00000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"1.00000\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"COMFEERATE\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0.00\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"BRANCHFEERATE\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0.00\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"PROPOSALCONTNO\"},\"value\":{\"string\":\"2021E43081090572\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"2021E43081090572\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"CONTPLANCODE\"},\"value\":{\"string\":\"PC1602419\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"PC1602419\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"CESSAMNT\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0.00\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"STATEFLAG\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"SUPPLEMENTARYPREM\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":{\"string\":\"0.00\"},\"isPresentBeforeImage\":{\"boolean\":true}},{\"name\":{\"string\":\"ACCTYPE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":true}}]}}";
        //insert
//       String json="{\"meta_data\":[{\"name\":{\"string\":\"INFA_SEQUENCE\"},\"value\":{\"string\":\"2,PWX_GENERIC,1,,2,3,D4083BEB3630F6000000000000083BEB3630F50000000100025C9500083F710070000100000000000100000000,00,000001EAD535E4B1\"},\"type\":null},{\"name\":{\"string\":\"INFA_TABLE_NAME\"},\"value\":{\"string\":\"d8oracapt.lpedorit_LPEDORITEM\"},\"type\":null},{\"name\":{\"string\":\"INFA_OP_TYPE\"},\"value\":{\"string\":\"INSERT_EVENT\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXRESTART1\"},\"value\":{\"string\":\"1Ag76zYw9gAAAAAAAAg76zYw9QAAAAEAAlyVAAg/cQBwAAEAAAAAAAEAAAAA\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXRESTART2\"},\"value\":{\"string\":\"AAAB6tU15LE=\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXUOW\"},\"value\":{\"string\":\"MFgwMDBCLjAxQy4wMDBGNUJDNA==\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXUSER\"},\"value\":{\"string\":\"GGS\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXTIMESTAMP\"},\"value\":{\"string\":\"202108111057170000000000\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXACTION\"},\"value\":{\"string\":\"I\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXROWID\"},\"value\":null,\"type\":null}],\"columns\":{\"array\":[{\"name\":{\"string\":\"EDORACCEPTNO\"},\"value\":{\"string\":\"20210811000138\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORNO\"},\"value\":{\"string\":\"20210811000138\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORAPPNO\"},\"value\":{\"string\":\"20210811000138\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORTYPE\"},\"value\":{\"string\":\"XT\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"DISPLAYTYPE\"},\"value\":{\"string\":\"1\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"GRPCONTNO\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"CONTNO\"},\"value\":{\"string\":\"2020E91120918337\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"INSUREDNO\"},\"value\":{\"string\":\"000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"POLNO\"},\"value\":{\"string\":\"000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MANAGECOM\"},\"value\":{\"string\":\"86110000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORVALIDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORAPPDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORSTATE\"},\"value\":{\"string\":\"3\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWOPERATOR\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWTIME\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"CHGPREM\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"CHGAMNT\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"GETMONEY\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"GETINTEREST\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"OPERATOR\"},\"value\":{\"string\":\"BQ0004\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MAKEDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MAKETIME\"},\"value\":{\"string\":\"10:57:13\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MODIFYDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MODIFYTIME\"},\"value\":{\"string\":\"10:57:13\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORREASONNO\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORREASON\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"REASON\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"REASONCODE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVEGRADE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVESTATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVEOPERATOR\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVEDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVETIME\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}}]}}";
//
        String json = "{\"meta_data\":[{\"name\":{\"string\":\"INFA_SEQUENCE\"},\"value\":{\"string\":\"2,PWX_GENERIC,1,,2,3,D4083BEB3630F6000000000000083BEB3630F50000000100025C9500083F710070000100000000000100000000,00,000001EAD535E4B1\"},\"type\":null},{\"name\":{\"string\":\"INFA_TABLE_NAME\"},\"value\":{\"string\":\"d8oracapt.lpedorit_LPEDORITEM\"},\"type\":null},{\"name\":{\"string\":\"INFA_OP_TYPE\"},\"value\":{\"string\":\"INSERT_EVENT\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXRESTART1\"},\"value\":{\"string\":\"1Ag76zYw9gAAAAAAAAg76zYw9QAAAAEAAlyVAAg/cQBwAAEAAAAAAAEAAAAA\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXRESTART2\"},\"value\":{\"string\":\"AAAB6tU15LE=\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXUOW\"},\"value\":{\"string\":\"MFgwMDBCLjAxQy4wMDBGNUJDNA==\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXUSER\"},\"value\":{\"string\":\"GGS\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXTIMESTAMP\"},\"value\":{\"string\":\"202108111057170000000000\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXACTION\"},\"value\":{\"string\":\"I\"},\"type\":null},{\"name\":{\"string\":\"DTL__CAPXROWID\"},\"value\":null,\"type\":null}],\"columns\":{\"array\":[{\"name\":{\"string\":\"EDORACCEPTNO\"},\"value\":{\"string\":\"20210811000138\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORNO\"},\"value\":{\"string\":\"20210811000138\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORAPPNO\"},\"value\":{\"string\":\"20210811000138\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORTYPE\"},\"value\":{\"string\":\"XT\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"DISPLAYTYPE\"},\"value\":{\"string\":\"1\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"GRPCONTNO\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"CONTNO\"},\"value\":{\"string\":\"2020E91120918337\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"INSUREDNO\"},\"value\":{\"string\":\"000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"POLNO\"},\"value\":{\"string\":\"000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MANAGECOM\"},\"value\":{\"string\":\"86110000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORVALIDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORAPPDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORSTATE\"},\"value\":{\"string\":\"3\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWFLAG\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWOPERATOR\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"UWTIME\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"CHGPREM\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"CHGAMNT\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"GETMONEY\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"GETINTEREST\"},\"value\":{\"string\":\"0.00\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"OPERATOR\"},\"value\":{\"string\":\"BQ0004\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MAKEDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MAKETIME\"},\"value\":{\"string\":\"10:57:13\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MODIFYDATE\"},\"value\":{\"string\":\"202108110000000000000000\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"MODIFYTIME\"},\"value\":{\"string\":\"10:57:13\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORREASONNO\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"EDORREASON\"},\"value\":{\"string\":\"0\"},\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"REASON\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"REASONCODE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVEGRADE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVESTATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVEOPERATOR\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVEDATE\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}},{\"name\":{\"string\":\"APPROVETIME\"},\"value\":null,\"isPresent\":{\"boolean\":true},\"beforeImage\":null,\"isPresentBeforeImage\":{\"boolean\":false}}]}}";
//        System.out.println(JsonParse.getMetaMap(json).get("INFA_TABLE_NAME"));
        System.out.println(JsonParse.getClmsMap(json).get("contno")._1);
        System.out.println(JsonParse.getTableName(json));
//        System.out.println(JsonParse.getMetaMap(json).get("INFA_OP_TYPE"));

//        System.out.println(JsonParse.getTableName(json));//没问题


    }

}
