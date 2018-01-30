/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.view;

import devman.SDevManConsts;
import devman.gui.SGuiMainSessionCustom;
import devman.gui.grid.SGridFilterPanel;
import devman.gui.grid.SGridFilterPanelUser;
import devman.mod.SModConsts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import sa.lib.SLibConsts;
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
 * @author Sergio Flores
 */
public class SViewProject extends SGridPaneView implements ActionListener {
    
    private SGridFilterPanel moFilterProject;
    private SGridFilterPanel moFilterStatus;
    private SGridFilterPanelUser moFilterUser;

    private JButton mjShowProjectStats;
    
    public SViewProject(SGuiClient client, String title) {
        super(client, SGridConsts.GRID_PANE_VIEW, SModConsts.PU_PRJ, SLibConsts.UNDEFINED, title);
        initComponentsCustom();
    }
    
    private void initComponentsCustom() {
        int[] projectStageKey = ((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey();
        
        setRowButtonsEnabled(true, true, true, false, true);
        
        moFilterProject = new SGridFilterPanel(miClient, this, SModConsts.PU_PRJ, SLibConsts.UNDEFINED);
        moFilterProject.initFilter(projectStageKey);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterProject);
        
        moFilterStatus = new SGridFilterPanel(miClient, this, SModConsts.CS_ST, SModConsts.PU_PRJ);
        moFilterStatus.initFilter(null);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterStatus);
        
        moFilterUser = new SGridFilterPanelUser(miClient, this);
        moFilterUser.initFilter(null);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterUser);
        
        mjShowProjectStats = SGridUtils.createButton(new ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_view.gif")), "Ver estadísticas del proyecto", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjShowProjectStats);
    }
    
    private void actionShowStats() {
        if (mjShowProjectStats.isEnabled()) {
            if (jtTable.getSelectedRowCount() != 1) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
            }
            else {
                SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

                if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                    miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                }
                else {
                    SGuiParams params = new SGuiParams();
                    
                    params.setKey(gridRow.getRowPrimaryKey());
                    
                    miClient.getSession().showForm(SModConsts.PX_PRJ_STA, SLibConsts.UNDEFINED, params);
                }
            }
        }
    }

    @Override
    public void prepareSqlQuery() {
        String sql = "";
        Object filter = null;

        moPaneSettings = new SGridPaneSettings(1);
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

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.CS_ST)).getValue();
        if (filter != null && ((int[]) filter).length == 1) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_prj_st = " + ((int[]) filter)[0] + " ";
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.CU_USR)).getValue();
        if (filter != null && ((int[]) filter).length == 1) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_prj_usr = " + ((int[]) filter)[0] + " ";
        }

        msSql = "SELECT "
                + "v.id_prj AS " + SDbConsts.FIELD_ID + "1, "
                + "v.code AS " + SDbConsts.FIELD_CODE + ", "
                + "v.name AS " + SDbConsts.FIELD_NAME + ", "
                + "v.date_sta_plan, "
                + "v.date_sta_real_n, "
                + "v.date_end_plan, "
                + "v.date_end_real_n, "
                + "v.time_plan, "
                + "v.time_real_r, "
                + "DATEDIFF(v.date_end_plan, v.date_sta_plan) + 1 AS f_days_plan, "
                + "COALESCE(DATEDIFF(v.date_end_real_n, v.date_sta_real_n) + 1, 0) AS f_days_real_n, "
                + "v.b_del AS " + SDbConsts.FIELD_IS_DEL + ", "
                + "v.b_sys AS " + SDbConsts.FIELD_IS_SYS + ", "
                + "v.fk_usr_ins AS " + SDbConsts.FIELD_USER_INS_ID + ", "
                + "v.fk_usr_upd AS " + SDbConsts.FIELD_USER_UPD_ID + ", "
                + "v.ts_usr_ins AS " + SDbConsts.FIELD_USER_INS_TS + ", "
                + "v.ts_usr_upd AS " + SDbConsts.FIELD_USER_UPD_TS + ", "
                + "pt.code, "
                + "pt.name, "
                + "ps.code, "
                + "ps.name, "
                + "pu.name, "
                + "ui.name AS " + SDbConsts.FIELD_USER_INS_NAME + ", "
                + "uu.name AS " + SDbConsts.FIELD_USER_UPD_NAME + " "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.PU_PRJ) + " AS v "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PS_PRJ_TP) + " AS pt ON "
                + "v.fk_prj_tp = pt.id_prj_tp "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CS_ST) + " AS ps ON "
                + "v.fk_prj_st = ps.id_st "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS pu ON "
                + "v.fk_prj_usr = pu.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS ui ON "
                + "v.fk_usr_ins = ui.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS uu ON "
                + "v.fk_usr_upd = uu.id_usr "
                + (sql.isEmpty() ? "" : "WHERE " + sql)
                + "ORDER BY v.code, v.name, v.id_prj ";
    }

    @Override
    public ArrayList<SGridColumnView> createGridColumns() {
        ArrayList<SGridColumnView> columns = new ArrayList<SGridColumnView>();

        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, SDbConsts.FIELD_CODE, SGridConsts.COL_TITLE_CODE));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_CAT_M, SDbConsts.FIELD_NAME, SGridConsts.COL_TITLE_NAME));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "pt.code", SGridConsts.COL_TITLE_TYPE));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "ps.code", SGridConsts.COL_TITLE_STAT));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE, "v.date_sta_plan", "Inicio planeado"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE, "v.date_end_plan", "Fin planeado"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_INT_2B, "f_days_plan", "Días planeados"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DEC_QTY, "v.time_plan", "Tiempo planeado (" + SDevManConsts.MAN_HRS + ")"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE, "v.date_sta_real_n", "Inicio real"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE, "v.date_end_real_n", "Fin real"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_INT_2B, "f_days_real_n", "Días reales"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DEC_QTY, "v.time_real_r", "Tiempo real (" + SDevManConsts.MAN_HRS + ")"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_USR, "pu.name", "Responsable"));
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
        moSuscriptionsSet.add(SModConsts.P_TSK);
        moSuscriptionsSet.add(SModConsts.P_TSK_LOG);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == mjShowProjectStats) {
                actionShowStats();
            }
        }
    }
}
