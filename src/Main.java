import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*  import packages
            load and register
            create connection
            create statement
            execute statement
            process the results
            close the connection
         */

        String url = "jdbc:postgresql://localhost:5432/demo";
        String user = "postgres";
        String password = "password";

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(url,user,password);

        System.out.println("Connection is established");

    }
}