package com.example.gini.data.repository
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.LiveData
import com.example.gini.data.dao.GiniDao
import com.example.gini.data.model.ImageEntity
import com.example.gini.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import android.util.Log
import com.example.gini.GiniViewModel
import okio.IOException
import retrofit2.HttpException

@Singleton
class ImageRepository @Inject constructor(
    private val giniDao: GiniDao,
    private val apiService: ApiService
) {
    private val TAG = GiniViewModel::class.java.simpleName
    val allImages: LiveData<List<ImageEntity>> = giniDao.getAllImages()

    fun refreshImageList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.fetchData("13398314-67b0a9023aca061e2950dbb5a")
                giniDao.deleteAllImages()
                response.hits.forEach { image ->
                    if(image.likes > 50 && image.comments > 50) {
                        giniDao.insert(
                            ImageEntity(
                                uri = image.largeImageURL,
                                likes = image.likes.toString(),
                                comments = image.comments.toString(),
                                uid = image.id
                            )
                        )
                    }
                }
            } catch (e: HttpException) {
                Log.e(TAG, "HTTP Error: ${e.response()?.errorBody()?.string()}")
            } catch (e: IOException) {
                Log.e(TAG, "Network Error: $e")
            } catch (e: Exception) {
                Log.e(TAG, "Error: $e")
            }
        }
    }
}