package com.timethor.terracontrol.bukkit.commands;

import com.timethor.terracontrol.bukkit.TerraPerm;
import com.timethor.terracontrol.bukkit.TerraPlugin;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends BaseCommand {

    public HelpCommand(TerraPlugin _plugin) {
        super(_plugin);
        name = "help";
        perm = TerraPerm.CMD_HELP.node;
        usage = "help";
        workOnConsole = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        List<String> lines = new ArrayList<>();
        for (BaseCommand command : plugin.commandExecutor.commandHashMap.values()) {
            lines.add(MESSAGE_COLOR + "/terra " + command.usage + " - " + command.getHelp());
        }

        int page = 1;
        if (args.size() > 0) {
            try {
                page = Integer.parseInt(args.get(0));
            } catch (NumberFormatException e) {
                sender.sendMessage(ERROR_COLOR + "Wrong page number " + args.get(0));
            }
        }

        this.ListMessage(sender, lines, page, "Available commands");
        return true;
    }
}
