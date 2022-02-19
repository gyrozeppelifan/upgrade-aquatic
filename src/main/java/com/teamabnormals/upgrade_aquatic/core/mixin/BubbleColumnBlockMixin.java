package com.teamabnormals.upgrade_aquatic.core.mixin;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BubbleColumnBlock.class)
public final class BubbleColumnBlockMixin {
	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(BlockState state, ServerLevel world, BlockPos pos, Random random, CallbackInfo info) {
		BlockPos abovePos = pos.above();
		Block aboveBlock = world.getBlockState(abovePos).getBlock();
		boolean noFallingBlockAbove = world.getEntitiesOfClass(FallingBlockEntity.class, new AABB(pos)).isEmpty();

		if (noFallingBlockAbove) {
			UABlocks.FALLABLES.forEach((inputBlock, outputBlock) -> {
				if (inputBlock.get() == aboveBlock) {
					this.spawnFallingBlock(world, pos, outputBlock.get());
				}
			});

			if (UABlocks.ATMOSHPERIC_FALLABLES != null) {
				UABlocks.ATMOSHPERIC_FALLABLES.forEach((inputBlock, outputBlock) -> {
					if (inputBlock.get() == aboveBlock) {
						this.spawnFallingBlock(world, pos, outputBlock.get());
					}
				});
			}
		}
	}

	private void spawnFallingBlock(ServerLevel world, BlockPos pos, Block block) {
		FallingBlockEntity fallingblockentity = new FallingBlockEntity(world, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, block.defaultBlockState());
		fallingblockentity.time = 1;
		world.addFreshEntity(fallingblockentity);
	}
}