package view;

import controller.XuatHangController;
import dao.XuatHangDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import model.HangHoa;

public class TimKiemHangXuatFrm extends JFrame {

    private JLabel jLabel1;
    private JTextField txtTenHang;
    private JButton btnTim;
    private XuatHangController controller;
    private XuatHangDAO xuatHangDAO;

    public TimKiemHangXuatFrm(XuatHangController controller) {
        this.controller = controller;
        this.xuatHangDAO = new XuatHangDAO();
        initComponents();
        this.setTitle("Tìm kiếm Hàng hóa");
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        jLabel1 = new JLabel("Nhập tên mặt hàng:");
        txtTenHang = new JTextField(20);
        btnTim = new JButton("Tìm");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenCanTim = txtTenHang.getText().trim();
                if (tenCanTim.isEmpty()) {
                    JOptionPane.showMessageDialog(TimKiemHangXuatFrm.this, "Vui lòng nhập tên hàng để tìm!");
                    return;
                }
                
                ArrayList<HangHoa> ketQuaTimKiem = xuatHangDAO.timHangHoaTheoTen(tenCanTim);
                
                if (ketQuaTimKiem.isEmpty()) {
                    JOptionPane.showMessageDialog(TimKiemHangXuatFrm.this, "Không tìm thấy hàng nào có tên chứa '" + tenCanTim + "'");
                } else {
                    new DanhSachMatHangFrm(controller, ketQuaTimKiem).setVisible(true);
                    TimKiemHangXuatFrm.this.dispose();
                }
            }
        });

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(txtTenHang)
                .addGroup(layout.createSequentialGroup()
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTim)
                )
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(txtTenHang))
            .addComponent(btnTim)
        );
        
        this.setContentPane(panel);
        this.pack();
    }
}