package com.phoenixwb.golemchad.capability;

import net.minecraft.nbt.CompoundTag;

public class GolemChadliness {
	private boolean chad;
	
	public GolemChadliness() {
		setChad(false);
	}
	
	public boolean isChad() {
		return chad;
	}
	
	public void setChad(boolean chad) {
		this.chad = chad;
	}
	
	
	public void saveNBT(CompoundTag nbt) {
		nbt.putBoolean("isChad", isChad());
	}
	
	public void loadNBT(CompoundTag nbt) {
		setChad(nbt.getBoolean("isChad"));
	}
}
