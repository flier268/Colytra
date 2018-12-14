package c4.colytra.mixin.hooks;

import c4.colytra.common.ColytraUtils;
import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.ArmorEntityRenderer;
import net.minecraft.client.render.entity.ElytraEntityRenderer;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ClientHooks {

    private static final Identifier SKIN = new Identifier("textures/entity/elytra.png");

    public static List<TextComponent> getColytraTooltip(ItemStack stack, List<TextComponent> tooltip) {
        if (ColytraUtils.hasElytraAttachment(stack)) {
            tooltip.add(1, new TranslatableTextComponent("colytra.elytraAttachment").applyFormat(TextFormat.GRAY, TextFormat.ITALIC));
        }
        return tooltip;
    }

    public static void renderColytra(LivingEntity var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, ElytraEntityRenderer elytraEntityRenderer, ElytraEntityModel model) {
        ItemStack var9 = var1.getEquippedStack(EquipmentSlot.CHEST);

        if (ColytraUtils.hasElytraAttachment(var9)) {
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SrcBlendFactor.ONE, GlStateManager.DstBlendFactor.ZERO);

            if (var1 instanceof AbstractClientPlayerEntity) {
                AbstractClientPlayerEntity var10 = (AbstractClientPlayerEntity)var1;

                if (var10.method_3126() && var10.method_3122() != null) {
                    elytraEntityRenderer.method_17164(var10.method_3122());
                } else if (var10.method_3125() && var10.method_3119() != null && var10.isSkinOverlayVisible(PlayerModelPart.CAPE)) {
                    elytraEntityRenderer.method_17164(var10.method_3119());
                } else {
                    elytraEntityRenderer.method_17164(SKIN);
                }
            } else {
                elytraEntityRenderer.method_17164(SKIN);
            }
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, 0.0F, 0.125F);
            model.method_17079(var1, var2, var3, var5, var6, var7, var8);
            model.method_17078(var1, var2, var3, var5, var6, var7, var8);

            if (var9.hasEnchantments()) {
                ArmorEntityRenderer.renderEnchantedGlint(elytraEntityRenderer::method_17164, var1, model, var2, var3, var4, var5, var6, var7, var8);
            }
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
}
