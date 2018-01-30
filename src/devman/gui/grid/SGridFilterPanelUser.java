/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.gui.grid;

import devman.mod.SModConsts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import sa.lib.SLibConsts;
import sa.lib.grid.SGridConsts;
import sa.lib.grid.SGridFilter;
import sa.lib.grid.SGridFilterValue;
import sa.lib.grid.SGridPaneView;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiItem;
import sa.lib.gui.SGuiUtils;

/**
 *
 * @author Sergio Flores
 */
public class SGridFilterPanelUser extends JPanel implements SGridFilter, ActionListener, ItemListener {

    private SGuiClient miClient;
    private SGridPaneView moPaneView;
    private int mnFilterType;
    
    /**
     * Creates new form SGridFilterProject
     */
    public SGridFilterPanelUser(SGuiClient client, SGridPaneView paneView) {
        miClient = client;
        moPaneView = paneView;
        mnFilterType = SModConsts.CU_USR;
        initComponents();
        initComponentsCustom();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbFilter = new javax.swing.JComboBox();
        jbClearFilter = new javax.swing.JButton();
        jbFilterOwn = new javax.swing.JButton();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jcbFilter.setToolTipText("Filtrar");
        jcbFilter.setPreferredSize(new java.awt.Dimension(100, 23));
        add(jcbFilter);

        jbClearFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_clear.gif"))); // NOI18N
        jbClearFilter.setToolTipText("Quitar filtro");
        jbClearFilter.setPreferredSize(new java.awt.Dimension(23, 23));
        add(jbClearFilter);

        jbFilterOwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/devman/gui/img/cmd_user.gif"))); // NOI18N
        jbFilterOwn.setToolTipText("Mis registros");
        jbFilterOwn.setPreferredSize(new java.awt.Dimension(23, 23));
        add(jbFilterOwn);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbClearFilter;
    private javax.swing.JButton jbFilterOwn;
    private javax.swing.JComboBox jcbFilter;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */
    
    private void initComponentsCustom() {
        jbClearFilter.addActionListener(this);
        jbFilterOwn.addActionListener(this);
        updateOptions();
    }
    
    private void actionClearFilter() {
        jbClearFilter.setEnabled(false);
        jcbFilter.setSelectedIndex(0);
        jcbFilter.requestFocus();
    }
    
    private void actionFilterOwn() {
        SGuiUtils.locateItem(jcbFilter, new int[] { miClient.getSession().getUser().getPkUserId() });
        jcbFilter.requestFocus();
    }
    
    private void itemStateChangedFilter() {
        moPaneView.putFilter(mnFilterType, new SGridFilterValue(mnFilterType, SGridConsts.FILTER_DATA_TYPE_INT_ARRAY, ((SGuiItem) jcbFilter.getSelectedItem()).getPrimaryKey()));
        jbClearFilter.setEnabled(jcbFilter.getSelectedIndex() > 0);
    }
    
    /*
     * Public methods
     */
    
    public void updateOptions() {
        jcbFilter.removeItemListener(this);
        
        miClient.getSession().populateCatalogue(jcbFilter, mnFilterType, SLibConsts.UNDEFINED, null);
        
        actionClearFilter();
        
        jcbFilter.addItemListener(this);
    }
    
    /*
     * Protected methods
     */
    
    @Override
    public void initFilter(Object value) {
        int[] key = null;
        
        jcbFilter.removeItemListener(this);
        
        key = value == null ? null : new int[] { ((int[]) value)[0] };
        SGuiUtils.locateItem(jcbFilter, key);
        jbClearFilter.setEnabled(jcbFilter.getSelectedIndex() > 0);
        moPaneView.getFiltersMap().put(mnFilterType, new SGridFilterValue(mnFilterType, SGridConsts.FILTER_DATA_TYPE_INT_ARRAY, jcbFilter.getSelectedIndex() <= 0 ? null : key));
        
        jcbFilter.addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == jbClearFilter) {
                actionClearFilter();
            }
            else if (button == jbFilterOwn) {
                actionFilterOwn();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof JComboBox) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox comboBox = (JComboBox) e.getSource();

                if (comboBox == jcbFilter) {
                    itemStateChangedFilter();
                }
            }
        }
    }
}