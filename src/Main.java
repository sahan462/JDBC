import javax.swing.plaf.synth.SynthLookAndFeel;
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

        //--------------------------create connection------------------------------
        String url = "jdbc:postgresql://localhost:5432/demo";
        String user = "postgres";
        String password = "password";

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(url,user,password);
        System.out.println("Connection is established");

        //create statement
        Statement st = con.createStatement();

        String query_1 = "select * from student where sid = 1";

        //execute statement
        ResultSet rs = st.executeQuery(query_1);

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

        // Close rs after processing
        rs.close();

        //--------------------------------crud operations----------------------------------
        //executeQuery will return resultSet and execute can return any type of data

        //create
        String query_3 = "insert into student values(11, 'John', 48)";
        boolean status = st.execute(query_3);
        System.out.println(status);

        //update
        String query_4 = "update student set sname='Thomas' where sid = 11";
        status = st.execute(query_4);
        System.out.println(status);

        //delete
        String query_5 = "delete from student where sid = 11";
        status = st.execute(query_5);
        System.out.println(status);

        //read - loop through all the rows in the table
        String query_2 = "select * from student";
        rs = st.executeQuery(query_2);

        while (rs.next()) {
            // Retrieve data from the current row
            int studentId = rs.getInt("sid");
            String sname = rs.getString("sname");
            int marks = rs.getInt("marks");

            // Do something with the data, like printing it
            System.out.println("Student ID: " + studentId + ", Name: " + sname + ", marks: " + marks);
        }

        //-----------------------prepared statements------------------------------

        /*
        Prepared statements offer several advantages over other approaches for executing SQL queries in Java applications:

        Security: Prepared statements help prevent SQL injection attacks by automatically escaping special characters in input parameters.
        This protects against malicious user input that could manipulate the SQL query and compromise the security of the database.

        Performance: Prepared statements can improve performance by precompiling the SQL query once and reusing the compiled statement with
        different parameter values. This reduces overhead associated with parsing and optimizing the query each time it is executed.

        Database Optimization: Prepared statements allow the database to optimize query execution by caching the execution plan for the
        prepared statement. This can lead to faster query execution times, especially for complex queries or queries executed frequently
        with different parameter values.

        Parameterized Queries: Prepared statements support parameterized queries, where placeholders are used for input parameters.
        This makes the SQL code more readable and maintainable, as it separates the query logic from the input data.

        Data Integrity: Prepared statements enforce data type compatibility between input parameters and database columns. This helps
        ensure data integrity by preventing type conversion errors or data truncation issues.

        Database Independence: Prepared statements are database-independent, meaning they can be used with any JDBC-compliant database
        without modification. This allows developers to write portable database code that can be easily migrated between different database systems.

        Automatic Resource Management: Prepared statements automatically handle resource management, such as opening and closing database connections,
        statement preparation, and result set handling. This simplifies code and reduces the risk of resource leaks.
        * */



        // Example 1: Using PreparedStatement for insert operation
        String insertQuery = "INSERT INTO student VALUES (?, ?, ?)";
        PreparedStatement insertStatement = con.prepareStatement(insertQuery);

        // Set values for placeholders in the prepared statement
        insertStatement.setInt(1, 11);  // Student ID
        insertStatement.setString(2, "John");  // Student Name
        insertStatement.setInt(3, 48);  // Student Marks

        // Execute the insert statement
        int rowsInserted = insertStatement.executeUpdate();
        System.out.println(rowsInserted + " row(s) inserted.");

        // Example 2: Using PreparedStatement for update operation
        String updateQuery = "UPDATE student SET sname = ? WHERE sid = ?";
        PreparedStatement updateStatement = con.prepareStatement(updateQuery);

        // Set values for placeholders in the prepared statement
        updateStatement.setString(1, "Thomas");  // New student name
        updateStatement.setInt(2, 11);  // Student ID to update

        // Execute the update statement
        int rowsUpdated = updateStatement.executeUpdate();
        System.out.println(rowsUpdated + " row(s) updated.");

        // Example 3: Using PreparedStatement for delete operation
        String deleteQuery = "DELETE FROM student WHERE sid = ?";
        PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);

        // Set value for placeholder in the prepared statement
        deleteStatement.setInt(1, 11);  // Student ID to delete

        // Execute the delete statement
        int rowsDeleted = deleteStatement.executeUpdate();
        System.out.println(rowsDeleted + " row(s) deleted.");


        //-----------------------close the connection------------------------------
        con.close();
        System.out.println("Connection is closed");

    }
}