/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package devman.mod;

import devman.mod.dev.db.SDbComponent;
import devman.mod.dev.db.SDbDocument;
import devman.mod.dev.db.SDbElement;
import devman.mod.dev.db.SDbRequirement;
import devman.mod.dev.db.SDbTestPlan;
import devman.mod.dev.db.SDbTestPlanTest;
import devman.mod.dev.db.SDbTestPlanTestLog;
import devman.mod.dev.db.SDbTrace;
import devman.mod.dev.db.SDbTraceLog;
import devman.mod.dev.db.SDevTraceWizard;
import devman.mod.dev.form.SFormComponent;
import devman.mod.dev.form.SFormDocument;
import devman.mod.dev.form.SFormElement;
import devman.mod.dev.form.SFormRequirement;
import devman.mod.dev.form.SFormTestPlan;
import devman.mod.dev.form.SFormTestPlanTest;
import devman.mod.dev.form.SFormTestPlanTestLog;
import devman.mod.dev.form.SFormTrace;
import devman.mod.dev.form.SFormTraceLog;
import devman.mod.dev.view.SViewComponent;
import devman.mod.dev.view.SViewDocument;
import devman.mod.dev.view.SViewElement;
import devman.mod.dev.view.SViewRequirement;
import devman.mod.dev.view.SViewTestPlan;
import devman.mod.dev.view.SViewTestPlanTest;
import devman.mod.dev.view.SViewTestPlanTestLog;
import devman.mod.dev.view.SViewTrace;
import devman.mod.dev.view.SViewTraceLog;
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
 * @author Juan Barajas
 */
public class SModModuleDev extends SGuiModule {

    private SFormRequirement moFormRequirement;
    private SFormElement moFormElement;
    private SFormComponent moFormComponent;
    private SFormDocument moFormDocument;
    private SFormTestPlan moFormTestPlan;
    private SFormTestPlanTest moFormTestPlanTest;
    private SFormTestPlanTestLog moFormTestPlanTestLog;
    private SFormTrace moFormTrace;
    private SFormTraceLog moFormTraceLog;
    
    public SModModuleDev(SGuiClient client) {
        super(client, SModConsts.MOD_DEV, SLibConsts.UNDEFINED);
    }

