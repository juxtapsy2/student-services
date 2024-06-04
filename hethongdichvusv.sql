CREATE database hethongdichvusv;
USE hethongdichvusv;
CREATE TABLE NguoiDung (
    MaNguoiDung VARCHAR(10),
    HoTen NVARCHAR(40) NOT NULL,
    NgaySinh DATETIME,
    GioiTinh NVARCHAR(7),
    DanToc NVARCHAR(10),
    CMND VARCHAR(14),
    TonGiao NVARCHAR(20),
    DiaChi NVARCHAR(100),
    SDT VARCHAR(11),
    Email VARCHAR(50),
    QueQuan NVARCHAR(150),
    MaLop VARCHAR(10),
    VaiTro VARCHAR(10),
    MaTinhTrang VARCHAR(10),
    PRIMARY KEY (MaNguoiDung)
);
CREATE TABLE DangNhap (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    MaNguoiDung VARCHAR(10) UNIQUE,
    TenDangNhap VARCHAR(50) UNIQUE,
    MatKhau VARCHAR(50),
    VaiTro VARCHAR(20),
    LuotTruyCap INT,
    TruyCapGanNhat DATETIME,
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

CREATE TABLE THONGBAO (
    MaTB VARCHAR(10) PRIMARY KEY,
    MaNguoiGui VARCHAR(10),
    MaNguoiNhan VARCHAR(10),
    TieuDe NVARCHAR(100),
    NoiDung TEXT,
    NgayGui DATE,
    FOREIGN KEY (MaNguoiGui) REFERENCES NguoiDung(MaNguoiDung),
    FOREIGN KEY (MaNguoiNhan) REFERENCES NguoiDung(MaNguoiDung)
);


-- First, create the NGANH table
CREATE TABLE NGANH (
    MaNganh VARCHAR(10),
    TenNganh NVARCHAR(255),
    MaKhoa VARCHAR(10),
    PRIMARY KEY (MaNganh) -- Assuming MaNganh is the primary key
);

-- Then, create the LOP table
CREATE TABLE LOP (
    MaLop VARCHAR(10),
    MaNganh VARCHAR(10),
    TenLop NVARCHAR(255),
    NamNhapHoc INT,
    PRIMARY KEY (MaLop),
    FOREIGN KEY (MaNganh) REFERENCES NGANH(MaNganh)
);

CREATE TABLE KHOA (
    MaKhoa VARCHAR(10) PRIMARY KEY,
    TenKhoa NVARCHAR(90),
    ViTri NVARCHAR(100)
);
CREATE TABLE TINHTRANG (
    MaTinhTrang VARCHAR(10) PRIMARY KEY,
    TenTinhTrang NVARCHAR(30),
    MoTa TEXT
);
-- Thêm khóa ngoại MaKhoa đến bảng KHOA
ALTER TABLE NguoiDung
ADD CONSTRAINT FK_SINHVIEN_Lop
FOREIGN KEY (MaLop)
REFERENCES LOP(MaLop);


ALTER TABLE NGANH
ADD CONSTRAINT FK_NGANH_KHOA
FOREIGN KEY (MaKhoa)
REFERENCES KHOA(MaKhoa);


-- Thêm khóa ngoại MaTinhTrang đến bảng TINHTRANG
ALTER TABLE NguoiDung
ADD CONSTRAINT FK_SINHVIEN_TINHTRANG
FOREIGN KEY (MaTinhTrang)
REFERENCES TINHTRANG(MaTinhTrang);

CREATE TABLE YEUCAU (
    MaYC VARCHAR(15) PRIMARY KEY,
    MaDV VARCHAR(10),
    MaNguoiDung VARCHAR(10),
    STT INT AUTO_INCREMENT,
    TieuDeYC NVARCHAR(50),
    NoiDungYC TEXT,
    NgayYC DATETIME,
    TrangThai NVARCHAR(20),
    INDEX(STT)
);
CREATE TABLE DICHVU (
    MaDV VARCHAR(10) PRIMARY KEY,
    TenDV NVARCHAR(80),
    MoTa TEXT,
    NamHoc VARCHAR(20),
    HocKy NVARCHAR(20)
);
ALTER TABLE YEUCAU
ADD CONSTRAINT FK_YECAU_SV
FOREIGN KEY (MaNguoiDung)
REFERENCES NguoiDung(MaNguoiDung);


ALTER TABLE YEUCAU
ADD CONSTRAINT FK_YECAU_DV
FOREIGN KEY (MaDV)
REFERENCES DICHVU(MaDV);

CREATE TABLE PHANHOI (
    MaPH  VARCHAR(15) PRIMARY KEY,
    MaYC  VARCHAR(10),
    MaNguoiDung VARCHAR(10),
    TieuDePH NVARCHAR(50),
    NoiDungPH TEXT,
    NgayPH DATETIME,
    FOREIGN KEY (MaYC) REFERENCES YEUCAU(MaYC)
);
ALTER TABLE PHANHOI
ADD CONSTRAINT FK_PHANHOI_YC
FOREIGN KEY (MaYC)
REFERENCES YeuCau(MaYC);
ALTER TABLE PHANHOI
ADD CONSTRAINT FK_PHANHOI_MaND
FOREIGN KEY (MaNguoiDung)
REFERENCES NguoiDung(MaNguoiDung);

CREATE TABLE DKGiayXacNhanSinhVien (
    MaDV VARCHAR(10),
    IDGiayXacNhan VARCHAR(20) PRIMARY KEY,
    MaNguoiDung VARCHAR(10),
    NamHoc VARCHAR(20),
    HocKy NVARCHAR(20),
    STT INT AUTO_INCREMENT,
    SoLuong INT,
    NgayDangKy DATE,
    ThoiGianNhan DATE,
    TinhTrang NVARCHAR(50),
    TenDichVu NVARCHAR(80), -- Thêm cột mới
    INDEX(STT)
);

ALTER TABLE DKGiayXacNhanSinhVien
ADD CONSTRAINT FK_XNSV_DV
FOREIGN KEY (MaDV)
REFERENCES DICHVU(MaDV);
ALTER TABLE DKGiayXacNhanSinhVien
ADD CONSTRAINT FK_XNSV_SV
FOREIGN KEY (MaNguoiDung)
REFERENCES NguoiDung(MaNguoiDung);

INSERT INTO TINHTRANG (MaTinhTrang, TenTinhTrang, MoTa)
VALUES 
  ('CTH', 'Còn học', 'Tình trạng học tập hiện tại.'),
  ('DN', 'Đã nghỉ', 'Học viên đã nghỉ học.'),
  ('TN', 'Tạm nghỉ', 'Học viên tạm nghỉ học với lý do cụ thể.');
INSERT INTO KHOA (MaKhoa, TenKhoa, ViTri)
VALUES
  ('K001', 'Khoa Công nghệ thông tin', 'Tầng 5, Nhà A'),
  ('K002', 'Khoa Kinh tế', 'Tầng 3, Nhà B'),
  ('K003', 'Khoa Ngoại ngữ', 'Tầng 2, Nhà C'),
 ('K004', 'Khoa Công nghệ thông tin', 'Tầng 1, Nhà C');
  INSERT INTO NGANH (MaNganh, TenNganh, MaKhoa)
VALUES
  ('N001', 'Công nghệ thông tin', 'K001'),
  ('N002', 'Kinh doanh quốc tế', 'K002'),
  ('N003', 'Ngôn ngữ Anh', 'K003'),
   ('N004', 'Trí tuệ nhân tạo', 'K004');
 INSERT INTO LOP (MaLop, MaNganh, TenLop, NamNhapHoc)
VALUES
  ('L001', 'N001', 'Lớp 001', 2022),
  ('L002', 'N002', 'Lớp 002', 2023),
  ('L003', 'N003', 'Lớp 003', 2021),
  ('L004', 'N004', 'Lớp 004', 2024); 


INSERT INTO NguoiDung (MaNguoiDung, HoTen, NgaySinh, GioiTinh, DanToc, CMND, TonGiao, DiaChi, SDT, Email, QueQuan, MaLop, VaiTro, MaTinhTrang)
VALUES
  ('21110603', 'Nguyen Van A', '1990-01-01', 'Nam', 'Kinh', '123456789', 'Khong', 'Ha Noi', '0123456789', 'nva8720@gmail.com', 'Ha Noi', 'L003', 'SV', 'CTH'),
  ('CTSV001', 'Tran Thi B', '1992-05-15', 'Nu', 'Kinh', '987654321', 'Khong', 'Ho Chi Minh', '0987654321', 'tranthib1992@gmail.com', 'Ho Chi Minh', NULL, 'CTSV', 'DN'),
  ('QTVHT001', 'Le Van C', '1988-09-20', 'Nam', 'Muong', '654321987', 'Cao Dai', 'Da Nang', '0112233445', 'levanc1988@gmail.com', 'Da Nang', NULL, 'QTVHT', 'TN'),
  ('23141953', 'Pham Thi D', '1995-03-10', 'Nu', 'Tay', '555555555', 'Phat Giao', 'Can Tho', '0987654321', 'phamthid@example.com', 'Can Tho', 'L001', 'SV', 'CTH'),
  ('CTSV002', 'Tran Van E', '1993-08-25', 'Nam', 'Hoa', '777777777', 'Khong', 'Vung Tau', '0123456789', 'tranve@example.com', 'Vung Tau', NULL, 'CTSV', 'DN'),
  ('QTVHT002', 'Le Thi F', '1998-12-05', 'Nu', 'Nung', '999999999', 'Khong', 'Hai Phong', '0112233445', 'lethif@example.com', 'Hai Phong', NULL, 'QTVHT', 'TN');

-- Chèn dữ liệu cho Người Dùng có MaNguoiDung là 'ND001'
INSERT INTO DangNhap (MaNguoiDung, TenDangNhap, MatKhau, VaiTro, LuotTruyCap, TruyCapGanNhat)
VALUES ('21110603', 'nguyenvana', '1234', 'SV', 0, NULL);

-- Chèn dữ liệu cho Người Dùng có MaNguoiDung là 'ND002'
INSERT INTO DangNhap (MaNguoiDung, TenDangNhap, MatKhau, VaiTro, LuotTruyCap, TruyCapGanNhat)
VALUES ('CTSV001', 'tranthib', '5678', 'CTSV', 0, NULL);

-- Chèn dữ liệu cho Người Dùng có MaNguoiDung là 'ND003'
INSERT INTO DangNhap (MaNguoiDung, TenDangNhap, MatKhau, VaiTro, LuotTruyCap, TruyCapGanNhat)
VALUES ('QTVHT001', 'levanc', '9123', 'QTVHT', 0, NULL);

-- Chèn dữ liệu cho Người Dùng có MaNguoiDung là 'ND004'
INSERT INTO DangNhap (MaNguoiDung, TenDangNhap, MatKhau, VaiTro, LuotTruyCap, TruyCapGanNhat)
VALUES ('23141953', 'phamthid', '234', 'SV', 0, NULL);

-- Chèn dữ liệu cho Người Dùng có MaNguoiDung là 'ND005'
INSERT INTO DangNhap (MaNguoiDung, TenDangNhap, MatKhau, VaiTro, LuotTruyCap, TruyCapGanNhat)
VALUES ('CTSV002', 'tranve', '345', 'CTSV', 0, NULL);

-- Chèn dữ liệu cho Người Dùng có MaNguoiDung là 'ND006'
INSERT INTO DangNhap (MaNguoiDung, TenDangNhap, MatKhau, VaiTro, LuotTruyCap, TruyCapGanNhat)
VALUES ('QTVHT002', 'lethif', '566', 'QTVHT', 0, NULL);


INSERT INTO DICHVU (MaDV, TenDV, MoTa, NamHoc, HocKy) VALUES
('DV001', 'Giay xac nhan sinh vien', 'Cap giay chung nhan sinh vien', '2023-2024', 'Hoc ky 1'),
('DV002', 'Dang ky noi - ngoai tru', 'Ho tro sinh vien cac van de lien quan toi qua trinh dang ky noi tru, ngoai tru', '2023-2024', 'Hoc ky 1'),
('DV003', 'Xin tam ngung hoc tap', 'Giai quyet van de voi sinh vien co nhu cau tam ngung hoc tap', '2023-2024', 'Hoc ky 1'),
('DV004', 'Dang ky quy doi diem ngoai ngu', 'Ho tro sinh vien quy doi diem ngoai ngu', '2023-2024', 'Hoc ky 1'),
('DV005', 'Xu ly khieu nai', 'Ho tro sinh vien giai quyet cac khuc mac trong qua trinh hoc tap', '2023-2024', 'Hoc ky 1'),
('DV006', 'Dich vu khac', 'Xu ly cac dich vu khac do sinh vien yeu cau', '2023-2024', 'Hoc ky 1');

INSERT INTO YEUCAU (`MaYC`, `MaDV`, `MaNguoiDung`, `TieuDeYC`, `NoiDungYC`, `NgayYC`, `TrangThai`) VALUES ('YCzzsad3', 'DV004', '21110603', 'Xin...', 'Noi dung...', NOW(), 'Chua duyet');
INSERT INTO YEUCAU (`MaYC`, `MaDV`, `MaNguoiDung`, `TieuDeYC`, `NoiDungYC`, `NgayYC`, `TrangThai`) VALUES ('YC021zxc', 'DV006', '23141953', 'Yeu cau...', 'Noi dung...', NOW(), 'Chua duyet');
