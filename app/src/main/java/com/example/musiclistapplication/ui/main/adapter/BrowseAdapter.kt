package com.example.musiclistapplication.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclistapplication.LinkActivity
import com.example.musiclistapplication.R
import com.example.musiclistapplication.model.Browse
import com.example.musiclistapplication.utils.ItemTypeEnum
import kotlinx.android.synthetic.main.row_browse.view.*


class BrowseAdapter(val context: Context) : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    // Gets the number of animals in the list

    var items : List<Browse>

    init {
        items = ArrayList()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_browse, parent, false)
        return ViewHolder( view )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items.get(position).text
        holder.itemView.setOnClickListener(){

            if( !items.get( position ).type.equals( ItemTypeEnum.TEXT.toString() ) )
            {
                val intent = Intent(context, LinkActivity::class.java)
                intent.putExtra(LinkActivity.URL, "" + items.get(position).URL)
                startActivity(context, intent, null)
            }

        }
    }

    fun updateList( browseList: List<Browse> )
    {
        items = browseList
        notifyDataSetChanged()
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.textView
    }
}

