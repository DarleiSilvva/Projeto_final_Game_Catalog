package caatsoft.studio.com.gamecatalog.network

import com.google.gson.annotations.SerializedName

data class GameResponse (
        @SerializedName( "error")  val error: String,
        @SerializedName( "limit")  val limit: Int,
        @SerializedName( "offset")  val offset: Int,
        @SerializedName( "number_of_page_results")  val number_of_page_results: Int,
        @SerializedName( "number_of_total_results")  val number_of_total_results: Int,
        @SerializedName( "status_code")  val status_code: Int,
        @SerializedName( "results")  val results: List<Game>)

data class Game (
        @SerializedName( "deck")  val deck: String,
        @SerializedName( "image")  val image: Image,
        @SerializedName( "name")  val name: String,
        @SerializedName( "original_release_date")  val original_release_date: String,
        @SerializedName( "platforms")  val platforms: List<Platforms>)

data class Image (
        @SerializedName( "icon_url")  val icon_url: String,
        @SerializedName( "medium_url")  val medium_url: String,
        @SerializedName( "screen_url")  val screen_url: String,
        @SerializedName( "screen_large_url")  val screen_large_url: String,
        @SerializedName( "small_url")  val small_url: String,
        @SerializedName( "super_url")  val super_url: String,
        @SerializedName( "thumb_url")  val thumb_url: String,
        @SerializedName( "tiny_url")  val tiny_url: String,
        @SerializedName( "original_url")  val original_url: String,
        @SerializedName( "image_tags")  val image_tags: String)

data class Platforms (
        @SerializedName( "api_detail_url")  val api_detail_url: String,
        @SerializedName( "id")  val id: Int,
        @SerializedName( "name")  val name: String,
        @SerializedName( "site_detail_url")  val site_detail_url: String,
        @SerializedName( "abbreviation")  val abbreviation: String)