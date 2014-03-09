package net.acomputerdog.MCInject.transformations.method;

import javassist.*;

/**
 * A transformation that inserts code at the specified line.
 */
public class MethodSourceInsertAtLineTransformation extends MethodTransformation {
    private final String name;
    private final String desc;
    private final String code;
    private final int line;

    public MethodSourceInsertAtLineTransformation(boolean constructor, String name, String desc, String code, int line) {
        super(constructor);
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
            CtBehavior behavior;
            if (isConstructor()) {
                behavior = cls.getConstructor(getDesc());
            } else {
                behavior = cls.getMethod(getName(), getDesc());
            }
            behavior.insertAt(getLine(), getCode());
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
