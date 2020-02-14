package me.chrisumb.supermod.blocks.colored

import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.loot.context.LootContext
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView

object ColoredLogBlock : LogBlock(
    MaterialColor.WOOD,
    Settings.of(Material.WOOD).strength(2.0f, 3.0f)
) {

    init {
        ColorProviderRegistry.BLOCK.register(RGBColorProvider, this)

        defaultState = stateManager.defaultState
            .with(PillarBlock.AXIS, Direction.Axis.Y)
            .withColor1Properties(3, 3, 3)
            .withColor2Properties(3, 3, 3)
    }

    override fun getSoundGroup(state: BlockState): BlockSoundGroup {
        return BlockSoundGroup.WOOD
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(
            propR1,
            propG1,
            propB1,
            propR2,
            propG2,
            propB2
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
        tag.putByte("R2", state[propR2].toByte())
        tag.putByte("G2", state[propG2].toByte())
        tag.putByte("B2", state[propB2].toByte())
        stack.tag = tag
        return stack
    }

    override fun getPickStack(world: BlockView, pos: BlockPos, state: BlockState): ItemStack {
        return getItemStack(state)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val tag = ctx.stack.tag ?: return randomColor().with(PillarBlock.AXIS, ctx.side.axis)
        val r1 = tag.getByte("R1").toInt()
        val g1 = tag.getByte("G1").toInt()
        val b1 = tag.getByte("B1").toInt()
        val r2 = tag.getByte("R2").toInt()
        val g2 = tag.getByte("G2").toInt()
        val b2 = tag.getByte("B2").toInt()
        return stateManager.defaultState
            .withColor1Properties(r1, g1, b1)
            .withColor2Properties(r2, g2, b2)
            .with(PillarBlock.AXIS, ctx.side.axis)
    }

    fun randomColor(): BlockState {
        return stateManager.defaultState.withRandomColor1Properties().withRandomColor2Properties()
    }

}