package me.doges.fextras.settings;

import lombok.Data;
import lombok.Getter;
import me.doges.fextras.FactionsExtras;
import me.doges.fextras.settings.impl.*;

@Data
public final class Settings implements ISettings {

    private final FactionsExtras plugin;

    @Getter private final ChunkbusterSettings chunkbusterSettings;
    @Getter private final CombatTagSettings combatTagSettings;
    @Getter private final CooldownSettings cooldownSettings;
    @Getter private final MessageSettings messages;
    @Getter private final MiscSettings miscSettings;
    @Getter private final ModmodeSettings modmodeSettings;


    public Settings(FactionsExtras plugin) {
        this.plugin = plugin;

        this.chunkbusterSettings = new ChunkbusterSettings(plugin);
        this.combatTagSettings = new CombatTagSettings(plugin);
        this.cooldownSettings = new CooldownSettings(plugin);
        this.messages = new MessageSettings(plugin);
        this.miscSettings = new MiscSettings(plugin);
        this.modmodeSettings = new ModmodeSettings(plugin);
    }

    @Override
    public void load() {
        plugin.reloadConfig();

        chunkbusterSettings.load();
        combatTagSettings.load();
        cooldownSettings.load();
        messages.load();
        miscSettings.load();
        modmodeSettings.load();
    }
}
