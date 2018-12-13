package c4.colytra;

import c4.colytra.common.AttachElytraRecipe;
import net.fabricmc.api.ModInitializer;
import net.minecraft.recipe.RecipeSerializers;

public class Colytra implements ModInitializer {

	public static final String MODID = "colytra";

	public static RecipeSerializers.Dummy<AttachElytraRecipe> ATTACH_ELYTRA;

	@Override
	public void onInitialize() {
		ATTACH_ELYTRA = RecipeSerializers.register(new RecipeSerializers.Dummy<>("colytra:crafting_special_attachelytra", AttachElytraRecipe::new));
	}
}
