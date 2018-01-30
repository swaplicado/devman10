/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.view;

import devman.SDevManConsts;
import devman.gui.SGuiMainSessionCustom;
import devman.gui.grid.SGridFilterPanel;
import devman.gui.grid.SGridFilterPanelCompound;
import devman.gui.grid.SGridFilterPanelUser;
import devman.mod.SModConsts;
import java.util.ArrayList;
import sa.lib.SLibConsts;
import sa.lib.db.SDbConsts;
import sa.lib.grid.SGridColumnView;
import sa.lib.grid.SGridConsts;
import sa.lib.grid.SGridFilterDatePeriod;
import sa.lib.grid.SGridFilterValue;
import sa.lib.grid.SGridPaneSettings;
import sa.lib.grid.SGridPaneView;
import sa.lib.grid.SGridUtils;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiDate;

/**
 *
 * @author Sergio Flores
 */
public class SViewTaskLog extends SGridPaneView {

    private SGridFilterPanelCompound moFilterProjectStage;
    private SGridFilterPanel moFilterStatus;
    private SGridFilterPanelUser moFilterUser;
    private SGridFilterDatePeriod moFilterDatePeriod;

    public SViewTaskLog(SGuiClient client, String title) {
        super(client, SGridConsts.GRID_PANE_VIEW, SModConsts.P_TSK_LOG, SLibConsts.UNDEFINED, title);
        initComponentsCustom();
    }
    
    private void initComponentsCustom() {
        int[] projectStageKey = ((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey();
        
        setRowButtonsEnabled(false, true, true, false, true);
        
        moFilterProjectStage = new SGridFilterPanelCompound(miClient, this, SModConsts.PU_PRJ, SModConsts.PU_PRJ_STG);
        moFilterProjectStage.initFilter(projectStageKey);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterProjectStage);
        
        moFilterStatus = new SGridFilterPanel(miClient, this, SModConsts.CS_ST, SModConsts.P_TSK);
        moFilterStatus.initFilter(null);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterStatus);
        
        moFilterUser = new SGridFilterPanelUser(miClient, this);
        moFilterUser.initFilter(null);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterUser);
        
