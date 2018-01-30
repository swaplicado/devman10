/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.db;

import devman.SDevManUtils;
import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import sa.gui.util.SUtilConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.db.SDbRegistryUser;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class SDbProjectStage extends SDbRegistryUser {

    protected int mnPkProjectId;
    protected int mnPkStageId;
    protected int mnNumber;
    protected String msCode;
    protected String msName;
    protected Date mtDateStartPlanned;
    protected Date mtDateEndPlanned;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    public SDbProjectStage() {
        super(SModConsts.PU_PRJ_STG);
    }
    
    private void computeNumber(final SGuiSession session) throws Exception {
        msCode = (String) session.readField(SModConsts.PU_PRJ, new int[] { mnPkProjectId }, SDbRegistry.FIELD_CODE) + "-" + 
                SDevManUtils.FormatCode2.format(mnNumber);
    }

    private void createPhases(final SGuiSession session) throws SQLException, Exception {
        SDbProject project = (SDbProject) session.readRegistry(SModConsts.PU_PRJ, new int[] { mnPkProjectId });
        SDbProjectStagePhase phase = null;
        ArrayList<int[]> keys = new ArrayList<int[]>();
        
        switch (project.getFkProjectTypeId()) {
            case SModSysConsts.PS_PRJ_TP_DEV:
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEV_STA);
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEV_REQ);
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEV_ANA_DSG);
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEV_CON);
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEV_INT_TST);
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEV_CLS);
                break;
            case SModSysConsts.PS_PRJ_TP_DEP:
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEP_STA);
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEP_DIA);
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEP_SET_CFG);
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEP_TRA);
                keys.add(SModSysConsts.PS_PRJ_PHS_TP_DEP_CLS);
                break;
            default:
        }
        
        for (int[] key : keys) {
            phase = new SDbProjectStagePhase();
            phase.setPkProjectId(mnPkProjectId);
            phase.setPkStageId(mnPkStageId);
            phase.setCode((String) session.readField(SModConsts.PS_PRJ_PHS_TP, key, SDbRegistry.FIELD_CODE));
            phase.setName((String) session.readField(SModConsts.PS_PRJ_PHS_TP, key, SDbRegistry.FIELD_NAME));
            phase.setFkPhaseTypeProjectTypeId(key[0]);
            phase.setFkPhaseTypePhaseTypeId(key[1]);
            phase.save(session);
        }
    }

    public void setPkProjectId(int n) { mnPkProjectId = n; }
    public void setPkStageId(int n) { mnPkStageId = n; }
    public void setNumber(int n) { mnNumber = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDateStartPlanned(Date t) { mtDateStartPlanned = t; }
    public void setDateEndPlanned(Date t) { mtDateEndPlanned = t; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkProjectId() { return mnPkProjectId; }
    public int getPkStageId() { return mnPkStageId; }
    public int getNumber() { return mnNumber; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public Date getDateStartPlanned() { return mtDateStartPlanned; }
    public Date getDateEndPlanned() { return mtDateEndPlanned; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkProjectId = pk[0];
        mnPkStageId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkProjectId, mnPkStageId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();
        
        mnPkProjectId = 0;
        mnPkStageId = 0;
        mnNumber = 0;
        msCode = "";
        msName = "";
        mtDateStartPlanned = null;
        mtDateEndPlanned = null;
        mbDeleted = false;
        mbSystem = false;
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
                + "id_stg = " + mnPkStageId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_prj = " + pk[0] + " AND "
                + "id_stg = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkStageId = 0;

        msSql = "SELECT COALESCE(MAX(id_stg), 0) + 1 FROM " + getSqlTable() + " "
                + "WHERE id_prj = " + mnPkProjectId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkStageId = resultSet.getInt(1);
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
            mnNumber = resultSet.getInt("num");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            mtDateStartPlanned = resultSet.getDate("date_sta_plan");
            mtDateEndPlanned = resultSet.getDate("date_end_plan");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
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
            mnNumber = SDevManUtils.getNextCodeNumber(session, mnRegistryType, new int[] { mnPkProjectId, mnPkStageId });
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
                    mnNumber + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + SLibUtils.DbmsDateFormatDate.format(mtDateStartPlanned) + "', " + 
                    "'" + SLibUtils.DbmsDateFormatDate.format(mtDateEndPlanned) + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
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
                    "num = " + mnNumber + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "date_sta_plan = '" + SLibUtils.DbmsDateFormatDate.format(mtDateStartPlanned) + "', " +
                    "date_end_plan = '" + SLibUtils.DbmsDateFormatDate.format(mtDateEndPlanned) + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        if (mbRegistryNew) {
            createPhases(session);
        }
        
        mbRegistryNew = false;
        mnQueryResultId = SDbConsts.SAVE_OK;
    }

    @Override
    public SDbProjectStage clone() throws CloneNotSupportedException {
        SDbProjectStage registry = new SDbProjectStage();

        registry.setPkProjectId(this.getPkProjectId());
        registry.setPkStageId(this.getPkStageId());
        registry.setNumber(this.getNumber());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDateStartPlanned(this.getDateStartPlanned());
        registry.setDateEndPlanned(this.getDateEndPlanned());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
