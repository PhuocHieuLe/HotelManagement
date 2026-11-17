# Class Diagram - HotelManagement

```mermaid
classDiagram
    class Nguoi {
        <<abstract>>
        -String ma
        -String hoTen
        -int namSinh
        -String gioiTinh
        -String soDienThoai
        -String diaChi
        +hienThiThongTin()*
        +getVaiTro() String*
    }

    class KhachHang {
        -int diemTichLuy
        -String loaiKhach
        +hienThiThongTin()
        +getVaiTro() String
    }

    class NhanVien {
        <<abstract>>
        -double luongCoBan
        -String ngayVaoLam
        +tinhLuong() double*
    }

    class LeTan {
        -double phuCap
        +tinhLuong() double
        +getVaiTro() String
    }

    class QuanLy {
        -double heSoChucVu
        +tinhLuong() double
        +getVaiTro() String
    }

    class Phong {
        <<abstract>>
        -String maPhong
        -String tenPhong
        -int tang
        -String trangThai
        -double giaCoBan
        -int soNguoiToiDa
        +tinhGia(int soDem) double*
    }

    class PhongDon {
        -double phuThu
        +tinhGia(int soDem) double
    }

    class PhongDoi {
        -int soGiuong
        +tinhGia(int soDem) double
    }

    class PhongVIP {
        -double phiDichVu
        +tinhGia(int soDem) double
    }

    class DichVu {
        -String maDichVu
        -String tenDichVu
        -double donGia
        -String donViTinh
    }

    class DichVuAn {
        -String loaiMon
    }

    class DichVuGiatLa {
        -int soKgToiDa
    }

    class DichVuSpa {
        -int thoiLuong
    }

    class DatPhong {
        -String maDatPhong
        -String maKhach
        -String maPhong
        -String ngayNhan
        -String ngayTra
        -int soDem
        -String trangThai
        +hienThiThongTin()
    }

    class HoaDon {
        -String maHoaDon
        -String maDatPhong
        -String maKhach
        -double tienPhong
        -double tienDichVu
        -double giamGia
        -double tongTien
        -String chiTietDichVu
        -String trangThai
        +tinhTien() double
        +thanhToan()
    }

    class TaiKhoan {
        -String tenDangNhap
        -String matKhau
        -String vaiTro
        -String maNguoiDung
        -boolean dangHoatDong
        +dangNhap(String user, String pass) boolean
        +dangXuat()
    }

    class IXacThuc {
        <<interface>>
        +dangNhap(String user, String pass) boolean
    }

    class IThanhToan {
        <<interface>>
        +tinhTien() double
        +thanhToan()
    }

    class HotelManagementApp {
        +chay()
    }

    class MenuNhanVien {
        -HotelManagementApp ungDung
        +chay()
    }

    class MenuQuanLy {
        -HotelManagementApp ungDung
        +chay()
    }

    class MenuKhachHang {
        -HotelManagementApp ungDung
        +chay()
    }

    Nguoi <|-- KhachHang
    Nguoi <|-- NhanVien
    NhanVien <|-- LeTan
    NhanVien <|-- QuanLy

    Phong <|-- PhongDon
    Phong <|-- PhongDoi
    Phong <|-- PhongVIP

    DichVu <|-- DichVuAn
    DichVu <|-- DichVuGiatLa
    DichVu <|-- DichVuSpa

    TaiKhoan ..|> IXacThuc
    HoaDon ..|> IThanhToan
    KhachHang "1" <-- "0..*" DatPhong : maKhach
    Phong "1" <-- "0..*" DatPhong : maPhong
    DatPhong "1" <-- "0..*" HoaDon : maDatPhong
    KhachHang "1" <-- "0..*" HoaDon : maKhach
    TaiKhoan "0..*" --> "1" Nguoi : maNguoiDung

    MenuNhanVien --> HotelManagementApp : goi nghiep vu
    MenuQuanLy --> HotelManagementApp : goi nghiep vu
    MenuKhachHang --> HotelManagementApp : goi nghiep vu
```

Các quan hệ giữa `DatPhong`, `HoaDon`, `KhachHang` và `Phong` là liên kết logic
qua mã đối tượng (`maKhach`, `maPhong`, `maDatPhong`), không phải thuộc tính
tham chiếu trực tiếp trong Java.
