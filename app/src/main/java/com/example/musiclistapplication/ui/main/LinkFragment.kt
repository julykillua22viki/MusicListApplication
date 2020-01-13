package com.example.musiclistapplication.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclistapplication.R
import com.example.musiclistapplication.model.OpmlLink
import com.example.musiclistapplication.persistence.OpmlApi
import com.example.musiclistapplication.persistence.RetrofitService
import com.example.musiclistapplication.ui.main.adapter.LinkAdapter
import retrofit2.Call
import retrofit2.Response


class LinkFragment : Fragment() {

    companion object {

        val URL = "url"

        fun newInstance(url: String ): LinkFragment
        {
            val f = LinkFragment()
            val args = Bundle()
            args.putString(URL, url)
            f.setArguments(args)
            return f
        }
    }

    private lateinit var service: OpmlApi
    private lateinit var browseItemAdapter: LinkAdapter

    init {
        service = RetrofitService.createService(OpmlApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.link_fragment, container, false)
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        browseItemAdapter = LinkAdapter(context!!)


        recyclerView.adapter = browseItemAdapter
        recyclerView.layoutManager = LinearLayoutManager( context!! )

        val call = service.getListByCategory( arguments?.getString( URL ), "json" );

        call.enqueue(object : retrofit2.Callback<OpmlLink?> {

            override fun onResponse(call: Call<OpmlLink?>,
                                    response: Response<OpmlLink?>
            ) {
                if (response.isSuccessful){
                    Log.d("API Call Success","Response: ${response.toString()}")

                    val opml : OpmlLink = response.body()!!
                    browseItemAdapter.updateList( opml.body!! )
                    activity?.setTitle( opml.head?.title )
                }
                else
                {
                    Log.e("API Call Failed","Response: ${response.toString()}")
                }
            }
            override fun onFailure(call: Call<OpmlLink?>, t: Throwable) {
                Log.e("API Call Failed",""+t.message)
            }
        })

        return view
    }
}
