package com.example.mytestlibrary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodDetailScreen(foodName: String?, ingredients: List<String>?) {
	Column(modifier = Modifier.padding(16.dp)) {
		Text(text = foodName ?: "Unknown Food", style = MaterialTheme.typography.headlineLarge)

		Spacer(modifier = Modifier.height(16.dp))

		Text(text = "Ingredients:", style = MaterialTheme.typography.headlineSmall)

		ingredients?.forEach { ingredient ->
			Text(text = "• $ingredient", style = MaterialTheme.typography.bodyLarge)
		}
	}
}
