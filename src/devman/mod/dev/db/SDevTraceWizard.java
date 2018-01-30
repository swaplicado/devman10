/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.db;

import devman.mod.SModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistryUser;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class SDevTraceWizard extends SDbRegistryUser {
    
    protected int mnPkPhaseProjectId;
    protected int mnPkPhaseStageId;
    protected int mnPkPhasePhaseId;
    protected Date mtTraceDate;
    
    public SDevTraceWizard() {
        super(SModConsts.DX_TRC_WZD);
    }

    public void setPkPhaseProjectId(int n) { mnPkPhaseProjectId = n; }
    public void setPkPhaseStageId(int n) { mnPkPhaseStageId = n; }
    public void setPkPhasePhaseId(int n) { mnPkPhasePhaseId = n; }
    public void setTraceDate(Date t) { mtTraceDate = t; }
    
    public int getPkPhaseProjectId() { return mnPkPhaseProjectId; }
    public int getPkPhaseStageId() { return mnPkPhaseStageId; }
    public int getPkPhasePhaseId() { return mnPkPhasePhaseId; }
    public Date getTraceDate() { return mtTraceDate; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkPhaseProjectId = pk[0];
        mnPkPhaseStageId = pk[1];
        mnPkPhasePhaseId = pk[2];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkPhaseProjectId, mnPkPhaseStageId, mnPkPhasePhaseId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();
        
        mnPkPhaseProjectId = 0;
        mnPkPhaseStageId = 0;
        mnPkPhasePhaseId = 0;
        mtTraceDate = null;
    }

    @Override
    public String getSqlTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSqlWhere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSqlWhere(int[] pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void read(SGuiSession session, int[] pk) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(SGuiSession session) throws SQLException, Exception {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        SDbTrace trace = null;
        ArrayList<Integer> reqs = new ArrayList<>();
        
        initQueryMembers();
        mnQueryResultId = SDbConsts.SAVE_ERROR;
        
        statement = session.getStatement().getConnection().createStatement();

        sql = "SELECT DISTINCT fk_req_req "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.D_TRC) + " "
                + "WHERE fk_phs_prj = " + mnPkPhaseProjectId + " AND fk_phs_stg = " + mnPkPhaseStageId + " AND fk_phs_phs = " + mnPkPhasePhaseId + " AND "
                + "b_del = 0 "
                + "ORDER BY fk_req_req ";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            reqs.add(resultSet.getInt(1));
        }
        
        sql = "SELECT id_req "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.DU_REQ) + " "
                + "WHERE id_prj = " + mnPkPhaseProjectId + " AND "
                + "b_del = 0 "
                + "ORDER BY id_req ";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            if (!reqs.contains(resultSet.getInt(1))) {
                trace = new SDbTrace();
                trace.setPkProjectId(mnPkPhaseProjectId);
                //trace.setPkTraceId(...);
                trace.setTraceDate(mtTraceDate);
                trace.setNotes("");
                //trace.setDeleted(...);
                //trace.setSystem(...);
                trace.setFkRequirementProjectId(mnPkPhaseProjectId);
                trace.setFkRequirementRequirementId(resultSet.getInt(1));
                trace.setFkPhaseProjectId(mnPkPhaseProjectId);
                trace.setFkPhaseStageId(mnPkPhaseStageId);
                trace.setFkPhasePhaseId(mnPkPhasePhaseId);
                trace.save(session);
            }
        }
        
        mbRegistryNew = false;
        mnQueryResultId = SDbConsts.SAVE_OK;
    }

    @Override
    public SDevTraceWizard clone() throws CloneNotSupportedException {
        SDevTraceWizard registry = new SDevTraceWizard();
                
        registry.setPkPhaseProjectId(this.getPkPhaseProjectId());
        registry.setPkPhaseStageId(this.getPkPhaseStageId());
        registry.setPkPhasePhaseId(this.getPkPhasePhaseId());
        registry.setTraceDate(this.getTraceDate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
