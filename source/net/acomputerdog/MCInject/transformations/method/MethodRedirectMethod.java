package net.acomputerdog.MCInject.transformations.method;

import javassist.*;

/**
 * Transformation that makes a method redirect to another method
 */
public class MethodRedirectMethod extends MethodTransformation {
    private final String targetName;
    private final String targetDesc;
    private final String source;
    private final String sourceName;
    private final String sourceDesc;

    public MethodRedirectMethod(String targetName, String targetDesc, String source, String sourceName, String sourceDesc) {
        super(targetName.equalsIgnoreCase("<init>"));
        if (isConstructor()) {
            throw new IllegalArgumentException("Cannot redirect a constructor!");
        }
        this.targetName = targetName;
        this.targetDesc = targetDesc;
        this.source = source;
        this.sourceName = sourceName;
        this.sourceDesc = sourceDesc;
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
            CtMethod target = cls.getMethod(getTargetName(), getTargetDesc());
            CtClass sourceClass = ClassPool.getDefault().get(getSource());
            CtMethod source = sourceClass.getMethod(getSourceName(), getSourceDesc());
            if (source.getParameterTypes().length > 0) {
                throw new IllegalArgumentException("Replace method cannot have parameters!");
            }
            if (source.getReturnType() != null && !source.getReturnType().equals(target.getReturnType())) {
                throw new IllegalArgumentException("Target method and source method have incompatible return types!");
            }
            if (!source.visibleFrom(cls)) {
                throw new IllegalArgumentException("Replace method cannot be accessed by source method!");
            }
            if (!Modifier.isStatic(source.getModifiers())) {
                throw new IllegalArgumentException("Replace method is not static!");
            }
            String callCmd = sourceClass.getName() + "." + source.getName() + "();";
            if (target.getReturnType() == null) {
                source.setBody(callCmd + "return;");
            } else {
                source.setBody("return " + callCmd);
            }
            return true;
        } catch (NotFoundException e) {
            return false;
        } catch (CannotCompileException e2) {
            throw new IllegalArgumentException("Illegal injection code!", e2);
        }
    }

    protected String getTargetName() {
        return targetName;
    }

    protected String getTargetDesc() {
        return targetDesc;
    }

    protected String getSource() {
        return source;
    }

    protected String getSourceName() {
        return sourceName;
    }

    protected String getSourceDesc() {
        return sourceDesc;
    }
}
