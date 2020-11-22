package c4.colytra;

import c4.colytra.common.AttachElytraRecipe;
import net.fabricmc.api.ModInitializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;

public class Colytra implements ModInitializer {

	public static final String MODID = "colytra";

	public static RecipeSerializer<ShapelessRecipe> ATTACH_ELYTRA;

	@Override
	public void onInitialize() {
		ATTACH_ELYTRA = RecipeSerializer.register("crafting_special_attachelytra", new AttachElytraRecipe.Serializer());
	}
}
