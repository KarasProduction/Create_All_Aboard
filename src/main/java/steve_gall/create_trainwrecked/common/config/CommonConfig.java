package steve_gall.create_trainwrecked.common.config;

import java.util.Arrays;
import java.util.List;

import com.simibubi.create.AllTags.AllItemTags;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fluids.FluidType;

public class CommonConfig
{
	private ForgeConfigSpec configSpec;

	public final ConfigValue<List<? extends Number>> heatSourceSpeedLimits;
	public final ConfigValue<Integer> heatSourceBlazeFuelRegularBurnTime;
	public final ConfigValue<Integer> heatSourceBlazeFuelSpecialBurnTime;

	public final ConfigValue<Float> carriageSpeedStress;
	public final ConfigValue<Integer> carriageBlocksLimit;

	public final ConfigValue<Integer> oilGunCapacity;
	public final ConfigValue<Integer> oilGunTransfer;

	public CommonConfig()
	{
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		builder.push("heat_source");
		this.heatSourceSpeedLimits = builder.defineList("speed_limit", Arrays.asList(30.0F, 40.0F, 60.0F), s -> true);
		this.heatSourceBlazeFuelRegularBurnTime = builder.comment("#" + AllItemTags.BLAZE_BURNER_FUEL_REGULAR.tag.location()).define("blaze_fuel_regular_burn_time", 1600);
		this.heatSourceBlazeFuelSpecialBurnTime = builder.comment("#" + AllItemTags.BLAZE_BURNER_FUEL_SPECIAL.tag.location()).define("blaze_fuel_speecial_burn_time", 3200);
		builder.pop();

		builder.push("carriage");
		this.carriageSpeedStress = builder.define("speed_stress", 4.0F);
		this.carriageBlocksLimit = builder.define("blocks_limit", 50);
		builder.pop();

		builder.push("oil_gun");
		this.oilGunCapacity = builder.define("capacity", FluidType.BUCKET_VOLUME * 4);
		this.oilGunTransfer = builder.define("transfer", FluidType.BUCKET_VOLUME);
		builder.pop();

		this.configSpec = builder.build();
	}

	public ForgeConfigSpec getConfigSpec()
	{
		return this.configSpec;
	}

}
