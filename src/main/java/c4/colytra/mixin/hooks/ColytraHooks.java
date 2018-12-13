package c4.colytra.mixin.hooks;

import c4.caelus.Caelus;
import c4.colytra.common.ColytraUtils;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.item.ItemStack;

public class ColytraHooks {

    public static void updateColytra(LivingEntity livingEntity) {
        EntityAttributeInstance flight = livingEntity.getAttributeInstance(Caelus.ELYTRA_FLIGHT);

        if (flight != null) {
            flight.removeModifier(ColytraUtils.FLIGHT_MODIFIER);
            ItemStack stack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);

            if (ColytraUtils.hasElytraAttachment(stack)) {
                livingEntity.getAttributeInstance(Caelus.ELYTRA_FLIGHT).addModifier(ColytraUtils.FLIGHT_MODIFIER);
            }
        }
    }
}
