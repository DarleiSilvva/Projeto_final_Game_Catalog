package caatsoft.studio.com.gamecatalog.network.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "favorite_games")
class FavoriteGame(
    @PrimaryKey
    val name: String,
    val deck: String,
    val original_release_date: String,
    @ColumnInfo(name = "platformsName")
    val platforms: String,
    @ColumnInfo(name = "imageUrl")
    val image: String
) : Parcelable