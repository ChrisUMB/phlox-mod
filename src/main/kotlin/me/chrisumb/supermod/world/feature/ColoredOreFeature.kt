package me.chrisumb.supermod.world.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import me.chrisumb.supermod.blocks.colored.ColoredOreBlock
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import java.util.*

object ColoredOreFeature : Feature<ColoredOreFeature.Config>({ Config() }) {

    class Config : FeatureConfig {
        override fun <T : Any?> serialize(ops: DynamicOps<T>?): Dynamic<T> {
            return Dynamic(ops)
        }
    }

    override fun generate(
        world: IWorld,
        generator: ChunkGenerator<out ChunkGeneratorConfig>?,
        random: Random,
        pos: BlockPos,
        config: Config
    ): Boolean {
        return if (world.getBlockState(pos).block == Blocks.STONE) {
            world.setBlockState(pos, ColoredOreBlock.randomColor(), 2)
            true
        } else {
            false
        }
    }
}