/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.User;
import java.awt.BorderLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.xml.crypto.Data;

/**
 *
 * @author PC
 */
public class TrangChu extends javax.swing.JFrame {

    /**
     * Creates new form TrangChu
     */
    private DoanhThuJPane dtPanel;
    private KhachHangJPane khPanel;
    private NhanVienJPane nvPanel;
    private TonKhoJPanel tkPanel;
    private ThanhToannJPane ttPanel;
    private SanPhamJPane spPanel;

    private String tenDN;   
    
    private HoaDonnJPane hdPanel;
    

    private SimpleDateFormat timeFormat;

    public TrangChu(String tenDN) {
        initComponents();
//        tkPanel = new TonKhoJPanel();
//        spPanel = new SanPhamJPane(tkPanel, ttPanel);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.tenDN = tenDN;
        initClock();
//        ktQ();
    }

    public void initClock() {
        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
        updateTime();
    }

    public void updateTime() {
        String currentTime = timeFormat.format(new Date());
        jLabel1.setText(currentTime);
    }

//    private void ktQ(){
//        if(!tenDN.equalsIgnoreCase("QuanLy")){
//            jButton5.setEnabled(false);
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

        jToolBar1 = new javax.swing.JToolBar();
        btnDangXuat = new javax.swing.JButton();
        btnThongTinSanPham = new javax.swing.JButton();
        btnThongTinTonKho = new javax.swing.JButton();
        btnThongTinNV = new javax.swing.JButton();
        btnThongTinKH = new javax.swing.JButton();
        btnThongTinTT = new javax.swing.JButton();
        btnThongTinHoaDon = new javax.swing.JButton();
        btnDoanhThu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tabMain = new javax.swing.JTabbedPane();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuHeThong = new javax.swing.JMenu();
        mnuDangNhap = new javax.swing.JMenuItem();
        mnuDangXuat = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnuSanPham = new javax.swing.JMenuItem();
        mnuTonKho = new javax.swing.JMenuItem();
        mnuNhanVien = new javax.swing.JMenuItem();
        mnuKH = new javax.swing.JMenuItem();
        mnuThanhToan = new javax.swing.JMenuItem();
        mnuHoaDon = new javax.swing.JMenuItem();
        mnuDoanhThu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setRollover(true);

        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDangXuat);

        btnThongTinSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/khoahoc.png"))); // NOI18N
        btnThongTinSanPham.setText("Thông tin sản phẩm");
        btnThongTinSanPham.setFocusable(false);
        btnThongTinSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongTinSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongTinSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinSanPhamActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThongTinSanPham);

        btnThongTinTonKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boxes.png"))); // NOI18N
        btnThongTinTonKho.setText("Thông tin tồn kho");
        btnThongTinTonKho.setFocusable(false);
        btnThongTinTonKho.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongTinTonKho.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongTinTonKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinTonKhoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThongTinTonKho);

        btnThongTinNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien.png"))); // NOI18N
        btnThongTinNV.setText("Thông tin nhân viên");
        btnThongTinNV.setFocusable(false);
        btnThongTinNV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongTinNV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongTinNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinNVActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThongTinNV);

        btnThongTinKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nguoihoc.png"))); // NOI18N
        btnThongTinKH.setText("Thông tin khách hàng");
        btnThongTinKH.setFocusable(false);
        btnThongTinKH.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongTinKH.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongTinKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinKHActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThongTinKH);

        btnThongTinTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pay.png"))); // NOI18N
        btnThongTinTT.setText("Thông tin thanh toán");
        btnThongTinTT.setFocusable(false);
        btnThongTinTT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongTinTT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongTinTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinTTActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThongTinTT);

        btnThongTinHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N
        btnThongTinHoaDon.setText("Thông tin hóa đơn");
        btnThongTinHoaDon.setFocusable(false);
        btnThongTinHoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongTinHoaDon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongTinHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinHoaDonActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThongTinHoaDon);

        btnDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/doanhthu.png"))); // NOI18N
        btnDoanhThu.setText("Thống kê doanh thu");
        btnDoanhThu.setFocusable(false);
        btnDoanhThu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDoanhThu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoanhThuActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDoanhThu);

        jLabel1.setText("11:22:59 AM");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gioithieusanpham.png"))); // NOI18N
        jLabel2.setText("Trang chủ quản lý cửa hàng giày dép");

        mnuHeThong.setText("Hệ thống");

        mnuDangNhap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dangnhap.png"))); // NOI18N
        mnuDangNhap.setText("Đăng nhập");
        mnuDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDangNhapActionPerformed(evt);
            }
        });
        mnuHeThong.add(mnuDangNhap);

        mnuDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit.png"))); // NOI18N
        mnuDangXuat.setText("Đăng xuất");
        mnuDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDangXuatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mnuDangXuat);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ban.png"))); // NOI18N
        jMenuItem3.setText("Kết thúc");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuHeThong.add(jMenuItem3);

        jMenuBar1.add(mnuHeThong);

        jMenu2.setText("Quản lý");

        mnuSanPham.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/khoahoc.png"))); // NOI18N
        mnuSanPham.setText("Sản phẩm");
        mnuSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSanPhamActionPerformed(evt);
            }
        });
        jMenu2.add(mnuSanPham);

        mnuTonKho.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuTonKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boxes.png"))); // NOI18N
        mnuTonKho.setText("Tồn kho");
        mnuTonKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTonKhoActionPerformed(evt);
            }
        });
        jMenu2.add(mnuTonKho);

        mnuNhanVien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien.png"))); // NOI18N
        mnuNhanVien.setText("Nhân viên");
        mnuNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNhanVienActionPerformed(evt);
            }
        });
        jMenu2.add(mnuNhanVien);

        mnuKH.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nguoihoc.png"))); // NOI18N
        mnuKH.setText("Khách hàng");
        mnuKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuKHActionPerformed(evt);
            }
        });
        jMenu2.add(mnuKH);

        mnuThanhToan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pay.png"))); // NOI18N
        mnuThanhToan.setText("Thanh toán");
        mnuThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuThanhToanActionPerformed(evt);
            }
        });
        jMenu2.add(mnuThanhToan);

        mnuHoaDon.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N
        mnuHoaDon.setText("Hóa đơn");
        mnuHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHoaDonActionPerformed(evt);
            }
        });
        jMenu2.add(mnuHoaDon);

        mnuDoanhThu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/doanhthu.png"))); // NOI18N
        mnuDoanhThu.setText("Doanh thu");
        mnuDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDoanhThuActionPerformed(evt);
            }
        });
        jMenu2.add(mnuDoanhThu);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Liên hệ");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1456, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabMain)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabMain, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongTinSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinSanPhamActionPerformed
        // TODO add your handling code here:
