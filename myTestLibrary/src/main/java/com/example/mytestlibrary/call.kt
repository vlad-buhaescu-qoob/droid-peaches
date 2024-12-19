
import okhttp3.*
import okio.IOException

public class ServerCallExample {
	suspend fun main() {
		val client = OkHttpClient()

		val request = Request.Builder()
			.url("https://jsonplaceholder.typicode.com/posts")
			.build()

		client.newCall(request).enqueue(object : Callback {
			override fun onFailure(call: Call, e: IOException) {
				println("Request failed: $e")
			}

			override fun onResponse(call: Call, response: Response) {
				if (response.isSuccessful) {
					val responseBody = response.body?.string()
					println("Response from server: $responseBody")
				} else {
					println("Unsuccessful response: ${response.code}")
				}
			}
		})
	}
}