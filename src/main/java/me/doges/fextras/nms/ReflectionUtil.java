package me.doges.fextras.nms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class ReflectionUtil {
    private static String version;

    public static int getPlayerPing(Player player) {
        try {
            Class<?> nmsPlayer = Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".entity.CraftPlayer");
            Object handle = nmsPlayer.getMethod("getHandle").invoke(player);

            Integer ping = (Integer) handle.getClass().getDeclaredField("ping").get(handle);
            return ping.intValue();
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return -1;
        }
    }

    public static String getServerVersion() {
        if (version == null) {
            String name = Bukkit.getServer().getClass().getName();
            String[] parts = name.split("\\.");
            version = parts[3];
        }
        return version;
    }
}
