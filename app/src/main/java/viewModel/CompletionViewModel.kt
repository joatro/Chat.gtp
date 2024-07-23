package viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gtp.entity.CompletionResponse
import CompletionInterceptor

class CompletionViewModel: ViewModel() {
    private var interceptor: CompletionInterceptor = CompletionInterceptor()
    val completionLiveData: MutableLiveData<CompletionResponse?> = MutableLiveData()
    val errorLiveData: MutableLiveData<String?> = MutableLiveData()

    fun observeCompletionLiveData(): MutableLiveData<CompletionResponse?> {
        return completionLiveData
    }

    fun observeErrorLiveData(): MutableLiveData<String?> {
        return errorLiveData
    }

    fun postCompletionLiveData(prompt: String) {
        interceptor.postCompletion(viewModelScope, prompt) { response, error ->
            if (response != null) {
                completionLiveData.postValue(response)
            } else if (error != null) {
                errorLiveData.postValue(error)
            }
        }
    }
}