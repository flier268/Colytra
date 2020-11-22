package c4.colytra.common;

import c4.colytra.Colytra;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class AttachElytraRecipe extends ShapelessRecipe {


    public AttachElytraRecipe(Identifier id, String group, ItemStack output, DefaultedList<Ingredient> input) {
        super(id, group, output, input);
    }

    @Override
    public boolean matches(CraftingInventory craftingInventory, World world) {
        if (craftingInventory.getInvSize() == 2) {
            ItemStack Armor = ItemStack.EMPTY;
            ItemStack Elytra = ItemStack.EMPTY;
            if (craftingInventory.getInvStack(0).getItem() instanceof ArmorItem && craftingInventory.getInvStack(1).getItem().equals(Items.ELYTRA)) {
                Armor = craftingInventory.getInvStack(0);
                Elytra = craftingInventory.getInvStack(1);
            } else if (craftingInventory.getInvStack(1).getItem() instanceof ArmorItem && craftingInventory.getInvStack(0).getItem().equals(Items.ELYTRA)) {
                Armor = craftingInventory.getInvStack(1);
                Elytra = craftingInventory.getInvStack(0);
            } else
                return false;

            if (ColytraUtils.hasElytraAttachment(Armor))
                return false;
        }
        return super.matches(craftingInventory, world);
    }

    @Override
    public ItemStack craft(CraftingInventory craftingInventory) {
        if (craftingInventory.getInvSize() == 2) {
            ItemStack Armor = ItemStack.EMPTY;
            ItemStack Elytra = ItemStack.EMPTY;
            if (craftingInventory.getInvStack(0).getItem() instanceof ArmorItem && craftingInventory.getInvStack(1).getItem().equals(Items.ELYTRA)) {
                Armor = craftingInventory.getInvStack(0);
                Elytra = craftingInventory.getInvStack(1);
            } else if (craftingInventory.getInvStack(1).getItem() instanceof ArmorItem && craftingInventory.getInvStack(0).getItem().equals(Items.ELYTRA)) {
                Armor = craftingInventory.getInvStack(1);
                Elytra = craftingInventory.getInvStack(0);
            }
            else
                return ItemStack.EMPTY;
            CompoundTag tag = Armor.hasTag() ? Armor.getTag().copy() : new CompoundTag();
            CompoundTag attach = new CompoundTag();
            attach.putBoolean(ColytraUtils.ELYTRA_ACTIVE_TAG, true);
            attach.putInt(ColytraUtils.ELYTRA_DAMAGE_TAG, Items.ELYTRA.getMaxDamage());
            tag.put(ColytraUtils.ELYTRA_ATTACH_TAG, attach);
            Elytra.setTag(tag);
            return Elytra;
        }
        return super.craft(craftingInventory);
    }


    public RecipeSerializer<?> getSerializer() {
        return Colytra.ATTACH_ELYTRA;
    }
}
