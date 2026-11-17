package model;

import interfaces.IThanhToan;

public class HoaDon implements IThanhToan {
    private String maHoaDon;
    private String maDatPhong;
    private String maKhach;
    private String ngayLap;
    private double tienPhong;
    private double tienDichVu;
    private double giamGia;
    private double tongTien;
    private String chiTietDichVu;
    private String trangThai;

    public HoaDon() {
        maHoaDon = "";
        maDatPhong = "";
        maKhach = "";
        ngayLap = "";
        tienPhong = 0;
        tienDichVu = 0;
        giamGia = 0;
        tongTien = 0;
        chiTietDichVu = "";
        trangThai = "Chua thanh toan";
    }

    public HoaDon(String maHoaDon, String maDatPhong, String maKhach,
                  String ngayLap, double tienPhong, double tienDichVu,
                  double giamGia, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.maDatPhong = maDatPhong;
        this.maKhach = maKhach;
        this.ngayLap = ngayLap;
        this.tienPhong = tienPhong;
        this.tienDichVu = tienDichVu;
        this.giamGia = giamGia;
        this.chiTietDichVu = "Chua nhap chi tiet dich vu";
        this.trangThai = trangThai;
        tongTien = tinhTien();
    }

    public HoaDon(String maHoaDon, String maDatPhong, String maKhach,
                  String ngayLap, double tienPhong, double tienDichVu,
                  double giamGia, String chiTietDichVu, String trangThai) {
        this(maHoaDon, maDatPhong, maKhach, ngayLap, tienPhong, tienDichVu,
                giamGia, trangThai);
        this.chiTietDichVu = chiTietDichVu;
    }

    @Override
    public double tinhTien() {
        tongTien = tienPhong + tienDichVu - giamGia;
        if (tongTien < 0) {
            tongTien = 0;
        }
        return tongTien;
    }

    @Override
    public void thanhToan() {
        tongTien = tinhTien();
        trangThai = "Da thanh toan";
    }

    public void hienThiThongTin() {
        System.out.println("Ma hoa don: " + maHoaDon);
        System.out.println("Ma dat phong: " + maDatPhong);
        System.out.println("Ma khach: " + maKhach);
        System.out.println("Ngay lap: " + ngayLap);
        System.out.println("Tien phong: " + tienPhong);
        System.out.println("Tien dich vu: " + tienDichVu);
        System.out.println("Chi tiet dich vu: " + chiTietDichVu);
        System.out.println("Tong tien: " + tinhTien());
        System.out.println("Trang thai: " + trangThai);
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(String maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    public String getMaKhach() {
        return maKhach;
    }

    public void setMaKhach(String maKhach) {
        this.maKhach = maKhach;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTienPhong() {
        return tienPhong;
    }

    public void setTienPhong(double tienPhong) {
        this.tienPhong = tienPhong;
    }

    public double getTienDichVu() {
        return tienDichVu;
    }

    public void setTienDichVu(double tienDichVu) {
        this.tienDichVu = tienDichVu;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getChiTietDichVu() {
        return chiTietDichVu;
    }

    public void setChiTietDichVu(String chiTietDichVu) {
        this.chiTietDichVu = chiTietDichVu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
