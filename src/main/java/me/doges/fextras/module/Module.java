package me.doges.fextras.module;

import lombok.Getter;
import me.doges.fextras.FactionsExtras;
import org.bukkit.event.Listener;

public abstract class Module implements Listener {

    protected final FactionsExtras plugin;

    @Getter private String moduleName;

    protected Module(FactionsExtras plugin, String moduleName) {
        this.plugin = plugin;
        this.moduleName = moduleName;
    }
}
