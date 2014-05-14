package windows;

import windows.UBend;
import java.awt.Color;
import javax.swing.JToggleButton;
import pkginterface.Interaction;

public class DrawWindow extends javax.swing.JFrame {

    Interaction inter;

    public DrawWindow(Interaction inter) {
        initComponents();
        this.inter = inter;
        this.jPanel1.add(inter);
        this.jPanel1.setBackground(Color.white);
        this.requestFocus();
        this.setFocusable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public boolean isFocusTraversable() {
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToggleNewNode = new javax.swing.JToggleButton();
        jToggleNewEdge = new javax.swing.JToggleButton();
        jButtonUBend = new javax.swing.JButton();
        jButtonLine = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tube Design");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jToggleNewNode.setText("N");
        jToggleNewNode.setFocusable(false);

        jToggleNewEdge.setText("E");
        jToggleNewEdge.setFocusable(false);

        jButtonUBend.setText("U");
        jButtonUBend.setFocusable(false);
        jButtonUBend.setMaximumSize(new java.awt.Dimension(30, 30));
        jButtonUBend.setMinimumSize(new java.awt.Dimension(30, 30));
        jButtonUBend.setPreferredSize(new java.awt.Dimension(30, 30));
        jButtonUBend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUBendActionPerformed(evt);
            }
        });

        jButtonLine.setText("L");
        jButtonLine.setFocusable(false);
        jButtonLine.setMaximumSize(new java.awt.Dimension(30, 30));
        jButtonLine.setMinimumSize(new java.awt.Dimension(30, 30));
        jButtonLine.setPreferredSize(new java.awt.Dimension(30, 30));
        jButtonLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleNewNode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jToggleNewEdge, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButtonUBend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleNewNode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleNewEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUBend, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLine, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(293, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUBendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUBendActionPerformed
        UBend ub = new UBend(inter);
        ub.setLocationRelativeTo(null);
        ub.setVisible(true);
    }//GEN-LAST:event_jButtonUBendActionPerformed

    private void jButtonLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLineActionPerformed
        StraightLine sl = new StraightLine(inter);
        sl.setLocationRelativeTo(null);
        sl.setVisible(true);
    }//GEN-LAST:event_jButtonLineActionPerformed

    public JToggleButton getjToggleNewNode() {
        return jToggleNewNode;
    }

    public JToggleButton getjToggleNewEdge() {
        return jToggleNewEdge;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLine;
    private javax.swing.JButton jButtonUBend;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton jToggleNewEdge;
    private javax.swing.JToggleButton jToggleNewNode;
    // End of variables declaration//GEN-END:variables
}
