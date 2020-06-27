package me.doges.fextras.manager;

import lombok.Getter;
import me.doges.fextras.FactionsExtras;
import me.doges.fextras.listener.ListenerManager;
import me.doges.fextras.module.combattag.CombatTag;
import me.doges.fextras.module.combattag.managers.NPCManager;
import me.doges.fextras.module.combattag.managers.TagManager;
import me.doges.fextras.module.cooldowns.EnderpearlCooldown;
import me.doges.fextras.module.cooldowns.GappleCooldown;
import me.doges.fextras.module.ModuleManager;
import me.doges.fextras.module.jellylegs.JellyLegs;

public class ManagerHandler {

    @Getter private final FactionsExtras plugin;

    @Getter private ModuleManager moduleManager;
    @Getter private ListenerManager listenerManager;

    @Getter private TagManager tagManager;
    @Getter private NPCManager npcManager;

    public ManagerHandler(FactionsExtras plugin) {
        this.plugin = plugin;
    }

    public void load() {
        tagManager = new TagManager(this);
        npcManager = new NPCManager(this);
        moduleManager = new ModuleManager(this);
        listenerManager = new ListenerManager(this);

//        loadModules();
        moduleManager.registerModules();
        listenerManager.registerListeners();
    }

    private void loadModules() {
        moduleManager.addModule(new CombatTag(plugin));
        moduleManager.addModule(new EnderpearlCooldown(plugin));
        moduleManager.addModule(new GappleCooldown(plugin));
        moduleManager.addModule(new JellyLegs(plugin));
    }
}
