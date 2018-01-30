/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod;

import devman.mod.prj.db.SDbProject;
import devman.mod.prj.db.SDbProjectDeliverable;
import devman.mod.prj.db.SDbProjectStage;
import devman.mod.prj.db.SDbProjectStagePhase;
import devman.mod.prj.db.SDbProjectStagePhaseActivity;
import devman.mod.prj.db.SDbTask;
import devman.mod.prj.db.SDbTaskLog;
import devman.mod.prj.form.SDlgProjectStats;
import devman.mod.prj.form.SFormProject;
import devman.mod.prj.form.SFormProjectDeliverable;
import devman.mod.prj.form.SFormProjectStage;
import devman.mod.prj.form.SFormProjectStagePhase;
import devman.mod.prj.form.SFormProjectStagePhaseActivity;
import devman.mod.prj.form.SFormTask;
import devman.mod.prj.form.SFormTaskLog;
import devman.mod.prj.view.SViewProject;
import devman.mod.prj.view.SViewProjectDeliverable;
import devman.mod.prj.view.SViewProjectStage;
import devman.mod.prj.view.SViewProjectStagePhase;
import devman.mod.prj.view.SViewProjectStagePhaseActivity;
import devman.mod.prj.view.SViewTask;
import devman.mod.prj.view.SViewTaskLog;
import devman.mod.prj.view.SViewWork;
import javax.swing.JMenu;
import sa.lib.SLibConsts;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.db.SDbRegistrySysFly;
import sa.lib.grid.SGridPaneView;
import sa.lib.gui.SGuiCatalogueSettings;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiForm;
import sa.lib.gui.SGuiModule;
import sa.lib.gui.SGuiOptionPicker;
import sa.lib.gui.SGuiParams;
import sa.lib.gui.SGuiReport;

/**
 *
 * @author Sergio Flores
 */
public class SModModulePrj extends SGuiModule {
    
    private SFormProject moFormProject;
    private SFormProjectDeliverable moFormProjectDeliverable;
    private SFormProjectStage moFormProjectStage;
    private SFormProjectStagePhase moFormProjectStagePhase;
    private SFormProjectStagePhaseActivity moFormProjectStagePhaseActivity;
    private SFormTask moFormTask;
    private SFormTaskLog moFormTaskLog;
    private SDlgProjectStats moDlgProjectStats;

    public SModModulePrj(SGuiClient client) {
        super(client, SModConsts.MOD_PRJ, SLibConsts.UNDEFINED);
    }

    @Override
    public JMenu[] getMenus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SDbRegistry getRegistry(int type, SGuiParams params) {
        SDbRegistry registry = null;

        switch (type) {
            case SModConsts.PS_PRJ_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_prj_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.PS_PRJ_PHS_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_prj_tp = " + pk[0] + " AND id_phs_tp = " + pk[1] + " "; }
                };
                break;
            case SModConsts.PS_TSK_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_tsk_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.PS_WRK_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_wrk_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.PU_PRJ:
            case SModConsts.PX_PRJ_STA:
                registry = new SDbProject();
                break;
            case SModConsts.PU_PRJ_DLV:
                registry = new SDbProjectDeliverable();
                break;
            case SModConsts.PU_PRJ_STG:
                registry = new SDbProjectStage();
                break;
            case SModConsts.PU_PRJ_STG_PHS:
                registry = new SDbProjectStagePhase();
                break;
            case SModConsts.PU_PRJ_STG_PHS_ACT:
                registry = new SDbProjectStagePhaseActivity();
                break;
            case SModConsts.P_TSK:
                registry = new SDbTask();
                break;
            case SModConsts.P_TSK_LOG:
                registry = new SDbTaskLog();
                break;
            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return registry;
    }

