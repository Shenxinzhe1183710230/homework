package op;

import Bean.DBBean;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class op {

    /**
     * 新增订单时候，计算新增的订单中的所有商品的总价钱
     * @param item   新增订单中的商品列表
     * @return  返回总价钱
     */
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


    /**
     * 计算所有订单的净利润
     * @param orderdata    所有订单数据
     * @param db
     * @return
     */
    public static String TotalProfit(Vector<Vector<Object>> orderdata,DBBean db){
        float sum=0;
        ResultSet tmp;
        for (Vector<Object> x:orderdata) {
            String items=(String) x.get(1);
            String tmp1[]=items.split("<|>");
            float inPriceAll=0;

            //算总开销
            for(String x1:tmp1) {
                if(!x1.equals("")) {
                    String tmp2[]=x1.split(",");
                    tmp=db.executeFind(tmp2[0], "itemmanager", "name");
                    try {
                        tmp.next();
                        System.out.println(String.valueOf(tmp.getObject("inprice")));
                        String inprice=String.valueOf(tmp.getObject("inprice"));
                        inPriceAll+=Float.valueOf(inprice)*Integer.valueOf(tmp2[1]);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            sum+=Float.valueOf(String.valueOf(x.get(3)))-inPriceAll;
        }
        return String.valueOf(sum);
    }

}
