package controller;

import java.util.ArrayList;
import model.DaiLyCon;
import model.MatHangXuat;

public class XuatHangController {
    private DaiLyCon daiLyDaChon;
    private ArrayList<MatHangXuat> danhSachHangXuat;

    public XuatHangController() {
        danhSachHangXuat = new ArrayList<>();
    }

    public DaiLyCon getDaiLyDaChon() {
        return daiLyDaChon;
    }

    public void setDaiLyDaChon(DaiLyCon daiLyDaChon) {
        this.daiLyDaChon = daiLyDaChon;
    }

    public ArrayList<MatHangXuat> getDanhSachHangXuat() {
        return danhSachHangXuat;
    }
    
    public void themHangVaoHoaDon(MatHangXuat mhx) {
        this.danhSachHangXuat.add(mhx);
    }
}