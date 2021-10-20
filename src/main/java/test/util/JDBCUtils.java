/*
 * 改进的访问MySQL数据库的通用Java类MyDb
 * 在使用本类的程序里，不需要创建MyDb的实例对象，避免了对服务器的重复连接
 * 定义了一个获取MyDb实例对象的静态方法getMyDb()
 * 两个主要的支持参数式查询的方法：选择查询方法query()和操作查询方法cud()
 */
package test.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class JDBCUtils { // 类定义
    private Connection conn = null;
    private PreparedStatement pst = null; // 参数式查询必须
    private static JDBCUtils mydb = null;
    private JDBCUtils() throws Exception {    // 私有的构造方法，外部不能创建实例
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/student?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "184520";
        conn = DriverManager.getConnection(url, username, password); // 创建链接对象
    }
    public static JDBCUtils getMyDb() throws Exception{
        if(mydb==null)   //单例
            mydb=new JDBCUtils();  //单例模式避免了对数据库服务器重复的连接
        return  mydb;
    }
    public ResultSet query(String sql, Object... args) throws Exception { // 选择查询方法
        // SQL命令中含有通配符，使用可变参数使得可以传离散或数组两种方式的参数
        pst = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pst.setObject(i + 1, args[i]);
        }
        return pst.executeQuery();
    }
    public boolean cud(String sql, Object... args) throws Exception { // 增加_c，修改_u，删除_d
        pst = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pst.setObject(i + 1, args[i]);
        }
        return pst.executeUpdate() >= 1 ? true : false; // 返回操作查询是否成功？
    }
    public void closeConn() throws Exception { // 关闭数据库访问方法
        if (pst != null && !pst.isClosed())
            pst.close();
        if (conn != null && !conn.isClosed())
            conn.close();
    }
}
