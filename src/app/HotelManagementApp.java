package app;

import java.io.File;
import model.DanhSachDatPhong;
import model.DanhSachDichVu;
import model.DanhSachHoaDon;
import model.DanhSachKhachHang;
import model.DanhSachNhanVien;
import model.DanhSachPhong;
import model.DanhSachTaiKhoan;
import model.DatPhong;
import model.DichVu;
import model.DichVuAn;
import model.DichVuGiatLa;
import model.DichVuSpa;
import model.HoaDon;
import model.KhachHang;
import model.LeTan;
import model.NhanVien;
import model.Phong;
import model.PhongDon;
import model.PhongDoi;
import model.PhongVIP;
import model.QuanLy;
import model.TaiKhoan;
import java.util.ArrayList;
import java.util.Calendar;

public class HotelManagementApp {
    private static final String DUONG_DAN_DATA = "data\\";
    private static final String FILE_PHONG = DUONG_DAN_DATA + "phong.txt";
    private static final String FILE_KHACH = DUONG_DAN_DATA + "khachhang.txt";
    private static final String FILE_NHAN_VIEN = DUONG_DAN_DATA + "nhanvien.txt";
    private static final String FILE_TAI_KHOAN = DUONG_DAN_DATA + "taikhoan.txt";
    private static final String FILE_DAT_PHONG = DUONG_DAN_DATA + "datphong.txt";
    private static final String FILE_HOA_DON = DUONG_DAN_DATA + "hoadon.txt";
    private static final String FILE_DICH_VU = DUONG_DAN_DATA + "dichvu.txt";

    private DanhSachPhong dsPhong = new DanhSachPhong();
    private DanhSachKhachHang dsKhach = new DanhSachKhachHang();
    private DanhSachNhanVien dsNhanVien = new DanhSachNhanVien();
    private DanhSachTaiKhoan dsTaiKhoan = new DanhSachTaiKhoan();
    private DanhSachDatPhong dsDatPhong = new DanhSachDatPhong();
    private DanhSachHoaDon dsHoaDon = new DanhSachHoaDon();
    private DanhSachDichVu dsDichVu = new DanhSachDichVu();

