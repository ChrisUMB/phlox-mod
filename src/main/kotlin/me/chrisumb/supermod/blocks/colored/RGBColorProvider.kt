package me.chrisumb.supermod.blocks.colored

import net.minecraft.block.BlockState
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView
import org.lwjgl.glfw.GLFW
import java.awt.Color

object RGBColorProvider : BlockColorProvider, ItemColorProvider {
    override fun getColor(state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int {
        return when (tintIndex) {
            0 -> state.color1
            1 -> state.color2
            else -> 0xFFFFFF
        }
    }

    override fun getColor(stack: ItemStack, tintIndex: Int): Int {
        val tag = stack.tag
        if (tag == null) {
            val time = ((GLFW.glfwGetTime() / 8.0) % 1.0).toFloat() + (tintIndex * 0.5f)
            return Color.getHSBColor(time, 0.7f, 0.7f).rgb
        }

        val redValue = tag.getByte("R${tintIndex + 1}").toInt()
        val greenValue = tag.getByte("G${tintIndex + 1}").toInt()
        val blueValue = tag.getByte("B${tintIndex + 1}").toInt()

        val r = (redValue shl 6) + ((1 shl 6) - 1)
        val g = (greenValue shl 6) + ((1 shl 6) - 1)
        val b = (blueValue shl 6) + ((1 shl 6) - 1)
        return (r shl 16) or (g shl 8) or b
    }
}