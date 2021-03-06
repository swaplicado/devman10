/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.form;

import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import devman.mod.dev.db.SDbRequirement;
import devman.mod.dev.db.SDbTrace;
import devman.mod.dev.db.SDbTraceLog;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiParams;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanFieldKey;
import sa.lib.gui.bean.SBeanForm;

/**
 *
 * @author Juan Barajas, Sergio Flores
 */
public class SFormTraceLog extends SBeanForm implements ItemListener {

    private SDbTraceLog moRegistry;
    
    private SDbTrace moTrace;
    private int mnProject;
    private int mnStage;
    private int mnTrace;

    /**
     * Creates new form SFormTraceLog
     * @param client
     * @param title
     */
    public SFormTraceLog(SGuiClient client, String title) {
        setFormSettings(client, SGuiConsts.BEAN_FORM_EDIT, SModConsts.D_TST_PLN_TST_LOG, SLibConsts.UNDEFINED, title);
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
        jPanel8 = new javax.swing.JPanel();
        jlProject = new javax.swing.JLabel();
        jtfProjectName = new javax.swing.JTextField();
        jtfProjectCode = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jlStage = new javax.swing.JLabel();
        jtfProjectStageName = new javax.swing.JTextField();
        jtfProjectStageCode = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jlPhase = new javax.swing.JLabel();
        jtfProjectStagePhaseName = new javax.swing.JTextField();
        jtfProjectStagePhaseCode = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jlRequirement = new javax.swing.JLabel();
        jtfRequirementName = new javax.swing.JTextField();
        jtfRequirementCode = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jlReferenceType = new javax.swing.JLabel();
        moKeyReferenceType = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel13 = new javax.swing.JPanel();
        jlDocumentStage = new javax.swing.JLabel();
        moKeyDocumentStage = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel11 = new javax.swing.JPanel();
        jlDocumentDocument = new javax.swing.JLabel();
        moKeyDocumentDocument = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel12 = new javax.swing.JPanel();
        jlComponent = new javax.swing.JLabel();
        moKeyComponent = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel18 = new javax.swing.JPanel();
        jlElement = new javax.swing.JLabel();
        moKeyElement = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel14 = new javax.swing.JPanel();
        jlTestPlan = new javax.swing.JLabel();
        moKeyTestPlan = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel15 = new javax.swing.JPanel();
        jlTestPlanTest = new javax.swing.JLabel();
        moKeyTestPlanTest = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel5 = new javax.swing.JPanel();
        jlNotes = new javax.swing.JLabel();
        moTextNotes = new sa.lib.gui.bean.SBeanFieldText();

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro de rastreo:"));
        jPanel2.setLayout(new java.awt.GridLayout(4, 0, 0, 5));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlProject.setText("Proyecto:");
        jlProject.setFocusable(false);
        jlProject.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlProject);

        jtfProjectName.setEditable(false);
        jtfProjectName.setFocusable(false);
        jtfProjectName.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel8.add(jtfProjectName);

        jtfProjectCode.setEditable(false);
        jtfProjectCode.setFocusable(false);
        jtfProjectCode.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel8.add(jtfProjectCode);

        jPanel2.add(jPanel8);

        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlStage.setText("Etapa:");
        jlStage.setFocusable(false);
        jlStage.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel16.add(jlStage);

        jtfProjectStageName.setEditable(false);
        jtfProjectStageName.setFocusable(false);
        jtfProjectStageName.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel16.add(jtfProjectStageName);

        jtfProjectStageCode.setEditable(false);
        jtfProjectStageCode.setFocusable(false);
        jtfProjectStageCode.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel16.add(jtfProjectStageCode);

        jPanel2.add(jPanel16);

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlPhase.setText("Fase:");
        jlPhase.setFocusable(false);
        jlPhase.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel17.add(jlPhase);

        jtfProjectStagePhaseName.setEditable(false);
        jtfProjectStagePhaseName.setFocusable(false);
        jtfProjectStagePhaseName.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel17.add(jtfProjectStagePhaseName);

