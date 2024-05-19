package de.marylieh.simplebingo.teams

data class Team(val name: String, var color: TeamColors, var members: MutableList<String> = mutableListOf())

enum class TeamColors {
    WHITE,
    LIGHT_GRAY,
    GRAY,
    BLACK,
    BROWN,
    RED,
    ORANGE,
    YELLOW,
    LIME,
    GREEN,
    CYAN,
    LIGHT_BLUE,
    BLUE,
    PURPLE,
    MAGENTA,
    PINK
}
