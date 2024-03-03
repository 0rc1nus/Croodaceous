package coda.croodaceous.client.render;

import coda.croodaceous.CroodaceousMod;
import coda.croodaceous.client.model.BearPearModel;
import coda.croodaceous.common.entities.BearPear;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BearPearRenderer extends GeoEntityRenderer<BearPear> {
	private static final ResourceLocation TEXTURE_IDLE = new ResourceLocation(CroodaceousMod.MOD_ID, "textures/entity/bear_pear/idle.png");
	private static final ResourceLocation TEXTURE_HOSTILE = new ResourceLocation(CroodaceousMod.MOD_ID, "textures/entity/bear_pear/hostile.png");

	public BearPearRenderer(EntityRendererProvider.Context mgr) {
		super(mgr, new BearPearModel());
	}

	@Override
	public ResourceLocation getTextureLocation(BearPear animatable) {
		if (animatable.isAggressive() || animatable.isDropping()) {
			return TEXTURE_HOSTILE;
		}
		return TEXTURE_IDLE;
	}

	@Override
	public RenderType getRenderType(BearPear animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityCutoutNoCull(texture);
	}

	@Override
	public void render(BearPear entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		if (entity.isBaby()) {
			poseStack.scale(0.5F, 0.5F, 0.5F);
		}
		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
	}
}