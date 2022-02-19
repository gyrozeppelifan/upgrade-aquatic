package com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish;

import com.teamabnormals.upgrade_aquatic.client.model.jellyfish.ImmortalJellyfishModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish.layers.JellyfishEmissiveLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.ImmortalJellyfishEntity;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;

public class ImmortalJellyfishRenderer<I extends ImmortalJellyfishEntity> extends AbstractJellyfishRenderer<I> {

	public ImmortalJellyfishRenderer(EntityRenderDispatcher renderManager) {
		super(renderManager, new ImmortalJellyfishModel<>(), 0.25F);
		this.addLayer(new JellyfishEmissiveLayer<>(this, this));
	}

	@Override
	public ResourceLocation getTextureLocation(I jellyfish) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/jellyfish/immortal/" + jellyfish.getBucketName() + "_jellyfish.png");
	}

	@Override
	public ResourceLocation getOverlayTexture(I jellyfish) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/jellyfish/immortal/" + jellyfish.getBucketName() + "_jellyfish_overlay.png");
	}

	@Override
	protected RenderType getRenderType(I jellyfish, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
		return RenderType.entityTranslucent(this.getTextureLocation(jellyfish));
	}

}