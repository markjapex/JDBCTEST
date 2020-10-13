package JDBC.MySQLQueries;

import java.sql.*;


public class JDBC_PreparedStatement2 {
     //**
     //
     // It's a precompiled SQL Statement
     //     makes it easier to SET SQL Parameter values
     //     Prevent against SQL dependency injection attacks
     //     may improve application performance as SQL statement is precompiled
     //

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        // 1. Get Connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo"
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false"
                + "&serverTimezone=UTC", "root", "root6");

        // 2. Prepare the statement
        //use parameter placeholder '?'
        preparedStatement = connection.prepareStatement("select * from employees where salary > ? and department = ? ");

        // 3. Set the parameters
        preparedStatement.setDouble(1, 60000);
        preparedStatement.setString(2, "HR");

        // 4. Execute the query
        resultSet = preparedStatement.executeQuery();

        // 5. Display the result

        //helper method
        displayQueryResults(resultSet);

        // 6. Reuse the prepared statement

        System.out.println("Printing the new prepared statement...!!!");
        preparedStatement.setDouble(1, 30000);
        preparedStatement.setString(2, "Legal");

        resultSet = preparedStatement.executeQuery();

        //helper method
        displayQueryResults(resultSet);
    }

    private static void displayQueryResults(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String department = resultSet.getString("department");
            String email = resultSet.getString("email");
            double salary = resultSet.getDouble("salary");

            System.out.printf("\n%s, %s, %s, %s, %.2f\n", firstName, lastName, department, email, salary);

        }
    }



}
