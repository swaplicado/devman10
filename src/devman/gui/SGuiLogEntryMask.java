/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.gui;

import java.util.Date;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiLogEntryMask {
    
    public Date getDate();
    public double getTime();
    public String getType();
    public String getUser();
    public String getNotes();
    public String getInsertUser();
    public Date getInsertTimestamp();
    public String getUpdateUser();
    public Date getUpdateTimestamp();
}
