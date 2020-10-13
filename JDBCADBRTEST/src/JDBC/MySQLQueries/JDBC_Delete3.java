package JDBC.MySQLQueries;

import java.sql.*;


public class JDBC_Delete3 {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //1. get connected to the Database
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo"
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false"
                + "&serverTimezone=UTC", "root", "root6");

        //2. Make a statement (optional)--delete
        statement = connection.createStatement();

        //3. Execute the query (optional)
        statement.executeUpdate("delete from employees where last_name='Jackson' and first_name='Nelson'");

        //4. Process the resultSet (output for the query)

        resultSet = statement.executeQuery("select * from employees order by last_name");


        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String department = resultSet.getString("department");
            double salary = resultSet.getDouble("salary");

            System.out.printf("\n%s, %s, %s, %.2f \n", firstName, lastName, department, salary);


        }
        while (resultSet.next()) {
            System.out.println(resultSet.getString(3) + " , " + resultSet.getString(2));
        }


        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }


    }
}
