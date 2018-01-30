/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.db;

import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Sergio Flores
 */
public abstract class SPrjUtils {
    
    public static void updateRealTimeTask(final SGuiSession session, final int taskId) throws SQLException, Exception {
        int error = SLibConsts.UNDEFINED;
        CallableStatement callableStatement = null;
        SDbTask task = null;

        callableStatement = session.getStatement().getConnection().prepareCall(
                "{ CALL upd_tsk_time_real(?, ?) }");
        callableStatement.setInt(1, taskId);
        callableStatement.registerOutParameter(2, Types.SMALLINT);
        callableStatement.execute();

        error = callableStatement.getInt(2);

        if (error != SLibConsts.UNDEFINED) {
            throw new Exception("Error code: [" + error + "]");
        }
        
        // Update task's project aswell:
        
        task = (SDbTask) session.readRegistry(SModConsts.P_TSK, new int[] { taskId });
        updateRealTimeProject(session, task.getFkActivityProjectId());
    }
    
    public static void updateRealTimeProject(final SGuiSession session, final int projectId) throws SQLException, Exception {
        int error = SLibConsts.UNDEFINED;
        CallableStatement callableStatement = null;

        callableStatement = session.getStatement().getConnection().prepareCall(
                "{ CALL upd_prj_time_real(?, ?) }");
        callableStatement.setInt(1, projectId);
        callableStatement.registerOutParameter(2, Types.SMALLINT);
        callableStatement.execute();

        error = callableStatement.getInt(2);

        if (error != SLibConsts.UNDEFINED) {
            throw new Exception("Error code: [" + error + "]");
        }
    }
    
    public static SPrjStatsBasic computeProjectStatsBasic(final SGuiSession session, final int projectId) throws SQLException, Exception {
        String sql = "";
        ResultSet resultSet = null;
        SPrjStatsBasic statsBasic = new SPrjStatsBasic();
        
        sql = "SELECT "
                + "t.fk_tsk_st, "
                + "SUM(t.time_plan) AS f_plan, "
                + "SUM(t.time_real_r) AS f_real "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.P_TSK) + " AS t "
                + "WHERE t.fk_act_prj = " + projectId + " AND t.b_del = 0 "
                + "GROUP BY t.fk_tsk_st "
                + "ORDER BY t.fk_tsk_st ";
        resultSet = session.getStatement().executeQuery(sql);
        while (resultSet.next()) {
            statsBasic.TimePlan += resultSet.getDouble("f_plan");
            statsBasic.TimeReal += resultSet.getDouble("f_real");
            
            switch (resultSet.getInt("t.fk_tsk_st")) {
                case SModSysConsts.CS_ST_PND:
                    statsBasic.TimePlanPnd = resultSet.getDouble("f_plan");
                    statsBasic.TimeRealPnd = resultSet.getDouble("f_real");
                    break;
                case SModSysConsts.CS_ST_PRC:
                    statsBasic.TimePlanPrc = resultSet.getDouble("f_plan");
                    statsBasic.TimeRealPrc = resultSet.getDouble("f_real");
                    break;
                case SModSysConsts.CS_ST_FIN:
                    statsBasic.TimePlanFin = resultSet.getDouble("f_plan");
                    statsBasic.TimeRealFin = resultSet.getDouble("f_real");
                    break;
                case SModSysConsts.CS_ST_CAN:
                    statsBasic.TimePlanCan = resultSet.getDouble("f_plan");
                    statsBasic.TimeRealCan = resultSet.getDouble("f_real");
                    break;
                default:
            }
        }
        
