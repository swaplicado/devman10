/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.view;

import devman.SDevManConsts;
import devman.gui.grid.SGridFilterPanelUser;
import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import java.util.ArrayList;
import java.util.Date;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.grid.SGridColumnView;
import sa.lib.grid.SGridConsts;
import sa.lib.grid.SGridFilterDateCutOff;
import sa.lib.grid.SGridFilterValue;
import sa.lib.grid.SGridPaneSettings;
import sa.lib.grid.SGridPaneView;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;

/**
 *
 * @author Sergio Flores
 */
public class SViewWork extends SGridPaneView {
    
    private SGridFilterPanelUser moFilterUser;
    private SGridFilterDateCutOff moFilterDateCutOff;

    public SViewWork(SGuiClient client, String title) {
        super(client, SGridConsts.GRID_PANE_VIEW, SModConsts.PX_WRK, SLibConsts.UNDEFINED, title);
        initComponentsCustom();
    }
    
    private void initComponentsCustom() {
        setRowButtonsEnabled(false);
        jtbFilterDeleted.setEnabled(false);

        moFilterUser = new SGridFilterPanelUser(miClient, this);
        moFilterUser.initFilter(new int[] { miClient.getSession().getUser().getPkUserId() });
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterUser);

        moFilterDateCutOff = new SGridFilterDateCutOff(miClient, this);
        moFilterDateCutOff.initFilter(null);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterDateCutOff);
    }

    @Override
    public void prepareSqlQuery() {
        String sql = "";
        Object filter = null;

        moPaneSettings = new SGridPaneSettings(3);

        filter = ((SGridFilterValue) moFiltersMap.get(SGridConsts.FILTER_DELETED)).getValue();
        if ((Boolean) filter) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.b_del = 0 ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.CU_USR)).getValue();
        if (filter != null && ((int[]) filter).length == 1) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_tsk_usr = " + ((int[]) filter)[0] + " ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SGridConsts.FILTER_DATE)).getValue();
        if (filter != null) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.date_end_plan <= '" + SLibUtils.DbmsDateFormatDate.format((Date) filter) + "' ";
        }

        sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_tsk_st IN (" + SModSysConsts.CS_ST_PND + ", " + SModSysConsts.CS_ST_PRC + ") ";
        
        msSql = "SELECT "
                + "v.fk_tsk_usr AS " + SDbConsts.FIELD_ID + "1, "
                + "v.fk_act_prj AS " + SDbConsts.FIELD_ID + "2, "
                + "v.fk_tsk_st AS " + SDbConsts.FIELD_ID + "3, "
                + "tu.name AS " + SDbConsts.FIELD_CODE + ", "
                + "tu.name AS " + SDbConsts.FIELD_NAME + ", "
                + "p.code, "
                + "p.name, "
                + "ts.code, "
                + "ts.name, "
                + "SUM(v.time_plan) AS f_time, "
                + "COUNT(*) AS f_task "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.P_TSK) + " AS v "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS tu ON "
                + "v.fk_tsk_usr = tu.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ) + " AS p ON "
                + "v.fk_act_prj = p.id_prj "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CS_ST) + " AS ts ON "
                + "v.fk_tsk_st = ts.id_st "
                + (sql.isEmpty() ? "" : "WHERE " + sql)
                + "GROUP BY v.fk_tsk_usr, v.fk_act_prj, v.fk_tsk_st, tu.name, p.code, p.name, ts.code, ts.name "
                + "ORDER BY tu.name, v.fk_tsk_usr, p.name, v.fk_act_prj, ts.code, v.fk_tsk_st ";
    }

    @Override
    public ArrayList<SGridColumnView> createGridColumns() {
        SGridColumnView column = null;
        ArrayList<SGridColumnView> columns = new ArrayList<SGridColumnView>();

        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_USR, SDbConsts.FIELD_NAME, "Responsable"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_CAT_M, "p.name", SGridConsts.COL_TITLE_NAME + " proyecto"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "p.code", SGridConsts.COL_TITLE_CODE + " proyecto"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "ts.code", SGridConsts.COL_TITLE_STAT));
        column = new SGridColumnView(SGridConsts.COL_TYPE_DEC_QTY, "f_time", "Tiempo planeado (" + SDevManConsts.MAN_HRS + ")");
        column.setSumApplying(true);
        columns.add(column);
        column = new SGridColumnView(SGridConsts.COL_TYPE_INT_2B, "f_task", "Tareas");
        column.setSumApplying(true);
        columns.add(column);

        return columns;
    }

    @Override
    public void defineSuscriptions() {
        moSuscriptionsSet.add(mnGridType);
        moSuscriptionsSet.add(SModConsts.CU_USR);
        moSuscriptionsSet.add(SModConsts.PU_PRJ);
        moSuscriptionsSet.add(SModConsts.P_TSK);
        moSuscriptionsSet.add(SModConsts.P_TSK_LOG);
    }
}
