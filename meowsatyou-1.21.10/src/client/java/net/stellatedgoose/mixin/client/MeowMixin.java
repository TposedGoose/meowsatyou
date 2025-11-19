package net.stellatedgoose.mixin.client;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.stellatedgoose.MeowsAtYouClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class MeowMixin {
	@Inject(at = @At("TAIL"), method = "attackBlock")
	private void meowChanceOnAttackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
		MeowsAtYouClient.tryMeow(600, false);
	}

	@Inject(at = @At("TAIL"), method = "attackEntity")
	private void meowChanceOnAttackEntity(PlayerEntity player, Entity target, CallbackInfo ci) {
		MeowsAtYouClient.tryMeow(200, false);
	}
}