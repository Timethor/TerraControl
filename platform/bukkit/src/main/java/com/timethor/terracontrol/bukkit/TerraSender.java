package com.timethor.terracontrol.bukkit;

import com.timethor.terracontrol.core.builtin.TerraCatalog;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.configuration.WorldConfig;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Timethor
 */
public class TerraSender {

    /**
     */
    private TerraPlugin plugin;

    public TerraSender(TerraPlugin plugin) {
        this.plugin = plugin;
    }

    public void send(Player player) {
        // Send the configs
        World world = player.getWorld();

        if (plugin.worlds.containsKey(world.getUID())) {
            WorldConfig config = plugin.worlds.get(world.getUID()).getSettings();

            // TerraControl.log("config sent to player for world " + config.WorldName); //debug
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DataOutputStream stream = new DataOutputStream(outputStream);

            try {
                stream.writeInt(TerraCatalog.ProtocolVersion.intValue());
                config.Serialize(stream);
                stream.flush();
            } catch (IOException e) {
                TerraControl.log(Level.WARNING, e.getStackTrace().toString());
            }

            byte[] data = outputStream.toByteArray();

            player.sendPluginMessage(plugin, TerraCatalog.ChannelName.stringValue(), data);
        }
    }
}
