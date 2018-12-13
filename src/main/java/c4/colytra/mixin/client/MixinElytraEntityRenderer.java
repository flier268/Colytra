package c4.colytra.mixin.client;

import c4.colytra.mixin.hooks.ClientHooks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.ElytraEntityRenderer;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ElytraEntityRenderer.class)
public abstract class MixinElytraEntityRenderer<T extends LivingEntity, M extends Model<T>> extends class_3887<T, M> {

    @Shadow
    @Final
    private ElytraEntityModel<T> field_4852;

    private MixinElytraEntityRenderer(class_3883<T, M> var1) {
        super(var1);
    }

    @Inject(method = "method_17161", at = @At("HEAD"))
    private void method_17161(T var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, CallbackInfo info) {
        ClientHooks.renderColytra(var1, var2, var3, var4, var5, var6, var7, var8, (ElytraEntityRenderer<T,M>)(Object)this, field_4852);
    }
}
