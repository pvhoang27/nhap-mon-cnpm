-- Tạo CSDL nếu chưa có
-- CREATE DATABASE IF NOT EXISTS quanlykhovattu;
USE quanlykhovattu;

-- Bảng lưu thông tin nhân viên
CREATE TABLE tblNhanVien (
    maNV VARCHAR(50) PRIMARY KEY,
    hoTenNV VARCHAR(255) NOT NULL,
    diaChi VARCHAR(255),
    sdt VARCHAR(20),
    chucVu VARCHAR(100),
    matKhau VARCHAR(255) -- Thêm cột mật khẩu để đăng nhập
);

-- Bảng lưu thông tin đại lý con
CREATE TABLE tblDaiLyCon (
    maDL VARCHAR(50) PRIMARY KEY,
    tenDL VARCHAR(255) NOT NULL,
    diaChi VARCHAR(255),
    sdt VARCHAR(20)
);

-- Bảng lưu thông tin hàng hóa trong kho
CREATE TABLE tblHangHoa (
    maHang VARCHAR(50) PRIMARY KEY,
    tenHang VARCHAR(255) NOT NULL,
    moTa TEXT,
    soLuongTrongKho INT NOT NULL
);

-- Bảng lưu thông tin một hóa đơn xuất (chưa có chi tiết hàng)
CREATE TABLE tblHoaDonXuat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    maDL VARCHAR(50),
    maNV VARCHAR(50),
    ngayXuat DATE,
    tongTien FLOAT,
    FOREIGN KEY (maDL) REFERENCES tblDaiLyCon(maDL),
    FOREIGN KEY (maNV) REFERENCES tblNhanVien(maNV)
);

-- Bảng chi tiết các mặt hàng trong một hóa đơn xuất
CREATE TABLE tblChiTietHoaDonXuat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idHoaDonXuat INT,
    maHang VARCHAR(50),
    soLuongXuat INT,
    donGia FLOAT,
    thanhTien FLOAT,
    FOREIGN KEY (idHoaDonXuat) REFERENCES tblHoaDonXuat(id),
    FOREIGN KEY (maHang) REFERENCES tblHangHoa(maHang)
);

-- Thêm dữ liệu mẫu để thử nghiệm
INSERT INTO tblNhanVien(maNV, hoTenNV, matKhau) VALUES ('NV001', 'Phạm Việt Hoàng', '123456');

INSERT INTO tblDaiLyCon(maDL, tenDL, diaChi, sdt) VALUES
('D070', 'Vật liệu điện Thu Mùi', 'Số 58 Phùng Hưng, Phúc La, Hà Đông, Hà Nội', '0915112781'),
('D071', 'Vật liệu điện Thu Mùi', 'Số 58 Phùng Hưng, Hàng Mã, Hoàn Kiếm, Hà Nội', '0971132856'),
('XD001', 'Cửa hàng vật liệu xây dựng Trang Trí', 'Số 25A đường Lê Đức Thọ kéo dài, Mai Dịch, Cầu Giấy, Hà Nội', '0982616689');

INSERT INTO tblHangHoa(maHang, tenHang, moTa, soLuongTrongKho) VALUES
('BD001', 'Băng dính 1 mặt', 'Loại nhỏ', 1000),
('BD002', 'Băng dính 2 mặt', 'Loại nhỏ', 1000),
('BDD010', 'Băng dính điện', 'Màu đen', 500),
('NL002', 'Túi nilon 5kg', 'Null', 20);