/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.db;

import devman.mod.SModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sa.gui.util.SUtilConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistryUser;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class SDbProject extends SDbRegistryUser {

    protected int mnPkProjectId;
    protected String msCode;
    protected String msName;
    protected Date mtDateStartPlanned;
    protected Date mtDateStartReal_n;
    protected Date mtDateEndPlanned;
    protected Date mtDateEndReal_n;
    protected double mdTimePlanned;
    protected double mdTimeReal_r;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkProjectTypeId;
    protected int mnFkProjectStatusId;
    protected int mnFkProjectUserId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    public SDbProject() {
        super(SModConsts.PU_PRJ);
    }

    public void setPkProjectId(int n) { mnPkProjectId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDateStartPlanned(Date t) { mtDateStartPlanned = t; }
    public void setDateStartReal_n(Date t) { mtDateStartReal_n = t; }
    public void setDateEndPlanned(Date t) { mtDateEndPlanned = t; }
    public void setDateEndReal_n(Date t) { mtDateEndReal_n = t; }
    public void setTimePlanned(double d) { mdTimePlanned = d; }
    public void setTimeReal_r(double d) { mdTimeReal_r = d; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkProjectTypeId(int n) { mnFkProjectTypeId = n; }
    public void setFkProjectStatusId(int n) { mnFkProjectStatusId = n; }
    public void setFkProjectUserId(int n) { mnFkProjectUserId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkProjectId() { return mnPkProjectId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public Date getDateStartPlanned() { return mtDateStartPlanned; }
    public Date getDateStartReal_n() { return mtDateStartReal_n; }
    public Date getDateEndPlanned() { return mtDateEndPlanned; }
    public Date getDateEndReal_n() { return mtDateEndReal_n; }
    public double getTimePlanned() { return mdTimePlanned; }
    public double getTimeReal_r() { return mdTimeReal_r; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkProjectTypeId() { return mnFkProjectTypeId; }
    public int getFkProjectStatusId() { return mnFkProjectStatusId; }
    public int getFkProjectUserId() { return mnFkProjectUserId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkProjectId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkProjectId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();
        
        mnPkProjectId = 0;
        msCode = "";
        msName = "";
        mtDateStartPlanned = null;
        mtDateStartReal_n = null;
        mtDateEndPlanned = null;
        mtDateEndReal_n = null;
        mdTimePlanned = 0;
        mdTimeReal_r = 0;
        mbDeleted = false;
        mbSystem = false;
        mnFkProjectTypeId = 0;
        mnFkProjectStatusId = 0;
        mnFkProjectUserId = 0;
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
        return "WHERE id_prj = " + mnPkProjectId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_prj = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkProjectId = 0;

        msSql = "SELECT COALESCE(MAX(id_prj), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkProjectId = resultSet.getInt(1);
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
            mnPkProjectId = resultSet.getInt("id_prj");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            mtDateStartPlanned = resultSet.getDate("date_sta_plan");
            mtDateStartReal_n = resultSet.getDate("date_sta_real_n");
            mtDateEndPlanned = resultSet.getDate("date_end_plan");
            mtDateEndReal_n = resultSet.getDate("date_end_real_n");
            mdTimePlanned = resultSet.getDouble("time_plan");
            mdTimeReal_r = resultSet.getDouble("time_real_r");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkProjectTypeId = resultSet.getInt("fk_prj_tp");
            mnFkProjectStatusId = resultSet.getInt("fk_prj_st");
            mnFkProjectUserId = resultSet.getInt("fk_prj_usr");
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
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = SUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkProjectId + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + SLibUtils.DbmsDateFormatDate.format(mtDateStartPlanned) + "', " + 
                    (mtDateStartReal_n == null ? "NULL" : "'" + SLibUtils.DbmsDateFormatDate.format(mtDateStartReal_n) + "'") + ", " + 
                    "'" + SLibUtils.DbmsDateFormatDate.format(mtDateEndPlanned) + "', " + 
                    (mtDateEndReal_n == null ? "NULL" : "'" + SLibUtils.DbmsDateFormatDate.format(mtDateEndReal_n) + "'") + ", " + 
                    mdTimePlanned + ", " + 
                    mdTimeReal_r + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkProjectTypeId + ", " + 
                    mnFkProjectStatusId + ", " + 
                    mnFkProjectUserId + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_prj = " + mnPkProjectId + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "date_sta_plan = '" + SLibUtils.DbmsDateFormatDate.format(mtDateStartPlanned) + "', " +
                    "date_sta_real_n = " + (mtDateStartReal_n == null ? "NULL" : "'" + SLibUtils.DbmsDateFormatDate.format(mtDateStartReal_n) + "'") + ", " +
                    "date_end_plan = '" + SLibUtils.DbmsDateFormatDate.format(mtDateEndPlanned) + "', " +
                    "date_end_real_n = " + (mtDateEndReal_n == null ? "NULL" : "'" + SLibUtils.DbmsDateFormatDate.format(mtDateEndReal_n) + "'") + ", " +
                    "time_plan = " + mdTimePlanned + ", " +
                    "time_real_r = " + mdTimeReal_r + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_prj_tp = " + mnFkProjectTypeId + ", " +
                    "fk_prj_st = " + mnFkProjectStatusId + ", " +
                    "fk_prj_usr = " + mnFkProjectUserId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        SPrjUtils.updateRealTimeProject(session, mnPkProjectId);
        
        mbRegistryNew = false;
        mnQueryResultId = SDbConsts.SAVE_OK;
    }

    @Override
    public SDbProject clone() throws CloneNotSupportedException {
        SDbProject registry = new SDbProject();

        registry.setPkProjectId(this.getPkProjectId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDateStartPlanned(this.getDateStartPlanned());
        registry.setDateStartReal_n(this.getDateStartReal_n());
        registry.setDateEndPlanned(this.getDateEndPlanned());
        registry.setDateEndReal_n(this.getDateEndReal_n());
        registry.setTimePlanned(this.getTimePlanned());
        registry.setTimeReal_r(this.getTimeReal_r());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkProjectTypeId(this.getFkProjectTypeId());
        registry.setFkProjectStatusId(this.getFkProjectStatusId());
        registry.setFkProjectUserId(this.getFkProjectUserId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