        moFilterDatePeriod = new SGridFilterDatePeriod(miClient, this, SGuiConsts.DATE_PICKER_DATE_PERIOD);
        moFilterDatePeriod.initFilter(new SGuiDate(SGuiConsts.GUI_DATE_MONTH, miClient.getSession().getCurrentDate().getTime()));
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterDatePeriod);
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
            sql += (sql.isEmpty() ? "" : "AND ") + "t.fk_act_prj = " + ((int[]) filter)[0] + " ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.PU_PRJ_STG)).getValue();
        if (filter != null && ((int[]) filter).length == 2) {
            sql += (sql.isEmpty() ? "" : "AND ") + "t.fk_act_prj = " + ((int[]) filter)[0] + " AND t.fk_act_stg = " + ((int[]) filter)[1] + " ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.CS_ST)).getValue();
        if (filter != null && ((int[]) filter).length == 1) {
            sql += (sql.isEmpty() ? "" : "AND ") + "t.fk_tsk_st = " + ((int[]) filter)[0] + " ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.CU_USR)).getValue();
        if (filter != null && ((int[]) filter).length == 1) {
            sql += (sql.isEmpty() ? "" : "AND ") + "t.fk_tsk_usr = " + ((int[]) filter)[0] + " ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SGridConsts.FILTER_DATE_PERIOD)).getValue();
        if (filter != null) {
            sql += (sql.isEmpty() ? "" : "AND ") + SGridUtils.getSqlFilterDate("v.log_date", (SGuiDate) filter);
        }
        
        msSql = "SELECT "
                + "v.id_tsk AS " + SDbConsts.FIELD_ID + "1, "
                + "v.id_log AS " + SDbConsts.FIELD_ID + "2, "
                + "t.code AS " + SDbConsts.FIELD_CODE + ", "
                + "t.name AS " + SDbConsts.FIELD_NAME + ", "
                + "v.log_date, "
                + "v.log_time, "
                + "v.b_del AS " + SDbConsts.FIELD_IS_DEL + ", "
                + "v.b_sys AS " + SDbConsts.FIELD_IS_SYS + ", "
                + "v.fk_usr_ins AS " + SDbConsts.FIELD_USER_INS_ID + ", "
                + "v.fk_usr_upd AS " + SDbConsts.FIELD_USER_UPD_ID + ", "
                + "v.ts_usr_ins AS " + SDbConsts.FIELD_USER_INS_TS + ", "
                + "v.ts_usr_upd AS " + SDbConsts.FIELD_USER_UPD_TS + ", "
                + "wt.code, "
                + "wt.name, "
                + "wu.name, "
                + "t.code, "
                + "t.name, "
                + "tt.code, "
                + "tt.name, "
                + "ts.code, "
                + "ts.name, "
                + "tu.name, "
                + "p.code, "
                + "p.name, "
                + "ps.code, "
                + "ps.name, "
                + "psp.code, "
                + "psp.name, "
                + "pspa.code, "
                + "pspa.name, "
                + "ui.name AS " + SDbConsts.FIELD_USER_INS_NAME + ", "
                + "uu.name AS " + SDbConsts.FIELD_USER_UPD_NAME + " "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.P_TSK_LOG) + " AS v "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PS_WRK_TP) + " AS wt ON "
                + "v.fk_wrk_tp = wt.id_wrk_tp "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS wu ON "
                + "v.fk_wrk_usr = wu.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.P_TSK) + " AS t ON "
                + "v.id_tsk = t.id_tsk "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PS_TSK_TP) + " AS tt ON "
                + "t.fk_tsk_tp = tt.id_tsk_tp "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CS_ST) + " AS ts ON "
                + "t.fk_tsk_st = ts.id_st "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS tu ON "
                + "t.fk_tsk_usr = tu.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ) + " AS p ON "
                + "t.fk_act_prj = p.id_prj "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG) + " AS ps ON "
                + "t.fk_act_prj = ps.id_prj AND t.fk_act_stg = ps.id_stg "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG_PHS) + " AS psp ON "
                + "t.fk_act_prj = psp.id_prj AND t.fk_act_stg = psp.id_stg AND t.fk_act_phs = psp.id_phs "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG_PHS_ACT) + " AS pspa ON "
                + "t.fk_act_prj = pspa.id_prj AND t.fk_act_stg = pspa.id_stg AND t.fk_act_phs = pspa.id_phs AND t.fk_act_act = pspa.id_act "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS ui ON "
                + "v.fk_usr_ins = ui.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS uu ON "
                + "v.fk_usr_upd = uu.id_usr "
                + (sql.isEmpty() ? "" : "WHERE " + sql)
                + "ORDER BY p.code, ps.code, psp.code, pspa.code, t.name, t.code, v.id_tsk, v.id_log ";
    }

    @Override
    public ArrayList<SGridColumnView> createGridColumns() {
        SGridColumnView column = null;
        ArrayList<SGridColumnView> columns = new ArrayList<SGridColumnView>();

        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "p.code", "Proyecto"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "ps.code", "Etapa"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "psp.code", "Fase"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "pspa.code", "Actividad"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_CAT_L, "t.name", SGridConsts.COL_TITLE_NAME));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "t.code", SGridConsts.COL_TITLE_CODE, 150));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "tt.code", SGridConsts.COL_TITLE_TYPE));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "ts.code", SGridConsts.COL_TITLE_STAT));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_USR, "tu.name", "Responsable"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE, "v.log_date", "Fecha"));
        column = new SGridColumnView(SGridConsts.COL_TYPE_DEC_QTY, "v.log_time", "Esfuerzo (" + SDevManConsts.MAN_HRS + ")");
        column.setSumApplying(true);
        columns.add(column);
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "wt.code", SGridConsts.COL_TITLE_TYPE + " trabajo"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_USR, "wu.name", "Recurso"));
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
        moSuscriptionsSet.add(SModConsts.PU_PRJ_STG_PHS_ACT);
        moSuscriptionsSet.add(SModConsts.P_TSK);
    }
}
