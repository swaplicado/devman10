/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.view;

import devman.gui.SGuiMainSessionCustom;
import devman.gui.grid.SGridFilterPanel;
import devman.gui.grid.SGridFilterPanelCompound;
import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import devman.mod.dev.db.SDbTrace;
import devman.mod.dev.db.SDevTraceWizard;
import devman.mod.dev.form.SDlgReportTraceLog;
import devman.mod.dev.form.SDlgTraceLog;
import devman.mod.dev.form.SDlgTraceWizard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.grid.SGridColumnView;
import sa.lib.grid.SGridConsts;
import sa.lib.grid.SGridFilterValue;
import sa.lib.grid.SGridPaneSettings;
import sa.lib.grid.SGridPaneView;
import sa.lib.grid.SGridRowView;
import sa.lib.grid.SGridUtils;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiParams;

/**
 *
 * @author Juan Barajas, Sergio Flores
 */
public class SViewTrace extends SGridPaneView implements ActionListener {

    private SDlgTraceWizard moDlgTraceWizard;
    private SDlgTraceLog moDlgTraceLog;
    private SGridFilterPanelCompound moFilterProjectStage;
    private SGridFilterPanel moFilterType;
    private JButton mjShowTraceWizard;
    private JButton mjAddTraceLog;
    private JButton mjViewTraceLog;
    private JButton mjPrintTraceLog;
    
    public SViewTrace(SGuiClient client, String title) {
        super(client, SGridConsts.GRID_PANE_VIEW, SModConsts.D_TRC, SLibConsts.UNDEFINED, title);
        initComponentCustom();
    }
    
