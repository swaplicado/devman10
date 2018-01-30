/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.db;

import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sa.gui.util.SUtilConsts;
import sa.lib.SLibConsts;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.db.SDbRegistryUser;
import sa.lib.grid.SGridRow;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Juan Barajas, Sergio Flores
 */
public class SDbTraceLog extends SDbRegistryUser implements SGridRow {

    protected int mnPkProjectId;
    protected int mnPkTraceId;
    protected int mnPkLogId;
    protected String msNotes;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkReferenceTypeId;
    protected int mnFkDocumentProjectId_n;
    protected int mnFkDocumentStageId_n;
    protected int mnFkDocumentDocumentId_n;
    protected int mnFkComponentProjectId_n;
    protected int mnFkComponentComponentId_n;
    protected int mnFkElementProjectId_n;
    protected int mnFkElementElementId_n;
    protected int mnFkTestPlanId_n;
    protected int mnFkTestTestId_n;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */

    protected String msXtaReferenceTypeCode;
    protected String msXtaReferenceTypeName;
    protected String msXtaReferenceCode;
    protected String msXtaReferenceName;
    protected String msXtaInsertUser;
    protected String msXtaUpdateUser;
    
    public SDbTraceLog() {
        super(SModConsts.D_TRC_LOG);
    }

    /*
     * Public methods
     */

