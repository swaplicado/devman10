/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman;

import devman.mod.SModConsts;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import sa.lib.SLibConsts;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class SDevManUtils {
    
    public static final DecimalFormat FormatCode1 = new DecimalFormat("0");
    public static final DecimalFormat FormatCode2 = new DecimalFormat("00");
    public static final DecimalFormat FormatCode3 = new DecimalFormat("000");
    public static final DecimalFormat FormatCode4 = new DecimalFormat("0000");
    public static final DecimalFormat FormatCode5 = new DecimalFormat("00000");
    
    public static int getNextCodeNumber(final SGuiSession session, final int tableId, final int[] parentKey) throws Exception {
        int number = 0;
        String sql = "";
        ResultSet resultSet = null;
        
        sql = "SELECT COALESCE(MAX(num), 0) + 1 FROM " + SModConsts.TablesMap.get(tableId) + " WHERE b_del = 0 ";
        
        switch (tableId) {
            case SModConsts.PU_PRJ_DLV:
            case SModConsts.PU_PRJ_STG:
                sql += "AND id_prj = " + parentKey[0] + " ";
                break;
            case SModConsts.PU_PRJ_STG_PHS:
                sql += "AND id_prj = " + parentKey[0] + " AND id_stg = " + parentKey[1] + " ";
                break;
            case SModConsts.PU_PRJ_STG_PHS_ACT:
                sql += "AND id_prj = " + parentKey[0] + " AND id_stg = " + parentKey[1] + " AND id_phs = " + parentKey[2] + " ";
                break;
            case SModConsts.P_TSK:
                sql += "AND fk_act_prj = " + parentKey[0] + " AND fk_act_stg = " + parentKey[1] + " AND fk_act_phs = " + parentKey[2] + " AND fk_act_act = " + parentKey[3] + " ";
                break;
            case SModConsts.DU_REQ:
                sql += "AND id_prj = " + parentKey[0] + " AND fk_req_tp = " + parentKey[1] + " ";
                break;
            case SModConsts.DU_ADE:
                sql += "AND id_prj = " + parentKey[0] + " AND fk_ade_tp = " + parentKey[1] + " ";
                break;
            case SModConsts.DU_CMP:
                sql += "AND id_prj = " + parentKey[0] + " AND fk_cmp_tp = " + parentKey[1] + " ";
                break;
            case SModConsts.DU_DOC:
                sql += "AND id_prj = " + parentKey[0] + " AND id_stg = " + parentKey[1] + " AND fk_doc_tp = " + parentKey[2] + " ";
                break;
            case SModConsts.D_TST_PLN:
                sql += "AND fk_stg_prj = " + parentKey[0] + " AND fk_stg_stg = " + parentKey[1] + " AND fk_pln_tp = " + parentKey[2] + " ";
                break;
            case SModConsts.D_TST_PLN_TST:
                sql += "AND id_pln = " + parentKey[0] + " ";
                break;
            default:
                throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }
        
        resultSet = session.getStatement().executeQuery(sql);
        if (resultSet.next()) {
            number = resultSet.getInt(1);
        }
        
        return number;
    }
}
