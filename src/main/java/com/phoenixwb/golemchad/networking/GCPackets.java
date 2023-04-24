package com.phoenixwb.golemchad.networking;

import com.phoenixwb.golemchad.GolemChad;
import com.phoenixwb.golemchad.networking.packet.GolemChadCapSyncS2CPacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class GCPackets {
	private static SimpleChannel INSTANCE;

	private static int packetID = 0;

	public static void register() {
		SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(GolemChad.MODID, "messages"))
				.networkProtocolVersion(() -> "1.0").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true)
				.simpleChannel();

		INSTANCE = net;

		net.messageBuilder(GolemChadCapSyncS2CPacket.class, packetID++, NetworkDirection.PLAY_TO_CLIENT)
				.decoder(GolemChadCapSyncS2CPacket::new).encoder(GolemChadCapSyncS2CPacket::toBytes)
				.consumerMainThread(GolemChadCapSyncS2CPacket::handle).add();
	}

	public static <MSG> void sendToServer(MSG message) {
		INSTANCE.sendToServer(message);
	}

	public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
		INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
	}
}
