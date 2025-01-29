package com.example.mytestlibrary

data class FoodItem(val name: String, val ingredients: List<String>)

val foodList = listOf(
	FoodItem("Pizza", listOf("Dough", "Cheese", "Tomato Sauce", "Pepperoni")),
	FoodItem("Burger", listOf("Bun", "Beef Patty", "Lettuce", "Cheese", "Tomato")),
	FoodItem("Pasta", listOf("Pasta", "Olive Oil", "Garlic", "Parmesan")),
	FoodItem("Sushi", listOf("Rice", "Nori", "Fish", "Soy Sauce", "Wasabi"))
)