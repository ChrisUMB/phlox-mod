package me.chrisumb.supermod.items.colored

import me.chrisumb.supermod.blocks.colored.ColoredOreBlock
import me.chrisumb.supermod.blocks.colored.RGBColorProvider
import me.chrisumb.supermod.items.superItemSettings
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.item.BlockItem

object ColoredOreItem: BlockItem(ColoredOreBlock, superItemSettings) {
    init {
        ColorProviderRegistry.ITEM.register(RGBColorProvider, this)
    }
}