package com.phoenixwb.golemchad;

import com.phoenixwb.golemchad.capability.GolemChadliness;
import com.phoenixwb.golemchad.config.GolemChadCommonConfigs;
import com.phoenixwb.golemchad.networking.GCPackets;
import com.phoenixwb.golemchad.sound.SoundEventInit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GolemChad.MODID)
public class GolemChad {
	public static final String MODID = "golemchad";

	public GolemChad() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GolemChadCommonConfigs.SPEC,
				"golemchad-common.toml");

		SoundEventInit.SOUND_EVENTS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::onRegisterCapabilities);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			GCPackets.register();
		});
	}

	private void onRegisterCapabilities(final RegisterCapabilitiesEvent event) {
		event.register(GolemChadliness.class);
	}
}
