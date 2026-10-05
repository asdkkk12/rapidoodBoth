package com.rm2pt.rapidood.sequencediagram.design;

import com.rm2pt.rapidood.sd.sequenceDiagram.*;
import com.rm2pt.rapidood.sd.sequenceDiagram.Message;
import com.rm2pt.rapidood.sd.sequenceDiagram.MessageEnd;
import com.rm2pt.rapidood.sd.sequenceDiagram.SequenceDiagram;
import com.rm2pt.rapidood.sd.sequenceDiagram.SequenceDiagramFactory;

import java.util.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

/**
 * The services class used by VSM.
 */
public class Services {
    
	public static SequenceDiagram sd;
	public static boolean isInit = false;
	public static List<EObject> ends;
	public static List<Execution> executions;
	public static Map<Message, MessageEnd> messageToSender = new HashMap<>();
	public static Map<Message, MessageEnd> messageToReceiver = new HashMap<>();
    /**
    * See http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%2Fdoc%2Findex.html&cp=24 for documentation on how to write service methods.
    */
    public EObject myService(EObject self, String arg) {
       // TODO Auto-generated code
      return self;
    }
//    public void getSequenceDiagram(EObject self) {
//    	
//    	Session session = SessionManager.INSTANCE.getSession(self);
////    	Resource rs = session.getSemanticResources().stream().findAny().get();
////    	EList<EObject> rsList = rs.getContents();
////    	if (rsList.get(0) instanceof SequenceDiagram) {
////    		sd = (SequenceDiagram)(rsList.get(0));
////    	}
//    	EObject tmp = self;
//    	while (!(tmp instanceof SequenceDiagram)) {
//    		tmp = tmp.eContainer();
//    	}
//    	sd = (SequenceDiagram)tmp;
//    }
//    public void init(EObject self) {
//    	if (isInit) 
//    		return;
//    	getSequenceDiagram(self);
//    	generateEnds(sd);
//    	isInit = true;
//    }
//    public EObject getSource(Message self) {
//    	init(self);
//    	return messageToSender.get(self).getContext();
//    }
//    public EObject getTarget(Message self) {
//    	init(self);
//    	return messageToReceiver.get(self).getContext();
//    }
//    public EObject getSendingEnd(Message self) {
//    	init(self);
//    	return messageToSender.get(self);
//    }
//    public EObject getReceivingEnd(Message self) {
//    	init(self);
//    	return messageToReceiver.get(self);
//    }
//    // 在模型资源更新后，Services中的函数会重新调用
//    public List<EObject> generateEnds(EObject self) {
//    	System.out.println("is this work?");
//    	List<EObject> allContents = self.eContents();
//    	List<EObject> result = new ArrayList<>();
//    	int MessageNum = 0;
//    	for (EObject ob : allContents) {
//    		if (ob instanceof Message) {
//    			Message m = (Message)ob;
//    			MessageEnd sendEnd = SequenceDiagramFactory.eINSTANCE.createMessageEnd();
//    			sendEnd.setContext(m.getSender());
//    			sendEnd.setMessage(m);
//    			sendEnd.setName("Message" + MessageNum + "SenderEnd");
//    			result.add(sendEnd);
//    			messageToSender.put(m, sendEnd);
//    			MessageEnd receiveEnd = SequenceDiagramFactory.eINSTANCE.createMessageEnd();
//    			// 这里暂时不处理Execution和Participant混合End的情况
//    			receiveEnd.setContext(m.getReceiver());
//    			receiveEnd.setMessage(m);
//    			receiveEnd.setName("Message" + MessageNum + "ReceiveEnd");
//    			MessageNum++;
//    			result.add(receiveEnd);
//    			messageToReceiver.put(m, receiveEnd);
//    		} else if (ob instanceof Activation) {
//    			Activation activation = (Activation)ob;
//    			ExecutionStart startEnd = SequenceDiagramFactory.eINSTANCE.createExecutionStart();
//    			startEnd.setContext(activation.getContext());
//    			result.add(startEnd);
//    		} else if (ob instanceof Deactivation) {
//    			Deactivation deactivation = (Deactivation)ob;
//    			ExecutionEnd finishEnd = SequenceDiagramFactory.eINSTANCE.createExecutionEnd();
//    			finishEnd.setContext(deactivation.getContext());
//    			result.add(finishEnd);
//    		}
//    	}
//    	ends = result;
//    	generateExecutions(self);
//    	return result;
//    	
//    }
//    public List<EObject> getEnds(EObject self) {
//    	init(self);
//    	return ends;
//    }
//    public List<Execution> getExecutions(EObject self) {
//    	init(self);
//    	return executions;
//    }
//    public List<Execution> generateExecutions(EObject self) {
//    	List<Execution> result = new ArrayList<>();
//    	Map<Participant, Deque<MixEnd>> map = new HashMap<>();
//    	ExecutionStart startTBD = null;
//    	ExecutionEnd endTBD = null;
//    	for (EObject tmp : ends) {
//    		MixEnd end = (MixEnd)tmp;
//    		if (end instanceof ExecutionStart) {
//    			Participant participant = (Participant)(end.getContext());
//    			if(map.containsKey(participant)) {
//    				Deque<MixEnd> stack = map.get(participant);
//    				stack.push(end);
//    			} else {
//    				Deque<MixEnd> stack = new ArrayDeque<>();
//    				stack.push(end);
//    				map.put(participant, stack);
//    			}
//    		} else if (end instanceof ExecutionEnd) {
//    			Participant participant = (Participant)(end.getContext());
//    			if (!map.containsKey(participant)) {
//    				System.out.println("No ExecutionStart for ExecutionEnd");
//    				continue;
//    			}
//    			if (map.get(participant).isEmpty()) {
//    				System.out.println("No ExecutionStart for ExecutionEnd");
//    				continue;
//    			}
//    			Deque<MixEnd> stack = map.get(participant);
//    			// 跟栈顶的Start完成匹配
//    			// 1. 栈顶Start出栈
//    			ExecutionStart eStart = (ExecutionStart)stack.pop();
//    			ExecutionEnd eEnd = (ExecutionEnd)end;
//    			// 2. 创建一个Execution，并设置其相关属性
//    			Execution execution = SequenceDiagramFactory.eINSTANCE.createExecution();
//    			execution.setStart(eStart);
//    			execution.setEnd(eEnd);
//    			execution.setOwner(participant);
//    			execution.setName(null);
//    			if (startTBD != null) {
//    				startTBD.setContext(execution);
//    			}
//    			if (endTBD != null) {
//    				endTBD.setContext(execution);
//    			}
//    			// 3. 设置Start和End的相关属性
//    			eStart.setExecution(execution);
//    			eStart.setName(null);
//    			eEnd.setExecution(execution);
//    			eEnd.setName(null);
//    			// 4. 如果当前stack中还存在Start，说明当前Execution是嵌套Execution, End的Context需要稍后处理
//    			if (!stack.isEmpty()) {
//    				startTBD = eStart;
//    				endTBD = eEnd;
//    			} else {
//    				eStart.setContext(participant);
//    				eEnd.setContext(participant);
//    				startTBD = null;
//    				endTBD = null;
//    			}
//    			result.add(execution);
//    		}
//    	}
//    	executions = result;
//    	return result;
//    }
    
}
