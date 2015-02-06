package com.timethor.terracontrol.bukkit.commands;

import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.configuration.WorldConfig;
import com.timethor.terracontrol.bukkit.BukkitWorld;
import com.timethor.terracontrol.bukkit.TerraPerm;
import com.timethor.terracontrol.bukkit.TerraPlugin;
import com.timethor.terracontrol.core.builtin.TerraCatalog;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Level;

public class ReloadCommand extends BaseCommand {

    public ReloadCommand(TerraPlugin _plugin) {
        super(_plugin);
        name = "reload";
        perm = TerraPerm.CMD_RELOAD.node;
        usage = "reload [world_name]";
        workOnConsole = true;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        BukkitWorld world = this.getWorld(sender, args.size() > 0 ? args.get(0) : "");
        if (world == null) {
            sender.sendMessage(ERROR_COLOR + "World not found. Either you are not in a world with " + TerraCatalog.ChannelName.stringValue() + ", or you are the console.");
            return false;
        }

        WorldConfig newSettings = new WorldConfig(plugin.getWorldSettingsFolder(world.getName()), world, false);
        world.setSettings(newSettings);

        sender.sendMessage(MESSAGE_COLOR + "Configs for world '" + world.getName() + "' reloaded");
        if (sender instanceof Player) {
            TerraControl.log(Level.INFO, sender.getName(), " reloaded the config files for world '", world.getName(), "'.");
        }
        return true;
    }
}