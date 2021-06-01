package caatsoft.studio.com.gamecatalog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import caatsoft.studio.com.gamecatalog.databinding.ModelBinding
import caatsoft.studio.com.gamecatalog.network.Game
import com.bumptech.glide.Glide


class GameAdapter(val context: Context, val gameListData: List<Game>):RecyclerView.Adapter<GameAdapter.GameAdapterHolder>(){

    class GameAdapterHolder (val binding: ModelBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameAdapterHolder {
        return GameAdapterHolder(ModelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GameAdapterHolder, position: Int) {
        holder.binding.nameText.text = gameListData[position].name
        Glide.with(context).load(gameListData[position].image.icon_url).into(holder.binding.iconGameImage)
        holder.binding.deckText.text = gameListData[position].deck
        if (gameListData[position].original_release_date == null){
            holder.binding.originalReleaseDateText.text = "Data de lançamento não informada!"
            holder.binding.originalReleaseDateText.textSize = 12F
        } else{
            holder.binding.originalReleaseDateText.text = gameListData[position].original_release_date
        }
        holder.binding.platformsText.text = gameListData[position].platforms[0].name
    }

    override fun getItemCount(): Int {
        return gameListData.size
    }
}
