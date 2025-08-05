package view;

import controller.XuatHangController;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tạo một bộ điều khiển duy nhất cho cả ứng dụng
                XuatHangController controller = new XuatHangController();
                
                // Truyền bộ điều khiển này vào giao diện chính
                XuatHangFrm xuatHangView = new XuatHangFrm(controller);
                xuatHangView.setVisible(true);
            }
        });
    }
}