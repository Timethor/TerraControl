package com.timethor.terraincontrol.forge;

import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.builtin.TerraCatalog;
import com.timethor.terracontrol.core.configuration.WorldConfig;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;

public class PacketHandler implements IPacketHandler {

    TerraPlugin plugin;

    public PacketHandler(TerraPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload receivedPacket, Player player) {
        // This method receives the TerrainControl packet with the custom biome
        // colors and weather.

        if (!receivedPacket.channel.equals(TerraCatalog.ChannelName.stringValue())) {
            // Make sure that the right channel is being received
            return;
        }

        // We're on the client, receive the packet
        ByteArrayInputStream inputStream = new ByteArrayInputStream(receivedPacket.data);
        DataInputStream stream = new DataInputStream(inputStream);
        try {
            int serverProtocolVersion = stream.readInt();
            int clientProtocolVersion = TerraCatalog.ProtocolVersion.intValue();
            if (serverProtocolVersion == clientProtocolVersion) {
                // Server sent config

                // Restore old biomes
                ForgeWorld.restoreBiomes();

                if (receivedPacket.length > 4) {
                    // If the packet wasn't empty, add the new biomes
                    WorldClient worldMC = FMLClientHandler.instance().getClient().theWorld;

                    ForgeWorld worldTC = new ForgeWorld("external");
                    WorldConfig config = new WorldConfig(stream, worldTC);

                    worldTC.InitM(worldMC, config);
                }

                System.out.println(TerraCatalog.ChannelName.stringValue() + ": config received from server");
            } else {
                // Server or client is outdated
                System.out.println(TerraCatalog.ChannelName.stringValue() + ": server has different protocol version! " + "Client: " + TerraCatalog.ProtocolVersion.intValue() + " Server: " + serverProtocolVersion);
            }
        } catch (IOException e) {
            TerraControl.log(Level.SEVERE, e.getStackTrace().toString());
        }

    }
}
