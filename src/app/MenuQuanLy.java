package app;

import model.LeTan;
import model.NhanVien;
import model.Phong;
import model.PhongDoi;
import model.PhongDon;
import model.PhongVIP;
import model.QuanLy;
import model.TaiKhoan;

public class MenuQuanLy {
    private HotelManagementApp ungDung;

    public MenuQuanLy(HotelManagementApp ungDung) {
        this.ungDung = ungDung;
    }

    public void chay() {
        boolean tiepTuc = true;
        while (tiepTuc) {
            System.out.println("\n===== MENU QUAN LY =====");
            System.out.println("1. Quan ly phong");
            System.out.println("2. Quan ly nhan vien");
            System.out.println("3. Quan ly khach hang");
            System.out.println("4. Quan ly tai khoan");
            System.out.println("5. Xem hoa don va doanh thu");
            System.out.println("6. Xem luong nhan vien");
            System.out.println("7. Dang ky tai khoan nhan vien");
            System.out.println("8. Nhap them n phong");
            System.out.println("9. Tim kiem phong theo tu khoa");
            System.out.println("10. Thong ke theo nhieu tieu chi");
            System.out.println("0. Dang xuat");
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) {
                quanLyPhong();
            } else if (chon == 2) {
                quanLyNhanVien();
            } else if (chon == 3) {
                new MenuKhachHang(ungDung).chay();
            } else if (chon == 4) {
                ungDung.hienThiTaiKhoanVaDoanhThu();
            } else if (chon == 5) {
                ungDung.hienThiHoaDonVaDoanhThu();
            } else if (chon == 6) {
                ungDung.hienThiLuong();
            } else if (chon == 7) {
                dangKyTaiKhoanNhanVien();
            } else if (chon == 8) {
                nhapNhieuPhong();
            } else if (chon == 9) {
                timKiemPhong();
            } else if (chon == 10) {
                thongKe();
            } else if (chon == 0) {
                tiepTuc = false;
            } else {
                System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private void quanLyPhong() {
            ungDung.dsPhong.hienThiDanhSach();
            System.out.println("1. Them phong  2. Sua phong  3. Xoa phong  0. Quay lai");
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) {
                themPhong();
            } else if (chon == 2) {
                suaPhong();
            } else if (chon == 3) {
                String ma = Utils.nhapChuoi("Ma phong can xoa: ");
                if (ungDung.dsPhong.tim(ma) == null) {
                    System.out.println("Khong tim thay ma phong, khong xoa.");
                } else if (coDatPhong(ma)) {
                    System.out.println("Khong the xoa phong dang co dat phong.");
                } else {
                    ungDung.dsPhong.xoa(ma);
                    ungDung.ghiDuLieu();
                    System.out.println("Xoa phong thanh cong.");
                }
            } else if (chon != 0) {
                System.out.println("Lua chon khong hop le.");
            }
        }

        private void themPhong() {
            String loai = Utils.nhapChuoi("Loai phong DON/DOI/VIP: ").toUpperCase();
            if (!loai.equals("DON") && !loai.equals("DOI") && !loai.equals("VIP")) {
                System.out.println("Loai phong khong hop le.");
                return;
            }
            String ma = Utils.nhapChuoi("Ma phong: ");
            if (ma.length() == 0 || ungDung.dsPhong.tim(ma) != null) {
                System.out.println("Ma phong khong duoc trong hoac da ton tai.");
                return;
            }
            String ten = Utils.nhapChuoi("Ten phong: ");
            int tang = Utils.nhapSo("Tang: ");
            double gia = Utils.nhapSoThuc("Gia co ban: ");
            int sucChua = Utils.nhapSo("So nguoi toi da: ");
            if (tang <= 0 || gia <= 0 || sucChua <= 0) {
                System.out.println("Tang, gia phong va so nguoi phai lon hon 0.");
                return;
            }
            Phong phong;
            if (loai.equals("DOI")) {
                phong = new PhongDoi(ma, ten, tang, "Trong", gia, 2);
            } else if (loai.equals("VIP")) {
                phong = new PhongVIP(ma, ten, tang, "Trong", gia, 200000);
            } else {
                phong = new PhongDon(ma, ten, tang, "Trong", gia, 0);
            }
            phong.setSoNguoiToiDa(sucChua);
            ungDung.dsPhong.them(phong);
            ungDung.ghiDuLieu();
            System.out.println("Them thanh cong.");
        }

        private void suaPhong() {
            String ma = Utils.nhapChuoi("Ma phong can sua: ");
            Phong phong = ungDung.dsPhong.tim(ma);
            if (phong == null) {
                System.out.println("Khong tim thay phong.");
                return;
            }
            phong.setTenPhong(Utils.nhapChuoi("Ten phong moi: "));
            phong.setTang(Utils.nhapSo("Tang moi: "));
            phong.setGiaCoBan(Utils.nhapSoThuc("Gia co ban moi: "));
            phong.setSoNguoiToiDa(Utils.nhapSo("So nguoi toi da moi: "));
            if (phong.getTenPhong().length() == 0 || phong.getTang() <= 0
                    || phong.getGiaCoBan() <= 0 || phong.getSoNguoiToiDa() <= 0) {
                System.out.println("Thong tin phong khong hop le.");
                return;
            }
            ungDung.ghiDuLieu();
            System.out.println("Sua phong thanh cong.");
        }

        private boolean coDatPhong(String maPhong) {
            for (int i = 0; i < ungDung.dsDatPhong.getSoLuong(); i++) {
                String trangThai = ungDung.dsDatPhong.get(i).getTrangThai();
                if (ungDung.dsDatPhong.get(i).getMaPhong().equalsIgnoreCase(maPhong)
                        && !trangThai.equalsIgnoreCase("Da huy")
                        && !trangThai.equalsIgnoreCase("Da tra phong")) {
                    return true;
                }
            }
            return false;
        }

        private void quanLyNhanVien() {
            ungDung.dsNhanVien.hienThiDanhSach();
            System.out.println("1. Them  2. Sua  3. Xoa  0. Quay lai");
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) {
                themNhanVien();
            } else if (chon == 2) {
                suaNhanVien();
            } else if (chon == 3) {
                String ma = Utils.nhapChuoi("Nhap ma nhan vien can xoa: ");
                if (ungDung.dsNhanVien.xoa(ma)) {
                    System.out.println("Xoa nhan vien thanh cong.");
                } else {
                    System.out.println("Khong tim thay ma nhan vien.");
                }
                ungDung.ghiDuLieu();
            } else if (chon != 0) {
                System.out.println("Lua chon khong hop le.");
            }
        }

