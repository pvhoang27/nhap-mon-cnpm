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
import model.DaiLyCon;


public class DanhSachDaiLyConFrm extends JFrame {
    private JTable tblDaiLyCon;
    private JButton btnXacNhan;
    private XuatHangController controller;
    private ArrayList<DaiLyCon> danhSach;

    public DanhSachDaiLyConFrm(XuatHangController controller, ArrayList<DaiLyCon> danhSach) {
        this.controller = controller;
        this.danhSach = danhSach;
        initComponents();
        this.setTitle("Kết quả tìm kiếm");
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 300));
        setLayout(new BorderLayout());

        String[] columnNames = {"Mã đại lí", "Tên đại lí", "Địa chỉ", "Số điện thoại"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
             @Override
             public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa bảng
             }
        };
        tblDaiLyCon = new JTable(model);

        for (DaiLyCon dlc : this.danhSach) {
            model.addRow(new Object[]{ dlc.getMaDL(), dlc.getTenDL(), dlc.getDiaChi(), dlc.getSoDT() });
        }
        
        JScrollPane scrollPane = new JScrollPane(tblDaiLyCon);
        add(scrollPane, BorderLayout.CENTER);

        btnXacNhan = new JButton("Xác nhận");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnXacNhan);
        add(bottomPanel, BorderLayout.SOUTH);
        
        btnXacNhan.addActionListener(e -> {
            int selectedRow = tblDaiLyCon.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một đại lý trong bảng!");
                return;
            }
            DaiLyCon daiLyChon = this.danhSach.get(selectedRow);
            controller.setDaiLyDaChon(daiLyChon);
            
            JOptionPane.showMessageDialog(this, "Đã chọn đại lý: " + daiLyChon.getTenDL());
            this.dispose();
        });
        pack();
    }
}