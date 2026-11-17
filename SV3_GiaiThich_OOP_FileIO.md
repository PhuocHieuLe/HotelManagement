# Tài liệu báo cáo SV3: Danh sách và `IFileIO`

> Tài liệu này được viết theo đúng mã nguồn hiện tại của project HotelManagement.
> Phần SV3 trong bảng phân công là **7 lớp `DanhSach...` + interface `IFileIO`**.

## 1. SV3 phụ trách những gì?

Bảy lớp danh sách là:

1. `DanhSachPhong`
2. `DanhSachNhanVien`
3. `DanhSachDatPhong`
4. `DanhSachKhachHang`
5. `DanhSachDichVu`
6. `DanhSachHoaDon`
7. `DanhSachTaiKhoan`

Các lớp này là lớp quản lý tập hợp đối tượng. Chúng không mô tả một phòng hay
một khách hàng riêng lẻ; chúng quản lý nhiều đối tượng cùng loại và cung cấp:

- thêm (`them`);
- xóa (`xoa`);
- tìm (`tim`);
- sửa (`sua`);
- lấy phần tử theo vị trí (`get`);
- đếm số phần tử (`getSoLuong`);
- hiển thị (`hienThiDanhSach`);
- đọc dữ liệu từ file (`docFile`);
- ghi dữ liệu ra file (`ghiFile`).

`IFileIO` thống nhất hai hành vi đọc và ghi file cho cả bảy lớp.

## 2. Bức tranh tổng thể của chương trình

Trong [`Main.java`](C:/Users/HELIOS%20300/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/GitHub/HotelManagement/src/app/Main.java),
chương trình tạo bảy đối tượng danh sách:

```java
private DanhSachPhong dsPhong = new DanhSachPhong();
private DanhSachKhachHang dsKhach = new DanhSachKhachHang();
private DanhSachNhanVien dsNhanVien = new DanhSachNhanVien();
private DanhSachTaiKhoan dsTaiKhoan = new DanhSachTaiKhoan();
private DanhSachDatPhong dsDatPhong = new DanhSachDatPhong();
private DanhSachHoaDon dsHoaDon = new DanhSachHoaDon();
private DanhSachDichVu dsDichVu = new DanhSachDichVu();
```

Luồng chạy:

1. `Main` gọi `docDuLieu()`.
2. `docDuLieu()` truyền từng danh sách vào `docNeuCo`.
3. `docNeuCo` nhận kiểu `IFileIO`, kiểm tra file tồn tại rồi gọi `docFile`.
4. Người dùng thao tác; các menu gọi `them`, `xoa`, `tim`, `sua`.
5. Khi thoát, `ghiDuLieu()` gọi `ghiFile` của cả bảy danh sách.

Điểm quan trọng: biến trong `docNeuCo` có kiểu `IFileIO`, nhưng đối tượng thật
có thể là `DanhSachPhong`, `DanhSachKhachHang` hoặc lớp danh sách khác. Đây là
**đa hình qua interface**.

## 3. Ôn lý thuyết OOP cần biết

### 3.1. Class và object

- **Class (lớp)** là bản thiết kế, gồm thuộc tính và phương thức.
- **Object (đối tượng)** là một thực thể được tạo từ class bằng `new`.

Ví dụ:

```java
DanhSachPhong dsPhong = new DanhSachPhong();
```

`DanhSachPhong` là class; `dsPhong` là object. Object này chứa nhiều object
`Phong`.

### 3.2. Bốn tính chất chính của OOP

#### Đóng gói (Encapsulation)

Đóng gói là gom dữ liệu và hành vi liên quan vào một class, đồng thời hạn chế
truy cập trực tiếp dữ liệu. Trong các lớp danh sách, mảng và số lượng được khai
báo `private`:

```java
private Phong[] ds;
private int soLuong;
```

Code bên ngoài không được tự ý sửa `ds` hoặc `soLuong`; phải thông qua các
phương thức như `them`, `xoa`, `get` và `getSoLuong`. Nhờ đó danh sách giữ được
quy tắc: không thêm `null`, không trùng mã, và `soLuong` luôn phản ánh số phần
tử hợp lệ.

#### Kế thừa (Inheritance)

Kế thừa cho phép class con dùng lại thuộc tính/phương thức của class cha.
Ví dụ trong phần khác của project:

```text
Nguoi
├── KhachHang
└── NhanVien
    ├── LeTan
    ├── KeToan
    ├── BuongPhong
    └── QuanLy

Phong
├── PhongDon
├── PhongDoi
└── PhongVIP
```

