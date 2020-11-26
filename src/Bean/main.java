package Bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        DBBean dbBean=new DBBean();
        dbBean.executeDelete("yzj","nameandpassword","user_name");
        //dbBean.execQuery("nameandpassword(user_name,user_password,age)","'yzj','654321',10");
        //ResultSet resultSet=dbBean.executeQuery("wkr","nameandpassword","user_name");
        /*String password="1234567";
        while (resultSet.next())
        {
            if(password.equals(resultSet.getObject("user_password"))){
                System.out.println(111111);
            }else {
                System.out.println(22222222);
            }
        }*/
    }
}
