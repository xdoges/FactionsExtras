package me.doges.fextras.listener.listeners;

import me.doges.fextras.FactionsExtras;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CraftListener implements Listener {

    private List<Material> DISABLED_ITEMS = new ArrayList<>();

    private final FactionsExtras plugin;

    public CraftListener(FactionsExtras plugin) {
        this.plugin = plugin;

        DISABLED_ITEMS.addAll(plugin.getConfig().getStringList(  "crafting.disabled")
                .stream()
                .map(disabled -> Material.valueOf(disabled.toUpperCase()))
                .collect(Collectors.toList()));
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        Material type = event.getRecipe().getResult().getType();

        if (getDisabledItems().contains(type)) {
            event.getInventory().setResult(null);
            for (HumanEntity player : event.getInventory().getViewers()) {
                player.sendMessage(plugin.getSettings().getMessages().cannotCraft(type));
            }
        }
    }

    public List<Material> getDisabledItems() {
        return DISABLED_ITEMS;
    }
}
