package me.chrisumb.supermod.blocks.colored

import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.LeavesBlock
import net.minecraft.block.Material
import net.minecraft.entity.EntityType
import net.minecraft.item.ItemPlacementContext
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView

object ColoredLeavesBlock : LeavesBlock(Settings.of(Material.LEAVES).strength(3.0f, 3.0f).nonOpaque()) {

    init {
        ColorProviderRegistry.BLOCK.register(RGBColorProvider, this)

        defaultState = stateManager.defaultState
            .with(propR1, 3)
            .with(propG1, 3)
            .with(propB1, 3)
    }

    override fun getSoundGroup(state: BlockState): BlockSoundGroup {
        return BlockSoundGroup.GRASS
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(
            propR1,
            propG1,
            propB1
        )
    }

    override fun allowsSpawning(
        state: BlockState?,
        view: BlockView?,
        pos: BlockPos?,
        type: EntityType<*>?
    ): Boolean {
        return false
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        return randomColor(super.getPlacementState(ctx)!!)
    }

    fun randomColor(initial: BlockState = stateManager.defaultState): BlockState {
        return initial.withRandomColor1Properties()
    }

}