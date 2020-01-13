package com.example.musiclistapplication.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclistapplication.StationActivity
import com.example.musiclistapplication.R
import com.example.musiclistapplication.model.Link
import com.example.musiclistapplication.utils.ItemTypeEnum
import kotlinx.android.synthetic.main.row_browse.view.*

class LinkAdapter(val context: Context) : RecyclerView.Adapter<LinkAdapter.ViewHolder>() {

    // Gets the number of animals in the list

    var items : List<Link>

    init {
        items = ArrayList()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_link, parent, false)
        return ViewHolder( view )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items.get(position).text
        holder.itemView.setOnClickListener(){

            if( items.get( position ).type != null && !items.get( position ).type.equals( ItemTypeEnum.TEXT.toString() ) ) {
                val intent = Intent(context, StationActivity::class.java)
                intent.putExtra(StationActivity.URL, "" + items.get(position).URL)
                ContextCompat.startActivity(context, intent, null)
            }
        }
    }

    fun updateList( browseList: List<Link> )
    {
        items = browseList
        notifyDataSetChanged()
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.textView
    }
}

