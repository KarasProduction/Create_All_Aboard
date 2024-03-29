package steve_gall.create_trainwrecked.common.init;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.create_trainwrecked.common.CreateTrainwrecked;
import steve_gall.create_trainwrecked.common.crafting.TrainEngineCoolantRecipe;
import steve_gall.create_trainwrecked.common.crafting.TrainEngineTypeRecipe;
import steve_gall.create_trainwrecked.common.crafting.TrainHeatSourceRecipe;

public class ModRecipeTypes
{
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, CreateTrainwrecked.MOD_ID);
	public static final RegistryObject<RecipeType<TrainEngineTypeRecipe>> TRAIN_ENGINE_TYPE = register("train_engine_type");
	public static final RegistryObject<RecipeType<TrainEngineCoolantRecipe>> TRAIN_ENGINE_COOLANT = register("train_engine_coolant");
	public static final RegistryObject<RecipeType<TrainHeatSourceRecipe>> TRAIN_HEAT_SOURCE = register("train_heat_source");

	private static <RECIPE extends Recipe<?>> RegistryObject<RecipeType<RECIPE>> register(String name)
	{
		return RECIPE_TYPES.register(name, () -> RecipeType.simple(CreateTrainwrecked.asResource(name)));
	}

	private ModRecipeTypes()
	{

	}

}
