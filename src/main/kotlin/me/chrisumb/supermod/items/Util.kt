package me.chrisumb.supermod.items

import me.chrisumb.supermod.items.onyx.OnyxItem
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

val superItemGroup = FabricItemGroupBuilder.build(Identifier("supermod", "general")) {
    return@build ItemStack(OnyxItem)
}

val superItemSettings = Item.Settings().group(superItemGroup)

fun Item.register(id: String) {
    Registry.register(Registry.ITEM, Identifier("supermod", id), this)
}