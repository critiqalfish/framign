package com.smallpufferfish.framign;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Mod(   modid = Framign.MODID,
        name = Framign.MODID,
        clientSideOnly = true,
        version = Framign.VERSION)
public class Framign
{
    public static final String MODID = "framign";
    public static final String VERSION = "0.3";
    public static CropMode mode = CropMode.NONE;
    public static boolean goingRight = false;
    public static boolean goingLeft = false;
    public static boolean goingForward = false;
    public static float sensitivity;

    public static boolean DEBUG = true;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        KeyBinds.registerKeybinds();
        ClientCommandHandler.instance.registerCommand(new FramignCommand());
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

        if (!DEBUG) {
            if (!Framign.isInGarden()) return;
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
        if (event.phase != TickEvent.Phase.END) return;

        if (!DEBUG) {
            if (!Framign.isInGarden()) return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        MovingObjectPosition target = mc.objectMouseOver;

        // test if this gets banned first
        /*if (mc.gameSettings.keyBindAttack.isKeyDown()) {
            if (mc.thePlayer != null && mc.thePlayer.isSwingInProgress == false) {
                if (target != null && target.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                    mc.thePlayer.swingItem();
                    mc.playerController.attackEntity(mc.thePlayer, target.entityHit);
                }
            }
        }*/

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

        if (mc.gameSettings.mouseSensitivity != -1F/3F) {
            Framign.sensitivity = mc.gameSettings.mouseSensitivity;
        }

        if (goingRight || goingLeft || goingForward) {
            mc.gameSettings.mouseSensitivity = -1F/3F;
        } else {
            mc.gameSettings.mouseSensitivity = Framign.sensitivity;
        }
    }

    public static boolean isInGarden() {
        List<String> lines = Framign.getScoreboardLines();
        for (String line : lines) {
            if (line.contains("The Garde\ud83c\udf20n") || line.contains("The Garden") || line.contains("Plot -")) return true;
        }
        return false;
    }

    public static List<String> getScoreboardLines() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.theWorld == null) return Collections.emptyList();
        Scoreboard sb = mc.theWorld.getScoreboard();
        if (sb == null) return Collections.emptyList();
        ScoreObjective obj = sb.getObjectiveInDisplaySlot(1);
        Collection<Score> scores = sb.getSortedScores(obj);
        List<String> list = new ArrayList<>();
        for (Score line : scores) {
            ScorePlayerTeam team = sb.getPlayersTeam(line.getPlayerName());
            Pattern colorStripper = Pattern.compile("(?i)§[0-9A-FK-ORZ]");
            String cleansed1 = colorStripper.matcher(ScorePlayerTeam.formatPlayerName(team, line.getPlayerName()).trim()).replaceAll("");
            list.add(cleansed1);
        }
        Collections.reverse(list);
        return list;
    }
}
