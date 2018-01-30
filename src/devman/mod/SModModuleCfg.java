/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package devman.mod;

import devman.mod.cfg.db.SDbConfig;
import devman.mod.cfg.db.SDbUser;
import devman.mod.cfg.db.SDbUserGui;
import devman.mod.cfg.form.SFormUser;
import devman.mod.cfg.view.SViewUser;
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
public class SModModuleCfg extends SGuiModule {
    
    private SFormUser moFormUser;

    public SModModuleCfg(SGuiClient client) {
        super(client, SModConsts.MOD_CFG, SLibConsts.UNDEFINED);
    }

    @Override
    public JMenu[] getMenus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SDbRegistry getRegistry(int type, SGuiParams params) {
        SDbRegistry registry = null;

        switch (type) {
            case SModConsts.CS_ST:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_st = " + pk[0] + " "; }
                };
                break;
            case SModConsts.CS_USR_TP:
                registry = new SDbRegistrySysFly(type) {
                    public void initRegistry() { }
                    public String getSqlTable() { return SModConsts.TablesMap.get(mnRegistryType); }
                    public String getSqlWhere(int[] pk) { return "WHERE id_usr_tp = " + pk[0] + " "; }
                };
                break;
            case SModConsts.CU_USR:
                registry = new SDbUser();
                break;
            case SModConsts.C_CFG:
                registry = new SDbConfig();
                break;
            case SModConsts.C_USR_GUI:
                registry = new SDbUserGui();
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
            case SModConsts.CS_ST:
                switch (subtype) {
                    case SModConsts.PU_PRJ:
                    case SModConsts.P_TSK:
                    case SModConsts.D_TST_PLN:
                    case SModConsts.D_TST_PLN_TST:
                        filter = "AND id_st IN (" + SModSysConsts.CS_ST_PND + ", " + SModSysConsts.CS_ST_PRC + ", " + SModSysConsts.CS_ST_FIN + ", " + SModSysConsts.CS_ST_CAN + ") ";
                        break;
                    default:
                }
                
                settings = new SGuiCatalogueSettings("Estatus", 1);
                if (params != null && params.getType() == SDbRegistry.FIELD_CODE) {
                    sql = "SELECT id_st AS " + SDbConsts.FIELD_ID + "1, code AS " + SDbConsts.FIELD_ITEM + " "
                            + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 " + filter + "ORDER BY sort ";
                }
                else {
                    sql = "SELECT id_st AS " + SDbConsts.FIELD_ID + "1, name AS " + SDbConsts.FIELD_ITEM + " "
                            + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 " + filter + "ORDER BY sort ";
                }
                break;
            case SModConsts.CS_USR_TP:
                settings = new SGuiCatalogueSettings("Tipo usuario", 1);
                sql = "SELECT id_usr_tp AS " + SDbConsts.FIELD_ID + "1, name AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case SModConsts.CU_USR:
                switch (subtype) {
                    case SModConsts.PU_PRJ:
                    case SModConsts.P_TSK:
                        filter = "AND id_usr > " + SModSysConsts.CS_USR_TP_SUP + " ";
                        break;
                    default:
                }
                
                settings = new SGuiCatalogueSettings("Usuario", 1);
                sql = "SELECT id_usr AS " + SDbConsts.FIELD_ID + "1, name AS " + SDbConsts.FIELD_ITEM + " "
                        + "FROM " + SModConsts.TablesMap.get(type) + " WHERE b_del = 0 " + filter + "ORDER BY name, id_usr ";
                break;
            case SModConsts.C_CFG:
            case SModConsts.C_USR_GUI:
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
            case SModConsts.CS_ST:
            case SModConsts.CS_USR_TP:
                break;
            case SModConsts.CU_USR:
                view = new SViewUser(miClient, "Usuarios");
                break;
            case SModConsts.C_CFG:
            case SModConsts.C_USR_GUI:
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
            case SModConsts.CS_ST:
            case SModConsts.CS_USR_TP:
            case SModConsts.CU_USR:
                if (moFormUser == null) moFormUser = new SFormUser(miClient, "Usuario");
                form = moFormUser;
                break;
            case SModConsts.C_CFG:
            case SModConsts.C_USR_GUI:
                break;
            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return form;
    }

    @Override
    public SGuiReport getReport(int type, int subtype, SGuiParams params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