SV3 quản lý các đối tượng con thông qua kiểu cha, ví dụ `Phong[]` có thể chứa
`PhongDon`, `PhongDoi`, `PhongVIP`.

#### Đa hình (Polymorphism)

Một biến kiểu cha/interface có thể trỏ tới nhiều kiểu con khác nhau. Java sẽ
chọn phương thức phù hợp với object thật lúc chạy.

```java
IFileIO danhSach = new DanhSachPhong();
danhSach.docFile("data\\phong.txt");
```

Lời gọi `docFile` trên cùng một kiểu `IFileIO` nhưng cách xử lý thực tế phụ
thuộc object đang trỏ tới. Đây là đa hình runtime (dynamic dispatch).

#### Trừu tượng (Abstraction)

Trừu tượng là chỉ đưa ra phần cần dùng và che giấu chi tiết cài đặt. `IFileIO`
chỉ nói rằng lớp thực hiện nó phải có `docFile` và `ghiFile`; interface không
quy định bên trong phải dùng mảng, `ArrayList`, hay cách phân tích dòng file
nào.

### 3.3. Interface là gì?

Interface là một **hợp đồng hành vi**. Class dùng từ khóa `implements` để cam
kết cài đặt các phương thức trong interface.

[`IFileIO.java`](C:/Users/HELIOS%20300/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/GitHub/HotelManagement/src/interfaces/IFileIO.java):

```java
public interface IFileIO {
    void docFile(String path);
    void ghiFile(String path);
}
```

Các lớp danh sách đều có dạng:

```java
public class DanhSachPhong implements IFileIO {
    ...
}
```

Lợi ích:

- mọi danh sách có cùng cách gọi từ bên ngoài;
- `Main` không cần biết chi tiết từng lớp;
- có thể thay đổi cách lưu trữ bên trong mà không đổi code gọi;
- giảm phụ thuộc giữa `Main` và các lớp danh sách;
- dễ mở rộng thêm một danh sách mới.

Interface khác class cha ở chỗ: `IFileIO` mô tả khả năng “đọc/ghi file”, không
phải quan hệ “là một loại”. Một class cũng có thể `implements` nhiều interface.

## 4. Cấu trúc chung của một lớp `DanhSach`

### 4.1. Khóa định danh và chống trùng

Mỗi loại có khóa riêng:

| Danh sách | Khóa dùng để tìm/trùng |
|---|---|
| Phòng | `maPhong` |
| Nhân viên | `ma` |
| Đặt phòng | `maDatPhong` |
| Khách hàng | `ma` |
| Dịch vụ | `maDichVu` |
| Hóa đơn | `maHoaDon` |
| Tài khoản | `tenDangNhap` |

Mẫu kiểm tra khi thêm:

```java
if (doiTuong == null || tim(khoa) != null) {
    return false;
}
```

Ý nghĩa:

- không nhận object rỗng;
- không cho hai object có cùng khóa;
- trả `true` nếu thêm thành công, `false` nếu dữ liệu không hợp lệ.

So sánh dùng `equalsIgnoreCase`, vì vậy mã viết hoa hay viết thường vẫn được
coi là cùng một mã.

### 4.2. Tìm kiếm tuyến tính

`tim` duyệt từ đầu đến cuối:

```java
for (int i = 0; i < soLuong; i++) {
    if (ds[i].getMaPhong().equalsIgnoreCase(maPhong)) {
        return ds[i];
    }
}
return null;
```

Đây là **linear search**:

- trường hợp tốt nhất: tìm ngay phần tử đầu, khoảng `O(1)`;
- trường hợp xấu nhất: duyệt hết danh sách, `O(n)`;
- bộ nhớ phụ thêm: `O(1)`.

Project có quy mô nhỏ nên cách này đơn giản, dễ trình bày. Nếu dữ liệu rất lớn,
có thể dùng `HashMap<Ma, Object>` để tìm nhanh hơn, nhưng khi đó phải xử lý
thứ tự hiển thị và nhiều quy tắc khác.

### 4.3. Thêm và xóa trong mảng

`DanhSachPhong`, `DanhSachNhanVien`, `DanhSachDatPhong` dùng mảng object và
biến `soLuong`. Khi mảng đầy, code tạo mảng mới lớn gấp đôi rồi dùng
`System.arraycopy` để sao chép.

```java
if (soLuong == ds.length) {
    Phong[] mangMoi = new Phong[ds.length * 2];
    System.arraycopy(ds, 0, mangMoi, 0, soLuong);
    ds = mangMoi;
}
```

