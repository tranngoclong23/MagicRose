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
import javax.swing.DefaultListModel;
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
    private SanPhamJPane spp;
    public DefaultListModel<String> modelSP = new DefaultListModel<>();

    /**
     * Creates new form TonKhoJPanel
     */
    public TonKhoJPanel(SanPhamJPane spp) {
        initComponents();
        this.spp = spp;
        
        
        
        ListSP.setModel(modelSP);
//        initClock();
        loadSanPhamList();
        updateTonKho();
        initTable();
        fillToTable();
    }

//    public void initClock() {
//        timeFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Timer timer = new Timer(86400000, e -> updateTime());
//        timer.start();
//        updateTime();
//    }
//    public void updateTime() {
//        String currentTime = timeFormat.format(new Date());
//        txtTgianNhapHang.setText(currentTime);
//    }
//    private List<String> availableSanPhamList = new ArrayList<>();
    public void loadSanPhamList() {
        try {
//            availableSanPhamList = SanPhamDAL.getAllTenSanPham();
            List<String> sanPhamList = TonKhoDAL.getSanPhamList();

            if (sanPhamList == null || sanPhamList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong danh sách sản phẩm!");
                    return;
            }
            modelSP.clear();
            for (String tenSP : sanPhamList) {
                if (!modelSP.contains(tenSP)) {
                    modelSP.addElement(tenSP);
                }
            }
//            ListSP.setListData(sanPhamList.toArray(new String[0]));
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách sản phẩm!");
        }
    }

