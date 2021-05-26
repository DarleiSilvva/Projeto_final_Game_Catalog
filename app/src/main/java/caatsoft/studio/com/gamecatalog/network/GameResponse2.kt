package caatsoft.studio.com.gamecatalog.network

import com.google.gson.annotations.SerializedName

data class GameResponse2 (
        @SerializedName( "error")  val error: String,
        @SerializedName( "limit")  val limit: Int,
        @SerializedName( "offset")  val offset: Int,
        @SerializedName( "number_of_page_results")  val number_of_page_results: Int,
        @SerializedName( "number_of_total_results")  val number_of_total_results: Int,
        @SerializedName( "status_code")  val status_code: Int,
        @SerializedName( "results")  val results: List<Results2>)

data class Results2 (
        @SerializedName( "deck")  val deck: String,
        @SerializedName( "image")  val image: Image2,
        @SerializedName( "name")  val name: String,
        @SerializedName( "original_release_date")  val original_release_date: String,
        @SerializedName( "platforms")  val platforms: List<Platforms2>)

data class Image2 (
        @SerializedName( "icon_url")  val icon_url: String)

data class Platforms2 (
        @SerializedName( "name")  val name: String,
        @SerializedName( "abbreviation")  val abbreviation: String)