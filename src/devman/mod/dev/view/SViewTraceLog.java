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
import java.util.ArrayList;
import sa.lib.SLibConsts;
import sa.lib.db.SDbConsts;
import sa.lib.grid.SGridColumnView;
import sa.lib.grid.SGridConsts;
import sa.lib.grid.SGridFilterValue;
import sa.lib.grid.SGridPaneSettings;
import sa.lib.grid.SGridPaneView;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;

/**
 *
 * @author Juan Barajas, Sergio Flores
 */
public class SViewTraceLog extends SGridPaneView {

    private SGridFilterPanelCompound moFilterProjectStage;
    private SGridFilterPanel moFilterType;
    
    public SViewTraceLog(SGuiClient client, String title) {
        super(client, SGridConsts.GRID_PANE_VIEW, SModConsts.D_TRC_LOG, SLibConsts.UNDEFINED, title);
        initComponentCustom();
    }
    
    private void initComponentCustom() {
        int[] projectStageKey = ((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey();
        
        setRowButtonsEnabled(false, true, true, false, true);
        
        moFilterProjectStage = new SGridFilterPanelCompound(miClient, this, SModConsts.PU_PRJ, SModConsts.PU_PRJ_STG);
        moFilterProjectStage.initFilter(projectStageKey);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterProjectStage);
        
        moFilterType = new SGridFilterPanel(miClient, this, SModConsts.PS_PRJ_PHS_TP, SModSysConsts.PS_PRJ_TP_DEV);
        moFilterType.initFilter(null);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterType);
    }

