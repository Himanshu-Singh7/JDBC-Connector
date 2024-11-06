package jdbc;
import java.sql.*;

public class UserDAO {

    // Create tables
    public void createUserTable() {

        Connection connection = null;
        Statement statementQuery = null;
        String sql = "CREATE TABLE USERS (user_id INT AUTO_INCREMENT PRIMARY KEY, user_name VARCHAR(100), age INT)";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statementQuery = connection.createStatement();
            int result  = statementQuery.executeUpdate(sql);
            if(result == 0){
                System.out.println("Table already exists or created successfully.");
            }else {
                System.out.println("Table created successfully.");
            }
        }catch (SQLException e){
            System.err.println("Error executing table creation: " + e.getMessage());
            e.printStackTrace();
        }finally {
            if(statementQuery != null){
                try {
                    statementQuery.close();
                }catch (SQLException e){
                   e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }
   // Insert data in table
    public void createUser(String userName,int userAge){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO User(user_name ,age) VALUES(? ,? ) ";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, userAge);
            System.out.println("Insert table successfully.");
        }catch (SQLException e){
            System.err.println("Error executing Insertion in Table : " + e.getMessage());
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void readUser(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * " +  " FROM users";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet output = preparedStatement.executeQuery();
            while (output.next()){
                String userDetails = output.getInt("user_Id") + " : "
                        + output.getString("user_name") + " : " + output.getInt("age");
                System.out.println(userDetails);
            }
        }catch (SQLException e){
            System.err.println("Error executing read from Table : " + e.getMessage());
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
