package me.chrisumb.supermod.blocks.colored

import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.OreBlock
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.loot.context.LootContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView

object ColoredOreBlock : OreBlock(Settings.of(Material.STONE).strength(3.0f, 3.0f)) {

    init {
        ColorProviderRegistry.BLOCK.register(RGBColorProvider, this)

        defaultState = stateManager.defaultState
            .withColor1Properties(3, 3, 3)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(
            propR1,
            propG1,
            propB1
        )
    }

    override fun getDroppedStacks(state: BlockState, builder: LootContext.Builder): MutableList<ItemStack> {
        return mutableListOf(getItemStack(state))
    }

    fun getItemStack(state: BlockState): ItemStack {
        val stack = ItemStack(this)
        val tag = stack.orCreateTag
        tag.putByte("R1", state[propR1].toByte())
        tag.putByte("G1", state[propG1].toByte())
        tag.putByte("B1", state[propB1].toByte())
        stack.tag = tag
        return stack
    }

    override fun getPickStack(world: BlockView, pos: BlockPos, state: BlockState): ItemStack {
        return getItemStack(state)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val tag = ctx.stack.tag ?: return randomColor()
        return stateManager.defaultState
            .withColor1Properties(tag.getByte("R1").toInt(), tag.getByte("G1").toInt(), tag.getByte("B1").toInt())
    }

    fun randomColor(): BlockState {
        return stateManager.defaultState.withRandomColor1Properties()
    }

}