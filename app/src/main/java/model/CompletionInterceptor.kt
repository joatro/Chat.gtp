import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import service.CompletionService
import viewModel.RetrofitInstance
import gtp.entity.CompletionData
import gtp.entity.CompletionResponse
import gtp.entity.Message

class CompletionInterceptor {
    fun postCompletion(scope: CoroutineScope, prompt: String, callback: (CompletionResponse?, String?) -> Unit) {
        val service = RetrofitInstance.getRetroInstance().create(CompletionService::class.java)
        scope.launch(Dispatchers.IO) {
            try {
                var message = Message(content = prompt, role = "user")
                val data = CompletionData(List(1) { message }, "gpt-3.5-turbo")
                val response = service.getCompletion(data, "Barer- you API key here-")
                launch(Dispatchers.Main) {
                    callback(response, null)
                }
            } catch (e: HttpException) {
                launch(Dispatchers.Main) {
                    if (e.code() == 429) {
                        callback(null, "Insufficient quota. Please check your API usage.")
                    } else {
                        callback(null, "HTTP error: ${e.message()}")
                    }
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    callback(null, "Non-HTTP error: ${e.message}")
                }
            }
        }
    }
}

