package com.smallpufferfish.framign;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBinds {
    public static KeyBinding rightKeybind;
    public static KeyBinding leftKeybind;
    public static KeyBinding miscKeybind;
    public static KeyBinding homeKeybind;

    public static void registerKeybinds() {
        rightKeybind = new KeyBinding("right", Keyboard.KEY_RIGHT, "framign");
        leftKeybind = new KeyBinding("left", Keyboard.KEY_LEFT, "framign");
        miscKeybind = new KeyBinding("misc", Keyboard.KEY_UP, "framign");
        homeKeybind = new KeyBinding("home", Keyboard.KEY_DOWN, "framign");
        ClientRegistry.registerKeyBinding(rightKeybind);
        ClientRegistry.registerKeyBinding(leftKeybind);
        ClientRegistry.registerKeyBinding(miscKeybind);
        ClientRegistry.registerKeyBinding(homeKeybind);
    }
}
