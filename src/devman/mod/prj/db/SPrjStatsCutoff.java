/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.db;

import java.util.Date;

/**
 *
 * @author Sergio Flores
 */
public class SPrjStatsCutoff {
    
    public Date DateStart;
    public Date DateEnd;
    public Date Cutoff;
    public int DaysPlanned;
    public int DaysElapsed;
    public double WorkPlanned;
    public double WorkElapsed;
    public double WorkFinished;
    public double EffortRequired;

    public SPrjStatsCutoff() {
        DateStart = null;
        DateEnd = null;
        Cutoff = null;
        DaysPlanned = 0;
        DaysElapsed = 0;
        WorkPlanned = 0;
        WorkElapsed = 0;
        WorkFinished = 0;
        EffortRequired = 0;
    }
    
    public double getDaysToElapse() {
        return DaysPlanned - DaysElapsed;
    }
    
    public double getDaysElapsedPercentage() {
        return DaysPlanned == 0 ? 0 : (double) DaysElapsed / (double) DaysPlanned;
    }
    
    public double getWorkElapsedPercentage() {
        return WorkPlanned == 0 ? 0 : WorkElapsed / WorkPlanned;
    }
    
    public double getWorkFinishedPercentage() {
        return WorkPlanned == 0 ? 0 : WorkFinished / WorkPlanned;
    }
    
    public double getWorkGap() {
        return WorkElapsed - WorkFinished;
    }
    
    public double getWorkGapPercentage() {
        return getWorkElapsedPercentage() - getWorkFinishedPercentage();
    }
    
    public double getEffortEfficiency() {
        return WorkFinished - EffortRequired;
    }
    
    public double getEffortEfficiencyPercentage() {
        return WorkFinished == 0 ? 0 : 1 - EffortRequired / WorkFinished;
    }
}
