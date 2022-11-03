package com.example.mynavigationsample.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynavigationsample.models.Movie
import com.example.mynavigationsample.network.MovieService
import com.example.mynavigationsample.network.MyListResponse
import com.example.mynavigationsample.network.MyResponse
import com.example.mynavigationsample.network.RetrofitInstance
import com.example.mynavigationsample.utils.Constants
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    fun getListOfMoviesFromRemoteDb(): List<Movie> {
        viewModelScope.launch {
            try {
                val retrofitInstance = RetrofitInstance
                    .getRetrofitInstance()
                    .create(MovieService::class.java)
                val response: MyListResponse<Movie> =
                    retrofitInstance.getAllMovies(Constants.STUDENT_ID)
                val movies = response.data
                if (movies != null) {
                    for (movie in movies){
                        Log.d("Movie_item: ", movie.toString())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return emptyList() //todo
    }
}