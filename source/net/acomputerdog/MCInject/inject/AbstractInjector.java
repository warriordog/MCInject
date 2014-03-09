package net.acomputerdog.MCInject.inject;

import javassist.CtClass;
import net.acomputerdog.MCInject.transformations.AbstractTransformation;

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
    protected Map<String, List<AbstractTransformation>> registeredTransformations = new HashMap<String, List<AbstractTransformation>>();

    /**
     * Attempts to inject any registered transformations into the passed class.
     * @param cls The class to inject.
     * @return Return true if the class was modified, false otherwise.
     */
    public boolean injectIntoClass(CtClass cls) {
        List<AbstractTransformation> transformations = registeredTransformations.get(cls.getName());
        if (transformations == null || transformations.size() == 0) {
            return false;
        }
        for (AbstractTransformation transformation : transformations) {
            transformation.apply(cls);
        }
        return true;
    }
}
