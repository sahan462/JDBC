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

        //create connection
        String url = "jdbc:postgresql://localhost:5432/demo";
        String user = "postgres";
        String password = "password";

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(url,user,password);
        System.out.println("Connection is established");

        //create statement
        Statement st = con.createStatement();

        String query = "select * from student where sid = 1";

        //execute statement
        ResultSet rs = st.executeQuery(query);

        //process result set
        // rs.next()); => if it returns a set of results rs.next() will returns true, otherwise false.
        if (rs.next()) { //rs.next() will increase rs pointer value by 1

            // If rs.next() returns true, move the cursor to the first row
            // Access data from the result set
            String name = rs.getString("sname");
            System.out.println(name);
        } else {
            // If rs.next() returns false, no rows were returned by the query
            System.out.println("No data found for sid = 1");
        }


        //close the connection
        con.close();
        System.out.println("Connection is closed");



    }
}