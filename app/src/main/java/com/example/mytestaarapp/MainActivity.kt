package com.example.mytestaarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.mytestaarapp.ui.theme.MyTestAARAppTheme
import com.example.mytestlibrary.FoodApp

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()


		setContent {
			MyTestAARAppTheme {
				Surface(color = MaterialTheme.colorScheme.background) {
					FoodApp()
				}
			}
		}
	}
}