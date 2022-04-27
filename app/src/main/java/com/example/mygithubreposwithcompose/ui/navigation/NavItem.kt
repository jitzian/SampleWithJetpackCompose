package com.example.mygithubreposwithcompose.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList()
) {
    val route = run {
        //baseRoute/{arg1}/{arg2}

        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    object Main : NavItem("main")
    object Detail : NavItem("detail", listOf(NavArg.MediaId)) {
        fun createNavRoute(mediaId: Int) = "$baseRoute/$mediaId"
        //fun createNavRoute(user: String, id: Int) = "$baseRoute/$user/$id"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    MediaId("mediaId", NavType.IntType),
    User("user", NavType.StringType),
    RepoId("repoId", NavType.IntType)
}