/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.SanPham;
import Model.ThanhToan;
import Model.TonKho;
import Service.HoaDonDAL;
import Service.SanPhamDAL;
import Service.ThanhToanDAL;
import Service.TonKhoDAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class TonKhoJPanel extends javax.swing.JPanel {

    DefaultTableModel tblModel;
    ArrayList<TonKho> spList;
    private SimpleDateFormat timeFormat;

    /**
     * Creates new form TonKhoJPanel
     */
    public TonKhoJPanel() {
        initComponents();
        initClock();
        loadSanPhamList();
        updateTonKho();
        initTable();
        fillToTable();
    }

    public void initClock() {
        timeFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timer timer = new Timer(86400000, e -> updateTime());
        timer.start();
        updateTime();
    }

    public void updateTime() {
        String currentTime = timeFormat.format(new Date());
        txtTgianNhapHang.setText(currentTime);
    }

    private List<String> availableSanPhamList = new ArrayList<>();

    private void loadSanPhamList() {
         try {
             availableSanPhamList = SanPhamDAL.getAllTenSanPham(); 

             if (availableSanPhamList == null || availableSanPhamList.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong danh sách sản phẩm!");
                 return;
             }
         } catch (SQLException | ClassNotFoundException ex) {
             ex.printStackTrace();
             JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách sản phẩm!");
         }
     }

     private boolean isSanPhamExists(String tenSP) {
         return availableSanPhamList.contains(tenSP);
     }


//    private List<String> layDanhSachMaSP() {
//        List<String> danhSachMaSP = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=SOF102_SD1808_SmileyBall";
//            String user = "sa";
//            String password = "123";
//            conn = DriverManager.getConnection(url, user, password);
//            String sql = "SELECT MaSP FROM SANPHAM";
//            stmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maSP = rs.getString("MaSP");
//
//                danhSachMaSP.add(maSP);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return danhSachMaSP;
//    }

//    private void loadDataToTable() {
//        tblModel.setRowCount(0);
//
//        List<SanPham> danhSachSP = layDanhSachMaSP();
//        for (SanPham sp : danhSachSP) {
//            tblModel.addRow(new Object[]{
//                sp.getMaSP(),
//                sp.getTenSP(),
//                sp.getLoaiSP(),
//                sp.getHang(),
//                sp.getPhanLoai(),
//                sp.getSize(),
//                sp.getChiPhi(),
//                sp.getTinhTrang(),
//                sp.getMotaChiTiet(),
//            });
//        }
//    }
    private void initTable() {
        tblModel = new DefaultTableModel();
        String[] cols = new String[]{"Mã tồn kho", "Tên sản phẩm", "Giá nhập", "Giá bán", "Số lượng", "Tồn kho", "Tổng chi phí nhập hàng", "Ngày nhập","Ghi chú"};
        tblModel.setColumnIdentifiers(cols);
        tblBang.setModel(tblModel);
    }

    private void fillToTable() {
        try {
            List<TonKho> list = TonKhoDAL.findAll();
            tblModel.setNumRows(0);
            for (TonKho tonkho : list) {
                tblModel.addRow(new Object[]{tonkho.getMaTK(), tonkho.getTenSP(), tonkho.getGiaN(),
                    tonkho.getGiaB(), tonkho.getSoLuong() , tonkho.getTonKho(),
                    tonkho.getTongCPN(), tonkho.getTg(), tonkho.getGhiChu()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tinhCP() {
        try {
            int sl = Integer.parseInt(txtSoLuong.getText());
            Double dg = Double.valueOf(txtGiaNhap.getText());
            Double cp = sl * dg;
            txtChiPhiNhapHang.setText(String.valueOf(cp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     private void tinhGiaBan() {
        try {
            
            Double gn = Double.valueOf(txtGiaNhap.getText());
            Double gb = (gn+(gn*0.3));
            txtGiaBan.setText(String.valueOf(gb));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

//    private boolean checkTrung(String MaSP) {
//        List<String> danhSachMaSP = layDanhSachMaSP();
//        return danhSachMaSP.contains(MaSP);
//    }

    private boolean checkGiaNhap() {

        String donGiaText = txtGiaNhap.getText().trim();
        if (!donGiaText.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải nhập số nguyên dương!");
            return false;
        }

        return true;
    }
    private boolean checkGiaBan() {

        String donGiaText = txtGiaBan.getText().trim();
        if (!donGiaText.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Giá bán phải nhập số nguyên dương!");
            return false;
        }

        return true;
    }
    private boolean checkSoLuong() {

        String soLuongText = txtSoLuong.getText().trim();

        if (!soLuongText.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số lượng phải nhập số nguyên dương!");
            return false;
        }
        return true;
    }

    private boolean checkLuu() {
//        if (txtMaTK.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Mã tồn kho không được để trống!");
//            txtMaTK.requestFocus();
//            return false;
//        }
//        if (checkTrung(txtSP.getText().trim())) {
//            JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại! Vui lòng nhập lại!");
//            txtSP.requestFocus();
//            return false;
//        }

        if (txtTenSanPham.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
            txtTenSanPham.requestFocus();
            return false;
        }


//        if (txtLoaiSanPham.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Loại sản phẩm không được để trống!");
//            txtHang.requestFocus();
//            return false;
//        }
//        if (cboPhanLoai.getSelectedItem().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Phân loại sản phẩm không được để trống!");
//            txtHang.requestFocus();
//            return false;
//        }
        if (txtGiaNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá nhập sản phẩm không được để trống!");
            txtGiaNhap.requestFocus();
            return false;

        }
        if (!checkGiaNhap()) {
            txtGiaNhap.requestFocus();
            return false;
        }
        if (txtGiaBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá bán sản phẩm không được để trống!");
            txtGiaBan.requestFocus();
            return false;

        }
        if (!checkGiaBan()) {
            txtGiaBan.requestFocus();
            return false;
        }
        if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được để trống!");
            txtSoLuong.requestFocus();
            return false;

        }
        if (!checkSoLuong()) {
            txtSoLuong.requestFocus();
            return false;
        }
        

//        try {
////            if (txtTgianNhapHang.getText().trim().isEmpty()) {
////            JOptionPane.showMessageDialog(this, "Thời gian nhập hàng không được để trống!");
////            return false;
////        }
////             sdf.parse(txtTgianNhapHang.getText());
////          
////        } catch (ParseException e) {
////            JOptionPane.showMessageDialog(this,"Thời gian phải nhập đúng thứ tự dd-MM-yyyy");
////            return false;
////        };
//        if (txtaMoTa.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Mô tả sản phẩm không được để trống!");
//            txtaMoTa.requestFocus();
//            return false;
//        }
        return true;
    }

    private boolean checkCapNhat() {
        if (txtMaTK.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã tồn kho không được để trống!");
            txtMaTK.requestFocus();
            return false;
        }
//        if (checkTrung(txtSP.getText().trim())) {
//            JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại! Vui lòng nhập lại!");
//            txtSP.requestFocus();
//            return false;
//        }

        if (txtTenSanPham.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
            txtTenSanPham.requestFocus();
            return false;
        }


//        if (txtLoaiSanPham.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Loại sản phẩm không được để trống!");
//            txtHang.requestFocus();
//            return false;
//        }
//        if (cboPhanLoai.getSelectedItem().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Phân loại sản phẩm không được để trống!");
//            txtHang.requestFocus();
//            return false;
//        }
        if (txtGiaNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá nhập sản phẩm không được để trống!");
            txtGiaNhap.requestFocus();
            return false;

        }
        if (!checkGiaNhap()) {
            txtGiaNhap.requestFocus();
            return false;
        }
        if (txtGiaBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá bán sản phẩm không được để trống!");
            txtGiaBan.requestFocus();
            return false;

        }
        if (!checkGiaBan()) {
            txtGiaBan.requestFocus();
            return false;
        }
        if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được để trống!");
            txtSoLuong.requestFocus();
            return false;

        }
        if (!checkSoLuong()) {
            txtSoLuong.requestFocus();
            return false;
        }

//        if (txtaMoTa.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Mô tả sản phẩm không được để trống!");
//            txtaMoTa.requestFocus();
//            return false;
//        }
        return true;
    }

    public boolean checkXoa() {
        if (txtMaTK.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã tồn kho không được để trống!");
            txtMaTK.requestFocus();
            return false;
        }
        return true;
    }

    public boolean checkTimKiem() {
        if (txtTim.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!");
            txtTim.requestFocus();
            return false;
        }
        return true;
    }

private void updateTonKho() {
    String tenSP = txtTenSanPham.getText();
    if (tenSP == null || tenSP.isEmpty()) {
        return;
    }

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SOF102_SD1808_SmileyBall";
        String user = "sa";
        String password = "123";
        conn = DriverManager.getConnection(url, user, password);

        // Step 1: Get the sum of SoLuong from the TONKHO table
        String sqlTonKho = "SELECT SUM(SoLuong) FROM TONKHO WHERE TenSP = ?";
        stmt = conn.prepareStatement(sqlTonKho);
        stmt.setString(1, tenSP);
        rs = stmt.executeQuery();

        int databaseSum = 0;
        if (rs.next()) {
            databaseSum = rs.getInt(1);
        }

        // Step 2: Get the sum of SoLuong from the ThanhToan table
        String sqlThanhToan = "SELECT SUM(SoLuong) FROM THANHTOAN WHERE SPDaMua = ?";
        stmt = conn.prepareStatement(sqlThanhToan);
        stmt.setString(1, tenSP);
        rs = stmt.executeQuery();

        int thanhToanSum = 0;
        if (rs.next()) {
            thanhToanSum = rs.getInt(1);
        }

        // Step 3: Subtract the ThanhToan sum from the TONKHO sum
        int currentSoLuong = 0;
        try {
            currentSoLuong = Integer.parseInt(txtSoLuong.getText());
        } catch (NumberFormatException e) {
            currentSoLuong = 0;
        }

        int totalTonKho = databaseSum - thanhToanSum + currentSoLuong;
        txtTonKho.setText(String.valueOf(totalTonKho));

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaMoTa = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtChiPhiNhapHang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTonKho = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTgianNhapHang = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        cboTimKiem = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtMaTK = new javax.swing.JTextField();

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jLabel6.setText("Tên sản phẩm");

        jLabel10.setText("Ghi chú");

        txtaMoTa.setColumns(20);
        txtaMoTa.setRows(5);
        jScrollPane1.setViewportView(txtaMoTa);

        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Giá nhập", "Giá bán", "Số lượng", "Tồn kho", "Tổng chi phí nhập", "Ngày nhập", "Mô tả"
            }
        ));
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblBangMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tblBang);

        jLabel11.setText("Giá nhập");

        txtGiaNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiaNhapKeyReleased(evt);
            }
        });

        jLabel12.setText("Số lượng");

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });

        jLabel13.setText("Tổng chi phí nhập hàng");

        txtChiPhiNhapHang.setEnabled(false);
        txtChiPhiNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChiPhiNhapHangActionPerformed(evt);
            }
        });
        txtChiPhiNhapHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChiPhiNhapHangKeyReleased(evt);
            }
        });

        jLabel5.setText("Tồn kho");

        txtTonKho.setEnabled(false);

        jLabel9.setText("Thời gian nhập hàng");

        txtTgianNhapHang.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 153));
        jLabel14.setText("QUẢN LÝ TỒN KHO");

        jLabel2.setText("Giá bán");

        txtGiaBan.setEnabled(false);
        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });
        txtGiaBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiaBanKeyReleased(evt);
            }
        });

        cboTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã Tồn Kho", "Tên Sản Phẩm", "Ngày Nhập" }));

        jLabel3.setText("Mã tồn kho");

        txtMaTK.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTgianNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtTim)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cboTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnTim))
                                        .addComponent(jScrollPane1)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(btnThem)
                                            .addGap(56, 56, 56)
                                            .addComponent(btnSua)
                                            .addGap(63, 63, 63)
                                            .addComponent(btnXoa)
                                            .addGap(62, 62, 62)
                                            .addComponent(btnMoi))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING))
                                                        .addComponent(txtChiPhiNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(58, 58, 58)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel5))
                                                    .addGap(32, 32, 32))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel6)))
                                            .addGap(8, 8, 8)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim)
                    .addComponent(cboTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtChiPhiNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTgianNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
         String luaChon = (String) cboTimKiem.getSelectedItem();
        String tuKhoa = txtTim.getText().trim();
        
        if (!checkTimKiem()) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn tìm kiếm thông tin sản phẩm này không?");
        if (chon == JOptionPane.YES_OPTION) {
            try {
                List<TonKho> list = new ArrayList<>();
                if (luaChon.equals("Mã Tồn Kho")) {
                    if (!tuKhoa.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Mã tồn  phải là số!");
                    return;
                    }
                    list=TonKhoDAL.findMaTK(Integer.parseInt(tuKhoa));
                }else if (luaChon.equals("Tên Sản Phẩm")) {
//                if (!tuKhoa.matches("\\d+")) {
//                    JOptionPane.showMessageDialog(this, "Số điện thoại phải là số nguyên!");
//                    return;
//                }
               list=TonKhoDAL.findTenSP(tuKhoa);
            } else if (luaChon.equals("Ngày Nhập")) {
                list = TonKhoDAL.findThoiGian(tuKhoa);
            }
                hienThiKetQuaTimKiem(list);
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (!isSanPhamExists(txtTenSanPham.getText().trim())) {
         JOptionPane.showMessageDialog(this, "Tên sản phẩm không tồn tại trong danh mục sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
         return; // Stop the add operation
     }
     if (!checkLuu()) {
         return;
     }
     int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn lưu thông tin sản phẩm này không?");
     if (chon == JOptionPane.YES_OPTION) {
         try {
             TonKho tk = new TonKho();
             tk.setMaTK(txtMaTK.getText());
             tk.setTenSP(txtTenSanPham.getText());
             tk.setGiaN(Double.parseDouble(txtGiaNhap.getText()));
             tk.setGiaB(Double.parseDouble(txtGiaBan.getText()));
             tk.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
             tk.setTonKho(Integer.parseInt(txtTonKho.getText()));
             tk.setTongCPN(Double.parseDouble(txtChiPhiNhapHang.getText()));
             tk.setTg(txtTgianNhapHang.getText());
             tk.setGhiChu(txtaMoTa.getText());
             TonKhoDAL save = new TonKhoDAL();
             save.insertTK(tk);

             JOptionPane.showMessageDialog(this, "Lưu thông tin sản phẩm thành công!");
         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
             return;
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Lưu thông tin sản phẩm thất bại!");
             e.printStackTrace();
         }
     }
     fillToTable();
     updateTonKho();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (!isSanPhamExists(txtTenSanPham.getText().trim())) {
         JOptionPane.showMessageDialog(this, "Tên sản phẩm không tồn tại trong danh mục sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
         return; // Stop the update operation
     }
     if (!checkCapNhat()) {
         return;
     }
     int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn cập nhật thông tin sản phẩm này không?");
     if (chon == JOptionPane.YES_OPTION) {
         try {
             TonKho tk = new TonKho();
             tk.setMaTK(txtMaTK.getText());
             tk.setTenSP(txtTenSanPham.getText());
             tk.setGiaN(Double.parseDouble(txtGiaNhap.getText()));
             tk.setGiaB(Double.parseDouble(txtGiaBan.getText()));
             tk.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
             tk.setTonKho(Integer.parseInt(txtTonKho.getText()));
             tk.setTongCPN(Double.parseDouble(txtChiPhiNhapHang.getText()));
             tk.setTg(txtTgianNhapHang.getText());
             tk.setGhiChu(txtaMoTa.getText());
             TonKhoDAL update = new TonKhoDAL();
             update.updateTK(tk);
             fillToTable();
             JOptionPane.showMessageDialog(this, "Cập nhật thông tin sản phẩm thành công!");
         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
             return;
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Cập nhật thông tin sản phẩm thất bại!");
             e.printStackTrace();
         }
     }
     updateTonKho();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (!checkXoa()) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa thông tin sản phẩm này không?");
        if (chon == JOptionPane.YES_OPTION) {
            try {
                TonKhoDAL delete = new TonKhoDAL();
                boolean ketQuaXoa = delete.deleteTK(txtMaTK.getText());

                if (ketQuaXoa) {
                    fillToTable();
                    JOptionPane.showMessageDialog(this, "Xóa thông tin sản phẩm thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm sản phẩm để xóa!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xóa thông tin sản phẩm thất bại!");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        txtMaTK.setText("");
        txtTenSanPham.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtSoLuong.setText("");
        txtTonKho.setText("");
        txtChiPhiNhapHang.setText("");
        cboTimKiem.setSelectedIndex(0);
        txtaMoTa.setText("");
        txtTim.setText("");
        //txtTgianNhapHang.setText("");
        fillToTable();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        // TODO add your handling code here:
        int index = tblBang.getSelectedRow();
        if (index >= 0) {
            String ma = (String) tblBang.getValueAt(index, 0);
            TonKho tk = (TonKho) TonKhoDAL.findByID(ma);
            txtMaTK.setText(String.valueOf(tk.getMaTK()));
            txtTenSanPham.setText(tk.getTenSP());
            txtGiaNhap.setText(String.valueOf(tk.getGiaN()));
            txtGiaBan.setText(String.valueOf(tk.getGiaB()));
            //txtSoLuong.setText(String.valueOf(tk.getSoLuong()));
            txtChiPhiNhapHang.setText(String.valueOf(tk.getTongCPN()));
            txtTgianNhapHang.setText(String.valueOf(tk.getTg()));
            txtaMoTa.setText(tk.getGhiChu());
            updateTonKho();
        }
    }//GEN-LAST:event_tblBangMouseClicked

    private void tblBangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBangMouseEntered

    private void txtGiaNhapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaNhapKeyReleased
        // TODO add your handling code here:
        tinhCP();
        tinhGiaBan();
    }//GEN-LAST:event_txtGiaNhapKeyReleased

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        // TODO add your handling code here:
        tinhCP();
        updateTonKho();
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void txtChiPhiNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChiPhiNhapHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChiPhiNhapHangActionPerformed

    private void txtChiPhiNhapHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChiPhiNhapHangKeyReleased
        // TODO add your handling code here:
        tinhCP();
    }//GEN-LAST:event_txtChiPhiNhapHangKeyReleased

    private void txtGiaBanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaBanKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanKeyReleased

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed
private void hienThiKetQuaTimKiem(List<TonKho> danhSach) {
        DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        for (TonKho tt : danhSach) {
            model.addRow(new Object[]{
                tt.getMaTK(),
                //tt.getMaKH(),
                tt.getTenSP(),
                tt.getGiaN(),
                tt.getGiaB(),
                tt.getSoLuong(),
                tt.getTonKho(),
                tt.getTongCPN(),
                tt.getTg(),
                tt.getGhiChu(),
               });
        }}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtChiPhiNhapHang;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaTK;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTgianNhapHang;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTonKho;
    private javax.swing.JTextArea txtaMoTa;
    // End of variables declaration//GEN-END:variables
}