//    private boolean isSanPhamExists(String tenSP) {
//        return availableSanPhamList.contains(tenSP);
//    }
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
        String[] cols = new String[]{"Mã tồn kho", "Tên sản phẩm", "Tồn kho", "Ghi chú"};
        tblModel.setColumnIdentifiers(cols);
        tblBang.setModel(tblModel);
    }

    private void fillToTable() {
        try {
            List<TonKho> list = TonKhoDAL.findAll();
            tblModel.setNumRows(0);
            for (TonKho tonkho : list) {
                tblModel.addRow(new Object[]{tonkho.getMaTK(), tonkho.getTenSP(),
                    tonkho.getTonKho(),
                    tonkho.getGhiChu()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void tinhCP() {
//        try {
//            int sl = Integer.parseInt(txtSoLuong.getText());
//            Double dg = Double.valueOf(txtGiaNhap.getText());
//            Double cp = sl * dg;
//            txtChiPhiNhapHang.setText(String.valueOf(cp));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//     private void tinhGiaBan() {
//        try {
//            
//            Double gn = Double.valueOf(txtGiaNhap.getText());
//            Double gb = (gn+(gn*0.3));
//            txtGiaBan.setText(String.valueOf(gb));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private boolean checkTrung(String MaSP) {
//        List<String> danhSachMaSP = layDanhSachMaSP();
//        return danhSachMaSP.contains(MaSP);
//    }
//
//    private boolean checkGiaNhap() {
//
//        String donGiaText = txtGiaNhap.getText().trim();
//        if (!donGiaText.matches("\\d+(\\.\\d+)?")) {
//            JOptionPane.showMessageDialog(this, "Giá nhập phải nhập số nguyên dương!");
//            return false;
//        }
//
//        return true;
//    }
//    private boolean checkGiaBan() {
//
//        String donGiaText = txtGiaBan.getText().trim();
//        if (!donGiaText.matches("\\d+(\\.\\d+)?")) {
//            JOptionPane.showMessageDialog(this, "Giá bán phải nhập số nguyên dương!");
//            return false;
//        }
//
//        return true;
//    }
//    private boolean checkSoLuong() {
//
//        String soLuongText = txtSoLuong.getText().trim();
//
//        if (!soLuongText.matches("\\d+")) {
//            JOptionPane.showMessageDialog(this, "Số lượng phải nhập số nguyên dương!");
//            return false;
//        }
//        return true;
//    }
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

//        if (txtTenSanPham.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
//            txtTenSanPham.requestFocus();
//            return false;
//        }
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
//        if (txtGiaNhap.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Giá nhập sản phẩm không được để trống!");
//            txtGiaNhap.requestFocus();
//            return false;
//
//        }
//        if (!checkGiaNhap()) {
//            txtGiaNhap.requestFocus();
//            return false;
//        }
//        if (txtGiaBan.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Giá bán sản phẩm không được để trống!");
//            txtGiaBan.requestFocus();
//            return false;
//
//        }
//        if (!checkGiaBan()) {
//            txtGiaBan.requestFocus();
//            return false;
//        }
//        if (txtSoLuong.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được để trống!");
//            txtSoLuong.requestFocus();
//            return false;
//
//        }
//        if (!checkSoLuong()) {
//            txtSoLuong.requestFocus();
//            return false;
//        }
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

//    private boolean checkCapNhat() {
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
//        if (txtTenSanPham.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
//            txtTenSanPham.requestFocus();
//            return false;
//        }
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
//        if (txtGiaNhap.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Giá nhập sản phẩm không được để trống!");
//            txtGiaNhap.requestFocus();
//            return false;
//
//        }
//        if (!checkGiaNhap()) {
//            txtGiaNhap.requestFocus();
//            return false;
//        }
//        if (txtGiaBan.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Giá bán sản phẩm không được để trống!");
//            txtGiaBan.requestFocus();
//            return false;
//
//        }
//        if (!checkGiaBan()) {
//            txtGiaBan.requestFocus();
//            return false;
//        }
//        if (txtSoLuong.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được để trống!");
//            txtSoLuong.requestFocus();
//            return false;
//
//        }
//        if (!checkSoLuong()) {
//            txtSoLuong.requestFocus();
//            return false;
//        }
//        if (txtaMoTa.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Mô tả sản phẩm không được để trống!");
//            txtaMoTa.requestFocus();
//            return false;
//        }
//        return true;
//    }
//    public boolean checkXoa() {
//        if (txtMaTK.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Mã tồn kho không được để trống!");
//            txtMaTK.requestFocus();
//            return false;
//        }
//        return true;
//    }
    public boolean checkTimKiem() {
        if (txtTim.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa cần tìm kiếm!");
            txtTim.requestFocus();
            return false;
        }
        return true;
    }

    private void updateTonKho() {
        String tenSP = ListSP.getSelectedValue();
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

            String sqlTonKho = "SELECT SUM(SoLuong) FROM SANPHAM WHERE TenSP = ?";
            stmt = conn.prepareStatement(sqlTonKho);
            stmt.setString(1, tenSP);
            rs = stmt.executeQuery();

            int databaseSum = 0;
            if (rs.next()) {
                databaseSum = rs.getInt(1);
            }

            String sqlThanhToan = "SELECT SUM(SoLuong) FROM THANHTOAN WHERE SPDaMua = ?";
            stmt = conn.prepareStatement(sqlThanhToan);
            stmt.setString(1, tenSP);
            rs = stmt.executeQuery();

            int thanhToanSum = 0;
            if (rs.next()) {
                thanhToanSum = rs.getInt(1);
            }

            int currentSoLuong = 0;
            String sl = spp.gettxtSL().getText();
            try {
                currentSoLuong = Integer.parseInt(sl);
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
    
//    private void loadTenSPKhongTrung() {
//        try {
//            List<String> tenSPList = SanPhamDAL.getAllTenSanPham();
//            if (tenSPList == null || tenSPList.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách sản phẩm!");
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        cboTimKiem = new javax.swing.JComboBox<>();
        btnTim = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaTK = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListSP = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        txtTonKho = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaMoTa = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 153));
        jLabel14.setText("QUẢN LÝ TỒN KHO");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        cboTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã Tồn Kho", "Tên Sản Phẩm" }));

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã tồn kho");

        txtMaTK.setEnabled(false);

        jLabel6.setText("Tên sản phẩm");

        ListSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListSPMouseClicked(evt);
            }
        });
        ListSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ListSPKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(ListSP);

        jLabel5.setText("Tồn kho");

        txtTonKho.setEnabled(false);

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

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã tồn kho", "Tên sản phẩm", "Tồn kho", "Ghi chú"
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel10))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTim)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(btnTim))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtMaTK, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                            .addComponent(txtTonKho))
                                        .addGap(65, 65, 65)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(btnThem)
                        .addGap(87, 87, 87)
                        .addComponent(btnMoi)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel14)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnMoi))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String luaChon = (String) cboTimKiem.getSelectedItem();
        String tuKhoa = txtTim.getText().trim();

        if (!checkTimKiem()) {
            return;
        }
        try {
            List<TonKho> list = new ArrayList<>();
            if (luaChon.equals("Mã Tồn Kho")) {
                if (!tuKhoa.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Mã tồn kho phải là số!");
                    return;
                }
                list = TonKhoDAL.findMaTK(Integer.parseInt(tuKhoa));
            } else if (luaChon.equals("Tên Sản Phẩm")) {
//                if (!tuKhoa.matches("\\d+")) {
//                    JOptionPane.showMessageDialog(this, "Số điện thoại phải là số nguyên!");
//                    return;
//                }
                list = TonKhoDAL.findTenSP(tuKhoa);
            }
            hienThiKetQuaTimKiem(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnTimActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
//        if (!isSanPhamExists(txtTenSanPham.getText().trim())) {
//            JOptionPane.showMessageDialog(this, "Tên sản phẩm không tồn tại trong danh mục sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return; // Stop the add operation
//        }
        if (!checkLuu()) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn lưu thông tin sản phẩm này không?");
        if (chon == JOptionPane.YES_OPTION) {
            try {
                TonKho tk = new TonKho();
                tk.setMaTK(txtMaTK.getText());
                tk.setTenSP(ListSP.getSelectedValue());
//             tk.setGiaN(Double.parseDouble(txtGiaNhap.getText()));
//             tk.setGiaB(Double.parseDouble(txtGiaBan.getText()));
//             tk.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
                tk.setTonKho(Integer.parseInt(txtTonKho.getText()));
//             tk.setTongCPN(Double.parseDouble(txtChiPhiNhapHang.getText()));
//             tk.setTg(txtTgianNhapHang.getText());
                tk.setGhiChu(txtaMoTa.getText());
                TonKhoDAL save = new TonKhoDAL();
                save.insertTK(tk);

                JOptionPane.showMessageDialog(this, "Lưu thông tin tồn kho thành công!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lưu thông tin tồn kho thất bại!");
                e.printStackTrace();
            }
        }
        fillToTable();
        updateTonKho();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        txtMaTK.setText("");
        ListSP.clearSelection();
//        txtGiaNhap.setText("");
//        txtGiaBan.setText("");
//        txtSoLuong.setText("");
        txtTonKho.setText("");
//        txtChiPhiNhapHang.setText("");
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
            String tenSP = (String) tblBang.getValueAt(index, 1); 
            ListSP.setSelectedValue(tenSP, true);
//            txtGiaNhap.setText(String.valueOf(tk.getGiaN()));
//            txtGiaBan.setText(String.valueOf(tk.getGiaB()));
            //txtSoLuong.setText(String.valueOf(tk.getSoLuong()));
//            txtChiPhiNhapHang.setText(String.valueOf(tk.getTongCPN()));
//            txtTgianNhapHang.setText(String.valueOf(tk.getTg()));
            txtaMoTa.setText(tk.getGhiChu());
            updateTonKho();
        }
    }//GEN-LAST:event_tblBangMouseClicked

    private void tblBangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBangMouseEntered

    private void ListSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ListSPKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ListSPKeyReleased

    private void ListSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListSPMouseClicked
        // TODO add your handling code here:
        updateTonKho();
    }//GEN-LAST:event_ListSPMouseClicked
    private void hienThiKetQuaTimKiem(List<TonKho> danhSach) {
        DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        for (TonKho tt : danhSach) {
            model.addRow(new Object[]{
                tt.getMaTK(),
                //tt.getMaKH(),
                tt.getTenSP(),
                tt.getTonKho(),
                tt.getGhiChu(),});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListSP;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JComboBox<String> cboTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtMaTK;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTonKho;
    private javax.swing.JTextArea txtaMoTa;
    // End of variables declaration//GEN-END:variables
}
