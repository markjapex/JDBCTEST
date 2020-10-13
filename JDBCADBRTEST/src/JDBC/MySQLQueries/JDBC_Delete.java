package JDBC.MySQLQueries;

import java.sql.*;
import java.sql.PreparedStatement;

public class JDBC_Delete {

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //1. Get connection to the database
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo"
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false"
                + "&serverTimezone=UTC", "root", "root6");

        System.out.println("The database connection is successfully achieved!");

        //2. Make a statement
        statement = connection.createStatement();

        //3. Before delete, display the employee's info

        System.out.println("Before the delete, the employee info!...");
        //helper method
        displayEmployee(connection, "Jackson", "Nelson");

        //4. Delete the employee
        System.out.println("\nThe employee is being deleted...!\n");

        int rowsAffected = statement.executeUpdate(
                "delete from employees where last_name='Nelson' and first_name='Jackson'"
        );

        System.out.println("The number of rows affected is: " + rowsAffected);

        //5. Verify Update
        System.out.println("After the delete...");
        //helper method
        displayEmployee(connection, "Jackson", "Nelson");

        //close
        close(connection, statement, resultSet);

    }

    private static void displayEmployee(Connection connection, String firstName, String lastName) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //1. Prepare statement (connect and make a statement)
        preparedStatement = connection.prepareStatement("select last_name, first_name, email from employees where last_name=? and first_name=?");
        preparedStatement.setString(1, lastName);
        preparedStatement.setString(2, firstName);


        //2. Execute the query
        resultSet = preparedStatement.executeQuery();

        //3. Process the result

        boolean found = false;

//        System.out.println(resultSet.getString("last_name") + " , " + resultSet.getString(3), resultSet...);
        while (resultSet.next()){
            String theLastName = resultSet.getString("last_name");
            String theFirstName = resultSet.getString("first_name");
            String email = resultSet.getString("email");

            System.out.printf("%s, %s, %s\n", theFirstName, theLastName, email);

            found = true;
        }

        if (!found){
            System.out.println("The employee was NOT FOUND! " + firstName + " " + lastName);
        }

        //helper method
        close(preparedStatement, resultSet);
    }

    private static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
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

    private static void close(Statement statement, ResultSet resultSet) throws SQLException {
        close(null, statement, resultSet);
    }


}
