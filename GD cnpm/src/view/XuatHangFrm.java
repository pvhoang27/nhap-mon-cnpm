package view;

import controller.XuatHangController;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class XuatHangFrm extends JFrame {

    private JButton btnTimKiemDaiLy;
    private JButton btnTimKiemHang;
    private JButton btnXemHoaDon;
    private XuatHangController controller;

    public XuatHangFrm(XuatHangController controller) {
        this.controller = controller;
        
        setTitle("Chức năng Xuất Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 100));

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(1, 3, 10, 10));
        setContentPane(contentPane);

        btnTimKiemDaiLy = new JButton("Tìm kiếm đại lý con");
        btnTimKiemHang = new JButton("Tìm kiếm hàng xuất");
        btnXemHoaDon = new JButton("Xem hóa đơn");

        contentPane.add(btnTimKiemDaiLy);
        contentPane.add(btnTimKiemHang);
        contentPane.add(btnXemHoaDon);
        
        btnTimKiemDaiLy.addActionListener(e -> new TimKiemDaiLyConFrm(controller).setVisible(true));
        btnTimKiemHang.addActionListener(e -> new TimKiemHangXuatFrm(controller).setVisible(true));
        
        btnXemHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.getDaiLyDaChon() == null) {
                    JOptionPane.showMessageDialog(XuatHangFrm.this, "Vui lòng chọn một đại lý trước!");
                    return;
                }
                if (controller.getDanhSachHangXuat().isEmpty()) {
                    JOptionPane.showMessageDialog(XuatHangFrm.this, "Vui lòng chọn ít nhất một mặt hàng để xuất!");
                    return;
                }
                // Mở cửa sổ hóa đơn thật
                new XemHoaDonFrm(controller).setVisible(true);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }
}