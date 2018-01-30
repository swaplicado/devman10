/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.db;

import devman.gui.SGuiLogEntryMask;
import devman.mod.SModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sa.gui.util.SUtilConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.db.SDbRegistryUser;
import sa.lib.grid.SGridRow;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class SDbTaskLog extends SDbRegistryUser implements SGridRow, SGuiLogEntryMask {

    protected int mnPkTaskId;
    protected int mnPkLogId;
    protected Date mtLogDate;
    protected double mdLogTime;
    protected String msNotes;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkWorkTypeId;
    protected int mnFkWorkUserId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected String msXtaWorkTypeCode;
    protected String msXtaWorkTypeName;
    protected String msXtaWorkUser;
    protected String msXtaInsertUser;
    protected String msXtaUpdateUser;
    
    public SDbTaskLog() {
        super(SModConsts.P_TSK_LOG);
    }

    public void setPkTaskId(int n) { mnPkTaskId = n; }
    public void setPkLogId(int n) { mnPkLogId = n; }
    public void setLogDate(Date t) { mtLogDate = t; }
    public void setLogTime(double d) { mdLogTime = d; }
    public void setNotes(String s) { msNotes = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkWorkTypeId(int n) { mnFkWorkTypeId = n; }
    public void setFkWorkUserId(int n) { mnFkWorkUserId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkTaskId() { return mnPkTaskId; }
    public int getPkLogId() { return mnPkLogId; }
    public Date getLogDate() { return mtLogDate; }
    public double getLogTime() { return mdLogTime; }
    public String getNotes() { return msNotes; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkWorkTypeId() { return mnFkWorkTypeId; }
    public int getFkWorkUserId() { return mnFkWorkUserId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setXtaWorkTypeCode(String s) { msXtaWorkTypeCode = s; }
    public void setXtaWorkTypeName(String s) { msXtaWorkTypeName = s; }
    public void setXtaWorkUser(String s) { msXtaWorkUser = s; }
    public void setXtaInsertUser(String s) { msXtaInsertUser = s; }
    public void setXtaUpdateUser(String s) { msXtaUpdateUser = s; }
    
    public String getXtaWorkTypeCode() { return msXtaWorkTypeCode; }
    public String getXtaWorkTypeName() { return msXtaWorkTypeName; }
    public String getXtaWorkUser() { return msXtaWorkUser; }
    public String getXtaInsertUser() { return msXtaInsertUser; }
    public String getXtaUpdateUser() { return msXtaUpdateUser; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkTaskId = pk[0];
        mnPkLogId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkTaskId, mnPkLogId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();
        
        mnPkTaskId = 0;
        mnPkLogId = 0;
        mtLogDate = null;
        mdLogTime = 0;
        msNotes = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkWorkTypeId = 0;
        mnFkWorkUserId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        msXtaWorkTypeCode = "";
        msXtaWorkTypeName = "";
        msXtaWorkUser = "";
        msXtaInsertUser = "";
        msXtaUpdateUser = "";
    }

    @Override
    public String getSqlTable() {
        return SModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_tsk = " + mnPkTaskId + " AND "
                + "id_log = " + mnPkLogId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_tsk = " + pk[0] + " AND "
                + "id_log = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkLogId = 0;

        msSql = "SELECT COALESCE(MAX(id_log), 0) + 1 FROM " + getSqlTable() + " "
                + "WHERE id_tsk = " + mnPkTaskId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkLogId = resultSet.getInt(1);
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
            mnPkLogId = resultSet.getInt("id_log");
            mtLogDate = resultSet.getDate("log_date");
            mdLogTime = resultSet.getDouble("log_time");
            msNotes = resultSet.getString("note");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkWorkTypeId = resultSet.getInt("fk_wrk_tp");
            mnFkWorkUserId = resultSet.getInt("fk_wrk_usr");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");
            
            // Read aswell extra members:
            
            msXtaWorkTypeCode = (String) session.readField(SModConsts.PS_WRK_TP, new int[] { mnFkWorkTypeId }, SDbRegistry.FIELD_CODE);
            msXtaWorkTypeName = (String) session.readField(SModConsts.PS_WRK_TP, new int[] { mnFkWorkTypeId }, SDbRegistry.FIELD_NAME);
            msXtaWorkUser = (String) session.readField(SModConsts.CU_USR, new int[] { mnFkWorkUserId }, SDbRegistry.FIELD_NAME);
            msXtaInsertUser = (String) session.readField(SModConsts.CU_USR, new int[] { mnFkUserInsertId }, SDbRegistry.FIELD_NAME);
            msXtaUpdateUser = (String) session.readField(SModConsts.CU_USR, new int[] { mnFkUserUpdateId }, SDbRegistry.FIELD_NAME);
            
            // Continue reading registry:

            mbRegistryNew = false;
        }

        mnQueryResultId = SDbConsts.READ_OK;
    }

    @Override
    public void save(SGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = SDbConsts.SAVE_ERROR;

        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = SUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkTaskId + ", " + 
                    mnPkLogId + ", " + 
                    "'" + SLibUtils.DbmsDateFormatDate.format(mtLogDate) + "', " + 
                    mdLogTime + ", " + 
                    "'" + msNotes + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkWorkTypeId + ", " + 
                    mnFkWorkUserId + ", " + 
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
                    //"id_log = " + mnPkLogId + ", " +
                    "log_date = '" + SLibUtils.DbmsDateFormatDate.format(mtLogDate) + "', " +
                    "log_time = " + mdLogTime + ", " +
                    "note = '" + msNotes + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_wrk_tp = " + mnFkWorkTypeId + ", " +
                    "fk_wrk_usr = " + mnFkWorkUserId + ", " +
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
    public SDbTaskLog clone() throws CloneNotSupportedException {
        SDbTaskLog registry = new SDbTaskLog();

        registry.setPkTaskId(this.getPkTaskId());
        registry.setPkLogId(this.getPkLogId());
        registry.setLogDate(this.getLogDate());
        registry.setLogTime(this.getLogTime());
        registry.setNotes(this.getNotes());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkWorkTypeId(this.getFkWorkTypeId());
        registry.setFkWorkUserId(this.getFkWorkUserId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());
        
        registry.setXtaWorkTypeCode(this.getXtaWorkTypeCode());
        registry.setXtaWorkTypeName(this.getXtaWorkTypeName());
        registry.setXtaWorkUser(this.getXtaWorkUser());
        registry.setXtaInsertUser(this.getXtaInsertUser());
        registry.setXtaUpdateUser(this.getXtaUpdateUser());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public void delete(final SGuiSession session) throws SQLException, Exception {
        super.delete(session);
        
        SPrjUtils.updateRealTimeTask(session, mnPkTaskId);
    }
    
    @Override
    public int[] getRowPrimaryKey() {
        return getPrimaryKey();
    }

    @Override
    public String getRowCode() {
        return getCode();
    }

    @Override
    public String getRowName() {
        return getName();
    }

    @Override
    public boolean isRowSystem() {
        return isSystem();
    }

    @Override
    public boolean isRowDeletable() {
        return isDeletable();
    }

    @Override
    public boolean isRowEdited() {
        return isRegistryEdited();
    }

    @Override
    public void setRowEdited(boolean edited) {
        setRegistryEdited(edited);
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = mtLogDate;
                break;
            case 1:
                value = mdLogTime;
                break;
            case 2:
                value = msXtaWorkTypeCode;
                break;
            case 3:
                value = msXtaWorkUser;
                break;
            case 4:
                value = msNotes;
                break;
            case 5:
                value = msXtaInsertUser;
                break;
            case 6:
                value = mtTsUserInsert;
                break;
            case 7:
                value = msXtaUpdateUser;
                break;
            case 8:
                value = mtTsUserUpdate;
                break;
            default:
        }
        
        return value;
    }

    @Override
    public void setRowValueAt(Object value, int col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getDate() {
        return getLogDate();
    }

    @Override
    public double getTime() {
        return getLogTime();
    }

    @Override
    public String getType() {
        return getXtaWorkTypeCode();
    }

    @Override
    public String getUser() {
        return getXtaWorkUser();
    }

    @Override
    public String getInsertUser() {
        return getXtaInsertUser();
    }

    @Override
    public Date getInsertTimestamp() {
        return getTsUserInsert();
    }

    @Override
    public String getUpdateUser() {
        return getXtaUpdateUser();
    }

    @Override
    public Date getUpdateTimestamp() {
        return getTsUserUpdate();
    }
}
