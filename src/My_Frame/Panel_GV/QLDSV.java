/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Frame.Panel_GV;

import My_Classes.DiemSinhVien;
import My_Classes.IServiceDiem;
import My_Classes.IServiceSV;
import My_Classes.ServiceDiemSV;
import My_Classes.ServiceSV;
import My_Classes.SinhVien;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ocean-tor
 */
public class QLDSV extends javax.swing.JPanel {

    private DefaultTableModel table;
    private DiemSinhVien diem = new DiemSinhVien();
    private IServiceDiem dao = new ServiceDiemSV();
    private SinhVien sv = new SinhVien();
    private IServiceSV daoSv = new ServiceSV();

    /**
     * Creates new form QLDSV
     */
    public QLDSV() {
        initComponents();
        initTable();
        fillTableN();
    }

    public void initTable() {
        table = (DefaultTableModel) tbl_bangDiem.getModel();
        table.setRowCount(0);
    }

    public void fillTableN() {
        initTable();
        for (DiemSinhVien o : dao.getAllDiemSinhVien()) {
            String avg = String.format("%.2f", o.getAVG());
            table.addRow(new Object[]{
                o.getIdSV(), o.getNameSV(), o.getMarkToan(), o.getMarkVan(), o.getMarkAnh(), avg, o.getRank()
            });
        }
    }

    public void clear() {
        txt_idDiem.setText("");
        txt_id.setText("");
        txt_name.setText("");
        txt_math.setText("");
        txt_lit.setText("");
        txt_eng.setText("");
        lbl_markTB.setText("0");
    }

