package com.rm2pt.rapidood.optimization.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.gson.Gson;
import com.rm2pt.rapidood.cd.classDiagram.AbstractElement;
import com.rm2pt.rapidood.cd.classDiagram.ClassDiagram;
import com.rm2pt.rapidood.cd.classDiagram.ClassDiagramFactory;

import net.mydreamy.requirementmodel.rEMODEL.DomainModel;
import net.mydreamy.requirementmodel.rEMODEL.RequirementModel;


public class DesignModelOptimizationDialog extends JFrame{
	private JTabbedPane tabbedPane;
	public DesignModelOptimizationDialog(Resource cdResource) {
    	initUI(cdResource);
    }
	
	private void initUI(Resource cdResource) {
        setTitle("设计优化工具");
        setSize(1024, 768);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // 添加领域划分标签页
        addDomainKnowledgeTab(cdResource);
        
      //添加职责分配标签页
        addDesignPatternTab(cdResource);

    }
	
	private void addDomainKnowledgeTab(Resource cdResource) {
    	DomainKnowledgePanel domainPartitionPanel = new DomainKnowledgePanel(cdResource);
    	tabbedPane.addTab("领域知识补全", domainPartitionPanel);
    }
	
	private void addDesignPatternTab(Resource cdResource) {
        DesignPatternPanel responsibilityPanel = new DesignPatternPanel(cdResource);
        tabbedPane.addTab("设计模式应用", responsibilityPanel);
    }
	
}
class DesignPatternPanel extends JPanel {
	public static  Resource cdResource;
	// 设计模式名称--工厂模式以及相关类
    private JTextArea domainKeywordText;
    // 设计模式PlantUML代码实例
    private JTextArea domainKnowledgeText;
    // 代码生成提示词
    private JTextArea promptText;
    // 返回结果
    private JTextArea resultText;
    private static final String API_URL = "https://api.chatfire.cn/v1/chat/completions";
    private static final String API_KEY = System.getenv("RAPIDOOD_API_KEY");
    
    public DesignPatternPanel(Resource cdResource) {
        this.cdResource = cdResource;
        initComponents();
    }

