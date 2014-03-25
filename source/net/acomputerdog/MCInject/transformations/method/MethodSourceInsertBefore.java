package net.acomputerdog.MCInject.transformations.method;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 * A transformation that inserts code at the beginning of a method
 */
public class MethodSourceInsertBefore extends MethodTransformation {
    private final String name;
    private final String desc;
    private final String code;

    public MethodSourceInsertBefore(String name, String desc, String code) {
        super(name.equalsIgnoreCase("<init>"));
        this.name = name;
        this.desc = desc;
        this.code = code;
    }

    /**
     * Applies the transformation to the passed class.
     *
     * @param cls The class to transform.
     * @return Return false if the class could not be transformed, true otherwise.
     */
    @Override
    public boolean apply(CtClass cls) {
        try {
            getMethod(cls, getName(), getDesc()).insertBefore(getCode());
            return true;
        } catch (NotFoundException e) {
            return false;
        } catch (CannotCompileException e2) {
            throw new IllegalArgumentException("Illegal injection code!", e2);
        }
    }

    protected String getName() {
        return name;
    }

    protected String getDesc() {
        return desc;
    }

    protected String getCode() {
        return code;
    }
}
