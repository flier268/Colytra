package c4.colytra.mixin.client;

import c4.colytra.mixin.hooks.ClientHooks;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(ItemStack.class)
public class MixinItemStack {

    @ModifyVariable(method = "getTooltipText", at = @At("TAIL"))
    private List<TextComponent> getTooltipText(List<TextComponent> list) {
        ItemStack stack = (ItemStack)(Object)this;
        return ClientHooks.getColytraTooltip(stack, list);
    }
}