Khi xóa, các phần tử phía sau được dồn sang trái, ô cuối được gán `null`, rồi
giảm `soLuong`:

```java
System.arraycopy(ds, viTri + 1, ds, viTri, soLuong - viTri - 1);
ds[soLuong - 1] = null;
soLuong--;
```

Gán ô cuối bằng `null` là quan trọng vì giải phóng reference không còn dùng và
tránh giữ object thừa trong mảng.

Chi phí:

- thêm cuối mảng thường là `O(1)`;
- thêm đúng lúc phải nới mảng là `O(n)`;
- xóa cần dồn phần tử là `O(n)`.

### 4.4. `ArrayList` khác mảng tự quản lý

`DanhSachKhachHang`, `DanhSachDichVu`, `DanhSachHoaDon`,
`DanhSachTaiKhoan` dùng:

```java
private ArrayList<KhachHang> ds;
```

`ArrayList` tự quản lý việc tăng kích thước:

- thêm: `ds.add(object)`;
- xóa: `ds.remove(object)`;
- thay thế: `ds.set(index, object)`;
- lấy số lượng: `ds.size()`.

Về bản chất, `ArrayList` vẫn dựa trên mảng bên trong, nhưng lập trình viên
không phải tự tạo mảng mới và tự cập nhật kích thước. Mảng thủ công giúp thể
hiện rõ kiến thức mảng đối tượng; `ArrayList` giảm code và dễ bảo trì hơn.

## 5. Giải thích từng lớp SV3

### 5.1. `DanhSachPhong`

File: [`DanhSachPhong.java`](C:/Users/HELIOS%20300/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/GitHub/HotelManagement/src/model/DanhSachPhong.java)

- lưu `Phong[]`;
- dùng `maPhong` làm khóa;
- hỗ trợ thêm, xóa, tìm, sửa, hiển thị;
- `docFile` đọc tiền tố `DON`, `DOI`, `VIP`;
- `ghiFile` ghi lại loại phòng và thuộc tính đặc thù.

Khi đọc `DON`, code tạo `PhongDon`; khi đọc `DOI`, tạo `PhongDoi`; còn lại tạo
`PhongVIP`. Đây là khôi phục đúng lớp con từ dữ liệu text.

Ví dụ một dòng:

```text
DON|P101|Phong don 101|1|Trong|300000|0
```

### 5.2. `DanhSachNhanVien`

File: [`DanhSachNhanVien.java`](C:/Users/HELIOS%20300/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/GitHub/HotelManagement/src/model/DanhSachNhanVien.java)

- lưu `NhanVien[]`;
- dùng mã nhân viên;
- đọc tiền tố `LETAN`, `KETOAN`, `BUONGPHONG`, `QUANLY`;
- tạo object lớp con tương ứng trong `taoNhanVien`;
- ghi lại phần chung và một thuộc tính riêng như phụ cấp, thưởng hoặc hệ số.

Đây là ví dụ rõ về đa hình: mảng khai báo là `NhanVien[]`, nhưng phần tử thực
tế có thể là `LeTan`, `KeToan`, `BuongPhong`, `QuanLy`.

### 5.3. `DanhSachDatPhong`

File: [`DanhSachDatPhong.java`](C:/Users/HELIOS%20300/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/GitHub/HotelManagement/src/model/DanhSachDatPhong.java)

- lưu `DatPhong[]`;
- dùng `maDatPhong`;
- đọc bảy trường: mã đặt phòng, mã khách, mã phòng, ngày nhận, ngày trả,
  số đêm, trạng thái;
- dữ liệu số đêm được đổi từ chuỗi bằng `Integer.parseInt`.

Lớp này không tự quyết định phòng có trống hay không; kiểm tra nghiệp vụ đặt
phòng nằm ở tầng nghiệp vụ/`Main`. Vai trò của danh sách là lưu và quản lý các
object `DatPhong`.

### 5.4. `DanhSachKhachHang`

File: [`DanhSachKhachHang.java`](C:/Users/HELIOS%20300/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/GitHub/HotelManagement/src/model/DanhSachKhachHang.java)

- dùng `ArrayList<KhachHang>`;
- khóa là mã khách;
- file có tám trường;
- `namSinh` và `diemTichLuy` được parse thành `int`.

`ArrayList` tự dồn phần tử sau khi gọi `remove`, nên lớp không cần tự viết logic
dịch mảng.

