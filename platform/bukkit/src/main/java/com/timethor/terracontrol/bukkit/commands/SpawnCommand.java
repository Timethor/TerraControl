package com.timethor.terracontrol.bukkit.commands;

import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.custom.object.CustomObject;
import com.timethor.terracontrol.core.builtin.MaterialCatalog;
import com.timethor.terracontrol.core.custom.object.Rotation;

import com.timethor.terracontrol.bukkit.BukkitWorld;
import com.timethor.terracontrol.bukkit.TerraPerm;
import com.timethor.terracontrol.bukkit.TerraPlugin;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SpawnCommand extends BaseCommand {

    public SpawnCommand(TerraPlugin _plugin) {
        super(_plugin);
        name = "spawn";
        perm = TerraPerm.CMD_SPAWN.node;
        usage = "spawn Name [World]";
        workOnConsole = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        Player me = (Player) sender;
        Random random = new Random();

        BukkitWorld bukkitWorld = this.getWorld(me, args.size() > 1 ? args.get(1) : "");

        if (args.isEmpty()) {
            me.sendMessage(ERROR_COLOR + "You must enter the name of the BO2.");
            return true;
        }
        CustomObject spawnObject = null;

        if (bukkitWorld != null) {
            spawnObject = TerraControl.getCustomObjectManager().getObjectFromString(args.get(0), bukkitWorld);
        }

        if (spawnObject == null) {
            sender.sendMessage(ERROR_COLOR + "Object not found, use '/terra list' to list the available ones.");
            return true;
        }

        Block block = this.getWatchedBlock(me, true);
        if (block == null) {
            return true;
        }

        if (spawnObject.spawnForced(bukkitWorld, random, Rotation.NORTH, block.getX(), block.getY(), block.getZ())) {
            me.sendMessage(BaseCommand.MESSAGE_COLOR + spawnObject.getName() + " was spawned.");
        } else {
            me.sendMessage(BaseCommand.ERROR_COLOR + "Object can't be spawned over there.");
        }

        return true;
    }

    public Block getWatchedBlock(Player me, boolean verbose) {
        if (me == null) {
            return null;
        }

        Block block;
        Block previousBlock = null;

        Iterator<Block> itr = new BlockIterator(me, 200);
        while (itr.hasNext()) {
            block = itr.next();
            if (block.getTypeId() != MaterialCatalog.AIR.id && block.getTypeId() != MaterialCatalog.LONG_GRASS.id) {
                return previousBlock;
            }
            previousBlock = block;
        }

        if (verbose) {
            me.sendMessage(ERROR_COLOR + "No block in sight.");
        }

        return null;
    }
}