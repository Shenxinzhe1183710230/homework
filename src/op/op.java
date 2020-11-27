package op;

import Bean.DBBean;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.Vector;

public class op {

    public static float caculateAllPrice(Vector<Vector<Object>>item) {
        float sum=0;
        for(Vector<Object> x:item) {
            sum=sum+Float.valueOf((String) x.get(3));
        }
        return sum;
    }


    /**
     * 状态int标号转String说明
     * @param StateID 状态编号
     * @return String说明
     */
    public static String StateConvert(String StateID){
        switch (StateID){
            case "1": return "待审核";
            case "2": return "代收款";
            case "3": return "退货";
            case "4": return "已完成";
            default: return "Wrong";
        }
    }


    public static String TotalProfit(Vector<Vector<Object>> orderdata, Vector<Vector<Object>> itemdata){
        return null;
    }

}
