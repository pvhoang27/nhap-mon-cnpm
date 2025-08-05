package model;

public class MatHangXuat extends HangHoa {
    private int soLuongXuat;
    private float donGia;
    private float thanhTien;

    public MatHangXuat(HangHoa hh, int soLuongXuat, float donGia) {
        super(hh.getMaHang(), hh.getTenHang(), hh.getMoTa(), hh.getSoLuongTrongKho());
        this.soLuongXuat = soLuongXuat;
        this.donGia = donGia;
        this.thanhTien = soLuongXuat * donGia;
    }
    
    // Getters
    public int getSoLuongXuat() { return soLuongXuat; }
    public float getDonGia() { return donGia; }
    public float getThanhTien() { return thanhTien; }
}