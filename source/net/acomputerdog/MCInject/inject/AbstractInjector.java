package net.acomputerdog.MCInject.inject;

import net.acomputerdog.MCInject.transformations.Transformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract superclass for code injectors.  Subclasses should accept a class through injectIntoClass() and edit it how the Injector is configured.
 */
public abstract class AbstractInjector {
    /**
     * A map of class names to transformations to apply to them.  Should be populated by subclasses.
     */
    protected Map<String, List<Transformation>> registeredTransformations = new HashMap<String, List<Transformation>>();

    /**
     * Attempts to inject any registered transformations into the passed class name and bytecode.
     * @param name The name of the class
     * @param bytecode The bytecode of the class
     * @return Return true if the class was modified, false otherwise.
     */
    public boolean injectIntoClass(String name, byte[] bytecode) {
        List<Transformation> transformations = registeredTransformations.get(name);
        if (transformations == null || transformations.size() == 0) {
            return false;
        }
        //TODO implement
        return true;
    }
}
