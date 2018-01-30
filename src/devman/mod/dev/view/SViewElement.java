/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.view;

import devman.gui.SGuiMainSessionCustom;
import devman.gui.grid.SGridFilterPanel;
import devman.mod.SModConsts;
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
public class SViewElement extends SGridPaneView {

    private SGridFilterPanel moFilterProject;
    private SGridFilterPanel moFilterType;
    
    public SViewElement(SGuiClient client, String title) {
        super(client, SGridConsts.GRID_PANE_VIEW, SModConsts.DU_ADE, SLibConsts.UNDEFINED, title);
        initComponentCustom();
    }
    
    private void initComponentCustom() {
        int[] projectStageKey = ((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey();
        
        setRowButtonsEnabled(true, true, true, false, true);
        
        moFilterProject = new SGridFilterPanel(miClient, this, SModConsts.PU_PRJ, SLibConsts.UNDEFINED);
        moFilterProject.initFilter(projectStageKey);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterProject);
        
        moFilterType = new SGridFilterPanel(miClient, this, SModConsts.DS_ADE_TP, SLibConsts.UNDEFINED);
        moFilterType.initFilter(null);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterType);
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

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.DS_ADE_TP)).getValue();
        if (filter != null && ((int[]) filter).length == 1) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_ade_tp = " + ((int[]) filter)[0] + " ";
        }

        msSql = "SELECT "
                + "v.id_prj AS " + SDbConsts.FIELD_ID + "1, "
                + "v.id_ade AS " + SDbConsts.FIELD_ID + "2, "
                + "v.code AS " + SDbConsts.FIELD_CODE + ", "
                + "v.name AS " + SDbConsts.FIELD_NAME + ", "
                + "vp.code AS f_prj, "
                + "v.dcrp, "
                + "vt.code AS f_type, "
                + "v.b_del AS " + SDbConsts.FIELD_IS_DEL + ", "
                + "v.b_sys AS " + SDbConsts.FIELD_IS_SYS + ", "
                + "v.fk_usr_ins AS " + SDbConsts.FIELD_USER_INS_ID + ", "
                + "v.fk_usr_upd AS " + SDbConsts.FIELD_USER_UPD_ID + ", "
                + "v.ts_usr_ins AS " + SDbConsts.FIELD_USER_INS_TS + ", "
                + "v.ts_usr_upd AS " + SDbConsts.FIELD_USER_UPD_TS + ", "
                + "ui.name AS " + SDbConsts.FIELD_USER_INS_NAME + ", "
                + "uu.name AS " + SDbConsts.FIELD_USER_UPD_NAME + " "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.DU_ADE) + " AS v "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ) + " AS vp ON "
                + "v.id_prj = vp.id_prj "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.DS_ADE_TP) + " AS vt ON "
                + "v.fk_ade_tp = vt.id_ade_tp "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS ui ON "
                + "v.fk_usr_ins = ui.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS uu ON "
                + "v.fk_usr_upd = uu.id_usr "
                + (sql.isEmpty() ? "" : "WHERE " + sql)
                + "ORDER BY f_prj, v.name, v.id_prj, v.id_ade ";
    }

    @Override
    public ArrayList<SGridColumnView> createGridColumns() {
        ArrayList<SGridColumnView> columns = new ArrayList<SGridColumnView>();

        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "f_prj", "Proyecto"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_ITM_L, SDbConsts.FIELD_NAME, SGridConsts.COL_TITLE_NAME));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, SDbConsts.FIELD_CODE, SGridConsts.COL_TITLE_CODE));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "f_type", SGridConsts.COL_TITLE_TYPE));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_CAT_L, "v.dcrp", "Descripción"));
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
        moSuscriptionsSet.add(SModConsts.CU_USR);
    }
}
