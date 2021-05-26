package caatsoft.studio.com.gamecatalog.network

import com.google.gson.annotations.SerializedName

data class GameResponse (
        @SerializedName( "error")  val error: String,
        @SerializedName( "limit")  val limit: Int,
        @SerializedName( "offset")  val offset: Int,
        @SerializedName( "number_of_page_results")  val number_of_page_results: Int,
        @SerializedName( "number_of_total_results")  val number_of_total_results: Int,
        @SerializedName( "status_code")  val status_code: Int,
        @SerializedName( "results")  val results: List<Results>)

data class Results (
        @SerializedName( "aliases")  val aliases: String,
        @SerializedName( "api_detail_url")  val api_detail_url: String,
        @SerializedName( "date_added")  val date_added: String,
        @SerializedName( "date_last_updated")  val date_last_updated: String,
        @SerializedName( "deck")  val deck: String,
        @SerializedName( "description")  val description: String,
        @SerializedName( "expected_release_day")  val expected_release_day: String,
        @SerializedName( "expected_release_month")  val expected_release_month: String,
        @SerializedName( "expected_release_quarter")  val expected_release_quarter: String,
        @SerializedName( "expected_release_year")  val expected_release_year: String,
        @SerializedName( "guid")  val guid: String,
        @SerializedName( "id")  val id: Int,
        @SerializedName( "image")  val image: Image,
        @SerializedName( "image_tags")  val image_tags: List<Image_tags>,
        @SerializedName( "name")  val name: String,
        @SerializedName( "number_of_user_reviews")  val number_of_user_reviews: Int,
        @SerializedName( "original_game_rating")  val original_game_rating: List<Original_game_rating>,
        @SerializedName( "original_release_date")  val original_release_date: String,
        @SerializedName( "platforms")  val platforms: List<Platforms>,
        @SerializedName( "site_detail_url")  val site_detail_url: String)

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

data class Image_tags (
        @SerializedName( "api_detail_url")  val api_detail_url: String,
        @SerializedName( "name")  val name: String,
        @SerializedName( "total")  val total: Int)

data class Original_game_rating (
        @SerializedName( "api_detail_url")  val api_detail_url: String,
        @SerializedName( "id")  val id: Int,
        @SerializedName( "name")  val name: String)

data class Platforms (
        @SerializedName( "api_detail_url")  val api_detail_url: String,
        @SerializedName( "id")  val id: Int,
        @SerializedName( "name")  val name: String,
        @SerializedName( "site_detail_url")  val site_detail_url: String,
        @SerializedName( "abbreviation")  val abbreviation: String)