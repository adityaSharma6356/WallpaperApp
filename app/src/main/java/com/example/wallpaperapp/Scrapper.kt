package com.example.wallpaperapp

import android.util.Log
import org.jsoup.Jsoup

class MainScraper(
    private val api: Scrapper,
) {


    suspend fun getWallpapers(): List<Wallpaper>{
        val response = api.getAsura("")
        if(response.isSuccessful && response.body()!=null){

            val data  = Jsoup.parse(response.body())

            val images = data.select("img.tB6UZ")
            val returnData = mutableListOf<Wallpaper>()
            images.forEach {  img ->
                val imageLink = img.attr("src")
                val name = img.attr("alt")
                returnData.add(
                    Wallpaper(
                        image = imageLink,
                        name =name
                    )
                )
            }

            returnData.forEach {
                Log.d("wallpapertag", it.toString())
            }
            return returnData


        }
        return listOf()
    }

    suspend fun searchWallpaper(query:String): List<Wallpaper>{
        val response = api.getAsura(query)
        if(response.isSuccessful && response.body()!=null){

            val data  = Jsoup.parse(response.body())

            val images = data.select("img.tB6UZ")
            val returnData = mutableListOf<Wallpaper>()
            images.forEach {  img ->
                val imageLink = img.attr("src")
                val name = img.attr("alt")
                returnData.add(
                    Wallpaper(
                        image = imageLink,
                        name =name
                    )
                )
            }


            return returnData


        }
        return listOf()
    }
}


data class Wallpaper(

    val image: String = "",
    val name: String = "",

)