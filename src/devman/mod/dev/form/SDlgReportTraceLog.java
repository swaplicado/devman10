/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.form;

import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import sa.lib.SLibConsts;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiFieldKeyGroup;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanDialogReport;

/**
 *
 * @author Juan Barajas, Sergio Flores
 */
public class SDlgReportTraceLog extends SBeanDialogReport {

    private int[] moKey;
    private SGuiFieldKeyGroup moKeyGroupStagePhase;
    private SGuiFieldKeyGroup moKeyGroupRequirement;
    
    /**
     * Creates new form SDlgReportTraceLog
     * @param client
     * @param title
     */
    public SDlgReportTraceLog(SGuiClient client, int[] key, String title) {
        setFormSettings(client, SModConsts.DR_TRC, SLibConsts.UNDEFINED, title);
        moKey = key;
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
        moKeyProject = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel10 = new javax.swing.JPanel();
        jlProjectStage = new javax.swing.JLabel();
        moKeyProjectStage = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel12 = new javax.swing.JPanel();
        jlProjectStagePhase = new javax.swing.JLabel();
        moKeyProjectStagePhase = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel9 = new javax.swing.JPanel();
        jlProjectRequirement = new javax.swing.JLabel();
        moKeyProjectRequirement = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel6 = new javax.swing.JPanel();
        jlDate = new javax.swing.JLabel();
        moDateDate = new sa.lib.gui.bean.SBeanFieldDate();
        jPanel5 = new javax.swing.JPanel();
        jlSpot = new javax.swing.JLabel();
        moTextSpot = new sa.lib.gui.bean.SBeanFieldText();
        jPanel7 = new javax.swing.JPanel();
        jlVersion = new javax.swing.JLabel();
        moDecVersion = new sa.lib.gui.bean.SBeanFieldDecimal();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parámetros del reporte:"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(7, 1, 0, 5));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlProject.setText("Proyecto:*");
        jlProject.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlProject);

        moKeyProject.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel8.add(moKeyProject);

