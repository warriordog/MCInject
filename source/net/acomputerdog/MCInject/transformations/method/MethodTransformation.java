package net.acomputerdog.MCInject.transformations.method;

import net.acomputerdog.MCInject.component.TargetType;
import net.acomputerdog.MCInject.transformations.Transformation;

/**
 * A transformation on a method.
 */
public abstract class MethodTransformation extends Transformation {
    private boolean isConstructor;

    protected MethodTransformation(boolean isConstructor) {
        this.isConstructor = isConstructor;
    }

    protected boolean isConstructor() {
        return isConstructor;
    }

    /**
     * Gets the class component that this transformation applies to.
     *
     * @return Return the class component that this transformation applies to.
     */
    @Override
    public final TargetType getTargetType() {
        return isConstructor ? TargetType.CONSTRUCTOR : TargetType.METHOD;
    }

}
