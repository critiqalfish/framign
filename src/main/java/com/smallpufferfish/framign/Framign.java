package com.smallpufferfish.framign;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(   modid = Framign.MODID,
        name = Framign.MODID,
        clientSideOnly = true,
        version = Framign.VERSION)
public class Framign
{
    public static final String MODID = "framign";
    public static final String VERSION = "0.2";
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

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        MovingObjectPosition target = mc.objectMouseOver;
        if (target != null && target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            if (mc.theWorld == null) return;
            IBlockState blockState = mc.theWorld.getBlockState(target.getBlockPos());
            try {
                CropMode newMode = CropMode.valueOf(blockState.getBlock().getUnlocalizedName().substring("tile.".length()).toUpperCase());
                if (newMode != Framign.mode) {
                    Framign.mode = newMode;
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("selected crop " + Framign.mode));
                }
            } catch (IllegalArgumentException ignored) {}
        }
    }
}