        jPanel2.add(jPanel8);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlProjectStage.setText("Etapa:");
        jlProjectStage.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlProjectStage);

        moKeyProjectStage.setPreferredSize(new java.awt.Dimension(400, 23));
        moKeyProjectStage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moKeyProjectStageActionPerformed(evt);
            }
        });
        jPanel10.add(moKeyProjectStage);

        jPanel2.add(jPanel10);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlProjectStagePhase.setText("Fase:");
        jlProjectStagePhase.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel12.add(jlProjectStagePhase);

        moKeyProjectStagePhase.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel12.add(moKeyProjectStagePhase);

        jPanel2.add(jPanel12);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlProjectRequirement.setText("Requerimiento:");
        jlProjectRequirement.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlProjectRequirement);

        moKeyProjectRequirement.setPreferredSize(new java.awt.Dimension(500, 23));
        jPanel9.add(moKeyProjectRequirement);

        jPanel2.add(jPanel9);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDate.setText("Fecha:*");
        jlDate.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jlDate);

        moDateDate.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(moDateDate);

        jPanel2.add(jPanel6);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlSpot.setText("Lugar:*");
        jlSpot.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlSpot);

        moTextSpot.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel5.add(moTextSpot);

        jPanel2.add(jPanel5);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlVersion.setText("Versión:*");
        jlVersion.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel7.add(jlVersion);
        jPanel7.add(moDecVersion);

        jPanel2.add(jPanel7);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void moKeyProjectStageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moKeyProjectStageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moKeyProjectStageActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jlDate;
    private javax.swing.JLabel jlProject;
    private javax.swing.JLabel jlProjectRequirement;
    private javax.swing.JLabel jlProjectStage;
    private javax.swing.JLabel jlProjectStagePhase;
    private javax.swing.JLabel jlSpot;
    private javax.swing.JLabel jlVersion;
    private sa.lib.gui.bean.SBeanFieldDate moDateDate;
    private sa.lib.gui.bean.SBeanFieldDecimal moDecVersion;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProject;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProjectRequirement;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProjectStage;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProjectStagePhase;
    private sa.lib.gui.bean.SBeanFieldText moTextSpot;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 640, 400);
        
        moKeyGroupStagePhase = new SGuiFieldKeyGroup(miClient);
        moKeyGroupRequirement = new SGuiFieldKeyGroup(miClient);

        moKeyProject.setKeySettings(miClient, SGuiUtils.getLabelName(jlProject), true);
        moKeyProjectStage.setKeySettings(miClient, SGuiUtils.getLabelName(jlProjectStage), false);
        moKeyProjectStagePhase.setKeySettings(miClient, SGuiUtils.getLabelName(jlProjectStagePhase), false);
        moKeyProjectRequirement.setKeySettings(miClient, SGuiUtils.getLabelName(jlProjectRequirement), false);
        moDateDate.setDateSettings(miClient, SGuiUtils.getLabelName(jlDate), true);
        moTextSpot.setTextSettings(SGuiUtils.getLabelName(jlSpot), 255);
        moTextSpot.setTextCaseType(SLibConsts.UNDEFINED);
        moDecVersion.setDecimalSettings(SGuiUtils.getLabelName(jlVersion), SGuiConsts.GUI_TYPE_DEC_AMT, true);

        moFields.addField(moKeyProject);
        moFields.addField(moKeyProjectStage);
        moFields.addField(moKeyProjectStagePhase);
        moFields.addField(moKeyProjectRequirement);
        moFields.addField(moDateDate);
        moFields.addField(moTextSpot);
        moFields.addField(moDecVersion);
        moFields.resetFields();
        
        moDateDate.setValue(miClient.getSession().getCurrentDate());

        setModal(false);
        
        reloadCatalogues();
        
        moKeyGroupStagePhase.resetGroup();
        moKeyGroupRequirement.resetGroup();
        
        if (moKey != null) {
            moKeyProject.setValue(new int[] { moKey[0] });
            moKeyProjectStage.setValue(new int[] { moKey[0], moKey[1] });
            moKeyProjectStagePhase.setValue(new int[] { moKey[0], moKey[1], moKey[2] });
            moKeyProjectRequirement.setValue(new int[] { moKey[0], moKey[3] });
        }
    }

    /*
     * Public methods
     */

    /*
     * Overriden methods
     */

    public void reloadCatalogues() {
        moKeyGroupStagePhase.initGroup();
        moKeyGroupStagePhase.addFieldKey(moKeyProject, SModConsts.PU_PRJ, SLibConsts.UNDEFINED, null);
        moKeyGroupStagePhase.addFieldKey(moKeyProjectStage, SModConsts.PU_PRJ_STG, SLibConsts.UNDEFINED, null);
        moKeyGroupStagePhase.addFieldKey(moKeyProjectStagePhase, SModConsts.PU_PRJ_STG_PHS, SLibConsts.UNDEFINED, null);
        moKeyGroupStagePhase.populateCatalogues();
        
        moKeyGroupRequirement.initGroup();
        moKeyGroupRequirement.addFieldKey(moKeyProject, SModConsts.PU_PRJ, SLibConsts.UNDEFINED, null);
        moKeyGroupRequirement.addFieldKey(moKeyProjectRequirement, SModConsts.DU_REQ, SLibConsts.UNDEFINED, null);
        moKeyGroupRequirement.populateCatalogues();
    }

    @Override
    public SGuiValidation validateForm() {
        return moFields.validateFields();
    }
    
    @Override
    public void createParamsMap() {
        String sqlWhere = "";
        
        moParamsMap = miClient.createReportParams();

        if (moKeyProjectStagePhase.getSelectedIndex() > 0) {
            sqlWhere = moKeyProjectStagePhase.getSelectedIndex() > 0 ? " WHERE f_id_phs_prj = " + moKeyProjectStagePhase.getValue()[0] + " AND f_id_phs_stg = " + moKeyProjectStagePhase.getValue()[1] + " AND f_id_phs_phs = " + moKeyProjectStagePhase.getValue()[2] + " " : "";
        }
        else if (moKeyProjectStage.getSelectedIndex() > 0) {
            sqlWhere = moKeyProjectStage.getSelectedIndex() > 0 ? " WHERE f_id_phs_prj = " + moKeyProjectStage.getValue()[0] + " AND f_id_phs_stg = " + moKeyProjectStage.getValue()[1] + " " : "";
        }
        sqlWhere += moKeyProjectRequirement.getSelectedIndex() > 0 ? (sqlWhere.isEmpty() ? "WHERE " : " AND ") + " f_id_req_prj = " + moKeyProjectRequirement.getValue()[0] + " AND f_id_req_req = " + moKeyProjectRequirement.getValue()[1] + " " : "";

        moParamsMap.put("sProjectName", moKeyProject.getSelectedItem().getItem());
        moParamsMap.put("sStage", moKeyProjectStage.getSelectedItem().getItem());
        moParamsMap.put("sVersion", moDecVersion.getValue());
        moParamsMap.put("sSpot", moTextSpot.getValue());
        moParamsMap.put("nProjectId", moKeyProject.getValue()[0]);
        moParamsMap.put("sSqlWhere", sqlWhere);
        moParamsMap.put("tDate", moDateDate.getValue());
        moParamsMap.put("nPhaseReq", SModSysConsts.PS_PRJ_PHS_TP_DEV_REQ[1]);
        moParamsMap.put("nPhaseAD", SModSysConsts.PS_PRJ_PHS_TP_DEV_ANA_DSG[1]);
        moParamsMap.put("nPhaseCon", SModSysConsts.PS_PRJ_PHS_TP_DEV_CON[1]);
        moParamsMap.put("nPhaseIT", SModSysConsts.PS_PRJ_PHS_TP_DEV_INT_TST[1]);
        moParamsMap.put("nDocTp", SModSysConsts.DS_DOC_TP_OTH);
        moParamsMap.put("nPlanUni", SModSysConsts.DS_TST_PLN_TP_UNT);
        moParamsMap.put("nPlanInt", SModSysConsts.DS_TST_PLN_TP_INT);
        moParamsMap.put("nPlanSys", SModSysConsts.DS_TST_PLN_TP_SYS);
        moParamsMap.put("nElementArq", SModSysConsts.DS_ADE_TP_ARQ);
        moParamsMap.put("nElementDet", SModSysConsts.DS_ADE_TP_DET);
        moParamsMap.put("nElementUi", SModSysConsts.DS_ADE_TP_UI);
    }
}