    public void setPkProjectId(int n) { mnPkProjectId = n; }
    public void setPkTraceId(int n) { mnPkTraceId = n; }
    public void setPkLogId(int n) { mnPkLogId = n; }
    public void setNotes(String s) { msNotes = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkReferenceTypeId(int n) { mnFkReferenceTypeId = n; }
    public void setFkDocumentProjectId_n(int n) { mnFkDocumentProjectId_n = n; }
    public void setFkDocumentStageId_n(int n) { mnFkDocumentStageId_n = n; }
    public void setFkDocumentDocumentId_n(int n) { mnFkDocumentDocumentId_n = n; }
    public void setFkComponentProjectId_n(int n) { mnFkComponentProjectId_n = n; }
    public void setFkComponentComponentId_n(int n) { mnFkComponentComponentId_n = n; }
    public void setFkElementProjectId_n(int n) { mnFkElementProjectId_n = n; }
    public void setFkElementElementId_n(int n) { mnFkElementElementId_n = n; }
    public void setFkTestPlanId_n(int n) { mnFkTestPlanId_n = n; }
    public void setFkTestTestId_n(int n) { mnFkTestTestId_n = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkProjectId() { return mnPkProjectId; }
    public int getPkTraceId() { return mnPkTraceId; }
    public int getPkLogId() { return mnPkLogId; }
    public String getNotes() { return msNotes; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkReferenceTypeId() { return mnFkReferenceTypeId; }
    public int getFkDocumentProjectId_n() { return mnFkDocumentProjectId_n; }
    public int getFkDocumentStageId_n() { return mnFkDocumentStageId_n; }
    public int getFkDocumentDocumentId_n() { return mnFkDocumentDocumentId_n; }
    public int getFkComponentProjectId_n() { return mnFkComponentProjectId_n; }
    public int getFkComponentComponentId_n() { return mnFkComponentComponentId_n; }
    public int getFkElementProjectId_n() { return mnFkElementProjectId_n; }
    public int getFkElementElementId_n() { return mnFkElementElementId_n; }
    public int getFkTestPlanId_n() { return mnFkTestPlanId_n; }
    public int getFkTestTestId_n() { return mnFkTestTestId_n; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setXtaReferenceTypeCode(String s) { msXtaReferenceTypeCode = s; }
    public void setXtaReferenceTypeName(String s) { msXtaReferenceTypeName = s; }
    public void setXtaReferenceCode(String s) { msXtaReferenceCode = s; }
    public void setXtaReferenceName(String s) { msXtaReferenceName = s; }
    public void setXtaInsertUser(String s) { msXtaInsertUser = s; }
    public void setXtaUpdateUser(String s) { msXtaUpdateUser = s; }
    
    public String getXtaReferenceTypeCode() { return msXtaReferenceTypeCode; }
    public String getXtaReferenceTypeName() { return msXtaReferenceTypeName; }
    public String getXtaReferenceCode() { return msXtaReferenceCode; }
    public String getXtaReferenceName() { return msXtaReferenceName; }
    public String getXtaInsertUser() { return msXtaInsertUser; }
    public String getXtaUpdateUser() { return msXtaUpdateUser; }
    
    /*
     * Overriden methods
     */

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkProjectId = pk[0];
        mnPkTraceId = pk[1];
        mnPkLogId = pk[2];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkProjectId, mnPkTraceId, mnPkLogId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkProjectId = 0;
        mnPkTraceId = 0;
        mnPkLogId = 0;
        msNotes = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkReferenceTypeId = 0;
        mnFkDocumentProjectId_n = 0;
        mnFkDocumentStageId_n = 0;
        mnFkDocumentDocumentId_n = 0;
        mnFkComponentProjectId_n = 0;
        mnFkComponentComponentId_n = 0;
        mnFkElementProjectId_n = 0;
        mnFkElementElementId_n = 0;
        mnFkTestPlanId_n = 0;
        mnFkTestTestId_n = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        msXtaReferenceTypeCode = "";
        msXtaReferenceTypeName = "";
        msXtaReferenceCode = "";
        msXtaReferenceName = "";
        msXtaInsertUser = "";
        msXtaUpdateUser = "";
    }

    @Override
    public String getSqlTable() {
        return SModConsts.TablesMap.get(SModConsts.D_TRC_LOG);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_prj = " + mnPkProjectId + " AND " +
                "id_trc = " + mnPkTraceId + " AND " +
                "id_log = " + mnPkLogId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_prj = " + pk[0] + " AND " +
                "id_trc = " + pk[1] + " AND " +
                "id_log = " + pk[2] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkLogId = 0;

        msSql = "SELECT COALESCE(MAX(id_log), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_prj = " + mnPkProjectId + " AND " +
                "id_trc = " + mnPkTraceId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkLogId = resultSet.getInt(1);
        }
    }

    @Override
    public void read(SGuiSession session, int[] pk) throws SQLException, Exception {
        int type = SLibConsts.UNDEFINED;
        int[] key = null;
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
            mnPkLogId = resultSet.getInt("id_log");
            msNotes = resultSet.getString("note");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkReferenceTypeId = resultSet.getInt("fk_ref_tp");
            mnFkDocumentProjectId_n = resultSet.getInt("fk_doc_prj_n");
            mnFkDocumentStageId_n = resultSet.getInt("fk_doc_stg_n");
            mnFkDocumentDocumentId_n = resultSet.getInt("fk_doc_doc_n");
            mnFkComponentProjectId_n = resultSet.getInt("fk_cmp_prj_n");
            mnFkComponentComponentId_n = resultSet.getInt("fk_cmp_cmp_n");
            mnFkElementProjectId_n = resultSet.getInt("fk_ade_prj_n");
            mnFkElementElementId_n = resultSet.getInt("fk_ade_ade_n");
            mnFkTestPlanId_n = resultSet.getInt("fk_tst_pln_n");
            mnFkTestTestId_n = resultSet.getInt("fk_tst_tst_n");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            // Read aswell extra members:
            
            switch (mnFkReferenceTypeId) {
                case SModSysConsts.DS_TRC_REF_TP_DOC:
                    type = SModConsts.DU_DOC;
                    key = new int[] { mnFkDocumentProjectId_n, mnFkDocumentStageId_n, mnFkDocumentDocumentId_n };
                    break;
                case SModSysConsts.DS_TRC_REF_TP_CMP:
                    type = SModConsts.DU_CMP;
                    key = new int[] { mnFkComponentProjectId_n, mnFkComponentComponentId_n };
                    break;
                case SModSysConsts.DS_TRC_REF_TP_ADE:
                    type = SModConsts.DU_ADE;
                    key = new int[] { mnFkElementProjectId_n, mnFkElementElementId_n };
                    break;
                case SModSysConsts.DS_TRC_REF_TP_TST:
                    type = SModConsts.D_TST_PLN_TST;
                    key = new int[] { mnFkTestPlanId_n, mnFkTestTestId_n };
                    break;
                default:
            }
            
            msXtaReferenceTypeCode = (String) session.readField(SModConsts.DS_TRC_REF_TP, new int[] { mnFkReferenceTypeId }, SDbRegistry.FIELD_CODE);
            msXtaReferenceTypeName = (String) session.readField(SModConsts.DS_TRC_REF_TP, new int[] { mnFkReferenceTypeId }, SDbRegistry.FIELD_NAME);
            msXtaReferenceCode = (String) session.readField(type, key, SDbRegistry.FIELD_CODE);
            msXtaReferenceName = (String) session.readField(type, key, SDbRegistry.FIELD_NAME);
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
                    mnPkProjectId + ", " + 
                    mnPkTraceId + ", " + 
                    mnPkLogId + ", " + 
                    "'" + msNotes + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkReferenceTypeId + ", " + 
                    (mnFkDocumentProjectId_n > 0 ? mnFkDocumentProjectId_n : "NULL") + ", " +
                    (mnFkDocumentStageId_n > 0 ? mnFkDocumentStageId_n : "NULL") + ", " +
                    (mnFkDocumentDocumentId_n > 0 ? mnFkDocumentDocumentId_n : "NULL") + ", " +
                    (mnFkComponentProjectId_n > 0 ? mnFkComponentProjectId_n : "NULL") + ", " +
                    (mnFkComponentComponentId_n > 0 ? mnFkComponentComponentId_n : "NULL") + ", " +
                    (mnFkElementProjectId_n > 0 ? mnFkElementProjectId_n : "NULL") + ", " +
                    (mnFkElementElementId_n > 0 ? mnFkElementElementId_n : "NULL") + ", " +
                    (mnFkTestPlanId_n > 0 ? mnFkTestPlanId_n : "NULL") + ", " +
                    (mnFkTestTestId_n > 0 ? mnFkTestTestId_n : "NULL") + ", " +
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
                    "id_log = " + mnPkLogId + ", " +
                    */
                    "note = '" + msNotes + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_ref_tp = " + mnFkReferenceTypeId + ", " +
                    "fk_doc_prj_n = " + (mnFkDocumentProjectId_n > 0 ? mnFkDocumentProjectId_n : "NULL") + ", " +
                    "fk_doc_stg_n = " + (mnFkDocumentStageId_n > 0 ? mnFkDocumentStageId_n : "NULL") + ", " +
                    "fk_doc_doc_n = " + (mnFkDocumentDocumentId_n > 0 ? mnFkDocumentDocumentId_n : "NULL") + ", " +
                    "fk_cmp_prj_n = " + (mnFkComponentProjectId_n > 0 ? mnFkComponentProjectId_n : "NULL") + ", " +
                    "fk_cmp_cmp_n = " + (mnFkComponentComponentId_n > 0 ? mnFkComponentComponentId_n : "NULL") + ", " +
                    "fk_ade_prj_n = " + (mnFkElementProjectId_n > 0 ? mnFkElementProjectId_n : "NULL") + ", " +
                    "fk_ade_ade_n = " + (mnFkElementElementId_n > 0 ? mnFkElementElementId_n : "NULL") + ", " +
                    "fk_tst_pln_n = " + (mnFkTestPlanId_n > 0 ? mnFkTestPlanId_n : "NULL") + ", " +
                    "fk_tst_tst_n = " + (mnFkTestTestId_n > 0 ? mnFkTestTestId_n : "NULL") + ", " +
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
    public SDbTraceLog clone() throws CloneNotSupportedException {
        SDbTraceLog registry = new SDbTraceLog();

        registry.setPkProjectId(this.getPkProjectId());
        registry.setPkTraceId(this.getPkTraceId());
        registry.setPkLogId(this.getPkLogId());
        registry.setNotes(this.getNotes());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkReferenceTypeId(this.getFkReferenceTypeId());
        registry.setFkDocumentProjectId_n(this.getFkDocumentProjectId_n());
        registry.setFkDocumentStageId_n(this.getFkDocumentStageId_n());
        registry.setFkDocumentDocumentId_n(this.getFkDocumentDocumentId_n());
        registry.setFkComponentProjectId_n(this.getFkComponentProjectId_n());
        registry.setFkComponentComponentId_n(this.getFkComponentComponentId_n());
        registry.setFkElementProjectId_n(this.getFkElementProjectId_n());
        registry.setFkElementElementId_n(this.getFkElementElementId_n());
        registry.setFkTestPlanId_n(this.getFkTestPlanId_n());
        registry.setFkTestTestId_n(this.getFkTestTestId_n());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setXtaReferenceTypeCode(this.getXtaReferenceTypeCode());
        registry.setXtaReferenceTypeName(this.getXtaReferenceTypeName());
        registry.setXtaReferenceCode(this.getXtaReferenceCode());
        registry.setXtaReferenceName(this.getXtaReferenceName());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRowName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                value = msXtaReferenceTypeCode;
                break;
            case 1:
                value = msXtaReferenceName;
                break;
            case 2:
                value = msXtaReferenceCode;
                break;
            case 3:
                value = msNotes;
                break;
            case 4:
                value = msXtaInsertUser;
                break;
            case 5:
                value = mtTsUserInsert;
                break;
            case 6:
                value = msXtaUpdateUser;
                break;
            case 7:
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
}
