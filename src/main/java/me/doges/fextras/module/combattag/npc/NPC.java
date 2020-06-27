package me.doges.fextras.module.combattag.npc;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NPC {

    @Getter private UUID logger;
    @Getter private Zombie entity;
    @Getter private List<ItemStack> inventoryContents;

    public NPC(Player player) {
        this.logger = player.getUniqueId();
        this.entity = getZombie(player);
        this.inventoryContents = getContents(player.getInventory());
    }

    public boolean isNPC(Zombie zombie) {
        return entity.getUniqueId().equals(zombie.getUniqueId());
    }

    private List<ItemStack> getContents(PlayerInventory inventory) {
        List<ItemStack> output = new ArrayList<>();

        for (ItemStack i : inventory.getContents()) {
            if (i != null) {
                output.add(i);
            }
        }

        for (ItemStack i : inventory.getArmorContents()) {
            if (i != null) {
                output.add(i);
            }
        }
        return output;
    }

    private Zombie getZombie(Player player) {
        Zombie zombie = player.getWorld().spawn(player.getLocation(), Zombie.class);

        zombie.setCustomName(player.getName());
        zombie.setCustomNameVisible(true);
        zombie.setRemoveWhenFarAway(true);
        zombie.getEquipment().setArmorContents(player.getInventory().getArmorContents());
        zombie.getEquipment().setHelmet(getPlayerSkull(player));
        zombie.setBaby(false);
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 100));
        return zombie;
    }

    private ItemStack getPlayerSkull(Player player) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();

        meta.setOwner(player.getName());
        skull.setItemMeta(meta);
        return skull;
    }
}
