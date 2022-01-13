import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.sql.SQLException;

public class ShiciFile extends HttpServlet {
     // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
     static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
     // mydatabase是本地数据库的database
     static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
     // 用户名
     static final String USER = "root";
     // 密码
     static final String PASS = "123456";
     // https://www.runoob.com/java/java-mysql-connect.html
     @Override
     protected void doGet(
       HttpServletRequest request, 
       HttpServletResponse response) throws ServletException, IOException {
 
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         // 建立连接
         Connection conn = null;
         Statement stmt = null;
         try {
           // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
           // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,"");
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT title, author, content FROM gu_shi_tab";
            ResultSet rs = stmt.executeQuery(sql);
           // 展开结果集数据库
            while(rs.next()) {
              // 通过字段检索
              String id  = rs.getString("title");
              String name = rs.getString("author");
              String url = rs.getString("content");
              // 输出数据
              out.println(String.format("<p>userID: %s</p>", id));
              out.println(String.format("<p>name: %s</p>", name));
              out.println(String.format("<p>url: %s</p>", url));       
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
       } catch (SQLException se) {
         // 处理 JDBC 错误
          se.printStackTrace();
       } catch (Exception e) {
          // 处理 Class.forName 错误
          e.printStackTrace();
       } finally {
         // 关闭资源
         try {
           if (stmt != null) stmt.close();
         } catch (SQLException se2) {
           // 什么都不做
         }
         try {
           if (conn != null) conn.close();
         } catch (SQLException se3) {
           // 什么都不做
         }
       }
     }
}
