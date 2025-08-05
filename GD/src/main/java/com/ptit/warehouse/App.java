// package main.java.com.ptit.warehouse;

package com.ptit.warehouse;

import javax.swing.SwingUtilities;
import com.ptit.warehouse.view.XuatHangFrm;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new XuatHangFrm().setVisible(true);
        });
    }
}
