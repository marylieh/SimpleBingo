package de.marylieh.simplebingo.game

import java.io.File

object ResetManager {

    val world: File = File("./world")
    val worldNether: File = File("./world_nether")
    val worldEnd: File = File("./world_the_end")

    fun resetWorld(directory: File) {
        if (directory.exists() && directory.isDirectory) {
            directory.listFiles()?.forEach { file ->
                if (file.isDirectory) {
                    resetWorld(file)
                } else {
                    file.delete()
                }
            }
            directory.delete()
        }
    }
}