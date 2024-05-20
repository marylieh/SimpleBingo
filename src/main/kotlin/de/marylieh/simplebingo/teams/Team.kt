package de.marylieh.simplebingo.teams

import org.bukkit.Material

data class Team(val name: String, var color: Material, var members: MutableList<String> = mutableListOf())

val teamMaterials = listOf<Material>(
    Material.WHITE_WOOL,
    Material.LIGHT_GRAY_WOOL,
    Material.GRAY_WOOL,
    Material.BLACK_WOOL,
    Material.BROWN_WOOL,
    Material.RED_WOOL,
    Material.ORANGE_WOOL,
    Material.YELLOW_WOOL,
    Material.LIME_WOOL,
    Material.GREEN_WOOL,
    Material.CYAN_WOOL,
    Material.LIGHT_BLUE_WOOL,
    Material.BLUE_WOOL,
    Material.PURPLE_WOOL,
    Material.MAGENTA_WOOL,
    Material.PINK_WOOL
)
