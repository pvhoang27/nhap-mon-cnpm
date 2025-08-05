package model;

public class HangHoa {
    private String maHang;
    private String tenHang;
    private String moTa;
    private int soLuongTrongKho;

    public HangHoa(String maHang, String tenHang, String moTa, int soLuongTrongKho) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.moTa = moTa;
        this.soLuongTrongKho = soLuongTrongKho;
    }

    // Getters and Setters
    public String getMaHang() { return maHang; }
    public void setMaHang(String maHang) { this.maHang = maHang; }
    public String getTenHang() { return tenHang; }
    public void setTenHang(String tenHang) { this.tenHang = tenHang; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public int getSoLuongTrongKho() { return soLuongTrongKho; }
    public void setSoLuongTrongKho(int soLuongTrongKho) { this.soLuongTrongKho = soLuongTrongKho; }
}