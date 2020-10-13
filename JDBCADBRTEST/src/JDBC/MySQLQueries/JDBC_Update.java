package JDBC.MySQLQueries;

import java.sql.*;

public class JDBC_Update {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //1. Make a connection to the database
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo" +
                "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false"
                + "&serverTimezone=UTC", "root", "root6");
        //2. Make a statement
        statement = connection.createStatement();

        //3. Before Update Display Employee Info
        System.out.println("Before the UPDATE......");

        //helper method
        displayEmployee(connection, "Adil", "Soyuer3");

        //3. Update the employee
        int rowsAffected = statement.executeUpdate("update employees set last_name='Soyuer2' where first_name='Adil'");

        System.out.println("The number of rows affected : " + rowsAffected);


        //4. Process the result

        System.out.println("After the UPDATE......");

        displayEmployee(connection, "Adil", "Soyuer2");

        close(connection, statement, resultSet);

//        resultSet = statement.executeQuery("select * from employees");
//        while (resultSet.next()){
//            String firstName = resultSet.getString("first_name");
//            String lastName = resultSet.getString("last_name");
//            String department = resultSet.getString("department");
//            double salary = resultSet.getDouble("salary");
//
//            System.out.printf("\n%s, %s, %s, %.2f\n", firstName, lastName, department, salary);
//        }

    }

    private static void displayEmployee(Connection connection, String firstName, String lastName) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //1. Prepare the Statement
        //use parameter placeholder ?
        preparedStatement = connection.prepareStatement("select last_name, first_name, email from employees where first_name=? and last_name=?");
        //set parameters
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);

        //2. Execute the query
        resultSet = preparedStatement.executeQuery();

        //3. Process the results
        while (resultSet.next()){
            String theFirstName = resultSet.getString("first_name");
            String theLastName = resultSet.getString("last_name");
            String theEmail = resultSet.getString("email");

            System.out.printf("\n%s, %s, %s\n", theFirstName, theLastName, theEmail);
        }
    }

    private static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet !=null){
            resultSet.close();
        }

        if (statement !=null){
            statement.close();
        }
        if (connection !=null){
            connection.close();
        }

    }
}