//        if (tkPanel == null) {
//            tkPanel = new TonKhoJPanel();
//            tabMain.addTab("Thông tin tồn kho", tkPanel);
//        }
        if (spPanel == null) {
            spPanel = new SanPhamJPane(tkPanel, ttPanel);
            tabMain.addTab("Thông tin sản phẩm", spPanel);
        }
        tabMain.setSelectedComponent(spPanel);
    }//GEN-LAST:event_btnThongTinSanPhamActionPerformed

    private void btnThongTinNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinNVActionPerformed
        // TODO add your handling code here:
        if (!tenDN.equals("QuanLy")) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập quản lý nhân viên!");
            return;
        } else {
            if (nvPanel == null) {
                nvPanel = new NhanVienJPane();
                tabMain.addTab("Thông tin nhân viên", nvPanel);
            }
            tabMain.setSelectedComponent(nvPanel);
        }

    }//GEN-LAST:event_btnThongTinNVActionPerformed

    private void btnDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoanhThuActionPerformed
        // TODO add your handling code here:
        if (!tenDN.equals("QuanLy")) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập doanh thu!");
            return;
        } else {
            if (dtPanel == null) {
                dtPanel = new DoanhThuJPane();
                tabMain.addTab("Thống kê doanh thu", dtPanel);
            }
            tabMain.setSelectedComponent(dtPanel);
        }

    }//GEN-LAST:event_btnDoanhThuActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
