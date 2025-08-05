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
import model.DaiLyCon;

public class TimKiemDaiLyConFrm extends JFrame {

    private JLabel jLabel1;
    private JTextField txtTenDaiLy;
    private JButton btnTim;
    private XuatHangController controller;
    private XuatHangDAO xuatHangDAO;

    public TimKiemDaiLyConFrm(XuatHangController controller) {
        this.controller = controller;
        this.xuatHangDAO = new XuatHangDAO();
        initComponents();
        this.setTitle("Tìm kiếm Đại lý con");
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        jLabel1 = new JLabel("Nhập tên đại lí con:");
        txtTenDaiLy = new JTextField(20);
        btnTim = new JButton("Tìm");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenCanTim = txtTenDaiLy.getText().trim();
//                if (tenCanTim.isEmpty()) {
//                    JOptionPane.showMessageDialog(TimKiemDaiLyConFrm.this, "Vui lòng nhập tên đại lý để tìm!");
//                    return;
//                }
                
                ArrayList<DaiLyCon> ketQuaTimKiem = xuatHangDAO.timDaiLyTheoTen(tenCanTim);
                
                if (ketQuaTimKiem.isEmpty()) {
                    JOptionPane.showMessageDialog(TimKiemDaiLyConFrm.this, "Không tìm thấy đại lý nào có tên chứa '" + tenCanTim + "'");
                } else {
                    new DanhSachDaiLyConFrm(controller, ketQuaTimKiem).setVisible(true);
                    TimKiemDaiLyConFrm.this.dispose();
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
                .addComponent(txtTenDaiLy)
                .addGroup(layout.createSequentialGroup()
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTim)
                )
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(txtTenDaiLy))
            .addComponent(btnTim)
        );
        
        this.setContentPane(panel);
        this.pack();
    }
}