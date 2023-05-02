package com.guilhermereisdev.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.guilhermereisdev.retrofitdemo.api.AlbumService
import com.guilhermereisdev.retrofitdemo.api.RetrofitInstance
import com.guilhermereisdev.retrofitdemo.model.Albums
import com.guilhermereisdev.retrofitdemo.model.AlbumsItem
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvAlbums: TextView = findViewById(R.id.tv_albums)

        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getAlbums()
//            Exemplo usando Query Parameter
//            val response = retService.getAlbumsFromUserID(3)
            emit(response)
        }

        responseLiveData.observe(this) {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val result = "Album ID: ${albumsItem.id}\n" +
                            "Album title: ${albumsItem.title}\n" +
                            "User ID: ${albumsItem.userId}\n\n"
                    tvAlbums.append(result)
                }
            }
        }

////        Exemplo usando Path Parameter
//        val responseLiveDataPath: LiveData<Response<AlbumsItem>> = liveData {
//            val response = retService.getSingleAlbum(7)
//            emit(response)
//        }
//
//        responseLiveDataPath.observe(this) {
//            val result = "Album ID: ${it.body()?.id}\n" +
//                    "Album title: ${it.body()?.title}\n" +
//                    "User ID: ${it.body()?.userId}\n\n"
//            tvAlbums.append(result)
//        }
    }
}
