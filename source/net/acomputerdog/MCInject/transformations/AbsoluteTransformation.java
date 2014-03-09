package net.acomputerdog.MCInject.transformations;

import javassist.CtClass;

/**
 * Represents a class transformation.
 */
public abstract class AbsoluteTransformation {

    /**
     * Gets the class component that this transformation applies to.
     * @return Return the class component that this transformation applies to.
     */
    public abstract TargetType getTargetType();

    /**
     * Applies the transformation to the passed class.
     * @param cls The class to transform.
     * @return Return false if the class could not be transformed, true otherwise.
     */
    public abstract boolean apply(CtClass cls);
}