    private void initComponentCustom() {
        int[] projectStageKey = ((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey();
        
        setRowButtonsEnabled(true, true, true, false, true);
        
        moDlgTraceWizard = new SDlgTraceWizard(miClient, "Asistente registro de rastreo");
        moDlgTraceLog = new SDlgTraceLog(miClient, "Bitácora");
        
        moFilterProjectStage = new SGridFilterPanelCompound(miClient, this, SModConsts.PU_PRJ, SModConsts.PU_PRJ_STG);
        moFilterProjectStage.initFilter(projectStageKey);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterProjectStage);
        
        moFilterType = new SGridFilterPanel(miClient, this, SModConsts.PS_PRJ_PHS_TP, SModSysConsts.PS_PRJ_TP_DEV);
        moFilterType.initFilter(null);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterType);
        
        mjShowTraceWizard = SGridUtils.createButton(new ImageIcon(getClass().getResource("/devman/gui/img/icon_std_wizard.gif")), "Mostrar asistente registro de rastreo", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjShowTraceWizard);
        
        mjAddTraceLog = SGridUtils.createButton(new ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_add.gif")), "Crear entrada de bitácora", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjAddTraceLog);
        
        mjViewTraceLog = SGridUtils.createButton(new ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_view.gif")), "Ver bitácora", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjViewTraceLog);
        
        mjPrintTraceLog = SGridUtils.createButton(new ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_print.gif")), "Imprimir registro de rastreo", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjPrintTraceLog);
    }

    private void actionShowTraceWizard() {
        SDevTraceWizard registry = null;
        
        if (mjShowTraceWizard.isEnabled()) {
            moDlgTraceWizard.resetForm();
            moDlgTraceWizard.setVisible(true);

            if (moDlgTraceWizard.getFormResult() == SGuiConsts.FORM_RESULT_OK) {
                try {
                    registry = moDlgTraceWizard.getRegistry();
                    if (miClient.getSession().saveRegistry(registry) == SDbConsts.SAVE_OK) {
                        miClient.getSession().notifySuscriptors(registry.getRegistryType());
                    }
                }
                catch (Exception e) {
                    SLibUtils.showException(this, e);
                }
            }
        }
    }

    private void actionAddTraceLog() {
        SGuiParams params = null;
        
        if (mjAddTraceLog.isEnabled()) {
            if (jtTable.getSelectedRowCount() != 1) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
            }
            else {
                SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

                if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                    miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                }
                else {
                    params = new SGuiParams();

                    params.getParamsMap().put(SModConsts.D_TRC, gridRow.getRowPrimaryKey());
                    miClient.getSession().showForm(SModConsts.D_TRC_LOG, SLibConsts.UNDEFINED, params);
                }
            }
        }
    }
    
    private void actionViewTraceLog() {
        if (mjViewTraceLog.isEnabled()) {
            if (jtTable.getSelectedRowCount() != 1) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
            }
            else {
                SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

                if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                    miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                }
                else {
                    SDbTrace trace = (SDbTrace) miClient.getSession().readRegistry(SModConsts.D_TRC, gridRow.getRowPrimaryKey());
                    
                    moDlgTraceLog.setValue(SModConsts.D_TRC, trace);
                    moDlgTraceLog.setVisible(true);
                }
            }
        }
    }
    
    private void actionPrintTraceLog() {
        int[] key = null;
        SDbTrace trace = null;
        
        if (mjPrintTraceLog.isEnabled()) {
            if (jtTable.getSelectedRowCount() != 1) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
            }
            else {
                SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

                trace = (SDbTrace) miClient.getSession().readRegistry(SModConsts.D_TRC, gridRow.getRowPrimaryKey());
                
                key = new int[4];
                
                key[0] = trace.getPkProjectId();
                key[1] = trace.getFkPhaseStageId();
                key[2] = trace.getFkPhasePhaseId();
                key[3] = trace.getFkRequirementRequirementId();
                
                if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                    miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                }
                else {
                    new SDlgReportTraceLog(miClient, key, "Registro de Rastreo").setVisible(true);
                }
            }
        }
    }

    @Override
    public void prepareSqlQuery() {
        String sql = "";
        Object filter = null;

        moPaneSettings = new SGridPaneSettings(2);
        moPaneSettings.setDeletedApplying(true);
        moPaneSettings.setSystemApplying(true);
        moPaneSettings.setUserInsertApplying(true);
        moPaneSettings.setUserUpdateApplying(true);

        filter = ((SGridFilterValue) moFiltersMap.get(SGridConsts.FILTER_DELETED)).getValue();
        if ((Boolean) filter) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.b_del = 0 ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.PU_PRJ)).getValue();
        if (filter != null && ((int[]) filter).length == 1) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.id_prj = " + ((int[]) filter)[0] + " ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.PU_PRJ_STG)).getValue();
        if (filter != null && ((int[]) filter).length == 2) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_phs_prj = " + ((int[]) filter)[0] + " AND v.fk_phs_stg = " + ((int[]) filter)[1] + " ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.PS_PRJ_PHS_TP)).getValue();
        if (filter != null && ((int[]) filter).length == 2) {
            sql += (sql.isEmpty() ? "" : "AND ") + "phs.fk_phs_tp_prj_tp = " + ((int[]) filter)[0] + " AND phs.fk_phs_tp_phs_tp = " + ((int[]) filter)[1] + " ";
        }

        msSql = "SELECT "
                + "v.id_prj AS " + SDbConsts.FIELD_ID + "1, "
                + "v.id_trc AS " + SDbConsts.FIELD_ID + "2, "
                + "'' AS " + SDbConsts.FIELD_CODE + ", "
                + "'' AS " + SDbConsts.FIELD_NAME + ", "
                + "v.trc_date, "
                + "v.note, "
                + "v.fk_phs_prj, "
                + "v.fk_phs_stg, "
                + "v.fk_phs_phs, "
                + "vp.code AS prj, "
                + "req.code AS req_code, "
                + "req.name AS req_name, "
                + "stg.code AS stg, "
                + "phs.code AS phs, "
                + "v.b_del AS " + SDbConsts.FIELD_IS_DEL + ", "
                + "v.b_sys AS " + SDbConsts.FIELD_IS_SYS + ", "
                + "v.fk_usr_ins AS " + SDbConsts.FIELD_USER_INS_ID + ", "
                + "v.fk_usr_upd AS " + SDbConsts.FIELD_USER_UPD_ID + ", "
                + "v.ts_usr_ins AS " + SDbConsts.FIELD_USER_INS_TS + ", "
                + "v.ts_usr_upd AS " + SDbConsts.FIELD_USER_UPD_TS + ", "
                + "ui.name AS " + SDbConsts.FIELD_USER_INS_NAME + ", "
                + "uu.name AS " + SDbConsts.FIELD_USER_UPD_NAME + ", "
                + "(SELECT COUNT(*) FROM " + SModConsts.TablesMap.get(SModConsts.D_TRC_LOG) + " AS vl WHERE vl.id_prj = v.id_prj AND vl.id_trc = v.id_trc AND vl.b_del = 0) AS f_logs "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.D_TRC) + " AS v "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.DU_REQ) + " AS req ON "
                + "v.fk_req_prj = req.id_prj AND v.fk_req_req = req.id_req "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG) + " AS stg ON "
                + "v.fk_phs_prj = stg.id_prj AND v.fk_phs_stg = stg.id_stg "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG_PHS) + " AS phs ON "
                + "v.fk_phs_prj = phs.id_prj AND v.fk_phs_stg = phs.id_stg AND v.fk_phs_phs = phs.id_phs "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ) + " AS vp ON "
                + "v.id_prj = vp.id_prj "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS ui ON "
                + "v.fk_usr_ins = ui.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS uu ON "
                + "v.fk_usr_upd = uu.id_usr "
                + (sql.isEmpty() ? "" : "WHERE " + sql)
                + "ORDER BY prj, req_code, req_name, stg, phs, v.id_prj, v.id_trc, v.trc_date ";
    }

    @Override
    public ArrayList<SGridColumnView> createGridColumns() {
        ArrayList<SGridColumnView> columns = new ArrayList<SGridColumnView>();

        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "prj", "Proyecto"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "req_code", SGridConsts.COL_TITLE_CODE + " requerimiento"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_ITM_L, "req_name", SGridConsts.COL_TITLE_NAME + " requerimiento"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "stg", "Etapa"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "phs", "Fase"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE, "v.trc_date", SGridConsts.COL_TITLE_DATE));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_CAT_M, "v.note", "Notas"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_INT_2B, "f_logs", "Entradas bitácora"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_BOOL_S, SDbConsts.FIELD_IS_DEL, SGridConsts.COL_TITLE_IS_DEL));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_BOOL_S, SDbConsts.FIELD_IS_SYS, SGridConsts.COL_TITLE_IS_SYS));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_USR, SDbConsts.FIELD_USER_INS_NAME, SGridConsts.COL_TITLE_USER_INS_NAME));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE_DATETIME, SDbConsts.FIELD_USER_INS_TS, SGridConsts.COL_TITLE_USER_INS_TS));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_USR, SDbConsts.FIELD_USER_UPD_NAME, SGridConsts.COL_TITLE_USER_UPD_NAME));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE_DATETIME, SDbConsts.FIELD_USER_UPD_TS, SGridConsts.COL_TITLE_USER_UPD_TS));

        return columns;
    }

    @Override
    public void defineSuscriptions() {
        moSuscriptionsSet.add(mnGridType);
        moSuscriptionsSet.add(SModConsts.CU_USR);
        moSuscriptionsSet.add(SModConsts.PU_PRJ);
        moSuscriptionsSet.add(SModConsts.PU_PRJ_STG);
        moSuscriptionsSet.add(SModConsts.PU_PRJ_STG_PHS);
        moSuscriptionsSet.add(SModConsts.DU_REQ);
        moSuscriptionsSet.add(SModConsts.D_TRC_LOG);
        moSuscriptionsSet.add(SModConsts.DX_TRC_WZD);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();

            if (button == mjShowTraceWizard) {
                actionShowTraceWizard();
            }
            else if (button == mjAddTraceLog) {
                actionAddTraceLog();
            }
            else if (button == mjViewTraceLog) {
                actionViewTraceLog();
            }
            else if (button == mjPrintTraceLog) {
                actionPrintTraceLog();
            }
        }
    }
}