    @Override
    public SGuiCatalogueSettings getCatalogueSettings(int type, int subtype, SGuiParams params) {
        String sql = "";
        String filter = "";
        SGuiCatalogueSettings settings = null;

        switch (type) {
            case SModConsts.PS_PRJ_TP:
                settings = new SGuiCatalogueSettings("Tipo proyecto", 1);
                sql = "SELECT id_prj_tp AS " + SDbConsts.FIELD_ID + "1, name AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.PS_PRJ_PHS_TP:
                settings = new SGuiCatalogueSettings("Tipo fase", 2);
                sql = "SELECT id_prj_tp AS " + SDbConsts.FIELD_ID + "1, id_phs_tp AS " + SDbConsts.FIELD_ID + "2, name AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 AND id_prj_tp = " + subtype + " ORDER BY sort ";
                break;
            case SModConsts.PS_TSK_TP:
                settings = new SGuiCatalogueSettings("Tipo tarea", 1);
                sql = "SELECT id_tsk_tp AS " + SDbConsts.FIELD_ID + "1, name AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.PS_WRK_TP:
                settings = new SGuiCatalogueSettings("Tipo trabajo", 1);
                sql = "SELECT id_wrk_tp AS " + SDbConsts.FIELD_ID + "1, name AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.PU_PRJ:
                settings = new SGuiCatalogueSettings("Proyecto", 1);
                if (params != null && params.getType() == SDbRegistry.FIELD_CODE) {
                    sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, code AS " + SDbConsts.FIELD_ITEM + " "
                            + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY code, id_prj ";
                }
                else {
                    sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + " "
                            + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, code, id_prj ";
                }
                break;
            case SModConsts.PU_PRJ_DLV:
                if (params != null && params.getKey() != null) {
                    filter += "AND id_prj = " + params.getKey()[0] + " ";
                }
                
                settings = new SGuiCatalogueSettings("Entregable", 2, 1);
                sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, id_dlv AS " + SDbConsts.FIELD_ID + "2, CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + ", "
                        + "id_prj AS " + SDbConsts.FIELD_FK + "1 "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 " + filter + "ORDER BY name, code, id_prj, id_dlv ";
                break;
            case SModConsts.PU_PRJ_STG:
                if (params != null && params.getKey() != null) {
                    filter += "AND id_prj = " + params.getKey()[0] + " ";
                }
                
                settings = new SGuiCatalogueSettings("Etapa", 2, 1);
                sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, id_stg AS " + SDbConsts.FIELD_ID + "2, CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + ", "
                        + "id_prj AS " + SDbConsts.FIELD_FK + "1 "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 " + filter + "ORDER BY name, code, id_prj, id_stg ";
                break;
            case SModConsts.PU_PRJ_STG_PHS:
                if (params != null && params.getKey() != null) {
                    filter += "AND id_prj = " + params.getKey()[0] + " ";
                }
                
                if (subtype == SModConsts.D_TRC) {
                    filter += "AND fk_phs_tp_prj_tp = " + SModSysConsts.PS_PRJ_PHS_TP_DEV_ANA_DSG[0] + " AND "
                            + "fk_phs_tp_phs_tp BETWEEN " + SModSysConsts.PS_PRJ_PHS_TP_DEV_ANA_DSG[1] + " AND " + SModSysConsts.PS_PRJ_PHS_TP_DEV_INT_TST[1] + " ";
                }
                
                settings = new SGuiCatalogueSettings("Fase", 3, 2);
                sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, id_stg AS " + SDbConsts.FIELD_ID + "2, id_phs AS " + SDbConsts.FIELD_ID + "3, CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + ", "
                        + "id_prj AS " + SDbConsts.FIELD_FK + "1, id_stg AS " + SDbConsts.FIELD_FK + "2 "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 " + filter + "ORDER BY name, code, id_prj, id_stg, id_phs ";
                break;
            case SModConsts.PU_PRJ_STG_PHS_ACT:
                if (params != null && params.getKey() != null) {
                    filter += "AND id_prj = " + params.getKey()[0] + " ";
                }
                
                settings = new SGuiCatalogueSettings("Actividad", 4, 3);
                sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, id_stg AS " + SDbConsts.FIELD_ID + "2, id_phs AS " + SDbConsts.FIELD_ID + "3, id_act AS " + SDbConsts.FIELD_ID + "4, CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + ", "
                        + "id_prj AS " + SDbConsts.FIELD_FK + "1, id_stg AS " + SDbConsts.FIELD_FK + "2, id_phs AS " + SDbConsts.FIELD_FK + "3 "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 " + filter + "ORDER BY name, code, id_prj, id_stg, id_phs, id_act ";
                break;
            case SModConsts.P_TSK:
            case SModConsts.P_TSK_LOG:
                break;
            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        if (settings != null) {
            settings.setSql(sql);
        }

        return settings;
    }

    @Override
    public SGridPaneView getView(int type, int subtype, SGuiParams params) {
        String title = "";
        SGridPaneView view = null;

        switch (type) {
            case SModConsts.PS_PRJ_TP:
            case SModConsts.PS_PRJ_PHS_TP:
            case SModConsts.PS_TSK_TP:
            case SModConsts.PS_WRK_TP:
                break;
            case SModConsts.PU_PRJ:
                view = new SViewProject(miClient, "Proyectos");
                break;
            case SModConsts.PU_PRJ_DLV:
                view = new SViewProjectDeliverable(miClient, "Entregables");
                break;
            case SModConsts.PU_PRJ_STG:
                view = new SViewProjectStage(miClient, "Etapas");
                break;
            case SModConsts.PU_PRJ_STG_PHS:
                view = new SViewProjectStagePhase(miClient, "Fases");
                break;
            case SModConsts.PU_PRJ_STG_PHS_ACT:
                view = new SViewProjectStagePhaseActivity(miClient, "Actividades");
                break;
            case SModConsts.P_TSK:
                title = "Tareas";
                switch (subtype) {
                    case SLibConsts.UNDEFINED:
                        break;
                    case SModSysConsts.CS_ST_PND:
                        title += " pend.";
                        break;
                    case SModSysConsts.CS_ST_PRC:
                        title += " proc.";
                        break;
                    case SModSysConsts.CS_ST_FIN:
                        title += " term.";
                        break;
                    case SModSysConsts.CS_ST_CAN:
                        title += " canc.";
                        break;
                    default:
                }
                view = new SViewTask(miClient, subtype, title);
                break;
            case SModConsts.P_TSK_LOG:
                view = new SViewTaskLog(miClient, "Bitácora tareas");
                break;
            case SModConsts.PX_WRK:
                view = new SViewWork(miClient, "Trabajo asignado");
                break;
            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return view;
    }

    @Override
    public SGuiOptionPicker getOptionPicker(int type, int subtype, SGuiParams params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SGuiForm getForm(int type, int subtype, SGuiParams params) {
        SGuiForm form = null;

        switch (type) {
            case SModConsts.PS_PRJ_TP:
            case SModConsts.PS_PRJ_PHS_TP:
            case SModConsts.PS_TSK_TP:
            case SModConsts.PS_WRK_TP:
                break;
            case SModConsts.PU_PRJ:
                if (moFormProject == null) moFormProject = new SFormProject(miClient, "Proyecto");
                form = moFormProject;
                break;
            case SModConsts.PU_PRJ_DLV:
                if (moFormProjectDeliverable == null) moFormProjectDeliverable = new SFormProjectDeliverable(miClient, "Entregable");
                form = moFormProjectDeliverable;
                break;
            case SModConsts.PU_PRJ_STG:
                if (moFormProjectStage == null) moFormProjectStage = new SFormProjectStage(miClient, "Etapa");
                form = moFormProjectStage;
                break;
            case SModConsts.PU_PRJ_STG_PHS:
                if (moFormProjectStagePhase == null) moFormProjectStagePhase = new SFormProjectStagePhase(miClient, "Fase");
                form = moFormProjectStagePhase;
                break;
            case SModConsts.PU_PRJ_STG_PHS_ACT:
                if (moFormProjectStagePhaseActivity == null) moFormProjectStagePhaseActivity = new SFormProjectStagePhaseActivity(miClient, "Actividad");
                form = moFormProjectStagePhaseActivity;
                break;
            case SModConsts.P_TSK:
                if (moFormTask == null) moFormTask = new SFormTask(miClient, "Tarea");
                form = moFormTask;
                break;
            case SModConsts.P_TSK_LOG:
                if (moFormTaskLog == null) moFormTaskLog = new SFormTaskLog(miClient, "Entrada de bitácora de tareas");
                form = moFormTaskLog;
                break;
            case SModConsts.PX_PRJ_STA:
                if (moDlgProjectStats == null) moDlgProjectStats = new SDlgProjectStats(miClient, "Estadísticas del proyecto");
                form = moDlgProjectStats;
                break;
            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return form;
    }

    @Override
    public SGuiReport getReport(int type, int subtype, SGuiParams params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
