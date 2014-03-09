package net.acomputerdog.MCInject.transformations;

/**
 * Represents a class transformation.
 */
public abstract class Transformation {

    /**
     * Gets the class component that this transformation applies to.
     * @return Return the class component that this transformation applies to.
     */
    public abstract TransformationTarget getTargetType();

    /**
     * Applies the transformation to the passed class.
     * @param className The name of the class to apply to.
     * @param bytecode The code of the class
     * @return Return false if the class could not be transformed, true otherwise.
     */
    public abstract boolean apply(String className, byte[] bytecode);
}
