package me.chrisumb.supermod.items.colored

import me.chrisumb.supermod.blocks.colored.ColoredDirtBlock
import me.chrisumb.supermod.blocks.colored.RGBColorProvider
import me.chrisumb.supermod.items.superItemSettings
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.item.BlockItem

object ColoredDirtItem : BlockItem(ColoredDirtBlock, superItemSettings) {
    init {
        ColorProviderRegistry.ITEM.register(RGBColorProvider, this)
    }
}