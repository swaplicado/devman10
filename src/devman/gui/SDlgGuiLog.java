/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.gui;

import devman.SDevManConsts;
import devman.mod.SModConsts;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Vector;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.grid.SGridColumnForm;
import sa.lib.grid.SGridConsts;
import sa.lib.grid.SGridPaneForm;
import sa.lib.grid.SGridRow;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanFormDialog;

/**
 *
 * @author Sergio Flores
 */
public class SDlgGuiLog extends SBeanFormDialog {
    
    private SGridPaneForm moGridLog;

    /**
     * Creates new form SDlgGuiLog
     */
    public SDlgGuiLog(SGuiClient client, int subtype, String title) {
        setFormSettings(client, SGuiConsts.BEAN_FORM_EDIT, SModConsts.XX_GUI_LOG, subtype, title);
        initComponents();
        initComponentsCustom();
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
        jpRegistry = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jlTaskCode = new javax.swing.JLabel();
        jtfTaskCode = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jlTaskName = new javax.swing.JLabel();
        jtfTaskName = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jlTime = new javax.swing.JLabel();
        moCompTime = new sa.lib.gui.bean.SBeanCompoundField();
        jpLog = new javax.swing.JPanel();

        setTitle("Bitácora");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jpRegistry.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpRegistry.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlTaskCode.setText("Código:");
        jlTaskCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlTaskCode);

        jtfTaskCode.setEditable(false);
        jtfTaskCode.setFocusable(false);
        jtfTaskCode.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel4.add(jtfTaskCode);

        jpRegistry.add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlTaskName.setText("Nombre:");
        jlTaskName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlTaskName);

        jtfTaskName.setEditable(false);
        jtfTaskName.setFocusable(false);
        jtfTaskName.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel5.add(jtfTaskName);

        jpRegistry.add(jPanel5);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlTime.setText("Tiempo total:");
        jlTime.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jlTime);

        moCompTime.setEditable(false);
        jPanel6.add(moCompTime);

        jpRegistry.add(jPanel6);

        jPanel1.add(jpRegistry, java.awt.BorderLayout.NORTH);

        jpLog.setBorder(javax.swing.BorderFactory.createTitledBorder("Entradas de la bitácora:"));
        jpLog.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jpLog, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel jlTaskCode;
    private javax.swing.JLabel jlTaskName;
    private javax.swing.JLabel jlTime;
    private javax.swing.JPanel jpLog;
    private javax.swing.JPanel jpRegistry;
    private javax.swing.JTextField jtfTaskCode;
    private javax.swing.JTextField jtfTaskName;
    private sa.lib.gui.bean.SBeanCompoundField moCompTime;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 960, 600);
        
        moFields.setFormButton(jbCancel);
        
        jbCancel.setText(SGuiConsts.TXT_BTN_CLOSE);
        jbSave.setText(SGuiConsts.TXT_BTN_OK);
        jbSave.setEnabled(false);
        
        moCompTime.setCompoundFieldSettings(miClient);
        moCompTime.getField().setDecimalSettings("", SGuiConsts.GUI_TYPE_DEC_QTY, false);
        moCompTime.setCompoundText(SDevManConsts.MAN_HRS);
        
        moGridLog = new SGridPaneForm(miClient, mnFormType, mnFormSubtype, msTitle) {
            
            @Override
            public void initGrid() {
                setRowButtonsEnabled(false);
            }
            
            @Override
            public ArrayList<SGridColumnForm> createGridColumns() {
                ArrayList<SGridColumnForm> columns = new ArrayList<>();
                
                columns.add(new SGridColumnForm(SGridConsts.COL_TYPE_DATE, SGridConsts.COL_TITLE_DATE));
                columns.add(new SGridColumnForm(SGridConsts.COL_TYPE_DEC_QTY, "Esfuerzo (" + SDevManConsts.MAN_HRS + ")"));
                columns.add(new SGridColumnForm(SGridConsts.COL_TYPE_TEXT_CODE_CAT, SGridConsts.COL_TITLE_TYPE));
                columns.add(new SGridColumnForm(SGridConsts.COL_TYPE_TEXT_CODE_CAT, SGridConsts.COL_TITLE_USER_USR_NAME));
                columns.add(new SGridColumnForm(SGridConsts.COL_TYPE_TEXT, "Notas", 250));
                columns.add(new SGridColumnForm(SGridConsts.COL_TYPE_TEXT_NAME_USR, SGridConsts.COL_TITLE_USER_INS_NAME));
                columns.add(new SGridColumnForm(SGridConsts.COL_TYPE_DATE_DATETIME, SGridConsts.COL_TITLE_USER_INS_TS));
                columns.add(new SGridColumnForm(SGridConsts.COL_TYPE_TEXT_NAME_USR, SGridConsts.COL_TITLE_USER_UPD_NAME));
                columns.add(new SGridColumnForm(SGridConsts.COL_TYPE_DATE_DATETIME, SGridConsts.COL_TITLE_USER_UPD_TS));
                
                return columns;
            }
        };
        
        jpLog.add(moGridLog, BorderLayout.CENTER);
        
        mvFormGrids.add(moGridLog);
    }
    
    /*
     * Private methods
     */
    
    /*
     * Public methods
     */
    
    public void setLog(SGuiLogMask log) {
        double time = 0;
        Vector<SGridRow> rows = new Vector<>();
        ArrayList<SGuiLogEntryMask> entries = null;
        
        jtfTaskCode.setText(log.getCode());
        jtfTaskCode.setCaretPosition(0);
        jtfTaskName.setText(log.getName());
        jtfTaskName.setCaretPosition(0);
        
        try {
            entries = log.getEntries(miClient.getSession());
            
            for (SGuiLogEntryMask entry : entries) {
                time += entry.getTime();
                rows.add((SGridRow) entry);
            }
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        
        moGridLog.populateGrid(rows);
        moCompTime.getField().setValue(time);
    }
    
    /*
     * Overriden methods
     */
    
    @Override
    public void addAllListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAllListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reloadCatalogues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRegistry(SDbRegistry registry) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SDbRegistry getRegistry() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SGuiValidation validateForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void resetForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setValue(final int type, final Object value) {
        switch (type) {
            case SModConsts.XX_GUI_LOG:
                setLog((SGuiLogMask) value);
                break;
            default:
        }
    }

    @Override
    public Object getValue(final int type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
