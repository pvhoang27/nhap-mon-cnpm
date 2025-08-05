-- Chọn cơ sở dữ liệu để làm việc
USE quanlykhovattu;

-- BỔ SUNG CÁC ĐẠI LÝ CÒN THIẾU VÀO BẢNG tblDaiLyCon
INSERT INTO tblDaiLyCon (maDL, tenDL, diaChi, sdt) VALUES
('TH010', 'Cửa hàng tạp hóa Hòa Thạc', 'Số 87 Phan Bá Vành, Cầu Diễn, Từ Liêm, Hà Nội', '0989918570'),
('XD002', 'Cửa hàng vật liệu xây dựng Tuấn Hương', 'Số 324 Phạm Văn Đồng, Cổ Nhuệ, Bắc Từ Liêm, Hà Nội', '02438361739');

-- BỔ SUNG CÁC MẶT HÀNG CÒN THIẾU VÀO BẢNG tblHangHoa
INSERT INTO tblHangHoa (maHang, tenHang, moTa, soLuongTrongKho) VALUES
('NL001', 'Túi nilon 2kg', 'Null', 0),
('G001', 'Gạch hoa lát nền nhà', 'Null', 1500),
('XM001', 'Xi măng Bỉm Sơn', 'Null', 2000),
('XM002', 'Xi măng Hà Tiên', 'Null', 2000);
