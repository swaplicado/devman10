/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod;

import java.util.HashMap;

/**
 *
 * @author Sergio Flores
 */
public abstract class SModConsts {
    
    public static final int XX_GUI_TME = 5001;
    public static final int XX_GUI_LOG = 5002;

    public static final int MOD_CFG = 110000;
    public static final int MOD_PRJ = 210000;
    public static final int MOD_DEV = 310000;

    public static final int SU_SYS = 110001;
    public static final int SU_COM = 110002;

    public static final int CS_ST = 111001;
    public static final int CS_USR_TP = 111011;

    public static final int CU_USR = 112011;

    public static final int C_CFG = 113001;
    public static final int C_USR_GUI = 113101;

    public static final int PS_PRJ_TP = 211001;
    public static final int PS_PRJ_PHS_TP = 211021;
    public static final int PS_TSK_TP = 211101;
    public static final int PS_WRK_TP = 211111;

    public static final int PU_PRJ = 212001;
    public static final int PU_PRJ_DLV = 212006;
    public static final int PU_PRJ_STG = 212011;
    public static final int PU_PRJ_STG_PHS = 212012;
    public static final int PU_PRJ_STG_PHS_ACT = 212021;

    public static final int P_TSK = 213101;
    public static final int P_TSK_LOG = 213109;
    
    public static final int PX_WRK = 215001;        // work
    public static final int PX_PRJ_STA = 215011;    // project statistics

    public static final int DS_REQ_TP = 311011;
    public static final int DS_ADE_TP = 311016;
    public static final int DS_CMP_TP = 311017;
    public static final int DS_DOC_TP = 311021;
    public static final int DS_TST_PLN_TP = 311031;
    public static final int DS_TST_RES_TP = 311036;
    public static final int DS_TRC_REF_TP = 311041;

    public static final int DU_REQ = 312011;
    public static final int DU_ADE = 312016;
    public static final int DU_CMP = 312017;
    public static final int DU_DOC = 312021;

    public static final int D_TST_PLN = 313031;
    public static final int D_TST_PLN_TST = 313032;
    public static final int D_TST_PLN_TST_LOG = 313039;
    public static final int D_TRC = 313041;
    public static final int D_TRC_LOG = 313049;

    public static final int DR_TRC = 314041;
    
    public static final int DX_TRC_WZD = 315041;
    
    public static final HashMap<Integer, String> TablesMap = new HashMap<Integer, String>();

    static {
        TablesMap.put(SU_SYS, "su_sys");
        TablesMap.put(SU_COM, "su_com");

        TablesMap.put(CS_ST, "cs_st");
        TablesMap.put(CS_USR_TP, "cs_usr_tp");

        TablesMap.put(CU_USR, "cu_usr");

        TablesMap.put(C_CFG, "c_cfg");
        TablesMap.put(C_USR_GUI, "c_usr_gui");

        TablesMap.put(PS_PRJ_TP, "ps_prj_tp");
        TablesMap.put(PS_PRJ_PHS_TP, "ps_prj_phs_tp");
        TablesMap.put(PS_TSK_TP, "ps_tsk_tp");
        TablesMap.put(PS_WRK_TP, "ps_wrk_tp");

        TablesMap.put(PU_PRJ, "pu_prj");
        TablesMap.put(PU_PRJ_DLV, "pu_prj_dlv");
        TablesMap.put(PU_PRJ_STG, "pu_prj_stg");
        TablesMap.put(PU_PRJ_STG_PHS, "pu_prj_stg_phs");
        TablesMap.put(PU_PRJ_STG_PHS_ACT, "pu_prj_stg_phs_act");

        TablesMap.put(P_TSK, "p_tsk");
        TablesMap.put(P_TSK_LOG, "p_tsk_log");

        TablesMap.put(DS_REQ_TP, "ds_req_tp");
        TablesMap.put(DS_ADE_TP, "ds_ade_tp");
        TablesMap.put(DS_CMP_TP, "ds_cmp_tp");
        TablesMap.put(DS_DOC_TP, "ds_doc_tp");
        TablesMap.put(DS_TST_PLN_TP, "ds_tst_pln_tp");
        TablesMap.put(DS_TST_RES_TP, "ds_tst_res_tp");
        TablesMap.put(DS_TRC_REF_TP, "ds_trc_ref_tp");

        TablesMap.put(DU_REQ, "du_req");
        TablesMap.put(DU_ADE, "du_ade");
        TablesMap.put(DU_CMP, "du_cmp");
        TablesMap.put(DU_DOC, "du_doc");

        TablesMap.put(D_TST_PLN, "d_tst_pln");
        TablesMap.put(D_TST_PLN_TST, "d_tst_pln_tst");
        TablesMap.put(D_TST_PLN_TST_LOG, "d_tst_pln_tst_log");
        TablesMap.put(D_TRC, "d_trc");
        TablesMap.put(D_TRC_LOG, "d_trc_log");
    }
}
