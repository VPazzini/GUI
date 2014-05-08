
package pkginterface;

import javax.swing.JDialog;

public class Restraints extends javax.swing.JPanel {
    
    JDialog jFrame;
    
    public Restraints(JDialog jFrame) {
        initComponents();
        this.jFrame = jFrame;
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
        jButton1 = new javax.swing.JButton();

        jCheckBoxX.setText("X");

        jCheckBoxY.setText("Y");

        jCheckBoxZ.setText("Z");

        jCheckBoxRX.setText("RX");

        jCheckBoxRY.setText("RY");

        jCheckBoxRZ.setText("RZ");

        jLabel1.setText("Select the wanted Restraints");

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.jFrame.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBoxRX;
    private javax.swing.JCheckBox jCheckBoxRY;
    private javax.swing.JCheckBox jCheckBoxRZ;
    private javax.swing.JCheckBox jCheckBoxX;
    private javax.swing.JCheckBox jCheckBoxY;
    private javax.swing.JCheckBox jCheckBoxZ;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