//        LoginForm lg = new LoginForm();
//        lg.setVisible(true);
    }//GEN-LAST:event_formWindowOpened

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        tabMain.removeAll();
        LoginForm lg = new LoginForm();
        lg.setVisible(true);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void mnuDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDangNhapActionPerformed
        // TODO add your handling code here:
        tabMain.removeAll();
        LoginForm lg = new LoginForm();
        lg.setVisible(true);
    }//GEN-LAST:event_mnuDangNhapActionPerformed

    private void mnuDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDangXuatActionPerformed
        // TODO add your handling code here:
        tabMain.removeAll();
        LoginForm lg = new LoginForm();
        lg.setVisible(true);
    }//GEN-LAST:event_mnuDangXuatActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btnThongTinHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinHoaDonActionPerformed
        // TODO add your handling code here:
        if (hdPanel == null) {
            hdPanel = new HoaDonnJPane();
            tabMain.addTab("Thông tin hóa đơn", hdPanel);
        }
        tabMain.setSelectedComponent(hdPanel);
    }//GEN-LAST:event_btnThongTinHoaDonActionPerformed

    private void btnThongTinKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinKHActionPerformed
        // TODO add your handling code here:
        if (khPanel == null) {
            khPanel = new KhachHangJPane();
            tabMain.addTab("Thông tin khách hàng", khPanel);
        }
        tabMain.setSelectedComponent(khPanel);
    }//GEN-LAST:event_btnThongTinKHActionPerformed

    private void btnThongTinTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinTTActionPerformed
        // TODO add your handling code here:
        if (ttPanel == null) {
            ttPanel = new ThanhToannJPane();
            tabMain.addTab("Thông tin thanh toán", ttPanel);
        }
        tabMain.setSelectedComponent(ttPanel);
    }//GEN-LAST:event_btnThongTinTTActionPerformed

    private void btnThongTinTonKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinTonKhoActionPerformed
        // TODO add your handling code here: 
        if (spPanel == null) {
            spPanel = new SanPhamJPane(tkPanel, ttPanel);
            tabMain.addTab("Thông tin sản phẩm", spPanel);
        }
        if (tkPanel == null) {
            tkPanel = new TonKhoJPanel(spPanel);
            tabMain.addTab("Thông tin tồn kho", tkPanel);
        }
        
        tabMain.setSelectedComponent(tkPanel);
    }//GEN-LAST:event_btnThongTinTonKhoActionPerformed

    private void mnuDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDoanhThuActionPerformed
        // TODO add your handling code here:
        if (!tenDN.equals("QuanLy")) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập doanh thu!");
            return;
        } else {
            if (dtPanel == null) {
                dtPanel = new DoanhThuJPane();
                tabMain.addTab("Thống kê doanh thu", dtPanel);
            }
            tabMain.setSelectedComponent(dtPanel);
        }
    }//GEN-LAST:event_mnuDoanhThuActionPerformed

    private void mnuHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHoaDonActionPerformed
        // TODO add your handling code here:
        if (hdPanel == null) {
            hdPanel = new HoaDonnJPane();
            tabMain.addTab("Thông tin hóa đơn", hdPanel);
        }
        tabMain.setSelectedComponent(hdPanel);
    }//GEN-LAST:event_mnuHoaDonActionPerformed

    private void mnuThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuThanhToanActionPerformed
        // TODO add your handling code here:
        if (ttPanel == null) {
            ttPanel = new ThanhToannJPane();
            tabMain.addTab("Thông tin thanh toán", ttPanel);
        }
        tabMain.setSelectedComponent(ttPanel);
    }//GEN-LAST:event_mnuThanhToanActionPerformed

    private void mnuKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuKHActionPerformed
        // TODO add your handling code here:
        if (khPanel == null) {
            khPanel = new KhachHangJPane();
            tabMain.addTab("Thông tin khách hàng", khPanel);
        }
        tabMain.setSelectedComponent(khPanel);
    }//GEN-LAST:event_mnuKHActionPerformed

    private void mnuNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNhanVienActionPerformed
        // TODO add your handling code here:
        if (!tenDN.equals("QuanLy")) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập quản lý nhân viên!");
            return;
        } else {
            if (nvPanel == null) {
                nvPanel = new NhanVienJPane();
                tabMain.addTab("Thông tin nhân viên", nvPanel);
            }
            tabMain.setSelectedComponent(nvPanel);
        }
    }//GEN-LAST:event_mnuNhanVienActionPerformed

    private void mnuSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSanPhamActionPerformed
        // TODO add your handling code here:
        if (spPanel == null) {
            spPanel = new SanPhamJPane(tkPanel, ttPanel);
            tabMain.addTab("Thông tin sản phẩm", spPanel);
        }
        tabMain.setSelectedComponent(spPanel);
    }//GEN-LAST:event_mnuSanPhamActionPerformed

    private void mnuTonKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTonKhoActionPerformed
        // TODO add your handling code here:
        if (spPanel == null) {
            spPanel = new SanPhamJPane(tkPanel, ttPanel);
            tabMain.addTab("Thông tin sản phẩm", spPanel);
        }
        if (tkPanel == null) {
            tkPanel = new TonKhoJPanel(spPanel);
            tabMain.addTab("Thông tin tồn kho", tkPanel);
        }
        
        tabMain.setSelectedComponent(tkPanel);
    }//GEN-LAST:event_mnuTonKhoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChu("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDoanhThu;
    private javax.swing.JButton btnThongTinHoaDon;
    private javax.swing.JButton btnThongTinKH;
    private javax.swing.JButton btnThongTinNV;
    private javax.swing.JButton btnThongTinSanPham;
    private javax.swing.JButton btnThongTinTT;
    private javax.swing.JButton btnThongTinTonKho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem mnuDangNhap;
    private javax.swing.JMenuItem mnuDangXuat;
    private javax.swing.JMenuItem mnuDoanhThu;
    private javax.swing.JMenu mnuHeThong;
    private javax.swing.JMenuItem mnuHoaDon;
    private javax.swing.JMenuItem mnuKH;
    private javax.swing.JMenuItem mnuNhanVien;
    private javax.swing.JMenuItem mnuSanPham;
    private javax.swing.JMenuItem mnuThanhToan;
    private javax.swing.JMenuItem mnuTonKho;
    private javax.swing.JTabbedPane tabMain;
    // End of variables declaration//GEN-END:variables
}
