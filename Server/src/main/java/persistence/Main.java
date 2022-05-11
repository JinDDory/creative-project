package persistence;

import java.sql.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String args[]){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/database?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            //String url = "jdbc:mysql://localhost/database";
            conn = DriverManager.getConnection(url, "root", "hoon@0815!!");

            String query = "SELECT * FROM ADMIN";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                String id = rs.getString("admin_id");
                String password = rs.getString("admin_password");
                String name = rs.getString("admin_name");
                System.out.printf("%s | %s | %s \n", id, password, name);
                System.out.println("-------------------------------------");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e){
            System.out.println("error : " + e);
        }  finally{
            try{
                if(conn != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    rs.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}