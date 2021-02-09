package test2;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCPDataSourceExample {

	public static void main(String[] args) throws IOException, PropertyVetoException, InterruptedException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
//        	for(int i = 0; i < 15; i++) {
            connection = DataSourceTest.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Test");
             while (resultSet.next()) {
                 System.out.println("id: " + resultSet.getString("ID"));
                 System.out.println("name: " + resultSet.getString("NAME"));
             }
//             Thread.sleep(10000);
//        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
            if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
            if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
        }

    }

}