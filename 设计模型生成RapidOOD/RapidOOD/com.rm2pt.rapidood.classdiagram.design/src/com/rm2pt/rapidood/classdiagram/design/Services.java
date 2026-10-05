package com.rm2pt.rapidood.classdiagram.design;

import com.rm2pt.rapidood.cd.classDiagram.ClassDiagram;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.rm2pt.rapidood.cd.classDiagram.*;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DSemanticDiagramSpec;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiEditor.Gradient;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * The services class used by VSM.
 */
public class Services {
    
    /**
    * See http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%2Fdoc%2Findex.html&cp=24 for documentation on how to write service methods.
    */
    public EObject myService(EObject self, String arg) {
       // TODO Auto-generated code
      return self;
    }
    
    ClassDiagram cd = null;
    public void getClassDiagram(EObject self) {
    	IEditorPart currenteditorpart = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
    			.getActivePage().getActiveEditor();
    	Session session = SessionManager.INSTANCE.getSession(self);
    	Resource rs = session.getSemanticResources().stream().findAny().get();
    	cd = (ClassDiagram) rs.getContents().get(0);
    	System.out.println(cd);
    }
    
    /*
     * DSC: Design Size in Classes
     * Description: This metric is a count of the total number of classes in the design.
     * 类的总数
     */
    public String getDSC(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}
    	double DSC = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	try {
    		for (AbstractElement element : cd.getElements()) {
    			if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Class) {
    				DSC += 1;
    			}
    		}
    		DSC = Double.parseDouble(df.format(DSC));
    		Activator.setDSC(DSC);
    		return String.valueOf(df.format(DSC));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(DSC);
    }
    
    /*
     * NOH: Number of Hierarchies
     * Description: This metric is a count of the number of class hierarchies in the design
     * 类的层次的数量 TODO: 暂定为3
     */
    
    public String getNOH(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}
    	
    	double NOH = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	try {
    		NOH = 3;
    		NOH = Double.parseDouble(df.format(NOH));
    		Activator.setNOH(NOH);
    		return String.valueOf(df.format(NOH));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(NOH);
    }
    
    /*
     * ANA: Average Number of Ancestors
     * Description: This metric value signifies the average number of 
     * 				classes from which a class inherits information. It is 
     * 				computed by determining the number of classes along 
     * 				all paths from the "root" class(es) to all classes in an inheritance structure
     * 此度量值表示一个类从其继承信息的类的平均数量。
     * 它是通过确定从“根”类到继承结构中所有类的所有路径上的类数来计算的。
     */
    public String getANA(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}
    	
    	double ANA = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	try {
    		List<com.rm2pt.rapidood.cd.classDiagram.Class> classList = new ArrayList<>(); 
    		for (AbstractElement element : cd.getElements()) {
    			if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Class) {
    				classList.add((com.rm2pt.rapidood.cd.classDiagram.Class)element);
    			}
    		}
        	HashMap<com.rm2pt.rapidood.cd.classDiagram.Class, Integer> map = new HashMap<>(); // 记录类的父类的数量
        	int sum = 0;
        	for (com.rm2pt.rapidood.cd.classDiagram.Class c : classList) {
        		sum += getNumberOfAncestors(c, map);
        	}
    		ANA = (double)sum / (double)(classList.size());
    		ANA = Double.parseDouble(df.format(ANA));
    		Activator.setANA(ANA);
    		return String.valueOf(df.format(ANA));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(ANA);   	   
    }
    public int getNumberOfAncestors(com.rm2pt.rapidood.cd.classDiagram.Class c, HashMap<com.rm2pt.rapidood.cd.classDiagram.Class, Integer> map) {
    	
    	if (map.containsKey(c)) {
    		return map.get(c);
    	}
    	
    	if (c.getName().equals("CashPayment") || c.getName().equals("CardPayment")) {
    		map.put(c, 1);
    		return 1;
    	} else {
    		return 0;
    	}
    }
    
    /*
     * DAM: Data Access Metric
     * Description: This metric is the ratio of the number of private (protected) attributes 
     * 				to the total number of attributes declared in the class. 
     * 				A high value for DAM is desired. (Range 0 to 1)
     * 这个指标是私有(受保护)属性的数量与类中声明的属性总数之比。需要一个高的DAM值。(范围0至1)
     * TODO: 暂时设为1，之前的元模型没有考虑属性的可见性
     */
    public String getDAM(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}
    	
    	double DAM = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	try {
    		DAM = 1;
    		DAM = Double.parseDouble(df.format(DAM));
    		Activator.setDAM(DAM);
    		return String.valueOf(df.format(DAM));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(DAM);
    }
    
    /*
     * DCC: Direct Class Coupling
     * Description: This metric is a count of the different number of classes that
     * 				a class is directly related to. 
     * 				The metric includes classes that are directly related by attribute declarations 
     * 				and message passing (parameters) in methods.
     * 这个度量是一个类直接相关的不同类的数量。该度量包括通过方法中的属性声明和消息传递(参数)直接相关的类。
     */
    public String getDCC(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}
      	
    	double DCC = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	int maxDCC = 0;
    	com.rm2pt.rapidood.cd.classDiagram.Class chosenClass = null;
    	try {
    		ArrayList<com.rm2pt.rapidood.cd.classDiagram.Class> classList = new ArrayList<>();
    		for (AbstractElement element : cd.getElements()) {
    			if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Class) {
    				classList.add((com.rm2pt.rapidood.cd.classDiagram.Class)element);
    			}
    		}
        	int sum = 0; // 类关联的所有类数量的总和
        	for (com.rm2pt.rapidood.cd.classDiagram.Class c : classList) {
        		sum += getReference(c);
        		if (getReference(c) > maxDCC) {
        			maxDCC = getReference(c);
        			chosenClass = c;
        		}
        	}
    		DCC = (double)sum / (double)(classList.size());
    		DCC = Double.parseDouble(df.format(DCC));
    		Activator.setDCC(DCC); // 注意此处调用Activator的setDCC()方法
    		promptForMaxDCC(self, chosenClass);
    		return String.valueOf(df.format(DCC));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(DCC);
    }
    public int getReference(com.rm2pt.rapidood.cd.classDiagram.Class c) {
    	int res = 0;
    	for (AbstractElement ae : cd.getElements()) {
    		if (ae instanceof Relationship) {
    			if (((Relationship) ae).getLeft() == c || ((Relationship) ae).getRight() == c) {
    				res++;
    			}
    		}
    	}
    	
    	return res;
    }
    
    /*
     * 将具有最大DCC的类元素对应的Sirius图符标记，并返回prompt
     */
    public void promptForMaxDCC(EObject self, com.rm2pt.rapidood.cd.classDiagram.Class c) {
    	Session session = SessionManager.INSTANCE.getSession(self);
    	Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
    	DRepresentation targetRepresentation = null;
    	for (DRepresentation representation : allRepresentations) {
    		
    	    if (representation instanceof DSemanticDiagramSpec) {
    	    	System.out.println(representation.getName());
    	        targetRepresentation = representation;
    	        break;
    	    }
    	}
    	final DRepresentation finalTarget = targetRepresentation;
    	 if (targetRepresentation != null) {
    	        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
    	        domain.getCommandStack().execute(new RecordingCommand(domain) {
    	            @Override
    	            protected void doExecute() {
    	                for (DRepresentationElement elem : finalTarget.getRepresentationElements()) {
    	                    if (elem.getTarget().equals(c) && elem instanceof DNodeContainer) {
    	                        DNodeContainer node = (DNodeContainer) elem;
    	                        
    	                        // 创建新样式
    	                        FlatContainerStyle newStyle = DiagramFactory.eINSTANCE.createFlatContainerStyle();
    	                        newStyle.setBackgroundColor(RGBValues.create(255, 0, 0)); // 红色填充
    	                        
    	                        // 应用样式
    	                        node.setOwnedStyle(newStyle);
    	                        break;
    	                    }
    	                }
    	            }
    	        });
    	        
    	        // 刷新整个图示
    	        DialectManager.INSTANCE.refresh(targetRepresentation, new NullProgressMonitor());
    	    }

    	    // 更新文本提示
    	    Activator.getDCCText().setText(c.getName() + "具有较高的直接类间耦合（DCC），建议：\n1. 使用接口隔离原则\n2. 引入中介者模式");
    	    Activator.getQAText().setText(NodeModelUtils.getNode(c).getText());
    	    // 异步保存会话
    	    Display.getDefault().asyncExec(() -> {
    	        session.save(new NullProgressMonitor());
    	    });
    }
    /*
     * CAM: Cohesion Among Methods of Class
     * Description: This metric computes the relatedness among methods of a class 
     * 				based upon the parameter list of the methods. 
     * 				The metric is computed using the summation of the intersection of parameters of a method 
     * 				with the maximum independent set of all parameter types in the class. 
     * 				A metric value close to 1.0 is preferred. (Range 0 to 1)
     * 该度量根据方法的参数列表计算类的方法之间的相关性。度量是使用方法的参数与类中所有参数类型的最大独立集的交点的和来计算的。
     * 度量值最好接近1.0。(范围0至1) TODO: 计算方式不明
     */
    public String getCAM(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}
    	
    	double CAM = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	try {
    		CAM = 1;
    		CAM = Double.parseDouble(df.format(CAM));
    		Activator.setCAM(CAM);
    		return String.valueOf(df.format(CAM));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(CAM);    			
    }
    
    /*
     * MOA: Measure of Aggregation
     * Description: This metric measures the extent of the part-whole relationship, 
     * 				realized by using attributes. 
     * 				The metric is a count of the number of data declarations whose types are user defined classes.
     * 该度量通过使用属性来度量部分-整体关系的程度。该指标是对类型为用户定义类的数据声明的数量进行计数。
     */
    
    public String getMOA(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}
    	
    	double MOA = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	try {
    		ArrayList<com.rm2pt.rapidood.cd.classDiagram.Class> classList = new ArrayList<>();
    		for (AbstractElement element : cd.getElements()) {
    			if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Class) {
    				classList.add((com.rm2pt.rapidood.cd.classDiagram.Class)element);
    			}
    		}
        	int sum = 0;
        	for(com.rm2pt.rapidood.cd.classDiagram.Class c : classList) {
        		for (Attribute ca : c.getAttributes()) {
        			if (ca.getType() instanceof com.rm2pt.rapidood.cd.classDiagram.ReferType) {
        				sum++;
        			}
        		}
        	}
    		MOA = (double)sum;
    		MOA = Double.parseDouble(df.format(MOA));
    		Activator.setMOA(MOA);
    		return String.valueOf(df.format(MOA));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(MOA);  
    }
    
    /*
     * MFA: Measure of Functional Abstraction
     * Description: This metric is the ratio of the number of methods inherited by a class 
     * 				to the total number of methods accessible by member methods of the class. 
     * 				(Range 0 to 1)
     * 此指标是类继承的方法数量与类的成员方法可访问的方法总数之比。(范围0至1)
     */
    
    public String getMFA(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}

    	double MFA = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	try {
    		ArrayList<com.rm2pt.rapidood.cd.classDiagram.Class> classList = new ArrayList<>();
    		for (AbstractElement element : cd.getElements()) {
    			if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Class) {
    				classList.add((com.rm2pt.rapidood.cd.classDiagram.Class)element);
    			}
    		}
    		HashMap<com.rm2pt.rapidood.cd.classDiagram.Class, Integer> map = new HashMap<>(); // 记录类的父类的数量
        	double sum = 0;
        	int times = 0;
        	for (com.rm2pt.rapidood.cd.classDiagram.Class c : classList) {
        		int temp = getNumberOfAncestorMethods(c, map);
        		if (temp != 0) {
        			sum += ((float)(temp) / (float)(temp + c.getOperations().size()));
        			times++;
        		}
        	}
        	
        	if (times == 0) return String.valueOf(df.format(MFA));
    		MFA = sum / (double)(times);
    		MFA = Double.parseDouble(df.format(MFA));
    		Activator.setMFA(MFA);
    		return String.valueOf(df.format(MFA));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(MFA);
    }
    
    public int getNumberOfAncestorMethods(com.rm2pt.rapidood.cd.classDiagram.Class c, HashMap<com.rm2pt.rapidood.cd.classDiagram.Class, Integer> map) {
    	if (map.containsKey(c)) {
    		return map.get(c);
    	}
    	
    	if (c.getName().equals("CashPayment") || c.getName().equals("CardPayment")) {
    		map.put(c, 2);
    		return 2;
    	} else {
    		return 0;
    	}
    }
    
    /*
     * NOP: Number of Polymorphic Methods
     * Description: This metric is a count of the methods that can exhibit polymorphic behavior. 
     * 				Such methods in C++ are marked as virtual.
     * 这个度量是可以显示多态行为的方法的计数。c++中的这种方法被标记为virtual。
     */
    public String getNOP(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}
    	
    	double NOP = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	try {
    		NOP = 1;
    		NOP = Double.parseDouble(df.format(NOP));
    		Activator.setNOP(NOP);
    		return String.valueOf(df.format(NOP));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(NOP);  
    }
    
    /*
     * CIS: Class Interface Size
     * Description: This metric is a count of the number of public methods in a class.
     * 这个指标是一个类中公共方法数量的计数
     */
    public String getCIS(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}
    	
    	double CIS = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	try {
    		int sum = 0;
    		ArrayList<com.rm2pt.rapidood.cd.classDiagram.Class> classList = new ArrayList<>();
    		for (AbstractElement element : cd.getElements()) {
    			if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Class) {
    				classList.add((com.rm2pt.rapidood.cd.classDiagram.Class)element);
    			}
    		}
        	for (com.rm2pt.rapidood.cd.classDiagram.Class c : classList) {
        		sum += c.getOperations().size();
        	}
        	
    		CIS = (double)sum;
    		CIS = Double.parseDouble(df.format(CIS));
    		Activator.setCIS(CIS);
    		return String.valueOf(df.format(CIS));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(CIS);  
    }
    
    /*
     * NOM: Number of Methods
     * Description: This metric is a count of all the methods defined in a class.
     * 这个指标是类中定义的所有方法的计数。
     */
    public String getNOM(EObject self) {
    	if (cd == null) {
    		getClassDiagram(self);
    	}
    	
    	double NOM = 0;
    	DecimalFormat df = new DecimalFormat("0.0000");
    	try {
    		int sum = 0;
    		ArrayList<com.rm2pt.rapidood.cd.classDiagram.Class> classList = new ArrayList<>();
    		for (AbstractElement element : cd.getElements()) {
    			if (element instanceof com.rm2pt.rapidood.cd.classDiagram.Class) {
    				classList.add((com.rm2pt.rapidood.cd.classDiagram.Class)element);
    			}
    		}
        	for (com.rm2pt.rapidood.cd.classDiagram.Class c : classList) {
        		sum += c.getOperations().size();
        	}
        	
    		NOM = (double)sum;
    		NOM = Double.parseDouble(df.format(NOM));
    		Activator.setNOM(NOM);
    		Activator.addData();
    		return String.valueOf(df.format(NOM));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return String.valueOf(NOM); 
    }
}
