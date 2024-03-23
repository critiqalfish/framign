package com.smallpufferfish.framign;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class KeyReactor {
    public static void melonFarmer() {
        if (KeyBinds.rightKeybind.isKeyDown()) {
            if (!Framign.goingRight) {
                Framign.goingRight = true;
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode(), true);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode(), true);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), true);
            }
        } else {
            if (Framign.goingRight) {
                Framign.goingRight = false;
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode(), false);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode(), false);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), false);
            }
        }

        if (KeyBinds.leftKeybind.isKeyDown()) {
            if (!Framign.goingLeft) {
                Framign.goingLeft = true;
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode(), true);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode(), true);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), true);
            }
        } else {
            if (Framign.goingLeft) {
                Framign.goingLeft = false;
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode(), false);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode(), false);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), false);
            }
        }
    }

    public static void pumpkinFarmer() {
        melonFarmer();
    }

    public static void cactusFarmer() {
        if (KeyBinds.rightKeybind.isKeyDown()) {
            if (!Framign.goingRight) {
                Framign.goingRight = true;
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode(), true);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), true);
            }
        } else {
            if (Framign.goingRight) {
                Framign.goingRight = false;
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode(), false);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), false);
            }
        }

        if (KeyBinds.leftKeybind.isKeyDown()) {
            if (!Framign.goingLeft) {
                Framign.goingLeft = true;
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode(), true);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), true);
            }
        } else {
            if (Framign.goingLeft) {
                Framign.goingLeft = false;
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode(), false);
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), false);
            }
        }

        if (KeyBinds.miscKeybind.isKeyDown()) {
            if (!Framign.goingForward) {
                Framign.goingForward = true;
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode(), true);
            }
        } else {
            if (Framign.goingForward) {
                Framign.goingForward = false;
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode(), false);
            }
        }
    }
}
