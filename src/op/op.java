package op;

import java.util.Vector;

public class op {

    public static float caculateAllPrice(Vector<Vector<Object>>item) {
        float sum=0;
        for(Vector<Object> x:item) {
            sum=sum+Float.valueOf((String) x.get(3));
        }
        return sum;
    }

}
