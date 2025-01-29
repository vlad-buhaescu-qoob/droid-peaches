package com.example.mytestlibrary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// 3️⃣ Food List Screen
@Composable
fun FoodListScreen(navController: NavController) {
	LazyColumn(modifier = Modifier.padding(16.dp)) {
		items(foodList) { food ->
			Card(
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 8.dp)
					.clickable {
						navController.navigate("foodDetail/${food.name}/${food.ingredients.joinToString(",")}")
					},
				elevation = CardDefaults.cardElevation(4.dp)
			) {
				Row(
					modifier = Modifier.padding(16.dp),
					verticalAlignment = Alignment.CenterVertically
				) {
					Text(
						text = food.name,
						fontWeight = FontWeight.Bold,
						modifier = Modifier.weight(1f)
					)
				}
			}
		}
	}
}