### 5.5. `DanhSachDichVu`

File: [`DanhSachDichVu.java`](C:/Users/HELIOS%20300/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/GitHub/HotelManagement/src/model/DanhSachDichVu.java)

- dùng `ArrayList<DichVu>`;
- khóa là mã dịch vụ;
- nhận diện `DichVuAn`, `DichVuGiatLa`, `DichVuSpa` bằng tiền tố;
- dùng `instanceof` khi ghi để lấy thuộc tính riêng của từng loại dịch vụ.

Ví dụ, biến `DichVu d` có thể trỏ tới object `DichVuAn`. Câu lệnh
`d.getDonGia()` gọi phần chung; để lấy `getLoaiMon()` thì code phải kiểm tra và
ép kiểu về `DichVuAn`.

### 5.6. `DanhSachHoaDon`

File: [`DanhSachHoaDon.java`](C:/Users/HELIOS%20300/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/GitHub/HotelManagement/src/model/DanhSachHoaDon.java)

- dùng `ArrayList<HoaDon>`;
- khóa là mã hóa đơn;
- có thêm nghiệp vụ `tinhDoanhThu`;
- chỉ cộng `tinhTien()` của hóa đơn có trạng thái `Da thanh toan`.

`tinhDoanhThu` thể hiện nguyên tắc: doanh thu thực tế không nên cộng hóa đơn
chưa thanh toán.

### 5.7. `DanhSachTaiKhoan`

File: [`DanhSachTaiKhoan.java`](C:/Users/HELIOS%20300/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/GitHub/HotelManagement/src/model/DanhSachTaiKhoan.java)

- dùng `ArrayList<TaiKhoan>`;
- khóa là tên đăng nhập;
- `dangNhap` tìm tài khoản rồi gọi `taiKhoan.dangNhap(user, pass)`;
- đọc/ghi trạng thái hoạt động bằng `Boolean.parseBoolean`.

Lớp danh sách chỉ điều phối việc tìm tài khoản; việc kiểm tra mật khẩu thuộc
trách nhiệm của `TaiKhoan`.

## 6. File I/O trong project

### 6.1. Đọc file

Mẫu đọc:

```java
BufferedReader br = new BufferedReader(new FileReader(path));
String dong;
while ((dong = br.readLine()) != null) {
    String[] p = dong.split("\\|");
    // kiểm tra p rồi tạo object
}
br.close();
```

Giải thích:

1. `FileReader` mở file ký tự.
2. `BufferedReader` đọc hiệu quả từng dòng.
3. `readLine()` lấy từng dòng, trả `null` khi hết file.
4. `split("\\|")` tách dòng theo dấu `|`.
5. các chuỗi được parse sang `int`, `double`, `boolean` khi cần.
6. tạo object rồi gọi `them`, nhờ đó vẫn áp dụng kiểm tra trùng khóa.

