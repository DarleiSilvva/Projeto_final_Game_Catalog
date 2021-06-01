package caatsoft.studio.com.gamecatalog.adapter

import android.content.Context
import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import caatsoft.studio.com.gamecatalog.R
import caatsoft.studio.com.gamecatalog.databinding.BottomModelBinding
import caatsoft.studio.com.gamecatalog.databinding.ModelBinding
import caatsoft.studio.com.gamecatalog.network.Game
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog


class GameAdapter(private val context: Context, private val gameListData: List<Game>, private val layoutInflater:LayoutInflater):RecyclerView.Adapter<GameAdapter.GameAdapterHolder>(){

    class GameAdapterHolder (val modelBinding: ModelBinding): RecyclerView.ViewHolder(modelBinding.root){
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameAdapterHolder {
        return GameAdapterHolder(ModelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GameAdapterHolder, position: Int) {
        holder.modelBinding.nameText.text = gameListData[position].name
        Glide.with(context).load(gameListData[position].image.icon_url).into(holder.modelBinding.iconGameImage)
        if (gameListData[position].deck == null){
            holder.modelBinding.deckText.text =  context.resources.getString(R.string.description_not_informed)
        } else{
            holder.modelBinding.deckText.text = gameListData[position].deck
        }
        if (gameListData[position].original_release_date == null){
            holder.modelBinding.originalReleaseDateText.text = context.resources.getString(R.string.release_date_not_informed)
            holder.modelBinding.originalReleaseDateText.textSize = 12F
        } else{
            holder.modelBinding.originalReleaseDateText.text = gameListData[position].original_release_date
        }
        holder.modelBinding.platformsText.text = gameListData[position].platforms[0].name
        holder.modelBinding.idModelConstraint.setOnClickListener {
            //val btnsheet = layoutInflater.inflate (R.layout.bottom_model, null)
            val bottomModelBinding: BottomModelBinding = BottomModelBinding.inflate(layoutInflater)

            val dialog = BottomSheetDialog (context)
            dialog.setContentView (bottomModelBinding.root)
            bottomModelBinding.nameText.text = gameListData[position].name
            Glide.with(context).load(gameListData[position].image.icon_url).into(bottomModelBinding.iconGameImage)
            if (gameListData[position].deck == null){
                bottomModelBinding.deckText.text = context.resources.getString(R.string.description_not_informed)
            } else{
                bottomModelBinding.deckText.text = gameListData[position].deck
            }

            if (gameListData[position].original_release_date == null){
                bottomModelBinding.originalReleaseDateText.text = context.resources.getString(R.string.release_date_not_informed)
                bottomModelBinding.originalReleaseDateText.textSize = 12F
            } else{
                bottomModelBinding.originalReleaseDateText.text = gameListData[position].original_release_date
            }
            bottomModelBinding.platformsText.text = gameListData[position].platforms[0].name
            bottomModelBinding.dragImageView.setOnClickListener {
                dialog.dismiss ()
            }
            bottomModelBinding.favoriteImageView.setOnClickListener {
                bottomModelBinding.favoriteImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
                Toast.makeText(context, context.resources.getString(R.string.it_is_among_the_favourites), Toast.LENGTH_LONG).show()
            }
            dialog.show ()
        }
    }

    override fun getItemCount(): Int {
        return gameListData.size
    }
}
