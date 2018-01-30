/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.view;

import devman.SDevManConsts;
import devman.gui.SDlgGuiLog;
import devman.gui.SGuiMainSessionCustom;
import devman.gui.grid.SGridFilterPanel;
import devman.gui.grid.SGridFilterPanelCompound;
import devman.gui.grid.SGridFilterPanelUser;
import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import devman.mod.prj.db.SDbTask;
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
import sa.lib.grid.SGridFilterDatePeriod;
import sa.lib.grid.SGridFilterValue;
import sa.lib.grid.SGridPaneSettings;
import sa.lib.grid.SGridPaneView;
import sa.lib.grid.SGridRowView;
import sa.lib.grid.SGridUtils;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiDate;
import sa.lib.gui.SGuiParams;

/**
 *
 * @author Sergio Flores
 */
public class SViewTask extends SGridPaneView implements ActionListener {
    
    private SDlgGuiLog moDlgGuiLog;
    private SGridFilterPanelCompound moFilterProjectStage;
    private SGridFilterPanel moFilterStatus;
    private SGridFilterPanelUser moFilterUser;
    private SGridFilterDatePeriod moFilterDatePeriod;

    private JButton mjStatusPrev;
    private JButton mjStatusNext;
    private JButton mjAddTaskLog;
    private JButton mjViewTaskLog;

    /**
     * @param client GUI Client.
     * @param subtype Allowed options: <code>SLibConsts.UNDEFINED</code> or <code>SModSysConsts.CS_ST_...</code>.
     * @param title View tab's title.
     */
    public SViewTask(SGuiClient client, int subtype, String title) {
        super(client, SGridConsts.GRID_PANE_VIEW, SModConsts.P_TSK, subtype, title);
        initComponentsCustom();
    }
    
    private void initComponentsCustom() {
        int[] projectStageKey = ((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey();
        
        setRowButtonsEnabled(true, true, true, false, true);
        
        moDlgGuiLog = new SDlgGuiLog(miClient, mnGridType, "Bitácora");

        moFilterProjectStage = new SGridFilterPanelCompound(miClient, this, SModConsts.PU_PRJ, SModConsts.PU_PRJ_STG);
        moFilterProjectStage.initFilter(projectStageKey);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterProjectStage);

        switch (mnGridSubtype) {
            case SLibConsts.UNDEFINED:
                moFilterStatus = new SGridFilterPanel(miClient, this, SModConsts.CS_ST, SModConsts.P_TSK);
                moFilterStatus.initFilter(null);
                getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterStatus);

                moFilterUser = new SGridFilterPanelUser(miClient, this);
                moFilterUser.initFilter(null);
                getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterUser);
                break;
                
            case SModSysConsts.CS_ST_PND:
            case SModSysConsts.CS_ST_PRC:
            case SModSysConsts.CS_ST_FIN:
            case SModSysConsts.CS_ST_CAN:
                moFilterUser = new SGridFilterPanelUser(miClient, this);
                moFilterUser.initFilter(new int[] { miClient.getSession().getUser().getPkUserId() });
                getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterUser);
                break;
                
