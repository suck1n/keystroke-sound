package com.github.suck1n.keystrokesound.services

import com.intellij.openapi.project.Project
import com.github.suck1n.keystrokesound.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
