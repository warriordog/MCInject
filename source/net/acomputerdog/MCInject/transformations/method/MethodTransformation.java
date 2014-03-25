package net.acomputerdog.MCInject.transformations.method;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;
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

    protected CtBehavior getMethod(CtClass cls, String name, String desc) throws NotFoundException {
        if (isConstructor) {
            return cls.getConstructor(desc);
        } else {
            return cls.getMethod(name, desc);
        }
    }

}
