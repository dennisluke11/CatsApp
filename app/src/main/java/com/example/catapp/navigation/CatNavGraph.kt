package com.example.catapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.catapp.data.local.CatEntity
import com.example.catapp.ui.cats.CatDetailScreen
import com.example.catapp.ui.cats.CatListScreen
import com.example.catapp.ui.cats.CatViewModel
import com.google.gson.Gson

@Composable
fun CatNavGraph(viewModel: CatViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "catList") {
        composable("catList") {
            CatListScreen(viewModel, onCatClick = { cat ->
                val catJson = Gson().toJson(cat)
                navController.navigate("catDetail/$catJson")
            })
        }

        composable(
            route = "catDetail/{cat}",
            arguments = listOf(navArgument("cat") { type = NavType.StringType })
        ) { backStackEntry ->
            val catJson = backStackEntry.arguments?.getString("cat") ?: ""
            val cat = Gson().fromJson(catJson, CatEntity::class.java)
            CatDetailScreen(
                cat = cat,
                onBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}