        private void themNhanVien() {
            String ma = Utils.nhapChuoi("Ma nhan vien: ");
            if (ma.length() == 0 || ungDung.dsNhanVien.tim(ma) != null) {
                System.out.println("Ma nhan vien khong duoc trong hoac da ton tai.");
                return;
            }
            NhanVien nhanVien = nhapNhanVien(ma);
            if (nhanVien != null && ungDung.dsNhanVien.them(nhanVien)) {
                ungDung.ghiDuLieu();
                System.out.println("Them nhan vien thanh cong.");
            }
        }

        private void suaNhanVien() {
            String ma = Utils.nhapChuoi("Ma nhan vien can sua: ");
            if (ungDung.dsNhanVien.tim(ma) == null) {
                System.out.println("Khong tim thay nhan vien.");
                return;
            }
            NhanVien moi = nhapNhanVien(ma);
            if (moi != null) {
                ungDung.dsNhanVien.sua(ma, moi);
                for (int i = 0; i < ungDung.dsTaiKhoan.getSoLuong(); i++) {
                    TaiKhoan taiKhoan = ungDung.dsTaiKhoan.get(i);
                    if (taiKhoan.getMaNguoiDung().equalsIgnoreCase(ma)) {
                        taiKhoan.setVaiTro(moi.getVaiTro());
                    }
                }
                ungDung.ghiDuLieu();
                System.out.println("Sua nhan vien thanh cong.");
            }
        }

        private NhanVien nhapNhanVien(String ma) {
            String ten = Utils.nhapChuoi("Ho ten: ");
            if (ten.length() == 0) {
                System.out.println("Ho ten khong duoc de trong.");
                return null;
            }
            int nam = ungDung.nhapNamSinh();
            String gioiTinh = ungDung.nhapGioiTinh();
            String dienThoai = ungDung.nhapSoDienThoai();
            String diaChi = Utils.nhapChuoi("Dia chi: ");
            double luong = Utils.nhapSoThuc("Luong co ban: ");
            if (luong <= 0) {
                System.out.println("Luong phai lon hon 0.");
                return null;
            }
            String ngay = Utils.nhapChuoi("Ngay vao lam: ");
            String vaiTro = Utils.nhapChuoi("Chuc vu (LETAN/QUANLY): ").toUpperCase();
            if (vaiTro.equals("LETAN")) {
                return new LeTan(ma, ten, nam, gioiTinh, dienThoai, diaChi, luong, ngay,
                        Utils.nhapSoThuc("Phu cap: "));
            }
            if (vaiTro.equals("QUANLY")) {
                return new QuanLy(ma, ten, nam, gioiTinh, dienThoai, diaChi, luong, ngay,
                        Utils.nhapSoThuc("He so chuc vu: "));
            }
            System.out.println("Chuc vu khong hop le.");
            return null;
        }

        private void nhapNhieuPhong() {
            int soLuong = Utils.nhapSo("Nhap so luong phong can them: ");
            if (soLuong <= 0) {
                System.out.println("So luong phai lon hon 0.");
                return;
            }
            for (int i = 0; i < soLuong; i++) {
                System.out.println("\n--- Nhap phong thu " + (i + 1) + " ---");
                themPhong();
            }
        }

