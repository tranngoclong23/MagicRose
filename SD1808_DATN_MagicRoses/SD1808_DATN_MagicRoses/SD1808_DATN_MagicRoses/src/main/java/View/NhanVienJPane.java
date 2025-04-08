/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.NhanVien;
import Service.HoaDonDAL;
import Service.NhanVienDAL;
import Service.SanPhamDAL;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class NhanVienJPane extends javax.swing.JPanel {

    ArrayList<NhanVien> nvList;
    DefaultTableModel tblModel;

    /**
     * Creates new form NhanVienJPane
     */
    public NhanVienJPane() {
        initComponents();
        loadThang();
        loadNam();
        initTable();
        loadDataToTable();
//        fillToTable(0);
    }
 private void loadThang(){
        for(int i = 1; i <= 12; i++){
            cboThang.addItem(String.valueOf(i));
        }
        int thangHT = Calendar.getInstance().get(Calendar.MONTH) + 1;
        cboThang.setSelectedItem(thangHT);
    }
    private void loadNam(){
        int namHT = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = namHT - 5; i <= namHT; i++) {
            cboNam.addItem(String.valueOf(i));
        }
        cboNam.setSelectedItem(namHT);
    }
    private List<String> layDanhSachNhanVien() {
        List<String> danhSachMaNV = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=SOF102_SD1808_SmileyBall";
            String user = "sa";
            String password = "123";
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT MaNV FROM NHANVIEN";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("MaNV");

                danhSachMaNV.add(maNV);
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
        return danhSachMaNV;
    }

    private void initTable() {

        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã NV", "Tên NV", "Giới Tính", "Số ĐT", "Email"});
        tblNvien.setModel(tblModel);
    }

   private void loadDataToTable() {
        try {
             List<NhanVien> list = NhanVienDAL.findAll();
             tblModel.setNumRows(0);
             for (NhanVien nhanVien : list) {
                tblModel.addRow(new Object[]{nhanVien.getMaNV(),nhanVien.getTenNV(),
                    nhanVien.getGioiTinh(),nhanVien.getSoDT(),nhanVien.getEmail()});
            }
        } catch (Exception e) {
             e.printStackTrace();
        }

    }

    private void fillToTable(int rowIndex) {
        txtMa.setText(tblModel.getValueAt(rowIndex, 0).toString());
        txtTen.setText(tblModel.getValueAt(rowIndex, 1).toString());
        String gioiTinh = tblModel.getValueAt(rowIndex, 2).toString();
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtSdt.setText(tblModel.getValueAt(rowIndex, 3).toString());
        txtEmail.setText(tblModel.getValueAt(rowIndex, 4).toString());
        txtLuong.setText(tblModel.getValueAt(rowIndex, 5).toString());
    }

    private boolean checkTrung(String MaNV) {
        List<String> danhSachNV = layDanhSachNhanVien();
        return danhSachNV.contains(MaNV);
    }

//    private boolean checkNV() {
//        double luong = Double.parseDouble(txtLuong.getText());
//        if (luong <= 0) {
//            JOptionPane.showMessageDialog(this, "Lương không hợp lệ! Vui lòng nhập lại!");
//            return false;
//        }
//        return true;
//    }
      private boolean checkSDT() {
    String sdt = txtSdt.getText().trim();

    // Kiểm tra số điện thoại có đúng 10 chữ số không
    if (!sdt.matches("0\\d{9}")) {
        JOptionPane.showMessageDialog(this, "Số điện thoại phải là số nguyên! Phải bắt đầu bằng số 0 và có đúng 10 chữ số.");
        txtSdt.requestFocus();
        return false;
    }
    return true;
}private boolean checkEmail() {
    String email = txtEmail.getText().trim();

    // Regex kiểm tra email có đuôi @gmail.com
    if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
        JOptionPane.showMessageDialog(this, "Email không hợp lệ! Vui lòng nhập đúng định dạng, ví dụ: example@gmail.com");
        txtEmail.requestFocus();
        return false;
    }
    return true;
}

    private boolean checkLuu() {
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!");
            txtMa.requestFocus();
            return false;
        }
        if (checkTrung(txtMa.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại! Vui lòng nhập lại!");
            txtMa.requestFocus();
            return false;
        }

        if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống!");
            txtTen.requestFocus();
            return false;
        }
        if (!rdoNam.isSelected() && !rdoNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính!");
            return false;
        }
        if (txtSdt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
            txtSdt.requestFocus();
            return false;
        }
         if (!checkSDT()) {
            txtSdt.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống!");
            txtEmail.requestFocus();
            return false;
        }
        if (!checkEmail()) {
            txtEmail.requestFocus();
            return false;
        }
//        if (txtLuong.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Lương không được để trống!");
//            txtLuong.requestFocus();
//            return false;
//        }
        return true;

    }

    private boolean checkCapNhat() {
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!");
            txtMa.requestFocus();
            return false;
        }

        if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống!");
            txtTen.requestFocus();
            return false;
        }
        if (!rdoNam.isSelected() && !rdoNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính!");
            return false;
        }
        if (txtSdt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
            txtSdt.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống!");
            txtEmail.requestFocus();
            return false;
        }
