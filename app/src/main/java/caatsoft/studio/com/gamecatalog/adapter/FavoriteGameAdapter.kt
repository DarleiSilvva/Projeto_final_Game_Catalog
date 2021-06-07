package caatsoft.studio.com.gamecatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import caatsoft.studio.com.gamecatalog.R
import caatsoft.studio.com.gamecatalog.databinding.ModelBinding
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame
import com.bumptech.glide.Glide


class FavoriteGameAdapter(private val gameListData: List<FavoriteGame>, private val gameClickListener:GameClickListener):RecyclerView.Adapter<FavoriteGameAdapter.GameAdapterHolder>(){

    class GameAdapterHolder (val modelBinding: ModelBinding): RecyclerView.ViewHolder(modelBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameAdapterHolder {
        return GameAdapterHolder(ModelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GameAdapterHolder, position: Int) {
        val context = holder.modelBinding.root.context
        val game = gameListData[position]

        holder.modelBinding.nameText.text = game.name
        Glide.with(context).load(game.image).into(holder.modelBinding.iconGameImage)

        if (game.original_release_date == null){
            holder.modelBinding.originalReleaseDateText.text = context.resources.getString(R.string.release_date_not_informed)
            holder.modelBinding.originalReleaseDateText.textSize = 12F
        } else{
            holder.modelBinding.originalReleaseDateText.text = game.original_release_date
        }
        holder.modelBinding.platformsText.text = game.platforms
        holder.modelBinding.idModelConstraint.setOnClickListener {
            gameClickListener.onGameClicked(game)
        }
    }

    override fun getItemCount(): Int {
        return gameListData.size
    }

    interface GameClickListener{
        fun onGameClicked(favoriteGame: FavoriteGame)
    }
}
