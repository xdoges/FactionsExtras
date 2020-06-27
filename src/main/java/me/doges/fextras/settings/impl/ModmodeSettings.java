package me.doges.fextras.settings.impl;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.settings.ISettings;

public class ModmodeSettings implements ISettings {

    public final String PREFIX = "mod-mode-";

    private final FactionsExtras plugin;

    public ModmodeSettings(FactionsExtras plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {

    }
}
