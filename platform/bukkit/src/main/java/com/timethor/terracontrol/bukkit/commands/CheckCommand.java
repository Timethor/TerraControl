package com.timethor.terracontrol.bukkit.commands;

import com.timethor.terracontrol.core.configuration.WorldConfig;
import com.timethor.terracontrol.bukkit.BukkitWorld;
import com.timethor.terracontrol.bukkit.TerraPerm;
import com.timethor.terracontrol.bukkit.TerraPlugin;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.List;

public class CheckCommand extends BaseCommand {

    public CheckCommand(TerraPlugin _plugin) {
        super(_plugin);
        name = "check";
        perm = TerraPerm.CMD_CHECK.node;
        usage = "check World_Name";
        workOnConsole = true;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if (args.isEmpty()) {
            sender.sendMessage(ERROR_COLOR + "You need to select world");
            return true;
        }

        String worldName = args.get(0);
        File settingsFolder = plugin.getWorldSettingsFolder(worldName);
        new WorldConfig(settingsFolder, new BukkitWorld(worldName), true);

        sender.sendMessage(MESSAGE_COLOR + "Done!");
        return true;
    }
}