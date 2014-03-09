package net.acomputerdog.MCInject.transformations.constructor;

import net.acomputerdog.MCInject.component.TargetType;
import net.acomputerdog.MCInject.transformations.AbstractTransformation;

/**
 * A transformation on a constructor
 */
public abstract class ConstructorTransformation extends AbstractTransformation {
    /**
     * Gets the class component that this transformation applies to.
     *
     * @return Return the class component that this transformation applies to.
     */
    @Override
    public final TargetType getTargetType() {
        return TargetType.CONSTRUCTOR;
    }
}
