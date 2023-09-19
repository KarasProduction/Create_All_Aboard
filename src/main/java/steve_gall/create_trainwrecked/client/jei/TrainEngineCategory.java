package steve_gall.create_trainwrecked.client.jei;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import steve_gall.create_trainwrecked.common.fluid.FluidHelper;
import steve_gall.create_trainwrecked.common.init.ModRecipeTypes;
import steve_gall.create_trainwrecked.common.recipe.TrainEngineRecipe;

public class TrainEngineCategory implements IRecipeCategory<TrainEngineRecipe>
{
	public static final ResourceLocation TEXTURE_BACKGROUND = ModJEI.texture(ModJEIRecipeTypes.TRAIN_ENGINE);
	public static final String TEXT_TITLE = ModJEI.translationKey(ModJEIRecipeTypes.TRAIN_ENGINE, "title");
	public static final String TEXT_MAX_CARRIAGES = ModJEI.translationKey(ModJEIRecipeTypes.TRAIN_ENGINE, "max_carriages");
	public static final String TEXT_MAX_BLOCKS_PER_CARRIAGE = ModJEI.translationKey(ModJEIRecipeTypes.TRAIN_ENGINE, "max_blocks_per_carriage");
	public static final String TEXT_MAX_SPEED = ModJEI.translationKey(ModJEIRecipeTypes.TRAIN_ENGINE, "max_speed");
	public static final String TEXT_ACCELERATION = ModJEI.translationKey(ModJEIRecipeTypes.TRAIN_ENGINE, "acceleration");
	public static final String TEXT_MAX_FUEL_USAGE = ModJEI.translationKey(ModJEIRecipeTypes.TRAIN_ENGINE, "max_fuel_usage");
	public static final String TEXT_HEAT_DURABILITY = ModJEI.translationKey(ModJEIRecipeTypes.TRAIN_ENGINE, "heat_durability");

	private final IDrawable background;
	private final Component title;

	public TrainEngineCategory(IJeiHelpers jeiHelpers)
	{
		this.background = jeiHelpers.getGuiHelper().createDrawable(TEXTURE_BACKGROUND, 0, 0, 178, 96);
		this.title = new TranslatableComponent(TEXT_TITLE);
	}

	@Override
	public IDrawable getBackground()
	{
		return this.background;
	}

	@Override
	public Component getTitle()
	{
		return this.title;
	}

	@Override
	public IDrawable getIcon()
	{
		return null;
	}

	@Override
	public ResourceLocation getUid()
	{
		return ModRecipeTypes.TRAIN_ENGINE.getId();
	}

	@Override
	public Class<? extends TrainEngineRecipe> getRecipeClass()
	{
		return TrainEngineRecipe.class;
	}

	@Override
	public void draw(TrainEngineRecipe recipe, IRecipeSlotsView slotsView, PoseStack stack, double mouseX, double mouseY)
	{
		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;
		float maxSpeed = recipe.getMaxSpeed();
		double maxFuelUsage = recipe.getFuelUsage(0, maxSpeed);

		int textX = 4;
		int textY = 26;
		font.draw(stack, new TranslatableComponent(TEXT_MAX_CARRIAGES, String.format("%.2f", recipe.getMaxCarriageCount())), textX, textY, 0x000000);
		textY += font.lineHeight;

		font.draw(stack, new TranslatableComponent(TEXT_MAX_BLOCKS_PER_CARRIAGE, String.format("%.2f", recipe.getMaxBlockCountPerCarriage())), textX, textY, 0x000000);
		textY += font.lineHeight;

		font.draw(stack, new TranslatableComponent(TEXT_MAX_SPEED, String.format("%.2f", recipe.getMaxSpeed())), textX, textY, 0x000000);
		textY += font.lineHeight;

		font.draw(stack, new TranslatableComponent(TEXT_ACCELERATION, String.format("%.2f", recipe.getAcceleration())), textX, textY, 0x000000);
		textY += font.lineHeight;

		font.draw(stack, new TranslatableComponent(TEXT_MAX_FUEL_USAGE, String.format("%.1f", maxFuelUsage)), textX, textY, 0x000000);
		textY += font.lineHeight;

		if (recipe.getHeatPerFuel() > 0)
		{
			double heatDuration = recipe.getHeatDuration(maxFuelUsage);
			font.draw(stack, new TranslatableComponent(TEXT_HEAT_DURABILITY, String.format("%.2f", heatDuration)), textX, textY, 0x000000);
			textY += font.lineHeight;
		}

	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder layout, TrainEngineRecipe recipe, IFocusGroup focus)
	{
		layout.addSlot(RecipeIngredientRole.INPUT, 4, 4).addIngredients(recipe.getBlock());

		List<FluidStack> fluids = FluidHelper.deriveAmount(recipe.getFuelType().toIngredient().getMatchingFluidStacks(), FluidAttributes.BUCKET_VOLUME).toList();
		layout.addSlot(RecipeIngredientRole.INPUT, 28, 4).addIngredients(ForgeTypes.FLUID_STACK, fluids);
	}

}
