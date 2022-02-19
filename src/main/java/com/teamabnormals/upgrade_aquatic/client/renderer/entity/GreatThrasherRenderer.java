package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.teamabnormals.upgrade_aquatic.client.model.ThrasherModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.ThrasherRenderLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.thrasher.GreatThrasherEntity;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GreatThrasherRenderer extends MobRenderer<GreatThrasherEntity, ThrasherModel<GreatThrasherEntity>> {

	public GreatThrasherRenderer(EntityRenderDispatcher renderer) {
		super(renderer, new ThrasherModel<>(), 1.575F);
		this.addLayer(new ThrasherRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(GreatThrasherEntity entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/thrasher/great_thrasher.png");
	}

	@Override
	protected void scale(GreatThrasherEntity thrasher, PoseStack matrixStack, float partialTickTime) {
		float scale = 1.75F;
		matrixStack.scale(scale, scale, scale);
	}

}