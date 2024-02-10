/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Doctor;
import Model.Whours;
import static View.BashekimGUI.bashekim;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class DoctorGUI extends javax.swing.JFrame {

    private static Doctor doctor = new Doctor();
    private DefaultTableModel whoursModel = null;
    private Object[] whoursData = null;

    public DoctorGUI(Doctor doctor) throws SQLException {
        initComponents();
        Image icon = new ImageIcon(this.getClass().getResource("/icons/Hospital32.png")).getImage();
        this.setIconImage(icon);
        table_hours.setDefaultEditor(Object.class, null);

        //Whours Model
        whoursModel = new DefaultTableModel();
        Object[] colWhoursName = new Object[2];
        colWhoursName[0] = "ID";
        colWhoursName[1] = "Tarih";
        whoursModel.setColumnIdentifiers(colWhoursName);
        whoursData = new Object[2];
        for (int i = 0; i < doctor.getWhoursList(doctor.getId()).size(); i++) {
            whoursData[0] = doctor.getWhoursList(doctor.getId()).get(i).getId();
            whoursData[1] = doctor.getWhoursList(doctor.getId()).get(i).getWdate();
            whoursModel.addRow(whoursData);
        }
        table_hours.setModel(whoursModel);

        jLabel1.setText("Hoşgeldiniz, Sayın " + doctor.getName());

        btn_addHours.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                String date = "";
                try {
                    date = sdf.format(date_chooser.getDate());
                } catch (Exception ex) {
                }

                if (date.length() == 0) {
                    date_error.setForeground(Color.red);
                } else {
                    String time = " " + select_time.getSelectedItem().toString() + ":00";
                    String selectedDate = date + time;

                    try {
                        boolean control = doctor.addHours(doctor.getId(), doctor.getName(), selectedDate);
                        if (control) {
                            UIManager.put("OptionPane.okButtonText", "Tamam");
                            JOptionPane.showMessageDialog(rootPane, "İşlem başarılı", "Çalışma Saatleri", JOptionPane.OK_OPTION, new ImageIcon(getClass().getResource("/icons/Done.png")));
                            updateWhoursModel(doctor);
                        } else {
                            UIManager.put("OptionPane.okButtonText", "Tamam");
                            JOptionPane.showMessageDialog(rootPane, "İşlem Başarısız", "Çalışma Saatleri", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }

        });
        this.date_chooser.getDateEditor().getUiComponent().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 1) {
                    date_error.setForeground(Color.WHITE);
                }
            }
        });

        table_hours.setDefaultEditor(table_hours.getClass(), null);
        
        btn_deleteHours.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ıd_date.getText().length() == 0) {
            ıd_label.setForeground(Color.red);
        }else {
            int selectedID = Integer.parseInt(ıd_date.getText());
            try {
                UIManager.put("OptionPane.okButtonText", "Evet");
                UIManager.put("OptionPane.noButtonText", "Hayır");
                int result = JOptionPane.showConfirmDialog(rootPane, "Bu işlemi gerçekleştirmek istiyor musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    boolean control = doctor.deleteWhoursModel(selectedID);
                    if (control) {

                        ıd_date.setText(null);
                        updateWhoursModel(doctor);
                    }
                }
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }
        
            }
        });

    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        w_pane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cıkısyap = new javax.swing.JButton();
        w_whours = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        date_chooser = new com.toedter.calendar.JDateChooser();
        select_time = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_hours = new javax.swing.JTable();
        date_error = new javax.swing.JLabel();
        btn_deleteHours = new javax.swing.JButton();
        btn_addHours = new javax.swing.JButton();
        ıd_date = new javax.swing.JTextField();
        ıd_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hastane Yönetim Sistemi");
        setMinimumSize(new java.awt.Dimension(715, 510));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        w_pane.setBackground(new java.awt.Color(255, 255, 255));
        w_pane.setPreferredSize(new java.awt.Dimension(700, 500));
        w_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(100, 169, 68));
        jLabel1.setText("Hoşgeldiniz");
        w_pane.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 10, 270, 30));

        cıkısyap.setBackground(new java.awt.Color(100, 169, 68));
        cıkısyap.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        cıkısyap.setForeground(new java.awt.Color(255, 255, 255));
        cıkısyap.setText("Çıkış Yap");
        cıkısyap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cıkısyapActionPerformed(evt);
            }
        });
        w_pane.add(cıkısyap, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 10, 160, 30));

        w_whours.setBackground(new java.awt.Color(255, 255, 255));
        w_whours.setForeground(new java.awt.Color(100, 169, 68));
        w_whours.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        date_chooser.setBackground(new java.awt.Color(255, 255, 255));
        date_chooser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 169, 68)));
        date_chooser.setForeground(new java.awt.Color(0, 0, 0));
        date_chooser.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        date_chooser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                date_chooserMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                date_chooserMousePressed(evt);
            }
        });
        jPanel1.add(date_chooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, -1));

        select_time.setBackground(new java.awt.Color(255, 255, 255));
        select_time.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        select_time.setForeground(new java.awt.Color(0, 0, 0));
        select_time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00" }));
        select_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 169, 68)));
        select_time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select_timeMouseClicked(evt);
            }
        });
        jPanel1.add(select_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 80, 25));

        table_hours.setBackground(new java.awt.Color(0, 102, 102));
        table_hours.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        table_hours.setForeground(new java.awt.Color(0, 0, 0));
        table_hours.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tarih"
            }
        ) );
        table_hours.setCellSelectionEnabled(true);
        table_hours.setFocusable(false);
        table_hours.setGridColor(new java.awt.Color(153, 153, 0));
        table_hours.setShowGrid(false);
        table_hours.getTableHeader().setResizingAllowed(false);
        table_hours.getTableHeader().setReorderingAllowed(false);
        table_hours.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_hoursMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_hours);
        table_hours.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (table_hours.getColumnModel().getColumnCount() > 0) {
            table_hours.getColumnModel().getColumn(0).setResizable(false);
            table_hours.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 680, 340));

        date_error.setForeground(new java.awt.Color(255, 255, 255));
        date_error.setText("Bu kısım boş bırakılamaz");
        jPanel1.add(date_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, 15));

        btn_deleteHours.setBackground(new java.awt.Color(100, 169, 68));
        btn_deleteHours.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        btn_deleteHours.setForeground(new java.awt.Color(255, 255, 255));
        btn_deleteHours.setText("Sil");
        btn_deleteHours.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteHoursMouseClicked(evt);
            }
        });
        btn_deleteHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteHoursActionPerformed(evt);
            }
        });
        jPanel1.add(btn_deleteHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 80, 25));

        btn_addHours.setBackground(new java.awt.Color(100, 169, 68));
        btn_addHours.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        btn_addHours.setForeground(new java.awt.Color(255, 255, 255));
        btn_addHours.setText("Ekle");
        btn_addHours.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_addHoursMouseClicked(evt);
            }
        });
        btn_addHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addHoursActionPerformed(evt);
            }
        });
        jPanel1.add(btn_addHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 80, 25));

        ıd_date.setBackground(new java.awt.Color(255, 255, 255));
        ıd_date.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        ıd_date.setForeground(new java.awt.Color(0, 0, 0));
        ıd_date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 169, 68)));
        ıd_date.setEnabled(false);
        ıd_date.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ıd_dateMouseClicked(evt);
            }
        });
        ıd_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ıd_dateActionPerformed(evt);
            }
        });
        jPanel1.add(ıd_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 100, 25));

        ıd_label.setForeground(new java.awt.Color(255, 255, 255));
        ıd_label.setText("Lütfen geçerli bir doktor seçiniz");
        jPanel1.add(ıd_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 170, -1));

        w_whours.addTab("Çalışma Saatleri", jPanel1);

        w_pane.add(w_whours, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 710, 450));

        getContentPane().add(w_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cıkısyapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cıkısyapActionPerformed
        LogınGUI logınGUI = new LogınGUI();
        logınGUI.setVisible(true);
        dispose();
    }//GEN-LAST:event_cıkısyapActionPerformed

    private void btn_addHoursMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addHoursMouseClicked

    }//GEN-LAST:event_btn_addHoursMouseClicked

    private void btn_addHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addHoursActionPerformed


    }//GEN-LAST:event_btn_addHoursActionPerformed

    private void date_chooserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_chooserMouseClicked

    }//GEN-LAST:event_date_chooserMouseClicked

    private void date_chooserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_chooserMousePressed

    }//GEN-LAST:event_date_chooserMousePressed

    private void select_timeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_timeMouseClicked

    }//GEN-LAST:event_select_timeMouseClicked

    private void btn_deleteHoursMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteHoursMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteHoursMouseClicked

    private void btn_deleteHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteHoursActionPerformed
       
    }//GEN-LAST:event_btn_deleteHoursActionPerformed

    private void ıd_dateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ıd_dateMouseClicked
        ıd_date.setText(table_hours.getValueAt(table_hours.getSelectedRow(), 0).toString());
        ıd_label.setForeground(Color.WHITE);
    }//GEN-LAST:event_ıd_dateMouseClicked

    private void table_hoursMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_hoursMouseClicked
        ıd_date.setText(table_hours.getValueAt(table_hours.getSelectedRow(), 0).toString());
        ıd_label.setForeground(Color.WHITE);
    }//GEN-LAST:event_table_hoursMouseClicked

    private void ıd_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ıd_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ıd_dateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DoctorGUI(doctor).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DoctorGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void updateWhoursModel(Doctor doctor) throws SQLException {
        DefaultTableModel updatedTable = (DefaultTableModel) table_hours.getModel();
        updatedTable.setRowCount(0);
        for (int i = 0; i < doctor.getWhoursList(doctor.getId()).size(); i++) {
            whoursData[0] = doctor.getWhoursList(doctor.getId()).get(i).getId();
            whoursData[1] = doctor.getWhoursList(doctor.getId()).get(i).getWdate();
            whoursModel.addRow(whoursData);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_addHours;
    public javax.swing.JButton btn_deleteHours;
    private javax.swing.JButton cıkısyap;
    private com.toedter.calendar.JDateChooser date_chooser;
    private javax.swing.JLabel date_error;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> select_time;
    private javax.swing.JTable table_hours;
    private javax.swing.JPanel w_pane;
    private javax.swing.JTabbedPane w_whours;
    private javax.swing.JTextField ıd_date;
    private javax.swing.JLabel ıd_label;
    // End of variables declaration//GEN-END:variables
}
