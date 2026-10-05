package com.rm2pt.rapidood.generator.ui;

import javax.swing.*;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.rm2pt.rapidood.cd.classDiagram.AbstractElement;
import com.rm2pt.rapidood.cd.classDiagram.ClassDiagram;
import com.rm2pt.rapidood.cd.classDiagram.ClassDiagramFactory;
import com.rm2pt.rapidood.cd.classDiagram.Operation;
import com.rm2pt.rapidood.cd.classDiagram.PackageDeclaration;
import com.rm2pt.rapidood.cd.classDiagram.Parameter;
import com.rm2pt.rapidood.cd.classDiagram.PrimitiveType;
import com.rm2pt.rapidood.cd.classDiagram.Relationship;

import net.mydreamy.requirementmodel.rEMODEL.Contract;
import net.mydreamy.requirementmodel.rEMODEL.DomainModel;
import net.mydreamy.requirementmodel.rEMODEL.RequirementModel;
import net.mydreamy.requirementmodel.rEMODEL.UseCaseModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.List;
public class DomainModelDesignDialog extends JFrame{
	private JTabbedPane tabbedPane;
	public DomainModelDesignDialog(Resource rmResource, Resource cdResource) {
    	initUI(rmResource, cdResource);
    }
    private void initUI(Resource rmResource, Resource cdResource) {
        setTitle("设计生成工具");
        setSize(1024, 768);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // 添加职责分配标签页
        addResponsibilityTab(rmResource, cdResource);
        
        // 添加领域划分标签页
        addDomainPartitionTab(rmResource, cdResource);
        // 添加其他标签页（示例）
//        addConfigurationTab();
    }
	

    private void addResponsibilityTab(Resource rmResource, Resource cdResource) {
        ResponsibilityPanel responsibilityPanel = new ResponsibilityPanel(rmResource, cdResource);
        tabbedPane.addTab("职责分配", responsibilityPanel);
    }
    
    private void addDomainPartitionTab(Resource rmResource, Resource cdResource) {
    	DomainPartitionPanel domainPartitionPanel = new DomainPartitionPanel(rmResource, cdResource);
    	tabbedPane.addTab("领域划分", domainPartitionPanel);
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

class ResponsibilityAssign {
	public String Operation;
	public String owner;
	public String reasoning;
}
class DomainPartitionPanel extends JPanel {
	private Resource rmResource;
	private Resource cdResource;
    private JTextArea promptText;
    private JTextArea resultText;
    private HashMap<String, com.rm2pt.rapidood.cd.classDiagram.AbstractElement> nameToClass = new HashMap<>();
    private static final String API_URL = "https://api.chatfire.cn/v1/chat/completions";
    private static final String API_KEY = System.getenv("RAPIDOOD_API_KEY");
    
    public DomainPartitionPanel(Resource rmResource, Resource cdResource) {
        this.rmResource = rmResource;
        this.cdResource = cdResource;
        getNameToClass();
        initComponents();
    }

    private void getNameToClass() {
    	ClassDiagram cd = (ClassDiagram)cdResource.getContents().get(0);
    	for (com.rm2pt.rapidood.cd.classDiagram.AbstractElement element : cd.getElements()) {
    		if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Class) {
    			nameToClass.put(((com.rm2pt.rapidood.cd.classDiagram.Class)element).getName(), element);
    		} else if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Interface) {
    			nameToClass.put(((com.rm2pt.rapidood.cd.classDiagram.Interface)element).getName(), element);
    		} else if (element instanceof com.rm2pt.rapidood.cd.classDiagram.AbstractClass) {
    			nameToClass.put(((com.rm2pt.rapidood.cd.classDiagram.AbstractClass)element).getName(), element);
    		} else if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Enum) {
    			nameToClass.put(((com.rm2pt.rapidood.cd.classDiagram.Enum)element).getName(), element);
    		} 
    	}
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
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        // 提示词输入
        JPanel promptPanel = createTextPanel("提示词：", true);
        promptText = (JTextArea) ((JScrollPane)promptPanel.getComponent(1)).getViewport().getView();;
        
        // 结果展示
        JPanel resultPanel = createTextPanel("返回结果：", false);
        resultText = (JTextArea) ((JScrollPane)resultPanel.getComponent(1)).getViewport().getView();
        
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
        scrollPane.setPreferredSize(new Dimension(0, 200));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton assignBtn = new JButton("领域划分");
        assignBtn.addActionListener(this::handleAssign);
        
        JButton adoptBtn = new JButton("采用结果");
        adoptBtn.addActionListener(this::handleAdopt);
        
