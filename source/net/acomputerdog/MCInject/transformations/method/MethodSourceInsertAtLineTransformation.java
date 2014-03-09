package net.acomputerdog.MCInject.transformations.method;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * A transformation that inserts code at the specified line.
 */
public class MethodSourceInsertAtLineTransformation extends MethodTransformation {
    private final String name;
    private final String desc;
    private final String code;
    private final int line;

    public MethodSourceInsertAtLineTransformation(String name, String desc, String code, int line) {
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
            CtMethod m = cls.getMethod(getName(), getDesc());
            m.insertAt(getLine(), getCode());
            m.insertBefore(getCode());
            return true;
        } catch (NotFoundException e) {
            return false;
        } catch (CannotCompileException e2) {
            throw new RuntimeException("Illegal injection code!", e2);
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
