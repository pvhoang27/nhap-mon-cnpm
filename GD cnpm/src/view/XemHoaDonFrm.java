package view;

import controller.XuatHangController;
import dao.XuatHangDAO; // Thêm import này
import java.awt.Font;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import model.MatHangXuat;

public class XemHoaDonFrm extends JFrame {
    
    public XemHoaDonFrm(XuatHangController controller) {
        initComponents(controller);
        this.setTitle("HÓA ĐƠN XUẤT HÀNG");
        this.setLocationRelativeTo(null);
        this.pack();
    }

    private void initComponents(XuatHangController controller) {
        // ... Toàn bộ code layout của bạn giữ nguyên ...
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        JLabel lblTitle = new JLabel("HÓA ĐƠN XUẤT HÀNG");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel lblDate = new JLabel("Ngày: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        JLabel lblTenDaiLy = new JLabel("Tên đại lý: " + controller.getDaiLyDaChon().getTenDL());
        JLabel lblMaDaiLy = new JLabel("Mã đại lý: " + controller.getDaiLyDaChon().getMaDL());
        JLabel lblDiaChi = new JLabel("Địa chỉ: " + controller.getDaiLyDaChon().getDiaChi());
        JLabel lblSdt = new JLabel("Số điện thoại: " + controller.getDaiLyDaChon().getSoDT());

        String[] columnNames = {"STT", "Mã hàng", "Tên hàng", "Mô tả", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
             @Override
             public boolean isCellEditable(int row, int column) {
                return false;
             }
        };
        JTable tblHoaDon = new JTable(model);
        
        float tongTien = 0;
        int stt = 1;
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        for(MatHangXuat mhx : controller.getDanhSachHangXuat()) {
            model.addRow(new Object[] {
                stt++, mhx.getMaHang(), mhx.getTenHang(), mhx.getMoTa(),
                mhx.getSoLuongXuat(), nf.format(mhx.getDonGia()), nf.format(mhx.getThanhTien())
            });
            tongTien += mhx.getThanhTien();
        }
        
        JScrollPane scrollPane = new JScrollPane(tblHoaDon);

        JLabel lblTongTien = new JLabel("Tổng tiền: " + nf.format(tongTien));
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 16));
        
        JButton btnLuu = new JButton("Lưu");
        JButton btnIn = new JButton("In hóa đơn");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLuu);
        buttonPanel.add(btnIn);

        // --- THAY ĐỔI DUY NHẤT Ở ĐÂY ---
        btnLuu.addActionListener(e -> {
            XuatHangDAO dao = new XuatHangDAO();
            boolean success = dao.luuHoaDon(controller);
            if (success) {
                JOptionPane.showMessageDialog(this, "Đã lưu hóa đơn thành công vào CSDL!");
                // Có thể thêm chức năng reset controller ở đây nếu muốn tạo hóa đơn mới
                btnLuu.setEnabled(false); // Vô hiệu hóa nút Lưu sau khi đã lưu
            } else {
                JOptionPane.showMessageDialog(this, "Lưu hóa đơn thất bại! Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnIn.addActionListener(e -> {
            new InHoaDonFrm().setVisible(true);
        });
        
        // ... Toàn bộ code layout giữ nguyên ...
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblTitle)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDate))
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongTien))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblMaDaiLy)
                        .addComponent(lblTenDaiLy))
                    .addGap(50)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblDiaChi)
                        .addComponent(lblSdt)))
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(lblDate))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaDaiLy)
                    .addComponent(lblDiaChi))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenDaiLy)
                    .addComponent(lblSdt))
                .addGap(20)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongTien)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonPanel)
        );

        this.setContentPane(mainPanel);
    }
}