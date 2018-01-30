/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.prj.form;

import devman.SDevManConsts;
import devman.gui.SGuiMain;
import devman.gui.SGuiMainSessionCustom;
import devman.mod.SModConsts;
import devman.mod.SModSysConsts;
import devman.mod.prj.db.SDbTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiFieldKeyGroup;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanFieldKey;
import sa.lib.gui.bean.SBeanForm;

/**
 *
 * @author Sergio Flores
 */
public class SFormTask extends SBeanForm implements ActionListener, ItemListener {
    
    private SDbTask moRegistry;
    private SGuiFieldKeyGroup moKeyGroupStagePhaseActivity;
    
    /**
     * Creates new form SFormTask
     */
    public SFormTask(SGuiClient client, String title) {
        setFormSettings(client, SGuiConsts.BEAN_FORM_EDIT, SModConsts.P_TSK, SLibConsts.UNDEFINED, title);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jlProject = new javax.swing.JLabel();
        moKeyProject = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel12 = new javax.swing.JPanel();
        jlProjectStage = new javax.swing.JLabel();
        moKeyProjectStage = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel13 = new javax.swing.JPanel();
        jlProjectStagePhase = new javax.swing.JLabel();
        moKeyProjectStagePhase = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel11 = new javax.swing.JPanel();
        jlProjectStagePhaseActivity = new javax.swing.JLabel();
        moKeyProjectStagePhaseActivity = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel9 = new javax.swing.JPanel();
        jlType = new javax.swing.JLabel();
        moKeyType = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel3 = new javax.swing.JPanel();
        jlCode = new javax.swing.JLabel();
        moTextCode = new sa.lib.gui.bean.SBeanFieldText();
        jPanel4 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        moTextName = new sa.lib.gui.bean.SBeanFieldText();
        jPanel17 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jspDescription = new javax.swing.JScrollPane();
        jtaDescription = new javax.swing.JTextArea();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jlStatus = new javax.swing.JLabel();
        moKeyStatus = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel6 = new javax.swing.JPanel();
        jlDateEndPlanned = new javax.swing.JLabel();
        moDateEndPlanned = new sa.lib.gui.bean.SBeanFieldDate();
        jlDummy2 = new javax.swing.JLabel();
        jlDateEndReal = new javax.swing.JLabel();
        moDateEndReal = new sa.lib.gui.bean.SBeanFieldDate();
        jPanel7 = new javax.swing.JPanel();
        jlTimePlanned = new javax.swing.JLabel();
        moCompTimePlanned = new sa.lib.gui.bean.SBeanCompoundField();
        jbComputeTime = new javax.swing.JButton();
        jlTimeReal = new javax.swing.JLabel();
        moCompTimeReal = new sa.lib.gui.bean.SBeanCompoundField();
        jPanel14 = new javax.swing.JPanel();
        jlUser = new javax.swing.JLabel();
        moKeyUser = new sa.lib.gui.bean.SBeanFieldKey();
        jbSetUser = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jNotes = new javax.swing.JLabel();
        jspNotes = new javax.swing.JScrollPane();
        jtaNotes = new javax.swing.JTextArea();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jPanel1.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel2.setLayout(new java.awt.GridLayout(7, 1, 0, 5));

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlProject.setForeground(java.awt.Color.blue);
        jlProject.setText("Proyecto:*");
        jlProject.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlProject);

        moKeyProject.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel10.add(moKeyProject);

        jPanel2.add(jPanel10);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlProjectStage.setForeground(java.awt.Color.blue);
        jlProjectStage.setText("Etapa:*");
        jlProjectStage.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel12.add(jlProjectStage);

        moKeyProjectStage.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel12.add(moKeyProjectStage);

        jPanel2.add(jPanel12);

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlProjectStagePhase.setForeground(java.awt.Color.blue);
        jlProjectStagePhase.setText("Fase:*");
        jlProjectStagePhase.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel13.add(jlProjectStagePhase);

        moKeyProjectStagePhase.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel13.add(moKeyProjectStagePhase);

        jPanel2.add(jPanel13);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlProjectStagePhaseActivity.setForeground(java.awt.Color.blue);
        jlProjectStagePhaseActivity.setText("Actividad:*");
        jlProjectStagePhaseActivity.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel11.add(jlProjectStagePhaseActivity);