    public void chay() {
        docDuLieu();
        taoDuLieuMau();
        boolean tiepTuc = true;
        while (tiepTuc) {
            inMenuChinh();
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) {
                dangNhap();
            } else if (chon == 0) {
                ghiDuLieu();
                tiepTuc = false;
            } else {
                System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private void inMenuChinh() {
        System.out.println("\n===== QUAN LY KHACH SAN =====");
        System.out.println("1. Dang nhap");
        System.out.println("0. Thoat");
    }

    private void docDuLieu() {
        docNeuCo(dsPhong, FILE_PHONG);
        docNeuCo(dsKhach, FILE_KHACH);
        docNeuCo(dsNhanVien, FILE_NHAN_VIEN);
        docNeuCo(dsTaiKhoan, FILE_TAI_KHOAN);
        docNeuCo(dsDatPhong, FILE_DAT_PHONG);
        docNeuCo(dsHoaDon, FILE_HOA_DON);
        docNeuCo(dsDichVu, FILE_DICH_VU);
    }

    private void docNeuCo(interfaces.IFileIO danhSach, String path) {
        if (new File(path).exists()) {
            danhSach.docFile(path);
        }
    }

    private void ghiDuLieu() {
        dsPhong.ghiFile(FILE_PHONG);
        dsKhach.ghiFile(FILE_KHACH);
        dsNhanVien.ghiFile(FILE_NHAN_VIEN);
        dsTaiKhoan.ghiFile(FILE_TAI_KHOAN);
        dsDatPhong.ghiFile(FILE_DAT_PHONG);
        dsHoaDon.ghiFile(FILE_HOA_DON);
        dsDichVu.ghiFile(FILE_DICH_VU);
    }

    private void taoDuLieuMau() {
        if (dsPhong.getSoLuong() == 0) {
            PhongDon phongDon = new PhongDon("P101", "Phong don 101", 1,
                    "Trong", 300000, 0);
            phongDon.setSoNguoiToiDa(2);
            dsPhong.them(phongDon);
            PhongDoi phongDoi = new PhongDoi("P201", "Phong doi 201", 2,
                    "Trong", 500000, 2);
            phongDoi.setSoNguoiToiDa(4);
            dsPhong.them(phongDoi);
            PhongVIP phongVip = new PhongVIP("P301", "Phong VIP 301", 3,
                    "Trong", 1000000, 200000);
            phongVip.setSoNguoiToiDa(6);
            dsPhong.them(phongVip);
        }
        if (dsNhanVien.getSoLuong() == 0) {
            dsNhanVien.them(new LeTan("NV01", "Nguyen Le Tan", 1998,
                    "Nam", "0900000001", "Ha Noi", 7000000, "01/01/2022", 500000));
            dsNhanVien.them(new QuanLy("NV02", "Pham Quan Ly", 1990,
                    "Nam", "0900000004", "Hai Phong", 12000000, "01/01/2020", 1.5));
        }
        if (dsTaiKhoan.getSoLuong() == 0) {
            dsTaiKhoan.them(new TaiKhoan("admin", "admin123", "QUANLY", "NV02", true));
            dsTaiKhoan.them(new TaiKhoan("letan", "123456", "LETAN", "NV01", true));
            dsTaiKhoan.ghiFile(FILE_TAI_KHOAN);
        }
        if (dsDichVu.getSoLuong() == 0) {
            dsDichVu.them(new DichVuAn("DV01", "Bua sang", 50000,
                    "suat", "Mon an"));
            dsDichVu.them(new DichVuGiatLa("DV02", "Giat la", 100000,
                    "kg", 5));
            dsDichVu.them(new DichVuSpa("DV03", "Spa", 300000,
                    "goi", 60));
            dsDichVu.ghiFile(FILE_DICH_VU);
        }
    }

    private void dangNhap() {
        String user = Utils.nhapChuoi("Ten dang nhap: ");
        String pass = Utils.nhapChuoi("Mat khau: ");
        TaiKhoan taiKhoan = dsTaiKhoan.dangNhap(user, pass);
        if (taiKhoan == null) {
            System.out.println("Sai tai khoan, mat khau hoac tai khoan bi khoa.");
            return;
        }
        System.out.println("Dang nhap thanh cong voi vai tro " + taiKhoan.getVaiTro());
        hienThiMenuVaiTro(taiKhoan);
    }

    private void hienThiMenuVaiTro(TaiKhoan taiKhoan) {
        if (taiKhoan.getVaiTro().equals("LETAN")) {
            menuNhanVien();
        } else if (taiKhoan.getVaiTro().equals("QUANLY")) {
            menuQuanLy();
        } else {
            System.out.println("Tai khoan khong con duoc ho tro.");
        }
        TaiKhoan.dangXuat();
    }

    private void menuNhanVien() {
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
            if (chon == 1) xemPhongTrong();
            else if (chon == 2) datPhong();
            else if (chon == 3) menuThongTinKhachHang();
            else if (chon == 4) xemTatCaDatPhong();
            else if (chon == 5) thanhToanVaTraPhong();
            else if (chon == 0) tiepTuc = false;
            else System.out.println("Lua chon khong hop le.");
        }
    }

    private void menuQuanLy() {
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
            if (chon == 1) quanLyPhong();
            else if (chon == 2) quanLyNhanVien();
            else if (chon == 3) menuThongTinKhachHang();
            else if (chon == 4) dsTaiKhoan.hienThiDanhSach();
            else if (chon == 5) {
                dsHoaDon.hienThiDanhSach();
                System.out.println("Doanh thu: " + Utils.dinhDangTien(dsHoaDon.tinhDoanhThu()));
            } else if (chon == 6) hienThiLuong();
            else if (chon == 7) dangKyTaiKhoanNhanVien();
            else if (chon == 8) nhapNhieuPhong();
            else if (chon == 9) timKiemPhong();
            else if (chon == 10) thongKe();
            else if (chon == 0) tiepTuc = false;
            else System.out.println("Lua chon khong hop le.");
        }
    }

    private void xemPhongTrong() {
        for (int i = 0; i < dsPhong.getSoLuong(); i++) {
            Phong phong = dsPhong.get(i);
            if (phong.getTrangThai().equalsIgnoreCase("Trong")) {
                phong.hienThiThongTin();
                System.out.println("--------------------");
            }
        }
    }