//        if (txtLuong.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Lương không được để trống!");
//            txtLuong.requestFocus();
//            return false;
//        }
        return true;
    }

    public boolean checkXoa() {
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!");
            txtMa.requestFocus();
            return false;
        }
        return true;
    }

    public boolean checkTimKiem() {
        if (txtTImKiem.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền mã nhân viên cần tìm kiếm!");
            txtTImKiem.requestFocus();
            return false;
        }
        return true;
    }
    private void tinhL() {
        try {
            String maNV= txtMa.getText();
            String thang = (String) cboThang.getSelectedItem();
            String nam = (String) cboNam.getSelectedItem();
            double luong = NhanVienDAL.tinhLuongNVtheothang(maNV, thang, nam);
            txtLuong.setText(String.format("%,.0f", luong));
            
        } catch (Exception e) {
            e.printStackTrace();
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

        jButton1 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTImKiem = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        txtMa = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtLuong = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNvien = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cboThang = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 153));
        jLabel15.setText("QUẢN LÝ SẢN PHẨM");

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Tìm kiếm");

        jLabel12.setText("Mã nhân viên");

        jLabel13.setText("Giới tính");

        jLabel14.setText("Email");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });

        jLabel16.setText("Tên nhân viên");

        jLabel17.setText("Số điện thoại");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Lương");

        txtLuong.setEnabled(false);
        txtLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLuongActionPerformed(evt);
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

        tblNvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Giới tính", "SĐT", "Email"
            }
        ));
        tblNvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNvienMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNvien);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tháng");

        cboThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Năm");

        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 153));
        jLabel19.setText("QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtTImKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnTim))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                                        .addComponent(rdoNam)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(rdoNu)))
                                                .addGap(49, 49, 49)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel1)))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(btnThem)
                                                .addGap(53, 53, 53)
                                                .addComponent(btnSua)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnXoa))
                                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                                        .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(27, 27, 27)
                                                        .addComponent(jLabel2)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(btnMoi))))))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jLabel19)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTImKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(61, 61, 61))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnMoi)
                                .addGap(27, 27, 27))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        txtMa.setText("");
        txtTen.setText("");
        txtSdt.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
        buttonGroup1.clearSelection();
        cboThang.setSelectedIndex(0);
        cboNam.setSelectedIndex(0);
        txtTImKiem.setText("");
        loadDataToTable();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (!checkCapNhat()) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn cập nhật thông tin nhân viên này không?");
        if (chon == JOptionPane.YES_OPTION) {
            try {
                NhanVien nv = new NhanVien();
                nv.setMaNV(txtMa.getText());
                nv.setTenNV(txtTen.getText());
                if (rdoNam.isSelected()) {
                    nv.setGioiTinh("Nam");
                } else if (rdoNu.isSelected()) {
                    nv.setGioiTinh("Nữ");
                } else {
                    nv.setGioiTinh("");
                }
                nv.setSoDT(txtSdt.getText());
                nv.setEmail(txtEmail.getText());
//              
                NhanVienDAL update = new NhanVienDAL();
                update.suaNV(nv);
                loadDataToTable();
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên thất bại!");
            }
        }
        loadDataToTable();

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (!checkXoa()) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa thông tin nhân viên này không?");
        if (chon == JOptionPane.YES_OPTION) {
            try {
                NhanVienDAL delete = new NhanVienDAL();
                boolean ketQuaXoa = delete.xoaNV(txtMa.getText());
                loadDataToTable();
                if (ketQuaXoa) {
                    JOptionPane.showMessageDialog(this, "Xóa thông tin nhân viên thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên để xóa!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xóa thông tin nhân viên thất bại!");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if (!checkTimKiem()) {
            return;
        }
//      
        String ma = txtTImKiem.getText();
        try {
            NhanVienDAL nvDal = new NhanVienDAL();
            List<NhanVien> list = (List<NhanVien>) nvDal.timNV(ma);
            if (list != null) {
                tblModel.setNumRows(0);
                for (NhanVien nhanvien : list) {
                    tblModel.addRow(new Object[]{nhanvien.getMaNV(), nhanvien.getTenNV(),
                        nhanvien.getGioiTinh(), nhanvien.getSoDT(), nhanvien.getEmail()});
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void tblNvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNvienMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblNvien.getSelectedRow();
        if (selectedRow >= 0) {
            fillToTable(selectedRow);
        }
    }//GEN-LAST:event_tblNvienMouseClicked

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (!checkLuu()) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn lưu thông tin nhân viên này không?");
        if (chon == JOptionPane.YES_OPTION) {
            try {
                NhanVien nv = new NhanVien();
                nv.setMaNV(txtMa.getText());
                nv.setTenNV(txtTen.getText());
                if (rdoNam.isSelected()) {
                    nv.setGioiTinh("Nam");
                } else if (rdoNu.isSelected()) {
                    nv.setGioiTinh("Nữ");
                } else {
                    nv.setGioiTinh(""); 
                }
                nv.setSoDT(txtSdt.getText());
                nv.setEmail(txtEmail.getText());
//                nv.setLuong(Double.parseDouble(txtLuong.getText()));
                NhanVienDAL save = new NhanVienDAL();
                save.themNV(nv);
                JOptionPane.showMessageDialog(this, "Lưu thông tin nhân viên thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lưu thông tin nhân viên thất bại!");
            }
        }
        loadDataToTable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void cboThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangActionPerformed
        // TODO add your handling code here:
        tinhL();
    }//GEN-LAST:event_cboThangActionPerformed

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        // TODO add your handling code here:
        tinhL();
    }//GEN-LAST:event_cboNamActionPerformed

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
        tinhL();
    }//GEN-LAST:event_txtMaActionPerformed

    private void txtLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNvien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTImKiem;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
