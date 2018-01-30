/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod;

/**
 *
 * @author Sergio Flores
 */
public abstract class SModSysConsts {

    public static final int CS_ST_PND = 1;
    public static final int CS_ST_APR = 2;
    public static final int CS_ST_REJ = 3;
    public static final int CS_ST_PRC = 6;
    public static final int CS_ST_FIN = 8;
    public static final int CS_ST_CAN = 9;

    public static final int CS_USR_TP_USR = 1;
    public static final int CS_USR_TP_ADM = 2;
    public static final int CS_USR_TP_SUP = 3;

    public static final int CU_USR_NA = 1;
    public static final int CU_USR_ADM = 2;
    public static final int CU_USR_SUP = 3;
    
    public static final int C_CFG_CFG = 1;
    
    public static final int PS_PRJ_TP_DEV = 1;
    public static final int PS_PRJ_TP_DEP = 2;
    
    public static final int[] PS_PRJ_PHS_TP_DEV_STA = new int[] { 1, 1 };
    public static final int[] PS_PRJ_PHS_TP_DEV_REQ = new int[] { 1, 2 };
    public static final int[] PS_PRJ_PHS_TP_DEV_ANA_DSG = new int[] { 1, 3 };
    public static final int[] PS_PRJ_PHS_TP_DEV_CON = new int[] { 1, 4 };
    public static final int[] PS_PRJ_PHS_TP_DEV_INT_TST = new int[] { 1, 5 };
    public static final int[] PS_PRJ_PHS_TP_DEV_CLS = new int[] { 1, 6 };
    public static final int[] PS_PRJ_PHS_TP_DEP_STA = new int[] { 2, 1 };
    public static final int[] PS_PRJ_PHS_TP_DEP_DIA = new int[] { 2, 2 };
    public static final int[] PS_PRJ_PHS_TP_DEP_SET_CFG = new int[] { 2, 3 };
    public static final int[] PS_PRJ_PHS_TP_DEP_TRA = new int[] { 2, 4 };
    public static final int[] PS_PRJ_PHS_TP_DEP_CLS = new int[] { 2, 5 };
    
    public static final int PS_TSK_TP_MNG = 1;
    public static final int PS_TSK_TP_DEV = 2;
    public static final int PS_TSK_TP_DEP = 3;
    public static final int PS_TSK_TP_SUP = 4;
    public static final int PS_TSK_TP_OTH = 9;

    public static final int PS_WRK_TP_WRK = 1;
    public static final int PS_WRK_TP_RE_WRK = 2;

    public static final int DS_REQ_TP_FUN = 1;
    public static final int DS_REQ_TP_UI = 2;
    public static final int DS_REQ_TP_SHI = 3;
    public static final int DS_REQ_TP_CON = 4;
    public static final int DS_REQ_TP_EFI = 5;
    public static final int DS_REQ_TP_MTN = 6;
    public static final int DS_REQ_TP_POR = 7;
    public static final int DS_REQ_TP_INT = 8;
    public static final int DS_REQ_TP_RUS = 9;
    public static final int DS_REQ_TP_RES = 10;
    public static final int DS_REQ_TP_LEG = 11;

    public static final int DS_ADE_TP_ARQ = 1;
    public static final int DS_ADE_TP_DET = 2;
    public static final int DS_ADE_TP_UI = 3;
    
    public static final int DS_CMP_TP_DB = 11;
    public static final int DS_CMP_TP_DB_TBL = 12;
    public static final int DS_CMP_TP_DB_TBL_COL = 13;
    public static final int DS_CMP_TP_DB_PRC = 14;
    public static final int DS_CMP_TP_CLS = 21;
    public static final int DS_CMP_TP_CLS_MEM = 22;
    public static final int DS_CMP_TP_CLS_MET = 23;
    public static final int DS_CMP_TP_PCK = 26;
    public static final int DS_CMP_TP_MOD = 31;
    public static final int DS_CMP_TP_MOD_MEN = 32;
    public static final int DS_CMP_TP_MOD_VIE = 33;
    public static final int DS_CMP_TP_MOD_FRM = 34;
    public static final int DS_CMP_TP_MOD_DLG = 35;
    public static final int DS_CMP_TP_MOD_REP = 36;
    public static final int DS_CMP_TP_MOD_PRC = 37;
    public static final int DS_CMP_TP_SUB_SYS = 41;
    public static final int DS_CMP_TP_SYS = 51;

    public static final int DS_DOC_TP_REQ = 1;
    public static final int DS_DOC_TP_TST_SYS = 2;
    public static final int DS_DOC_TP_ANA_DSG = 3;
    public static final int DS_DOC_TP_TST_INT = 4;
    public static final int DS_DOC_TP_OTH = 9;

    public static final int DS_TST_PLN_TP_UNT = 1;
    public static final int DS_TST_PLN_TP_INT = 2;
    public static final int DS_TST_PLN_TP_SYS = 3;

    public static final int DS_TST_RES_TP_OK = 1;
    public static final int DS_TST_RES_TP_SUG_IMP = 2;
    public static final int DS_TST_RES_TP_ERR_APP = 6;
    public static final int DS_TST_RES_TP_ERR_TEC = 7;
    public static final int DS_TST_RES_TP_ERR_FUN = 8;

    public static final int DS_TRC_REF_TP_DOC = 1;
    public static final int DS_TRC_REF_TP_CMP = 2;
    public static final int DS_TRC_REF_TP_ADE = 3;
    public static final int DS_TRC_REF_TP_TST = 4;
}
