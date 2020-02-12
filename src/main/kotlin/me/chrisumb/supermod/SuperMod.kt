package me.chrisumb.supermod

import me.chrisumb.supermod.blocks.ColoredOreBlock
import me.chrisumb.supermod.blocks.onyx.OnyxOreBlock
import me.chrisumb.supermod.blocks.register
import me.chrisumb.supermod.items.ColoredOreItem
import me.chrisumb.supermod.items.onyx.OnyxItem
import me.chrisumb.supermod.items.onyx.OnyxOreItem
import me.chrisumb.supermod.items.onyx.OnyxPickaxeItem
import me.chrisumb.supermod.items.register
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.ConfiguredDecorator
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig

@Suppress("unused")
fun init() {
    OnyxItem.register("onyx")
    OnyxOreBlock.register("onyx_ore")
    OnyxOreItem.register("onyx_ore")
    ColoredOreBlock.register("colored_ore")
    ColoredOreItem.register("colored_ore")
    OnyxPickaxeItem.register("onyx_pickaxe")

    Registry.BIOME.forEach(::handleBiome)
    RegistryEntryAddedCallback.event(Registry.BIOME).register(
        RegistryEntryAddedCallback { i: Int, id: Identifier, biome: Biome -> handleBiome(biome)}
    )
}

private fun handleBiome(biome: Biome) {
    if (biome.category == Biome.Category.NETHER || biome.category == Biome.Category.THEEND) {
        return
    }

    biome.addFeature(
        GenerationStep.Feature.UNDERGROUND_ORES,
        ConfiguredFeature(
            Feature.ORE,
            OreFeatureConfig(
                OreFeatureConfig.Target.NATURAL_STONE,
                ColoredOreBlock.defaultState,
                8
            )
        ).createDecoratedFeature(
            ConfiguredDecorator(Decorator.COUNT_RANGE, RangeDecoratorConfig(8, 0, 0, 18))
        )
    )
}