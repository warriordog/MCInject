package net.acomputerdog.MCInject.loader;

import javassist.ClassPool;
import javassist.CtClass;
import net.acomputerdog.MCInject.inject.Injector;
import sun.misc.URLClassPath;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple ClassLoader that passes all loaded classes to it's injectors.
 */
public class MCIClassloader extends ClassLoader {
    private final List<Injector> injectors = new ArrayList<Injector>();
    private final URLClassPath ucp;

    public MCIClassloader(URL[] urls, List<Injector> injectors) {
        super();
        this.injectors.addAll(injectors);
        ucp = new URLClassPath(urls);
    }

    public MCIClassloader(ClassLoader parent, URL[] urls, List<Injector> injectors) {
        super(parent);
        this.injectors.addAll(injectors);
        ucp = new URLClassPath(urls);
    }

    public void addURL(URL url) {
        ucp.addURL(url);
    }

    public void addInjector(Injector injector) {
        injectors.add(injector);
    }

    public void addURLs(URL[] urls) {
        for (URL url : urls) {
            ucp.addURL(url);
        }
    }

    public void addInjectors(List<Injector> injectors) {
        injectors.addAll(injectors);
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            CtClass cls = ClassPool.getDefault().makeClass(ucp.getResource(name).getInputStream());
            for (Injector injector : injectors) {
                injector.injectIntoClass(cls);
            }
            return cls.toClass();
        } catch (Exception ignored) {
        }
        return super.loadClass(name, resolve);
    }
}