        moKeyProjectStagePhaseActivity.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel11.add(moKeyProjectStagePhaseActivity);

        jPanel2.add(jPanel11);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlType.setText("Tipo:*");
        jlType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlType);

        moKeyType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel9.add(moKeyType);

        jPanel2.add(jPanel9);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlCode.setText("Código:*");
        jlCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlCode);

        moTextCode.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel3.add(moTextCode);

        jPanel2.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlName.setText("Nombre:*");
        jlName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlName);

        moTextName.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel4.add(moTextName);

        jPanel2.add(jPanel4);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel17.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jspDescription.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspDescription.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jspDescription.setPreferredSize(new java.awt.Dimension(505, 150));

        jtaDescription.setColumns(20);
        jtaDescription.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jtaDescription.setRows(5);
        jspDescription.setViewportView(jtaDescription);

        jPanel5.add(jspDescription);

        jPanel17.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel16.setLayout(new java.awt.GridLayout(4, 0, 0, 5));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlStatus.setText("Estatus:*");
        jlStatus.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlStatus);

        moKeyStatus.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel8.add(moKeyStatus);

        jPanel16.add(jPanel8);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDateEndPlanned.setText("Fin planeado:*");
        jlDateEndPlanned.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jlDateEndPlanned);

        moDateEndPlanned.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(moDateEndPlanned);

        jlDummy2.setPreferredSize(new java.awt.Dimension(63, 23));
        jPanel6.add(jlDummy2);

        jlDateEndReal.setText("Fin real:");
        jlDateEndReal.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel6.add(jlDateEndReal);

        moDateEndReal.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(moDateEndReal);

        jPanel16.add(jPanel6);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlTimePlanned.setText("Tiempo planeado:*");
        jlTimePlanned.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel7.add(jlTimePlanned);
        jPanel7.add(moCompTimePlanned);

        jbComputeTime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/devman/gui/img/icon_std_wizard.gif"))); // NOI18N
        jbComputeTime.setToolTipText("Capturar tiempo");
        jbComputeTime.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel7.add(jbComputeTime);

        jlTimeReal.setText("Tiempo real:");
        jlTimeReal.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel7.add(jlTimeReal);
        jPanel7.add(moCompTimeReal);

        jPanel16.add(jPanel7);

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlUser.setText("Responsable:*");
        jlUser.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel14.add(jlUser);

        moKeyUser.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel14.add(moKeyUser);

        jbSetUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_copy.gif"))); // NOI18N
        jbSetUser.setToolTipText("Fijar responsable");
        jbSetUser.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel14.add(jbSetUser);

        jPanel16.add(jPanel14);

        jPanel15.add(jPanel16, java.awt.BorderLayout.NORTH);

        jPanel17.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel17, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles del trabajo:"));
        jPanel18.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jNotes.setText("Comentarios:");
        jNotes.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel19.add(jNotes);

        jPanel18.add(jPanel19, java.awt.BorderLayout.NORTH);

        jspNotes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspNotes.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jtaNotes.setColumns(20);
        jtaNotes.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jtaNotes.setRows(5);
        jspNotes.setViewportView(jtaNotes);

        jPanel18.add(jspNotes, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel18, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jNotes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbComputeTime;
    private javax.swing.JButton jbSetUser;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlDateEndPlanned;
    private javax.swing.JLabel jlDateEndReal;
    private javax.swing.JLabel jlDummy2;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlProject;
    private javax.swing.JLabel jlProjectStage;
    private javax.swing.JLabel jlProjectStagePhase;
    private javax.swing.JLabel jlProjectStagePhaseActivity;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JLabel jlTimePlanned;
    private javax.swing.JLabel jlTimeReal;
    private javax.swing.JLabel jlType;
    private javax.swing.JLabel jlUser;
    private javax.swing.JScrollPane jspDescription;
    private javax.swing.JScrollPane jspNotes;
    private javax.swing.JTextArea jtaDescription;
    private javax.swing.JTextArea jtaNotes;
    private sa.lib.gui.bean.SBeanCompoundField moCompTimePlanned;
    private sa.lib.gui.bean.SBeanCompoundField moCompTimeReal;
    private sa.lib.gui.bean.SBeanFieldDate moDateEndPlanned;
    private sa.lib.gui.bean.SBeanFieldDate moDateEndReal;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProject;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProjectStage;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProjectStagePhase;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProjectStagePhaseActivity;
    private sa.lib.gui.bean.SBeanFieldKey moKeyStatus;
    private sa.lib.gui.bean.SBeanFieldKey moKeyType;
    private sa.lib.gui.bean.SBeanFieldKey moKeyUser;
    private sa.lib.gui.bean.SBeanFieldText moTextCode;
    private sa.lib.gui.bean.SBeanFieldText moTextName;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */
    
    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 880, 550);
        
        moKeyProject.setKeySettings(miClient, SGuiUtils.getLabelName(jlProject), true);
        moKeyProjectStage.setKeySettings(miClient, SGuiUtils.getLabelName(jlProjectStage), true);
        moKeyProjectStagePhase.setKeySettings(miClient, SGuiUtils.getLabelName(jlProjectStagePhase), true);
        moKeyProjectStagePhaseActivity.setKeySettings(miClient, SGuiUtils.getLabelName(jlProjectStagePhaseActivity), true);
        moKeyType.setKeySettings(miClient, SGuiUtils.getLabelName(jlType), true);
        moTextCode.setTextSettings(SGuiUtils.getLabelName(jlCode), 25);
        moTextName.setTextSettings(SGuiUtils.getLabelName(jlName), 100);
        moKeyStatus.setKeySettings(miClient, SGuiUtils.getLabelName(jlStatus), true);
        moDateEndPlanned.setDateSettings(miClient, SGuiUtils.getLabelName(jlDateEndPlanned), true);
        moDateEndReal.setDateSettings(miClient, SGuiUtils.getLabelName(jlDateEndReal), false);
        moCompTimePlanned.setCompoundFieldSettings(miClient);
        moCompTimePlanned.getField().setDecimalSettings(SGuiUtils.getLabelName(jlTimePlanned), SGuiConsts.GUI_TYPE_DEC_QTY, true);
        moCompTimeReal.setCompoundFieldSettings(miClient);
        moCompTimeReal.getField().setDecimalSettings(SGuiUtils.getLabelName(jlTimeReal), SGuiConsts.GUI_TYPE_DEC_QTY, true);
        moKeyUser.setKeySettings(miClient, SGuiUtils.getLabelName(jlUser), true);
        
        moFields.addField(moKeyProject);
        moFields.addField(moKeyProjectStage);
        moFields.addField(moKeyProjectStagePhase);
        moFields.addField(moKeyProjectStagePhaseActivity);
        moFields.addField(moKeyType);
        moFields.addField(moTextCode);
        moFields.addField(moTextName);
        moFields.addField(moKeyStatus);
        moFields.addField(moDateEndPlanned);
        moFields.addField(moDateEndReal);
        moFields.addField(moCompTimePlanned.getField());
        moFields.addField(moCompTimeReal.getField());
        moFields.addField(moKeyUser);
        
        moFields.setFormButton(jbSave);
        
        moCompTimePlanned.setCompoundText(SDevManConsts.MAN_HRS);
        moCompTimeReal.setCompoundText(SDevManConsts.MAN_HRS);
        
        moKeyGroupStagePhaseActivity = new SGuiFieldKeyGroup(miClient);
    }
    
    private void setFieldsStatusByStatus() {
        if (moKeyStatus.getSelectedIndex() <= 0) {
            moDateEndPlanned.setEditable(false);
            moDateEndReal.setEditable(false);
            moCompTimePlanned.getField().setEditable(false);
            moCompTimeReal.getField().setEditable(false);
            jbComputeTime.setEnabled(false);
            
            moDateEndReal.setValue(null);
        }
        else {
            switch (moKeyStatus.getValue()[0]) {
                case SModSysConsts.CS_ST_PND:
                    moDateEndPlanned.setEditable(true);
                    moDateEndReal.setEditable(false);
                    moCompTimePlanned.getField().setEditable(true);
                    moCompTimeReal.getField().setEditable(false);
                    jbComputeTime.setEnabled(true);
                    
                    moDateEndReal.setValue(null);
                    break;
                    
                case SModSysConsts.CS_ST_PRC:
                    moDateEndPlanned.setEditable(false);
                    moDateEndReal.setEditable(true);
                    moCompTimePlanned.getField().setEditable(false);
                    moCompTimeReal.getField().setEditable(false);
                    jbComputeTime.setEnabled(false);
                    break;
                    
                case SModSysConsts.CS_ST_FIN:
                    moDateEndPlanned.setEditable(false);
                    moDateEndReal.setEditable(false);
                    moCompTimePlanned.getField().setEditable(false);
                    moCompTimeReal.getField().setEditable(false);
                    jbComputeTime.setEnabled(false);
                    break;
                    
                case SModSysConsts.CS_ST_CAN:
                    moDateEndPlanned.setEditable(false);
                    moDateEndReal.setEditable(false);
                    moCompTimePlanned.getField().setEditable(false);
                    moCompTimeReal.getField().setEditable(false);
                    jbComputeTime.setEnabled(false);
                    
                    moDateEndReal.setValue(null);
                    break;
                    
                default:
            }
        }
    }
    
    private void actionComputeTime() {
        ((SGuiMain) miClient).computeTime(moCompTimePlanned.getField());
    }
    
    private void actionSetUser() {
        moKeyUser.setValue(new int[] { miClient.getSession().getUser().getPkUserId() });
        moKeyUser.requestFocus();
    }

    private void itemStateChangedStatus() {
        setFieldsStatusByStatus();
    }
    
    /*
     * Public methods
     */
    
    /*
     * Overriden methods
     */
    
    @Override
    public void addAllListeners() {
        jbComputeTime.addActionListener(this);
        jbSetUser.addActionListener(this);
        moKeyStatus.addItemListener(this);
    }

    @Override
    public void removeAllListeners() {
        jbComputeTime.removeActionListener(this);
        jbSetUser.removeActionListener(this);
        moKeyStatus.removeItemListener(this);
    }

    @Override
    public void reloadCatalogues() {
        moKeyGroupStagePhaseActivity.initGroup();
        moKeyGroupStagePhaseActivity.addFieldKey(moKeyProject, SModConsts.PU_PRJ, SLibConsts.UNDEFINED, null);
        moKeyGroupStagePhaseActivity.addFieldKey(moKeyProjectStage, SModConsts.PU_PRJ_STG, SLibConsts.UNDEFINED, null);
        moKeyGroupStagePhaseActivity.addFieldKey(moKeyProjectStagePhase, SModConsts.PU_PRJ_STG_PHS, SLibConsts.UNDEFINED, null);
        moKeyGroupStagePhaseActivity.addFieldKey(moKeyProjectStagePhaseActivity, SModConsts.PU_PRJ_STG_PHS_ACT, SLibConsts.UNDEFINED, null);
        moKeyGroupStagePhaseActivity.populateCatalogues();
        
        miClient.getSession().populateCatalogue(moKeyType, SModConsts.PS_TSK_TP, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyStatus, SModConsts.CS_ST, SModConsts.P_TSK, null);
        miClient.getSession().populateCatalogue(moKeyUser, SModConsts.CU_USR, SModConsts.P_TSK, null);
    }

    @Override
    public void setRegistry(SDbRegistry registry) throws Exception {
        moRegistry = (SDbTask) registry;

        mnFormResult = SLibConsts.UNDEFINED;
        mbFirstActivation = true;

        removeAllListeners();
        reloadCatalogues();

        if (moRegistry.isRegistryNew()) {
            //moRegistry.setCode("");
            
            if (moRegistry.getPkTaskId()== SLibConsts.UNDEFINED) {
                moRegistry.setFkActivityProjectId(((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey()[0]);
                moRegistry.setFkActivityStageId(((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey()[1]);
                moRegistry.setDateEndPlanned(miClient.getSession().getCurrentDate());
                moRegistry.setFkTaskStatusId(SModSysConsts.CS_ST_PND);
            }
            
            jtfRegistryKey.setText("");
        }
        else {
            jtfRegistryKey.setText(SLibUtils.textKey(moRegistry.getPrimaryKey()));
        }

        moKeyProject.setValue(new int[] { moRegistry.getFkActivityProjectId() });
        moKeyProjectStage.setValue(new int[] { moRegistry.getFkActivityProjectId(), moRegistry.getFkActivityStageId() });
        moKeyProjectStagePhase.setValue(new int[] { moRegistry.getFkActivityProjectId(), moRegistry.getFkActivityStageId(), moRegistry.getFkActivityPhaseId() });
        moKeyProjectStagePhaseActivity.setValue(new int[] { moRegistry.getFkActivityProjectId(), moRegistry.getFkActivityStageId(), moRegistry.getFkActivityPhaseId(), moRegistry.getFkActivityActivityId() });
        moKeyType.setValue(new int[] { moRegistry.getFkTaskTypeId() });
        moTextCode.setValue(moRegistry.getCode());
        moTextName.setValue(moRegistry.getName());
        jtaDescription.setText(moRegistry.getDescription());
        jtaDescription.setCaretPosition(0);
        moKeyStatus.setValue(new int[] { moRegistry.getFkTaskStatusId() });
        itemStateChangedStatus();
        moDateEndPlanned.setValue(moRegistry.getDateEndPlanned());
        moDateEndReal.setValue(moRegistry.getDateEndReal_n());
        moCompTimePlanned.getField().setValue(moRegistry.getTimePlanned());
        moCompTimeReal.getField().setValue(moRegistry.getTimeReal_r());
        moKeyUser.setValue(new int[] { moRegistry.getFkTaskUserId() });
        jtaNotes.setText(moRegistry.getNotes());
        jtaNotes.setCaretPosition(0);

        setFormEditable(true);
        
        moTextCode.setEditable(false);
        
        setFieldsStatusByStatus();
        
        if (moRegistry.isRegistryNew()) {
            if (moRegistry.getFkActivityProjectId() == SLibConsts.UNDEFINED) {
                moKeyGroupStagePhaseActivity.resetGroup();
            }
            else {
                moKeyProjectStagePhase.setEnabled(moKeyProjectStagePhase.getItemCount() > 0);
                moKeyProjectStagePhaseActivity.setEnabled(moKeyProjectStagePhaseActivity.getItemCount() > 0);
            }
        }
        else {
            moKeyProject.setEnabled(false);
            moKeyProjectStagePhaseActivity.setEnabled(false);
            moKeyProjectStage.setEnabled(false);
            moKeyProjectStagePhase.setEnabled(false);
        }
        
        addAllListeners();
    }

    @Override
    public SDbTask getRegistry() throws Exception {
        SDbTask registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            registry.setFkActivityProjectId(moKeyProjectStagePhaseActivity.getValue()[0]);
            registry.setFkActivityStageId(moKeyProjectStagePhaseActivity.getValue()[1]);
            registry.setFkActivityPhaseId(moKeyProjectStagePhaseActivity.getValue()[2]);
            registry.setFkActivityActivityId(moKeyProjectStagePhaseActivity.getValue()[3]);
        }

        registry.setCode(moTextCode.getValue());
        registry.setName(moTextName.getValue());
        registry.setDescription(SLibUtils.textTrim(jtaDescription.getText()).replaceAll("'", "''"));
        registry.setDateEndPlanned(moDateEndPlanned.getValue());
        registry.setDateEndReal_n(moDateEndReal.getValue());
        registry.setTimePlanned(moCompTimePlanned.getField().getValue());
        registry.setTimeReal_r(moCompTimeReal.getField().getValue());
        registry.setNotes(SLibUtils.textTrim(jtaNotes.getText()).replaceAll("'", "''"));
        //registry.setDeleted(...);
        //registry.setSystem(...);
        registry.setFkTaskTypeId(moKeyType.getValue()[0]);
        registry.setFkTaskStatusId(moKeyStatus.getValue()[0]);
        registry.setFkTaskUserId(moKeyUser.getValue()[0]);

        return registry;
    }

    @Override
    public SGuiValidation validateForm() {
        SGuiValidation validation = moFields.validateFields();
        
        if (validation.isValid()) {
            if (moKeyStatus.getValue()[0] == SModSysConsts.CS_ST_FIN && moDateEndReal.getValue() == null) {
                validation.setMessage(SGuiConsts.ERR_MSG_FIELD_REQ + "'" + SGuiUtils.getLabelName(jlDateEndReal) + "'.");
                validation.setComponent(moDateEndReal.isEditable()? moDateEndReal : moKeyStatus);
            }
        }
        
        return validation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == jbComputeTime) {
                actionComputeTime();
            }
            else if (button == jbSetUser) {
                actionSetUser();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof SBeanFieldKey) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                SBeanFieldKey field = (SBeanFieldKey) e.getSource();
                
                if (field == moKeyStatus) {
                    itemStateChangedStatus();
                }
            }
        }
    }
}
