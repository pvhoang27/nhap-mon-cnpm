//package view;
//
//import controller.XuatHangController;
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.util.ArrayList;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.WindowConstants;
//import javax.swing.table.DefaultTableModel;
//import model.HangHoa;
//import model.MatHangXuat;
//
//public class DanhSachMatHangFrm extends JFrame {
//    private JTable tblHangHoa;
//    private JButton btnXacNhan;
//    private XuatHangController controller;
//    private ArrayList<HangHoa> danhSach;
//
//    public DanhSachMatHangFrm(XuatHangController controller, ArrayList<HangHoa> danhSach) {
//        this.controller = controller;
//        this.danhSach = danhSach;
//        initComponents();
//        this.setTitle("Kết quả tìm kiếm Hàng hóa");
//        this.setLocationRelativeTo(null);
//    }
//    
//    private void initComponents() {
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        setPreferredSize(new Dimension(800, 300));
//        setLayout(new BorderLayout());
//
//        String[] columnNames = {"Mã hàng", "Tên hàng", "Mô tả", "Số lượng trong kho"};
//        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
//             @Override
//             public boolean isCellEditable(int row, int column) {
//                return false; // Không cho phép chỉnh sửa bảng
//             }
//        };
//        tblHangHoa = new JTable(model);
//
//        for (HangHoa hh : this.danhSach) {
//            model.addRow(new Object[]{ hh.getMaHang(), hh.getTenHang(), hh.getMoTa(), hh.getSoLuongTrongKho() });
//        }
//        
//        JScrollPane scrollPane = new JScrollPane(tblHangHoa);
//        add(scrollPane, BorderLayout.CENTER);
//
//        btnXacNhan = new JButton("Xác nhận");
//        JPanel bottomPanel = new JPanel();
//        bottomPanel.add(btnXacNhan);
//        add(bottomPanel, BorderLayout.SOUTH);
//        
//        btnXacNhan.addActionListener(e -> {
//            int selectedRow = tblHangHoa.getSelectedRow();
//            if (selectedRow == -1) {
//                JOptionPane.showMessageDialog(this, "Vui lòng chọn một mặt hàng!");
//                return;
//            }
//            HangHoa hangHoaChon = this.danhSach.get(selectedRow);
//            
//            String soLuongStr = JOptionPane.showInputDialog(this, "Nhập số lượng xuất cho: " + hangHoaChon.getTenHang(), "Nhập số lượng", JOptionPane.QUESTION_MESSAGE);
//            
//            if (soLuongStr != null && !soLuongStr.isEmpty()) {
//                try {
//                    int soLuong = Integer.parseInt(soLuongStr);
//                    if(soLuong > hangHoaChon.getSoLuongTrongKho() || soLuong <= 0) {
//                        JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
//                        return;
//                    }
//
//                    float donGia = 10000;
//                    MatHangXuat mhx = new MatHangXuat(hangHoaChon, soLuong, donGia);
//                    controller.themHangVaoHoaDon(mhx);
//                    JOptionPane.showMessageDialog(this, "Đã thêm " + soLuong + " '" + hangHoaChon.getTenHang() + "' vào hóa đơn.");
//
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(this, "Vui lòng nhập một số hợp lệ!");
//                }
//            }
//        });
//        
//        pack();
//    }
//}
package view;

import controller.XuatHangController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import model.HangHoa;

public class DanhSachMatHangFrm extends JFrame {
    private JTable tblHangHoa;
    private JButton btnXacNhan;
    private XuatHangController controller;
    private ArrayList<HangHoa> danhSach;

    public DanhSachMatHangFrm(XuatHangController controller, ArrayList<HangHoa> danhSach) {
        this.controller = controller;
        this.danhSach = danhSach;
        initComponents();
        this.setTitle("Kết quả tìm kiếm Hàng hóa");
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 300));
        setLayout(new BorderLayout());

        String[] columnNames = {"Mã hàng", "Tên hàng", "Mô tả", "Số lượng trong kho"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
             @Override
             public boolean isCellEditable(int row, int column) {
                return false;
             }
        };
        tblHangHoa = new JTable(model);

        for (HangHoa hh : this.danhSach) {
            model.addRow(new Object[]{ hh.getMaHang(), hh.getTenHang(), hh.getMoTa(), hh.getSoLuongTrongKho() });
        }
        
        JScrollPane scrollPane = new JScrollPane(tblHangHoa);
        add(scrollPane, BorderLayout.CENTER);

        btnXacNhan = new JButton("Xác nhận");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnXacNhan);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // --- THAY ĐỔI LỚN Ở ĐÂY ---
        btnXacNhan.addActionListener(e -> {
            int selectedRow = tblHangHoa.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một mặt hàng!");
                return;
            }
            // Lấy hàng hóa đã chọn từ danh sách
            HangHoa hangHoaChon = this.danhSach.get(selectedRow);
            
            // Mở giao diện chi tiết thay vì hộp thoại cũ
            new NhapHangXuatFrm(controller, hangHoaChon).setVisible(true);
            
            // Đóng cửa sổ danh sách này lại
            this.dispose();
        });
        
        pack();
    }
}