package net.acomputerdog.MCInject.loader;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import net.acomputerdog.MCInject.inject.Injector;
import net.minecraft.launchwrapper.IClassTransformer;

import java.util.ArrayList;
import java.util.List;

public class MCITransformer implements IClassTransformer {
    public static List<Injector> injectors = new ArrayList<Injector>();

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        try {
            ClassPool cp = ClassPool.getDefault();
            cp.insertClassPath(new ByteArrayClassPath(name, bytes));
            CtClass cls = cp.get(name);
            for (Injector injector : injectors) {
                injector.injectIntoClass(cls);
            }
            return cls.toBytecode();
        } catch (Exception e) {
            throw new RuntimeException("Unable to create class to transform!");
        }
    }
}
