package com.phoenixwb.golemchad.event;

import java.util.Iterator;

import com.phoenixwb.golemchad.GolemChad;
import com.phoenixwb.golemchad.capability.GolemChadlinessProvider;
import com.phoenixwb.golemchad.networking.GCPackets;
import com.phoenixwb.golemchad.networking.packet.GolemChadCapSyncS2CPacket;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = GolemChad.MODID)
public class PlayerEvents {
	@SubscribeEvent
	public static void onPlayerLogIn(PlayerLoggedInEvent event) {
		if (!event.getEntity().getLevel().isClientSide()) {
			ServerLevel level = (ServerLevel) event.getEntity().getLevel();
			Iterator<Entity> entities = level.getEntities().getAll().iterator();

			while (entities.hasNext()) {
				Entity golem = entities.next();
				if (golem instanceof IronGolem) {
					golem.getCapability(GolemChadlinessProvider.GOLEM_CHADLINESS).ifPresent(cap -> {
						GCPackets.sendToPlayer(new GolemChadCapSyncS2CPacket(cap.isChad(), golem.getId()),
								(ServerPlayer) event.getEntity());
					});
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerRespawn(PlayerRespawnEvent event) {
		if (!event.getEntity().getLevel().isClientSide()) {
			ServerLevel level = (ServerLevel) event.getEntity().getLevel();
			Iterator<Entity> entities = level.getEntities().getAll().iterator();

			while (entities.hasNext()) {
				Entity golem = entities.next();
				if (golem instanceof IronGolem) {
					golem.getCapability(GolemChadlinessProvider.GOLEM_CHADLINESS).ifPresent(cap -> {
						GCPackets.sendToPlayer(new GolemChadCapSyncS2CPacket(cap.isChad(), golem.getId()),
								(ServerPlayer) event.getEntity());
					});
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerChangeDimension(PlayerChangedDimensionEvent event) {
		if (!event.getEntity().getLevel().isClientSide()) {
			ServerLevel level = (ServerLevel) event.getEntity().getLevel();
			Iterator<Entity> entities = level.getEntities().getAll().iterator();

			while (entities.hasNext()) {
				Entity golem = entities.next();
				if (golem instanceof IronGolem) {
					golem.getCapability(GolemChadlinessProvider.GOLEM_CHADLINESS).ifPresent(cap -> {
						GCPackets.sendToPlayer(new GolemChadCapSyncS2CPacket(cap.isChad(), golem.getId()),
								(ServerPlayer) event.getEntity());
					});
				}
			}
		}
	}
}
