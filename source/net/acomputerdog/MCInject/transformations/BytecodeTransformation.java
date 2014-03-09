package net.acomputerdog.MCInject.transformations;

import javassist.CtClass;

public class BytecodeTransformation extends AbsoluteTransformation {
    private final TargetType targetType;
    private final String itemName;

    public BytecodeTransformation(TargetType target, String name, byte[] code) {
        this.targetType = target;
        this.itemName = name;
    }

    /**
     * Gets the class component that this transformation applies to.
     *
     * @return Return the class component that this transformation applies to.
     */
    @Override
    public TargetType getTargetType() {
        return targetType;
    }

    /**
     * Applies the transformation to the passed class.
     *
     * @param cls The class to transform.
     * @return Return false if the class could not be transformed, true otherwise.
     */
    @Override
    public boolean apply(CtClass cls) {
        switch (targetType) {
            case METHOD: {

            }

            case FIELD: {

            }

            case CONSTRUCTOR: {

            }

            default: throw new IllegalArgumentException("An unknown TargetType was passed!");
        }
    }
}
