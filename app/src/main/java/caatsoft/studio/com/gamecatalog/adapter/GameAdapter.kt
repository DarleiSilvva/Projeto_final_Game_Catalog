package caatsoft.studio.com.gamecatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import caatsoft.studio.com.gamecatalog.R
import caatsoft.studio.com.gamecatalog.databinding.ModelBinding
import caatsoft.studio.com.gamecatalog.network.model.Game
import com.bumptech.glide.Glide


class GameAdapter(private val gameListData: List<Game>, private val gameClickListener:GameClickListener):RecyclerView.Adapter<GameAdapter.GameAdapterHolder>(){

    class GameAdapterHolder (val modelBinding: ModelBinding): RecyclerView.ViewHolder(modelBinding.root){
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameAdapterHolder {
        return GameAdapterHolder(ModelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GameAdapterHolder, position: Int) {
        val context = holder.modelBinding.root.context
        holder.modelBinding.nameText.text = gameListData[position].name
        Glide.with(context).load(gameListData[position].image.screen_url).into(holder.modelBinding.iconGameImage)

        if (gameListData[position].original_release_date == null){
            holder.modelBinding.originalReleaseDateText.text = context.resources.getString(R.string.release_date_not_informed)
            holder.modelBinding.originalReleaseDateText.textSize = 12F
        } else{
            holder.modelBinding.originalReleaseDateText.text = gameListData[position].original_release_date
        }
        holder.modelBinding.platformsText.text = gameListData[position].platforms[0].name
        holder.modelBinding.idModelConstraint.setOnClickListener {
            gameClickListener.onGameClicked(gameListData[position])
        }
    }

    override fun getItemCount(): Int {
        return gameListData.size
    }

    interface GameClickListener{
        fun onGameClicked(game: Game)
    }
}