    private void initComponents() {
    	
        setLayout(new BorderLayout(5, 5));
        // 右侧主区域
        createMainPanel();
    }
    
    
    private void createMainPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        // 输入区域
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel);
        
        // 按钮区域
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        // 设计模型应用识别
        JPanel DomainKeywordPanel = createTextPanel("设计模式应用识别：", true);
        domainKeywordText = (JTextArea)((JScrollPane)DomainKeywordPanel.getComponent(1)).getViewport().getView();
        // 设计模式PlantUML代码示例
        JPanel DomainKnowledgePanel = createTextPanel("设计模式PlantUML代码示例：", true);
        domainKnowledgeText = (JTextArea)((JScrollPane)DomainKnowledgePanel.getComponent(1)).getViewport().getView();
        // 提示词输入
        JPanel promptPanel = createTextPanel("提示词：", true);
        promptText = (JTextArea) ((JScrollPane)promptPanel.getComponent(1)).getViewport().getView();
        
        // 结果展示
        JPanel resultPanel = createTextPanel("返回结果：", false);
        resultText = (JTextArea) ((JScrollPane)resultPanel.getComponent(1)).getViewport().getView();
        
        panel.add(DomainKeywordPanel);
        panel.add(DomainKnowledgePanel);
        panel.add(promptPanel);
        panel.add(resultPanel);
        return panel;
    }

    private JPanel createTextPanel(String label, boolean editable) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(label), BorderLayout.NORTH);
        
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(editable);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(0, 400));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton extractBtn = new JButton("设计模式应用识别");
        extractBtn.addActionListener(this::handleExtract);
        
        JButton searchBtn = new JButton("设计模式示例检索");
        searchBtn.addActionListener(this::handleSearch);
        
        JButton generateBtn = new JButton("代码片段生成");
        generateBtn.addActionListener(this::handleGenerate);
        
        JButton adoptBtn = new JButton("采用代码片段");
        adoptBtn.addActionListener(this::handleAdopt);
        
        panel.add(extractBtn);
        panel.add(searchBtn);
        panel.add(generateBtn);
        panel.add(adoptBtn);
        return panel;
    }
    
    private void handleExtract(ActionEvent e) {
    	ClassDiagram cd = (ClassDiagram)cdResource.getContents().get(0);
    	INode cdNode = NodeModelUtils.getNode(cd);
        String cdText = cdNode.getText();
    	String prompt = "";
    	prompt += "参考设计模式，对下面以PlantUML表示的软件设计进行优化，指出可以采用的设计模式及改写的相应部分。\r\n"
    			+ "[设计模型]\r\n";
    	prompt += cdText;
    	prompt +=  "\r\n"
    			+ "[输出格式]\r\n"
    			+ "{\r\n"
    			+ "	\"pattern\": \"可以采用的设计模式名称\"\r\n"
    			+ "	\"reasoning\": \"适合用该设计模式改写的部分以及改写原因或改写后的优势\"\r\n"
    			+ "}\r\n"
    			+ "\r\n"
    			+ "[输出示例]\r\n"
    			+ "{\r\n"
    			+ "	\"pattern\": \"工厂方法模式（Factory Method Pattern）\"\r\n"
    			+ "	\"reasoning\": \"适合用工厂方法模式改写的部分是 Payment 及其子类（CashPayment 和 CardPayment）。通过引入一个支付工厂来封装支付方式的创建逻辑，可以避免在业务类（如 Sale）中直接依赖具体支付类型。\"\r\n"
    			+ "}\n";
    	
    	String systemSetting = "注意输出结果要严格符合输出格式，不要出现多余内容。";
    	String res = "";
    	try {
			res = callGPT4(systemSetting, prompt);
			domainKeywordText.setText(res);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
    
    private void handleSearch(ActionEvent e) {
    	String prompt = "参考设计模式识别的结果，给出一个该设计模式的通用的PlantUML表示。\r\n"
    			+ "[设计模式识别的结果]\r\n";
    	prompt += domainKeywordText.getText()
    			+ "\r\n"
    			+ "[输出结果]\r\n"
    			+ "设计模式的通用PlantUML表示\r\n"
    			+ "\r\n"
    			+ "[输出示例]\n";
    	prompt += "@startuml\r\n"
    			+ "' 定义抽象产品类\r\n"
    			+ "class Product {\r\n"
    			+ "    + operation()\r\n"
    			+ "}\r\n"
    			+ "\r\n"
    			+ "' 定义具体产品类\r\n"
    			+ "class ConcreteProductA {\r\n"
    			+ "    + operation()\r\n"
    			+ "}\r\n"
    			+ "\r\n"
    			+ "ConcreteProductA --|> Product"
    			+ "class ConcreteProductB {\r\n"
    			+ "    + operation()\r\n"
    			+ "}\r\n"
    			+ "ConcreteProductB --|> Product"
    			+ "\r\n"
    			+ "' 定义抽象工厂类\r\n"
    			+ "class Factory {\r\n"
    			+ "    + {abstract} createProduct(): Product\r\n"
    			+ "}\r\n"
    			+ "\r\n"
    			+ "' 定义具体工厂类\r\n"
    			+ "class ConcreteFactoryA {\r\n"
    			+ "    + createProduct(): Product\r\n"
    			+ "}\r\n"
    			+ "ConcreteFactoryA --|> Factory"
    			+ "\r\n"
    			+ "class ConcreteFactoryB {\r\n"
    			+ "    + createProduct(): Product\r\n"
    			+ "}\r\n"
    			+ "ConcreteFactoryB --|> Factory"
    			+ "\r\n"
    			+ "' 关联关系\r\n"
    			+ "Factory --> Product : creates\r\n"
    			+ "ConcreteCreatorA --> ConcreteProductA : creates\r\n"
    			+ "ConcreteCreatorB --> ConcreteProductB : creates\r\n"
    			+ "@enduml";
    	String systemSetting = "注意输出结果要严格符合输出格式，不要出现多余内容。具体的PlantUML语句参考输出示例，不要添加注释，并且不要出现示例中没有出现过的PlantUML语法。";
    	String res = "";
    	try {
        	res = callGPT4(systemSetting, prompt);
        	domainKnowledgeText.setText(res);
        } catch (Exception exception) {
        	exception.printStackTrace();
        }
    }
    
    private void handleGenerate(ActionEvent e) {
    	
    	ClassDiagram cd = (ClassDiagram)cdResource.getContents().get(0);
    	INode cdNode = NodeModelUtils.getNode(cd);
        String cdText = cdNode.getText();
        String prompt = "基于现有类图内容，结合设计模式应用建议和设计模式示例，给出修改后的PlantUML代码片段。\n";
        prompt += "[类图]\n";
        prompt += cdText;
        prompt += "\n";
        prompt += "[设计模式应用建议]\n";
        prompt += domainKeywordText.getText();
        prompt += "\n";
        prompt += "[设计模式示例]\n";
        prompt += domainKnowledgeText.getText();
        
        promptText.setText(prompt);
        String systemSetting = "仅给出完整的修改后的PlantUML代码，不要仅输出被修改的部分。参考示例如下：\n";
        systemSetting += "class Payment {\r\n"
        		+ "	- AmountTendered: Real\r\n"
        		+ "	+ {abstract} processPayment(): Boolean\r\n"
        		+ "}\r\n"
        		+ "class CashPayment {\r\n"
        		+ "	- Balance: Real\r\n"
        		+ "	+ processPayment(): Boolean\r\n"
        		+ "}\r\n"
        		+ "class CardPayment {\r\n"
        		+ "	- CardAccountNumber: String\r\n"
        		+ "	- ExpiryDate: Date\r\n"
        		+ "	+ processPayment(): Boolean\r\n"
        		+ "}\r\n"
        		+ "class PaymentFactory {\r\n"
        		+ "	+ {abstract} createPayment(Real amount): Payment\r\n"
        		+ "}\r\n"
        		+ "class CashPaymentFactory {\r\n"
        		+ "	+ createPayment(Real amount): Payment\r\n"
        		+ "}\r\n"
        		+ "class CardPaymentFactory {\r\n"
        		+ "	+ createPayment(Real amount): Payment\r\n"
        		+ "}\r\n"
        		+ "PaymentFactory--> Payment : creates\r\n"
        		+ "CashPaymentFactory--> CashPayment : creates\r\n"
        		+ "CardPaymentFactory--> CardPayment : creates\r\n"
        		+ "CashPaymentFactory--|> PaymentFactory\r\n"
        		+ "CardPaymentFactory--|> PaymentFactory\n";
        systemSetting += "注意输出的结果中不要出现上面示例以外的PlantUML语法，因为后续步骤仅支持PlantUML语法的子集。PlantUML中不要添加注释。PlantUML中类的方法格式遵循'类型 参数名'的格式，不要出现'参数名:类型'的格式。对于现有类图中未修改的部分要完整保留。方法要具有返回值，可以使用void。";
        
        String res = "";
        try {
			res = callGPT4(systemSetting, prompt);
			res = res.replaceAll("abstract class", "calss");
			res = res.replaceAll("interface", "class");
			resultText.setText(res);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    
    public static String callGPT4(String systemSetting, String prompt) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);


        Gson gson = new Gson();
        GPTRequest request = new GPTRequest("gpt-4o", Arrays.asList(new Message("system", systemSetting), new Message("user", prompt)));
        String requestBody = gson.toJson(request);

    
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

      
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

       
        GPTResponse gptResponse = gson.fromJson(response.toString(), GPTResponse.class);
        String content = gptResponse.getChoices().get(0).getMessage().getContent();

        return content;
    }
    
    private void handleAdopt(ActionEvent e) {
    	String res = resultText.getText();

        URI uri = cdResource.getURI();
        IFile file = ResourcesPlugin.getWorkspace().getRoot()
                .getFile(new Path(uri.toPlatformString(true)));
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8))) {
            try {
				file.setContents(inputStream, IResource.FORCE, null);
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        // 刷新资源（可选，确保Eclipse检测到更改）
        try {
			file.refreshLocal(IResource.DEPTH_ZERO, null);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        // 重新加载Resource
        ResourceSet resourceSet = cdResource.getResourceSet();
        cdResource.unload();
        resourceSet.getResources().remove(cdResource);
        XtextResource reloadedResource = (XtextResource) resourceSet.getResource(uri, true);
        cdResource = reloadedResource;
        DomainKnowledgePanel.cdResource = reloadedResource;
//        try {
//			cdResource.save(Collections.EMPTY_MAP);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
        
        JOptionPane.showMessageDialog(this, 
            "已提交结果：\n" + resultText.getText(), 
            "操作确认", 
            JOptionPane.INFORMATION_MESSAGE);
    }

}

class DomainKnowledgePanel extends JPanel {
	public static Resource cdResource;
	// 领域关键词
    private JTextArea domainKeywordText;
    // 领域知识--XMI格式Ecore
    private JTextArea domainKnowledgeText;
    // 代码生成提示词
    private JTextArea promptText;
    // 返回结果
    private JTextArea resultText;
    
    private static final String API_URL = "https://api.chatfire.cn/v1/chat/completions";
    private static final String API_KEY = System.getenv("RAPIDOOD_API_KEY");
    
    public DomainKnowledgePanel(Resource cdResource) {
        this.cdResource = cdResource;
        initComponents();
    }

    private void initComponents() {
    	
        setLayout(new BorderLayout(5, 5));
   
        // 右侧主区域
        createMainPanel();
    }
    
    
    private void createMainPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        // 输入区域
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel);
        
        // 按钮区域
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        // 领域关键词
        JPanel DomainKeywordPanel = createTextPanel("抽取出领域关键词：", true);
        domainKeywordText = (JTextArea)((JScrollPane)DomainKeywordPanel.getComponent(1)).getViewport().getView();
        // 领域知识
        JPanel DomainKnowledgePanel = createTextPanel("检索领域知识：", true);
        domainKnowledgeText = (JTextArea)((JScrollPane)DomainKnowledgePanel.getComponent(1)).getViewport().getView();
        // 提示词输入
        JPanel promptPanel = createTextPanel("提示词：", true);
        promptText = (JTextArea) ((JScrollPane)promptPanel.getComponent(1)).getViewport().getView();
        
        // 结果展示
        JPanel resultPanel = createTextPanel("返回结果：", false);
        resultText = (JTextArea) ((JScrollPane)resultPanel.getComponent(1)).getViewport().getView();
        
        panel.add(DomainKeywordPanel);
        panel.add(DomainKnowledgePanel);
        panel.add(promptPanel);
        panel.add(resultPanel);
        return panel;
    }

    private JPanel createTextPanel(String label, boolean editable) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(label), BorderLayout.NORTH);
        
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(editable);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(0, 400));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton extractBtn = new JButton("领域关键词抽取");
        extractBtn.addActionListener(this::handleExtract);
        
        JButton searchBtn = new JButton("领域知识检索");
        searchBtn.addActionListener(this::handleSearch);
        
        JButton generateBtn = new JButton("代码片段生成");
        generateBtn.addActionListener(this::handleGenerate);
        
        JButton adoptBtn = new JButton("采用代码片段");
        adoptBtn.addActionListener(this::handleAdopt);
        
        panel.add(extractBtn);
        panel.add(searchBtn);
        panel.add(generateBtn);
        panel.add(adoptBtn);
        return panel;
    }
    
    private void handleExtract(ActionEvent e) {
    	ClassDiagram cd = (ClassDiagram)cdResource.getContents().get(0);
    	INode cdNode = NodeModelUtils.getNode(cd);
        String cdText = cdNode.getText();
    	String prompt = "";
    	prompt += "根据下面的设计模型，识别其领域，给出相应的领域关键词。\r\n"
    			+ "[设计模型]\r\n";
    	prompt += cdText
    			+ "\n"
    			+ "[输出示例]\r\n"
    			+ "Retail Management System, Point of Sale (POS) System, Inventory Management, Order Processing, Payment Integration, Supplier Procurement, Repository Pattern, Product Catalog, Cashier Desk/Cash Register, Sales Transactions";
    	String res = "Retail Management System, Point of Sale (POS) System, Inventory Management, Order Processing, Payment Integration, Supplier Procurement, Repository Pattern, Product Catalog, Cashier Desk/Cash Register, Sales Transactions";
    	String systemSetting = "注意输出结果要严格参考输出示例，不要出现多余内容。";
    	try {
    		res = callGPT4(systemSetting, prompt);
    		domainKeywordText.setText(res);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
    
    private void handleSearch(ActionEvent e) {
    	String prompt = "根据领域关键词，给出该领域的一个Ecore模型实例\r\n"
    			+ "[领域关键词]\r\n";
    	prompt += domainKeywordText.getText() 
    			+ "\n"
    			+ "[输出示例]";
    	prompt += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
    			+ "<xmi:XMI xmi:version=\"2.0\" \r\n"
    			+ "  xmlns:xmi=\"http://www.omg.org/XMI\" \r\n"
    			+ "  xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\">\r\n"
    			+ "  \r\n"
    			+ "  <ecore:EPackage name=\"RetailCore\" nsURI=\"http://retail/core\">\r\n"
    			+ "    <eClassifiers xmi:type=\"ecore:EClass\" name=\"Store\">\r\n"
    			+ "      <eStructuralFeatures xmi:type=\"ecore:EAttribute\" name=\"id\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt\"/>\r\n"
    			+ "      <eStructuralFeatures xmi:type=\"ecore:EAttribute\" name=\"name\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\r\n"
    			+ "      <eStructuralFeatures xmi:type=\"ecore:EReference\" name=\"cashDesks\" upperBound=\"-1\" eType=\"#//CashDesk\"/>\r\n"
    			+ "    </eClassifiers>\r\n"
    			+ "\r\n"
    			+ "    <eClassifiers xmi:type=\"ecore:EClass\" name=\"ProductCatalog\">\r\n"
    			+ "      <eStructuralFeatures xmi:type=\"ecore:EReference\" name=\"items\" upperBound=\"-1\" eType=\"#//Item\"/>\r\n"
    			+ "    </eClassifiers>\r\n"
    			+ "\r\n"
    			+ "    <eClassifiers xmi:type=\"ecore:EClass\" name=\"Sale\">\r\n"
    			+ "      <eStructuralFeatures xmi:type=\"ecore:EReference\" name=\"lineItems\" upperBound=\"-1\" containment=\"true\" eType=\"#//SalesLineItem\"/>\r\n"
    			+ "      <eStructuralFeatures xmi:type=\"ecore:EReference\" name=\"payment\" eType=\"#//Payment\"/>\r\n"
    			+ "    </eClassifiers>\r\n"
    			+ "  </ecore:EPackage>\r\n"
    			+ "\r\n"
    			+ "  <ecore:EPackage name=\"Payment\" nsURI=\"http://retail/payment\">\r\n"
    			+ "    <eClassifiers xmi:type=\"ecore:EClass\" name=\"Payment\" abstract=\"true\">\r\n"
    			+ "      <eStructuralFeatures xmi:type=\"ecore:EAttribute\" name=\"amount\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EFloat\"/>\r\n"
    			+ "    </eClassifiers>\r\n"
    			+ "    \r\n"
    			+ "    <eClassifiers xmi:type=\"ecore:EClass\" name=\"CashPayment\" eSuperTypes=\"#//Payment\">\r\n"
    			+ "      <eStructuralFeatures xmi:type=\"ecore:EAttribute\" name=\"balance\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EFloat\"/>\r\n"
    			+ "    </eClassifiers>\r\n"
    			+ "  </ecore:EPackage>\r\n"
    			+ "\r\n"
    			+ "  <ecore:EPackage name=\"Repository\" nsURI=\"http://retail/repository\">\r\n"
    			+ "    <eClassifiers xmi:type=\"ecore:EClass\" name=\"StoreRepository\">\r\n"
    			+ "      <eOperations name=\"findStore\">\r\n"
    			+ "        <eParameters xmi:type=\"ecore:EParameter\" name=\"condition\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\r\n"
    			+ "        <eType xmi:type=\"ecore:EClassifier\" href=\"http://retail/core#//Store\"/>\r\n"
    			+ "      </eOperations>\r\n"
    			+ "    </eClassifiers>\r\n"
    			+ "  </ecore:EPackage>\r\n"
    			+ "</xmi:XMI>";
    	String res = "";
    	String systemSetting = "注意输出结果要严格参考输出示例，不要出现多余内容。";
    	try {
    		res = callGPT4(systemSetting, prompt);
    		domainKnowledgeText.setText(res);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
    
    private void handleGenerate(ActionEvent e) {
    	
    	ClassDiagram cd = (ClassDiagram)cdResource.getContents().get(0);
    	INode cdNode = NodeModelUtils.getNode(cd);
        String cdText = cdNode.getText();
        String prompt = "基于现有类图内容，结合搜索到的领域模型，给出可以增强了领域完整性的完整PlantUML代码。\n";
        prompt += "[类图]\n";
        prompt += cdText;
        prompt += "\n";
        prompt += "[参考领域模型]\n";
        prompt += domainKnowledgeText.getText();
        prompt += "[输出示例]\n"
        		+ "class Return {\r\n"
        		+ "	- ReturnTime : DateTime\r\n"
        		+ "	- Reason : String\r\n"
        		+ "	+ processRefund() : Boolean\r\n"
        		+ "}\r\n"
        		+ "Sale \"1\"--\"*\" Return\r\n"
        		+ "Payment \"1\"--\"*\" Return\r\n"
        		+ "class Shipment {\r\n"
        		+ "	- TrackingNumber : String\r\n"
        		+ "	- EstimatedArrival : Date\r\n"
        		+ "	- Carrier : String\r\n"
        		+ "}\r\n"
        		+ "OrderProduct \"1\"--\"1\" Shipment\r\n"
        		+ "class EmployeeRole {\r\n"
        		+ "	- CanProcessReturns : Boolean\r\n"
        		+ "	- MaxDiscountRate : Real\r\n"
        		+ "}\r\n"
        		+ "Cashier \"1\"--\"1\" EmployeeRole\r\n"
        		+ "class PaymentVerification {\r\n"
        		+ "	- AuthorizationCode : String\r\n"
        		+ "	- VerifiedAt : DateTime\r\n"
        		+ "}\r\n"
        		+ "Payment \"1\"--\"1\" PaymentVerification\r\n"
        		+ "class POSHardware {\r\n"
        		+ "	- DeviceID : String\r\n"
        		+ "	- LastMaintenance : Date\r\n"
        		+ "	- Status : DeviceStatus\r\n"
        		+ "}\r\n"
        		+ "enum DeviceStatus {\r\n"
        		+ "	ONLINE\r\n"
        		+ "	OFFLINE\r\n"
        		+ "	NEEDS_MAINTENANCE\r\n"
        		+ "}\r\n"
        		+ "CashDesk \"1\"--\"*\" POSHardware";
        promptText.setText(prompt);
        
        String systemSetting = "注意输出结果要严格参考输出示例，不要出现多余内容。对于类图中原有的内容要全部保留。输出的结果中不要出现上面示例以外的PlantUML语法，因为后续步骤仅支持PlantUML语法的子集。";
        String res = "";
        try {
        	res = callGPT4(systemSetting, prompt);
        	resultText.setText(res);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
    }
    
    
    public static String callGPT4(String systemSetting, String prompt) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);


        Gson gson = new Gson();
        GPTRequest request = new GPTRequest("gpt-4o", Arrays.asList(new Message("system", systemSetting), new Message("user", prompt)));
        String requestBody = gson.toJson(request);

    
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

      
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

       
        GPTResponse gptResponse = gson.fromJson(response.toString(), GPTResponse.class);
        String content = gptResponse.getChoices().get(0).getMessage().getContent();

        return content;
    }
    
    private void handleAdopt(ActionEvent e) {
        String res = resultText.getText();
        URI uri = cdResource.getURI();
        IFile file = ResourcesPlugin.getWorkspace().getRoot()
                .getFile(new Path(uri.toPlatformString(true)));
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8))) {
            try {
				file.setContents(inputStream, IResource.FORCE, null);
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        // 刷新资源（可选，确保Eclipse检测到更改）
        try {
			file.refreshLocal(IResource.DEPTH_ZERO, null);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        // 重新加载Resource
        ResourceSet resourceSet = cdResource.getResourceSet();
        cdResource.unload();
        resourceSet.getResources().remove(cdResource);
        XtextResource reloadedResource = (XtextResource) resourceSet.getResource(uri, true);
        cdResource = reloadedResource;
        DesignPatternPanel.cdResource = reloadedResource;
//        try {
//			cdResource.save(Collections.EMPTY_MAP);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
        
        JOptionPane.showMessageDialog(this, 
            "已提交结果：\n" + resultText.getText(), 
            "操作确认", 
            JOptionPane.INFORMATION_MESSAGE);
    }

}

class GPTRequest {
	private String model;
	private List<Message> messages;

	public GPTRequest(String model, List<Message> messages) {
	     this.model = model;
	     this.messages = messages;
	}
}

//消息内容类
class Message {
	private String role;
	private String content;

	public Message(String role, String content) {
	     this.role = role;
	     this.content = content;
	}
	public String getContent() {
		return content;
	}
}

//响应的Java类
class GPTResponse {
	private List<Choice> choices;

	public List<Choice> getChoices() {
		return choices;
	}
}

//Choice类，表示响应中的每个选择
class Choice {
	private Message message;

	public Message getMessage() {
		return message;
	}
}

//Message类中的`content`字段表示GPT-4的响应内容
class ResponseMessage {
	private String content;

	public String getContent() {
		return content;
	}
}
