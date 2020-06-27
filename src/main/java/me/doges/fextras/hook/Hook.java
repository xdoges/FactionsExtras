package me.doges.fextras.hook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.doges.fextras.hook.impl.FactionsHook;

@AllArgsConstructor
@Getter
public enum Hook {

    FACTIONS("Factions", FactionsHook.class);

    private final String pluginName;
    private final Class<?> clazz;
}
