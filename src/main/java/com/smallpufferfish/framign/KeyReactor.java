package com.smallpufferfish.framign;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class KeyReactor {
    public static void melonFarmer() {
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;

        if (KeyBinds.rightKeybind.isKeyDown()) {
            if (!Framign.goingRight) {
                Framign.goingRight = true;
                doKeys(true, gameSettings.keyBindRight, gameSettings.keyBindForward, gameSettings.keyBindAttack);
            }
        } else {
            if (Framign.goingRight) {
                Framign.goingRight = false;
                doKeys(false, gameSettings.keyBindRight, gameSettings.keyBindForward, gameSettings.keyBindAttack);
            }
        }

        if (KeyBinds.leftKeybind.isKeyDown()) {
            if (!Framign.goingLeft) {
                Framign.goingLeft = true;
                doKeys(true, gameSettings.keyBindLeft, gameSettings.keyBindForward, gameSettings.keyBindAttack);
            }
        } else {
            if (Framign.goingLeft) {
                Framign.goingLeft = false;
                doKeys(false, gameSettings.keyBindLeft, gameSettings.keyBindForward, gameSettings.keyBindAttack);
            }
        }
    }

    public static void pumpkinFarmer() {
        melonFarmer();
    }

    public static void cactusFarmer() {
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;

        if (KeyBinds.rightKeybind.isKeyDown()) {
            if (!Framign.goingRight) {
                Framign.goingRight = true;
                doKeys(true, gameSettings.keyBindRight, gameSettings.keyBindAttack);
            }
        } else {
            if (Framign.goingRight) {
                Framign.goingRight = false;
                doKeys(false, gameSettings.keyBindRight, gameSettings.keyBindAttack);
            }
        }

        if (KeyBinds.leftKeybind.isKeyDown()) {
            if (!Framign.goingLeft) {
                Framign.goingLeft = true;
                doKeys(true, gameSettings.keyBindLeft, gameSettings.keyBindAttack);
            }
        } else {
            if (Framign.goingLeft) {
                Framign.goingLeft = false;
                doKeys(false, gameSettings.keyBindLeft, gameSettings.keyBindAttack);
            }
        }

        if (KeyBinds.miscKeybind.isKeyDown()) {
            if (!Framign.goingForward) {
                Framign.goingForward = true;
                doKeys(true, gameSettings.keyBindForward);
            }
        } else {
            if (Framign.goingForward) {
                Framign.goingForward = false;
                doKeys(false, gameSettings.keyBindForward);
            }
        }
    }

    public static void wheatFarmer() {
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;

        if (KeyBinds.rightKeybind.isKeyDown()) {
            if (!Framign.goingRight) {
                Framign.goingRight = true;
                doKeys(true, gameSettings.keyBindRight, gameSettings.keyBindAttack);
            }
        } else {
            if (Framign.goingRight) {
                Framign.goingRight = false;
                doKeys(false, gameSettings.keyBindRight, gameSettings.keyBindAttack);
            }
        }

        if (KeyBinds.leftKeybind.isKeyDown()) {
            if (!Framign.goingLeft) {
                Framign.goingLeft = true;
                doKeys(true, gameSettings.keyBindLeft, gameSettings.keyBindAttack);
            }
        } else {
            if (Framign.goingLeft) {
                Framign.goingLeft = false;
                doKeys(false, gameSettings.keyBindLeft, gameSettings.keyBindAttack);
            }
        }
    }

    public static void potatoFarmer() {
        wheatFarmer();
    }

    public static void carrotFarmer() {
        wheatFarmer();
    }

    private static void doKeys(boolean pressed, KeyBinding... keys) {
        for (KeyBinding key : keys) {
            KeyBinding.setKeyBindState(key.getKeyCode(), pressed);
        }
    }
}
