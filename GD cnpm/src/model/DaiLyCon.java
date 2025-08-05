package model;

public class DaiLyCon {
    private String maDL;
    private String tenDL;
    private String diaChi;
    private String soDT;

    public DaiLyCon(String maDL, String tenDL, String diaChi, String soDT) {
        this.maDL = maDL;
        this.tenDL = tenDL;
        this.diaChi = diaChi;
        this.soDT = soDT;
    }

    // Getters
    public String getMaDL() { return maDL; }
    public String getTenDL() { return tenDL; }
    public String getDiaChi() { return diaChi; }
    public String getSoDT() { return soDT; }
}