package me.doges.fextras.manager;

import lombok.Getter;
import me.doges.fextras.FactionsExtras;
import me.doges.fextras.hook.HookManager;
import me.doges.fextras.listener.ListenerManager;
import me.doges.fextras.module.combattag.managers.NPCManager;
import me.doges.fextras.module.combattag.managers.TagManager;
import me.doges.fextras.module.ModuleManager;

public class ManagerHandler {

    @Getter private final FactionsExtras plugin;

    @Getter private ModuleManager moduleManager;
    @Getter private HookManager hookManager;
    @Getter private ListenerManager listenerManager;

    @Getter private TagManager tagManager;
    @Getter private NPCManager npcManager;

    public ManagerHandler(FactionsExtras plugin) {
        this.plugin = plugin;
    }

    public void load() {
        hookManager = new HookManager(this);
        hookManager.init();

        tagManager = new TagManager(this);
        npcManager = new NPCManager(this);
        moduleManager = new ModuleManager(this);
        listenerManager = new ListenerManager(this);

        moduleManager.registerModules();
        listenerManager.registerListeners();
    }
}
