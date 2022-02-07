package kg.geektech.youtubeapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.geektech.youtubeapi.`object`.Constant
import kg.geektech.youtubeapi.base.BaseViewModel
import kg.geektech.youtubeapi.data.model.Playist
import kg.geektech.youtubeapi.data.remote.ApiService
import kg.geektech.youtubeapi.data.remote.RetrofitCLient
import kg.geektech.youtubeapi.BuildConfig.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitCLient.create()
    }

    fun playlists(): LiveData<Playist> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<Playist> {
        val data = MutableLiveData<Playist>()

        apiService.getPlaylists(Constant.part, Constant.channelId, API_KEY, Constant.maxResults)
            .enqueue(object : Callback<Playist> {
                override fun onResponse(call: Call<Playist>, response: Response<Playist>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                    // 200 - 299
                }

                override fun onFailure(call: Call<Playist>, t: Throwable) {
                    // 404 - not found, 401 - токен истек 400-499
                    print(t.stackTrace)
                }
            })

        return data
    }
}