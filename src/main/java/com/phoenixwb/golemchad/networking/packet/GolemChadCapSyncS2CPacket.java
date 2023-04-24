package com.phoenixwb.golemchad.networking.packet;

import java.util.function.Supplier;

import com.phoenixwb.golemchad.capability.GolemChadlinessProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class GolemChadCapSyncS2CPacket {
	private boolean isChad;
	private int entityID;
	
	public GolemChadCapSyncS2CPacket(boolean isChad, int entityID) {
		this.isChad = isChad;
		this.entityID = entityID;
	}
	
	public GolemChadCapSyncS2CPacket(FriendlyByteBuf buf) {
		this(buf.readBoolean(), buf.readInt());
	}
	
	public void toBytes(FriendlyByteBuf buf) {
		buf.writeBoolean(isChad);
		buf.writeInt(entityID);
	}
	
	@SuppressWarnings("resource")
	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			Minecraft.getInstance().level.getEntity(entityID).getCapability(GolemChadlinessProvider.GOLEM_CHADLINESS).ifPresent(cap -> {
				cap.setChad(isChad);
			});
		});
		
		context.setPacketHandled(true);
		return true;
	}
}
