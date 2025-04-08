/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.KhachHang;
import Model.NhanVien;
import Model.SanPham;
import Service.KhachHangDAL;
import Service.SanPhamDAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author PC
 */
public class SanPhamJPane extends javax.swing.JPanel {

    DefaultTableModel tblModel;
    ArrayList<SanPham> spList;
    private SimpleDateFormat timeFormat;

    /**
     * Creates new form SanPhamJPane
     */
    public SanPhamJPane() {
        initComponents();
        
        initTable();
        fillToTable();
    }

//    public void initClock() {
//        timeFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Timer timer = new Timer(86400000, e -> updateTime());
//        timer.start();
//        updateTime();
//    }
//
//    public void updateTime() {
//        String currentTime = timeFormat.format(new Date());
//        txtTgianNhapHang.setText(currentTime);
//    }

    private List<String> layDanhSachTenSP() {
        List<String> danhSachTenSP = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=SOF102_SD1808_SmileyBall";
            String user = "sa";
            String password = "123";
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT TenSP FROM SANPHAM";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String tenSP = rs.getString("TenSP");

                danhSachTenSP.add(tenSP);
            }
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
        return danhSachTenSP;
    }
private boolean checkTrung(String TenSP) {
        List<String> danhSachTenSP = layDanhSachTenSP();
        return danhSachTenSP.contains(TenSP);
    }
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
        String[] cols = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Hãng", "Loại sản phẩm", "Phân loại", "Size", "Mô tả"};
        tblModel.setColumnIdentifiers(cols);
        tblBang.setModel(tblModel);
    }

    private void fillToTable() {
        try {
            List<SanPham> list = SanPhamDAL.findAll();
            tblModel.setNumRows(0);
            for (SanPham sanPham : list) {
                tblModel.addRow(new Object[]{sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getHang(),
                    sanPham.getLoaiSP(), sanPham.getPhanLoai(), sanPham.getSize(), sanPham.getMoTa()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void tinhCP() {
//        try {
//            int sl = Integer.parseInt(txtSoLuong.getText());
//            Double dg = Double.parseDouble(txtDonGia.getText());
//            Double cp = sl * dg;
//            txtChiPhiNhapHang.setText(String.valueOf(cp));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private boolean checkTrung(String MaSP) {
//        List<String> danhSachMaSP = layDanhSachMaSP();
//        return danhSachMaSP.contains(MaSP);
//    }

    private boolean checkSize() {
        String sizeText = txtSize.getText().trim();

        if (!sizeText.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Size phải nhập số nguyên dương!");
            return false;
        }

        return true;
    }

//    private boolean checkDonGia() {
//      
//        String donGiaText = txtDonGia.getText().trim();
//                if (!donGiaText.matches("\\d+(\\.\\d+)?")) {
//            JOptionPane.showMessageDialog(this, "Đơn giá phải nhập số nguyên dương!");
//            return false;
//        }
//        
//        return true;
//    }
//
//    private boolean checkSoLuong() {
//     
//        String soLuongText = txtSoLuong.getText().trim();
//
//        
//        if (!soLuongText.matches("\\d+")) {
//            JOptionPane.showMessageDialog(this, "Số lượng phải nhập số nguyên dương!");
//            return false;
//        }
//        return true;
//    }

    private boolean checkLuu() {
//        if (txtSP.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống!");
//            txtSP.requestFocus();
//            return false;
//        }


        if (txtTenSanPham.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
            txtTenSanPham.requestFocus();
            return false;
        }
        
        if (checkTrung(txtTenSanPham.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại! Vui lòng nhập lại!");
            txtTenSanPham.requestFocus();
            return false;
        }

        if (txtHang.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãng sản phẩm không được để trống!");
            txtHang.requestFocus();
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
        if (txtSize.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Size sản phẩm không được để trống!");
            txtSize.requestFocus();
            return false;
        }
        if (!checkSize()) {
            txtSize.requestFocus();
            return false;
        }
//        if (txtDonGia.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Đơn giá sản phẩm không được để trống!");
//            txtDonGia.requestFocus();
//            return false;
//
//        }
//        if (!checkDonGia()) {
//            txtDonGia.requestFocus();
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

    private boolean checkCapNhat() {
        if (txtTenSanPham.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
            txtTenSanPham.requestFocus();
            return false;
        }

        if (txtHang.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãng sản phẩm không được để trống!");
            txtHang.requestFocus();
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
        if (txtSize.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Size sản phẩm không được để trống!");
            txtSize.requestFocus();
            return false;
        }
        if (!checkSize()) {
            txtSize.requestFocus();
            return false;
        }
        return true;
    }

    public boolean checkXoa() {
        if (txtSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống!");
            txtSP.requestFocus();
            return false;
        }
        return true;
    }

    public boolean checkTimKiem() {
        if (txtTim.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền mã sản phẩm cần tìm kiếm!");
            txtTim.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        txtSP = new javax.swing.JTextField();
        txtHang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txtSize = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaMoTa = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        cboLoaiSP = new javax.swing.JComboBox<>();
        cboPhanLoai = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        jLabel2.setText("Mã sản phẩm");

        jLabel3.setText("Hãng");

        jLabel4.setText("Phân loại");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        txtSP.setEnabled(false);

        jLabel6.setText("Tên sản phẩm");

        jLabel7.setText("Loại sản phẩm");

        jLabel8.setText("Size");

        jLabel10.setText("Mô tả sản phẩm");

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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Hãng", "Loại sản phẩm", "Phân loại", "Size", "Mô tả"
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

        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giày", "Dép" }));
        cboLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSPActionPerformed(evt);
            }
        });

        cboPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Unisex" }));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 153));
        jLabel14.setText("QUẢN LÝ SẢN PHẨM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(239, 239, 239)
                                .addComponent(jLabel14))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnThem)
                                        .addGap(56, 56, 56)
                                        .addComponent(btnSua)
                                        .addGap(63, 63, 63)
                                        .addComponent(btnXoa)
                                        .addGap(62, 62, 62)
                                        .addComponent(btnMoi))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtTim)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnTim))
                                    .addComponent(jScrollPane1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtHang, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSP, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboPhanLoai, 0, 185, Short.MAX_VALUE))
                                        .addGap(49, 49, 49)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTenSanPham)
                                            .addComponent(txtSize)
                                            .addComponent(cboLoaiSP, 0, 190, Short.MAX_VALUE))))))
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        txtSP.setText("");
        txtTenSanPham.setText("");
        txtHang.setText("");
        cboPhanLoai.setSelectedIndex(0);
        cboLoaiSP.setSelectedIndex(0);
        txtSize.setText("");
       
        txtaMoTa.setText("");
        txtTim.setText("");
        //txtTgianNhapHang.setText("");
        fillToTable();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (!checkLuu()) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn lưu thông tin sản phẩm này không?");
        if (chon == JOptionPane.YES_OPTION) {
            try {
                SanPham sp = new SanPham();
                sp.setMaSP(txtSP.getText());
                sp.setTenSP(txtTenSanPham.getText());
                sp.setHang(txtHang.getText());
                sp.setLoaiSP((String) cboLoaiSP.getSelectedItem());
                sp.setPhanLoai((String) cboPhanLoai.getSelectedItem());
                sp.setSize(Integer.parseInt(txtSize.getText()));
              
                sp.setMoTa(txtaMoTa.getText());
                SanPhamDAL save = new SanPhamDAL();
                save.insertSP(sp);

                JOptionPane.showMessageDialog(this, "Lưu thông tin sản phẩm thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lưu thông tin sản phẩm thất bại!");
                e.printStackTrace();
            }
        }
        fillToTable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (!checkCapNhat()) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn cập nhật thông tin sản phẩm này không?");
        if (chon == JOptionPane.YES_OPTION) {
            try {
                SanPham sp = new SanPham();
                sp.setMaSP(txtSP.getText());
                sp.setTenSP(txtTenSanPham.getText());
                sp.setHang(txtHang.getText());
                sp.setLoaiSP((String) cboLoaiSP.getSelectedItem());
                sp.setPhanLoai((String) cboPhanLoai.getSelectedItem());
                sp.setSize(Integer.parseInt(txtSize.getText()));
                
                sp.setMoTa(txtaMoTa.getText());
                SanPhamDAL update = new SanPhamDAL();
                update.updateSP(sp);
                fillToTable();
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin sản phẩm thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin sản phẩm thất bại!");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (!checkXoa()) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa thông tin sản phẩm này không?");
        if (chon == JOptionPane.YES_OPTION) {
            try {
                SanPhamDAL delete = new SanPhamDAL();
                boolean ketQuaXoa = delete.deleteSP(txtSP.getText());

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

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if (!checkTimKiem()) {
            return;
        }
        
            try {
                List<SanPham> list = SanPhamDAL.searchSp(txtTim.getText());
                if (list != null) {
                    tblModel.setNumRows(0);
                    for (SanPham sanPham : list) {
                        tblModel.addRow(new Object[]{sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getHang(), sanPham.getLoaiSP(), sanPham.getPhanLoai(),
                             sanPham.getSize(), sanPham.getMoTa()});
                    }
                 
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }

  
    }//GEN-LAST:event_btnTimActionPerformed

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        // TODO add your handling code here:
        int index = tblBang.getSelectedRow();
        if (index >= 0) {
            String ma = (String) tblBang.getValueAt(index, 0);
            SanPham sp = (SanPham) SanPhamDAL.findByID(ma);
            txtSP.setText(sp.getMaSP());
            txtTenSanPham.setText(sp.getTenSP());
            txtHang.setText(sp.getHang());
            cboPhanLoai.setSelectedItem(sp.getPhanLoai());
            cboLoaiSP.setSelectedItem(sp.getPhanLoai());
            txtSize.setText(String.valueOf(sp.getSize()));
          
            txtaMoTa.setText(sp.getMoTa());
        }
    }//GEN-LAST:event_tblBangMouseClicked

    private void tblBangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBangMouseEntered

    private void cboLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboPhanLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtHang;
    private javax.swing.JTextField txtSP;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextArea txtaMoTa;
    // End of variables declaration//GEN-END:variables
}
