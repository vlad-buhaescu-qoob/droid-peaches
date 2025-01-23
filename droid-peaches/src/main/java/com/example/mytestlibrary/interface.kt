package com.example.mytestlibrary

import ServerCallExample
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class Interface {

	public fun callTheLibrary() {
		val scope = CoroutineScope(Dispatchers.IO)
		scope.launch {
			ServerCallExample().call(onSuccess = {
				println(it)
			}, onFailure = {
				println(it)
			})
		}
	}
}