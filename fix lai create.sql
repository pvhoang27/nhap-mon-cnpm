-- Chọn cơ sở dữ liệu để làm việc
USE quanlykhovattu;

-- Xóa các bảng cũ đi để tạo lại cho chính xác, cần xóa theo đúng thứ tự khóa ngoại
DROP TABLE IF EXISTS tblChiTietHoaDonXuat;
DROP TABLE IF EXISTS tblMatHangXuat28;
DROP TABLE IF EXISTS tblHoaDonXuat;
DROP TABLE IF EXISTS tblHoaDonXuatHang28;


-- TẠO BẢNG HÓA ĐƠN XUẤT HÀNG (Bảng chính)
-- Bảng này sẽ tương ứng với tblHoaDonXuatHang28 trong tài liệu của bạn
CREATE TABLE tblHoaDonXuatHang28 (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    maNV VARCHAR(50),
    maDL VARCHAR(50),      -- Thêm cột maDL để lưu thông tin đại lý
    ngayXuat DATE,         -- Thêm cột ngày xuất
    tongTien FLOAT,
    FOREIGN KEY (maNV) REFERENCES tblNhanVien(maNV),
    FOREIGN KEY (maDL) REFERENCES tblDaiLyCon(maDL)
);


-- TẠO BẢNG CHI TIẾT MẶT HÀNG XUẤT
-- Bảng này sẽ tương ứng với tblMatHangXuat28 trong tài liệu của bạn
CREATE TABLE tblMatHangXuat28 (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    IDHoaDonXuatHang INT, -- Khóa ngoại trỏ tới bảng tblHoaDonXuatHang28
    maHang VARCHAR(50),
    soLuongXuat INT,
    donGia FLOAT,
    thanhTien FLOAT,
    FOREIGN KEY (IDHoaDonXuatHang) REFERENCES tblHoaDonXuatHang28(ID),
    FOREIGN KEY (maHang) REFERENCES tblHangHoa(maHang)
);
