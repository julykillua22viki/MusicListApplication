package com.example.musiclistapplication.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musiclistapplication.LinkActivity
import com.example.musiclistapplication.R
import com.example.musiclistapplication.model.Audio
import com.example.musiclistapplication.utils.ItemTypeEnum
import kotlinx.android.synthetic.main.row_audio.view.*
import kotlinx.android.synthetic.main.row_browse.view.textView

class AudioAdapter(val context: Context) : RecyclerView.Adapter<AudioAdapter.ViewHolder>() {

    // Gets the number of animals in the list

    var items : List<Audio>

    init {
        items = ArrayList()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_audio, parent, false)
        return ViewHolder( view )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val audio = items.get( position )

        holder.textView.text = audio.text
        holder.subtitleTextView.text = audio.subtext
        holder.itemView.setOnClickListener(){

            if( audio.type != null && audio?.type?.equals( ItemTypeEnum.LINK.toString() ) )
            {
                val intent = Intent(context, LinkActivity::class.java)
                intent.putExtra(LinkActivity.URL, "" + items.get(position).URL)
                ContextCompat.startActivity(context, intent, null)
            }
            else if( audio.type != null && audio?.type?.equals( ItemTypeEnum.AUDIO.toString() ) ){
                Toast.makeText( context, holder.textView.text, Toast.LENGTH_LONG ).show()
            }
        }

        if( audio.image != null ) {

            holder.imageView.visibility = View.VISIBLE
            Glide.with(context)
                .load(audio.image)
                .into(holder.imageView)
        }
        else
        {
            holder.imageView.visibility = View.GONE
        }
    }

    fun updateList(browseList: ArrayList<Audio>?)
    {
        if (browseList != null) {
            items = browseList
        }
        notifyDataSetChanged()
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.textView
        val imageView = view.imageView
        val subtitleTextView = view.subtitleTextView
    }
}

