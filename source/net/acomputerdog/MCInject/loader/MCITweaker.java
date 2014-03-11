package net.acomputerdog.MCInject.loader;

import net.acomputerdog.MCInject.inject.Injector;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.List;

public class MCITweaker implements ITweaker {
    private final ITweaker parent;

    public MCITweaker(ITweaker parent, List<Injector> injectors) {
        MCITransformer.injectors.addAll(injectors);
        this.parent = parent;
    }

    @Override
    public void acceptOptions(List<String> strings, File file, File file2, String s) {
        parent.acceptOptions(strings, file, file2, s);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader launchClassLoader) {
        parent.injectIntoClassLoader(launchClassLoader);
        launchClassLoader.registerTransformer(MCITransformer.class.getName());
    }

    @Override
    public String getLaunchTarget() {
        return parent.getLaunchTarget();
    }

    @Override
    public String[] getLaunchArguments() {
        return parent.getLaunchArguments();
    }
}
