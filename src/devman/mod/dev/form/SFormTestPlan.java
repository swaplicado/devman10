/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.form;

import devman.gui.SGuiMainSessionCustom;
import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import devman.mod.dev.db.SDbTestPlan;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiFieldKeyGroup;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanForm;

/**
 *
 * @author Juan Barajas, Sergio Flores
 */
public class SFormTestPlan extends SBeanForm {

    private SDbTestPlan moRegistry;
    private SGuiFieldKeyGroup moKeyGroupStage;

    /**
     * Creates new form SFormTestPlan
     * @param client
     * @param title
     */
    public SFormTestPlan(SGuiClient client, String title) {
        setFormSettings(client, SGuiConsts.BEAN_FORM_EDIT, SModConsts.D_TST_PLN, SLibConsts.UNDEFINED, title);
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
        jPanel6 = new javax.swing.JPanel();
        jlProject = new javax.swing.JLabel();
        moKeyProject = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel14 = new javax.swing.JPanel();
        jlProjectStage = new javax.swing.JLabel();
        moKeyProjectStage = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel8 = new javax.swing.JPanel();
        jlType = new javax.swing.JLabel();
        moKeyType = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel3 = new javax.swing.JPanel();
        jlCode = new javax.swing.JLabel();
        moTextCode = new sa.lib.gui.bean.SBeanFieldText();
        jPanel4 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        moTextName = new sa.lib.gui.bean.SBeanFieldText();
        jPanel9 = new javax.swing.JPanel();
        jlStatus = new javax.swing.JLabel();
        moKeyStatus = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel10 = new javax.swing.JPanel();
        jlDocument = new javax.swing.JLabel();
        moKeyDocument = new sa.lib.gui.bean.SBeanFieldKey();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(7, 0, 0, 5));

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlProject.setForeground(java.awt.Color.blue);
        jlProject.setText("Proyecto:*");
        jlProject.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jlProject);

        moKeyProject.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel6.add(moKeyProject);

        jPanel2.add(jPanel6);

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlProjectStage.setForeground(java.awt.Color.blue);
        jlProjectStage.setText("Etapa:*");
        jlProjectStage.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel14.add(jlProjectStage);

        moKeyProjectStage.setPreferredSize(new java.awt.Dimension(400, 23));
        moKeyProjectStage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moKeyProjectStageActionPerformed(evt);
            }
        });
        jPanel14.add(moKeyProjectStage);

        jPanel2.add(jPanel14);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlType.setText("Tipo:*");
        jlType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlType);

        moKeyType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel8.add(moKeyType);

        jPanel2.add(jPanel8);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlCode.setText("Código:*");
        jlCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlCode);

        moTextCode.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel3.add(moTextCode);

        jPanel2.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlName.setText("Nombre:*");
        jlName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlName);

        moTextName.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel4.add(moTextName);

        jPanel2.add(jPanel4);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlStatus.setText("Estatus:*");
        jlStatus.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlStatus);

        moKeyStatus.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel9.add(moKeyStatus);

        jPanel2.add(jPanel9);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlDocument.setText("Documento:");
        jlDocument.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlDocument);

        moKeyDocument.setPreferredSize(new java.awt.Dimension(500, 23));
        jPanel10.add(moKeyDocument);

        jPanel2.add(jPanel10);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void moKeyProjectStageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moKeyProjectStageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moKeyProjectStageActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlDocument;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlProject;
    private javax.swing.JLabel jlProjectStage;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JLabel jlType;
    private sa.lib.gui.bean.SBeanFieldKey moKeyDocument;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProject;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProjectStage;
    private sa.lib.gui.bean.SBeanFieldKey moKeyStatus;
    private sa.lib.gui.bean.SBeanFieldKey moKeyType;
    private sa.lib.gui.bean.SBeanFieldText moTextCode;
    private sa.lib.gui.bean.SBeanFieldText moTextName;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 640, 400);

        moKeyProject.setKeySettings(miClient, SGuiUtils.getLabelName(jlProject), true);
        moKeyProjectStage.setKeySettings(miClient, SGuiUtils.getLabelName(jlProjectStage), true);
        moKeyType.setKeySettings(miClient, SGuiUtils.getLabelName(jlType), true);
        moTextCode.setTextSettings(SGuiUtils.getLabelName(jlCode), 20);
        moTextName.setTextSettings(SGuiUtils.getLabelName(jlName), 50);
        moKeyStatus.setKeySettings(miClient, SGuiUtils.getLabelName(jlStatus), true);
        moKeyDocument.setKeySettings(miClient, SGuiUtils.getLabelName(jlDocument), false);

        moFields.addField(moKeyProject);
        moFields.addField(moKeyProjectStage);
        moFields.addField(moKeyType);
        moFields.addField(moTextCode);
        moFields.addField(moTextName);
        moFields.addField(moKeyStatus);
        moFields.addField(moKeyDocument);

        moFields.setFormButton(jbSave);
        
        moKeyGroupStage = new SGuiFieldKeyGroup(miClient);
    }

    /*
     * Public methods
     */

    /*
     * Overriden methods
     */

    @Override
    public void addAllListeners() {

    }

    @Override
    public void removeAllListeners() {

    }

    @Override
    public void reloadCatalogues() {
        moKeyGroupStage.initGroup();
        moKeyGroupStage.addFieldKey(moKeyProject, SModConsts.PU_PRJ, SLibConsts.UNDEFINED, null);
        moKeyGroupStage.addFieldKey(moKeyProjectStage, SModConsts.PU_PRJ_STG, SLibConsts.UNDEFINED, null);
        moKeyGroupStage.addFieldKey(moKeyDocument, SModConsts.DU_DOC, SLibConsts.UNDEFINED, null);
        moKeyGroupStage.populateCatalogues();
        
        miClient.getSession().populateCatalogue(moKeyType, SModConsts.DS_TST_PLN_TP, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyStatus, SModConsts.CS_ST, SModConsts.D_TST_PLN, null);
    }

    @Override
    public void setRegistry(SDbRegistry registry) throws Exception {
        moRegistry = (SDbTestPlan) registry;

        mnFormResult = SLibConsts.UNDEFINED;
        mbFirstActivation = true;

        removeAllListeners();
        reloadCatalogues();

        if (moRegistry.isRegistryNew()) {
            //moRegistry.setCode("");
            
            if (moRegistry.getFkStageProjectId() == SLibConsts.UNDEFINED) {
                moRegistry.setFkStageProjectId(((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey()[0]);
                moRegistry.setFkStageStageId(((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey()[1]);
                moRegistry.setFkPlanStatusId(SModSysConsts.CS_ST_PND);
            }
            
            jtfRegistryKey.setText("");
        }
        else {
            jtfRegistryKey.setText(SLibUtils.textKey(moRegistry.getPrimaryKey()));
        }

        moKeyProject.setValue(new int[] { moRegistry.getFkStageProjectId() });
        moKeyProjectStage.setValue(new int[] { moRegistry.getFkStageProjectId(), moRegistry.getFkStageStageId() });
        moKeyType.setValue(new int[] { moRegistry.getFkPlanTypeId() });
        moTextCode.setValue(moRegistry.getCode());
        moTextName.setValue(moRegistry.getName());
        moKeyStatus.setValue(new int[] { moRegistry.getFkPlanStatusId() });
        moKeyDocument.setValue(new int[] { moRegistry.getFkDocumentProjectId_n(), moRegistry.getFkDocumentStageId_n(), moRegistry.getFkDocumentDocumentId_n() });

        setFormEditable(true);
        
        moTextCode.setEditable(false);
        
        if (moRegistry.isRegistryNew()) {
            if (moRegistry.getFkStageProjectId() == SLibConsts.UNDEFINED) {
                moKeyGroupStage.resetGroup();
            }
        }
        else {
            moKeyProject.setEnabled(false);
            moKeyProjectStage.setEnabled(false);
        }
        
        addAllListeners();
    }

    @Override
    public SDbTestPlan getRegistry() throws Exception {
        SDbTestPlan registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            registry.setFkStageProjectId(moKeyProjectStage.getValue()[0]);
            registry.setFkStageStageId(moKeyProjectStage.getValue()[1]);
        }

        registry.setCode(moTextCode.getValue());
        registry.setName(moTextName.getValue());
        registry.setFkPlanTypeId(moKeyType.getValue()[0]);
        registry.setFkPlanStatusId(moKeyStatus.getValue()[0]);
        registry.setFkDocumentProjectId_n(moKeyDocument.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyDocument.getValue()[0]);
        registry.setFkDocumentStageId_n(moKeyDocument.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyDocument.getValue()[1]);
        registry.setFkDocumentDocumentId_n(moKeyDocument.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyDocument.getValue()[2]);

        return registry;
    }

    @Override
    public SGuiValidation validateForm() {
        return moFields.validateFields();
    }
}