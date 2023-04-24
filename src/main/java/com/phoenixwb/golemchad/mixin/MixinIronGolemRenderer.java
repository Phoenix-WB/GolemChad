package com.phoenixwb.golemchad.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.phoenixwb.golemchad.GolemChad;
import com.phoenixwb.golemchad.capability.GolemChadlinessProvider;

import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.IronGolem;

@Mixin(IronGolemRenderer.class)
public class MixinIronGolemRenderer {
	private static final ResourceLocation GOLEM_CHAD = new ResourceLocation(GolemChad.MODID,
			"textures/entity/golem_chad.png");

	@Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/renderer/entity/IronGolemRenderer;getTextureLocation(Lnet/minecraft/world/entity/animal/IronGolem;)Lnet/minecraft/resources/ResourceLocation;", cancellable = true)
	public void getTextureLocation(IronGolem golem, CallbackInfoReturnable<ResourceLocation> callback) {
		golem.getCapability(GolemChadlinessProvider.GOLEM_CHADLINESS).ifPresent(cap -> {
			if(cap.isChad()) {
				callback.setReturnValue(GOLEM_CHAD);
			}
		});
	}
}
