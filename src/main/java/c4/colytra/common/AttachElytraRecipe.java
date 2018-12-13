package c4.colytra.common;

import c4.colytra.Colytra;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.AbstractRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class AttachElytraRecipe extends AbstractRecipe {

    public AttachElytraRecipe(Identifier id) {
        super(id);
    }

    public boolean matches(Inventory var1, World var2) {
        if (!(var1 instanceof CraftingInventory)) {
            return false;
        } else {
            ItemStack var3 = ItemStack.EMPTY;
            ItemStack var4 = ItemStack.EMPTY;

            for(int var5 = 0; var5 < var1.getInvSize(); ++var5) {
                ItemStack var6 = var1.getInvStack(var5);

                if (!var6.isEmpty()) {

                    if (var6.getItem() instanceof ArmorItem && ((ArmorItem) var6.getItem()).getSlotType() == EquipmentSlot.CHEST) {

                        if (!var4.isEmpty()) {
                            return false;
                        }

                        if (ColytraUtils.hasElytraAttachment(var6)) {
                            return false;
                        }
                        var4 = var6;
                    } else {

                        if (var6.getItem() != Items.ELYTRA) {
                            return false;
                        }

                        if (!var3.isEmpty()) {
                            return false;
                        }
                        var3 = var6;
                    }
                }
            }
            return !var3.isEmpty() && !var4.isEmpty();
        }
    }

    public ItemStack craft(Inventory var1) {
        ItemStack var2 = ItemStack.EMPTY;
        ItemStack var3 = ItemStack.EMPTY;

        for(int var4 = 0; var4 < var1.getInvSize(); ++var4) {
            ItemStack var5 = var1.getInvStack(var4);
            if (!var5.isEmpty()) {
                if (var5.getItem() instanceof ArmorItem && ((ArmorItem) var5.getItem()).getSlotType() == EquipmentSlot.CHEST) {
                    var2 = var5.copy();
                } else if (var5.getItem() == Items.ELYTRA) {
                    var3 = var5;
                }
            }
        }

        if (var3.isEmpty()) {
            return var2;
        } else {
            CompoundTag tag = var2.hasTag() ? var2.getTag().copy() : new CompoundTag();
            tag.putBoolean(ColytraUtils.ELYTRA_ATTACH_TAG, true);
            var2.setTag(tag);
            return var2;
        }
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int var1, int var2) {
        return var1 * var2 >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return Colytra.ATTACH_ELYTRA;
    }
}
