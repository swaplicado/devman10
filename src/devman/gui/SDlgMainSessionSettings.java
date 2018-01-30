/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.gui;

import devman.mod.SModConsts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import sa.lib.SLibConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiFieldKeyGroup;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanFieldKey;
import sa.lib.gui.bean.SBeanFormDialog;

/**
 *
 * @author Sergio Flores
 */
public class SDlgMainSessionSettings extends SBeanFormDialog implements ActionListener, ItemListener {
    
    private SGuiMainSessionCustom moMainSessionCustom;
    private SGuiFieldKeyGroup moKeyGroupStage;

    /**
     * Creates new form SDlgMainSessionSettings
     */
    public SDlgMainSessionSettings(SGuiClient client, String title) {
        setFormSettings(client, SGuiConsts.BEAN_FORM_EDIT, SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, title);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jlProyect = new javax.swing.JLabel();
        moKeyProject = new sa.lib.gui.bean.SBeanFieldKey();
        jbClearProject = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jlProyectStage = new javax.swing.JLabel();
        moKeyProjectStage = new sa.lib.gui.bean.SBeanFieldKey();
        jbClearProjectStage = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setTitle("Preferencias de la sesión");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Preferencias:"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlProyect.setText("Proyecto:");
        jlProyect.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlProyect);

        moKeyProject.setPreferredSize(new java.awt.Dimension(350, 23));
        jPanel4.add(moKeyProject);

        jbClearProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_clear.gif"))); // NOI18N
        jbClearProject.setToolTipText("Quitar filtro");
        jbClearProject.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel4.add(jbClearProject);

        jPanel2.add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlProyectStage.setText("Etapa:");
        jlProyectStage.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlProyectStage);

        moKeyProjectStage.setPreferredSize(new java.awt.Dimension(350, 23));
        jPanel5.add(moKeyProjectStage);

        jbClearProjectStage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_clear.gif"))); // NOI18N
        jbClearProjectStage.setToolTipText("Quitar filtro");
        jbClearProjectStage.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel5.add(jbClearProjectStage);

        jPanel2.add(jPanel5);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);
        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton jbClearProject;
    private javax.swing.JButton jbClearProjectStage;
    private javax.swing.JLabel jlProyect;
    private javax.swing.JLabel jlProyectStage;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProject;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProjectStage;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */
    
    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 560, 350);
        
        moKeyProject.setKeySettings(miClient, SGuiUtils.getLabelName(jlProyect), false);
        moKeyProjectStage.setKeySettings(miClient, SGuiUtils.getLabelName(jlProyectStage), false);
        
        moFields.addField(moKeyProject);
        moFields.addField(moKeyProjectStage);
        
        moFields.setFormButton(jbSave);
        
        jbSave.setText(SGuiConsts.TXT_BTN_OK);
        
        moKeyGroupStage = new SGuiFieldKeyGroup(miClient);
        
        jbClearProject.addActionListener(this);
        jbClearProjectStage.addActionListener(this);
        moKeyProject.addItemListener(this);
        moKeyProjectStage.addItemListener(this);
    }
    
    private void actionPerformedClearProject() {
        actionPerformedClearProjectStage();
        
        jbClearProject.setEnabled(false);
        
        if (moKeyProject.getSelectedIndex() > 0) {
            moKeyProject.setSelectedIndex(0);
            moKeyProject.requestFocus();
        }
    }
    
    private void actionPerformedClearProjectStage() {
        jbClearProjectStage.setEnabled(false);
        
        if (moKeyProjectStage.getSelectedIndex() > 0) {
            moKeyProjectStage.setSelectedIndex(0);
            moKeyProjectStage.requestFocus();
        }
    }
    
    private void itemStateChangedProject() {
        jbClearProject.setEnabled(moKeyProject.getSelectedIndex() > 0);
    }
    
    private void itemStateChangedProjectStage() {
        jbClearProjectStage.setEnabled(moKeyProjectStage.getSelectedIndex() > 0);
    }
    
    /*
     * Public methods
     */
    
    public void setMainSessionCustom(final SGuiMainSessionCustom mainSessionCustom) {
        moMainSessionCustom = mainSessionCustom;
        
        reloadCatalogues();
        moKeyProject.setValue(new int[] { moMainSessionCustom.getProjectStageKey()[0] });
        moKeyProjectStage.setValue(moMainSessionCustom.getProjectStageKey());
    }
    
    public SGuiMainSessionCustom getMainSessionCustom() {
        if (moKeyProjectStage.getSelectedIndex() > 0) {
            moMainSessionCustom.setProjectStageKey(moKeyProjectStage.getValue());
        }
        else if (moKeyProject.getSelectedIndex() > 0) {
            moMainSessionCustom.setProjectStageKey(new int[] { moKeyProject.getValue()[0], SLibConsts.UNDEFINED });
        }
        else {
            moMainSessionCustom.setProjectStageKey(new int[] { SLibConsts.UNDEFINED, SLibConsts.UNDEFINED });
        }
        
        return moMainSessionCustom;
    }
    
    /*
     * Protected methods
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
        moKeyGroupStage.initGroup();
        moKeyGroupStage.addFieldKey(moKeyProject, SModConsts.PU_PRJ, SLibConsts.UNDEFINED, null);
        moKeyGroupStage.addFieldKey(moKeyProjectStage, SModConsts.PU_PRJ_STG, SLibConsts.UNDEFINED, null);
        moKeyGroupStage.populateCatalogues();
        moKeyGroupStage.resetGroup();
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
        return moFields.validateFields();
    }
    
    @Override
    public void setValue(final int type, final Object value) {
        switch (type) {
            case SModConsts.MOD_CFG:
                setMainSessionCustom((SGuiMainSessionCustom) value);
                break;
            default:
        }
    }
    
    @Override
    public Object getValue(final int type) {
        Object value = null;
        
        switch (type) {
            case SModConsts.MOD_CFG:
                value = getMainSessionCustom();
                break;
            default:
        }
        
        return value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == jbClearProject) {
                actionPerformedClearProject();
            }
            else if (button == jbClearProjectStage) {
                actionPerformedClearProjectStage();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof SBeanFieldKey) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                SBeanFieldKey field = (SBeanFieldKey) e.getSource();

                if (field == moKeyProject) {
                    itemStateChangedProject();
                }
                else if (field == moKeyProjectStage) {
                    itemStateChangedProjectStage();
                }
            }
        }
    }
}