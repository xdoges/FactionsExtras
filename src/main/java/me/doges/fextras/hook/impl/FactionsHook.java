package me.doges.fextras.hook.impl;

import me.markeh.factionsframework.FactionsFramework;
import me.markeh.factionsframework.entities.FPlayer;
import me.markeh.factionsframework.entities.FPlayers;
import me.markeh.factionsframework.entities.Faction;
import org.bukkit.entity.Player;

public class FactionsHook {

    public FactionsHook() {
        FactionsFramework.get();
    }

    public Faction getFactionFromPlayer(Player player) {
        FPlayer fPlayer = FPlayers.getBySender(player);
        return fPlayer.getFaction();
    }
}
