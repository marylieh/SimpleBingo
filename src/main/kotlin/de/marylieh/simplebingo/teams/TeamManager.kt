package de.marylieh.simplebingo.teams

import de.marylieh.simplebingo.game.GamestateManager

object TeamManager {

    fun createTeam(name: String, color: TeamColors) {

        if (GamestateManager.teams.first { it.name == name }.name.equals(name, true)) {
            return
        }

        GamestateManager.teams.add(Team(name, color))
    }

    fun removeTeam(name: String) {

        if (!(GamestateManager.teams.first { it.name == name }.name.equals(name, true))) {
            return
        }

        GamestateManager.teams.remove(GamestateManager.teams.first { it.name == name })

    }

    fun addTeamMember(teamName: String, playerName: String) {

        if (!(GamestateManager.teams.first { it.name == teamName }.name.equals(teamName, true))) {
            return
        }

        GamestateManager.teams.first { it.name == teamName }.members.add(playerName)

    }

    fun removeTeamMember(teamName: String, playerName: String) {

        if (!(GamestateManager.teams.first { it.name == teamName }.name.equals(teamName, true))) {
            return
        }

        GamestateManager.teams.first { it.name == teamName }.members.remove(playerName)

    }

    fun getTeamMembers(teamName: String): List<String> {

        if (!GamestateManager.teams.first { it.name == teamName }.name.equals(teamName, true)) {
            return emptyList()
        }

        return GamestateManager.teams.first { it.name == teamName }.members

    }

}