        jtfProjectStagePhaseCode.setEditable(false);
        jtfProjectStagePhaseCode.setFocusable(false);
        jtfProjectStagePhaseCode.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel17.add(jtfProjectStagePhaseCode);

        jPanel2.add(jPanel17);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlRequirement.setText("Requerimiento:");
        jlRequirement.setFocusable(false);
        jlRequirement.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlRequirement);

        jtfRequirementName.setEditable(false);
        jtfRequirementName.setFocusable(false);
        jtfRequirementName.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel10.add(jtfRequirementName);

        jtfRequirementCode.setEditable(false);
        jtfRequirementCode.setFocusable(false);
        jtfRequirementCode.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel10.add(jtfRequirementCode);

        jPanel2.add(jPanel10);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(8, 1, 0, 5));

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlReferenceType.setText("Tipo referencia:*");
        jlReferenceType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlReferenceType);

        moKeyReferenceType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel9.add(moKeyReferenceType);

        jPanel4.add(jPanel9);

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlDocumentStage.setText("Etapa:");
        jlDocumentStage.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel13.add(jlDocumentStage);

        moKeyDocumentStage.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel13.add(moKeyDocumentStage);

        jPanel4.add(jPanel13);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlDocumentDocument.setText("Documento:");
        jlDocumentDocument.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel11.add(jlDocumentDocument);

        moKeyDocumentDocument.setPreferredSize(new java.awt.Dimension(500, 23));
        jPanel11.add(moKeyDocumentDocument);

        jPanel4.add(jPanel11);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlComponent.setText("Componente:");
        jlComponent.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel12.add(jlComponent);

        moKeyComponent.setPreferredSize(new java.awt.Dimension(400, 23));
        moKeyComponent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moKeyComponentActionPerformed(evt);
            }
        });
        jPanel12.add(moKeyComponent);

        jPanel4.add(jPanel12);

        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlElement.setText("Elemento A&D:");
        jlElement.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel18.add(jlElement);

        moKeyElement.setPreferredSize(new java.awt.Dimension(400, 23));
        moKeyElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moKeyElementActionPerformed(evt);
            }
        });
        jPanel18.add(moKeyElement);

        jPanel4.add(jPanel18);

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlTestPlan.setText("Plan pruebas:");
        jlTestPlan.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel14.add(jlTestPlan);

        moKeyTestPlan.setPreferredSize(new java.awt.Dimension(400, 23));
        moKeyTestPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moKeyTestPlanActionPerformed(evt);
            }
        });
        jPanel14.add(moKeyTestPlan);

        jPanel4.add(jPanel14);

        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlTestPlanTest.setText("Prueba:");
        jlTestPlanTest.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel15.add(jlTestPlanTest);

        moKeyTestPlanTest.setPreferredSize(new java.awt.Dimension(400, 23));
        moKeyTestPlanTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moKeyTestPlanTestActionPerformed(evt);
            }
        });
        jPanel15.add(moKeyTestPlanTest);

        jPanel4.add(jPanel15);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlNotes.setText("Notas:");
        jlNotes.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlNotes);

        moTextNotes.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel5.add(moTextNotes);

        jPanel4.add(jPanel5);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void moKeyComponentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moKeyComponentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moKeyComponentActionPerformed

    private void moKeyTestPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moKeyTestPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moKeyTestPlanActionPerformed

    private void moKeyTestPlanTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moKeyTestPlanTestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moKeyTestPlanTestActionPerformed

    private void moKeyElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moKeyElementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moKeyElementActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jlComponent;
    private javax.swing.JLabel jlDocumentDocument;
    private javax.swing.JLabel jlDocumentStage;
    private javax.swing.JLabel jlElement;
    private javax.swing.JLabel jlNotes;
    private javax.swing.JLabel jlPhase;
    private javax.swing.JLabel jlProject;
    private javax.swing.JLabel jlReferenceType;
    private javax.swing.JLabel jlRequirement;
    private javax.swing.JLabel jlStage;
    private javax.swing.JLabel jlTestPlan;
    private javax.swing.JLabel jlTestPlanTest;
    private javax.swing.JTextField jtfProjectCode;
    private javax.swing.JTextField jtfProjectName;
    private javax.swing.JTextField jtfProjectStageCode;
    private javax.swing.JTextField jtfProjectStageName;
    private javax.swing.JTextField jtfProjectStagePhaseCode;
    private javax.swing.JTextField jtfProjectStagePhaseName;
    private javax.swing.JTextField jtfRequirementCode;
    private javax.swing.JTextField jtfRequirementName;
    private sa.lib.gui.bean.SBeanFieldKey moKeyComponent;
    private sa.lib.gui.bean.SBeanFieldKey moKeyDocumentDocument;
    private sa.lib.gui.bean.SBeanFieldKey moKeyDocumentStage;
    private sa.lib.gui.bean.SBeanFieldKey moKeyElement;
    private sa.lib.gui.bean.SBeanFieldKey moKeyReferenceType;
    private sa.lib.gui.bean.SBeanFieldKey moKeyTestPlan;
    private sa.lib.gui.bean.SBeanFieldKey moKeyTestPlanTest;
    private sa.lib.gui.bean.SBeanFieldText moTextNotes;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 720, 450);

        moKeyReferenceType.setKeySettings(miClient, SGuiUtils.getLabelName(jlReferenceType), true);
        moKeyDocumentStage.setKeySettings(miClient, SGuiUtils.getLabelName(jlDocumentStage), true);
        moKeyDocumentDocument.setKeySettings(miClient, SGuiUtils.getLabelName(jlDocumentDocument), true);
        moKeyComponent.setKeySettings(miClient, SGuiUtils.getLabelName(jlComponent), true);
        moKeyElement.setKeySettings(miClient, SGuiUtils.getLabelName(jlElement), true);
        moKeyTestPlan.setKeySettings(miClient, SGuiUtils.getLabelName(jlTestPlan), true);
        moKeyTestPlanTest.setKeySettings(miClient, SGuiUtils.getLabelName(jlTestPlanTest), true);
        moTextNotes.setTextSettings(SGuiUtils.getLabelName(jlNotes), 255, 0);

        moFields.addField(moKeyReferenceType);
        moFields.addField(moKeyDocumentStage);
        moFields.addField(moKeyDocumentDocument);
        moFields.addField(moKeyComponent);
        moFields.addField(moKeyElement);
        moFields.addField(moKeyTestPlan);
        moFields.addField(moKeyTestPlanTest);
        moFields.addField(moTextNotes);

        moFields.setFormButton(jbSave);
    }
    
    private void renderTrace() {
        SDbRequirement requirement = null;
        
        if (mnProject != SLibConsts.UNDEFINED) {
            moTrace = (SDbTrace) miClient.getSession().readRegistry(SModConsts.D_TRC, new int[] { mnProject, mnTrace }, SDbConsts.MODE_VERBOSE);
            mnStage = moTrace.getFkPhaseStageId();
        }
        
        if (moTrace == null) {
            jtfProjectName.setText("");
            jtfProjectCode.setText("");
            jtfProjectStageName.setText("");
            jtfProjectStageCode.setText("");
            jtfProjectStagePhaseName.setText("");
            jtfProjectStagePhaseCode.setText("");
            jtfRequirementName.setText("");
            jtfRequirementCode.setText("");
        }
        else {
            requirement = (SDbRequirement) miClient.getSession().readRegistry(SModConsts.DU_REQ, new int[] { moTrace.getFkRequirementProjectId(), moTrace.getFkRequirementRequirementId() });
                    
            jtfProjectName.setText((String) miClient.getSession().readField(SModConsts.PU_PRJ, new int[] { moTrace.getPkProjectId() }, SDbRegistry.FIELD_NAME));
            jtfProjectName.setCaretPosition(0);
            jtfProjectCode.setText((String) miClient.getSession().readField(SModConsts.PU_PRJ, new int[] { moTrace.getPkProjectId() }, SDbRegistry.FIELD_CODE));
            jtfProjectCode.setCaretPosition(0);
            jtfProjectStageName.setText((String) miClient.getSession().readField(SModConsts.PU_PRJ_STG, new int[] { moTrace.getFkPhaseProjectId(), moTrace.getFkPhaseStageId() }, SDbRegistry.FIELD_NAME));
            jtfProjectStageName.setCaretPosition(0);
            jtfProjectStageCode.setText((String) miClient.getSession().readField(SModConsts.PU_PRJ_STG, new int[] { moTrace.getFkPhaseProjectId(), moTrace.getFkPhaseStageId() }, SDbRegistry.FIELD_CODE));
            jtfProjectStageCode.setCaretPosition(0);
            jtfProjectStagePhaseName.setText((String) miClient.getSession().readField(SModConsts.PU_PRJ_STG_PHS, new int[] { moTrace.getFkPhaseProjectId(), moTrace.getFkPhaseStageId(), moTrace.getFkPhasePhaseId() }, SDbRegistry.FIELD_NAME));
            jtfProjectStagePhaseName.setCaretPosition(0);
            jtfProjectStagePhaseCode.setText((String) miClient.getSession().readField(SModConsts.PU_PRJ_STG_PHS, new int[] { moTrace.getFkPhaseProjectId(), moTrace.getFkPhaseStageId(), moTrace.getFkPhasePhaseId() }, SDbRegistry.FIELD_CODE));
            jtfProjectStagePhaseCode.setCaretPosition(0);
            jtfRequirementName.setText(requirement.getName());
            jtfRequirementName.setCaretPosition(0);
            jtfRequirementCode.setText(requirement.getCode());
            jtfRequirementCode.setCaretPosition(0);
        }
    }
    
    private void setFieldsStatusByReferenceType() {
        moKeyDocumentStage.setEnabled(false);
        moKeyDocumentDocument.setEnabled(false);
        moKeyComponent.setEnabled(false);
        moKeyElement.setEnabled(false);
        moKeyTestPlan.setEnabled(false);
        moKeyTestPlanTest.setEnabled(false);
        
        if (moKeyReferenceType.getSelectedIndex() > 0) {
            switch (moKeyReferenceType.getValue()[0]) {
                case SModSysConsts.DS_TRC_REF_TP_DOC:
                    moKeyDocumentStage.setEnabled(true);
                    break;
                case SModSysConsts.DS_TRC_REF_TP_CMP:
                    moKeyComponent.setEnabled(true);
                    break;
                case SModSysConsts.DS_TRC_REF_TP_ADE:
                    moKeyElement.setEnabled(true);
                    break;
                case SModSysConsts.DS_TRC_REF_TP_TST:
                    moKeyTestPlan.setEnabled(true);
                    break;
                default:
            }
        }
    }
    
    private void setFieldsStatusByDocumentStage() {
        moKeyDocumentDocument.setEnabled(false);
        
        if (moKeyDocumentStage.getSelectedIndex() > 0) {
            moKeyDocumentDocument.setEnabled(true);
        }
    }
    
    private void setFieldsStatusByTestPlan() {
        moKeyTestPlanTest.setEnabled(false);
        
        if (moKeyTestPlan.getSelectedIndex() > 0) {
            moKeyTestPlanTest.setEnabled(true);
        }
    }
    
    private void itemStateChangedReferenceType() {
        moKeyDocumentStage.removeAllItems();
        moKeyDocumentDocument.removeAllItems();
        moKeyComponent.removeAllItems();
        moKeyElement.removeAllItems();
        moKeyTestPlan.removeAllItems();
        moKeyTestPlanTest.removeAllItems();
        
        if (moKeyReferenceType.getSelectedIndex() > 0) {
            switch (moKeyReferenceType.getValue()[0]) {
                case SModSysConsts.DS_TRC_REF_TP_DOC:
                    miClient.getSession().populateCatalogue(moKeyDocumentStage, SModConsts.PU_PRJ_STG, SLibConsts.UNDEFINED, new SGuiParams(new int[] { mnProject }));
                    break;
                case SModSysConsts.DS_TRC_REF_TP_CMP:
                    miClient.getSession().populateCatalogue(moKeyComponent, SModConsts.DU_CMP, SLibConsts.UNDEFINED, new SGuiParams(new int[] { mnProject }));
                    break;
                case SModSysConsts.DS_TRC_REF_TP_ADE:
                    miClient.getSession().populateCatalogue(moKeyElement, SModConsts.DU_ADE, SLibConsts.UNDEFINED, new SGuiParams(new int[] { mnProject }));
                    break;
                case SModSysConsts.DS_TRC_REF_TP_TST:
                    miClient.getSession().populateCatalogue(moKeyTestPlan, SModConsts.D_TST_PLN, SLibConsts.UNDEFINED, new SGuiParams(new int[] { mnProject, mnStage }));
                    break;
                default:
            }
        }
        setFieldsStatusByReferenceType();
    }
    
    private void itemStateChangedDocumentStage() {
        moKeyDocumentDocument.removeAllItems();
        
        if (moKeyDocumentStage.getSelectedIndex() > 0) {
            miClient.getSession().populateCatalogue(moKeyDocumentDocument, SModConsts.DU_DOC, SLibConsts.UNDEFINED, new SGuiParams(new int[] { moKeyDocumentStage.getValue()[0], moKeyDocumentStage.getValue()[1] }));
        }
        setFieldsStatusByDocumentStage();
    }
    
    private void itemStateChangedTestPlan() {
        moKeyTestPlanTest.removeAllItems();
        
        if (moKeyTestPlan.getSelectedIndex() > 0) {
            miClient.getSession().populateCatalogue(moKeyTestPlanTest, SModConsts.D_TST_PLN_TST, moKeyTestPlan.getValue()[0], null);
        }
        setFieldsStatusByTestPlan();
    }

    /*
     * Public methods
     */

    /*
     * Overriden methods
     */

    @Override
    public void addAllListeners() {
        moKeyReferenceType.addItemListener(this);
        moKeyDocumentStage.addItemListener(this);
        moKeyTestPlan.addItemListener(this);
    }

    @Override
    public void removeAllListeners() {
        moKeyReferenceType.removeItemListener(this);
        moKeyDocumentStage.removeItemListener(this);
        moKeyTestPlan.removeItemListener(this);
    }

    @Override
    public void reloadCatalogues() {
        miClient.getSession().populateCatalogue(moKeyReferenceType, SModConsts.DS_TRC_REF_TP, SLibConsts.UNDEFINED, null);
    }

    @Override
    public void setRegistry(SDbRegistry registry) throws Exception {
        moRegistry = (SDbTraceLog) registry;

        mnFormResult = SLibConsts.UNDEFINED;
        mbFirstActivation = true;
        moTrace = null;
        mnProject = 0;
        mnStage = 0;
        mnTrace = 0;

        removeAllListeners();
        reloadCatalogues();
        
        mnProject = moRegistry.getPkProjectId();
        mnTrace = moRegistry.getPkTraceId();

        if (moRegistry.isRegistryNew()) {
            //moRegistry.setCode("");
            
            jtfRegistryKey.setText("");
        }
        else {
            jtfRegistryKey.setText(SLibUtils.textKey(moRegistry.getPrimaryKey()));
        }

        renderTrace();
        
        moKeyReferenceType.setValue(new int[] { moRegistry.getFkReferenceTypeId() });
        itemStateChangedReferenceType();
        moKeyDocumentStage.setValue(new int[] { moRegistry.getFkDocumentProjectId_n(), moRegistry.getFkDocumentStageId_n() });
        itemStateChangedDocumentStage();
        moKeyDocumentDocument.setValue(new int[] { moRegistry.getFkDocumentProjectId_n(), moRegistry.getFkDocumentStageId_n(), moRegistry.getFkDocumentDocumentId_n() });
        moKeyComponent.setValue(new int[] { moRegistry.getFkComponentProjectId_n(), moRegistry.getFkComponentComponentId_n() });
        moKeyElement.setValue(new int[] { moRegistry.getFkElementProjectId_n(), moRegistry.getFkElementElementId_n()});
        moKeyTestPlan.setValue(new int[] { moRegistry.getFkTestPlanId_n() });
        itemStateChangedTestPlan();
        moKeyTestPlanTest.setValue(new int[] { moRegistry.getFkTestPlanId_n(), moRegistry.getFkTestTestId_n() });
        moTextNotes.setValue(moRegistry.getNotes());

        setFormEditable(true);
        
        setFieldsStatusByReferenceType();
        setFieldsStatusByDocumentStage();
        setFieldsStatusByTestPlan();
        
        if (moRegistry.isRegistryNew()) {
            
        }
        else {
            
        }
        
        addAllListeners();
    }

    @Override
    public SDbTraceLog getRegistry() throws Exception {
        SDbTraceLog registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            registry.setPkProjectId(mnProject);
            registry.setPkTraceId(mnTrace);
        }

        registry.setNotes(moTextNotes.getValue());
        registry.setFkReferenceTypeId(moKeyReferenceType.getValue()[0]);
        registry.setFkDocumentProjectId_n(!moKeyDocumentDocument.isEnabled() ? SLibConsts.UNDEFINED : moKeyDocumentDocument.getValue()[0]);
        registry.setFkDocumentStageId_n(!moKeyDocumentDocument.isEnabled() ? SLibConsts.UNDEFINED : moKeyDocumentDocument.getValue()[1]);
        registry.setFkDocumentDocumentId_n(!moKeyDocumentDocument.isEnabled() ? SLibConsts.UNDEFINED : moKeyDocumentDocument.getValue()[2]);
        registry.setFkComponentProjectId_n(!moKeyComponent.isEnabled() ? SLibConsts.UNDEFINED : moKeyComponent.getValue()[0]);
        registry.setFkComponentComponentId_n(!moKeyComponent.isEnabled() ? SLibConsts.UNDEFINED : moKeyComponent.getValue()[1]);
        registry.setFkElementProjectId_n(!moKeyElement.isEnabled() ? SLibConsts.UNDEFINED : moKeyElement.getValue()[0]);
        registry.setFkElementElementId_n(!moKeyElement.isEnabled() ? SLibConsts.UNDEFINED : moKeyElement.getValue()[1]);
        registry.setFkTestPlanId_n(!moKeyTestPlanTest.isEnabled() ? SLibConsts.UNDEFINED : moKeyTestPlanTest.getValue()[0]);
        registry.setFkTestTestId_n(!moKeyTestPlanTest.isEnabled() ? SLibConsts.UNDEFINED : moKeyTestPlanTest.getValue()[1]);

        return registry;
    }

    @Override
    public SGuiValidation validateForm() {
        return moFields.validateFields();
    }

    @Override
    public void setValue(final int type, final Object value) {
        switch (type) {
            case SModConsts.D_TRC:
                mnProject = ((int[]) value)[0];
                mnTrace = ((int[]) value)[1];
                
                renderTrace();
                break;
            default:
        }
    }

    @Override
    public Object getValue(final int type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof SBeanFieldKey) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                SBeanFieldKey field = (SBeanFieldKey) e.getSource();
                
                if (field == moKeyReferenceType) {
                    itemStateChangedReferenceType();
                }
                else if (field == moKeyDocumentStage) {
                    itemStateChangedDocumentStage();
                }
                else if (field == moKeyTestPlan) {
                    itemStateChangedTestPlan();
                }
            }
        }
    }
}
