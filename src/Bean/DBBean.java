package Bean;
import java.sql.*;


public class DBBean {
    private String driverStr = "com.mysql.jdbc.Driver";
    private String connStr = "jdbc:mysql://localhost:3306/homework_login?serverTimezone=UTC";
    private String dbusername = "root";
    private String dbpassword = "zhege00..";
    private Connection conn = null;
    private Statement stmt = null;

    public DBBean()
    {
        try
        {
            Class.forName(driverStr);
            conn = DriverManager.getConnection(connStr, dbusername, dbpassword);
            stmt = conn.createStatement();
            System.out.println("数据连接成功！");
        }
        catch (Exception ex) {
            System.out.println(ex);
            System.out.println("数据连接失败！");
        }

    }

    /**
     *删除数据库中数据
     * @param value      想要删除的那一列的哪个值
     * @param tableName     想要查询的数据库的表名
     * @param index      想要查询数据库的表的哪一列的名字
     * @return   返回查询到的所有行
     *  eg:   dbBean.executeDelete("wkr","nameandpassword","user_name");
     */
    public int executeDelete(String value,String tableName,String index){
        int rs = 0;
        String sql="delete from "+tableName+" where "+index+"="+"'"+value+"'";//定义一个删除语句
        System.out.print("--删除语句:"+sql+"\n");
        try {
            rs = stmt.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("ִ执行删除错误！");
        }
        return rs;
    }

    /**
     * 更新数据库中的数据（如果值为字符串类型，需要加单引号‘’）
     * @param index_value   查询那一列的值
     * @param tableName     想要更新的表名
     * @param index         查询哪一列
     * @param target_value  想要更改后的值。如果值为字符串类型，需要加单引号‘’
     * @param target_index  想要修改表单的哪一列值
     * @return
     * dbBean.executeUpdate(" ' wkr ' ", " nameandpassword ",
     *                 "user_name","654321","user_password");
     */
    public int executeUpdate(String index_value,String tableName,String index,String target_value,String target_index) {
        int result = 0;
        String sql="update "+tableName+" set "+target_index+"="+target_value+" where "+index+"="+index_value;
        System.out.println("--更新语句:"+sql+"\n");
        try {
            result = stmt.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("执行更新错误！");
        }
        return result;
    }


    /**
     *查询数据库中数据
     * @param value      想要查询的值
     * @param tableName     想要查询的数据库的表名
     * @param index      想要查询数据库的表的哪一列的名字
     * @return   返回查询到的所有行
     *  eg:   dbBean.executeQuery("wkr","nameandpassword","user_name");
     */
    public ResultSet executeFind(String value, String tableName, String index) {
        ResultSet rs = null;
        String sql="select * from "+tableName+" where "+index+"="+"'"+value+"'";//定义一个查询语句
        System.out.print("--查询语句:"+sql+"\n");
        try {
            rs = stmt.executeQuery(sql);
            System.out.println(rs);
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("ִ执行查询错误！");
        }
        return rs;
    }

    /**
     *向数据库中插入一个数据
     * @param table_name    数据库的表名及参数名   eg:table(id,name,age)
     * @param value         要传入的值   字符串需要打单引号          eg:1,'sxz',20
     * dbBean.execQuery("nameandpassword(user_name,user_password,age)","'yzj','654321',10");
     */
    public void executeQuery(String table_name, String value){
        String sql="insert into homework_login."+table_name +"values"+"("+value+")";//定义一个插入语句
        System.out.println(sql);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
            System.out.println("执行插入错误！");
        }
    }

    public void close() {
        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
        }
    }
}