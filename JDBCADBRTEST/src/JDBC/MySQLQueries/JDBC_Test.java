package JDBC.MySQLQueries;

import java.sql.*;

public class JDBC_Test {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //1. Get connection to the DataBase
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo"
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false"
                + "&serverTimezone=UTC", "root", "root6");

        //2. Create a statement
        statement = connection.createStatement();

        //3. Execute a query
        resultSet = statement.executeQuery("select * from employees");

        //4. Process the result set (customize the query)

        while (resultSet.next()){
            System.out.println(resultSet.getString("last_name") + " , " + resultSet.getString(3));
        }

        if (resultSet != null){
            resultSet.close();
        }
        if (statement != null){
            statement.close();
        }
        if (connection != null){
            connection.close();
        }

    }



}
