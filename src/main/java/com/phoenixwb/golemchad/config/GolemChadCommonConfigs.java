package com.phoenixwb.golemchad.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GolemChadCommonConfigs {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	public static final ForgeConfigSpec.ConfigValue<Boolean> PLAY_MUSIC;
	
	static {
		BUILDER.push("Golem Chad Config");
		
		PLAY_MUSIC = BUILDER.comment("Will chad music play when you chadify a golem?").define("play_chad_music", true);
		
		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}
