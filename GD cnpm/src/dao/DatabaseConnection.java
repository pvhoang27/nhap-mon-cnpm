package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quanlykhovattu";
    private static final String USER = "root"; // THAY BẰNG USERNAME CỦA BẠN
    private static final String PASSWORD = "hoang2001"; // THAY BẰNG PASSWORD CỦA BẠN

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL! Hãy kiểm tra lại XAMPP/MySQL và thông tin kết nối.", "Lỗi CSDL", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Không thể kết nối tới cơ sở dữ liệu!", e);
        }
    }
}