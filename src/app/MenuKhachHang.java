package app;

import model.KhachHang;

public class MenuKhachHang {
    private HotelManagementApp ungDung;

    public MenuKhachHang(HotelManagementApp ungDung) {
        this.ungDung = ungDung;
    }

    public void chay() {
        boolean tiepTuc = true;
        while (tiepTuc) {
            System.out.println("\n===== THONG TIN KHACH HANG =====");
            System.out.println("1. Xem tat ca");
            System.out.println("2. Tim theo tu khoa (ma, ten, sdt, gioi tinh, dia chi)");
            System.out.println("3. Them khach hang");
            System.out.println("4. Sua khach hang");
            System.out.println("5. Xoa khach hang");
            System.out.println("0. Quay lai");
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) {
                ungDung.dsKhach.hienThiDanhSach();
            } else if (chon == 2) {
                timKiemKhachHang();
            } else if (chon == 3) {
                themKhachHang();
            } else if (chon == 4) {
                suaKhachHang();
            } else if (chon == 5) {
                xoaKhachHang();
            } else if (chon == 0) {
                tiepTuc = false;
            } else {
                System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private void timKiemKhachHang() {
        String tuKhoa = Utils.nhapChuoi("Nhap tu khoa can tim: ").toLowerCase();
        boolean timThay = false;
        for (int i = 0; i < ungDung.dsKhach.getSoLuong(); i++) {
            KhachHang khach = ungDung.dsKhach.get(i);
            if (chuaTuKhoa(khach.getMa(), tuKhoa)
                    || chuaTuKhoa(khach.getHoTen(), tuKhoa)
                    || chuaTuKhoa(khach.getSoDienThoai(), tuKhoa)
                    || chuaTuKhoa(khach.getGioiTinh(), tuKhoa)
                    || chuaTuKhoa(khach.getDiaChi(), tuKhoa)) {
                khach.hienThiThongTin();
                System.out.println("--------------------");
                timThay = true;
            }
        }
        if (!timThay) {
            System.out.println("Khong tim thay khach hang phu hop.");
        }
    }

    private boolean chuaTuKhoa(String giaTri, String tuKhoa) {
        return giaTri != null && giaTri.toLowerCase().contains(tuKhoa);
    }

    private void themKhachHang() {
        String ma = "KH" + (ungDung.dsKhach.getSoLuong() + 1);
        KhachHang khach = ungDung.nhapKhachHang(ma);
        if (khach != null
                && ungDung.timKhachTheoSoDienThoai(khach.getSoDienThoai()) == null) {
            ungDung.dsKhach.them(khach);
            ungDung.ghiDuLieu();
            System.out.println("Them khach hang thanh cong.");
        } else if (khach != null) {
            System.out.println("So dien thoai da duoc dang ky.");
        }
    }

    private void suaKhachHang() {
        KhachHang khach = ungDung.timKhachTheoSoDienThoai(
                ungDung.nhapSoDienThoai());
        if (khach == null) {
            System.out.println("Khong tim thay khach hang.");
            return;
        }
        khach.setHoTen(Utils.nhapChuoi("Ho ten moi: "));
        khach.setNamSinh(ungDung.nhapNamSinhKhachHang());
        khach.setGioiTinh(ungDung.nhapGioiTinh());
        String soDienThoaiMoi = ungDung.nhapSoDienThoai();
        KhachHang khachTrung = ungDung.timKhachTheoSoDienThoai(soDienThoaiMoi);
        if (khachTrung != null && khachTrung != khach) {
            System.out.println("So dien thoai moi da duoc dang ky.");
            return;
        }
        khach.setSoDienThoai(soDienThoaiMoi);
        khach.setDiaChi(Utils.nhapChuoi("Dia chi moi: "));
        ungDung.ghiDuLieu();
        System.out.println("Sua khach hang thanh cong.");
    }

    private void xoaKhachHang() {
        KhachHang khach = ungDung.timKhachTheoSoDienThoai(
                ungDung.nhapSoDienThoai());
        if (khach == null) {
            System.out.println("Khong tim thay khach hang.");
            return;
        }
        if (ungDung.timDatPhongDangSuDung(khach.getMa()) != null) {
            System.out.println("Khong the xoa khach hang dang co dat phong.");
            return;
        }
        ungDung.dsKhach.xoa(khach.getMa());
        ungDung.ghiDuLieu();
        System.out.println("Xoa khach hang thanh cong.");
    }
}
