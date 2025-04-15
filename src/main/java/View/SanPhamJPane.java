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
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
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
    private TonKhoJPanel tonkhop;
    private ThanhToannJPane thanhtoanp;
    private DefaultListModel<String> modelSP = new DefaultListModel<>();
    private TonKhoJPanel tonKhoPanel;

    /**
     * Creates new form SanPhamJPane
     */
    public SanPhamJPane(TonKhoJPanel tkp, ThanhToannJPane ttp) {
        initComponents();
        this.tonkhop = tkp;
        this.thanhtoanp = ttp;
        initClock();
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

    public JTextField gettxtSL() {
        return txtSoLuong;
    }

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
        String[] cols = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Hãng", "Loại sản phẩm", "Phân loại", "Size", "Giá nhập", "Số lượng", "Giá bán", "Tổng chi phí nhập hàng", "Thời gian nhập hàng", "Mô tả"};
        tblModel.setColumnIdentifiers(cols);
        tblBang.setModel(tblModel);
    }

    private void fillToTable() {
        try {
            List<SanPham> list = SanPhamDAL.findAll();
            tblModel.setNumRows(0);
            for (SanPham sanPham : list) {
                tblModel.addRow(new Object[]{sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getHang(),
                    sanPham.getLoaiSP(), sanPham.getPhanLoai(), sanPham.getSize(), sanPham.getGiaN(),
                    sanPham.getSoLuong(), sanPham.getGiaB(), sanPham.getTongCPN(), sanPham.getTg(), sanPham.getMoTa()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tinhCP() {
        try {
            int sl = Integer.parseInt(txtSoLuong.getText());
            Double dg = Double.parseDouble(txtGiaNhap.getText());
            Double cp = sl * dg;
            txtChiPhiNhapHang.setText(String.valueOf(cp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tinhGiaBan() {
        try {

            Double gn = Double.valueOf(txtGiaNhap.getText());
            Double gb = (gn + (gn * 0.3));
            txtGiaBan.setText(String.valueOf(gb));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    private boolean checkTrung(String MaSP) {
//        List<String> danhSachMaSP = layDanhSachMaSP();
//        return danhSachMaSP.contains(MaSP);
//    }

    private boolean checkTrungTenSP(JList<String> list, String tenSP) {
        ListModel<String> model = list.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            if (model.getElementAt(i).equalsIgnoreCase(tenSP)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkSize() {
        String sizeText = txtSize.getText().trim();

        if (!sizeText.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Size phải nhập số nguyên dương!");
            return false;
        }

        return true;
    }

    private boolean checkGiaNhap() {

        String donGiaText = txtGiaNhap.getText().trim();
        if (!donGiaText.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải nhập số nguyên dương!");
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

//        if (checkTrung(txtTenSanPham.getText().trim())) {
//            JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại! Vui lòng nhập lại!");
//            txtTenSanPham.requestFocus();
//            return false;
//        }
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
        if (txtGiaNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá nhập sản phẩm không được để trống!");
            txtGiaNhap.requestFocus();
            return false;

        }
        if (!checkGiaNhap()) {
            txtGiaNhap.requestFocus();
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
        if (txtSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống!");
            txtSP.requestFocus();
            return false;
        }
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
        if (txtGiaNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá nhập sản phẩm không được để trống!");
            txtGiaNhap.requestFocus();
            return false;

        }
        if (!checkGiaNhap()) {
            txtGiaNhap.requestFocus();
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
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm cần tìm kiếm!");
            txtTim.requestFocus();
            return false;
        }
        return true;
    }
    
    public void setTonKhoPanel(TonKhoJPanel panel) {
        this.tonKhoPanel = panel;
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
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboPhanLoai = new javax.swing.JComboBox<>();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTgianNhapHang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtSize = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtChiPhiNhapHang = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaMoTa = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 153));
        jLabel14.setText("QUẢN LÝ SẢN PHẨM");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã sản phẩm");

        txtSP.setEnabled(false);

        jLabel3.setText("Hãng");

        jLabel4.setText("Phân loại");

        cboPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Unisex" }));

        txtGiaNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiaNhapKeyReleased(evt);
            }
        });

        jLabel5.setText("Giá nhập");

        txtGiaBan.setEnabled(false);

        jLabel9.setText("Giá bán");

        txtTgianNhapHang.setEnabled(false);

        jLabel12.setText("Thời gian nhập hàng");

        jLabel6.setText("Tên sản phẩm");

        jLabel7.setText("Loại sản phẩm");

        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giày", "Dép" }));
        cboLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSPActionPerformed(evt);
            }
        });

        jLabel8.setText("Size");

        jLabel13.setText("Số lượng");

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });

        jLabel11.setText("Tổng chi phí nhập hàng");

        txtChiPhiNhapHang.setEnabled(false);
        txtChiPhiNhapHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChiPhiNhapHangKeyReleased(evt);
            }
        });

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
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Hãng", "Loại sản phẩm", "Phân loại", "Size", "Giá nhập", "Số lượng", "Giá bán", "Mô tả"
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
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTim)
                                .addGap(18, 18, 18)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(104, 104, 104)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtHang)
                                    .addComponent(cboPhanLoai, javax.swing.GroupLayout.Alignment.LEADING, 0, 185, Short.MAX_VALUE)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTgianNhapHang, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSP))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboLoaiSP, 0, 190, Short.MAX_VALUE)
                                    .addComponent(txtSize, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtChiPhiNhapHang, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSanPham)))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel14)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel14)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel13)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(txtChiPhiNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTgianNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
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
        txtSoLuong.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtChiPhiNhapHang.setText("");

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
                sp.setGiaN(Double.parseDouble(txtGiaNhap.getText()));
                sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
                sp.setGiaB(Double.parseDouble(txtGiaBan.getText()));
                sp.setTongCPN(Double.parseDouble(txtChiPhiNhapHang.getText()));
                sp.setTg(txtTgianNhapHang.getText());
                sp.setMoTa(txtaMoTa.getText());
                SanPhamDAL save = new SanPhamDAL();
                save.insertSP(sp);
                if (tonkhop != null) {
                    tonkhop.loadSanPhamList();
                }

                fillToTable();
                if (tonKhoPanel != null) {
                    tonKhoPanel.loadSanPhamList();
                }
                JOptionPane.showMessageDialog(this, "Lưu thông tin sản phẩm thành công!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lưu thông tin sản phẩm thất bại!");
                e.printStackTrace();
            }
        }