            default:
        }

        mjStatusPrev = SGridUtils.createButton(new ImageIcon(getClass().getResource("/devman/gui/img/icon_std_move_left.gif")), "Enviar a estatus anterior", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjStatusPrev);
        
        mjStatusNext = SGridUtils.createButton(new ImageIcon(getClass().getResource("/devman/gui/img/icon_std_move_right.gif")), "Enviar a estatus siguiente", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjStatusNext);
        
        mjAddTaskLog = SGridUtils.createButton(new ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_add.gif")), "Crear entrada de bitácora", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjAddTaskLog);
        
        mjViewTaskLog = SGridUtils.createButton(new ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_view.gif")), "Ver bitácora", this);
        getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(mjViewTaskLog);
        
        switch (mnGridSubtype) {
            case SLibConsts.UNDEFINED:
                mjStatusPrev.setEnabled(false);
                mjStatusNext.setEnabled(false);
                mjAddTaskLog.setEnabled(false);
                break;
                
            case SModSysConsts.CS_ST_PND:
                mjStatusPrev.setEnabled(false);
                mjStatusNext.setEnabled(true);
                mjAddTaskLog.setEnabled(false);
                break;
                
            case SModSysConsts.CS_ST_PRC:
                mjStatusPrev.setEnabled(true);
                mjStatusNext.setEnabled(false);
                mjAddTaskLog.setEnabled(true);
                break;
                
            case SModSysConsts.CS_ST_FIN:
                mjStatusPrev.setEnabled(true);
                mjStatusNext.setEnabled(false);
                mjAddTaskLog.setEnabled(false);
                break;
                
            case SModSysConsts.CS_ST_CAN:
                mjStatusPrev.setEnabled(false);
                mjStatusNext.setEnabled(false);
                mjAddTaskLog.setEnabled(false);
                break;
                
            default:
        }
        
        switch (mnGridSubtype) {
            case SLibConsts.UNDEFINED:
            case SModSysConsts.CS_ST_PND:
            case SModSysConsts.CS_ST_PRC:
                break;
                
            case SModSysConsts.CS_ST_FIN:
            case SModSysConsts.CS_ST_CAN:
                moFilterDatePeriod = new SGridFilterDatePeriod(miClient, this, SGuiConsts.DATE_PICKER_DATE_PERIOD);
                moFilterDatePeriod.initFilter(new SGuiDate(SGuiConsts.GUI_DATE_MONTH, miClient.getSession().getCurrentDate().getTime()));
                getPanelCommandsSys(SGuiConsts.PANEL_CENTER).add(moFilterDatePeriod);
                break;
                
            default:
        }
    }

    private void actionStatusPrev() {
        if (mjStatusPrev.isEnabled()) {
            if (jtTable.getSelectedRowCount() != 1) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
            }
            else {
                SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

                if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                    miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                }
                else {
                    SDbTask task = null;
                    
                    try {
                        task = (SDbTask) miClient.getSession().readRegistry(SModConsts.P_TSK, gridRow.getRowPrimaryKey());
                        task.goPrev();
                        if (miClient.getSession().saveRegistry(task) == SDbConsts.SAVE_OK) {
                            miClient.getSession().notifySuscriptors(task.getRegistryType());
                        }
                    }
                    catch (Exception e) {
                        SLibUtils.showException(this, e);
                    }
                }
            }
        }
    }
    
    private void actionStatusNext() {
        if (mjStatusNext.isEnabled()) {
            if (jtTable.getSelectedRowCount() != 1) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
            }
            else {
                SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

                if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                    miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                }
                else {
                    SDbTask task = null;
                    
                    try {
                        task = (SDbTask) miClient.getSession().readRegistry(SModConsts.P_TSK, gridRow.getRowPrimaryKey());
                        task.goNext();
                        if (miClient.getSession().saveRegistry(task) == SDbConsts.SAVE_OK) {
                            miClient.getSession().notifySuscriptors(task.getRegistryType());
                        }
                    }
                    catch (Exception e) {
                        SLibUtils.showException(this, e);
                    }
                }
            }
        }
    }
    
    private void actionAddTaskLog() {
        if (mjAddTaskLog.isEnabled()) {
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
                    
                    params.getParamsMap().put(SModConsts.P_TSK, gridRow.getRowPrimaryKey()[0]);
                    
                    miClient.getSession().showForm(SModConsts.P_TSK_LOG, SLibConsts.UNDEFINED, params);
                }
            }
        }
    }
    
    private void actionViewTaskLog() {
        if (mjViewTaskLog.isEnabled()) {
            if (jtTable.getSelectedRowCount() != 1) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
            }
            else {
                SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

                if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                    miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                }
                else {
                    SDbTask task = (SDbTask) miClient.getSession().readRegistry(SModConsts.P_TSK, gridRow.getRowPrimaryKey());
                    
                    moDlgGuiLog.setValue(SModConsts.XX_GUI_LOG, task);
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

        moPaneSettings = new SGridPaneSettings(1);
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
                sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_act_prj = " + ((int[]) filter)[0] + " ";
            }
        }

        filter = ((SGridFilterValue) moFiltersMap.get(SModConsts.PU_PRJ_STG)).getValue();
        if (filter != null && ((int[]) filter).length == 2) {
            sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_act_prj = " + ((int[]) filter)[0] + " AND v.fk_act_stg = " + ((int[]) filter)[1] + " ";
        }

        filterValue = (SGridFilterValue) moFiltersMap.get(SModConsts.CS_ST);
        if (filterValue != null) {
            filter = filterValue.getValue();
            if (filter != null && ((int[]) filter).length == 1) {
                sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_tsk_st = " + ((int[]) filter)[0] + " ";
            }
        }

        filterValue = (SGridFilterValue) moFiltersMap.get(SModConsts.CU_USR);
        if (filterValue != null) {
            filter = filterValue.getValue();
            if (filter != null && ((int[]) filter).length == 1) {
                sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_tsk_usr = " + ((int[]) filter)[0] + " ";
            }
        }

        filterValue = (SGridFilterValue) moFiltersMap.get(SGridConsts.FILTER_DATE_PERIOD);
        if (filterValue != null) {
            filter = filterValue.getValue();
            if (filter != null) {
                sql += (sql.isEmpty() ? "" : "AND ") + SGridUtils.getSqlFilterDate("v.date_end_plan", (SGuiDate) filter);
            }
        }

        switch (mnGridSubtype) {
            case SLibConsts.UNDEFINED:
                break;
                
            case SModSysConsts.CS_ST_PND:
            case SModSysConsts.CS_ST_PRC:
            case SModSysConsts.CS_ST_FIN:
            case SModSysConsts.CS_ST_CAN:
                sql += (sql.isEmpty() ? "" : "AND ") + "v.fk_tsk_st = " + mnGridSubtype + " ";
                break;
                
            default:
        }
        
        msSql = "SELECT "
                + "v.id_tsk AS " + SDbConsts.FIELD_ID + "1, "
                + "v.code AS " + SDbConsts.FIELD_CODE + ", "
                + "v.name AS " + SDbConsts.FIELD_NAME + ", "
                + "v.date_end_plan, "
                + "v.date_end_real_n, "
                + "v.time_plan, "
                + "v.time_real_r, "
                + "v.b_del AS " + SDbConsts.FIELD_IS_DEL + ", "
                + "v.b_sys AS " + SDbConsts.FIELD_IS_SYS + ", "
                + "v.fk_usr_ins AS " + SDbConsts.FIELD_USER_INS_ID + ", "
                + "v.fk_usr_upd AS " + SDbConsts.FIELD_USER_UPD_ID + ", "
                + "v.ts_usr_ins AS " + SDbConsts.FIELD_USER_INS_TS + ", "
                + "v.ts_usr_upd AS " + SDbConsts.FIELD_USER_UPD_TS + ", "
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
                + "uu.name AS " + SDbConsts.FIELD_USER_UPD_NAME + ", "
                + "(SELECT COUNT(*) FROM " + SModConsts.TablesMap.get(SModConsts.P_TSK_LOG) + " AS vl WHERE vl.id_tsk = v.id_tsk AND vl.b_del = 0) AS f_logs "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.P_TSK) + " AS v "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PS_TSK_TP) + " AS tt ON "
                + "v.fk_tsk_tp = tt.id_tsk_tp "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CS_ST) + " AS ts ON "
                + "v.fk_tsk_st = ts.id_st "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS tu ON "
                + "v.fk_tsk_usr = tu.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ) + " AS p ON "
                + "v.fk_act_prj = p.id_prj "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG) + " AS ps ON "
                + "v.fk_act_prj = ps.id_prj AND v.fk_act_stg = ps.id_stg "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG_PHS) + " AS psp ON "
                + "v.fk_act_prj = psp.id_prj AND v.fk_act_stg = psp.id_stg AND v.fk_act_phs = psp.id_phs "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.PU_PRJ_STG_PHS_ACT) + " AS pspa ON "
                + "v.fk_act_prj = pspa.id_prj AND v.fk_act_stg = pspa.id_stg AND v.fk_act_phs = pspa.id_phs AND v.fk_act_act = pspa.id_act "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS ui ON "
                + "v.fk_usr_ins = ui.id_usr "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.CU_USR) + " AS uu ON "
                + "v.fk_usr_upd = uu.id_usr "
                + (sql.isEmpty() ? "" : "WHERE " + sql)
                + "ORDER BY p.code, ps.code, psp.code, pspa.code, v.name, v.id_tsk ";
    }

    @Override
    public ArrayList<SGridColumnView> createGridColumns() {
        SGridColumnView column = null;
        ArrayList<SGridColumnView> columns = new ArrayList<SGridColumnView>();

        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "p.code", "Proyecto"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "ps.code", "Etapa"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "psp.code", "Fase"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, "pspa.code", "Actividad"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_CAT_L, SDbConsts.FIELD_NAME, SGridConsts.COL_TITLE_NAME));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_ITM, SDbConsts.FIELD_CODE, SGridConsts.COL_TITLE_CODE, 150));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "tt.code", SGridConsts.COL_TITLE_TYPE));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "ts.code", SGridConsts.COL_TITLE_STAT));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_TEXT_NAME_USR, "tu.name", "Responsable"));
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE, "v.date_end_plan", "Fin planeado"));
        column = new SGridColumnView(SGridConsts.COL_TYPE_DEC_QTY, "v.time_plan", "Tiempo planeado (" + SDevManConsts.MAN_HRS + ")");
        column.setSumApplying(true);
        columns.add(column);
        columns.add(new SGridColumnView(SGridConsts.COL_TYPE_DATE, "v.date_end_real_n", "Fin real"));
        column = new SGridColumnView(SGridConsts.COL_TYPE_DEC_QTY, "v.time_real_r", "Tiempo real (" + SDevManConsts.MAN_HRS + ")");
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
        moSuscriptionsSet.add(SModConsts.CU_USR);
        moSuscriptionsSet.add(SModConsts.PU_PRJ);
        moSuscriptionsSet.add(SModConsts.PU_PRJ_STG);
        moSuscriptionsSet.add(SModConsts.PU_PRJ_STG_PHS);
        moSuscriptionsSet.add(SModConsts.PU_PRJ_STG_PHS_ACT);
        moSuscriptionsSet.add(SModConsts.P_TSK_LOG);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == mjStatusPrev) {
                actionStatusPrev();
            }
            else if (button == mjStatusNext) {
                actionStatusNext();
            }
            else if (button == mjAddTaskLog) {
                actionAddTaskLog();
            }
            else if (button == mjViewTaskLog) {
                actionViewTaskLog();
            }
        }
    }
}
