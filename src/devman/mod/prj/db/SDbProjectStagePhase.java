/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.db;

import devman.SDevManUtils;
import devman.mod.SModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sa.gui.util.SUtilConsts;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.db.SDbRegistryUser;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class SDbProjectStagePhase extends SDbRegistryUser {

    protected int mnPkProjectId;
    protected int mnPkStageId;
    protected int mnPkPhaseId;
    protected int mnNumber;
    protected String msCode;
    protected String msName;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkPhaseTypeProjectTypeId;
    protected int mnFkPhaseTypePhaseTypeId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    public SDbProjectStagePhase() {
        super(SModConsts.PU_PRJ_STG_PHS);
    }

    private void computeNumber(final SGuiSession session) throws Exception {
        msCode = (String) session.readField(SModConsts.PU_PRJ_STG, new int[] { mnPkProjectId, mnPkStageId }, SDbRegistry.FIELD_CODE) + "-" + 
                SDevManUtils.FormatCode1.format(mnNumber);
    }

    public void setPkProjectId(int n) { mnPkProjectId = n; }
    public void setPkStageId(int n) { mnPkStageId = n; }
    public void setPkPhaseId(int n) { mnPkPhaseId = n; }
    public void setNumber(int n) { mnNumber = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkPhaseTypeProjectTypeId(int n) { mnFkPhaseTypeProjectTypeId = n; }
    public void setFkPhaseTypePhaseTypeId(int n) { mnFkPhaseTypePhaseTypeId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkProjectId() { return mnPkProjectId; }
    public int getPkStageId() { return mnPkStageId; }
    public int getPkPhaseId() { return mnPkPhaseId; }
    public int getNumber() { return mnNumber; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkPhaseTypeProjectTypeId() { return mnFkPhaseTypeProjectTypeId; }
    public int getFkPhaseTypePhaseTypeId() { return mnFkPhaseTypePhaseTypeId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkProjectId = pk[0];
        mnPkStageId = pk[1];
        mnPkPhaseId = pk[2];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkProjectId, mnPkStageId, mnPkPhaseId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();
        
        mnPkProjectId = 0;
        mnPkStageId = 0;
        mnPkPhaseId = 0;
        mnNumber = 0;
        msCode = "";
        msName = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkPhaseTypeProjectTypeId = 0;
        mnFkPhaseTypePhaseTypeId = 0;
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
        return "WHERE id_prj = " + mnPkProjectId + " AND "
                + "id_stg = " + mnPkStageId + " AND "
                + "id_phs = " + mnPkPhaseId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_prj = " + pk[0] + " AND "
                + "id_stg = " + pk[1] + " AND "
                + "id_phs = " + pk[2] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkPhaseId = 0;

        msSql = "SELECT COALESCE(MAX(id_phs), 0) + 1 FROM " + getSqlTable() + " "
                + "WHERE id_prj = " + mnPkProjectId + " AND id_stg = " + mnPkStageId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkPhaseId = resultSet.getInt(1);
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
            mnPkStageId = resultSet.getInt("id_stg");
            mnPkPhaseId = resultSet.getInt("id_phs");
            mnNumber = resultSet.getInt("num");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkPhaseTypeProjectTypeId = resultSet.getInt("fk_phs_tp_prj_tp");
            mnFkPhaseTypePhaseTypeId = resultSet.getInt("fk_phs_tp_phs_tp");
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
            mnNumber = SDevManUtils.getNextCodeNumber(session, mnRegistryType, new int[] { mnPkProjectId, mnPkStageId, mnPkPhaseId });
        }
        
        computeNumber(session);
        
        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = SUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkProjectId + ", " + 
                    mnPkStageId + ", " + 
                    mnPkPhaseId + ", " + 
                    mnNumber + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkPhaseTypeProjectTypeId + ", " + 
                    mnFkPhaseTypePhaseTypeId + ", " + 
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
                    //"id_stg = " + mnPkStageId + ", " +
                    //"id_phs = " + mnPkPhaseId + ", " +
                    "num = " + mnNumber + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_phs_tp_prj_tp = " + mnFkPhaseTypeProjectTypeId + ", " +
                    "fk_phs_tp_phs_tp = " + mnFkPhaseTypePhaseTypeId + ", " +
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
    public SDbProjectStagePhase clone() throws CloneNotSupportedException {
        SDbProjectStagePhase registry = new SDbProjectStagePhase();

        registry.setPkProjectId(this.getPkProjectId());
        registry.setPkStageId(this.getPkStageId());
        registry.setPkPhaseId(this.getPkPhaseId());
        registry.setNumber(this.getNumber());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkPhaseTypeProjectTypeId(this.getFkPhaseTypeProjectTypeId());
        registry.setFkPhaseTypePhaseTypeId(this.getFkPhaseTypePhaseTypeId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
