/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.db;

import devman.mod.SModSysConsts;

/**
 *
 * @author Sergio Flores
 */
public class SPrjStatsBasic {
    
    public double TimePlan;
    public double TimePlanPnd;
    public double TimePlanPrc;
    public double TimePlanFin;
    public double TimePlanCan;
    public double TimeReal;
    public double TimeRealPnd;
    public double TimeRealPrc;
    public double TimeRealFin;
    public double TimeRealCan;
    
    public SPrjStatsBasic() {
        TimePlan = 0;
        TimePlanPnd = 0;
        TimePlanPrc = 0;
        TimePlanFin = 0;
        TimePlanCan = 0;
        TimeReal = 0;
        TimeRealPnd = 0;
        TimeRealPrc = 0;
        TimeRealFin = 0;
        TimeRealCan = 0;
    }
    
    public double getTimePlan(final int status) {
        double time = 0;
        
        switch (status) {
            case SModSysConsts.CS_ST_PND:
                time = TimePlanPnd;
                break;
            case SModSysConsts.CS_ST_PRC:
                time = TimePlanPrc;
                break;
            case SModSysConsts.CS_ST_FIN:
                time = TimePlanFin;
                break;
            case SModSysConsts.CS_ST_CAN:
                time = TimePlanCan;
                break;
            default:
        }
        
        return time;
    }
    
    public double getTimePlanPercentage(final int status) {
        return TimePlan == 0 ? 0 : getTimePlan(status) / TimePlan;
    }
    
    public double getTimeReal(final int status) {
        double time = 0;
        
        switch (status) {
            case SModSysConsts.CS_ST_PND:
                time = TimeRealPnd;
                break;
            case SModSysConsts.CS_ST_PRC:
                time = TimeRealPrc;
                break;
            case SModSysConsts.CS_ST_FIN:
                time = TimeRealFin;
                break;
            case SModSysConsts.CS_ST_CAN:
                time = TimeRealCan;
                break;
            default:
        }
        
        return time;
    }
    
    public double getTimeRealPercentage(final int status) {
        return TimeReal == 0 ? 0 : getTimeReal(status) / TimeReal;
    }
}
