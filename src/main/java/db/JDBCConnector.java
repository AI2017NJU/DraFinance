package db;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.List;

/**
 * Created by stonezhang on 2016/12/21.
 */
public class JDBCConnector {
    private String user;
    private String password;
    private String schema;
    private Connection conn;

    public JDBCConnector(String user, String password, String schema) {
        try {
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("成功加载MySQL驱动！");
        } catch (ClassNotFoundException e1) {
//            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }
        String url = "jdbc:mysql://139.199.90.26:3306/" + schema + "?useUnicode=true&characterEncoding=UTF-8";    //JDBC的URL
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, user, password);
            this.conn = conn;
            this.conn.setAutoCommit(false);
        } catch (Exception e) {
            this.conn = null;
            e.printStackTrace();
        }
    }

    public JSONArray select(String select) {
//        System.out.println(select);
        JSONArray array = new JSONArray();
        if(this.conn != null) {
//            List<Object> list = new ArrayList<Object>();
            try {

                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(select);

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    JSONObject jsonObj = new JSONObject();
//                System.out.println("fetching");

                    // 遍历每一列
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName =metaData.getColumnLabel(i);
                        String value = rs.getString(columnName);
                        jsonObj.put(columnName, value);
                    }
                    array.put(jsonObj);
                }
                conn.commit();
                stmt.close();
            } catch (SQLException e) {
                try {
                    //An error occured so we rollback the changes.
                    conn.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }

            return array;
        }
        else {
            return null;
        }
    }

    public void modify(String sql) {
        if(this.conn != null) {
            try{
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                conn.commit();
                stmt.close();
            } catch (SQLException e) {
                try {
                    //An error occured so we rollback the changes.
                    conn.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
    }

    public void modifyWithLock(String sql) throws SQLException {
        if(this.conn != null) {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
    }

    public JSONArray selectWithLock(String sql) throws SQLException {
        JSONArray array = new JSONArray();
        if(this.conn != null) {
//            List<Object> list = new ArrayList<Object>();

            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                JSONObject jsonObj = new JSONObject();

                // 遍历每一列
                for (int i = 1; i <= columnCount; i++) {
                    String columnName =metaData.getColumnLabel(i);
                    String value = rs.getString(columnName);
                    jsonObj.put(columnName, value);
                }
                array.put(jsonObj);
            }

            stmt.close();
            return array;
        }
        else {
            return null;
        }
    }

    public void commit() throws SQLException {
        if(conn != null) {
            conn.commit();
        }
    }

    public void rollback() throws SQLException {
        if(conn != null) {
            conn.rollback();
        }
    }

    public void transaction(List<String> sqlList) {
        if(this.conn != null) {
            try {
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                for(String sql: sqlList) {
//                System.out.println(sql);
                    stmt.executeUpdate(sql);
                }
                conn.commit();
                stmt.close();
            } catch (SQLException e) {
                try {
                    //An error occured so we rollback the changes.
                    conn.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
    }

    public void close() throws SQLException{
        if(this.conn != null) {

            this.conn.close();
        }
    }

    public boolean isConnecting() {
        return this.conn != null;
    }
}