    @Override
    public JMenu[] getMenus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SDbRegistry getRegistry(int type, SGuiParams params) {
        SDbRegistry registry = null;

        switch (type) {
            case SModConsts.DS_REQ_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_req_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.DS_ADE_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_ade_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.DS_CMP_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_cmp_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.DS_DOC_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_doc_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.DS_TST_PLN_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_pln_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.DS_TST_RES_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_res_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.DS_TRC_REF_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_ref_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.DU_REQ:
                registry = new SDbRequirement();
                break;
            case SModConsts.DU_ADE:
                registry = new SDbElement();
                break;
            case SModConsts.DU_CMP:
                registry = new SDbComponent();
                break;
            case SModConsts.DU_DOC:
                registry = new SDbDocument();
                break;
            case SModConsts.D_TST_PLN:
                registry = new SDbTestPlan();
                break;
            case SModConsts.D_TST_PLN_TST:
                registry = new SDbTestPlanTest();
                break;
            case SModConsts.D_TST_PLN_TST_LOG:
                registry = new SDbTestPlanTestLog();
                break;
            case SModConsts.D_TRC:
                registry = new SDbTrace();
                break;
            case SModConsts.DX_TRC_WZD:
                registry = new SDevTraceWizard();
                break;
            case SModConsts.D_TRC_LOG:
                registry = new SDbTraceLog();
                break;
            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return registry;
    }

    @Override
    public SGuiCatalogueSettings getCatalogueSettings(int type, int subtype, SGuiParams params) {
        String sql = "";
        SGuiCatalogueSettings settings = null;

        switch (type) {
            case SModConsts.DS_REQ_TP:
                settings = new SGuiCatalogueSettings("Tipo requerimiento", 1);
                sql = "SELECT id_req_tp AS " + SDbConsts.FIELD_ID + "1, CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.DS_ADE_TP:
                settings = new SGuiCatalogueSettings("Tipo elemento A&D", 1);
                sql = "SELECT id_ade_tp AS " + SDbConsts.FIELD_ID + "1, CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.DS_CMP_TP:
                settings = new SGuiCatalogueSettings("Tipo componente", 1);
                sql = "SELECT id_cmp_tp AS " + SDbConsts.FIELD_ID + "1, CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.DS_DOC_TP:
                settings = new SGuiCatalogueSettings("Tipo documento", 1);
                sql = "SELECT id_doc_tp AS " + SDbConsts.FIELD_ID + "1, CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.DS_TST_PLN_TP:
                settings = new SGuiCatalogueSettings("Tipo plan pruebas", 1);
                sql = "SELECT id_pln_tp AS " + SDbConsts.FIELD_ID + "1, CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.DS_TST_RES_TP:
                settings = new SGuiCatalogueSettings("Tipo resultado", 1);
                sql = "SELECT id_res_tp AS " + SDbConsts.FIELD_ID + "1, name AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.DS_TRC_REF_TP:
                settings = new SGuiCatalogueSettings("Tipo referencia", 1);
                sql = "SELECT id_ref_tp AS " + SDbConsts.FIELD_ID + "1, name AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.DU_REQ:
                settings = new SGuiCatalogueSettings("Requerimiento", 2, 1);
                sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, id_req AS " + SDbConsts.FIELD_ID + "2, "
                        + "id_prj AS " + SDbConsts.FIELD_FK + "1, "
                        + "CONCAT(code, ' - ', name) AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " "
                        + "WHERE b_del = 0 " + (subtype != SLibConsts.UNDEFINED ? "AND id_prj = " + subtype + " " : "")
                        + "ORDER BY code, name, id_prj, id_req ";
                break;
            case SModConsts.DU_ADE:
                settings = new SGuiCatalogueSettings("Elemento A&D", 2, 1);
                sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, id_ade AS " + SDbConsts.FIELD_ID + "2, "
                        + "id_prj AS " + SDbConsts.FIELD_FK + "1, "
                        + "CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " "
                        + "WHERE b_del = 0 " + (subtype != SLibConsts.UNDEFINED ? "AND id_prj = " + subtype + " " : "")
                        + "ORDER BY name, code, id_prj, id_ade ";
                break;
            case SModConsts.DU_CMP:
                settings = new SGuiCatalogueSettings("Componente", 2, 1);
                sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, id_cmp AS " + SDbConsts.FIELD_ID + "2, "
                        + "id_prj AS " + SDbConsts.FIELD_FK + "1, "
                        + "CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " "
                        + "WHERE b_del = 0 " + (subtype != SLibConsts.UNDEFINED ? "AND id_prj = " + subtype + " " : "")
                        + "ORDER BY name, code, id_prj, id_cmp ";
                break;
            case SModConsts.DU_DOC:
                settings = new SGuiCatalogueSettings("Documento", 3, 2);
                sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, id_stg AS " + SDbConsts.FIELD_ID + "2, id_doc AS " + SDbConsts.FIELD_ID + "3, "
                        + "id_prj AS " + SDbConsts.FIELD_FK + "1, id_stg AS " + SDbConsts.FIELD_FK + "2, "
                        + "CONCAT(name, ' (', code, ')', ' [', doc_name, ']') AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " "
                        + "WHERE b_del = 0 " + (subtype != SLibConsts.UNDEFINED ? "AND id_prj = " + subtype + " " : "")
                        + "ORDER BY name, doc_name, id_prj, id_stg, id_doc ";
                break;
            case SModConsts.D_TST_PLN:
                settings = new SGuiCatalogueSettings("Plan pruebas", 1, 2);
                sql = "SELECT id_pln AS " + SDbConsts.FIELD_ID + "1, "
                        + "fk_stg_prj AS " + SDbConsts.FIELD_FK + "1, "
                        + "fk_stg_stg AS " + SDbConsts.FIELD_FK + "2, "
                        + "CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " "
                        + "WHERE b_del = 0 " + (subtype != SLibConsts.UNDEFINED ? "AND fk_pln_tp = " + subtype + " " : "")
                        + "ORDER BY name, code, id_pln ";
                break;
            case SModConsts.D_TST_PLN_TST:
                settings = new SGuiCatalogueSettings("Prueba", 2, 1);
                sql = "SELECT id_pln AS " + SDbConsts.FIELD_ID + "1, id_tst AS " + SDbConsts.FIELD_ID + "2, "
                        + "id_pln AS " + SDbConsts.FIELD_FK + "1, "
                        + "CONCAT(name, ' (', code, ')') AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " "
                        + "WHERE b_del = 0 " + (subtype != SLibConsts.UNDEFINED ? "AND id_pln = " + subtype + " " : "")
                        + "ORDER BY name, code, id_pln, id_tst ";
                break;
            case SModConsts.D_TRC:
                settings = new SGuiCatalogueSettings("Registro rastreo", 2, 1);
                sql = "SELECT id_prj AS " + SDbConsts.FIELD_ID + "1, id_trc AS " + SDbConsts.FIELD_ID + "2, "
                        + "id_prj AS " + SDbConsts.FIELD_FK + "1, "
                        + "name AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " "
                        + "WHERE b_del = 0 "
                        + "ORDER BY id_prj, id_trc ";
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
        SGridPaneView view = null;

        switch (type) {
            case SModConsts.DU_REQ:
                view = new SViewRequirement(miClient, "Requerimientos");
                break;
            case SModConsts.DU_ADE:
                view = new SViewElement(miClient, "Elementos A&D");
                break;
            case SModConsts.DU_CMP:
                view = new SViewComponent(miClient, "Componentes");
                break;
            case SModConsts.DU_DOC:
                view = new SViewDocument(miClient, "Documentos");
                break;
            case SModConsts.D_TST_PLN:
                view = new SViewTestPlan(miClient, "Planes pruebas");
                break;
            case SModConsts.D_TST_PLN_TST:
                view = new SViewTestPlanTest(miClient, "Pruebas");
                break;
            case SModConsts.D_TST_PLN_TST_LOG:
                view = new SViewTestPlanTestLog(miClient, "Bitácora pruebas");
                break;
            case SModConsts.D_TRC:
                view = new SViewTrace(miClient, "Registro rastreo");
                break;
            case SModConsts.D_TRC_LOG:
                view = new SViewTraceLog(miClient, "Bitácora registro rastreo");
                break;
            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return view;
    }

    @Override
    public SGuiOptionPicker getOptionPicker(int type, int subtype, SGuiParams params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SGuiForm getForm(int type, int subtype, SGuiParams params) {
        SGuiForm form = null;

        switch (type) {
            case SModConsts.DU_REQ:
                if (moFormRequirement == null) moFormRequirement = new SFormRequirement(miClient, "Requerimiento");
                form = moFormRequirement;
                break;
            case SModConsts.DU_ADE:
                if (moFormElement == null) moFormElement = new SFormElement(miClient, "Elemento de A&D");
                form = moFormElement;
                break;
            case SModConsts.DU_CMP:
                if (moFormComponent == null) moFormComponent = new SFormComponent(miClient, "Componente");
                form = moFormComponent;
                break;
            case SModConsts.DU_DOC:
                if (moFormDocument == null) moFormDocument = new SFormDocument(miClient, "Documento");
                form = moFormDocument;
                break;
            case SModConsts.D_TST_PLN:
                if (moFormTestPlan == null) moFormTestPlan = new SFormTestPlan(miClient, "Plan de Pruebas");
                form = moFormTestPlan;
                break;
            case SModConsts.D_TST_PLN_TST:
                if (moFormTestPlanTest == null) moFormTestPlanTest = new SFormTestPlanTest(miClient, "Prueba");
                form = moFormTestPlanTest;
                break;
            case SModConsts.D_TST_PLN_TST_LOG:
                if (moFormTestPlanTestLog == null) moFormTestPlanTestLog = new SFormTestPlanTestLog(miClient, "Entrada de bitácora de pruebas");
                form = moFormTestPlanTestLog;
                break;
            case SModConsts.D_TRC:
                if (moFormTrace == null) moFormTrace = new SFormTrace(miClient, "Registro de rastreo");
                form = moFormTrace;
                break;
            case SModConsts.D_TRC_LOG:
                if (moFormTraceLog == null) moFormTraceLog = new SFormTraceLog(miClient, "Entrada de bitácora de registro de rastreo");
                form = moFormTraceLog;
                break;
            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return form;
    }

    @Override
    public SGuiReport getReport(int type, int subtype, SGuiParams params) {
        SGuiReport report = null;

        switch (type) {
            case SModConsts.DR_TRC:
                report = new SGuiReport("reps/trace.jasper", "Registro de rastreo");
                break;
            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return report;
    }
}
