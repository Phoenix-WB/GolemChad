package com.phoenixwb.golemchad.capability;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class GolemChadlinessProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
	public static Capability<GolemChadliness> GOLEM_CHADLINESS = CapabilityManager.get(new CapabilityToken<>() {
	});
	
	private GolemChadliness golemChadliness = null;
	private final LazyOptional<GolemChadliness> optional = LazyOptional.of(this::createGolemChadliness);
	
	private GolemChadliness createGolemChadliness() {
		if(this.golemChadliness == null) {
			this.golemChadliness = new GolemChadliness();
		}
		return this.golemChadliness;
	}
	
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if(cap == GOLEM_CHADLINESS) {
			return optional.cast();
		}
		return LazyOptional.empty();
	}
	
	@Override public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		createGolemChadliness().saveNBT(nbt);
		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		createGolemChadliness().loadNBT(nbt);
	}
}
