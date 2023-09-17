package steve_gall.create_trainwrecked.common.recipe;

import com.simibubi.create.foundation.fluid.FluidIngredient;

import net.minecraft.world.level.material.Fluid;

public class FluidTagEntry extends RegistryTagEntry<Fluid, FluidIngredient>
{
	public static final FluidTagEntryType TYPE = new FluidTagEntryType();

	public FluidTagEntry(WrappedTagEntry tagEntry)
	{
		super(tagEntry);
	}

	@Override
	public RegistryTagEntryType<Fluid, FluidIngredient, FluidTagEntry> getType()
	{
		return TYPE;
	}

}
