/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.view;

import devman.SDevManConsts;
import devman.gui.SGuiMainSessionCustom;
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
 * @author Juan Barajas, Sergio Flores
 */
public class SViewTestPlanTestLog extends SGridPaneView {

    private SGridFilterPanelCompound moFilterProjectStage;
    private SGridFilterPanelUser moFilterUser;
    private SGridFilterDatePeriod moFilterDatePeriod;
    
    public SViewTestPlanTestLog(SGuiClient client, String title) {
        super(client, SGridConsts.GRID_PANE_VIEW, SModConsts.D_TST_PLN_TST_LOG, SLibConsts.UNDEFINED, title);
        initComponentCustom();
    }
    
    private void initComponentCustom() {
        int[] projectStageKey = ((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey();
        
        setRowButtonsEnabled(false, true, true, false, true);
        
        moFilterProjectStage = new SGridFilterPanelCompound(miClient, this, SModConsts.PU_PRJ, SModConsts.PU_PRJ_STG);
        moFilterProjectStage.initFilter(projectStageKey);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterProjectStage);
        
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
        SGridFilterValue filterValue = null;

        moPaneSettings = new SGridPaneSettings(3);
        moPaneSettings.setDeletedApplying(true);
        moPaneSettings.setSystemApplying(true);
        moPaneSettings.setUserInsertApplying(true);
        moPaneSettings.setUserUpdateApplying(true);

        filter = ((SGridFilterValue) moFiltersMap.get(SGridConsts.FILTER_DELETED)).getValue();
        if ((Boolean) filter) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.b_del = 0 ";
        }

        filterValue = (SGridFilterValue) moFiltersMap.get(SModConsts.PU_PRJ);
        if (filterValue != null) {
            filter = filterValue.getValue();
            if (filter != null && ((int[]) filter).length == 1) {
                sql += (sql.isEmpty() ? "" : "AND ") + "pln.fk_stg_prj = " + ((int[]) filter)[0] + " ";
            }
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.PU_PRJ_STG)).getValue();
        if (filter != null && ((int[]) filter).length == 2) {
            sql += (sql.isEmpty() ? "" : "AND ") + "pln.fk_stg_prj = " + ((int[]) filter)[0] + " AND pln.fk_stg_stg = " + ((int[]) filter)[1] + " ";
        }

        filterValue = (SGridFilterValue) moFiltersMap.get(SModConsts.CU_USR);
        if (filterValue != null) {
            filter = filterValue.getValue();
            if (filter != null && ((int[]) filter).length == 1) {
                sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_res_usr = " + ((int[]) filter)[0] + " ";
            }
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SGridConsts.FILTER_DATE_PERIOD)).getValue();
        if (filter != null) {
            sql += (sql.isEmpty() ? "" : "AND ") + SGridUtils.getSqlFilterDate("v.log_date", (SGuiDate) filter);
        }

        msSql = "SELECT "
                + "v.id_pln AS " + SDbConsts.FIELD_ID + "1, "
                + "v.id_tst AS " + SDbConsts.FIELD_ID + "2, "
                + "v.id_log AS " + SDbConsts.FIELD_ID + "3, "
                + "'' AS " + SDbConsts.FIELD_CODE + ", "
                + "'' AS " + SDbConsts.FIELD_NAME + ", "
                + "vp.code AS prj, "
                + "vps.code AS stg, "
                + "pln.code AS pln, "
                + "pln_tst.code AS pln_tst, "
                + "v.log_date, "
                + "v.log_time, "
                + "v.note, "
                + "vt.code AS res_tp, "
                + "v.b_del AS " + SDbConsts.FIELD_IS_DEL + ", "
                + "v.b_sys AS " + SDbConsts.FIELD_IS_SYS + ", "
                + "v.fk_usr_ins AS " + SDbConsts.FIELD_USER_INS_ID + ", "
                + "v.fk_usr_upd AS " + SDbConsts.FIELD_USER_UPD_ID + ", "
                + "v.ts_usr_ins AS " + SDbConsts.FIELD_USER_INS_TS + ", "
                + "v.ts_usr_upd AS " + SDbConsts.FIELD_USER_UPD_TS + ", "
                + "ur.name AS " + SDbConsts.FIELD_USER_USR_NAME + ", "
                + "ui.name AS " + SDbConsts.FIELD_USER_INS_NAME + ", "
                + "uu.name AS " + SDbConsts.FIELD_USER_UPD_NAME + " "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.D_TST_PLN_TST_LOG) + " AS v "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.D_TST_PLN_TST) + " AS pln_tst ON "
                + "v.id_pln = pln_tst.id_pln AND v.id_tst = pln_tst.id_tst "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.D_TST_PLN) + " AS pln ON "
                + "v.id_pln = pln.id_pln "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ) + " AS vp ON "
                + "pln.fk_stg_prj = vp.id_prj "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG) + " AS vps ON "
                + "pln.fk_stg_prj = vps.id_prj AND pln.fk_stg_stg = vps.id_stg "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.DS_TST_RES_TP) + " AS vt ON "
                + "v.fk_res_tp = vt.id_res_tp "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS ur ON "
                + "v.fk_res_usr = ur.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS ui ON "
                + "v.fk_usr_ins = ui.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS uu ON "
                + "v.fk_usr_upd = uu.id_usr "
                + (sql.isEmpty() ? "" : "WHERE " + sql)
                + "ORDER BY prj, stg, pln, pln_tst, pln.fk_stg_prj, pln.fk_stg_stg, v.id_pln, v.id_tst, v.id_log ";
    }

    @Override
    public ArrayList<SGridColumnView> createGridColumns() {
        SGridColumnView column = null;
        ArrayList<SGridColumnView> columns = new ArrayList<SGridColumnView>();

        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "prj", "Proyecto"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "pln", "Plan pruebas"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "pln_tst", "Prueba"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE, "v.log_date", SGridConsts.COL_TITLE_DATE));
        column = new SGridColumnView(SGridConsts.COL_TYPE_DEC_QTY, "v.log_time", "Esfuerzo (" + SDevManConsts.MAN_HRS + ")");
        column.setSumApplying(true);
        columns.add(column);
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "res_tp", SGridConsts.COL_TITLE_TYPE + " resultado"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_USR, SDbConsts.FIELD_USER_USR_NAME, "Recurso"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_CAT_L, "v.note", "Notas"));
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
        moSuscriptionsSet.add(SModConsts.PU_PRJ);
        moSuscriptionsSet.add(SModConsts.D_TST_PLN);
        moSuscriptionsSet.add(SModConsts.D_TST_PLN_TST);
        moSuscriptionsSet.add(SModConsts.CU_USR);
    }
}
