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

    public int executeUpdate(String s) {
        int result = 0;
        System.out.println("--更新语句:"+s+"\n");
        try {
            result = stmt.executeUpdate(s);
        } catch (Exception ex) {
            System.out.println("执行更新错误！");
        }
        return result;
    }

    public ResultSet executeQuery(String s) {
        ResultSet rs = null;
        String sql="select * from nameandpassword where user_name="+"'"+s+"'";//定义一个查询语句
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
     *
     * @param table_name    数据库的表名及参数名   eg:table(id,name,age)
     * @param value         要传入的值           eg:1,sxz,20
     */
    public void execQuery(String table_name,String value){
        String sql="insert into homework_login."+table_name +"values"+"("+value+")";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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