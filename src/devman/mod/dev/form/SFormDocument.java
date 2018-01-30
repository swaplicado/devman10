/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devman.mod.dev.form;

import devman.gui.SGuiMainSessionCustom;
import devman.mod.SModConsts;
import devman.mod.dev.db.SDbDocument;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiFieldKeyGroup;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanForm;

/**
 *
 * @author Juan Barajas, Sergio Flores
 */
public class SFormDocument extends SBeanForm implements ActionListener {

    private SDbDocument moRegistry;
    
    private SGuiFieldKeyGroup moFieldKeyGroup;
    private FileInputStream moInputStream;
    private boolean mbDocumentChange;

    /**
     * Creates new form SFormDocument
     * @param client
     * @param title
     */
    public SFormDocument(SGuiClient client, String title) {
        setFormSettings(client, SGuiConsts.BEAN_FORM_EDIT, SModConsts.DU_DOC, SLibConsts.UNDEFINED, title);
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
        jPanel8 = new javax.swing.JPanel();
        jlProject = new javax.swing.JLabel();
        moKeyProject = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel10 = new javax.swing.JPanel();
        jlProjectStage = new javax.swing.JLabel();
        moKeyProjectStage = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel9 = new javax.swing.JPanel();
        jlType = new javax.swing.JLabel();
        moKeyType = new sa.lib.gui.bean.SBeanFieldKey();
        jPanel3 = new javax.swing.JPanel();
        jlCode = new javax.swing.JLabel();
        moTextCode = new sa.lib.gui.bean.SBeanFieldText();
        jPanel4 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        moTextName = new sa.lib.gui.bean.SBeanFieldText();
        jPanel12 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jspDescription = new javax.swing.JScrollPane();
        jtaDescription = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jlDocument = new javax.swing.JLabel();
        moTextDocument = new sa.lib.gui.bean.SBeanFieldText();
        jbDocument = new javax.swing.JButton();
        jbDocumentRemove = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jlDocumentName = new javax.swing.JLabel();
        moTextDocumentName = new sa.lib.gui.bean.SBeanFieldText();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(5, 0, 0, 5));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlProject.setForeground(java.awt.Color.blue);
        jlProject.setText("Proyecto:*");
        jlProject.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlProject);

        moKeyProject.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel8.add(moKeyProject);

        jPanel2.add(jPanel8);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlProjectStage.setForeground(java.awt.Color.blue);
        jlProjectStage.setText("Etapa:*");
        jlProjectStage.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlProjectStage);

        moKeyProjectStage.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel10.add(moKeyProjectStage);

        jPanel2.add(jPanel10);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlType.setText("Tipo:*");
        jlType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlType);

        moKeyType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel9.add(moKeyType);

        jPanel2.add(jPanel9);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlCode.setText("Código:*");
        jlCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlCode);

        moTextCode.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel3.add(moTextCode);

        jPanel2.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlName.setText("Nombre:*");
        jlName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlName);

        moTextName.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel4.add(moTextName);

        jPanel2.add(jPanel4);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jspDescription.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspDescription.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jspDescription.setPreferredSize(new java.awt.Dimension(505, 150));

        jtaDescription.setColumns(20);
        jtaDescription.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jtaDescription.setRows(5);
        jspDescription.setViewportView(jtaDescription);

        jPanel11.add(jspDescription);
        jPanel11.add(jPanel5);

        jPanel12.add(jPanel11, java.awt.BorderLayout.NORTH);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel14.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlDocument.setText("Documento:");
        jlDocument.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jlDocument);

        moTextDocument.setEditable(false);
        moTextDocument.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel6.add(moTextDocument);

        jbDocument.setText("jButton1");
        jbDocument.setToolTipText("Seleccionar documento");
        jbDocument.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel6.add(jbDocument);

        jbDocumentRemove.setToolTipText("Eliminar documento");
        jbDocumentRemove.setFocusable(false);
        jbDocumentRemove.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel6.add(jbDocumentRemove);

        jPanel14.add(jPanel6);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlDocumentName.setForeground(new java.awt.Color(0, 153, 153));
        jlDocumentName.setText("Nombre archivo:");
        jlDocumentName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel7.add(jlDocumentName);

        moTextDocumentName.setPreferredSize(new java.awt.Dimension(400, 23));
        jPanel7.add(moTextDocumentName);

        jPanel14.add(jPanel7);

        jPanel13.add(jPanel14, java.awt.BorderLayout.NORTH);

        jPanel12.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel12, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbDocument;
    private javax.swing.JButton jbDocumentRemove;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlDocument;
    private javax.swing.JLabel jlDocumentName;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlProject;
    private javax.swing.JLabel jlProjectStage;
    private javax.swing.JLabel jlType;
    private javax.swing.JScrollPane jspDescription;
    private javax.swing.JTextArea jtaDescription;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProject;
    private sa.lib.gui.bean.SBeanFieldKey moKeyProjectStage;
    private sa.lib.gui.bean.SBeanFieldKey moKeyType;
    private sa.lib.gui.bean.SBeanFieldText moTextCode;
    private sa.lib.gui.bean.SBeanFieldText moTextDocument;
    private sa.lib.gui.bean.SBeanFieldText moTextDocumentName;
    private sa.lib.gui.bean.SBeanFieldText moTextName;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 720, 450);
        moFieldKeyGroup = new SGuiFieldKeyGroup(miClient);

        moKeyProject.setKeySettings(miClient, SGuiUtils.getLabelName(jlProject), true);
        moKeyProjectStage.setKeySettings(miClient, SGuiUtils.getLabelName(jlProjectStage), true);
        moKeyType.setKeySettings(miClient, SGuiUtils.getLabelName(jlType), true);
        moTextCode.setTextSettings(SGuiUtils.getLabelName(jlCode), 25);
        moTextName.setTextSettings(SGuiUtils.getLabelName(jlName), 100);
        moTextDocument.setTextSettings(SGuiUtils.getLabelName(jlDocument), 255);
        moTextDocumentName.setTextSettings(SGuiUtils.getLabelName(jlDocumentName), 100, 0);
        moTextDocumentName.setTextCaseType(SLibConsts.UNDEFINED);
        
        jbDocumentRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_delete_tmp.gif")));;

        moFields.addField(moKeyProject);
        moFields.addField(moKeyProjectStage);
        moFields.addField(moKeyType);
        moFields.addField(moTextCode);
        moFields.addField(moTextName);
        moFields.addField(moTextDocument);
        moFields.addField(moTextDocumentName);

        moFields.setFormButton(jbSave);
    }

    private void actionLoadDocument() {
        miClient.getFileChooser().repaint();

        try {
            if (miClient.getFileChooser().showOpenDialog(miClient.getFrame()) == JFileChooser.APPROVE_OPTION) {
                moTextDocument.setValue(miClient.getFileChooser().getSelectedFile().getName());
                moTextDocumentName.setValue(miClient.getFileChooser().getSelectedFile().getName());
                moInputStream = new FileInputStream(miClient.getFileChooser().getSelectedFile());
                mbDocumentChange = true;
            }
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
    }
    
    private void actionRemoveDocument() {
        moTextDocument.setValue("");
        moTextDocumentName.setValue("");
        moInputStream = null;
        mbDocumentChange = true;
    }

    /*
     * Public methods
     */

    /*
     * Overriden methods
     */

    @Override
    public void addAllListeners() {
        jbDocument.addActionListener(this);
        jbDocumentRemove.addActionListener(this);
    }

    @Override
    public void removeAllListeners() {
        jbDocument.removeActionListener(this);
        jbDocumentRemove.removeActionListener(this);
    }

    @Override
    public void reloadCatalogues() {
        moFieldKeyGroup.initGroup();
        moFieldKeyGroup.addFieldKey(moKeyProject, SModConsts.PU_PRJ, SLibConsts.UNDEFINED, null);
        moFieldKeyGroup.addFieldKey(moKeyProjectStage, SModConsts.PU_PRJ_STG, SLibConsts.UNDEFINED, null);
        moFieldKeyGroup.populateCatalogues();
        
        miClient.getSession().populateCatalogue(moKeyType, SModConsts.DS_DOC_TP, SLibConsts.UNDEFINED, null);
    }

    @Override
    public void setRegistry(SDbRegistry registry) throws Exception {
        moRegistry = (SDbDocument) registry;

        mnFormResult = SLibConsts.UNDEFINED;
        mbFirstActivation = true;
        moInputStream = null;
        mbDocumentChange = false;

        removeAllListeners();
        reloadCatalogues();

        if (moRegistry.isRegistryNew()) {
            //moRegistry.setCode("");
            
            if (moRegistry.getPkProjectId() == SLibConsts.UNDEFINED) {
                moRegistry.setPkProjectId(((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey()[0]);
                moRegistry.setPkStageId(((SGuiMainSessionCustom) miClient.getSession().getSessionCustom()).getProjectStageKey()[1]);
            }
            
            jtfRegistryKey.setText("");
        }
        else {
            jtfRegistryKey.setText(SLibUtils.textKey(moRegistry.getPrimaryKey()));
        }

        moKeyProject.setValue(new int[] { moRegistry.getPkProjectId() });
        moKeyProjectStage.setValue(new int[] { moRegistry.getPkProjectId(), moRegistry.getPkStageId() });
        moKeyType.setValue(new int[] { moRegistry.getFkDocumentTypeId() });
        moTextCode.setValue(moRegistry.getCode());
        moTextName.setValue(moRegistry.getName());
        jtaDescription.setText(moRegistry.getDescription());
        jtaDescription.setCaretPosition(0);
        moTextDocument.setValue(moRegistry.getDocumentName()); // use save only
        moTextDocumentName.setValue(moRegistry.getDocumentName());

        setFormEditable(true);
        
        moTextDocument.setEditable(false);
        moTextCode.setEditable(false);
        
        if (moRegistry.isRegistryNew()) {
            if (moRegistry.getPkProjectId() == SLibConsts.UNDEFINED) {
                moFieldKeyGroup.resetGroup();
            }
        }
        else {
            moKeyProject.setEnabled(false);
            moKeyProjectStage.setEnabled(false);
        }
        
        addAllListeners();
    }

    @Override
    public SDbDocument getRegistry() throws Exception {
        SDbDocument registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            registry.setPkProjectId(moKeyProjectStage.getValue()[0]);
            registry.setPkStageId(moKeyProjectStage.getValue()[1]);
        }

        registry.setCode(moTextCode.getValue());
        registry.setName(moTextName.getValue());
        registry.setDescription(SLibUtils.textTrim(jtaDescription.getText()).replaceAll("'", "''"));
        //registry.setDocument_n(...);
        registry.setDocumentName(moTextDocumentName.getValue());
        registry.setFkDocumentTypeId(moKeyType.getValue()[0]);
        
        registry.setAuxFileInputStream(moInputStream);
        registry.setAuxDocumentChange(mbDocumentChange);

        return registry;
    }

    @Override
    public SGuiValidation validateForm() {
        SGuiValidation validation = moFields.validateFields();

        if (validation.isValid()) {
            if (moTextDocument.getValue().length() > 0 && moTextDocumentName.getValue().length() <= 0) {
                validation.setMessage(SGuiConsts.ERR_MSG_FIELD_REQ + "'" + SGuiUtils.getLabelName(jlDocumentName) + "'.");
                validation.setComponent(moTextDocumentName);
            }
        }

        return validation;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() instanceof javax.swing.JButton) {
            JButton button = (JButton) e.getSource();

            if (button == jbDocument) {
                actionLoadDocument();
            }
            else if (button == jbDocumentRemove) {
                actionRemoveDocument();
            }
        }
    }
}