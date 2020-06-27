package me.doges.fextras.module.cooldowns;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.module.Module;
import me.doges.fextras.settings.impl.CooldownSettings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

public class EnderpearlCooldown extends Module {

    private final CooldownSettings settings;

    public EnderpearlCooldown(FactionsExtras plugin) {
        super(plugin, "enderpearl-cooldown");
        settings = plugin.getSettings().getCooldownSettings();

        cooldownTime = settings.getEnderpearlCooldownTime();
    }

    private HashMap<UUID, Long> cooldown = new HashMap<>();
    private int cooldownTime;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType() == Material.ENDER_PEARL) {
                if (cooldown.containsKey(player.getUniqueId()) && cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    event.setCancelled(true);

                    long remainingTime = cooldown.get(player.getUniqueId()) - System.currentTimeMillis();
                    String message = settings.getEnderpearlCooldownMessage(remainingTime);

                    player.sendMessage(message);
                } else {
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis() + (cooldownTime * 1000));
                }
            }
        }
    }
}
