package com.phoenixwb.golemchad.sound;

import com.phoenixwb.golemchad.GolemChad;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundEventInit {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
			.create(ForgeRegistries.SOUND_EVENTS, GolemChad.MODID);

	public static final RegistryObject<SoundEvent> CHAD_THEME = SOUND_EVENTS.register("chad_theme",
			() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(GolemChad.MODID, "chad_theme")));
}
