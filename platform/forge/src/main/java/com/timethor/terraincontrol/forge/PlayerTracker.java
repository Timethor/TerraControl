package com.timethor.terraincontrol.forge;

import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.builtin.TerraCatalog;
import com.timethor.terracontrol.core.configuration.WorldConfig;
import com.timethor.terracontrol.forge.util.WorldHelper;
import cpw.mods.fml.common.IPlayerTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

public class PlayerTracker implements IPlayerTracker {

    TerraPlugin plugin;

    public PlayerTracker(TerraPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPlayerLogin(EntityPlayer player) {
        // Server-side - called whenever a player logs in
        // I couldn't find a way to detect if the client has TerrainControl,
        // so for now the configs are sent anyway.

        // Get the config
        TerraWorld worldTC = WorldHelper.toLocalWorld(player.worldObj);

        if (worldTC == null) {
            // World not loaded
            return;
        }
        WorldConfig config = worldTC.getSettings();

        // Serialize it
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream stream = new DataOutputStream(outputStream);
        try {
            stream.writeInt(TerraCatalog.ProtocolVersion.intValue());
            config.Serialize(stream);
        } catch (IOException e) {
            TerraControl.log(Level.SEVERE, e.getStackTrace().toString());
        }

        // Make the packet
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = TerraCatalog.ChannelName.stringValue();
        packet.data = outputStream.toByteArray();
        packet.length = outputStream.size();

        // Send the packet
        ((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(packet);
        System.out.println(TerraCatalog.ChannelName.stringValue() + ": sent config");
    }

    @Override
    public void onPlayerLogout(EntityPlayer player) {
        // Stub method
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player) {
        // Stub method
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player) {
        // Stub method
    }
}
