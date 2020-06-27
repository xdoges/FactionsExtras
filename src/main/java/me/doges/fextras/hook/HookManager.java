package me.doges.fextras.hook;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.hook.impl.FactionsHook;
import me.doges.fextras.manager.Manager;
import me.doges.fextras.manager.ManagerHandler;
import org.bukkit.plugin.PluginManager;

import java.util.HashMap;
import java.util.Map;

public class HookManager extends Manager {

    private final FactionsExtras p;

    private final Map<Class<?>, Hook> hooks = new HashMap<>();

    private FactionsHook factionsHook;

    public HookManager(ManagerHandler managerHandler) {
        super(managerHandler);
        p = managerHandler.getPlugin();

        factionsHook = new FactionsHook();
    }

    public void init() {
        PluginManager pm = p.getServer().getPluginManager();

        for (Hook hook : Hook.values()) {
            try {
                if (pm.isPluginEnabled(hook.getPluginName())) {
                    hooks.put(hook.getClazz(), hook);
                    p.getLogger().info("Hooked with " + hook.getPluginName() + "!");
                }
            } catch (Exception ex) {
                p.getLogger().severe("Exception thrown whilst hooking with " + hook.getPluginName());
                ex.printStackTrace();
            }
        }
    }

    public FactionsHook getFactionsHook() {
        if (!isHooked(FactionsHook.class)) {
            p.getLogger().info("This feature can not be used because we could not hook into Factions.");
        }
        return factionsHook;
    }

    public boolean isHooked(Class<?> clazz) {
        return hooks.containsKey(clazz);
    }
}
