package com.timethor.terracontrol.bukkit.commands;

import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.custom.object.CustomObject;
import com.timethor.terracontrol.bukkit.BukkitWorld;
import com.timethor.terracontrol.bukkit.TerraPerm;
import com.timethor.terracontrol.bukkit.TerraPlugin;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListCommand extends BaseCommand {

    public ListCommand(TerraPlugin _plugin) {
        super(_plugin);
        name = "list";
        perm = TerraPerm.CMD_LIST.node;
        usage = "list [-w World] [page]";
        workOnConsole = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {

        int page = 1;

        if (args.size() > 1 && args.get(0).equals("-w")) {
            String worldName = args.get(1);
            if (args.size() > 2) {
                try {
                    page = Integer.parseInt(args.get(2));
                } catch (Exception e) {
                    sender.sendMessage(ERROR_COLOR + "Wrong page number " + args.get(2));
                }
            }
            BukkitWorld world = this.getWorld(sender, worldName);

            if (world != null) {
                if (world.getSettings().customObjects.isEmpty()) {
                    sender.sendMessage(MESSAGE_COLOR + "This world does not have custom objects");
                }

                List<String> pluginList = new ArrayList<>();
                for (CustomObject object : world.getSettings().customObjects) {
                    pluginList.add(VALUE_COLOR + object.getName());
                }

                this.ListMessage(sender, pluginList, page, "World objects");

            } else {
                sender.sendMessage(ERROR_COLOR + "World not found " + worldName);
            }
            return true;

        }
        if (args.size() > 0) {
            try {
                page = Integer.parseInt(args.get(0));
            } catch (Exception e) {
                sender.sendMessage(ERROR_COLOR + "Wrong page number " + args.get(0));
            }
        }

        Collection<CustomObject> globalObjects = TerraControl.getCustomObjectManager().globalObjects.values();

        if (globalObjects.isEmpty()) {
            sender.sendMessage(MESSAGE_COLOR + "This global directory does not have custom objects");
        }

        List<String> pluginList = new ArrayList<>();
        for (CustomObject object : globalObjects) {
            if (object.canSpawnAsObject()) {
                pluginList.add(VALUE_COLOR + object.getName());
            }
        }

        this.ListMessage(sender, pluginList, page, "Global objects", "Use /tc list -w [world] for world objects");

        return true;

    }
}