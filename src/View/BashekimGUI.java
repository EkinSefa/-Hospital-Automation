/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Helper.*;
import Model.*;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class BashekimGUI extends javax.swing.JFrame {

    Clinic clinic = new Clinic();
    private DefaultTableModel doctorModel = null;
    private Object[] doctorData = null;
    static Bashekim bashekim = new Bashekim();
    private DefaultTableModel clinicModel = null;
    private DefaultTableModel workerModel = null;
    private Object[] clinicData = null;
    private Object[] workerData = null;

    public BashekimGUI(Bashekim bashekim) throws SQLException {
        initComponents();

        jLabel1.setText("Hoşgeldiniz, Sayın " + bashekim.getName());
        //doctor Model
        doctorModel = new DefaultTableModel();
        Object[] colDoctorName = new Object[4];
        colDoctorName[0] = "ID";
        colDoctorName[1] = "Ad Soyad";
        colDoctorName[2] = "TC NU";
        colDoctorName[3] = "Şifre";
        doctorModel.setColumnIdentifiers(colDoctorName);
        doctorData = new Object[4];
        for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
            doctorData[0] = bashekim.getDoctorList().get(i).getId();
            doctorData[1] = bashekim.getDoctorList().get(i).getName();
            doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
            doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
            doctorModel.addRow(doctorData);
        }
        jTable1.setModel(doctorModel);
        // Clinic Model
        clinicModel = new DefaultTableModel();
        Object[] colClinicName = new Object[2];
        colClinicName[0] = "ID";
        colClinicName[1] = "Poliklinik Adı";
        clinicModel.setColumnIdentifiers(colClinicName);
        clinicData = new Object[2];
        for (int i = 0; i < clinic.getList().size(); i++) {
            clinicData[0] = clinic.getList().get(i).getId();
            clinicData[1] = clinic.getList().get(i).getName();
            clinicModel.addRow(clinicData);
        }
        
        // Worker Model
        workerModel = new DefaultTableModel();
        Object[] colClinicDoctorName = new Object[2];
        colClinicDoctorName[0] = "ID";
        colClinicDoctorName[1] = "Ad Soyad";
        workerModel.setColumnIdentifiers(colClinicDoctorName);
        workerData = new Object[2];

        jTable2.setModel(clinicModel);

        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    try {
                        int selecetedID = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                        String selectedName = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
                        String selectedTc = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
                        String selectedPass = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();

                        boolean control = bashekim.updateDoctor(selectedName, selectedTc, selectedPass, selecetedID);
                        if (control) {
                            UIManager.put("OptionPane.okButtonText", "Tamam");
                            JOptionPane.showMessageDialog(rootPane, "Güncelleme başarılı", "Güncelleme", JOptionPane.OK_OPTION, new ImageIcon(getClass().getResource("/icons/Done.png")));

                        }
                    } catch (SQLException ex) {

                    }
                }
            }
        });
        jTable2.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    try {
                        int selecetedID = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
                        String selectedName = jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString();

                        boolean control = clinic.updateClinic(selectedName, selecetedID);
                        if (control) {
                            UIManager.put("OptionPane.okButtonText", "Tamam");
                            JOptionPane.showMessageDialog(rootPane, "Güncelleme başarılı", "Güncelleme", JOptionPane.OK_OPTION, new ImageIcon(getClass().getResource("/icons/Done.png")));

                        }
                    } catch (SQLException ex) {

                    }
                }
            }
        });
        /*/  clinicMenu = new JPopupMenu();
        JMenuItem updateItem = new JMenuItem("Güncelle");
        JMenuItem deleteItem = new JMenuItem("Sil");
        clinicMenu.add(updateItem);
        clinicMenu.add(deleteItem);
        

        jTable2.setComponentPopupMenu(clinicMenu);
         */

        for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
            select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
        }

    }

    public void updateDoctorModel() throws SQLException {
        DefaultTableModel updatedTable = (DefaultTableModel) jTable1.getModel();
        updatedTable.setRowCount(0);
        for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
            doctorData[0] = bashekim.getDoctorList().get(i).getId();
            doctorData[1] = bashekim.getDoctorList().get(i).getName();
            doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
            doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
            doctorModel.addRow(doctorData);
        }
    }

    public void updateClinicModel() throws SQLException {
        DefaultTableModel updatedTable = (DefaultTableModel) jTable2.getModel();
        updatedTable.setRowCount(0);
        for (int i = 0; i < clinic.getList().size(); i++) {
            clinicData[0] = clinic.getList().get(i).getId();
            clinicData[1] = clinic.getList().get(i).getName();
            clinicModel.addRow(clinicData);
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

        w_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cıkısyap = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        w_tabpane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ıd = new javax.swing.JTextField();
        docpass = new javax.swing.JPasswordField();
        ad_soyad = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(doctorModel);
        jButton2 = new javax.swing.JButton();
        tcnu = new javax.swing.JTextField();
        w_clinic = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(clinicModel);
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable(workerModel);
        pol_addbutton = new javax.swing.JButton();
        pol_fldname = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pol_label = new javax.swing.JLabel();
        pol_fldID = new javax.swing.JTextField();
        pol_delete = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        pol_labelID = new javax.swing.JLabel();
        worker_addButton = new javax.swing.JButton();
        combo_label = new javax.swing.JLabel();
        select_doctor = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hastane Yönetim Sistemi");
        setMinimumSize(new java.awt.Dimension(710, 525));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        w_panel.setBackground(new java.awt.Color(255, 255, 255));
        w_panel.setMinimumSize(new java.awt.Dimension(700, 500));
        w_panel.setName(""); // NOI18N
        w_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(100, 169, 68));
        jLabel1.setText("Hoşgeldiniz");
        w_panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 270, 30));

        cıkısyap.setBackground(new java.awt.Color(100, 169, 68));
        cıkısyap.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        cıkısyap.setForeground(new java.awt.Color(255, 255, 255));
        cıkısyap.setText("Çıkış Yap");
        cıkısyap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cıkısyapActionPerformed(evt);
            }
        });
        w_panel.add(cıkısyap, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 160, 30));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(100, 169, 68));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setDoubleBuffered(true);
        jTabbedPane1.setFocusCycleRoot(true);
        jTabbedPane1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jTabbedPane1.setRequestFocusEnabled(false);

        w_tabpane.setBackground(new java.awt.Color(255, 255, 255));
        w_tabpane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        w_tabpane.setForeground(new java.awt.Color(100, 169, 68));
        w_tabpane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(100, 169, 68));
        jLabel2.setText("Ad Soyad");
        w_tabpane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(100, 169, 68));
        jLabel3.setText("TC Nu");
        w_tabpane.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, 20));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(100, 169, 68));
        jLabel4.setText("Şifre");
        w_tabpane.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(100, 169, 68));
        jLabel5.setText("ID");
        w_tabpane.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bu alan boş bırakılamaz");
        w_tabpane.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 140, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Bu alan boş bırakılamaz");
        w_tabpane.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 140, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Lütfen geçerli bir doktor seçiniz");
        w_tabpane.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 170, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Bu alan boş bırakılamaz");
        w_tabpane.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 140, -1));

        ıd.setBackground(new java.awt.Color(255, 255, 255));
        ıd.setForeground(new java.awt.Color(0, 0, 0));
        ıd.setCaretColor(new java.awt.Color(0, 0, 0));
        ıd.setEnabled(false);
        ıd.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                ıdAncestorRemoved(evt);
            }
        });
        ıd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ıdMouseClicked(evt);
            }
        });
        ıd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ıdActionPerformed(evt);
            }
        });
        ıd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ıdKeyPressed(evt);
            }
        });
        w_tabpane.add(ıd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 140, 30));

        docpass.setBackground(new java.awt.Color(255, 255, 255));
        docpass.setForeground(new java.awt.Color(0, 0, 0));
        docpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                docpassMouseClicked(evt);
            }
        });
        docpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docpassActionPerformed(evt);
            }
        });
        docpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                docpassKeyPressed(evt);
            }
        });
        w_tabpane.add(docpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 140, 30));

        ad_soyad.setBackground(new java.awt.Color(255, 255, 255));
        ad_soyad.setForeground(new java.awt.Color(0, 0, 0));
        ad_soyad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ad_soyadMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ad_soyadMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ad_soyadMouseReleased(evt);
            }
        });
        ad_soyad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ad_soyadActionPerformed(evt);
            }
        });
        ad_soyad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ad_soyadKeyPressed(evt);
            }
        });
        w_tabpane.add(ad_soyad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, 30));

        jButton1.setBackground(new java.awt.Color(100, 169, 68));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ekle");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        w_tabpane.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 140, 40));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 169, 68)));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setEnabled(false);
        jScrollPane1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(0, 102, 102));
        jTable1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "TC NU", "İSİM", "SİFRE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setDragEnabled(true);
        jTable1.setGridColor(new java.awt.Color(153, 153, 0));
        jTable1.setOpaque(false);
        jTable1.setSelectionBackground(new java.awt.Color(153, 153, 0));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.setShowGrid(false);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        w_tabpane.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 490, 360));

        jButton2.setBackground(new java.awt.Color(100, 169, 68));
        jButton2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sil");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        w_tabpane.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 140, 40));

        tcnu.setBackground(new java.awt.Color(255, 255, 255));
        tcnu.setForeground(new java.awt.Color(0, 0, 0));
        tcnu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tcnuMouseClicked(evt);
            }
        });
        tcnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcnuActionPerformed(evt);
            }
        });
        tcnu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcnuKeyPressed(evt);
            }
        });
        w_tabpane.add(tcnu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 140, 30));

        jTabbedPane1.addTab("Doktor Yönetimi", w_tabpane);

        w_clinic.setBackground(new java.awt.Color(255, 255, 255));
        w_clinic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        w_clinic.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 169, 68)));
        jScrollPane2.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        jTable2.setBackground(new java.awt.Color(0, 102, 102));
        jTable2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jTable2.setForeground(new java.awt.Color(0, 0, 0));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Poliklinik Adı"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(153, 153, 0));
        jTable2.setSelectionBackground(new java.awt.Color(153, 153, 0));
        jTable2.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setShowGrid(false);
        jTable2.getTableHeader().setResizingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        w_clinic.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 340, 250));

        jScrollPane3.setBackground(new java.awt.Color(0, 102, 102));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 169, 68)));
        jScrollPane3.setForeground(new java.awt.Color(0, 0, 0));

        jTable3.setBackground(new java.awt.Color(0, 102, 102));
        jTable3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jTable3.setForeground(new java.awt.Color(0, 0, 0));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Ad Soyad"
            }
        ));
        jTable3.getTableHeader().setResizingAllowed(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);

        w_clinic.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 340, 250));

        pol_addbutton.setBackground(new java.awt.Color(100, 169, 68));
        pol_addbutton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        pol_addbutton.setForeground(new java.awt.Color(255, 255, 255));
        pol_addbutton.setText("Ekle");
        pol_addbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pol_addbuttonMouseClicked(evt);
            }
        });
        pol_addbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pol_addbuttonActionPerformed(evt);
            }
        });
        w_clinic.add(pol_addbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 140, 40));

        pol_fldname.setBackground(new java.awt.Color(255, 255, 255));
        pol_fldname.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        pol_fldname.setForeground(new java.awt.Color(0, 0, 0));
        pol_fldname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pol_fldnameMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pol_fldnameMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pol_fldnameMouseReleased(evt);
            }
        });
        pol_fldname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pol_fldnameActionPerformed(evt);
            }
        });
        pol_fldname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pol_fldnameKeyPressed(evt);
            }
        });
        w_clinic.add(pol_fldname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 140, 30));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(100, 169, 68));
        jLabel12.setText("Doktor Atama");
        jLabel12.setToolTipText("Bu kısımda polikliniklere doktor ataması yapılır");
        w_clinic.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, 140, 30));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(100, 169, 68));
        jLabel10.setText("Poliklinik Adı");
        w_clinic.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 140, 30));

        pol_label.setForeground(new java.awt.Color(255, 255, 255));
        pol_label.setText("Bu kısım boş bırakılamaz");
        w_clinic.add(pol_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 140, 15));

        pol_fldID.setBackground(new java.awt.Color(255, 255, 255));
        pol_fldID.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        pol_fldID.setForeground(new java.awt.Color(0, 0, 0));
        pol_fldID.setCaretColor(new java.awt.Color(0, 0, 0));
        pol_fldID.setEnabled(false);
        pol_fldID.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                pol_fldIDAncestorRemoved(evt);
            }
        });
        pol_fldID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pol_fldIDMouseClicked(evt);
            }
        });
        pol_fldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pol_fldIDActionPerformed(evt);
            }
        });
        pol_fldID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pol_fldIDKeyPressed(evt);
            }
        });
        w_clinic.add(pol_fldID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 140, 30));

        pol_delete.setBackground(new java.awt.Color(100, 169, 68));
        pol_delete.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 15)); // NOI18N
        pol_delete.setForeground(new java.awt.Color(255, 255, 255));
        pol_delete.setText("Sil");
        pol_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pol_deleteActionPerformed(evt);
            }
        });
        w_clinic.add(pol_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 140, 40));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(100, 169, 68));
        jLabel11.setText("ID");
        w_clinic.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 30, -1));

        pol_labelID.setForeground(new java.awt.Color(255, 255, 255));
        pol_labelID.setText("Lütfen geçerli bir poliklinik seçiniz");
        w_clinic.add(pol_labelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 170, -1));

        worker_addButton.setBackground(new java.awt.Color(100, 169, 68));
        worker_addButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        worker_addButton.setForeground(new java.awt.Color(255, 255, 255));
        worker_addButton.setText("Ekle");
        worker_addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                worker_addButtonMouseClicked(evt);
            }
        });
        worker_addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                worker_addButtonActionPerformed(evt);
            }
        });
        w_clinic.add(worker_addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 140, 40));

        combo_label.setForeground(new java.awt.Color(255, 255, 255));
        combo_label.setText("Lütfen geçerli bir poliklinik seçiniz");
        w_clinic.add(combo_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 170, -1));

        select_doctor.setBackground(new java.awt.Color(255, 255, 255));
        select_doctor.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        select_doctor.setForeground(new java.awt.Color(0, 0, 0));
        select_doctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select_doctorMouseClicked(evt);
            }
        });
        select_doctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_doctorActionPerformed(evt);
            }
        });
        w_clinic.add(select_doctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 140, 30));

        jTabbedPane1.addTab("Poliklinik", w_clinic);

        w_panel.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 710, 440));

        getContentPane().add(w_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 710, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cıkısyapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cıkısyapActionPerformed
        LogınGUI logınGUI = new LogınGUI();
        logınGUI.setVisible(true);
        dispose();
    }//GEN-LAST:event_cıkısyapActionPerformed

    private void ad_soyadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ad_soyadActionPerformed

    }//GEN-LAST:event_ad_soyadActionPerformed

    private void docpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_docpassActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (ad_soyad.getText().length() == 0 && tcnu.getText().length() != 0 && docpass.getText().length() != 0) {
            jLabel7.setForeground(Color.red);
        } else if (ad_soyad.getText().length() != 0 && tcnu.getText().length() == 0 && docpass.getText().length() != 0) {
            jLabel6.setForeground(Color.red);
        } else if (ad_soyad.getText().length() == 0 && tcnu.getText().length() != 0 && docpass.getText().length() == 0) {
            jLabel9.setForeground(Color.red);
        } else if (ad_soyad.getText().length() == 0 && tcnu.getText().length() == 0 && docpass.getText().length() != 0) {
            jLabel7.setForeground(Color.red);
            jLabel6.setForeground(Color.red);
        } else if (ad_soyad.getText().length() != 0 && tcnu.getText().length() == 0 && docpass.getText().length() == 0) {
            jLabel6.setForeground(Color.red);
            jLabel9.setForeground(Color.red);
        } else if (ad_soyad.getText().length() == 0 && tcnu.getText().length() != 0 && docpass.getText().length() == 0) {
            jLabel7.setForeground(Color.red);
            jLabel9.setForeground(Color.red);
        } else if (ad_soyad.getText().length() == 0 && tcnu.getText().length() == 0 && docpass.getText().length() == 0) {
            jLabel9.setForeground(Color.red);
            jLabel7.setForeground(Color.red);
            jLabel6.setForeground(Color.red);
        } else {
            try {
                String upperAd_soyad = ad_soyad.getText().substring(0, 1).toUpperCase() + ad_soyad.getText().substring(1);
                boolean control = bashekim.addDoctor(tcnu.getText(), docpass.getText(), upperAd_soyad);
                if (control) {
                    UIManager.put("OptionPane.yesButtonText", "Evet");
                    JOptionPane.showMessageDialog(rootPane, "Kayıt Başarılı");
                    tcnu.setText(null);
                    docpass.setText(null);
                    ad_soyad.setText(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                updateDoctorModel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ad_soyadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_soyadMouseExited

    }//GEN-LAST:event_ad_soyadMouseExited

    private void ıdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ıdActionPerformed

    }//GEN-LAST:event_ıdActionPerformed

    private void ad_soyadMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_soyadMouseReleased

    }//GEN-LAST:event_ad_soyadMouseReleased

    private void ad_soyadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_soyadMouseClicked

    }//GEN-LAST:event_ad_soyadMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (ıd.getText().length() == 0) {
            jLabel8.setForeground(Color.red);
        } else {
            int selectedID = Integer.parseInt(ıd.getText());
            try {
                UIManager.put("OptionPane.okButtonText", "Evet");
                UIManager.put("OptionPane.noButtonText", "Hayır");
                int result = JOptionPane.showConfirmDialog(rootPane, "Bu işlemi gerçekleştirmek istiyor musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    boolean control = bashekim.deleteDoktorModel(selectedID);
                    if (control) {

                        ıd.setText(null);
                        updateDoctorModel();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tcnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcnuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcnuActionPerformed

    private void tcnuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tcnuMouseClicked

    }//GEN-LAST:event_tcnuMouseClicked

    private void docpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_docpassMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_docpassMouseClicked

    private void docpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_docpassKeyPressed
        jLabel9.setForeground(Color.WHITE);
    }//GEN-LAST:event_docpassKeyPressed

    private void tcnuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcnuKeyPressed
        jLabel6.setForeground(Color.WHITE);
    }//GEN-LAST:event_tcnuKeyPressed

    private void ad_soyadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ad_soyadKeyPressed
        jLabel7.setForeground(Color.WHITE);
    }//GEN-LAST:event_ad_soyadKeyPressed

    private void ıdAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ıdAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_ıdAncestorRemoved

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        ıd.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        jLabel8.setForeground(Color.WHITE);
    }//GEN-LAST:event_jTable1MouseClicked

    private void ıdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ıdMouseClicked

    }//GEN-LAST:event_ıdMouseClicked

    private void ıdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ıdKeyPressed

    }//GEN-LAST:event_ıdKeyPressed

    private void pol_fldnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pol_fldnameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pol_fldnameMouseClicked

    private void pol_fldnameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pol_fldnameMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pol_fldnameMouseExited

    private void pol_fldnameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pol_fldnameMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_pol_fldnameMouseReleased

    private void pol_fldnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pol_fldnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pol_fldnameActionPerformed

    private void pol_fldnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pol_fldnameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pol_fldnameKeyPressed

    private void pol_addbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pol_addbuttonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pol_addbuttonMouseClicked

    private void pol_addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pol_addbuttonActionPerformed
        if (pol_fldname.getText().length() == 0) {
            pol_label.setForeground(Color.red);
        } else {
            try {
                String upperAd_soyad = pol_fldname.getText().substring(0, 1).toUpperCase() + pol_fldname.getText().substring(1);
                boolean control = clinic.addClinic(upperAd_soyad);
                if (control) {
                    UIManager.put("OptionPane.yesButtonText", "Evet");
                    JOptionPane.showMessageDialog(rootPane, "Kayıt Başarılı");
                    pol_fldname.setText(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                updateClinicModel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_pol_addbuttonActionPerformed

    private void pol_fldIDAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_pol_fldIDAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_pol_fldIDAncestorRemoved

    private void pol_fldIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pol_fldIDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pol_fldIDMouseClicked

    private void pol_fldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pol_fldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pol_fldIDActionPerformed

    private void pol_fldIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pol_fldIDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pol_fldIDKeyPressed

    private void pol_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pol_deleteActionPerformed
        if (pol_fldID.getText().length() == 0) {
            pol_labelID.setForeground(Color.red);
        } else {
            int selectedID = Integer.parseInt(pol_fldID.getText());
            try {
                UIManager.put("OptionPane.okButtonText", "Evet");
                UIManager.put("OptionPane.noButtonText", "Hayır");
                int result = JOptionPane.showConfirmDialog(rootPane, "Bu işlemi gerçekleştirmek istiyor musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    boolean control = clinic.deleteClinic(selectedID);
                    if (control) {

                        ıd.setText(null);
                        updateClinicModel();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_pol_deleteActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        pol_fldID.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        pol_labelID.setForeground(Color.WHITE);

        /*  JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());
         String s=source.getModel().getValueAt(row, column)+"";
         System.out.println(s);*/
        int selRow = jTable2.getSelectedRow();
        if (selRow >= 0) {
            String selClinic = jTable2.getModel().getValueAt(selRow, 0).toString();
            int selClinicID = Integer.parseInt(selClinic);
            DefaultTableModel clearModel = (DefaultTableModel) jTable3.getModel();
            clearModel.setRowCount(0);
            try {
                for (int i = 0; i < bashekim.getClinicDoktorList(selClinicID).size(); i++) {
                    workerData[0] = bashekim.getClinicDoktorList(selClinicID).get(i).getId();
                    workerData[1] = bashekim.getClinicDoktorList(selClinicID).get(i).getName();
                    workerModel.addRow(workerData);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            jTable3.setModel(workerModel);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void worker_addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_worker_addButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_worker_addButtonMouseClicked

    private void worker_addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_worker_addButtonActionPerformed
        int selRow = jTable2.getSelectedRow();
        if (selRow >= 0) {
            String selClinic = jTable2.getModel().getValueAt(selRow, 0).toString();
            int selClinicID = Integer.parseInt(selClinic);
            Item doctorItem = (Item) select_doctor.getSelectedItem();

            try {

                boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
                if (control) {
                    UIManager.put("OptionPane.yesButtonText", "Evet");
                    JOptionPane.showMessageDialog(rootPane, "Kayıt Başarılı");

                } else {
                    UIManager.put("OptionPane.okButtonText", "Tamam");
                    JOptionPane.showMessageDialog(rootPane, "Kayıt Başarısız");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else {
            combo_label.setForeground(Color.red);
        }
    }//GEN-LAST:event_worker_addButtonActionPerformed

    private void select_doctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_doctorActionPerformed

    }//GEN-LAST:event_select_doctorActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void select_doctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_doctorMouseClicked

    }//GEN-LAST:event_select_doctorMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BashekimGUI(bashekim).setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ad_soyad;
    private javax.swing.JLabel combo_label;
    private javax.swing.JButton cıkısyap;
    private javax.swing.JPasswordField docpass;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton pol_addbutton;
    private javax.swing.JButton pol_delete;
    private javax.swing.JTextField pol_fldID;
    private javax.swing.JTextField pol_fldname;
    private javax.swing.JLabel pol_label;
    private javax.swing.JLabel pol_labelID;
    private javax.swing.JComboBox select_doctor;
    private javax.swing.JTextField tcnu;
    private javax.swing.JPanel w_clinic;
    private javax.swing.JPanel w_panel;
    private javax.swing.JPanel w_tabpane;
    private javax.swing.JButton worker_addButton;
    private javax.swing.JTextField ıd;
    // End of variables declaration//GEN-END:variables

}
