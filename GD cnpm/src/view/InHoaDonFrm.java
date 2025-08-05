package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

public class InHoaDonFrm extends JFrame {

    private JSpinner spinnerSoBanIn;
    private JComboBox<String> comboMayIn;
    private JComboBox<String> comboKhoGiay;
    private JButton btnXacNhan;

    public InHoaDonFrm() {
        initComponents();
        this.setTitle("Tùy chọn In");
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel lblSoBanIn = new JLabel("Số bản in:");
        JLabel lblMayIn = new JLabel("Máy in:");
        JLabel lblKhoGiay = new JLabel("Khổ giấy in:");

        // Spinner cho phép chọn số từ 1 trở lên
        spinnerSoBanIn = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        // ComboBox với các lựa chọn mẫu
        String[] mayInOptions = {"Microsoft Print to PDF", "Canon LBP2900", "HP LaserJet Pro M404dn"};
        comboMayIn = new JComboBox<>(mayInOptions);

        String[] khoGiayOptions = {"A4 (21 x 29.7 cm)", "A5 (14.8 x 21 cm)", "Letter (8.5 x 11 in)"};
        comboKhoGiay = new JComboBox<>(khoGiayOptions);

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
                        .addComponent(lblSoBanIn)
                        .addComponent(lblMayIn)
                        .addComponent(lblKhoGiay))
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(spinnerSoBanIn)
                        .addComponent(comboMayIn, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(comboKhoGiay, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                .addComponent(btnXacNhan)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoBanIn)
                    .addComponent(spinnerSoBanIn))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMayIn)
                    .addComponent(comboMayIn))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKhoGiay)
                    .addComponent(comboKhoGiay))
                .addGap(20)
                .addComponent(btnXacNhan)
        );

        this.setContentPane(panel);
        this.pack();

        // --- Sự kiện cho nút Xác nhận ---
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Trong ứng dụng thực tế, đây là nơi gọi API in ấn.
                // Ở đây chúng ta chỉ mô phỏng bằng một thông báo.
                JOptionPane.showMessageDialog(InHoaDonFrm.this,
                    "Đã gửi lệnh in thành công!\n" +
                    "Số bản: " + spinnerSoBanIn.getValue() + "\n" +
                    "Máy in: " + comboMayIn.getSelectedItem(),
                    "In thành công",
                    JOptionPane.INFORMATION_MESSAGE);
                InHoaDonFrm.this.dispose(); // Đóng cửa sổ in
            }
        });
    }
}