package Bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        DBBean dbBean=new DBBean();
        ResultSet resultSet=dbBean.executeQuery("sxz");

        String password="1234567";
        if(resultSet.next())
        {
            if(password.equals(resultSet.getObject("user_password"))){
                System.out.println(111111);
            }
        }

    }
}
