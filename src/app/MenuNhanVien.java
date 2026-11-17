package app;

import model.DatPhong;
import model.KhachHang;
import model.Phong;

public class MenuNhanVien {
    private HotelManagementApp ungDung;

    public MenuNhanVien(HotelManagementApp ungDung) {
        this.ungDung = ungDung;
    }

    public void chay() {
        boolean tiepTuc = true;
        while (tiepTuc) {
            System.out.println("\n===== MENU NHAN VIEN =====");
            System.out.println("1. Xem phong trong");
            System.out.println("2. Dat phong");
            System.out.println("3. Thong tin khach hang");
            System.out.println("4. Xem phong da dat");
            System.out.println("5. Thanh toan va tra phong");
            System.out.println("0. Dang xuat");
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) {
                xemPhongTrong();
            } else if (chon == 2) {
                datPhong();
            } else if (chon == 3) {
                new MenuKhachHang(ungDung).chay();
            } else if (chon == 4) {
                xemTatCaDatPhong();
            } else if (chon == 5) {
                ungDung.thanhToanVaTraPhong();
            } else if (chon == 0) {
                tiepTuc = false;
            } else {
                System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private void xemPhongTrong() {
            for (int i = 0; i < ungDung.dsPhong.getSoLuong(); i++) {
                Phong phong = ungDung.dsPhong.get(i);
                if (phong.getTrangThai().equalsIgnoreCase("Trong")) {
                    phong.hienThiThongTin();
                    System.out.println("--------------------");
                }
            }
        }

    private void datPhong() {
            String soDienThoai = ungDung.nhapSoDienThoai();
            KhachHang khach = ungDung.timKhachTheoSoDienThoai(soDienThoai);
            if (khach == null) {
                System.out.println("So dien thoai chua duoc dang ky cho khach hang.");
                return;
            }
            xemPhongTrong();
            String maPhong = Utils.nhapChuoi("Nhap ma phong: ");
            Phong phong = ungDung.dsPhong.tim(maPhong);
            if (phong == null || !phong.getTrangThai().equalsIgnoreCase("Trong")) {
                System.out.println("Phong khong ton tai hoac khong trong.");
                return;
            }
            int soNguoi = Utils.nhapSo("So luong nguoi: ");
            if (soNguoi <= 0 || soNguoi > phong.getSoNguoiToiDa()) {
                System.out.println("So luong nguoi khong phu hop suc chua phong.");
                return;
            }
            String nhan = Utils.nhapChuoi("Ngay nhan (yyyyMMdd): ");
            String tra = Utils.nhapChuoi("Ngay tra (yyyyMMdd): ");
            int soDem = Utils.nhapSo("So dem: ");
            if (!ungDung.hopLeKhoangNgay(nhan, tra) || !Utils.laSoDuong(soDem)) {
                System.out.println("Khoang ngay hoac so dem khong hop le.");
                return;
            }
            if (ungDung.biTrungPhong(maPhong, nhan, tra)) {
                System.out.println("Phong da bi dat trong khoang ngay nay.");
                return;
            }
            String maDat = "DP" + (ungDung.dsDatPhong.getSoLuong() + 1);
            ungDung.dsDatPhong.them(new DatPhong(maDat, khach.getMa(), maPhong,
                    nhan, tra, soDem, "Cho xac nhan"));
            phong.setTrangThai("Da dat");
            ungDung.ghiDuLieu();
            System.out.println("Dat phong thanh cong: " + maDat);
        }

        private void xemTatCaDatPhong() {
            boolean coDatPhong = false;
            for (int i = 0; i < ungDung.dsDatPhong.getSoLuong(); i++) {
                DatPhong datPhong = ungDung.dsDatPhong.get(i);
                if (datPhong.getTrangThai().equalsIgnoreCase("Da tra phong")) {
                    continue;
                }
                datPhong.hienThiThongTin();
                KhachHang khach = ungDung.dsKhach.tim(datPhong.getMaKhach());
                if (khach != null) {
                    System.out.println("So dien thoai khach: " + khach.getSoDienThoai());
                }
                Phong phong = ungDung.dsPhong.tim(datPhong.getMaPhong());
                if (phong != null) {
                    System.out.println("Ten phong: " + phong.getTenPhong());
                    System.out.println("Loai phong: " + phong.getClass().getSimpleName());
                }
                System.out.println("--------------------");
                coDatPhong = true;
            }
            if (!coDatPhong) {
                System.out.println("Chua co phong nao duoc dat.");
            }
        }
    }
