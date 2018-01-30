/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.db;

import devman.SDevManUtils;
import devman.mod.SModConsts;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sa.gui.util.SUtilConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.db.SDbRegistryUser;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Juan Barajas, Sergio Flores
 */
public class SDbDocument extends SDbRegistryUser {

    protected int mnPkProjectId;
    protected int mnPkStageId;
    protected int mnPkDocumentId;
    protected int mnNumber;
    protected String msCode;
    protected String msName;
    protected String msDescription;
    protected java.sql.Blob moDocument_n;
    protected String msDocumentName;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkDocumentTypeId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    protected FileInputStream moAuxInputStream;
    protected byte[] moAuxDocumentBytes_n;
    protected boolean mbAuxDocumentChange;

    public SDbDocument() {
        super(SModConsts.DU_DOC);
    }

    /*
     * Private methods
     */
    
    private void computeNumber(final SGuiSession session) throws Exception {
        msCode = (String) session.readField(SModConsts.PU_PRJ_STG, new int[] { mnPkProjectId, mnPkStageId }, SDbRegistry.FIELD_CODE) + "-" + 
                (String) session.readField(SModConsts.DS_DOC_TP, new int[] { mnFkDocumentTypeId}, SDbRegistry.FIELD_CODE) + "-" + 
                SDevManUtils.FormatCode5.format(mnNumber);
    }
    
    private ByteArrayInputStream createArrayBytesForDocument() {
        ByteArrayOutputStream byteArrayOS = null;
        ByteArrayInputStream bais = null;
        byte[] byteArray = null;
        byte[] buffer = null;
        int read = -1;

        byteArrayOS = new ByteArrayOutputStream(16777215);
        buffer = new byte[500];

        try {
            while ((read = moAuxInputStream.read(buffer)) > 0) {
                byteArrayOS.write(buffer, 0, read);
            }
            moAuxInputStream.close();

            byteArray = byteArrayOS.toByteArray();
            bais = new ByteArrayInputStream(byteArray);
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        
        return bais;
    }

    /*
     * Public methods
     */

    public void setPkProjectId(int n) { mnPkProjectId = n; }
    public void setPkStageId(int n) { mnPkStageId = n; }
    public void setPkDocumentId(int n) { mnPkDocumentId = n; }
    public void setNumber(int n) { mnNumber = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDescription(String s) { msDescription = s; }
    public void setDocument_n(java.sql.Blob o) { moDocument_n = o; }
    public void setDocumentName(String s) { msDocumentName = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkDocumentTypeId(int n) { mnFkDocumentTypeId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }
    
    public void setAuxFileInputStream(FileInputStream o) { moAuxInputStream = o; }
    public void setAuxDocumentBytes_n(byte[] o) { moAuxDocumentBytes_n = o; }
    public void setAuxDocumentChange(boolean b) { mbAuxDocumentChange = b; }

    public int getPkProjectId() { return mnPkProjectId; }
    public int getPkStageId() { return mnPkStageId; }
    public int getPkDocumentId() { return mnPkDocumentId; }
    public int getNumber() { return mnNumber; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getDescription() { return msDescription; }
    public java.sql.Blob getDocument_n() { return moDocument_n; }
    public String getDocumentName() { return msDocumentName; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkDocumentTypeId() { return mnFkDocumentTypeId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }
    
    public FileInputStream getAuxFileInputStream() { return moAuxInputStream; }
    public byte[] getAuxDocumentBytes_n() { return moAuxDocumentBytes_n; }
    public boolean getAuxDocumentChange() { return mbAuxDocumentChange; }

    /*
     * Overriden methods
     */

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkProjectId = pk[0];
        mnPkStageId = pk[1];
        mnPkDocumentId = pk[2];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkProjectId, mnPkStageId, mnPkDocumentId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkProjectId = 0;
        mnPkStageId = 0;
        mnPkDocumentId = 0;
        mnNumber = 0;
        msCode = "";
        msName = "";
        msDescription = "";
        moDocument_n = null;
        msDocumentName = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkDocumentTypeId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        moAuxInputStream = null;
        moAuxDocumentBytes_n = null;
        mbAuxDocumentChange = false;
    }

    @Override
    public String getSqlTable() {
        return SModConsts.TablesMap.get(SModConsts.DU_DOC);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_prj = " + mnPkProjectId + " AND " +
                "id_stg = " + mnPkStageId + " AND " +
                "id_doc = " + mnPkDocumentId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_prj = " + pk[0] + " AND " +
                "id_stg = " + pk[1] + " AND " +
                "id_doc = " + pk[2] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkDocumentId = 0;

        msSql = "SELECT COALESCE(MAX(id_doc), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_prj = " + mnPkProjectId + " AND " +
                "id_stg = " + mnPkStageId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkDocumentId = resultSet.getInt(1);
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
            mnPkDocumentId = resultSet.getInt("id_doc");
            mnNumber = resultSet.getInt("num");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            msDescription = resultSet.getString("dcrp");
            moDocument_n = resultSet.getBlob("doc_n");
            msDocumentName = resultSet.getString("doc_name");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkDocumentTypeId = resultSet.getInt("fk_doc_tp");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");
            
            moAuxDocumentBytes_n = resultSet.getBytes("doc_n");

            mbRegistryNew = false;
        }

        mnQueryResultId = SDbConsts.READ_OK;
    }

    @Override
    public void save(SGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = SDbConsts.READ_ERROR;
        PreparedStatement preparedStatement = null;

        if (mbRegistryNew) {
            mnNumber = SDevManUtils.getNextCodeNumber(session, mnRegistryType, new int[] { mnPkProjectId, mnPkStageId, mnFkDocumentTypeId });
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
                    mnPkProjectId + ", " + 
                    mnPkStageId + ", " + 
                    mnPkDocumentId + ", " + 
                    mnNumber + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + msDescription + "', " + 
                    "NULL, " + 
                    "'" + msDocumentName + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkDocumentTypeId + ", " + 
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
                    "id_stg = " + mnPkStageId + ", " +
                    "id_doc = " + mnPkDocumentId + ", " +
                    */
                    "num = " + mnNumber + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "dcrp = '" + msDescription + "', " +
                    //"doc_n = NULL, " +
                    "doc_name = '" + msDocumentName + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_doc_tp = " + mnFkDocumentTypeId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        if (mbAuxDocumentChange) {
            msSql = "UPDATE " + getSqlTable() + " SET doc_n = ? " +
                    getSqlWhere();

            preparedStatement = session.getStatement().getConnection().prepareStatement(msSql);

            if (moAuxInputStream != null) {
                preparedStatement.setBlob(1, createArrayBytesForDocument());
            }
            else {
                preparedStatement.setNull(1, java.sql.Types.INTEGER);
            }
            preparedStatement.execute();
        }
        
        mbRegistryNew = false;
        mnQueryResultId = SDbConsts.SAVE_OK;
    }

    @Override
    public SDbDocument clone() throws CloneNotSupportedException {
        SDbDocument registry = new SDbDocument();

        registry.setPkProjectId(this.getPkProjectId());
        registry.setPkStageId(this.getPkStageId());
        registry.setPkDocumentId(this.getPkDocumentId());
        registry.setNumber(this.getNumber());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDescription(this.getDescription());
        registry.setDocument_n(this.getDocument_n());
        registry.setDocumentName(this.getDocumentName());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkDocumentTypeId(this.getFkDocumentTypeId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
