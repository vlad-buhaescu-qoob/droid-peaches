
import okhttp3.*
import okio.IOException

class ServerCallExample {

	suspend fun call(
		onSuccess: (String?) -> Unit,
		onFailure: (String?) -> Unit
	) {
		main(onSuccess = {
			onSuccess(it)
		}, onFailure = {
			onFailure(it)
		})
	}

	suspend fun main(onSuccess: (String?) -> Unit,
					 onFailure: (String?) -> Unit) {
		val client = OkHttpClient()

		val request = Request.Builder()
			.url("https://jsonplaceholder.typicode.com/posts")
			.build()

		client.newCall(request).enqueue(object : Callback {
			override fun onFailure(call: Call, e: IOException) {
				onFailure(e.localizedMessage)
				println("Request failed: $e")
			}

			override fun onResponse(call: Call, response: Response) {
				if (response.isSuccessful) {
					val responseBody = response.body?.string()
					println("Response from server: $responseBody")
					onSuccess(responseBody)
				} else {
					println("Unsuccessful response: ${response.code}")
				}
			}
		})
	}
}