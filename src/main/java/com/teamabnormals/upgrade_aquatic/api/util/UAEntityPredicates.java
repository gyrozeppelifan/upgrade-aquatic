package com.teamabnormals.upgrade_aquatic.api.util;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.pike.PikeEntity;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.pike.PikeType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;

import java.util.function.Predicate;

public class UAEntityPredicates {
	public static final Predicate<Entity> IS_SPECTRAL = (entity) -> ((PikeEntity) entity).getPikeType() == PikeType.SPECTRAL && !((PikeEntity) entity).isHidingInPickerelweed();
	public static final Predicate<Entity> IS_HIDING_IN_PICKERELWEED = (entity) -> !((PikeEntity) entity).isHidingInPickerelweed();
	public static final Predicate<Entity> IS_CHILD = (entity) -> ((Animal) entity).isBaby();
}