package me.chrisumb.supermod.blocks

import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.OreBlock
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import java.awt.Color
import kotlin.random.Random

val red = IntProperty.of("red", 0, 15)
val green = IntProperty.of("green", 0, 15)
val blue = IntProperty.of("blue", 0, 15)

object ColoredOreBlock : OreBlock(Settings.of(Material.STONE).strength(3.0f, 3.0f)) {


    init {
        ColorProviderRegistry.BLOCK.register(BlockColorProvider { state, view, pos, tintIndex ->
            if (tintIndex != 0) {
                return@BlockColorProvider Color(255, 255, 255).rgb
            }

            val r = state[red] shl 4
            val g = state[green] shl 4
            val b = state[blue] shl 4
            Color(r, g, b).rgb
        }, this)

        defaultState = stateManager.defaultState.with(red, 0).with(green, 0).with(blue, 0)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {

        builder.add(red, green, blue)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        return randomColor()
    }

    override fun isSimpleFullBlock(state: BlockState?, view: BlockView?, pos: BlockPos?): Boolean {
        return false
    }

    override fun isTranslucent(state: BlockState?, view: BlockView?, pos: BlockPos?): Boolean {
        return true
    }

    fun randomColor(): BlockState {
        return stateManager.defaultState
            .with(red, Random.nextInt(16))
            .with(green, Random.nextInt(16))
            .with(blue, Random.nextInt(16))
    }

}