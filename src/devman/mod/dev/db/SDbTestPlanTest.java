/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.db;

import devman.SDevManUtils;
import devman.gui.SGuiLogEntryMask;
import devman.gui.SGuiLogMask;
import devman.mod.SModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class SDbTestPlanTest extends SDbRegistryUser implements SGuiLogMask {

    protected int mnPkPlanId;
    protected int mnPkTestId;
    protected int mnNumber;
    protected String msCode;
    protected String msName;
    protected String msDescription;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkTestStatusId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected int mnAuxFkStageProjectId;
    protected int mnAuxFkStageStageId;

    public SDbTestPlanTest() {
        super(SModConsts.D_TST_PLN_TST);
    }

    /*
     * Private methods
     */
    
    private void computeNumber(final SGuiSession session) throws Exception {
        msCode = (String) session.readField(SModConsts.D_TST_PLN, new int[] { mnPkPlanId }, SDbRegistry.FIELD_CODE) + "-" + 
                SDevManUtils.FormatCode5.format(mnNumber);
    }

    /*
     * Public methods
     */

    public void setPkPlanId(int n) { mnPkPlanId = n; }
    public void setPkTestId(int n) { mnPkTestId = n; }
    public void setNumber(int n) { mnNumber = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDescription(String s) { msDescription = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkTestStatusId(int n) { mnFkTestStatusId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkPlanId() { return mnPkPlanId; }
    public int getPkTestId() { return mnPkTestId; }
    public int getNumber() { return mnNumber; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getDescription() { return msDescription; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkTestStatusId() { return mnFkTestStatusId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setAuxFkStageProjectId(int n) { mnAuxFkStageProjectId = n; }
    public void setAuxFkStageStageId(int n) { mnAuxFkStageStageId = n; }

    public int getAuxFkStageProjectId() { return mnAuxFkStageProjectId; }
    public int getAuxFkStageStageId() { return mnAuxFkStageStageId; }

    public ArrayList<SDbTestPlanTestLog> readLogEntries(final SGuiSession session) throws Exception {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<SDbTestPlanTestLog> entries = new ArrayList<>();
        
        statement = session.getStatement().getConnection().createStatement();
        
        sql = "SELECT id_log "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.D_TST_PLN_TST_LOG) + " "
                + "WHERE id_pln = " + mnPkPlanId + " AND id_tst = " + mnPkTestId + " AND b_del = 0 ";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            entries.add((SDbTestPlanTestLog) session.readRegistry(SModConsts.D_TST_PLN_TST_LOG, new int[] { mnPkPlanId, mnPkTestId, resultSet.getInt(1) }));
        }
        
        return entries;
    }

    /*
     * Overriden methods
     */

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkPlanId = pk[1];
        mnPkTestId = pk[2];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkPlanId, mnPkTestId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkPlanId = 0;
        mnPkTestId = 0;
        mnNumber = 0;
        msCode = "";
        msName = "";
        msDescription = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkTestStatusId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        mnAuxFkStageProjectId = 0;
        mnAuxFkStageStageId = 0;
    }

    @Override
    public String getSqlTable() {
        return SModConsts.TablesMap.get(SModConsts.D_TST_PLN_TST);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_pln = " + mnPkPlanId + " AND " +
                "id_tst = " + mnPkTestId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_pln = " + pk[0] + " AND " +
                "id_tst = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkTestId = 0;

        msSql = "SELECT COALESCE(MAX(id_tst), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_pln = " + mnPkPlanId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkTestId = resultSet.getInt(1);
        }
    }

    @Override
    public void read(SGuiSession session, int[] pk) throws SQLException, Exception {
        ResultSet resultSet = null;
        SDbTestPlan testPlan = null;

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
            mnNumber = resultSet.getInt("num");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            msDescription = resultSet.getString("dcrp");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkTestStatusId = resultSet.getInt("fk_tst_st");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");
            
            // Read aswell auxiliar members:
            
            testPlan = (SDbTestPlan) session.readRegistry(SModConsts.D_TST_PLN, new int[] { mnPkPlanId });
            mnAuxFkStageProjectId = testPlan.getFkStageProjectId();
            mnAuxFkStageStageId = testPlan.getFkStageStageId();
            
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
            mnNumber = SDevManUtils.getNextCodeNumber(session, mnRegistryType, new int[] { mnPkPlanId });
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
                    mnPkTestId + ", " + 
                    mnNumber + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + msDescription + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkTestStatusId + ", " + 
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
                    */
                    "num = " + mnNumber + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "dcrp = '" + msDescription + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_tst_st = " + mnFkTestStatusId + ", " +
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
    public SDbTestPlanTest clone() throws CloneNotSupportedException {
        SDbTestPlanTest registry = new SDbTestPlanTest();

        registry.setPkPlanId(this.getPkPlanId());
        registry.setPkTestId(this.getPkTestId());
        registry.setNumber(this.getNumber());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDescription(this.getDescription());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkTestStatusId(this.getFkTestStatusId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());
        
        registry.setAuxFkStageProjectId(this.getAuxFkStageProjectId());
        registry.setAuxFkStageStageId(this.getAuxFkStageStageId());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public ArrayList<SGuiLogEntryMask> getEntries(final SGuiSession session) throws Exception {
        ArrayList<SGuiLogEntryMask> entries = new ArrayList<>();
        
        entries.addAll(readLogEntries(session));
        
        return entries;
    }
}
