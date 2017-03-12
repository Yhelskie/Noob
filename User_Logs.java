
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Component;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yhel001
 */
public final class User_Logs extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    JTextField id = new JTextField();

    public User_Logs() {
        conn = (Connection) JavaMySqlConnection.ConnectDb();
        initComponents();
        webTable1_LOG_Table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        setIcon();
        ViewLogs();
        Table_Pane();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        webTable1_LOG_Table = new com.alee.laf.table.WebTable();
        webButton1_ref = new com.alee.laf.button.WebButton();
        jButton1_printLogs = new javax.swing.JButton();
        webButton1_dl_logs = new com.alee.laf.button.WebButton();
        webLabel1 = new com.alee.laf.label.WebLabel();

        setTitle("USER LOGS");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        webTable1_LOG_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        webTable1_LOG_Table.setEditable(false);
        webTable1_LOG_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                webTable1_LOG_TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(webTable1_LOG_Table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 700, 290));

        webButton1_ref.setText("Refresh");
        webButton1_ref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton1_refActionPerformed(evt);
            }
        });
        getContentPane().add(webButton1_ref, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 80, 30));

        jButton1_printLogs.setText("Print Logs");
        jButton1_printLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_printLogsActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1_printLogs, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, -1, -1));

        webButton1_dl_logs.setText("Download Logs");
        webButton1_dl_logs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton1_dl_logsActionPerformed(evt);
            }
        });
        getContentPane().add(webButton1_dl_logs, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, -1, 30));

        webLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Activity_Logs_png.png"))); // NOI18N
        getContentPane().add(webLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 420));

        setSize(new java.awt.Dimension(730, 458));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void webButton1_refActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1_refActionPerformed
        ViewLogs();
        Table_Pane();
    }//GEN-LAST:event_webButton1_refActionPerformed

    private void jButton1_printLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_printLogsActionPerformed
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("Users_Logs.jrxml");
            String sql = "select * from logs where id = '" + id.getText() + "'";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasperDesign.setQuery(newQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

            JasperViewer jview = new JasperViewer(jasperPrint, false, null);
            jview.setTitle("Report Viewer");
            //jview.setIconImage(new javax.swing.ImageIcon(getClass().getResource("tatelogo.png")).getImage());
            //jview.setSize(800, 600);
            //jview.setVisible(true);
            JasperViewer.viewReport(jasperPrint, false);
            System_Admin_and_User_Login saaul = new System_Admin_and_User_Login();
            saaul.getContentPane().add(new JRViewer(jasperPrint));
            saaul.pack();
            saaul.setVisible(true);

        } catch (Exception e) {

            //JOptionPane.showMessageDialog(null, e);
            //e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1_printLogsActionPerformed

    private void webTable1_LOG_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_webTable1_LOG_TableMouseClicked
        int row = webTable1_LOG_Table.getSelectedRow();
        String Table_Clickid = (webTable1_LOG_Table.getModel().getValueAt(row, 0).toString());
        id.setText(Table_Clickid);
    }//GEN-LAST:event_webTable1_LOG_TableMouseClicked

    private void webButton1_dl_logsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1_dl_logsActionPerformed

        try {
            File logs = new File("Logs.txt");
            FileWriter fw = new FileWriter(logs);
            fw.write(webTable1_LOG_Table.getModel().toString());
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(User_Logs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_webButton1_dl_logsActionPerformed

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

        /* Create and display the form */
        try {

            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");

        } catch (Exception ex) {

        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_Logs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1_printLogs;
    private javax.swing.JScrollPane jScrollPane1;
    private com.alee.laf.button.WebButton webButton1_dl_logs;
    private com.alee.laf.button.WebButton webButton1_ref;
    private com.alee.laf.label.WebLabel webLabel1;
    private com.alee.laf.table.WebTable webTable1_LOG_Table;
    // End of variables declaration//GEN-END:variables
private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons/tatelogo.png")));
    }

    public void Table_Pane() {

        for (int i = 0; i < webTable1_LOG_Table.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) webTable1_LOG_Table.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer = col.getHeaderRenderer();
            for (int r = 0; r < webTable1_LOG_Table.getRowCount(); r++) {
                renderer = webTable1_LOG_Table.getCellRenderer(r, i);
                Component comp = renderer.getTableCellRendererComponent(webTable1_LOG_Table, webTable1_LOG_Table.getValueAt(r, i),
                        false, false, r, i);
                width = Math.max(width, comp.getPreferredSize().width);
            }

            col.setPreferredWidth(width + 120);

        }
    }

    private void ViewLogs() {
        try {
            String sql = "select id as 'User ID', Personel_Users as 'Users', Activity as 'Activity', Date as 'Date and time of Activity'"
                    + " from logs where Personel_Users like '%Clerk Aide%' order by id desc";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            webTable1_LOG_Table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
