package kg.geektech.youtubeapi.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kg.geektech.youtubeapi.data.model.Items
import kg.geektech.youtubeapi.databinding.MainRvItemBinding

class PlaylistAdapter(
    private val list: List<Items>,
    private val listener:OnItemClickListener
) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private lateinit var binding: MainRvItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        binding = MainRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class PlaylistViewHolder(itemView: MainRvItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(items: Items) {
            Glide.with(binding.root)
                .load(items.snippet.thumbnails.default.url)
                .into(binding.mainIv)
            binding.playlistNameTv.text = items.snippet.title
            binding.playlistCountTv.text = items.contentDetails.itemCount.toString() + "video series"
            itemView.setOnClickListener{
                listener.onItemClick(items.id)
            }
        }

    }
    interface OnItemClickListener{
        fun onItemClick(id : String)
    }
}