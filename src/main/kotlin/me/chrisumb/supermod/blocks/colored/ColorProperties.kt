package me.chrisumb.supermod.blocks.colored

import net.minecraft.block.BlockState
import net.minecraft.state.property.IntProperty
import kotlin.random.Random

val propR1: IntProperty = IntProperty.of("r1", 0, 3)
val propG1: IntProperty = IntProperty.of("g1", 0, 3)
val propB1: IntProperty = IntProperty.of("b1", 0, 3)

val propR2 = IntProperty.of("r2", 0, 3)
val propG2 = IntProperty.of("g2", 0, 3)
val propB2 = IntProperty.of("b2", 0, 3)

val BlockState.color1: Int
    get() = getColor(propR1, propG1, propB1)


val BlockState.color2: Int
    get() = getColor(propR2, propG2, propB2)

fun BlockState.getColor(propR: IntProperty, propG: IntProperty, propB: IntProperty): Int {
    val r = (this[propR] shl 6) + ((1 shl 6) - 1)
    val g = (this[propG] shl 6) + ((1 shl 6) - 1)
    val b = (this[propB] shl 6) + ((1 shl 6) - 1)
    return (r shl 16) or (g shl 8) or b
}

fun BlockState.withColor1Properties(r: Int, g: Int, b: Int): BlockState {
    return with(propR1, r).with(propG1, g).with(propB1, b)
}

fun BlockState.withColor2Properties(r: Int, g: Int, b: Int): BlockState {
    return with(propR2, r).with(propG2, g).with(propB2, b)
}

fun BlockState.withRandomColor1Properties(): BlockState {
    return withColor1Properties(Random.nextInt(4), Random.nextInt(4), Random.nextInt(4))
}

fun BlockState.withRandomColor2Properties(): BlockState {
    return withColor2Properties(Random.nextInt(4), Random.nextInt(4), Random.nextInt(4))
}