    private void datPhong() {
        String soDienThoai = nhapSoDienThoai();
        KhachHang khach = timKhachTheoSoDienThoai(soDienThoai);
        if (khach == null) {
            System.out.println("So dien thoai chua duoc dang ky cho khach hang.");
            return;
        }
        xemPhongTrong();
        String maPhong = Utils.nhapChuoi("Nhap ma phong: ");
        Phong phong = dsPhong.tim(maPhong);
        if (phong == null || !phong.getTrangThai().equalsIgnoreCase("Trong")) {
            System.out.println("Phong khong ton tai hoac khong trong.");
            return;
        }
        int soNguoi = Utils.nhapSo("So luong nguoi: ");
        if (soNguoi <= 0) {
            System.out.println("So luong nguoi phai lon hon 0.");
            return;
        }
        if (soNguoi > phong.getSoNguoiToiDa()) {
            System.out.println("Phong chi phu hop toi da "
                    + phong.getSoNguoiToiDa() + " nguoi.");
            return;
        }
        String nhan = Utils.nhapChuoi("Ngay nhan (yyyyMMdd): ");
        String tra = Utils.nhapChuoi("Ngay tra (yyyyMMdd): ");
        int soDem = Utils.nhapSo("So dem: ");
        if (!hopLeKhoangNgay(nhan, tra) || !Utils.laSoDuong(soDem)) {
            System.out.println("Khoang ngay hoac so dem khong hop le.");
            return;
        }
        if (biTrungPhong(maPhong, nhan, tra)) {
            System.out.println("Phong da bi dat trong khoang ngay nay.");
            return;
        }
        String maDat = "DP" + (dsDatPhong.getSoLuong() + 1);
        dsDatPhong.them(new DatPhong(maDat, khach.getMa(), maPhong, nhan, tra,
                soDem, "Cho xac nhan"));
        phong.setTrangThai("Da dat");
        ghiDuLieu();
        System.out.println("Dat phong thanh cong: " + maDat);
    }

    private boolean hopLeKhoangNgay(String nhan, String tra) {
        return nhan.matches("\\d{8}") && tra.matches("\\d{8}")
                && ngayToSo(nhan) < ngayToSo(tra);
    }

    private int ngayToSo(String ngay) {
        try {
            return Integer.parseInt(ngay);
        } catch (Exception e) {
            return 0;
        }
    }

    private boolean biTrungPhong(String maPhong, String nhan, String tra) {
        for (int i = 0; i < dsDatPhong.getSoLuong(); i++) {
            DatPhong d = dsDatPhong.get(i);
            boolean cungPhong = d.getMaPhong().equalsIgnoreCase(maPhong);
            boolean dangDat = !d.getTrangThai().equalsIgnoreCase("Da huy")
                    && !d.getTrangThai().equalsIgnoreCase("Da tra phong");
            boolean giaoNhau = ngayToSo(nhan) < ngayToSo(d.getNgayTra())
                    && ngayToSo(tra) > ngayToSo(d.getNgayNhan());
            if (cungPhong && dangDat && giaoNhau) {
                return true;
            }
        }
        return false;
    }

