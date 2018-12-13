package c4.colytra.mixin.common;

import c4.colytra.mixin.hooks.ColytraHooks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

    private MixinLivingEntity() {
        super(null, null);
    }

    @Inject(method = "updateLogic", at = @At(value = "TAIL"))
    public void updateLogic(CallbackInfo info) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        ColytraHooks.updateColytra(livingEntity);
    }
}
