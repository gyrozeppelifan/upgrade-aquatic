package com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.ai;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.CassiopeaJellyfishEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class CassiopeaJellyfishFlipGoal extends Goal {
	private final CassiopeaJellyfishEntity jellyfish;
	private final Level world;
	private int ticksPassed;

	public CassiopeaJellyfishFlipGoal(CassiopeaJellyfishEntity jellyfish) {
		this.jellyfish = jellyfish;
		this.world = jellyfish.level;
		this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.jellyfish.getRandom().nextFloat() < 0.025F && this.jellyfish.isEyeInFluid(FluidTags.WATER) && this.world.isDay()) {
			BlockPos pos = this.jellyfish.blockPosition();
			if (pos.getY() >= this.world.getSeaLevel() - 2) {
				if (this.world.dimensionType().hasSkyLight() && this.world.canSeeSkyFromBelowWater(pos)) {
					return !this.jellyfish.hasUpsideDownCooldown() && !this.jellyfish.isOnGround();
				}
			}
		}
		return false;
	}

	@Override
	public void start() {
		this.ticksPassed = 0;
		this.jellyfish.upsideDownCooldown = this.jellyfish.getRandom().nextInt(1200) + 1600;
		this.jellyfish.lockedRotations[1] = 180.0F;
	}

	@Override
	public void tick() {
		this.ticksPassed++;
	}

	@Override
	public boolean canContinueToUse() {
		return this.ticksPassed < 40 && !this.jellyfish.isOnGround() && this.jellyfish.isEyeInFluid(FluidTags.WATER) && this.world.isDay() && this.jellyfish.blockPosition().getY() >= this.world.getSeaLevel() - 4;
	}
}