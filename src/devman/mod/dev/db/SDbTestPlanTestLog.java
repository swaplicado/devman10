/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.db;

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
 * @author Juan Barajas, Sergio Flores
 */
public class SDbTestPlanTestLog extends SDbRegistryUser implements SGridRow, SGuiLogEntryMask {

    protected int mnPkPlanId;
    protected int mnPkTestId;
    protected int mnPkLogId;
    protected Date mtLogDate;
    protected double mdLogTime;
    protected String msNotes;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkResultTypeId;
    protected int mnFkResultUserId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */

    protected String msXtaResultTypeCode;
    protected String msXtaResultTypeName;
    protected String msXtaResultUser;
    protected String msXtaInsertUser;
    protected String msXtaUpdateUser;
    
    public SDbTestPlanTestLog() {
        super(SModConsts.D_TST_PLN_TST_LOG);
    }

    /*
     * Public methods
     */

    public void setPkPlanId(int n) { mnPkPlanId = n; }
    public void setPkTestId(int n) { mnPkTestId = n; }
    public void setPkLogId(int n) { mnPkLogId = n; }
    public void setLogDate(Date t) { mtLogDate = t; }
    public void setLogTime(double d) { mdLogTime = d; }
    public void setNotes(String s) { msNotes = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkResultTypeId(int n) { mnFkResultTypeId = n; }
    public void setFkResultUserId(int n) { mnFkResultUserId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkPlanId() { return mnPkPlanId; }
    public int getPkTestId() { return mnPkTestId; }
    public int getPkLogId() { return mnPkLogId; }
    public Date getLogDate() { return mtLogDate; }
    public double getLogTime() { return mdLogTime; }
    public String getNotes() { return msNotes; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkResultTypeId() { return mnFkResultTypeId; }
    public int getFkResultUserId() { return mnFkResultUserId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setXtaResultTypeCode(String s) { msXtaResultTypeCode = s; }
    public void setXtaResultTypeName(String s) { msXtaResultTypeName = s; }
    public void setXtaResultUser(String s) { msXtaResultUser = s; }
    public void setXtaInsertUser(String s) { msXtaInsertUser = s; }
    public void setXtaUpdateUser(String s) { msXtaUpdateUser = s; }
    
    public String getXtaResultTypeCode() { return msXtaResultTypeCode; }
    public String getXtaResultTypeName() { return msXtaResultTypeName; }
    public String getXtaResultUser() { return msXtaResultUser; }
    public String getXtaInsertUser() { return msXtaInsertUser; }
    public String getXtaUpdateUser() { return msXtaUpdateUser; }
    
    /*
     * Overriden methods
     */

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkPlanId = pk[0];
        mnPkTestId = pk[1];
        mnPkLogId = pk[2];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkPlanId, mnPkTestId, mnPkLogId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkPlanId = 0;
        mnPkTestId = 0;
        mnPkLogId = 0;
        mtLogDate = null;
        mdLogTime = 0;
        msNotes = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkResultTypeId = 0;
        mnFkResultUserId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        msXtaResultTypeCode = "";
        msXtaResultTypeName = "";
        msXtaResultUser = "";
        msXtaInsertUser = "";
        msXtaUpdateUser = "";
    }

    @Override
    public String getSqlTable() {
        return SModConsts.TablesMap.get(SModConsts.D_TST_PLN_TST_LOG);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_pln = " + mnPkPlanId + " AND " +
                "id_tst = " + mnPkTestId + " AND " +
                "id_log = " + mnPkLogId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_pln = " + pk[0] + " AND " +
                "id_tst = " + pk[1] + " AND " +
                "id_log = " + pk[2] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkLogId = 0;

        msSql = "SELECT COALESCE(MAX(id_log), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_pln = " + mnPkPlanId + " AND " +
                "id_tst = " + mnPkTestId + " ";
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
            mnPkPlanId = resultSet.getInt("id_pln");
            mnPkTestId = resultSet.getInt("id_tst");
            mnPkLogId = resultSet.getInt("id_log");
            mtLogDate = resultSet.getDate("log_date");
            mdLogTime = resultSet.getDouble("log_time");
            msNotes = resultSet.getString("note");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkResultTypeId = resultSet.getInt("fk_res_tp");
            mnFkResultUserId = resultSet.getInt("fk_res_usr");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            // Read aswell extra members:
            
            msXtaResultTypeCode = (String) session.readField(SModConsts.DS_TST_RES_TP, new int[] { mnFkResultTypeId }, SDbRegistry.FIELD_CODE);
            msXtaResultTypeName = (String) session.readField(SModConsts.DS_TST_RES_TP, new int[] { mnFkResultTypeId }, SDbRegistry.FIELD_NAME);
            msXtaResultUser = (String) session.readField(SModConsts.CU_USR, new int[] { mnFkResultUserId }, SDbRegistry.FIELD_NAME);
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
        mnQueryResultId = SDbConsts.READ_ERROR;

        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbUpdatable = true;
            mbDisableable = true;
            mbDeletable = true;
            mbDisabled = false;
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = SUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkPlanId + ", " + 
                    mnPkTestId + ", " + 
                    mnPkLogId + ", " + 
                    "'" + SLibUtils.DbmsDateFormatDate.format(mtLogDate) + "', " + 
                    mdLogTime + ", " + 
                    "'" + msNotes + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkResultTypeId + ", " + 
                    mnFkResultUserId + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " +
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    /*
                    "id_pln = " + mnPkPlanId + ", " +
                    "id_tst = " + mnPkTestId + ", " +
                    "id_log = " + mnPkLogId + ", " +
                    */
                    "log_date = '" + SLibUtils.DbmsDateFormatDate.format(mtLogDate) + "', " +
                    "log_time = " + mdLogTime + ", " +
                    "note = '" + msNotes + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_res_tp = " + mnFkResultTypeId + ", " +
                    "fk_res_usr = " + mnFkResultUserId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        mbRegistryNew = false;
        mnQueryResultId = SDbConsts.SAVE_OK;
    }

    @Override
    public SDbTestPlanTestLog clone() throws CloneNotSupportedException {
        SDbTestPlanTestLog registry = new SDbTestPlanTestLog();

        registry.setPkPlanId(this.getPkPlanId());
        registry.setPkTestId(this.getPkTestId());
        registry.setPkLogId(this.getPkLogId());
        registry.setLogDate(this.getLogDate());
        registry.setLogTime(this.getLogTime());
        registry.setNotes(this.getNotes());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkResultTypeId(this.getFkResultTypeId());
        registry.setFkResultUserId(this.getFkResultUserId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setXtaResultTypeCode(this.getXtaResultTypeCode());
        registry.setXtaResultTypeName(this.getXtaResultTypeName());
        registry.setXtaResultUser(this.getXtaResultUser());
        registry.setXtaInsertUser(this.getXtaInsertUser());
        registry.setXtaUpdateUser(this.getXtaUpdateUser());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
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
                value = msXtaResultTypeCode;
                break;
            case 3:
                value = msXtaResultUser;
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
        return getXtaResultTypeCode();
    }

    @Override
    public String getUser() {
        return getXtaResultUser();
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
