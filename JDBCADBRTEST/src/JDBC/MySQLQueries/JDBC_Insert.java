package JDBC.MySQLQueries;

import java.sql.*;

public class JDBC_Insert {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //1. get connected to the database
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo"
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false"
                + "&serverTimezone=UTC", "root", "root6");

        System.out.println("Database connection is successfully achieved!");

        //2. create a statement
        statement = connection.createStatement();

        //3. insert a new employee in the database

        System.out.println("Inserting a new employee in the database!");

        int rowsAffected =
        statement.executeUpdate("insert into employees" +
                "(last_name, first_name, email, department, salary)" +
                "values " +
                "('Jackson', 'Nelson', 'nelsonjackson@gmail.com', 'HR', 100000.00)");

        System.out.println("Insert complete!");
        System.out.println("The number of rows affected is : " + rowsAffected);

        //4. verify update

        resultSet = statement.executeQuery("select * from employees order by last_name");

        // Process the resultSet

        while (resultSet.next()){
            System.out.println(resultSet.getString(3) + " , " + resultSet.getString(2));
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
