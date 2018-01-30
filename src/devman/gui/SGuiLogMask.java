/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.gui;

import java.util.ArrayList;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiLogMask {
    
    public String getCode();
    public String getName();
    public ArrayList<SGuiLogEntryMask> getEntries(SGuiSession session) throws Exception;
}
