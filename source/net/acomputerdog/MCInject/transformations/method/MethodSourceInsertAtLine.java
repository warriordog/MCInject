package net.acomputerdog.MCInject.transformations.method;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 * A transformation that inserts code at the specified line.
 */
public class MethodSourceInsertAtLine extends MethodTransformation {
    private final String name;
    private final String desc;
    private final String code;
    private final int line;

    public MethodSourceInsertAtLine(String name, String desc, String code, int line) {
        super(name.equalsIgnoreCase("<init>"));
        this.name = name;
        this.desc = desc;
        this.code = code;
        this.line = line;
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
            getMethod(cls, getName(), getDesc()).insertAt(getLine(), getCode());
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

    protected int getLine() {
        return line;
    }
}
