package steve_gall.create_trainwrecked.common.content.train;

import java.util.List;

import net.minecraft.core.BlockPos;

public interface CarriageContraptionExtension
{
	List<Engine> getAssembledEngines();

	List<HeatSource> getAssembledHeatSources();

	BlockPos toLocalPos(BlockPos globalPos);
}