        return statsBasic;
    }
    /**
     * Computes project statistics up to cutoff date.
     * @param session GUI session.
     * @param projectId Project ID.
     * @param stageId Project Stage ID. Can be omited.
     * @param cutoff Cutoff date.
     * @return Project statistics up to cutoff date.
     */
    public static SPrjStatsCutoff computeProjectStatsCutoff(final SGuiSession session, final int projectId, final int stageId, final Date cutoff) throws SQLException, Exception {
        String sql = "";
        ResultSet resultSet = null;
        SPrjStatsCutoff statsCutoff = new SPrjStatsCutoff();
        
        // Cutoff date:
        
        statsCutoff.Cutoff = cutoff;
        
        // Days planned and elapsed:

        sql = "SELECT p.date_sta_plan, p.date_end_plan, "
                + "DATEDIFF(p.date_end_plan, p.date_sta_plan) + 1 AS f_days_plan, "
                + "DATEDIFF('" + SLibUtils.DbmsDateFormatDate.format(cutoff) + "', p.date_sta_plan) + 1 AS f_days_elap "
                + "FROM " + SModConsts.TablesMap.get(stageId == SLibConsts.UNDEFINED ? SModConsts.PU_PRJ : SModConsts.PU_PRJ_STG) + " AS p "
                + "WHERE p.id_prj = " + projectId + " " + (stageId == SLibConsts.UNDEFINED ? "" : "AND p.id_stg = " + stageId + " ");
        resultSet = session.getStatement().executeQuery(sql);
        if (!resultSet.next()) {
            throw new Exception(SDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        else {
            statsCutoff.DateStart = resultSet.getDate("date_sta_plan");
            statsCutoff.DateEnd = resultSet.getDate("date_end_plan");
            statsCutoff.DaysPlanned = resultSet.getInt("f_days_plan");
            statsCutoff.DaysElapsed = resultSet.getInt("f_days_elap");
        }
        
        // Work planned, elapsed and finished:
        
        sql = "SELECT "
                + "COALESCE(SUM(t.time_plan)) AS f_time_plan, "
                + "COALESCE(SUM(CASE WHEN t.date_end_plan <= '" + SLibUtils.DbmsDateFormatDate.format(cutoff) + "' THEN t.time_plan ELSE 0 END)) AS f_time_elap, "
                + "COALESCE(SUM(CASE WHEN t.date_end_plan <= '" + SLibUtils.DbmsDateFormatDate.format(cutoff) + "' AND t.fk_tsk_st = " + SModSysConsts.CS_ST_FIN + " THEN t.time_plan ELSE 0 END)) AS f_time_fin "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.P_TSK) + " AS t "
                + "WHERE t.fk_act_prj = " + projectId + " " + (stageId == SLibConsts.UNDEFINED ? "" : "AND t.fk_act_stg = " + stageId + " ") + "AND t.b_del = 0 ";
        resultSet = session.getStatement().executeQuery(sql);
        if (!resultSet.next()) {
            throw new Exception(SDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        else {
            statsCutoff.WorkPlanned = resultSet.getDouble("f_time_plan");
            statsCutoff.WorkElapsed = resultSet.getDouble("f_time_elap");
            statsCutoff.WorkFinished = resultSet.getDouble("f_time_fin");
        }
        
        // Effort required:
        
        sql = "SELECT "
                + "COALESCE(SUM(tl.log_time)) AS f_eff_req "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.P_TSK) + " AS t "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.P_TSK_LOG) + " AS tl ON "
                + "t.id_tsk = tl.id_tsk "
                + "WHERE t.fk_act_prj = " + projectId + " " + (stageId == SLibConsts.UNDEFINED ? "" : "AND t.fk_act_stg = " + stageId + " ") + "AND t.b_del = 0 AND tl.b_del = 0 AND "
                + "t.date_end_plan <= '" + SLibUtils.DbmsDateFormatDate.format(cutoff) + "' AND "
                + "tl.log_date <= '" + SLibUtils.DbmsDateFormatDate.format(cutoff) + "' ";
        resultSet = session.getStatement().executeQuery(sql);
        if (!resultSet.next()) {
            throw new Exception(SDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        else {
            statsCutoff.EffortRequired = resultSet.getDouble("f_eff_req");
        }
        
        return statsCutoff;
    }
}
