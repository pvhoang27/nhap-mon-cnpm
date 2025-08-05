// package main.java.com.ptit.warehouse.view;

package com.ptit.warehouse.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class XuatHangFrm extends JFrame {

    private JButton btnTimDaiLi;
    private JButton btnTimHangXuat;
    private JButton btnXemHoaDon;

    public XuatHangFrm() {
        initUI();
        initEvents();
    }

    private void initUI() {
        setTitle("Module Lập Phiếu Xuất Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Xuất hàng", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));

        btnTimDaiLi = new JButton("Tìm kiếm đại lí con");
        btnTimHangXuat = new JButton("Tìm kiếm hàng xuất");
        btnXemHoaDon = new JButton("Xem hóa đơn");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(btnTimDaiLi);
        buttonPanel.add(btnTimHangXuat);
        buttonPanel.add(btnXemHoaDon);

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(title, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
    }

    private void initEvents() {
        btnTimDaiLi.addActionListener((ActionEvent e) -> {
            // TODO: mở TimKiemDaiLiConFrm (sẽ viết sau)
            JOptionPane.showMessageDialog(this, "Mở form tìm ĐL (chưa code).");
        });

        btnTimHangXuat.addActionListener((ActionEvent e) -> {
            // TODO: mở TimKiemHangXuatFrm
            JOptionPane.showMessageDialog(this, "Mở form tìm hàng xuất (chưa code).");
        });

        btnXemHoaDon.addActionListener((ActionEvent e) -> {
            // TODO: mở XemHoaDonFrm
            JOptionPane.showMessageDialog(this, "Mở form xem hóa đơn (chưa code).");
        });
    }
}

