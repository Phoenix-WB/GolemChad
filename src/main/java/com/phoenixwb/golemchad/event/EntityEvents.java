package com.phoenixwb.golemchad.event;

import java.util.UUID;

import com.phoenixwb.golemchad.GolemChad;
import com.phoenixwb.golemchad.capability.GolemChadlinessProvider;
import com.phoenixwb.golemchad.config.GolemChadCommonConfigs;
import com.phoenixwb.golemchad.networking.GCPackets;
import com.phoenixwb.golemchad.networking.packet.GolemChadCapSyncS2CPacket;
import com.phoenixwb.golemchad.sound.SoundEventInit;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = GolemChad.MODID)
public class EntityEvents {
	public static final UUID GOLEM_MAX_HEALTH = UUID.fromString("a4548de1-3167-45ab-a299-2f939828b562");
	public static final UUID GOLEM_ATTACK_DAMAGE = UUID.fromString("57a1cb91-13d8-437a-a619-2e08f7db3997");

	@SubscribeEvent
	public static void onInteractWithEntity(PlayerInteractEvent.EntityInteract event) {
		ItemStack star = event.getItemStack();
		Entity golem = event.getTarget();

		if (star.is(Items.NETHER_STAR) && golem instanceof IronGolem) {
			golem.getCapability(GolemChadlinessProvider.GOLEM_CHADLINESS).ifPresent(cap -> {
				if (!cap.isChad()) {
					if (!event.getLevel().isClientSide()) {
						star.shrink(1);
						
						if (GolemChadCommonConfigs.PLAY_MUSIC.get()) {
							golem.playSound(SoundEventInit.CHAD_THEME.get(), 0.6F, 1.0F);
						}

						((IronGolem) golem).getAttribute(Attributes.MAX_HEALTH)
								.removePermanentModifier(GOLEM_MAX_HEALTH);
						((IronGolem) golem).getAttribute(Attributes.ATTACK_DAMAGE)
								.removePermanentModifier(GOLEM_ATTACK_DAMAGE);

						((IronGolem) golem).getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(
								new AttributeModifier(GOLEM_MAX_HEALTH, "golemchadattribute.increased_health", 3,
										AttributeModifier.Operation.MULTIPLY_BASE));

						((IronGolem) golem).setHealth(((IronGolem) golem).getMaxHealth());

						((IronGolem) golem).getAttribute(Attributes.ATTACK_DAMAGE)
								.addPermanentModifier(new AttributeModifier(GOLEM_ATTACK_DAMAGE,
										"golemchadattribute.increased_damage", 3,
										AttributeModifier.Operation.MULTIPLY_BASE));
					}

					cap.setChad(true);
				}
			});
		}
	}
}
