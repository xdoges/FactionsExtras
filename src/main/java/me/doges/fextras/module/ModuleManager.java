package me.doges.fextras.module;

import lombok.Getter;
import me.doges.fextras.manager.Manager;
import me.doges.fextras.manager.ManagerHandler;
import me.doges.fextras.module.combattag.CombatTag;
import me.doges.fextras.module.cooldowns.EnderpearlCooldown;
import me.doges.fextras.module.cooldowns.GappleCooldown;
import me.doges.fextras.module.jellylegs.JellyLegs;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager extends Manager {

    @Getter public List<Module> modules = new ArrayList<>();

    public ModuleManager(ManagerHandler managerHandler) {
        super(managerHandler);

        addModule(new CombatTag(managerHandler.getPlugin()));
        addModule(new EnderpearlCooldown(managerHandler.getPlugin()));
        addModule(new GappleCooldown(managerHandler.getPlugin()));
        addModule(new JellyLegs(managerHandler.getPlugin()));
    }

    public void registerModules() {
        for (Module m : modules) {
            managerHandler.getPlugin().getServer().getPluginManager().registerEvents(m, managerHandler.getPlugin());
        }
    }

    public void addModule(Module module) {
        if (isEnabled(module.getModuleName())) {
            modules.add(module);
        }
    }

    public Module getModuleFromName(String name) {
        for (Module m : modules) {
            if (m.getModuleName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    public boolean isEnabled(String moduleName) {
        return managerHandler.getPlugin().c().isModuleEnabled(moduleName);
    }
}
