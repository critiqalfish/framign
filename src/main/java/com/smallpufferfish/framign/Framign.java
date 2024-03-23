package com.smallpufferfish.framign;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(   modid = Framign.MODID,
        clientSideOnly = true,
        version = Framign.VERSION)
public class Framign
{
    public static final String MODID = "framign";
    public static final String VERSION = "0.1";
    public static CropMode mode = CropMode.NONE;
    public static boolean goingRight = false;
    public static boolean goingLeft = false;
    public static boolean goingForward = false;
    public static boolean farming = false;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        KeyBinds.registerKeybinds();
        ClientCommandHandler.instance.registerCommand(new FCommand());
        System.out.println("--- framign by smallpufferfish was loaded! ---");
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (player == null) return;

        if (KeyBinds.homeKeybind.isPressed()) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/warp garden");
            return;
        }

        if (Framign.mode == null) return;
        switch (Framign.mode) {
            case MELON:
                KeyReactor.melonFarmer();
            case PUMPKIN:
                KeyReactor.pumpkinFarmer();
                break;
            case CACTUS:
                KeyReactor.cactusFarmer();
                break;
            default:
                break;
        }
    }
}
