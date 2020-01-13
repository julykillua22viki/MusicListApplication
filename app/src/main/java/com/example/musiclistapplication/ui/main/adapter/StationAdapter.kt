package com.example.musiclistapplication.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclistapplication.AudioActivity
import com.example.musiclistapplication.R
import com.example.musiclistapplication.model.Station
import kotlinx.android.synthetic.main.row_browse.view.*

class StationAdapter(val context: Context) : RecyclerView.Adapter<StationAdapter.ViewHolder>() {

    // Gets the number of animals in the list

    var items : List<Station>

    init {
        items = ArrayList()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_station, parent, false)
        return ViewHolder( view )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items.get(position).text
        holder.itemView.setOnClickListener(){

            val intent = Intent(context, AudioActivity::class.java)
            intent.putExtra(AudioActivity.CHILDREN, items.get( position ).getChildren())
            intent.putExtra(AudioActivity.TITLE, items.get( position ).text)
            ContextCompat.startActivity(context, intent, null)
        }
    }

    fun updateList( browseList: List<Station> )
    {
        items = browseList
        notifyDataSetChanged()
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.textView
    }
}

