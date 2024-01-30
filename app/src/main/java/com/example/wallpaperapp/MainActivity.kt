package com.example.wallpaperapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.example.wallpaperapp.ui.theme.WallpaperAppTheme
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retroSF = Retrofit.Builder()
            .baseUrl("https://unsplash.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api = retroSF.create(Scrapper::class.java)
        val scraper = MainScraper(api)
        val display = SnapshotStateList<Wallpaper>()

        setContent {
            WallpaperAppTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                }
            }
        }
    }
}


interface Scrapper {
    @GET
    suspend fun getAsura(@Url url: String): Response<String>
}




















