package com.smallpufferfish.framign;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class KeyReactorTest {
    public static void melonFarmer() {
        if(handleKey(KeyBinds.rightKeybind,
                Minecraft.getMinecraft().gameSettings.keyBindRight,
                Minecraft.getMinecraft().gameSettings.keyBindForward,
                Minecraft.getMinecraft().gameSettings.keyBindAttack)) return;

        if(handleKey(KeyBinds.leftKeybind,
                Minecraft.getMinecraft().gameSettings.keyBindLeft,
                Minecraft.getMinecraft().gameSettings.keyBindForward,
                Minecraft.getMinecraft().gameSettings.keyBindAttack)) return;
    }

    public static void pumpkinFarmer() {
        melonFarmer();
    }

    public static void cactusFarmer() {
        handleKey(KeyBinds.rightKeybind,
                Minecraft.getMinecraft().gameSettings.keyBindRight,
                Minecraft.getMinecraft().gameSettings.keyBindAttack);

        handleKey(KeyBinds.leftKeybind,
                Minecraft.getMinecraft().gameSettings.keyBindLeft,
                Minecraft.getMinecraft().gameSettings.keyBindAttack);

        handleKey(KeyBinds.miscKeybind,
                Minecraft.getMinecraft().gameSettings.keyBindForward);
    }

    private static boolean handleKey(KeyBinding keyBind, KeyBinding... keyBindingsToUpdate) {
        System.out.println(keyBind.getKeyDescription() + ", " + keyBind.isKeyDown() + ", " + Framign.farming);
        if (keyBind.isKeyDown()) {
            if (!Framign.farming) {
                Framign.farming = true;
                for (KeyBinding kb : keyBindingsToUpdate) {
                    KeyBinding.setKeyBindState(kb.getKeyCode(), true);
                }
                return true;
            }
            return true;
        }
        else if (!keyBind.isKeyDown()) {
            if (Framign.farming) {
                Framign.farming = false;
                for (KeyBinding kb : keyBindingsToUpdate) {
                    KeyBinding.setKeyBindState(kb.getKeyCode(), false);
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
