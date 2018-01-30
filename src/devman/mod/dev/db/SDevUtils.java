/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.swing.JFileChooser;
import sa.lib.SLibUtils;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;

/**
 *
 * @author Juan Barajas, Sergio Flores
 */
public abstract class SDevUtils {
    
    public static void writeDocument(SGuiClient client, byte[] fileBytes, java.lang.String docName) {
        String fileName = "";
        OutputStream out = null;
        
        client.getFileChooser().setSelectedFile(new File(docName));
        if (client.getFileChooser().showSaveDialog(client.getFrame()) == JFileChooser.APPROVE_OPTION) {
            fileName = client.getFileChooser().getSelectedFile().getAbsolutePath();
            client.getFileChooser().setSelectedFile(new File(docName));
            
            File file = new File(fileName);

            try {
                out = new FileOutputStream(file);
                
                out.write(fileBytes);
                out.close();
                client.showMsgBoxInformation(SGuiConsts.MSG_FILE_SAVED + file.getAbsolutePath());
            }
            catch (java.lang.Exception e) {
                SLibUtils.showException(SDevUtils.class.getName(), e);
            }
        }
    }
}
