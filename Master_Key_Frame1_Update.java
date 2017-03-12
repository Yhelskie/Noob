
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jcarrierpigeon.WindowPosition;
import net.sf.jtelegraph.Telegraph;
import net.sf.jtelegraph.TelegraphQueue;
import net.sf.jtelegraph.TelegraphType;
import org.jdesktop.swingx.JXBusyLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yhel001
 */
public final class Master_Key_Frame1_Update extends javax.swing.JFrame {

    int time = 5000;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    JTextField pfield = new JTextField();

    public Master_Key_Frame1_Update() {
        conn = (Connection) JavaMySqlConnection.ConnectDb();
        initComponents();
        setIcon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        new_pass = new com.alee.laf.text.WebPasswordField();
        webButton1_login = new com.alee.laf.button.WebButton();
        webButton2_cancel = new com.alee.laf.button.WebButton();
        webCheckBox1 = new com.alee.laf.checkbox.WebCheckBox();
        webTextField1_oldpassword = new com.alee.laf.text.WebTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change Master Key");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        new_pass.setInputPrompt("Input New Master Key");
        new_pass.setInputPromptFont(new java.awt.Font("Yu Gothic UI", 3, 12)); // NOI18N
        new_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_passActionPerformed(evt);
            }
        });
        getContentPane().add(new_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 280, 40));

        webButton1_login.setText("GO ->");
        webButton1_login.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        webButton1_login.setMaximumSize(new java.awt.Dimension(50, 25));
        webButton1_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton1_loginActionPerformed(evt);
            }
        });
        getContentPane().add(webButton1_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 70, 33));

        webButton2_cancel.setText("CANCEL");
        webButton2_cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        webButton2_cancel.setMinimumSize(new java.awt.Dimension(50, 25));
        webButton2_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton2_cancelActionPerformed(evt);
            }
        });
        getContentPane().add(webButton2_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 70, 33));

        webCheckBox1.setText("Show Password");
        webCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(webCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 195, -1));

        webTextField1_oldpassword.setInputPrompt("Input Old Master Key");
        webTextField1_oldpassword.setInputPromptFont(new java.awt.Font("Yu Gothic UI", 3, 12)); // NOI18N
        getContentPane().add(webTextField1_oldpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 14, 280, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Admin_Login_BG.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 200));

        jTextField1.setText("jTextField1");
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(326, 229));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void webButton2_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton2_cancelActionPerformed
        System_Admin_and_User_Login saaul = new System_Admin_and_User_Login();
        saaul.setVisible(true);
        dispose();
        saaul.dispose();
    }//GEN-LAST:event_webButton2_cancelActionPerformed

    private void webButton1_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1_loginActionPerformed
        String sql = "Select * from admin";
        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                pfield.setText(this.rs.getString("master_key"));
                if (webTextField1_oldpassword.getText().equals(pfield.getText())) {
                    String SQL_U = "Update admin SET master_key = ?";
                    pst = (PreparedStatement) conn.prepareStatement(SQL_U);
                    pst.setString(1, new_pass.getText());
                    pst.executeUpdate();
                    Telegraph tele = new Telegraph("Message", "<html>"
                            + "<body style='color:red;font-size:13px'>"
                            + "<b>"
                            + "Password Changed Succesfully...</b>"
                            + "</body>"
                            + "</html>", TelegraphType.NOTIFICATION_DONE, WindowPosition.TOPRIGHT, 1200);
                    TelegraphQueue q = new TelegraphQueue();
                    q.add(tele);
                    System_Admin_and_User_Login saaul = new System_Admin_and_User_Login();
                    saaul.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Old Password Mismatch. . .", "Warning", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException sq) {
            sq.getErrorCode();
        }

    // String oldpass = webTextField1_oldpassword.getText();
        /*
         try {
         if (rs.next()) {
         
         } else {
         JOptionPane.showMessageDialog(null, "Password Mismatch", "Warning", JOptionPane.ERROR_MESSAGE);
         }
         }
         } catch (Exception ex) {
         ex.printStackTrace();

         }*/

        /* Telegraph tele = new Telegraph("Message", "<html><body style='color:red;font-size:13px'><b>"
         + "You have Successfully Login...</b></body></html>", TelegraphType.NOTIFICATION_INFO, WindowPosition.TOPRIGHT, 1200);
         TelegraphQueue q = new TelegraphQueue();
         q.add(tele);*/

    }//GEN-LAST:event_webButton1_loginActionPerformed

    private void new_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_passActionPerformed
        webButton1_login.doClick();
    }//GEN-LAST:event_new_passActionPerformed

    private void webCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webCheckBox1ActionPerformed
        if (webCheckBox1.isSelected()) {
            new_pass.setEchoChar((char) 0);
        } else {
            new_pass.setEchoChar('*');
        }
    }//GEN-LAST:event_webCheckBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Master_Key_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Master_Key_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Master_Key_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Master_Key_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Master_Key_Frame1_Update().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private com.alee.laf.text.WebPasswordField new_pass;
    private com.alee.laf.button.WebButton webButton1_login;
    private com.alee.laf.button.WebButton webButton2_cancel;
    private com.alee.laf.checkbox.WebCheckBox webCheckBox1;
    private com.alee.laf.text.WebTextField webTextField1_oldpassword;
    // End of variables declaration//GEN-END:variables
private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons/tatelogo.png")));
    }

}
