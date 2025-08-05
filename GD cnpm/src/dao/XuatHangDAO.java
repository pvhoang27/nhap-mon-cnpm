package dao;

import controller.XuatHangController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import model.DaiLyCon;
import model.HangHoa;
import model.MatHangXuat;

public class XuatHangDAO {

    public ArrayList<DaiLyCon> timDaiLyTheoTen(String ten) {
        ArrayList<DaiLyCon> ketQua = new ArrayList<>();
        String sql = "SELECT * FROM tblDaiLyCon28 WHERE tenDL LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maDL = rs.getString("maDL");
                String tenDL = rs.getString("tenDL");
                String diaChi = rs.getString("diaChi");
                // ĐÃ SỬA LỖI: "sdt" -> "soDT"
                String sdt = rs.getString("soDT"); 
                ketQua.add(new DaiLyCon(maDL, tenDL, diaChi, sdt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<HangHoa> timHangHoaTheoTen(String ten) {
        ArrayList<HangHoa> ketQua = new ArrayList<>();
        String sql = "SELECT * FROM tblHangHoa28 WHERE tenHang LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ketQua.add(new HangHoa(rs.getString("maHang"), rs.getString("tenHang"), rs.getString("moTa"), rs.getInt("soLuongTrongKho")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public boolean luuHoaDon(XuatHangController controller) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Bắt đầu Transaction

            // BƯỚC 1: Thêm vào tblHoaDonXuat28 (Sự kiện xuất)
            String sqlSuKienXuat = "INSERT INTO tblHoaDonXuat28(maDL, ngayXuat) VALUES(?, ?)";
            PreparedStatement psSuKienXuat = conn.prepareStatement(sqlSuKienXuat, Statement.RETURN_GENERATED_KEYS);
            psSuKienXuat.setString(1, controller.getDaiLyDaChon().getMaDL());
            psSuKienXuat.setDate(2, new java.sql.Date(new Date().getTime()));
            psSuKienXuat.executeUpdate();
            
            ResultSet keysSuKien = psSuKienXuat.getGeneratedKeys();
            int idSuKienXuat = keysSuKien.next() ? keysSuKien.getInt(1) : 0;
            if (idSuKienXuat == 0) throw new SQLException("Tạo sự kiện xuất thất bại.");

            // BƯỚC 2: Thêm vào tblHoaDonXuatHang28 (Thông tin hóa đơn)
            String sqlHoaDon = "INSERT INTO tblHoaDonXuatHang28(IDHoaDonXuat, maNV, tongTien) VALUES(?, ?, ?)";
            PreparedStatement psHoaDon = conn.prepareStatement(sqlHoaDon, Statement.RETURN_GENERATED_KEYS);
            
            float tongTien = 0;
            for (MatHangXuat mhx : controller.getDanhSachHangXuat()) {
                tongTien += mhx.getThanhTien();
            }

            psHoaDon.setInt(1, idSuKienXuat);
            psHoaDon.setString(2, "NV001");
            psHoaDon.setFloat(3, tongTien);
            psHoaDon.executeUpdate();

            ResultSet keysHoaDon = psHoaDon.getGeneratedKeys();
            int idHoaDonXuatHang = keysHoaDon.next() ? keysHoaDon.getInt(1) : 0;
            if (idHoaDonXuatHang == 0) throw new SQLException("Tạo hóa đơn thất bại.");

            // BƯỚC 3 & 4: Thêm chi tiết vào tblMatHangXuat28 và Cập nhật kho
            String sqlChiTiet = "INSERT INTO tblMatHangXuat28(IDHoaDonXuatHang, maHang, soLuongXuat, donGia, thanhTien) VALUES(?, ?, ?, ?, ?)";
            String sqlUpdateKho = "UPDATE tblHangHoa28 SET soLuongTrongKho = soLuongTrongKho - ? WHERE maHang = ?";
            
            PreparedStatement psChiTiet = conn.prepareStatement(sqlChiTiet);
            PreparedStatement psUpdateKho = conn.prepareStatement(sqlUpdateKho);
            
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

            for (MatHangXuat mhx : controller.getDanhSachHangXuat()) {
                psChiTiet.setInt(1, idHoaDonXuatHang);
                psChiTiet.setString(2, mhx.getMaHang());
                psChiTiet.setInt(3, mhx.getSoLuongXuat());
                psChiTiet.setString(4, nf.format(mhx.getDonGia()) + "/cái"); // Định dạng String
                psChiTiet.setString(5, nf.format(mhx.getThanhTien())); // Định dạng String
                psChiTiet.addBatch();

                psUpdateKho.setInt(1, mhx.getSoLuongXuat());
                psUpdateKho.setString(2, mhx.getMaHang());
                psUpdateKho.addBatch();
            }
            
            psChiTiet.executeBatch();
            psUpdateKho.executeBatch();

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}
