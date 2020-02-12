package me.chrisumb.supermod.items.onyx

import net.minecraft.item.ToolMaterial
import net.minecraft.item.ToolMaterials
import net.minecraft.recipe.Ingredient

object OnyxToolMaterial : ToolMaterial {
    private val diamond = ToolMaterials.DIAMOND

    override fun getRepairIngredient(): Ingredient {
        return Ingredient.ofItems(OnyxItem)
    }

    override fun getDurability(): Int {
        return (diamond.durability * 1.4).toInt()
    }

    override fun getAttackDamage(): Float {
        return diamond.attackDamage * 1.4f
    }

    override fun getEnchantability(): Int {
        return (diamond.enchantability * 1.4).toInt()
    }

    override fun getMiningSpeed(): Float {
        return diamond.miningSpeed * 1.4f
    }

    override fun getMiningLevel(): Int {
        return diamond.miningLevel + 1
    }
}