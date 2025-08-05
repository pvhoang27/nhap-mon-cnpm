package view;

import controller.XuatHangController;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.HangHoa;
import model.MatHangXuat;


public class NhapHangXuatFrm extends JFrame {
    private XuatHangController controller;
    private HangHoa hangHoa;
    
    private JLabel lblMaHang, lblTenHang, lblMoTa, lblSoLuongKho, lblThanhTienValue;
    private JTextField txtSoLuongXuat, txtDonGia;
    private JButton btnXacNhan;

    public NhapHangXuatFrm(XuatHangController controller, HangHoa hangHoa) {
        this.controller = controller;
        this.hangHoa = hangHoa;
        initComponents();
        this.setTitle("Chi tiết mặt hàng xuất");
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        JLabel lblTitleMaHang = new JLabel("Mã hàng");
        JLabel lblTitleTenHang = new JLabel("Tên hàng");
        JLabel lblTitleMoTa = new JLabel("Mô tả");
        JLabel lblTitleSoLuongKho = new JLabel("Số lượng trong kho");
        JLabel lblTitleSoLuongXuat = new JLabel("Số lượng xuất");
        JLabel lblTitleDonGia = new JLabel("Đơn giá");
        JLabel lblTitleThanhTien = new JLabel("Thành tiền");
        
        lblMaHang = new JLabel(hangHoa.getMaHang());
        lblTenHang = new JLabel(hangHoa.getTenHang());
        lblMoTa = new JLabel(hangHoa.getMoTa());
        lblSoLuongKho = new JLabel(String.valueOf(hangHoa.getSoLuongTrongKho()));
        
        txtSoLuongXuat = new JTextField("0", 10);
        txtDonGia = new JTextField("0", 10);
        
        lblThanhTienValue = new JLabel("0 VNĐ");
        lblThanhTienValue.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnXacNhan = new JButton("Xác nhận");

        // --- Layout ---
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblTitleMaHang)
                        .addComponent(lblTitleTenHang)
                        .addComponent(lblTitleMoTa)
                        .addComponent(lblTitleSoLuongKho)
                        .addComponent(lblTitleSoLuongXuat)
                        .addComponent(lblTitleDonGia)
                        .addComponent(lblTitleThanhTien))
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblMaHang)
                        .addComponent(lblTenHang)
                        .addComponent(lblMoTa)
                        .addComponent(lblSoLuongKho)
                        .addComponent(txtSoLuongXuat)
                        .addComponent(txtDonGia)
                        .addComponent(lblThanhTienValue)))
                .addComponent(btnXacNhan)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTitleMaHang).addComponent(lblMaHang))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTitleTenHang).addComponent(lblTenHang))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTitleMoTa).addComponent(lblMoTa))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTitleSoLuongKho).addComponent(lblSoLuongKho))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTitleSoLuongXuat).addComponent(txtSoLuongXuat))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTitleDonGia).addComponent(txtDonGia))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTitleThanhTien).addComponent(lblThanhTienValue))
                .addGap(20)
                .addComponent(btnXacNhan)
        );
        
        this.setContentPane(panel);
        this.pack();
        
        // --- SỰ KIỆN ---
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateThanhTien(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateThanhTien(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateThanhTien(); }
        };
        
        txtSoLuongXuat.getDocument().addDocumentListener(documentListener);
        txtDonGia.getDocument().addDocumentListener(documentListener);
        
        btnXacNhan.addActionListener(e -> xacNhanThemHang());
    }
    
    private void updateThanhTien() {
        try {
            int soLuong = Integer.parseInt(txtSoLuongXuat.getText());
            float donGia = Float.parseFloat(txtDonGia.getText());
            float thanhTien = soLuong * donGia;
            lblThanhTienValue.setText(String.format("%,.0f VNĐ", thanhTien));
        } catch (NumberFormatException e) {
            lblThanhTienValue.setText("Lỗi định dạng số!");
        }
    }
    
    private void xacNhanThemHang() {
        try {
            int soLuong = Integer.parseInt(txtSoLuongXuat.getText());
            float donGia = Float.parseFloat(txtDonGia.getText());

            if (soLuong <= 0 || donGia <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng và đơn giá phải lớn hơn 0!");
                return;
            }
            if (soLuong > hangHoa.getSoLuongTrongKho()) {
                JOptionPane.showMessageDialog(this, "Số lượng xuất không được vượt quá số lượng trong kho!");
                return;
            }

            MatHangXuat mhx = new MatHangXuat(hangHoa, soLuong, donGia);
            controller.themHangVaoHoaDon(mhx);
            JOptionPane.showMessageDialog(this, "Đã thêm thành công mặt hàng '" + hangHoa.getTenHang() + "' vào hóa đơn.");
            this.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng và đơn giá hợp lệ!");
        }
    }
}