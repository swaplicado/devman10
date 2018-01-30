/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.view;

import devman.SDevManConsts;
import devman.gui.SDlgGuiLog;
import devman.gui.SGuiMainSessionCustom;
import devman.gui.grid.SGridFilterPanel;
import devman.gui.grid.SGridFilterPanelCompound;
import devman.mod.SModConsts;
import devman.mod.dev.db.SDbTestPlanTest;
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
 * @author Juan Barajas, Sergio Flores
 */
public class SViewTestPlanTest extends SGridPaneView implements ActionListener {

    private SDlgGuiLog moDlgGuiLog;
    private SGridFilterPanelCompound moFilterProjectStage;
    private SGridFilterPanel moFilterStatus;
    private JButton mjAddTestLog;
    private JButton mjViewTestLog;

    public SViewTestPlanTest(SGuiClient client, String title) {
        super(client, SGridConsts.GRID_PANE_VIEW, SModConsts.D_TST_PLN_TST, SLibConsts.UNDEFINED, title);
        initComponentCustom();
    }
    
    private void initComponentCustom() {
        int[] projectStageKey = ((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey();
        
        setRowButtonsEnabled(true, true, true, false, true);
        
        moDlgGuiLog = new SDlgGuiLog(miClient, mnGridType, "Bitácora");
        
        moFilterProjectStage = new SGridFilterPanelCompound(miClient, this, SModConsts.PU_PRJ, SModConsts.PU_PRJ_STG);
        moFilterProjectStage.initFilter(projectStageKey);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterProjectStage);
        
        moFilterStatus = new SGridFilterPanel(miClient, this, SModConsts.CS_ST, SModConsts.P_TSK);
        moFilterStatus.initFilter(null);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterStatus);
        
        mjAddTestLog = SGridUtils.createButton(new ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_add.gif")), "Crear entrada de bitácora", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjAddTestLog);
        
        mjViewTestLog = SGridUtils.createButton(new ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_view.gif")), "Ver bitácora", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjViewTestLog);
    }

    private void actionAddTestLog() {
        SGuiParams params = null;
        
        if (mjAddTestLog.isEnabled()) {
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

                    params.getParamsMap().put(SModConsts.D_TST_PLN_TST, gridRow.getRowPrimaryKey());
                    miClient.getSession().showForm(SModConsts.D_TST_PLN_TST_LOG, SLibConsts.UNDEFINED, params);
                }
            }
        }
    }

    private void actionViewTestLog() {
        if (mjViewTestLog.isEnabled()) {
            if (jtTable.getSelectedRowCount() != 1) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
            }
            else {
                SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

                if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                    miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                }
                else {
                    SDbTestPlanTest test = (SDbTestPlanTest) miClient.getSession().readRegistry(SModConsts.D_TST_PLN_TST, gridRow.getRowPrimaryKey());
                    
                    moDlgGuiLog.setValue(SModConsts.XX_GUI_LOG, test);
                    moDlgGuiLog.setVisible(true);
                }
            }
        }
    }
    
    @Override
    public void prepareSqlQuery() {
        String sql = "";
        Object filter = null;
        SGridFilterValue filterValue = null;

        moPaneSettings = new SGridPaneSettings(2);
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

        filterValue = (SGridFilterValue) moFiltersMap.get(SModConsts.CS_ST);
        if (filterValue != null) {
            filter = filterValue.getValue();
            if (filter != null && ((int[]) filter).length == 1) {
                sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_tst_st = " + ((int[]) filter)[0] + " ";
            }
        }

        msSql = "SELECT "
                + "v.id_pln AS " + SDbConsts.FIELD_ID + "1, "
                + "v.id_tst AS " + SDbConsts.FIELD_ID + "2, "
                + "v.code AS " + SDbConsts.FIELD_CODE + ", "
                + "v.name AS " + SDbConsts.FIELD_NAME + ", "
                + "pln.code AS pln, "
                + "vp.code AS prj, "
                + "vps.code AS stg, "
                + "v.dcrp, "
                + "vs.code AS tst_st, "
                + "(SELECT COALESCE(SUM(vl.log_time), 0) FROM " + SModConsts.TablesMap.get(SModConsts.D_TST_PLN_TST_LOG) + " AS vl WHERE v.id_pln = vl.id_pln AND v.id_tst = vl.id_tst AND vl.b_del = 0) AS f_time, "
                + "v.b_del AS " + SDbConsts.FIELD_IS_DEL + ", "
                + "v.b_sys AS " + SDbConsts.FIELD_IS_SYS + ", "
                + "v.fk_usr_ins AS " + SDbConsts.FIELD_USER_INS_ID + ", "
                + "v.fk_usr_upd AS " + SDbConsts.FIELD_USER_UPD_ID + ", "
                + "v.ts_usr_ins AS " + SDbConsts.FIELD_USER_INS_TS + ", "
                + "v.ts_usr_upd AS " + SDbConsts.FIELD_USER_UPD_TS + ", "
                + "ui.name AS " + SDbConsts.FIELD_USER_INS_NAME + ", "
                + "uu.name AS " + SDbConsts.FIELD_USER_UPD_NAME + ", "
                + "(SELECT COUNT(*) FROM " + SModConsts.TablesMap.get(SModConsts.D_TST_PLN_TST_LOG) + " AS vl WHERE v.id_pln = vl.id_pln AND v.id_tst = vl.id_tst AND vl.b_del = 0) AS f_logs "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.D_TST_PLN_TST) + " AS v "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.D_TST_PLN) + " AS pln ON "
                + "v.id_pln = pln.id_pln "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ) + " AS vp ON "
                + "pln.fk_stg_prj = vp.id_prj "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG) + " AS vps ON "
                + "pln.fk_stg_prj = vps.id_prj AND pln.fk_stg_stg = vps.id_stg "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CS_ST) + " AS vs ON "
                + "v.fk_tst_st = vs.id_st "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS ui ON "
                + "v.fk_usr_ins = ui.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS uu ON "
                + "v.fk_usr_upd = uu.id_usr "
                + (sql.isEmpty() ? "" : "WHERE " + sql)
                + "ORDER BY prj, stg, pln, v.name, pln.fk_stg_prj, pln.fk_stg_stg, v.id_pln, v.id_tst ";
    }

    @Override
    public ArrayList<SGridColumnView> createGridColumns() {
        SGridColumnView column = null;
        ArrayList<SGridColumnView> columns = new ArrayList<SGridColumnView>();

        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "prj", "Proyecto"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "stg", "Etapa"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "pln", "Plan pruebas"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_ITM_L, SDbConsts.FIELD_NAME, SGridConsts.COL_TITLE_NAME));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, SDbConsts.FIELD_CODE, SGridConsts.COL_TITLE_CODE));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_CAT_L, "v.dcrp", "Descripción"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "tst_st", SGridConsts.COL_TITLE_STAT));
        column = new SGridColumnView(SGridConsts.COL_TYPE_DEC_QTY, "f_time", "Esfuerzo (" + SDevManConsts.MAN_HRS + ")");
        column.setSumApplying(true);
        columns.add(column);
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
        moSuscriptionsSet.add(SModConsts.PU_PRJ);
        moSuscriptionsSet.add(SModConsts.D_TST_PLN);
        moSuscriptionsSet.add(SModConsts.D_TST_PLN_TST_LOG);
        moSuscriptionsSet.add(SModConsts.CU_USR);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();

            if (button == mjAddTestLog) {
                actionAddTestLog();
            }
            else if (button == mjViewTestLog) {
                actionViewTestLog();
            }
        }
    }
}
