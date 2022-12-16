package com.github.jobscolin.mvpplugin.services

import com.intellij.openapi.project.Project
import com.github.jobscolin.mvpplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
