package com.example.viceriseidorteste.presentation.navigation

enum class NavigationEnum(val route: String) {
    UserList("user_list"),
    UserDetail("user_detail/{userId}");

    fun withArgs(vararg args: Any): String {
        return args.fold(route) { acc, arg ->
            acc.replaceFirst(Regex("\\{.*?\\}"), arg.toString())
        }
    }
}