    @Override
    public void prepareSqlQuery() {
        String sql = "";
        Object filter = null;

        moPaneSettings = new SGridPaneSettings(3);
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
            sql += (sql.isEmpty() ? "" : "AND ") + "trc.fk_phs_prj = " + ((int[]) filter)[0] + " AND trc.fk_phs_stg = " + ((int[]) filter)[1] + " ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.PS_PRJ_PHS_TP)).getValue();
        if (filter != null && ((int[]) filter).length == 2) {
            sql += (sql.isEmpty() ? "" : "AND ") + "phs.fk_phs_tp_prj_tp = " + ((int[]) filter)[0] + " AND phs.fk_phs_tp_phs_tp = " + ((int[]) filter)[1] + " ";
        }

        msSql = "SELECT "
                + "v.id_prj AS " + SDbConsts.FIELD_ID + "1, "
                + "v.id_trc AS " + SDbConsts.FIELD_ID + "2, "
                + "v.id_log AS " + SDbConsts.FIELD_ID + "3, "
                + "'' AS " + SDbConsts.FIELD_CODE + ", "
                + "'' AS " + SDbConsts.FIELD_NAME + ", "
                + "v.note, "
                + "trc.fk_phs_prj, "
                + "trc.fk_phs_stg, "
                + "trc.fk_phs_phs, "
                + "p.code AS prj_code, "
                + "req.code AS req_code, "
                + "req.name AS req_name, "
                + "stg.code AS stg_code, "
                + "phs.code AS phs_code, "
                + "vt.code AS f_ref_tp, "
                + "doc.code AS doc_code, "
                + "doc.name AS doc_name, "
                + "cmp.code AS cmp_code, "
                + "cmp.name AS cmp_name, "
                + "ade.code AS ade_code, "
                + "ade.name AS ade_name, "
                + "tst.code AS tst_code, "
                + "tst.name AS tst_name, "
                + "v.b_del AS " + SDbConsts.FIELD_IS_DEL + ", "
                + "v.b_sys AS " + SDbConsts.FIELD_IS_SYS + ", "
                + "v.fk_usr_ins AS " + SDbConsts.FIELD_USER_INS_ID + ", "
                + "v.fk_usr_upd AS " + SDbConsts.FIELD_USER_UPD_ID + ", "
                + "v.ts_usr_ins AS " + SDbConsts.FIELD_USER_INS_TS + ", "
                + "v.ts_usr_upd AS " + SDbConsts.FIELD_USER_UPD_TS + ", "
                + "ui.name AS " + SDbConsts.FIELD_USER_INS_NAME + ", "
                + "uu.name AS " + SDbConsts.FIELD_USER_UPD_NAME + " "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.D_TRC_LOG) + " AS v "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.D_TRC) + " AS trc ON "
                + "v.id_prj = trc.id_prj AND v.id_trc = trc.id_trc "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ) + " AS p ON "
                + "v.id_prj = p.id_prj "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG) + " AS stg ON "
                + "trc.fk_phs_prj = stg.id_prj AND trc.fk_phs_stg = stg.id_stg "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG_PHS) + " AS phs ON "
                + "trc.fk_phs_prj = phs.id_prj AND trc.fk_phs_stg = phs.id_stg AND trc.fk_phs_phs = phs.id_phs "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.DU_REQ) + " AS req ON "
                + "trc.fk_req_prj = req.id_prj AND trc.fk_req_req = req.id_req "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.DS_TRC_REF_TP) + " AS vt ON "
                + "vt.id_ref_tp = v.fk_ref_tp "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS ui ON "
                + "v.fk_usr_ins = ui.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS uu ON "
                + "v.fk_usr_upd = uu.id_usr "
                + "LEFT OUTER JOIN " + SModConsts.TablesMap.get(SModConsts.DU_DOC) + " AS doc ON "
                + "v.fk_doc_prj_n = doc.id_prj AND v.fk_doc_stg_n = doc.id_stg AND v.fk_doc_doc_n = doc.id_doc "
                + "LEFT OUTER JOIN " + SModConsts.TablesMap.get(SModConsts.DU_CMP) + " AS cmp ON "
                + "v.fk_cmp_prj_n = cmp.id_prj AND v.fk_cmp_cmp_n = cmp.id_cmp "
                + "LEFT OUTER JOIN " + SModConsts.TablesMap.get(SModConsts.DU_ADE) + " AS ade ON "
                + "v.fk_ade_prj_n = ade.id_prj AND v.fk_ade_ade_n = ade.id_ade "
                + "LEFT OUTER JOIN " + SModConsts.TablesMap.get(SModConsts.D_TST_PLN_TST) + " AS tst ON "
                + "v.fk_tst_pln_n = tst.id_pln AND v.fk_tst_tst_n = tst.id_tst "
                + (sql.isEmpty() ? "" : "WHERE " + sql)
                + "ORDER BY prj_code, req_code, req_name, stg_code, phs_code, v.id_prj, v.id_trc, v.id_log, "
                + "doc_name, doc_code, cmp_name, cmp_code, ade_name, ade_code, tst_name, tst_code ";
    }

    @Override
    public ArrayList<SGridColumnView> createGridColumns() {
        ArrayList<SGridColumnView> columns = new ArrayList<SGridColumnView>();

        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "prj_code", "Proyecto"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "req_code", SGridConsts.COL_TITLE_CODE + " requerimiento"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_ITM_L, "req_name", SGridConsts.COL_TITLE_NAME + " requerimiento"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "stg_code", "Etapa"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "phs_code", "Fase"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "f_ref_tp", "Tipo referencia"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_ITM_S, "doc_name", SGridConsts.COL_TITLE_NAME + " documento"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "doc_code", SGridConsts.COL_TITLE_CODE + " documento"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_ITM_S, "ade_name", SGridConsts.COL_TITLE_NAME + " elemento A&D"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "ade_code", SGridConsts.COL_TITLE_CODE + " elemento A&D"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_ITM_S, "cmp_name", SGridConsts.COL_TITLE_NAME + " componente"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "cmp_code", SGridConsts.COL_TITLE_CODE + " componente"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_ITM_S, "tst_name", SGridConsts.COL_TITLE_NAME + " prueba"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "tst_code", SGridConsts.COL_TITLE_CODE + " prueba"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_CAT_M, "v.note", "Notas"));
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
        moSuscriptionsSet.add(SModConsts.DU_ADE);
        moSuscriptionsSet.add(SModConsts.DU_CMP);
        moSuscriptionsSet.add(SModConsts.DU_DOC);
        moSuscriptionsSet.add(SModConsts.D_TST_PLN);
        moSuscriptionsSet.add(SModConsts.D_TST_PLN_TST);
        moSuscriptionsSet.add(SModConsts.D_TRC);
        moSuscriptionsSet.add(SModConsts.DX_TRC_WZD);
    }
}