        panel.add(assignBtn);
        panel.add(adoptBtn);
        return panel;
    }
    
    private void handleAssign(ActionEvent e) {

        ClassDiagram cd = (ClassDiagram)cdResource.getContents().get(0);
        RequirementModel rm = (RequirementModel)rmResource.getContents().get(0);
        DomainModel dm = rm.getDomainModel();
        INode cdNode = NodeModelUtils.getNode(cd);
        String cdText = cdNode.getText();
        // 生成提示词
        String prompt = "基于以下类的语义关系和职责，将其划分为高内聚的包（package）。按照输出格式返回结果。\n";
        prompt += "[类图]\n";
        prompt += cdText;
        prompt += "\n";
        prompt += "[设计原则]\n"
        		+ "1. 高内聚原则（High Cohesion Principle）：同一包中的类应当具有较强的内聚性。\n"
        		+ "2. 低耦合原则（Low Coupling Principle）：不同包中的类之间应当具有较低的耦合性。\n"
        		+ "[输出格式]\n"
        		+ "{\n"
        		+ "\"package\" : [ {\n"
        		+ "\"name\": \" 包名\",\n"
        		+ "\"classes\": [\" 类 1\", \" 类 2\"]\n"
        		+ "\"reasoning\": \" 划分理由\"\n"
        		+ "}]\n"
        		+ "}";
        promptText.setText(prompt);
        
        String systemSetting = "注意输出结果要严格符合输出格式，不要出现多余内容。";
        String res = "";
        try {
        	res = callGPT4(systemSetting, prompt);
        	resultText.setText(res);
        } catch (Exception exception) {
        	exception.printStackTrace();
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
        res = res.replaceAll("```json", "");
        res = res.replaceAll("```", "");
        ClassDiagram cd = (ClassDiagram)cdResource.getContents().get(0);
        System.out.println(res);
        Gson gson = new Gson();
        PackageList packageList = gson.fromJson(res, PackageList.class);
        List<Package> packages = packageList.getPackages();
        for (Package pack : packages) {
        	com.rm2pt.rapidood.cd.classDiagram.PackageDeclaration newPack = ClassDiagramFactory.eINSTANCE.createPackageDeclaration();
        	newPack.setName(pack.getName());
        	for (String clz : pack.getClasses()) {
        		AbstractElement element = nameToClass.get(clz);
        		cd.getElements().remove(element);
        		newPack.getElements().add(element);
        	}
        	cd.getElements().add(newPack);
        }
        
        try {
			cdResource.save(Collections.EMPTY_MAP);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        JOptionPane.showMessageDialog(this, 
            "已提交结果：\n" + res, 
            "操作确认", 
            JOptionPane.INFORMATION_MESSAGE);
    }

}

class PackageList {
//	private String name;
    @SerializedName("package") // JSON字段名与Java关键字冲突，需要特殊标注
    private List<Package> packages;

    public List<Package> getPackages() {
        return packages;
    }
}

class Package {
    private String name;
    private List<String> classes;
    private String reasoning;

    // Getters
    public String getName() { return name; }
    public List<String> getClasses() { return classes; }
    public String getReasoning() { return reasoning; }
}

class ResponsibilityPanel extends JPanel {
	private ArrayList<String> contractList = new ArrayList<>();
	private HashMap<String, String> contractNameToString = new HashMap<>();
	private HashMap<String, Contract> nameToContract = new HashMap<>();
	private Resource rmResource;
	private Resource cdResource;
    private JList<String> itemList;
    private JTextArea promptText;
    private JTextArea resultText;
    private static final String API_URL = "https://api.chatfire.cn/v1/chat/completions";
    private static final String API_KEY = System.getenv("RAPIDOOD_API_KEY");
    
    public ResponsibilityPanel(Resource rmResource, Resource cdResource) {
        this.rmResource = rmResource;
        this.cdResource = cdResource;
        loadContracts();
        initComponents();
    }
    private void loadContracts() {
    	ClassDiagram cd = (ClassDiagram)cdResource.getContents().get(0);
		RequirementModel rm = (RequirementModel)rmResource.getContents().get(0);
		UseCaseModel ucm = rm.getUseCaseModel();
		List<Contract> contracts = ucm.getContract();
		for (Contract contract : contracts) {
			String name = contract.getOp().getName();
			contractList.add(name);
			INode node = NodeModelUtils.getNode(contract);
			String text = node.getText();
			contractNameToString.put(name, text);
			nameToContract.put(name, contract);
		}
    }
    private void initComponents() {
    	
        setLayout(new BorderLayout(5, 5));

        // 左侧列表
        createLeftPanel();
        
        // 右侧主区域
        createMainPanel();
    }
    
    private void createLeftPanel() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String contract : contractList) {
        	listModel.addElement(contract);
        }

        itemList = new JList<>(listModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setPreferredSize(new Dimension(150, 0));
        
        add(scrollPane, BorderLayout.WEST);
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
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        // 提示词输入
        JPanel promptPanel = createTextPanel("提示词：", true);
        promptText = (JTextArea) ((JScrollPane)promptPanel.getComponent(1)).getViewport().getView();;
        
        // 结果展示
        JPanel resultPanel = createTextPanel("返回结果：", false);
        resultText = (JTextArea) ((JScrollPane)resultPanel.getComponent(1)).getViewport().getView();
        
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
        scrollPane.setPreferredSize(new Dimension(0, 200));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton assignBtn = new JButton("分配职责");
        assignBtn.addActionListener(this::handleAssign);
        
        JButton adoptBtn = new JButton("采用结果");
        adoptBtn.addActionListener(this::handleAdopt);
        
        panel.add(assignBtn);
        panel.add(adoptBtn);
        return panel;
    }
    
    private void handleAssign(ActionEvent e) {
        String selected = itemList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "请先选择一个系统操作");
            return;
        }
        ClassDiagram cd = (ClassDiagram)cdResource.getContents().get(0);
        RequirementModel rm = (RequirementModel)rmResource.getContents().get(0);
        DomainModel dm = rm.getDomainModel();
        INode dmNode = NodeModelUtils.getNode(dm);
        String dmText = dmNode.getText();
        // 生成提示词
        String prompt = "";
        prompt += "基于领域驱动设计中充血设计的原则，将系统操作 (SystemOperation) 分配给最合适的领域类(Domain Class)。按照输出格式返回结果。\n";
        prompt += "[领域类]\n";
        prompt += dmText;
        prompt += "\n";
        prompt += "[系统操作合约]\n";
        prompt += contractNameToString.get(selected);
        prompt += "\n";
        prompt += "[设计原则]\n";
        prompt += "1. 信息专家原则（Information Expert Principle）：将操作分配给拥有所需信息的类。\n"
        		+ "2. 高内聚原则（High Cohesion Principle）：将操作分配给与其相关性高的类。\n"
        		+ "3. 单一职责原则（Single Responsibility Principle）：每个类应当只负责一个功能。\n";
        prompt += "[输出格式]\n";
        prompt += "{\n"
        		+ "\"Operation\": \" 系统操作名\",\n"
        		+ "\"owner\": \" 目标类名\",\n"
        		+ "\"reasoning\": \" 将该系统操作分配给目标类的原因\"\n"
        		+ "}\n";
        promptText.setText(prompt);
        
        String systemSetting = "注意输出结果要严格符合输出格式，不要出现多余内容。";
        String res = "";
        try {
        	res = callGPT4(systemSetting, prompt);
        	resultText.setText(res);
        } catch (Exception exception) {
        	exception.printStackTrace();
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
        ClassDiagram cd = (ClassDiagram)cdResource.getContents().get(0);
        Gson gson = new Gson();
        ResponsibilityAssign assign = gson.fromJson(res, ResponsibilityAssign.class);
        System.out.println(assign.Operation);
        System.out.println(assign.owner);
        System.out.println(assign.reasoning);
        Operation newOp = ClassDiagramFactory.eINSTANCE.createOperation();
        newOp.setName(assign.Operation);
        newOp.setVisibility("+");
        PrimitiveType returnType = ClassDiagramFactory.eINSTANCE.createPrimitiveType();
        returnType.setName("Boolean");
        newOp.setReturnType(returnType);
        
        net.mydreamy.requirementmodel.rEMODEL.Operation op = nameToContract.get(assign.Operation).getOp();
        List<net.mydreamy.requirementmodel.rEMODEL.Parameter> parameters = op.getParameter();
        for (net.mydreamy.requirementmodel.rEMODEL.Parameter p : parameters) {
//        	if (!(p.getType() instanceof net.mydreamy.requirementmodel.rEMODEL.PrimitiveTypeCS)) 
//        		continue;
        	Parameter newP = ClassDiagramFactory.eINSTANCE.createParameter();
        	newP.setName(p.getName());
        	PrimitiveType pType = ClassDiagramFactory.eINSTANCE.createPrimitiveType();
        	pType.setName("Integer");
        	newP.setType(pType);
        	newOp.getParams().add(newP);
        }
        
        // 查找assign.owner对应的Class
        for (AbstractElement element : cd.getElements()) {
        	if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Class && ((com.rm2pt.rapidood.cd.classDiagram.Class) element).getName().equals(assign.owner)) {
        		((com.rm2pt.rapidood.cd.classDiagram.Class) element).getOperations().add(newOp);
        		break;
        	}
        }        
        
        try {
			cdResource.save(Collections.EMPTY_MAP);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        JOptionPane.showMessageDialog(this, 
            "已提交结果：\n" + res, 
            "操作确认", 
            JOptionPane.INFORMATION_MESSAGE);
    }

}
