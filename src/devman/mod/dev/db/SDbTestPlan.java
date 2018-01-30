/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.db;

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
 * @author Juan Barajas, Sergio Flores
 */
public class SDbTestPlan extends SDbRegistryUser {

    protected int mnPkPlanId;
    protected int mnNumber;
    protected String msCode;
    protected String msName;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkPlanTypeId;
    protected int mnFkPlanStatusId;
    protected int mnFkStageProjectId;
    protected int mnFkStageStageId;
    protected int mnFkDocumentProjectId_n;
    protected int mnFkDocumentStageId_n;
    protected int mnFkDocumentDocumentId_n;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */

    public SDbTestPlan() {
        super(SModConsts.D_TST_PLN);
    }

    /*
     * Private methods
     */
    
    private void computeNumber(final SGuiSession session) throws Exception {
        msCode = (String) session.readField(SModConsts.PU_PRJ_STG, new int[] { mnFkStageProjectId, mnFkStageStageId }, SDbRegistry.FIELD_CODE) + "-" + 
                (String) session.readField(SModConsts.DS_TST_PLN_TP, new int[] { mnFkPlanTypeId}, SDbRegistry.FIELD_CODE) + "-" + 
                SDevManUtils.FormatCode3.format(mnNumber);
    }

    /*
     * Public methods
     */

    public void setPkPlanId(int n) { mnPkPlanId = n; }
    public void setNumber(int n) { mnNumber = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkPlanTypeId(int n) { mnFkPlanTypeId = n; }
    public void setFkPlanStatusId(int n) { mnFkPlanStatusId = n; }
    public void setFkStageProjectId(int n) { mnFkStageProjectId = n; }
    public void setFkStageStageId(int n) { mnFkStageStageId = n; }
    public void setFkDocumentProjectId_n(int n) { mnFkDocumentProjectId_n = n; }
    public void setFkDocumentStageId_n(int n) { mnFkDocumentStageId_n = n; }
    public void setFkDocumentDocumentId_n(int n) { mnFkDocumentDocumentId_n = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkPlanId() { return mnPkPlanId; }
    public int getNumber() { return mnNumber; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkPlanTypeId() { return mnFkPlanTypeId; }
    public int getFkPlanStatusId() { return mnFkPlanStatusId; }
    public int getFkStageProjectId() { return mnFkStageProjectId; }
    public int getFkStageStageId() { return mnFkStageStageId; }
    public int getFkDocumentProjectId_n() { return mnFkDocumentProjectId_n; }
    public int getFkDocumentStageId_n() { return mnFkDocumentStageId_n; }
    public int getFkDocumentDocumentId_n() { return mnFkDocumentDocumentId_n; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    /*
     * Overriden methods
     */

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkPlanId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkPlanId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkPlanId = 0;
        mnNumber = 0;
        msCode = "";
        msName = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkPlanTypeId = 0;
        mnFkPlanStatusId = 0;
        mnFkStageProjectId = 0;
        mnFkStageStageId = 0;
        mnFkDocumentProjectId_n = 0;
        mnFkDocumentStageId_n = 0;
        mnFkDocumentDocumentId_n = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
    }

    @Override
    public String getSqlTable() {
        return SModConsts.TablesMap.get(SModConsts.D_TST_PLN);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_pln = " + mnPkPlanId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_pln = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkPlanId = 0;

        msSql = "SELECT COALESCE(MAX(id_pln), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkPlanId = resultSet.getInt(1);
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
            mnNumber = resultSet.getInt("num");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkPlanTypeId = resultSet.getInt("fk_pln_tp");
            mnFkPlanStatusId = resultSet.getInt("fk_pln_st");
            mnFkStageProjectId = resultSet.getInt("fk_stg_prj");
            mnFkStageStageId = resultSet.getInt("fk_stg_stg");
            mnFkDocumentProjectId_n = resultSet.getInt("fk_doc_prj_n");
            mnFkDocumentStageId_n = resultSet.getInt("fk_doc_stg_n");
            mnFkDocumentDocumentId_n = resultSet.getInt("fk_doc_doc_n");
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
            mnNumber = SDevManUtils.getNextCodeNumber(session, mnRegistryType, new int[] { mnFkStageProjectId, mnFkStageStageId, mnFkPlanTypeId });
        }
        
        computeNumber(session);

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
                    mnNumber + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkPlanTypeId + ", " + 
                    mnFkPlanStatusId + ", " + 
                    mnFkStageProjectId + ", " + 
                    mnFkStageStageId + ", " + 
                    (mnFkDocumentProjectId_n > 0 ? mnFkDocumentProjectId_n : "NULL") + ", " +
                    (mnFkDocumentStageId_n > 0 ? mnFkDocumentStageId_n : "NULL") + ", " +
                    (mnFkDocumentDocumentId_n > 0 ? mnFkDocumentDocumentId_n : "NULL") + ", " +
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
                    */
                    "num = " + mnNumber + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_pln_tp = " + mnFkPlanTypeId + ", " +
                    "fk_pln_st = " + mnFkPlanStatusId + ", " +
                    "fk_stg_prj = " + mnFkStageProjectId + ", " +
                    "fk_stg_stg = " + mnFkStageStageId + ", " +
                    "fk_doc_prj_n = " + (mnFkDocumentProjectId_n > 0 ? mnFkDocumentProjectId_n : "NULL") + ", " +
                    "fk_doc_stg_n = " + (mnFkDocumentStageId_n > 0 ? mnFkDocumentStageId_n : "NULL") + ", " +
                    "fk_doc_doc_n = " + (mnFkDocumentDocumentId_n > 0 ? mnFkDocumentDocumentId_n : "NULL") + ", " +
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
    public SDbTestPlan clone() throws CloneNotSupportedException {
        SDbTestPlan registry = new SDbTestPlan();
         
        registry.setPkPlanId(this.getPkPlanId());
        registry.setNumber(this.getNumber());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkPlanTypeId(this.getFkPlanTypeId());
        registry.setFkPlanStatusId(this.getFkPlanStatusId());
        registry.setFkStageProjectId(this.getFkStageProjectId());
        registry.setFkStageStageId(this.getFkStageStageId());
        registry.setFkDocumentProjectId_n(this.getFkDocumentProjectId_n());
        registry.setFkDocumentStageId_n(this.getFkDocumentStageId_n());
        registry.setFkDocumentDocumentId_n(this.getFkDocumentDocumentId_n());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
