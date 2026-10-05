package com.rm2pt.rapidood.classdiagram.design;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
    public static final String PLUGIN_ID = "com.rm2pt.rapidood.classdiagram.design";
    private static Activator plugin;
    private static Set<Viewpoint> viewpoints; 
    
    private JTabbedPane tabbedPane;
    private DefaultTableModel model;
    private static JTable table;
    private static Vector<Vector<Object>> vData = null;
    private static Vector<String> vName;
    private DefaultTableModel qaModel;
    private static JTable qaTable;
    private static JTextArea qaText;
    private static JTextField DCCText;
    private static Vector<Vector<Object>> qaData = null;
    private static Vector<String> qaName;
    
    private static int num = 0;
    private static double DSC = 0;
    private static double NOH = 0;
    private static double ANA = 0;
    private static double DAM = 0;
    private static double DCC = 0;
    private static double CAM = 0;
    private static double MOA = 0;
    private static double MFA = 0;
    private static double NOP = 0;
    private static double CIS = 0;
    private static double NOM = 0;
    
    private static double Reusability = 1.00;
    private static double Flexibility = 1.00;
    private static double Understandability= -0.99;
    private static double Functionality= 1.00;
    private static double Extendibility= 1.00;
    private static double Effectiveness= 1.00;
    
    
    private static double defaultDSC = 0;
    private static double defaultNOH = 0;
    private static double defaultANA = 0;
    private static double defaultDAM = 0;
    private static double defaultDCC = 0;
    private static double defaultCAM = 0;
    private static double defaultMOA = 0;
    private static double defaultMFA = 0;
    private static double defaultNOP = 0;
    private static double defaultCIS = 0;
    private static double defaultNOM = 0;
    
    private static double perDSC = 0;
    private static double perNOH = 0;
    private static double perANA = 0;
    private static double perDAM = 0;
    private static double perDCC = 0;
    private static double perCAM = 0;
    private static double perMOA = 0;
    private static double perMFA = 0;
    private static double perNOP = 0;
    private static double perCIS = 0;
    private static double perNOM = 0;
    
    public static void addData() throws Exception {
    	Vector<Object> vRow = new Vector<>();
    	num++;
    	vRow.add(num);
    	vRow.add(DSC);
    	vRow.add(NOH);
    	vRow.add(ANA);
    	vRow.add(DAM);
    	vRow.add(DCC);
    	vRow.add(CAM);
    	vRow.add(MOA);
    	vRow.add(MFA);
    	vRow.add(NOP);
    	vRow.add(CIS);
    	vRow.add(NOM);
    	vData.add(vRow);
    	   	
    	if (num == 1) {
    		defaultDSC = DSC;
    		defaultNOH = NOH;
    		defaultANA = ANA;
    		defaultDAM = DAM;
    		defaultDCC = DCC;
    		defaultCAM = CAM;
    		defaultMOA = MOA;
    		defaultMFA = MFA;
    		defaultNOP = NOP;
    		defaultCIS = CIS;
    		defaultNOM = NOM;
    	} else {
    		calculatePer();
    		Reusability = -0.25 * perDCC + 0.25 * perCAM + 0.5 * perCIS + 0.5 * perDSC;
    		Flexibility = 0.25 * perDAM - 0.25 * perDCC + 0.5 * perMOA + 0.5 * perNOP;
    		Understandability = -0.33 * perANA + 0.33 * perDAM - 0.33 * perDCC + 0.33 * perCAM - 0.33 * perNOP - 0.33 * perNOM - 0.33 * perDSC;
    		Functionality = 0.12 * perCAM + 0.22 * perNOP + 0.22 * perCIS + 0.22 * perDSC + 0.22 * perNOH;
    		Extendibility = 0.5 * perANA - 0.5 * perDCC + 0.5 * perMFA + 0.5 * perNOP;
    		Effectiveness = 0.2 * perANA + 0.2 * perDAM + 0.2 * perMOA + 0.2 * perMFA + 0.2 * perNOP;
    	}
    	
    	Vector<Object> qaRow = new Vector<>();
    	qaRow.add(num);
    	qaRow.add(Reusability);
    	qaRow.add(Flexibility);
    	qaRow.add(Understandability);
    	qaRow.add(Functionality);
    	qaRow.add(Extendibility);
    	qaRow.add(Effectiveness);
    	qaData.add(qaRow);	
    	
    	table.updateUI();
    	qaTable.updateUI();
    	qaText.updateUI();
    	
    }
    
    public static void calculatePer() {
    	perDSC = DSC / defaultDSC;
    	perNOH = NOH / defaultNOH;
    	perANA = ANA / defaultANA;
    	perDAM = DAM / defaultDAM;
    	perDCC = DCC / defaultDCC;
    	perCAM = CAM / defaultCAM;
    	perMOA = MOA / defaultMOA;
    	perMFA = MFA / defaultMFA;
    	perNOP = NOP / defaultNOP;
    	perCIS = CIS / defaultCIS;
    	perNOM = NOM / defaultNOM;    	   	
    }
    
    private void initComponents() {
        // 初始化数据模型
    	vData = new Vector<>();
    	vName = new Vector<String>();
        vName.add("次数");
        vName.add("DSC");
        vName.add("NOH");
        vName.add("ANA");
        vName.add("DAM");
        vName.add("DCC");
        vName.add("CAM");
        vName.add("MOA");
        vName.add("MFA");
        vName.add("NOP");
        vName.add("CIS");
        vName.add("NOM");
        
        qaData = new Vector<>();
        qaName = new Vector<String>();
        qaName.add("次数");
        qaName.add("可重用性（Reusability）");
        qaName.add("灵活性（Flexibility）");
        qaName.add("可理解性（Understandability）");
        qaName.add("功能性（Functionality）");
        qaName.add("可扩展性（Extendibility）");
        qaName.add("有效性（Effectiveness）");

        // 创建表格模型
        model = new DefaultTableModel(vData, vName);
        qaModel = new DefaultTableModel(qaData, qaName);    	
    }
    
    private void setupUI() {
    	
        JFrame evaluatorUI = new JFrame("设计质量评估");
        evaluatorUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        evaluatorUI.setSize(800, 600);
        
        tabbedPane = new JTabbedPane();
        
        // 第一个标签页：设计指标
        JPanel metricsPanel = new JPanel(new BorderLayout());
        table = new JTable(model);
        metricsPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tabbedPane.addTab("设计指标", metricsPanel);

        // 第二个标签页：质量属性
        JPanel qaPanel = new JPanel(new BorderLayout());
        qaTable = new JTable(qaModel);
        qaPanel.add(new JScrollPane(qaTable), BorderLayout.CENTER);
        tabbedPane.addTab("质量属性", qaPanel);

        // 第三个标签页：提示信息
        JPanel promptPanel = new JPanel(new BorderLayout());
        qaText = new JTextArea();
        qaText.setLineWrap(true);
        qaText.setWrapStyleWord(true);
        DCCText = new JTextField();
        
        JScrollPane scrollPane = new JScrollPane(qaText);
        promptPanel.add(scrollPane, BorderLayout.CENTER);
        promptPanel.add(DCCText, BorderLayout.NORTH);
        tabbedPane.addTab("提示信息", promptPanel);

        for (int i = 1; i < vName.size(); i++) {
    		table.getColumn(vName.get(i)).setCellRenderer(fontColor);
    	}
    	
    	for (int i = 1; i < qaName.size(); i++) {
    		qaTable.getColumn(qaName.get(i)).setCellRenderer(qaFontColor);
    	}
    	
        evaluatorUI.add(tabbedPane);    
        SwingUtilities.invokeLater(() -> {
        	evaluatorUI.setVisible(true);
        });
    }
    /**
     * The constructor
     */
    public Activator() {
    	if (vData != null || qaData != null) {
    		return;
    	}
    	initComponents();
    	setupUI();	
    }
    
	private static DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer() {   
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			Component cell = super.getTableCellRendererComponent
					(table, value, isSelected, hasFocus, row, column);
			if (row == 0) {
				cell.setForeground(null);
				return cell;
			}
			double eps = 0.000000001;
			double preValue = (double) ((Vector) vData.get(row - 1)).get(column);
			double nowValue = (double) value;
			if (preValue > nowValue + eps)
				cell.setForeground(Color.green);
			else if (preValue < nowValue - eps)
				cell.setForeground(Color.red);
			else 
				cell.setForeground(null);
			return cell;
		}
    };;
    private static DefaultTableCellRenderer qaFontColor = new DefaultTableCellRenderer() {   
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			Component cell = super.getTableCellRendererComponent
					(table, value, isSelected, hasFocus, row, column);
			if (row == 0) {
				cell.setForeground(null);
				return cell;
			}
			double eps = 0.000000001;
			double preValue = (double) ((Vector) qaData.get(row - 1)).get(column);
			double nowValue = (double) value;
			if (preValue > nowValue + eps)
				cell.setForeground(Color.green);
			else if (preValue < nowValue - eps)
				cell.setForeground(Color.red);
			else 
				cell.setForeground(null);
			return cell;
		}
    };;
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
      super.start(context);
	  plugin = this;
	  viewpoints = new HashSet<Viewpoint>();
	  viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/description/classdiagram.odesign")); 
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
	plugin = null;
	if (viewpoints != null) {
	    for (final Viewpoint viewpoint: viewpoints) {
		ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
	    }
	    viewpoints.clear();
	    viewpoints = null; 
	}
	super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static Activator getDefault() {
    	return plugin;
    }
    
    public static double getDSC() {
		return DSC;
	}

	public static void setDSC(double dSC) {
		DSC = dSC;
	}

	public static double getNOH() {
		return NOH;
	}

	public static void setNOH(double nOH) {
		NOH = nOH;
	}

	public static double getANA() {
		return ANA;
	}

	public static void setANA(double aNA) {
		ANA = aNA;
	}

	public static double getDAM() {
		return DAM;
	}

	public static void setDAM(double dAM) {
		DAM = dAM;
	}

	public static double getDCC() {
		return DCC;
	}

	public static void setDCC(double dCC) {
		DCC = dCC;
	}

	public static double getCAM() {
		return CAM;
	}

	public static void setCAM(double cAM) {
		CAM = cAM;
	}

	public static double getMOA() {
		return MOA;
	}

	public static void setMOA(double mOA) {
		MOA = mOA;
	}

	public static double getMFA() {
		return MFA;
	}

	public static void setMFA(double mFA) {
		MFA = mFA;
	}

	public static double getNOP() {
		return NOP;
	}

	public static void setNOP(double nOP) {
		NOP = nOP;
	}

	public static double getCIS() {
		return CIS;
	}

	public static void setCIS(double cIS) {
		CIS = cIS;
	}

	public static double getNOM() {
		return NOM;
	}

	public static void setNOM(double nOM) {
		NOM = nOM;
	}

	public static double getReusability() {
		return Reusability;
	}

	public static void setReusability(double reusability) {
		Reusability = reusability;
	}

	public static double getFlexibility() {
		return Flexibility;
	}

	public static void setFlexibility(double flexibility) {
		Flexibility = flexibility;
	}

	public static double getUnderstandability() {
		return Understandability;
	}

	public static void setUnderstandability(double understandability) {
		Understandability = understandability;
	}

	public static double getFunctionality() {
		return Functionality;
	}

	public static void setFunctionality(double functionality) {
		Functionality = functionality;
	}

	public static double getExtendibility() {
		return Extendibility;
	}

	public static void setExtendibility(double extendibility) {
		Extendibility = extendibility;
	}

	public static double getEffectiveness() {
		return Effectiveness;
	}

	public static void setEffectiveness(double effectiveness) {
		Effectiveness = effectiveness;
	}
	
	public static JTextField getDCCText() {
		return DCCText;
	}
	public static JTextArea getQAText() {
		return qaText;
	}
}
