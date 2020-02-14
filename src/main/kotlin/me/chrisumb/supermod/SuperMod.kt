package me.chrisumb.supermod

import me.chrisumb.supermod.blocks.colored.*
import me.chrisumb.supermod.blocks.onyx.OnyxOreBlock
import me.chrisumb.supermod.blocks.register
import me.chrisumb.supermod.items.colored.*
import me.chrisumb.supermod.items.onyx.OnyxItem
import me.chrisumb.supermod.items.onyx.OnyxOreItem
import me.chrisumb.supermod.items.onyx.OnyxPickaxeItem
import me.chrisumb.supermod.items.register
import me.chrisumb.supermod.world.feature.ColoredOreFeature
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.ConfiguredDecorator
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.ConfiguredFeature

@Suppress("unused")
fun init() {
    OnyxItem.register("onyx")
    OnyxOreBlock.register("onyx_ore")
    OnyxOreItem.register("onyx_ore")
    ColoredOreBlock.register("colored_ore")
    ColoredOreItem.register("colored_ore")
    ColoredLogBlock.register("colored_log")
    ColoredLogItem.register("colored_log")
    ColoredPlanksBlock.register("colored_planks")
    ColoredPlanksItem.register("colored_planks")
    ColoredLeavesBlock.register("colored_leaves")
    ColoredLeavesItem.register("colored_leaves")
    ColoredDirtBlock.register("colored_dirt")
    ColoredDirtItem.register("colored_dirt")
    ColoredGrassBlock.register("colored_grass")
    ColoredGrassItem.register("colored_grass")
    OnyxPickaxeItem.register("onyx_pickaxe")

    Registry.BIOME.forEach(::handleBiome)
    RegistryEntryAddedCallback.event(Registry.BIOME).register(
        RegistryEntryAddedCallback { i: Int, id: Identifier, biome: Biome -> handleBiome(biome) }
    )

    BlockRenderLayerMap.INSTANCE.putBlock(ColoredOreBlock, RenderLayer.getCutout())
    BlockRenderLayerMap.INSTANCE.putBlock(ColoredLogBlock, RenderLayer.getCutout())
    BlockRenderLayerMap.INSTANCE.putBlock(ColoredLeavesBlock, RenderLayer.getCutout())
    BlockRenderLayerMap.INSTANCE.putBlock(ColoredGrassBlock, RenderLayer.getCutout())
    BlockRenderLayerMap.INSTANCE.putBlock(ColoredDirtBlock, RenderLayer.getCutout())
}

private fun handleBiome(biome: Biome) {
    if (biome.category == Biome.Category.NETHER || biome.category == Biome.Category.THEEND) {
        return
    }

    biome.addFeature(
        GenerationStep.Feature.UNDERGROUND_ORES,
        ConfiguredFeature(
            ColoredOreFeature,
            ColoredOreFeature.Config()
        ).createDecoratedFeature(
            ConfiguredDecorator(Decorator.COUNT_RANGE, RangeDecoratorConfig(512, 0, 0, 48))
        )
    )
//    biome.addFeature(
//        GenerationStep.Feature.UNDERGROUND_ORES,
//        ConfiguredFeature(
//            Feature.ORE,
//            OreFeatureConfig(
//                OreFeatureConfig.Target.NATURAL_STONE,
//                ColoredOreBlock.defaultState,
//                8
//            )
//        ).createDecoratedFeature(
//            ConfiguredDecorator(Decorator.COUNT_RANGE, RangeDecoratorConfig(64, 0, 0, 128))
//        )
//    )
}