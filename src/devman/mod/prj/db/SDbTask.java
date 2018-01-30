/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.db;

import devman.SDevManUtils;
import devman.gui.SGuiLogEntryMask;
import devman.gui.SGuiLogMask;
import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import sa.gui.util.SUtilConsts;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.db.SDbRegistryUser;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class SDbTask extends SDbRegistryUser implements SGuiLogMask {

    protected int mnPkTaskId;
    protected int mnNumber;
    protected String msCode;
    protected String msName;
    protected String msDescription;
    protected Date mtDateEndPlanned;
    protected Date mtDateEndReal_n;
    protected double mdTimePlanned;
    protected double mdTimeReal_r;
    protected String msNotes;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkTaskTypeId;
    protected int mnFkTaskStatusId;
    protected int mnFkTaskUserId;
    protected int mnFkActivityProjectId;
    protected int mnFkActivityStageId;
    protected int mnFkActivityPhaseId;
    protected int mnFkActivityActivityId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    public SDbTask() {
        super(SModConsts.P_TSK);
    }

    private void computeNumber(final SGuiSession session) throws Exception {
        msCode = (String) session.readField(SModConsts.PU_PRJ_STG_PHS_ACT, new int[] { mnFkActivityProjectId, mnFkActivityStageId, mnFkActivityPhaseId, mnFkActivityActivityId }, SDbRegistry.FIELD_CODE) + "-" + 
                SDevManUtils.FormatCode5.format(mnNumber);
    }

    public void setPkTaskId(int n) { mnPkTaskId = n; }
    public void setNumber(int n) { mnNumber = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDescription(String s) { msDescription = s; }
    public void setDateEndPlanned(Date t) { mtDateEndPlanned = t; }
    public void setDateEndReal_n(Date t) { mtDateEndReal_n = t; }
    public void setTimePlanned(double d) { mdTimePlanned = d; }
    public void setTimeReal_r(double d) { mdTimeReal_r = d; }
    public void setNotes(String s) { msNotes = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkTaskTypeId(int n) { mnFkTaskTypeId = n; }
    public void setFkTaskStatusId(int n) { mnFkTaskStatusId = n; }
    public void setFkTaskUserId(int n) { mnFkTaskUserId = n; }
    public void setFkActivityProjectId(int n) { mnFkActivityProjectId = n; }
    public void setFkActivityStageId(int n) { mnFkActivityStageId = n; }
    public void setFkActivityPhaseId(int n) { mnFkActivityPhaseId = n; }
    public void setFkActivityActivityId(int n) { mnFkActivityActivityId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkTaskId() { return mnPkTaskId; }
    public int getNumber() { return mnNumber; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getDescription() { return msDescription; }
    public Date getDateEndPlanned() { return mtDateEndPlanned; }
    public Date getDateEndReal_n() { return mtDateEndReal_n; }
    public double getTimePlanned() { return mdTimePlanned; }
    public double getTimeReal_r() { return mdTimeReal_r; }
    public String getNotes() { return msNotes; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkTaskTypeId() { return mnFkTaskTypeId; }
    public int getFkTaskStatusId() { return mnFkTaskStatusId; }
    public int getFkTaskUserId() { return mnFkTaskUserId; }
    public int getFkActivityProjectId() { return mnFkActivityProjectId; }
    public int getFkActivityStageId() { return mnFkActivityStageId; }
    public int getFkActivityPhaseId() { return mnFkActivityPhaseId; }
    public int getFkActivityActivityId() { return mnFkActivityActivityId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }
    
    public ArrayList<SDbTaskLog> readLogEntries(final SGuiSession session) throws Exception {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<SDbTaskLog> entries = new ArrayList<>();
        
        statement = session.getStatement().getConnection().createStatement();
        
        sql = "SELECT id_log "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.P_TSK_LOG) + " "
                + "WHERE id_tsk = " + mnPkTaskId + " AND b_del = 0 ";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            entries.add((SDbTaskLog) session.readRegistry(SModConsts.P_TSK_LOG, new int[] { mnPkTaskId, resultSet.getInt(1) }));
        }
        
        return entries;
    }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkTaskId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkTaskId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();
        
        mnPkTaskId = 0;
        mnNumber = 0;
        msCode = "";
        msName = "";
        msDescription = "";
        mtDateEndPlanned = null;
        mtDateEndReal_n = null;
        mdTimePlanned = 0;
        mdTimeReal_r = 0;
        msNotes = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkTaskTypeId = 0;
        mnFkTaskStatusId = 0;
        mnFkTaskUserId = 0;
        mnFkActivityProjectId = 0;
        mnFkActivityStageId = 0;
        mnFkActivityPhaseId = 0;
        mnFkActivityActivityId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
    }

    @Override
    public String getSqlTable() {
        return SModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_tsk = " + mnPkTaskId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_tsk = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkTaskId = 0;

        msSql = "SELECT COALESCE(MAX(id_tsk), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkTaskId = resultSet.getInt(1);
        }
    }

    @Override
    public void read(SGuiSession session, int[] pk) throws SQLException, Exception {
        ResultSet resultSet = null;

        initRegistry();
        initQueryMembers();
        mnQueryResultId = SDbConsts.READ_ERROR;

        msSql = "SELECT * " + getSqlFromWhere(pk);
        resultSet = session.getStatement().executeQuery(msSql);
        if (!resultSet.next()) {
            throw new Exception(SDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        else {
            mnPkTaskId = resultSet.getInt("id_tsk");
            mnNumber = resultSet.getInt("num");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            msDescription = resultSet.getString("dcrp");
            mtDateEndPlanned = resultSet.getDate("date_end_plan");
            mtDateEndReal_n = resultSet.getDate("date_end_real_n");
            mdTimePlanned = resultSet.getDouble("time_plan");
            mdTimeReal_r = resultSet.getDouble("time_real_r");
            msNotes = resultSet.getString("note");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkTaskTypeId = resultSet.getInt("fk_tsk_tp");
            mnFkTaskStatusId = resultSet.getInt("fk_tsk_st");
            mnFkTaskUserId = resultSet.getInt("fk_tsk_usr");
            mnFkActivityProjectId = resultSet.getInt("fk_act_prj");
            mnFkActivityStageId = resultSet.getInt("fk_act_stg");
            mnFkActivityPhaseId = resultSet.getInt("fk_act_phs");
            mnFkActivityActivityId = resultSet.getInt("fk_act_act");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            mbRegistryNew = false;
        }

        mnQueryResultId = SDbConsts.READ_OK;
    }

    @Override
    public void save(SGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = SDbConsts.SAVE_ERROR;

        if (mbRegistryNew) {
            mnNumber = SDevManUtils.getNextCodeNumber(session, mnRegistryType, new int[] { mnFkActivityProjectId, mnFkActivityStageId, mnFkActivityPhaseId, mnFkActivityActivityId });
        }
        
        computeNumber(session);
        
        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = SUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkTaskId + ", " + 
                    mnNumber + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + msDescription + "', " + 
                    "'" + SLibUtils.DbmsDateFormatDate.format(mtDateEndPlanned) + "', " + 
                    (mtDateEndReal_n == null ? "NULL" : "'" + SLibUtils.DbmsDateFormatDate.format(mtDateEndReal_n) + "'") + ", " +
                    mdTimePlanned + ", " + 
                    mdTimeReal_r + ", " + 
                    "'" + msNotes + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkTaskTypeId + ", " + 
                    mnFkTaskStatusId + ", " + 
                    mnFkTaskUserId + ", " + 
                    mnFkActivityProjectId + ", " + 
                    mnFkActivityStageId + ", " + 
                    mnFkActivityPhaseId + ", " + 
                    mnFkActivityActivityId + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_tsk = " + mnPkTaskId + ", " +
                    "num = " + mnNumber + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "dcrp = '" + msDescription + "', " +
                    "date_end_plan = '" + SLibUtils.DbmsDateFormatDate.format(mtDateEndPlanned) + "', " +
                    "date_end_real_n = " + (mtDateEndReal_n == null ? "NULL" : "'" + SLibUtils.DbmsDateFormatDate.format(mtDateEndReal_n) + "'") + ", " +
                    "time_plan = " + mdTimePlanned + ", " +
                    "time_real_r = " + mdTimeReal_r + ", " +
                    "note = '" + msNotes + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_tsk_tp = " + mnFkTaskTypeId + ", " +
                    "fk_tsk_st = " + mnFkTaskStatusId + ", " +
                    "fk_tsk_usr = " + mnFkTaskUserId + ", " +
                    "fk_act_prj = " + mnFkActivityProjectId + ", " +
                    "fk_act_stg = " + mnFkActivityStageId + ", " +
                    "fk_act_phs = " + mnFkActivityPhaseId + ", " +
                    "fk_act_act = " + mnFkActivityActivityId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        SPrjUtils.updateRealTimeTask(session, mnPkTaskId);
        
        mbRegistryNew = false;
        mnQueryResultId = SDbConsts.SAVE_OK;
    }

    @Override
    public SDbTask clone() throws CloneNotSupportedException {
        SDbTask registry = new SDbTask();

        registry.setPkTaskId(this.getPkTaskId());
        registry.setNumber(this.getNumber());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDescription(this.getDescription());
        registry.setDateEndPlanned(this.getDateEndPlanned());
        registry.setDateEndReal_n(this.getDateEndReal_n());
        registry.setTimePlanned(this.getTimePlanned());
        registry.setTimeReal_r(this.getTimeReal_r());
        registry.setNotes(this.getNotes());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkTaskTypeId(this.getFkTaskTypeId());
        registry.setFkTaskStatusId(this.getFkTaskStatusId());
        registry.setFkTaskUserId(this.getFkTaskUserId());
        registry.setFkActivityProjectId(this.getFkActivityProjectId());
        registry.setFkActivityStageId(this.getFkActivityStageId());
        registry.setFkActivityPhaseId(this.getFkActivityPhaseId());
        registry.setFkActivityActivityId(this.getFkActivityActivityId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
    
    @Override
    public void delete(final SGuiSession session) throws SQLException, Exception {
        super.delete(session);
        
        SPrjUtils.updateRealTimeTask(session, mnPkTaskId);
    }
    
    public void goPrev() throws Exception {
        int newTaskStatusId = SLibConsts.UNDEFINED;
        boolean resetDateEndReal = false;
        
        switch (mnFkTaskStatusId) {
            case SModSysConsts.CS_ST_PND:
                break;
            case SModSysConsts.CS_ST_PRC:
                newTaskStatusId = SModSysConsts.CS_ST_PND;
                resetDateEndReal = true;
                break;
            case SModSysConsts.CS_ST_FIN:
                newTaskStatusId = SModSysConsts.CS_ST_PRC;
                resetDateEndReal = false;
                break;
            case SModSysConsts.CS_ST_CAN:
                break;
            default:
                throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }
        
        if (newTaskStatusId == SLibConsts.UNDEFINED) {
            throw new Exception(SLibConsts.ERR_MSG_WRONG_TYPE);
        }
        else {
            mnFkTaskStatusId = newTaskStatusId;
            if (resetDateEndReal) {
                mtDateEndReal_n = null;
            }
        }
    }
    
    public void goNext() throws Exception {
        int newTaskStatusId = SLibConsts.UNDEFINED;
        
        switch (mnFkTaskStatusId) {
            case SModSysConsts.CS_ST_PND:
                newTaskStatusId = SModSysConsts.CS_ST_PRC;
                break;
            case SModSysConsts.CS_ST_PRC:
            case SModSysConsts.CS_ST_FIN:
            case SModSysConsts.CS_ST_CAN:
                break;
            default:
                throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }
        
        if (newTaskStatusId == SLibConsts.UNDEFINED) {
            throw new Exception(SLibConsts.ERR_MSG_WRONG_TYPE);
        }
        else {
            mnFkTaskStatusId = newTaskStatusId;
        }
    }

    @Override
    public ArrayList<SGuiLogEntryMask> getEntries(final SGuiSession session) throws Exception {
        ArrayList<SGuiLogEntryMask> entries = new ArrayList<>();
        
        entries.addAll(readLogEntries(session));
        
        return entries;
    }
}