        private void timKiemPhong() {
            String tuKhoa = Utils.nhapChuoi("Nhap ma, ten, tang hoac trang thai: ")
                    .toLowerCase();
            boolean timThay = false;
            for (int i = 0; i < ungDung.dsPhong.getSoLuong(); i++) {
                Phong phong = ungDung.dsPhong.get(i);
                if (chuaTuKhoa(phong.getMaPhong(), tuKhoa)
                        || chuaTuKhoa(phong.getTenPhong(), tuKhoa)
                        || String.valueOf(phong.getTang()).contains(tuKhoa)
                        || chuaTuKhoa(phong.getTrangThai(), tuKhoa)) {
                    phong.hienThiThongTin();
                    System.out.println("--------------------");
                    timThay = true;
                }
            }
            if (!timThay) {
                System.out.println("Khong tim thay phong phu hop.");
            }
        }

        private boolean chuaTuKhoa(String giaTri, String tuKhoa) {
            return giaTri != null && giaTri.toLowerCase().contains(tuKhoa);
        }

        private void thongKe() {
            int trong = 0;
            int daDat = 0;
            int don = 0;
            int doi = 0;
            int vip = 0;
            for (int i = 0; i < ungDung.dsPhong.getSoLuong(); i++) {
                Phong phong = ungDung.dsPhong.get(i);
                if (phong.getTrangThai().equalsIgnoreCase("Trong")) trong++;
                else daDat++;
                if (phong instanceof PhongDon) don++;
                else if (phong instanceof PhongDoi) doi++;
                else if (phong instanceof PhongVIP) vip++;
            }
            int nam = 0;
            int nu = 0;
            int khac = 0;
            for (int i = 0; i < ungDung.dsKhach.getSoLuong(); i++) {
                String gioiTinh = ungDung.dsKhach.get(i).getGioiTinh();
                if (gioiTinh.equalsIgnoreCase("Nam")) nam++;
                else if (gioiTinh.equalsIgnoreCase("Nu")) nu++;
                else khac++;
            }
            int choXacNhan = 0;
            int daTraPhong = 0;
            int daHuy = 0;
            for (int i = 0; i < ungDung.dsDatPhong.getSoLuong(); i++) {
                String trangThai = ungDung.dsDatPhong.get(i).getTrangThai();
                if (trangThai.equalsIgnoreCase("Cho xac nhan")) choXacNhan++;
                else if (trangThai.equalsIgnoreCase("Da tra phong")) daTraPhong++;
                else if (trangThai.equalsIgnoreCase("Da huy")) daHuy++;
            }
            int daThanhToan = 0;
            int chuaThanhToan = 0;
            for (int i = 0; i < ungDung.dsHoaDon.getSoLuong(); i++) {
                if (ungDung.dsHoaDon.get(i).getTrangThai()
                        .equalsIgnoreCase("Da thanh toan")) daThanhToan++;
                else chuaThanhToan++;
            }
            System.out.println("\n===== THONG KE =====");
            System.out.println("Phong: tong " + ungDung.dsPhong.getSoLuong()
                    + ", trong " + trong + ", dang dat " + daDat);
            System.out.println("Theo loai: don " + don + ", doi " + doi + ", VIP " + vip);
            System.out.println("Khach hang theo gioi tinh: Nam " + nam + ", Nu "
                    + nu + ", Khac " + khac);
            System.out.println("Dat phong: cho xac nhan " + choXacNhan
                    + ", da tra phong " + daTraPhong + ", da huy " + daHuy);
            System.out.println("Hoa don: da thanh toan " + daThanhToan
                    + ", chua thanh toan " + chuaThanhToan);
            System.out.println("Doanh thu da thanh toan: "
                    + Utils.dinhDangTien(ungDung.dsHoaDon.tinhDoanhThu()));
        }

        private void dangKyTaiKhoanNhanVien() {
            String tenDangNhap = Utils.nhapChuoi("Ten dang nhap: ");
            if (tenDangNhap.length() == 0
                    || ungDung.dsTaiKhoan.tim(tenDangNhap) != null) {
                System.out.println("Ten dang nhap khong hop le hoac da ton tai.");
                return;
            }
            String ma = taoMaNhanVienMoi();
            NhanVien nhanVien = nhapNhanVien(ma);
            if (nhanVien == null) return;
            String matKhau = ungDung.nhapMatKhauMoi();
            ungDung.dsNhanVien.them(nhanVien);
            ungDung.dsTaiKhoan.them(new TaiKhoan(tenDangNhap, matKhau,
                    nhanVien.getVaiTro(), ma, true));
            ungDung.ghiDuLieu();
            System.out.println("Dang ky tai khoan nhan vien thanh cong.");
        }

        private String taoMaNhanVienMoi() {
            int soThuTu = ungDung.dsNhanVien.getSoLuong() + 1;
            String ma = "NV" + soThuTu;
            while (ungDung.dsNhanVien.tim(ma) != null) {
                soThuTu++;
                ma = "NV" + soThuTu;
            }
            return ma;
        }
    }
