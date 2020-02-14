package me.chrisumb.supermod.items.colored

import me.chrisumb.supermod.blocks.colored.ColoredGrassBlock
import me.chrisumb.supermod.blocks.colored.RGBColorProvider
import me.chrisumb.supermod.items.superItemSettings
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.item.BlockItem

object ColoredGrassItem : BlockItem(ColoredGrassBlock, superItemSettings) {
    init {
        ColorProviderRegistry.ITEM.register(RGBColorProvider, this)
    }
}