package JDBC.MySQLQueries;

import java.sql.*;

public class JDBC_PreparedStatement {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;

        // 1. Get a connection to the Database
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo"
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false"
                + "&serverTimezone=UTC", "root", "root6");

        // 2. Prepare the Statement
        preparedStatement = connection.prepareStatement("select * from employees where salary > ? and department=?");
        //3. Set the parameters
        preparedStatement.setDouble(1, 80000);
        preparedStatement.setString(2, "HR");

        //4. Execute the query
        resultSet = preparedStatement.executeQuery();

        //5. Display the resultSet
        display(resultSet);

        // Reuse the prepared statement:  salary > 25000,  department = HR

        System.out.println("\nPrepared Statement for salary > 25000,  department = HR \n");
        //6. Set the parameters
        preparedStatement.setDouble(1, 60000);
        preparedStatement.setString(2, "IT");

        //Execute the new query
        resultSet = preparedStatement.executeQuery();

        //7. Display the new results
        display(resultSet);
    }

    private static void display(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");
            double salary = resultSet.getDouble("salary");
            String department = resultSet.getString("department");

            System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
        }
    }
}