    public boolean validMark() {
        if (txt_idDiem.getText().isEmpty()
                || txt_id.getText().isEmpty()
                || txt_name.getText().isEmpty()
                || txt_math.getText().isEmpty()
                || txt_lit.getText().isEmpty()
                || txt_eng.getText().isEmpty()) {
            return false;
        } else {
            try {
                int idMark = Integer.parseInt(txt_idDiem.getText());
                double maths = Double.parseDouble(txt_math.getText());
                double lit = Double.parseDouble(txt_lit.getText());
                double eng = Double.parseDouble(txt_eng.getText());
                if (idMark < 0) {
                    return false;
                } else if (maths < 0 || maths > 10) {
                    JOptionPane.showMessageDialog(this, "MARK FROM 0 TO 10");
                    return false;
                } else if (lit < 0 || lit > 10) {
                    JOptionPane.showMessageDialog(this, "MARK FROM 0 TO 10");
                    return false;
                } else if (eng < 0 || eng > 10) {
                    JOptionPane.showMessageDialog(this, "MARK FROM 0 TO 10");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    public void setModel(DiemSinhVien diem) {
        txt_id.setText(String.valueOf(diem.getIdSV()));
        txt_idDiem.setText(String.valueOf(diem.getIdDiem()));
        txt_name.setText(String.valueOf(diem.getNameSV()));
        txt_math.setText(String.valueOf(diem.getMarkAnh()));
        txt_lit.setText(String.valueOf(diem.getMarkVan()));
        txt_eng.setText(String.valueOf(diem.getMarkAnh()));
        String avg = String.format("%.2f", diem.getAVG());
        lbl_markTB.setText(avg);
    }

    public DiemSinhVien getModal() {
        diem = new DiemSinhVien();
        diem.setIdDiem(Integer.parseInt(txt_idDiem.getText()));
        diem.setIdSV(txt_id.getText());
        diem.setNameSV(txt_name.getText());
        diem.setMarkToan(Double.parseDouble(txt_math.getText()));
        diem.setMarkVan(Double.parseDouble(txt_lit.getText()));
        diem.setMarkAnh(Double.parseDouble(txt_eng.getText()));
        return diem;
    }

    public void remove() throws SQLException {
        if (txt_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID STUDENT NOT EXITS");
            txt_id.setBackground(Color.red);
            return;
        } else {
            int choose = JOptionPane.showConfirmDialog(this, "ARE YOU SURE DELETE STUDENT " + txt_id.getText(), "THÔNG BÁO", JOptionPane.YES_NO_OPTION);
            if (choose == JOptionPane.YES_OPTION) {
                dao.removeMark(txt_id.getText());
                JOptionPane.showMessageDialog(this, "DELETET SUCCESSFUL");
            }
        }
    }

    public void find() throws SQLException {
        if (txt_search.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID STUDENT NOT EXITS");
            txt_search.setBackground(Color.red);
            return;
        } else {
            diem = dao.findMark(txt_search.getText());
            if (diem != null) {
                txt_idDiem.setText(diem.getIdDiem() + "");
                txt_name.setText(diem.getNameSV());
                txt_id.setText(diem.getIdSV());
                txt_math.setText(diem.getMarkToan() + "");
                txt_lit.setText(diem.getMarkVan() + "");
                txt_eng.setText(diem.getMarkAnh() + "");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_math = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_lit = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_eng = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        btn_remove = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_idDiem = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bangDiem = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_markTB = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(30, 139, 195));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("TÌM KIẾM");

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/My_Images/search-icon.png"))); // NOI18N
        btn_search.setContentAreaFilled(false);
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(35, 35, 35)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(342, 342, 342))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_search, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ID SINH VIÊN ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 121, -1, -1));

        txt_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_idFocusLost(evt);
            }
        });
        jPanel2.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 142, 279, 27));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("TOÁN");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        txt_math.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_mathFocusLost(evt);
            }
        });
        jPanel2.add(txt_math, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 201, 279, 27));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("NGỮ VĂN");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 239, -1, -1));

        txt_lit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_mathFocusLost(evt);
            }
        });
        jPanel2.add(txt_lit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 279, 27));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("ANH VĂN");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 298, -1, -1));

        txt_eng.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_mathFocusLost(evt);
            }
        });
        jPanel2.add(txt_eng, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 319, 279, 27));

        btn_add.setBackground(new java.awt.Color(204, 204, 204));
        btn_add.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_add.setText("THÊM ĐIỂM");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        jPanel2.add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 371, 180, 35));

        btn_remove.setBackground(new java.awt.Color(204, 204, 204));
        btn_remove.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_remove.setText("XÓA ĐIỂM");
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });
        jPanel2.add(btn_remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 412, 180, 35));

        btn_update.setBackground(new java.awt.Color(248, 148, 6));
        btn_update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("CẬP NHẬT ĐIỂM");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        jPanel2.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 458, 180, 35));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID ĐIỂM ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        txt_idDiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_idDiemFocusLost(evt);
            }
        });
        jPanel2.add(txt_idDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 32, 279, 27));

        txt_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nameFocusLost(evt);
            }
        });
        jPanel2.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 88, 279, 27));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("HỌ TÊN ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, -1));

        tbl_bangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FULL NAME", "ĐIỂM TOÁN", "ĐIỂM NGỮ VĂN", "ĐIỂM ANH VĂN", "ĐIỂM TRUNG BÌNH", "XẾP LOẠI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_bangDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bangDiemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bangDiem);

        jPanel3.setBackground(new java.awt.Color(51, 204, 0));

        jPanel4.setBackground(new java.awt.Color(0, 255, 102));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ĐIỂM TRUNG BÌNH");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(75, 75, 75))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        lbl_markTB.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbl_markTB.setForeground(new java.awt.Color(255, 255, 255));
        lbl_markTB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(lbl_markTB, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_markTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        try {
            if (validMark()) {
                diem = getModal();
                if (dao.addMark(diem)) {
                    fillTableN();
                    clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        // TODO add your handling code here:
        try {
            remove();
            fillTableN();
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_removeActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        try {
            if (validMark()) {
                diem = getModal();
                if (dao.updateMark(diem)) {
                    fillTableN();
                    clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        try {
            find();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void tbl_bangDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bangDiemMouseClicked
        // TODO add your handling code here:
        table = (DefaultTableModel) tbl_bangDiem.getModel();
        int index = tbl_bangDiem.getSelectedRow();

        txt_id.setText(table.getValueAt(index, 0).toString());
        txt_name.setText(table.getValueAt(index, 1).toString());
        txt_math.setText(table.getValueAt(index, 2).toString());
        txt_lit.setText(table.getValueAt(index, 3).toString());
        txt_eng.setText(table.getValueAt(index, 4).toString());
        lbl_markTB.setText(table.getValueAt(index, 5).toString());
    }//GEN-LAST:event_tbl_bangDiemMouseClicked

    private void txt_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameFocusLost

    private void txt_idDiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idDiemFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idDiemFocusLost

    private void txt_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idFocusLost
        // TODO add your handling code here:
        try {
            diem = dao.findMark(txt_id.getText());
            sv = daoSv.findSV(txt_id.getText());
            if (diem != null || sv != null) {
                txt_idDiem.setText(diem.getIdDiem() + "");
                txt_name.setText(sv.getNameSV());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt_idFocusLost

    private void txt_mathFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mathFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_mathFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_remove;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_markTB;
    private javax.swing.JTable tbl_bangDiem;
    private javax.swing.JTextField txt_eng;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_idDiem;
    private javax.swing.JTextField txt_lit;
    private javax.swing.JTextField txt_math;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
