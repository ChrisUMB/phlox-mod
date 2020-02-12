package me.chrisumb.supermod.blocks

import net.minecraft.block.Block
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

fun Block.register(id: String) {
    Registry.register(Registry.BLOCK, Identifier("supermod", id), this)
}