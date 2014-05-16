package windows;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import elements.Force;

public class Restraints extends javax.swing.JPanel {

    JDialog jFrame;
    DefaultListModel resultList;

    public Restraints(JDialog jFrame, boolean[] rest, ArrayList<Force> forces) {
        initComponents();
        this.jFrame = jFrame;
        this.jCheckBoxX.setSelected(rest[0]);
        this.jCheckBoxY.setSelected(rest[1]);
        this.jCheckBoxZ.setSelected(rest[2]);
        this.jCheckBoxRX.setSelected(rest[3]);
        this.jCheckBoxRY.setSelected(rest[4]);
        this.jCheckBoxRZ.setSelected(rest[5]);

        resultList = new DefaultListModel();
        jListForces.setModel(resultList);

        for (Force f : forces) {
            resultList.addElement(f);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxX = new javax.swing.JCheckBox();
        jCheckBoxY = new javax.swing.JCheckBox();
        jCheckBoxZ = new javax.swing.JCheckBox();
        jCheckBoxRX = new javax.swing.JCheckBox();
        jCheckBoxRY = new javax.swing.JCheckBox();
        jCheckBoxRZ = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboForceAxis = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListForces = new javax.swing.JList();
        jTextFieldForceValue = new javax.swing.JTextField();
        jButtonAddForce = new javax.swing.JButton();
        jButtonRemoveForce = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jCheckBoxX.setText("X");

        jCheckBoxY.setText("Y");

        jCheckBoxZ.setText("Z");

        jCheckBoxRX.setText("RX");

        jCheckBoxRY.setText("RY");

        jCheckBoxRZ.setText("RZ");

        jLabel1.setText("Select the wanted Restraints");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Forces"));

        jComboForceAxis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "X", "Y", "Z", "RX", "RY", "RZ" }));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(54, 100));

        jScrollPane1.setViewportView(jListForces);

        jTextFieldForceValue.setText("100");

        jButtonAddForce.setText("Add");
        jButtonAddForce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddForceActionPerformed(evt);
            }
        });

        jButtonRemoveForce.setText("Remove");
        jButtonRemoveForce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveForceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboForceAxis, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldForceValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAddForce)
                            .addComponent(jButtonRemoveForce, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboForceAxis, jTextFieldForceValue});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAddForce, jButtonRemoveForce});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboForceAxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldForceValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAddForce)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoveForce)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxX)
                                    .addComponent(jCheckBoxY))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(jCheckBoxZ)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBoxRY))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBoxRX)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBoxRZ))))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jCheckBoxRX, jCheckBoxRY, jCheckBoxRZ, jCheckBoxX, jCheckBoxY, jCheckBoxZ});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxX)
                            .addComponent(jCheckBoxZ)
                            .addComponent(jCheckBoxRY))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxY)
                            .addComponent(jCheckBoxRX)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jCheckBoxRZ)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.jFrame.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonAddForceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddForceActionPerformed
        String axis = (String) this.jComboForceAxis.getSelectedItem();
        int force = 0;
        try {
            force = Integer.parseInt(jTextFieldForceValue.getText());
        } catch (Exception e) {
            force = 0;
        }
        resultList.addElement(new Force(axis, force));
    }//GEN-LAST:event_jButtonAddForceActionPerformed

    private void jButtonRemoveForceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveForceActionPerformed
        int ind = jListForces.getSelectedIndex();
        resultList.remove(ind);
    }//GEN-LAST:event_jButtonRemoveForceActionPerformed

    public ArrayList<Force> getForces() {
        ArrayList<Force> forces = new ArrayList<>();
        for(Object o : resultList.toArray()){
            forces.add((Force) o);
        }
        return forces;
    }

    public boolean isX() {
        return jCheckBoxX.isSelected();
    }

    public boolean isY() {
        return jCheckBoxY.isSelected();
    }

    public boolean isZ() {
        return jCheckBoxZ.isSelected();
    }

    public boolean isRX() {
        return jCheckBoxRX.isSelected();
    }

    public boolean isRY() {
        return jCheckBoxRY.isSelected();
    }

    public boolean isRZ() {
        return jCheckBoxRZ.isSelected();
    }
    
    public boolean[] getRest(){
        boolean[] rest = new boolean[6];
        rest[0] = isX();
        rest[1] = isY();
        rest[2] = isZ();
        rest[3] = isRX();
        rest[4] = isRY();
        rest[5] = isRZ();
        return rest;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddForce;
    private javax.swing.JButton jButtonRemoveForce;
    private javax.swing.JCheckBox jCheckBoxRX;
    private javax.swing.JCheckBox jCheckBoxRY;
    private javax.swing.JCheckBox jCheckBoxRZ;
    private javax.swing.JCheckBox jCheckBoxX;
    private javax.swing.JCheckBox jCheckBoxY;
    private javax.swing.JCheckBox jCheckBoxZ;
    private javax.swing.JComboBox jComboForceAxis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jListForces;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldForceValue;
    // End of variables declaration//GEN-END:variables
}
