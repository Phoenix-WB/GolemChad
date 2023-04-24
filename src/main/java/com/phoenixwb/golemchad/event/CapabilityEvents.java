package com.phoenixwb.golemchad.event;

import com.phoenixwb.golemchad.GolemChad;
import com.phoenixwb.golemchad.capability.GolemChadlinessProvider;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = GolemChad.MODID)
public class CapabilityEvents {
	@SubscribeEvent
	public static void onAttachCapabilitiesGolem(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof IronGolem) {
			event.addCapability(new ResourceLocation(GolemChad.MODID, "golemchadliness"),
					new GolemChadlinessProvider());
		}
	}
}