    private void xemTatCaDatPhong() {
        boolean coDatPhong = false;
        for (int i = 0; i < dsDatPhong.getSoLuong(); i++) {
            DatPhong datPhong = dsDatPhong.get(i);
            datPhong.hienThiThongTin();
            KhachHang khach = dsKhach.tim(datPhong.getMaKhach());
            if (khach != null) {
                System.out.println("So dien thoai khach: " + khach.getSoDienThoai());
            }
            Phong phong = dsPhong.tim(datPhong.getMaPhong());
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

    private void thanhToanVaTraPhong() {
        String soDienThoai = nhapSoDienThoai();
        KhachHang khach = timKhachTheoSoDienThoai(soDienThoai);
        if (khach == null) {
            System.out.println("So dien thoai chua duoc dang ky cho khach hang.");
            return;
        }
        ArrayList<DatPhong> datPhongs = timTatCaDatPhongDangSuDung(khach.getMa());
        if (datPhongs.size() == 0) {
            System.out.println("Khach hang khong co phong dang dat.");
            return;
        }
        ArrayList<HoaDon> hoaDons = new ArrayList<HoaDon>();
        double tongTien = 0;
        for (int i = 0; i < datPhongs.size(); i++) {
            DatPhong datPhong = datPhongs.get(i);
            datPhong.hienThiThongTin();
            Phong phong = dsPhong.tim(datPhong.getMaPhong());
            double tienPhong = phong == null ? 0 : phong.tinhGia(datPhong.getSoDem());
            HoaDon hoaDon = timHoaDonTheoDatPhong(datPhong.getMaDatPhong());
            if (hoaDon == null) {
                hoaDon = new HoaDon("HD" + (dsHoaDon.getSoLuong() + 1),
                        datPhong.getMaDatPhong(), khach.getMa(), datPhong.getNgayTra(),
                        tienPhong, 0, 0, "Chua thanh toan");
                hoaDon.setChiTietDichVu("Khong su dung dich vu");
                dsHoaDon.them(hoaDon);
            } else if (hoaDon.getTrangThai().equalsIgnoreCase("Chua thanh toan")) {
                hoaDon.setTienPhong(tienPhong);
            }
            if (hoaDon.getTrangThai().equalsIgnoreCase("Chua thanh toan")) {
                hoaDon.setGiamGia(0);
                suaChiTietHoaDon(hoaDon);
            }
            hoaDon.hienThiThongTin();
            tongTien += hoaDon.tinhTien();
            hoaDons.add(hoaDon);
        }
        System.out.println("Tong tien tat ca phong: "
                + Utils.dinhDangTien(tongTien));
        String xacNhan = Utils.nhapChuoi("Xac nhan thanh toan? (Y/N): ");
        if (!xacNhan.equalsIgnoreCase("Y")) {
            ghiDuLieu();
            System.out.println("Da luu thay doi hoa don, chua thanh toan.");
            return;
        }
        for (int i = 0; i < datPhongs.size(); i++) {
            DatPhong datPhong = datPhongs.get(i);
            hoaDons.get(i).thanhToan();
            datPhong.setTrangThai("Da tra phong");
            Phong phong = dsPhong.tim(datPhong.getMaPhong());
            if (phong != null) {
                phong.setTrangThai("Trong");
            }
        }
        ghiDuLieu();
        System.out.println("Thanh toan va tra phong thanh cong.");
    }

    private void suaChiTietHoaDon(HoaDon hoaDon) {
        String suDung = Utils.nhapChuoi("Khach co su dung dich vu? (Y/N): ");
        if (!suDung.equalsIgnoreCase("Y")) {
            hoaDon.setTienDichVu(0);
            hoaDon.setChiTietDichVu("Khong su dung dich vu");
            hoaDon.tinhTien();
            return;
        }
        if (dsDichVu.getSoLuong() == 0) {
            System.out.println("Chua co dich vu nao trong he thong.");
            hoaDon.setTienDichVu(0);
            hoaDon.setChiTietDichVu("Khong su dung dich vu");
            return;
        }

        double tienDichVu = 0;
        StringBuilder chiTiet = new StringBuilder();
        while (true) {
            System.out.println("\n===== DICH VU CO SAN =====");
            for (int i = 0; i < dsDichVu.getSoLuong(); i++) {
                DichVu dichVu = dsDichVu.get(i);
                System.out.println((i + 1) + ". " + dichVu.getTenDichVu()
                        + " - " + Utils.dinhDangTien(dichVu.getDonGia())
                        + "/" + dichVu.getDonViTinh());
            }
            System.out.println("0. Hoan tat chon dich vu");
            int chon = Utils.nhapSo("Chon dich vu: ");
            if (chon == 0) {
                break;
            }
            if (chon < 1 || chon > dsDichVu.getSoLuong()) {
                System.out.println("Dich vu khong hop le.");
                continue;
            }
            int soLuong = Utils.nhapSo("So luong: ");
            if (soLuong <= 0) {
                System.out.println("So luong phai lon hon 0.");
                continue;
            }
            DichVu dichVu = dsDichVu.get(chon - 1);
            double thanhTien = dichVu.getDonGia() * soLuong;
            tienDichVu += thanhTien;
            if (chiTiet.length() > 0) {
                chiTiet.append(", ");
            }
            chiTiet.append(dichVu.getTenDichVu()).append(" x ")
                    .append(soLuong).append(" = ")
                    .append(Utils.dinhDangTien(thanhTien));
            System.out.println("Da them dich vu.");
        }
        if (chiTiet.length() == 0) {
            chiTiet.append("Khong su dung dich vu");
        }
        hoaDon.setTienDichVu(tienDichVu);
        hoaDon.setChiTietDichVu(chiTiet.toString().replace("|", "/"));
        hoaDon.tinhTien();
    }

    private DatPhong timDatPhongDangSuDung(String maKhach) {
        for (int i = 0; i < dsDatPhong.getSoLuong(); i++) {
            DatPhong datPhong = dsDatPhong.get(i);
            if (datPhong.getMaKhach().equalsIgnoreCase(maKhach)
                    && !datPhong.getTrangThai().equalsIgnoreCase("Da huy")
                    && !datPhong.getTrangThai().equalsIgnoreCase("Da tra phong")) {
                return datPhong;
            }
        }
        return null;
    }

    private ArrayList<DatPhong> timTatCaDatPhongDangSuDung(String maKhach) {
        ArrayList<DatPhong> ketQua = new ArrayList<DatPhong>();
        for (int i = 0; i < dsDatPhong.getSoLuong(); i++) {
            DatPhong datPhong = dsDatPhong.get(i);
            if (datPhong.getMaKhach().equalsIgnoreCase(maKhach)
                    && !datPhong.getTrangThai().equalsIgnoreCase("Da huy")
                    && !datPhong.getTrangThai().equalsIgnoreCase("Da tra phong")) {
                ketQua.add(datPhong);
            }
        }
        return ketQua;
    }

    private HoaDon timHoaDonTheoDatPhong(String maDatPhong) {
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            if (dsHoaDon.get(i).getMaDatPhong().equalsIgnoreCase(maDatPhong)) {
                return dsHoaDon.get(i);
            }
        }
        return null;
    }

    private KhachHang timKhachTheoSoDienThoai(String soDienThoai) {
        for (int i = 0; i < dsKhach.getSoLuong(); i++) {
            KhachHang khach = dsKhach.get(i);
            if (khach.getSoDienThoai().equals(soDienThoai)) {
                return khach;
            }
        }
        return null;
    }

    private KhachHang nhapKhachHang(String ma) {
        String ten = Utils.nhapChuoi("Ho ten: ");
        if (ten.length() == 0) {
            System.out.println("Ho ten khong duoc de trong.");
            return null;
        }
        int nam = nhapNamSinhKhachHang();
        String gioiTinh = nhapGioiTinh();
        String dienThoai = nhapSoDienThoai();
        String diaChi = Utils.nhapChuoi("Dia chi: ");
        return new KhachHang(ma, ten, nam, gioiTinh, dienThoai, diaChi, 0, "Thuong");
    }

    private void menuThongTinKhachHang() {
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
                dsKhach.hienThiDanhSach();
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

    private void themKhachHang() {
        String ma = "KH" + (dsKhach.getSoLuong() + 1);
        KhachHang khach = nhapKhachHang(ma);
        if (khach != null && timKhachTheoSoDienThoai(khach.getSoDienThoai()) == null) {
            dsKhach.them(khach);
            ghiDuLieu();
            System.out.println("Them khach hang thanh cong.");
        } else if (khach != null) {
            System.out.println("So dien thoai da duoc dang ky.");
        }
    }

    private void suaKhachHang() {
        KhachHang khach = timKhachTheoSoDienThoai(nhapSoDienThoai());
        if (khach == null) {
            System.out.println("Khong tim thay khach hang.");
            return;
        }
        String ten = Utils.nhapChuoi("Ho ten moi: ");
        int nam = nhapNamSinhKhachHang();
        String gioiTinh = nhapGioiTinh();
        String soDienThoaiMoi = nhapSoDienThoai();
        KhachHang khachTrung = timKhachTheoSoDienThoai(soDienThoaiMoi);
        if (khachTrung != null && khachTrung != khach) {
            System.out.println("So dien thoai moi da duoc dang ky.");
            return;
        }
        khach.setHoTen(ten);
        khach.setNamSinh(nam);
        khach.setGioiTinh(gioiTinh);
        khach.setSoDienThoai(soDienThoaiMoi);
        khach.setDiaChi(Utils.nhapChuoi("Dia chi moi: "));
        ghiDuLieu();
        System.out.println("Sua khach hang thanh cong.");
    }

    private void xoaKhachHang() {
        KhachHang khach = timKhachTheoSoDienThoai(nhapSoDienThoai());
        if (khach == null) {
            System.out.println("Khong tim thay khach hang.");
            return;
        }
        if (timDatPhongDangSuDung(khach.getMa()) != null) {
            System.out.println("Khong the xoa khach hang dang co dat phong.");
            return;
        }
        dsKhach.xoa(khach.getMa());
        ghiDuLieu();
        System.out.println("Xoa khach hang thanh cong.");
    }

    private String nhapMatKhauMoi() {
        while (true) {
            String pass = Utils.nhapChuoi("Mat khau (it nhat 6 ky tu): ");
            if (pass.length() >= 6) {
                return pass;
            }
            System.out.println("Mat khau phai co it nhat 6 ky tu.");
        }
    }

    private int nhapNamSinh() {
        int namHienTai = Calendar.getInstance().get(Calendar.YEAR);
        while (true) {
            int nam = Utils.nhapSo("Nam sinh: ");
            if (nam >= 1900 && nam <= namHienTai) {
                return nam;
            }
            System.out.println("Nam sinh phai tu 1900 den " + namHienTai + ".");
        }
    }

    private int nhapNamSinhKhachHang() {
        int namHienTai = Calendar.getInstance().get(Calendar.YEAR);
        while (true) {
            int nam = Utils.nhapSo("Nam sinh: ");
            if (nam >= 1900 && nam <= namHienTai - 18) {
                return nam;
            }
            System.out.println("Khach hang phai du 18 tuoi tro len.");
        }
    }

    private String nhapGioiTinh() {
        while (true) {
            String gioiTinh = Utils.nhapChuoi("Gioi tinh (Nam/Nu/Khac): ");
            if (gioiTinh.equalsIgnoreCase("Nam")
                    || gioiTinh.equalsIgnoreCase("Nu")
                    || gioiTinh.equalsIgnoreCase("Khac")) {
                return gioiTinh;
            }
            System.out.println("Gioi tinh chi nhan Nam, Nu hoac Khac.");
        }
    }

    private String nhapSoDienThoai() {
        while (true) {
            String dienThoai = Utils.nhapChuoi("So dien thoai (10 so): ");
            if (dienThoai.matches("0\\d{9}")) {
                return dienThoai;
            }
            System.out.println("So dien thoai phai co 10 chu so va bat dau bang 0.");
        }
    }

    private void quanLyPhong() {
        dsPhong.hienThiDanhSach();
        System.out.println("1. Them phong  2. Sua phong  3. Xoa phong  0. Quay lai");
        int chon = Utils.nhapSo("Chon: ");
        if (chon == 1) themPhong();
        else if (chon == 2) suaPhong();
        else if (chon == 3) {
            String ma = Utils.nhapChuoi("Ma phong can xoa: ");
            if (dsPhong.tim(ma) == null) {
                System.out.println("Khong tim thay ma phong, khong xoa.");
                return;
            }
            if (coDatPhong(ma)) {
                System.out.println("Khong the xoa phong dang co dat phong.");
                return;
            }
            dsPhong.xoa(ma);
            ghiDuLieu();
            System.out.println("Xoa phong thanh cong.");
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
        if (ma.length() == 0 || dsPhong.tim(ma) != null) {
            System.out.println("Ma phong khong duoc trong hoac da ton tai.");
            return;
        }
        String ten = Utils.nhapChuoi("Ten phong: ");
        int tang = Utils.nhapSo("Tang: ");
        double gia = Utils.nhapSoThuc("Gia co ban: ");
        int soNguoiToiDa = Utils.nhapSo("So nguoi toi da: ");
        if (tang <= 0 || gia <= 0 || soNguoiToiDa <= 0) {
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
        phong.setSoNguoiToiDa(soNguoiToiDa);
        dsPhong.them(phong);
        System.out.println("Them thanh cong.");
        ghiDuLieu();
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

    private void timKiemKhachHang() {
        String tuKhoa = Utils.nhapChuoi("Nhap tu khoa can tim: ").toLowerCase();
        boolean timThay = false;
        for (int i = 0; i < dsKhach.getSoLuong(); i++) {
            KhachHang khach = dsKhach.get(i);
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

    private void timKiemPhong() {
        String tuKhoa = Utils.nhapChuoi("Nhap ma, ten, tang hoac trang thai: ")
                .toLowerCase();
        boolean timThay = false;
        for (int i = 0; i < dsPhong.getSoLuong(); i++) {
            Phong phong = dsPhong.get(i);
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
        int phongTrong = 0;
        int phongDaDat = 0;
        int phongDon = 0;
        int phongDoi = 0;
        int phongVip = 0;
        for (int i = 0; i < dsPhong.getSoLuong(); i++) {
            Phong phong = dsPhong.get(i);
            if (phong.getTrangThai().equalsIgnoreCase("Trong")) {
                phongTrong++;
            } else {
                phongDaDat++;
            }
            if (phong instanceof PhongDon) {
                phongDon++;
            } else if (phong instanceof PhongDoi) {
                phongDoi++;
            } else if (phong instanceof PhongVIP) {
                phongVip++;
            }
        }

        int nam = 0;
        int nu = 0;
        int khac = 0;
        for (int i = 0; i < dsKhach.getSoLuong(); i++) {
            String gioiTinh = dsKhach.get(i).getGioiTinh();
            if (gioiTinh.equalsIgnoreCase("Nam")) {
                nam++;
            } else if (gioiTinh.equalsIgnoreCase("Nu")) {
                nu++;
            } else {
                khac++;
            }
        }

        int choXacNhan = 0;
        int daTraPhong = 0;
        int daHuy = 0;
        for (int i = 0; i < dsDatPhong.getSoLuong(); i++) {
            String trangThai = dsDatPhong.get(i).getTrangThai();
            if (trangThai.equalsIgnoreCase("Cho xac nhan")) {
                choXacNhan++;
            } else if (trangThai.equalsIgnoreCase("Da tra phong")) {
                daTraPhong++;
            } else if (trangThai.equalsIgnoreCase("Da huy")) {
                daHuy++;
            }
        }

        int daThanhToan = 0;
        int chuaThanhToan = 0;
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            if (dsHoaDon.get(i).getTrangThai().equalsIgnoreCase("Da thanh toan")) {
                daThanhToan++;
            } else {
                chuaThanhToan++;
            }
        }

        System.out.println("\n===== THONG KE =====");
        System.out.println("Phong: tong " + dsPhong.getSoLuong()
                + ", trong " + phongTrong + ", dang dat " + phongDaDat);
        System.out.println("Theo loai: don " + phongDon + ", doi " + phongDoi
                + ", VIP " + phongVip);
        System.out.println("Khach hang theo gioi tinh: Nam " + nam + ", Nu "
                + nu + ", Khac " + khac);
        System.out.println("Dat phong: cho xac nhan " + choXacNhan
                + ", da tra phong " + daTraPhong + ", da huy " + daHuy);
        System.out.println("Hoa don: da thanh toan " + daThanhToan
                + ", chua thanh toan " + chuaThanhToan);
        System.out.println("Doanh thu da thanh toan: "
                + Utils.dinhDangTien(dsHoaDon.tinhDoanhThu()));
    }

    private void suaPhong() {
        String maCu = Utils.nhapChuoi("Ma phong can sua: ");
        Phong phongCu = dsPhong.tim(maCu);
        if (phongCu == null) {
            System.out.println("Khong tim thay phong.");
            return;
        }
        String ten = Utils.nhapChuoi("Ten phong moi: ");
        int tang = Utils.nhapSo("Tang moi: ");
        double gia = Utils.nhapSoThuc("Gia co ban moi: ");
        int soNguoiToiDa = Utils.nhapSo("So nguoi toi da moi: ");
        if (ten.length() == 0 || tang <= 0 || gia <= 0 || soNguoiToiDa <= 0) {
            System.out.println("Thong tin phong khong hop le.");
            return;
        }
        phongCu.setTenPhong(ten);
        phongCu.setTang(tang);
        phongCu.setGiaCoBan(gia);
        phongCu.setSoNguoiToiDa(soNguoiToiDa);
        ghiDuLieu();
        System.out.println("Sua phong thanh cong.");
    }

    private boolean coDatPhong(String maPhong) {
        for (int i = 0; i < dsDatPhong.getSoLuong(); i++) {
            DatPhong datPhong = dsDatPhong.get(i);
            if (datPhong.getMaPhong().equalsIgnoreCase(maPhong)
                    && !datPhong.getTrangThai().equalsIgnoreCase("Da huy")
                    && !datPhong.getTrangThai().equalsIgnoreCase("Da tra phong")) {
                return true;
            }
        }
        return false;
    }

    private void quanLyNhanVien() {
        dsNhanVien.hienThiDanhSach();
        System.out.println("1. Them  2. Sua  3. Xoa  0. Quay lai");
        int chon = Utils.nhapSo("Chon: ");
        if (chon == 1) {
            themNhanVien();
        } else if (chon == 2) {
            suaNhanVien();
        } else if (chon == 3) {
            String ma = Utils.nhapChuoi("Nhap ma nhan vien can xoa: ");
            if (dsNhanVien.xoa(ma)) {
                System.out.println("Xoa nhan vien thanh cong.");
            } else {
                System.out.println("Khong tim thay ma nhan vien.");
            }
            ghiDuLieu();
        } else if (chon != 0) {
            System.out.println("Lua chon khong hop le.");
        }
    }

    private void themNhanVien() {
        String ma = Utils.nhapChuoi("Ma nhan vien: ");
        if (ma.length() == 0 || dsNhanVien.tim(ma) != null) {
            System.out.println("Ma nhan vien khong duoc trong hoac da ton tai.");
            return;
        }
        NhanVien nhanVien = nhapNhanVien(ma);
        if (nhanVien != null && dsNhanVien.them(nhanVien)) {
            ghiDuLieu();
            System.out.println("Them nhan vien thanh cong.");
        }
    }

    private void suaNhanVien() {
        String ma = Utils.nhapChuoi("Ma nhan vien can sua: ");
        if (dsNhanVien.tim(ma) == null) {
            System.out.println("Khong tim thay nhan vien.");
            return;
        }
        NhanVien moi = nhapNhanVien(ma);
        if (moi != null) {
            dsNhanVien.sua(ma, moi);
            for (int i = 0; i < dsTaiKhoan.getSoLuong(); i++) {
                TaiKhoan taiKhoan = dsTaiKhoan.get(i);
                if (taiKhoan.getMaNguoiDung().equalsIgnoreCase(ma)) {
                    taiKhoan.setVaiTro(moi.getVaiTro());
                }
            }
            ghiDuLieu();
            System.out.println("Sua nhan vien thanh cong.");
        }
    }

    private NhanVien nhapNhanVien(String ma) {
        String ten = Utils.nhapChuoi("Ho ten: ");
        if (ten.length() == 0) {
            System.out.println("Ho ten khong duoc de trong.");
            return null;
        }
        int nam = nhapNamSinh();
        String gioiTinh = nhapGioiTinh();
        String dienThoai = nhapSoDienThoai();
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

    private void dangKyTaiKhoanNhanVien() {
        String tenDangNhap = Utils.nhapChuoi("Ten dang nhap: ");
        if (tenDangNhap.length() == 0
                || dsTaiKhoan.tim(tenDangNhap) != null) {
            System.out.println("Ten dang nhap khong hop le hoac da ton tai.");
            return;
        }
        String ma = taoMaNhanVienMoi();
        NhanVien nhanVien = nhapNhanVien(ma);
        if (nhanVien == null) {
            return;
        }
        String matKhau = nhapMatKhauMoi();
        dsNhanVien.them(nhanVien);
        dsTaiKhoan.them(new TaiKhoan(tenDangNhap, matKhau,
                nhanVien.getVaiTro(), ma, true));
        ghiDuLieu();
        System.out.println("Dang ky tai khoan nhan vien thanh cong.");
    }

    private String taoMaNhanVienMoi() {
        int soThuTu = dsNhanVien.getSoLuong() + 1;
        String ma = "NV" + soThuTu;
        while (dsNhanVien.tim(ma) != null) {
            soThuTu++;
            ma = "NV" + soThuTu;
        }
        return ma;
    }

    private void hienThiLuong() {
        for (int i = 0; i < dsNhanVien.getSoLuong(); i++) {
            NhanVien nhanVien = dsNhanVien.get(i);
            System.out.println(nhanVien.getVaiTro() + " - "
                    + nhanVien.getHoTen() + ": "
                    + Utils.dinhDangTien(nhanVien.tinhLuong()));
        }
    }
}

