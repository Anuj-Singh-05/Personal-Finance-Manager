package app;
import java.sql.*;

public class Database {
    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:finance.db");
            String sql = "CREATE TABLE IF NOT EXISTS transactions (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, amount REAL)";
            conn.createStatement().execute(sql);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
