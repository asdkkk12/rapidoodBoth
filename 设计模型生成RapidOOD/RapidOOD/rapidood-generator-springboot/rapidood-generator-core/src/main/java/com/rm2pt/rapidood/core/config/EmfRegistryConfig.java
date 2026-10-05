package com.rm2pt.rapidood.core.config;

import com.rm2pt.rapidood.cd.ClassDiagramStandaloneSetup;
import net.mydreamy.requirementmodel.REMODELStandaloneSetup;

/**
 * Registers REMODEL and ClassDiagram Xtext/EMF factories once for headless use.
 */
public final class EmfRegistryConfig {

    private static volatile boolean initialized;

    private EmfRegistryConfig() {
    }

    public static synchronized void initialize() {
        if (initialized) {
            return;
        }
        new REMODELStandaloneSetup().createInjectorAndDoEMFRegistration();
        new ClassDiagramStandaloneSetup().createInjectorAndDoEMFRegistration();
        initialized = true;
    }
}