Tại sao viết `\\|`? `split` nhận regular expression; dấu `|` trong regex là
toán tử OR nên phải escape thành `\|`, và trong Java dấu `\` lại phải viết
thành `\\`.

### 6.2. Ghi file

Mẫu ghi:

```java
PrintWriter pw = new PrintWriter(new FileWriter(path));
for (...) {
    pw.println(cacTruongCachNhauBangDauPhay);
}
pw.close();
```

`FileWriter` ghi ký tự; `PrintWriter` cung cấp `println` tiện dụng. File được
ghi lại theo từng dòng, một dòng tương ứng một object. Dấu `|` là định dạng
đơn giản kiểu CSV tự thiết kế.

### 6.3. Vì sao cần tiền tố loại?

Nếu file chỉ chứa các thuộc tính chung thì khi đọc chương trình không biết
object là `PhongDon` hay `PhongVIP`. Vì vậy file có trường đầu tiên:

```text
DON|...
DOI|...
VIP|...
```

Đây là một dạng **type discriminator**. Chương trình đọc discriminator, chọn
constructor lớp con, rồi khôi phục đúng object.

### 6.4. Xử lý lỗi

Code dùng `try/catch` để tránh chương trình dừng khi file không tồn tại hoặc
dòng có dữ liệu sai. Dòng sai thường được bỏ qua; lỗi mở/ghi file in thông báo
cho người dùng.

Khi báo cáo nên nói rõ: đây là cách xử lý lỗi đơn giản phù hợp đồ án console.
Trong hệ thống thực tế nên bắt loại exception cụ thể, dùng `try-with-resources`
để tự đóng file, kiểm tra encoding và ghi log chi tiết hơn.

## 7. Các câu hỏi thầy có thể hỏi và câu trả lời mẫu

### Câu 1: Vì sao cần `IFileIO`?

Vì cả bảy lớp đều có nhu cầu đọc/ghi file. Interface tạo một hợp đồng chung
gồm `docFile` và `ghiFile`, giúp `Main` làm việc với kiểu `IFileIO` thay vì
phụ thuộc vào từng class cụ thể. Đây là trừu tượng hóa và đa hình.

### Câu 2: Vì sao `IFileIO` không có code xử lý file?

Interface chỉ quy định class phải làm được gì, không quy định làm như thế nào.
Mỗi danh sách có số trường và loại object khác nhau nên phần cài đặt phải nằm
ở từng lớp.

### Câu 3: Tại sao phải kiểm tra `null` khi thêm?

Object `null` không có dữ liệu để gọi getter, có thể gây
`NullPointerException`. Từ chối ngay giúp danh sách chỉ chứa object hợp lệ.

### Câu 4: Tại sao phải chống trùng mã?

Mã là định danh của object. Nếu trùng, `tim`, `sua`, `xoa` không biết chính xác
đang thao tác với bản ghi nào và dữ liệu nghiệp vụ bị sai.

### Câu 5: Vì sao dùng `equalsIgnoreCase`, không dùng `==`?

`==` so sánh reference của String, không đảm bảo so sánh nội dung. `equals` so
sánh nội dung; `equalsIgnoreCase` còn bỏ qua khác biệt chữ hoa/chữ thường.

### Câu 6: Vì sao có `timViTri` riêng?

`tim` trả object, còn sửa/xóa cần index. `timViTri` trả vị trí và `-1` nếu
không tìm thấy, giúp thao tác dồn mảng hoặc `ArrayList.set` rõ ràng.

### Câu 7: Tại sao mảng phải có `soLuong`?

Mảng có `length` là sức chứa, không phải số phần tử đang dùng. `soLuong` cho
biết chỉ số nào đang chứa dữ liệu; các ô từ `soLuong` đến cuối là sức chứa
trống.

### Câu 8: Vì sao khi mảng đầy lại nhân đôi?

Nhân đôi giảm số lần cấp phát và sao chép. Chi phí một lần nới là `O(n)`,
nhưng chi phí trung bình của thao tác thêm vẫn gần `O(1)` (amortized).

### Câu 9: `ArrayList` có phải mảng không?

`ArrayList` là cấu trúc danh sách động được cài đặt dựa trên mảng bên trong.
Nó tự tăng sức chứa và cung cấp API tiện lợi, còn mảng trong ba lớp SV3 phải
tự quản lý việc nới và xóa.

### Câu 10: Vì sao xóa phải gán ô cuối bằng `null`?

Sau khi dồn, phần tử cuối bị dư. Gán `null` loại bỏ reference cũ, tránh giữ
object không còn thuộc danh sách và giúp garbage collector có thể thu hồi.

### Câu 11: Đa hình nằm ở đâu trong phần SV3?

`Main.docNeuCo` nhận tham số `IFileIO`, nhưng có thể truyền bất kỳ lớp
`DanhSach...` nào. Ngoài ra `Phong[]`, `NhanVien[]` và `ArrayList<DichVu>`
lưu object lớp con thông qua kiểu cha.

### Câu 12: Vì sao dùng `instanceof` khi ghi file?

Các lớp con có thuộc tính riêng. Ví dụ `PhongDon` có phụ thu, còn `PhongDoi`
có số giường. Kiểu cha không cung cấp getter cho thuộc tính riêng, nên code
kiểm tra kiểu thật rồi đọc thuộc tính tương ứng.

### Câu 13: Nếu file có một dòng sai thì sao?

Tùy lớp, dòng có số trường không đủ sẽ bị bỏ qua; lỗi parse số được bắt bằng
`try/catch`. Mục tiêu là một dòng hỏng không làm mất toàn bộ khả năng đọc file.

### Câu 14: Đọc file có tự động chống trùng không?

Có. Sau khi tạo object, code gọi `them`, mà `them` gọi `tim` để kiểm tra khóa.
Vì vậy bản ghi trùng sẽ không được thêm lần nữa.

### Câu 15: Độ phức tạp của `tim`, `them`, `xoa` là gì?

Vì tìm tuyến tính nên `tim` là `O(n)`. `them` thường là `O(n)` do phải tìm
trùng; nếu mảng đầy còn phải sao chép. `xoa` là `O(n)` do tìm và dồn phần tử.

### Câu 16: Tại sao không dùng database?

Project là đồ án OOP cơ bản nên file text giúp tập trung vào class, interface,
CRUD và File I/O, không cần cài database. Đổi lại file text không phù hợp dữ
liệu lớn, truy cập đồng thời hoặc yêu cầu giao dịch.

### Câu 17: `docFile` có phải constructor không?

Không. Constructor dùng để khởi tạo object khi gọi `new`; `docFile` là phương
thức bình thường, được gọi sau đó để nạp dữ liệu từ file vào object danh sách.

### Câu 18: Tại sao `docFile` và `ghiFile` trả `void`?

Thiết kế hiện tại chỉ yêu cầu thực hiện thao tác và in thông báo khi lỗi, không
trả kết quả. Nếu nâng cấp, có thể trả `boolean` hoặc ném exception để caller
biết chính xác thành công/thất bại.

### Câu 19: Có thể tạo `new IFileIO()` không?

Không. Interface không thể tạo object trực tiếp vì chỉ là hợp đồng. Phải tạo
class cụ thể như `new DanhSachPhong()`; class đó phải cài đủ phương thức.

### Câu 20: `private` có ý nghĩa gì?

`private` giới hạn truy cập trong chính class khai báo. Đây là công cụ quan
trọng của đóng gói, ngăn class khác sửa trực tiếp cấu trúc bên trong.

## 8. Những điểm nên nói trung thực nếu thầy hỏi sâu

1. Định dạng `|` đơn giản; nếu dữ liệu thực tế chứa ký tự `|` trong tên hoặc
   địa chỉ thì cần escaping hoặc dùng JSON/CSV chuẩn.
2. Các lớp hiện tại dùng `try/catch (Exception)` khá rộng; hệ thống lớn nên
   bắt exception cụ thể và dùng `try-with-resources`.
3. Một số parser cần kiểm tra số trường đúng với số index sẽ truy cập. Ví dụ
   lớp dịch vụ dùng các trường đến `p[5]`, lớp phòng dùng đến `p[6]`; kiểm tra
   độ dài nên tương ứng ít nhất 6 và 7 trường. Đây là điểm có thể cải thiện,
   không làm thay đổi ý tưởng OOP của SV3.
4. File text không mã hóa an toàn; đặc biệt tài khoản nên dùng hash mật khẩu
   chuẩn trong ứng dụng thật.
5. `tim` là tìm tuyến tính, phù hợp dữ liệu nhỏ nhưng có thể chậm nếu dữ liệu
   rất lớn.

## 9. Bài nói mẫu 60 giây

> Em phụ trách bảy lớp `DanhSach` và interface `IFileIO`. Các lớp `DanhSach`
> đóng vai trò quản lý tập hợp object, cung cấp CRUD, hiển thị và lưu trữ dữ
> liệu. Ba lớp dùng mảng object tự quản lý kích thước bằng `System.arraycopy`;
> bốn lớp dùng `ArrayList` để tự động tăng kích thước. Mỗi danh sách kiểm tra
> object null và khóa trùng trước khi thêm, tìm bằng duyệt tuyến tính. Interface
> `IFileIO` định nghĩa hai phương thức `docFile` và `ghiFile`, nhờ đó `Main` có
> thể gọi các danh sách thông qua một kiểu chung, thể hiện trừu tượng hóa và đa
> hình. File text dùng dấu `|`; các tiền tố như `DON`, `DOI`, `VIP` giúp khôi
> phục đúng lớp con khi đọc. Khi chương trình thoát, toàn bộ danh sách được
> ghi lại để bảo toàn dữ liệu.

## 10. Cách tự demo khi báo cáo

1. Mở `IFileIO.java`, giải thích hợp đồng hai phương thức.
2. Mở `DanhSachPhong.them`, chỉ ra kiểm tra `null`, trùng mã và nới mảng.
3. Mở `DanhSachPhong.xoa`, giải thích dồn mảng và gán `null`.
4. Mở `DanhSachDichVu.taoDichVu` hoặc `DanhSachNhanVien.taoNhanVien`,
   giải thích discriminator và tạo lớp con.
5. Mở `Main.docNeuCo`, giải thích tham số kiểu `IFileIO` là đa hình.
6. Mở một file trong thư mục `data`, đối chiếu thứ tự trường với `docFile`.
7. Thêm một object mới, thoát chương trình, mở file để chứng minh `ghiFile`.

