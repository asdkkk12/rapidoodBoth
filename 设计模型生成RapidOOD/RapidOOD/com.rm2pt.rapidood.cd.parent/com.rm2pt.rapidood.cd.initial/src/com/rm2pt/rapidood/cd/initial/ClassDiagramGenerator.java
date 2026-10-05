package com.rm2pt.rapidood.cd.initial;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

import com.google.common.collect.Iterables;
import com.google.inject.Injector;
import com.rm2pt.rapidood.cd.ClassDiagramStandaloneSetup;
import com.rm2pt.rapidood.cd.classDiagram.*;
import net.mydreamy.requirementmodel.rEMODEL.*;

public class ClassDiagramGenerator {
	// 缓存injector避免重复初始化
	private static Injector injector;
	
	public void doGenerate(Resource resource) {
	    try {
	    	
	        String projectName = ClassDiagramGeneratorHandler.project.getName();
	        IPath designPath = ClassDiagramGeneratorHandler.project.getFullPath().append("Design");
	        String outputFileName = (projectName + ".cd");
	       
	        // 构建平台 URI
	        URI uri = URI.createPlatformResourceURI(designPath.append(outputFileName).toString(), true);
	        
	        // 初始化资源集
	        ResourceSetImpl resourceSet = new ResourceSetImpl();
	        IResourceFactory resourceFactory = getInjector().getInstance(IResourceFactory.class);
	        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("cd", resourceFactory);
	        
	        // 加载或创建资源 ClassDiagram对应的资源
	        Resource newResource = resourceSet.getResource(uri, false);
	        if ((newResource == null)) {
	          newResource = resourceSet.createResource(uri);
	        }
	        generateInitalModel(resource, newResource);
	      } catch (Throwable _e) {
	        throw Exceptions.sneakyThrow(_e);
	      }
	}
	
	private static synchronized Injector getInjector() {
		if (injector == null) {
            injector = new ClassDiagramStandaloneSetup().createInjectorAndDoEMFRegistration();
        }
        return injector;
	}
	
    public void generateInitalModel(Resource source, Resource target) {
	    ClassDiagram classDiagram = ClassDiagramFactory.eINSTANCE.createClassDiagram();
	    RequirementModel remodel = (RequirementModel) source.getContents().get(0);
	    DomainModel domainmodel = remodel.getDomainModel();

	    for (Entity entity : domainmodel.getEntity()) {
	    	com.rm2pt.rapidood.cd.classDiagram.Class cls = ClassDiagramFactory.eINSTANCE.createClass();
	    	cls.setName(entity.getName());
	    	for (net.mydreamy.requirementmodel.rEMODEL.Attribute attribute : entity.getAttributes()) {
	    		com.rm2pt.rapidood.cd.classDiagram.Attribute attr = ClassDiagramFactory.eINSTANCE.createAttribute();
	    		attr.setName(attribute.getName());
	    		com.rm2pt.rapidood.cd.classDiagram.Type type = ClassDiagramFactory.eINSTANCE.createPrimitiveType();
	    		type.setName("void");
	    		attr.setType(type);
	    		cls.getAttributes().add(attr);
	    	}
	    	classDiagram.getElements().add(cls);
	    }
	    target.getContents().add(classDiagram);
	    
	    try {
	      target.save(Collections.emptyMap());
	      System.out.println("Resource saved successfully.");
	    } catch (IOException e) {
            System.err.println("Failed to save resource: " + e.getMessage());
            throw Exceptions.sneakyThrow(e);
        }
	}
}
