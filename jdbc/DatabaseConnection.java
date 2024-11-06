package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";
    private static DatabaseConnection instance;

    private DatabaseConnection(){
     try {
         // Load MySQL Driver
         Class.forName("com.mysql.cj.jdbc.Driver");
     }catch (ClassNotFoundException e){
         System.err.println("Database driver not found: " + e.getMessage());
         e.printStackTrace();
     }
    }
    public static DatabaseConnection getInstance(){
        if(instance == null){
            synchronized (DatabaseConnection.class){
                if(instance == null){
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
     }

     public Connection getConnection(){
         Connection connection = null;

         try {
           connection = DriverManager.getConnection(URL,USER,PASSWORD);
             System.out.println("Connected to the database successfully.");
         }catch (SQLException e){
             System.err.println("Error connecting to the database: " + e.getMessage());
             e.printStackTrace();
         }
         return connection;
     }

}
