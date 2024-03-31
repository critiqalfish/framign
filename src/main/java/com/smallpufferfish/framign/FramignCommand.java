package com.smallpufferfish.framign;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.Collections;
import java.util.List;

public class FramignCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "framign";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }

    private static final String[] SUBCOMMANDS = Utils.getNames(CropMode.class);

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return CommandBase.getListOfStringsMatchingLastWord(args, SUBCOMMANDS);
        }
        return Collections.emptyList();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("framign " + Framign.VERSION));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("selected crop " + Framign.mode));
            return;
        }

        try {
            Framign.mode = CropMode.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException ex) {
            return;
        }

        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("selected crop " + Framign.mode));
    }
}
