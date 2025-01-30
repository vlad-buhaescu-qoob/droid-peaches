package com.example.mytestlibrary

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
public fun FoodApp() {
	val navController = rememberNavController()

	NavHost(navController, startDestination = "foodList") {
		composable("foodList") { FoodListScreen(navController) }
		composable("foodDetail/{foodName}/{ingredients}") { backStackEntry ->
			val foodName = backStackEntry.arguments?.getString("foodName")
			val ingredients = backStackEntry.arguments?.getString("ingredients")?.split(",")
			FoodDetailScreen(foodName, ingredients)
		}
	}
}