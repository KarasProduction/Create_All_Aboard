package steve_gall.create_trainwrecked.common.mixin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.trains.entity.CarriageContraption;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;
import steve_gall.create_trainwrecked.common.content.train.CarriageContraptionExtension;
import steve_gall.create_trainwrecked.common.content.train.CarriageContraptionHelper;
import steve_gall.create_trainwrecked.common.content.train.EnginPos;

@Mixin(value = CarriageContraption.class, remap = false)
public abstract class CarriageContraptionMixin extends Contraption implements CarriageContraptionExtension
{
	// during assembly only
	@Unique
	private List<EnginPos> assembledEnginePos = new ArrayList<>();

	@Inject(method = "capture", at = @At(value = "HEAD"), cancellable = true)
	protected void capture(Level level, BlockPos pos, CallbackInfoReturnable<Pair<StructureBlockInfo, BlockEntity>> cir)
	{
		CarriageContraptionHelper.capture((CarriageContraption) (Object) this, level, pos);
	}

	@Override
	@Unique
	public List<EnginPos> getAssembledEnginePos()
	{
		return this.assembledEnginePos;
	}

	@Override
	public BlockPos toLocalPos(BlockPos globalPos)
	{
		return super.toLocalPos(globalPos);
	}

}