//        fillToTable();
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
                sp.setGiaN(Double.parseDouble(txtGiaNhap.getText()));
                sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
                sp.setGiaB(Double.parseDouble(txtGiaBan.getText()));
                sp.setTongCPN(Double.parseDouble(txtChiPhiNhapHang.getText()));
                sp.setTg(txtTgianNhapHang.getText());
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
            List<SanPham> list = SanPhamDAL.findTenSP(txtTim.getText());
            if (list != null) {
                tblModel.setNumRows(0);
                for (SanPham sanPham : list) {
                    tblModel.addRow(new Object[]{sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getHang(), sanPham.getLoaiSP(), sanPham.getPhanLoai(),
                        sanPham.getSize(), sanPham.getGiaN(), sanPham.getSoLuong(), sanPham.getGiaB(), sanPham.getTongCPN(), sanPham.getTg(), sanPham.getMoTa()});
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
            txtGiaNhap.setText(String.valueOf(sp.getGiaN()));
            txtGiaBan.setText(String.valueOf(sp.getGiaB()));
            txtSoLuong.setText(String.valueOf(sp.getSoLuong()));
            txtChiPhiNhapHang.setText(String.valueOf(sp.getTongCPN()));
            txtTgianNhapHang.setText(String.valueOf(sp.getTg()));
            txtaMoTa.setText(sp.getMoTa());
        }
    }//GEN-LAST:event_tblBangMouseClicked

    private void tblBangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBangMouseEntered

    private void cboLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiSPActionPerformed

    private void txtGiaNhapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaNhapKeyReleased
        // TODO add your handling code here:
        tinhCP();
        tinhGiaBan();
    }//GEN-LAST:event_txtGiaNhapKeyReleased

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        // TODO add your handling code here:
        tinhCP();
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void txtChiPhiNhapHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChiPhiNhapHangKeyReleased
        // TODO add your handling code here:
        tinhCP();
    }//GEN-LAST:event_txtChiPhiNhapHangKeyReleased


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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtChiPhiNhapHang;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtHang;
    private javax.swing.JTextField txtSP;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTgianNhapHang;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextArea txtaMoTa;
    // End of variables declaration//GEN-END:variables
}
