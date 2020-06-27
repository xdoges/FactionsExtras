package me.doges.fextras.module.cooldowns;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.module.Module;
import me.doges.fextras.settings.impl.CooldownSettings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class GappleCooldown extends Module {

    private final CooldownSettings settings;

    public GappleCooldown(FactionsExtras plugin) {
        super(plugin, "gapple-cooldown");
        this.settings = plugin.getSettings().getCooldownSettings();

        this.goldenAppleCooldownTime = settings.getGoldenAppleCooldownTime();
        this.notchAppleCooldownTime = settings.getNotchAppleCooldownTime();
    }

    private HashMap<UUID, Long> goldenAppleCooldown = new HashMap<>();
    private HashMap<UUID, Long> notchAppleCooldown = new HashMap<>();

    private int goldenAppleCooldownTime;
    private int notchAppleCooldownTime;

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        if (event.getItem().isSimilar(getGoldenApple())) {
            if (goldenAppleCooldown.containsKey(player.getUniqueId()) && goldenAppleCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                event.setCancelled(true);

                long remainingTime = goldenAppleCooldown.get(player.getUniqueId()) - System.currentTimeMillis();
                String message = settings.getGoldenAppleCooldownMessage(remainingTime);

                player.sendMessage(message);
            } else {
                goldenAppleCooldown.put(player.getUniqueId(), System.currentTimeMillis() + (goldenAppleCooldownTime * 1000));
            }
        } else if (event.getItem().isSimilar(getNotchApple())) {
            if (notchAppleCooldown.containsKey(player.getUniqueId()) && notchAppleCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                event.setCancelled(true);

                long remainingTime = notchAppleCooldown.get(player.getUniqueId()) - System.currentTimeMillis();
                String message = settings.getNotchAppleCooldownMessage(remainingTime);

                player.sendMessage(message);
            } else {
                notchAppleCooldown.put(player.getUniqueId(), System.currentTimeMillis() + (notchAppleCooldownTime * 1000));
            }
        }
    }

    private ItemStack getGoldenApple() {
        return new ItemStack(Material.GOLDEN_APPLE, 1, (short) 0);
    }

    private ItemStack getNotchApple() {
        return new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);
    }
}
