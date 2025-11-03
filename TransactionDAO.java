package app;
import java.sql.*;
import java.util.*;

public class TransactionDAO {
    public static void insert(String name, double amount) {
        try (Connection conn = Database.connect()) {
            String sql = "INSERT INTO transactions(name, amount) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, amount);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> getAll() {
        List<Transaction> list = new ArrayList<>();
        try (Connection conn = Database.connect()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM transactions");
            while (rs.next()) {
                list.add(new Transaction(rs.getInt("id"), rs.getString("name"), rs.getDouble("amount")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
