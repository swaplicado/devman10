/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.db;

import devman.mod.SModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import sa.gui.util.SUtilConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistryUser;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Juan Barajas, Sergio Flores
 */
public class SDbTrace extends SDbRegistryUser {

    protected int mnPkProjectId;
    protected int mnPkTraceId;
    protected Date mtTraceDate;
    protected String msNotes;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkRequirementProjectId;
    protected int mnFkRequirementRequirementId;
    protected int mnFkPhaseProjectId;
    protected int mnFkPhaseStageId;
    protected int mnFkPhasePhaseId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */

    public SDbTrace() {
        super(SModConsts.D_TRC);
    }

    /*
     * Public methods
     */

    public void setPkProjectId(int n) { mnPkProjectId = n; }
    public void setPkTraceId(int n) { mnPkTraceId = n; }
    public void setTraceDate(Date t) { mtTraceDate = t; }
    public void setNotes(String s) { msNotes = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkRequirementProjectId(int n) { mnFkRequirementProjectId = n; }
    public void setFkRequirementRequirementId(int n) { mnFkRequirementRequirementId = n; }
    public void setFkPhaseProjectId(int n) { mnFkPhaseProjectId = n; }
    public void setFkPhaseStageId(int n) { mnFkPhaseStageId = n; }
    public void setFkPhasePhaseId(int n) { mnFkPhasePhaseId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkProjectId() { return mnPkProjectId; }
    public int getPkTraceId() { return mnPkTraceId; }
    public Date getTraceDate() { return mtTraceDate; }
    public String getNotes() { return msNotes; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkRequirementProjectId() { return mnFkRequirementProjectId; }
    public int getFkRequirementRequirementId() { return mnFkRequirementRequirementId; }
    public int getFkPhaseProjectId() { return mnFkPhaseProjectId; }
    public int getFkPhaseStageId() { return mnFkPhaseStageId; }
    public int getFkPhasePhaseId() { return mnFkPhasePhaseId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }
    
    public ArrayList<SDbTraceLog> readLogs(SGuiSession session) throws Exception {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<SDbTraceLog> logs = new ArrayList<>();
        
        statement = session.getStatement().getConnection().createStatement();
        
        sql = "SELECT id_log FROM " + SModConsts.TablesMap.get(SModConsts.D_TRC_LOG) + " "
                + "WHERE id_prj = " + mnPkProjectId + " AND id_trc = " + mnPkTraceId + " AND b_del = 0 "
                + "ORDER BY id_log ";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            logs.add((SDbTraceLog) session.readRegistry(SModConsts.D_TRC_LOG, new int[] { mnPkProjectId, mnPkTraceId, resultSet.getInt(1) }));
        }
        
        return logs;
    }

    /*
     * Overriden methods
     */

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkProjectId = pk[0];
        mnPkTraceId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkProjectId, mnPkTraceId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkProjectId = 0;
        mnPkTraceId = 0;
        mtTraceDate = null;
        msNotes = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkRequirementProjectId = 0;
        mnFkRequirementRequirementId = 0;
        mnFkPhaseProjectId = 0;
        mnFkPhaseStageId = 0;
        mnFkPhasePhaseId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
    }

    @Override
    public String getSqlTable() {
        return SModConsts.TablesMap.get(SModConsts.D_TRC);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_prj = " + mnPkProjectId + " AND " +
                "id_trc = " + mnPkTraceId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_prj = " + pk[0] + " AND " +
                "id_trc = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkTraceId = 0;

        msSql = "SELECT COALESCE(MAX(id_trc), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_prj = " + mnPkProjectId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkTraceId = resultSet.getInt(1);
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
            mnPkTraceId = resultSet.getInt("id_trc");
            mtTraceDate = resultSet.getDate("trc_date");
            msNotes = resultSet.getString("note");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkRequirementProjectId = resultSet.getInt("fk_req_prj");
            mnFkRequirementRequirementId = resultSet.getInt("fk_req_req");
            mnFkPhaseProjectId = resultSet.getInt("fk_phs_prj");
            mnFkPhaseStageId = resultSet.getInt("fk_phs_stg");
            mnFkPhasePhaseId = resultSet.getInt("fk_phs_phs");
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
                    mnPkProjectId + ", " + 
                    mnPkTraceId + ", " + 
                    "'" + SLibUtils.DbmsDateFormatDate.format(mtTraceDate) + "', " + 
                    "'" + msNotes + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkRequirementProjectId + ", " + 
                    mnFkRequirementRequirementId + ", " + 
                    mnFkPhaseProjectId + ", " + 
                    mnFkPhaseStageId + ", " + 
                    mnFkPhasePhaseId + ", " + 
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
                    "id_prj = " + mnPkProjectId + ", " +
                    "id_trc = " + mnPkTraceId + ", " +
                    */
                    "trc_date = '" + SLibUtils.DbmsDateFormatDate.format(mtTraceDate) + "', " +
                    "note = '" + msNotes + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_req_prj = " + mnFkRequirementProjectId + ", " +
                    "fk_req_req = " + mnFkRequirementRequirementId + ", " +
                    "fk_phs_prj = " + mnFkPhaseProjectId + ", " +
                    "fk_phs_stg = " + mnFkPhaseStageId + ", " +
                    "fk_phs_phs = " + mnFkPhasePhaseId + ", " +
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
    public SDbTrace clone() throws CloneNotSupportedException {
        SDbTrace registry = new SDbTrace();

        registry.setPkProjectId(this.getPkProjectId());
        registry.setPkTraceId(this.getPkTraceId());
        registry.setTraceDate(this.getTraceDate());
        registry.setNotes(this.getNotes());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkRequirementProjectId(this.getFkRequirementProjectId());
        registry.setFkRequirementRequirementId(this.getFkRequirementRequirementId());
        registry.setFkPhaseProjectId(this.getFkPhaseProjectId());
        registry.setFkPhaseStageId(this.getFkPhaseStageId());
        registry.setFkPhasePhaseId(this.getFkPhasePhaseId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
