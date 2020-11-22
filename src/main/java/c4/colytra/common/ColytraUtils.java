package c4.colytra.common;

import c4.colytra.Colytra;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class ColytraUtils {

    public static final String ELYTRA_ATTACH_TAG = Colytra.MODID + ":ElytraAttachment";
    public static final String ELYTRA_ACTIVE_TAG = "Active";
    public static final String ELYTRA_DAMAGE_TAG = "Damage";

    public static final EntityAttributeModifier FLIGHT_MODIFIER = new EntityAttributeModifier(UUID.fromString(
            "00250634-b3ac-4821-97b2-e1680947643f"),"Flight modifier", 1.0d, EntityAttributeModifier.
            Operation.ADDITION);

    public static boolean hasElytraAttachment(ItemStack stack) {
        return stack.hasTag() && stack.getTag().get(ELYTRA_ATTACH_TAG) != null;
    }
}
