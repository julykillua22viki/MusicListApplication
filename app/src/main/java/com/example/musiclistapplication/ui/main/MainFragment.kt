package com.example.musiclistapplication.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclistapplication.R
import com.example.musiclistapplication.model.Browse
import com.example.musiclistapplication.model.Opml
import com.example.musiclistapplication.persistence.OpmlApi
import com.example.musiclistapplication.persistence.RetrofitService
import com.example.musiclistapplication.ui.main.adapter.BrowseAdapter
import retrofit2.Call
import retrofit2.Response


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var service: OpmlApi
    private lateinit var browseAdapter: BrowseAdapter

    init {
        service = RetrofitService.createService(OpmlApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.main_fragment, container, false)
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        browseAdapter = BrowseAdapter(context!!)

        recyclerView.adapter = browseAdapter
        recyclerView.layoutManager = LinearLayoutManager( context!! )

        val call = service.getList();

        call.enqueue(object : retrofit2.Callback<Opml?> {

            override fun onResponse(call: Call<Opml?>,
                                    response: Response<Opml?>
            ) {
                if (response.isSuccessful){
                    Log.d("API Call Success","Response: ${response.toString()}")

                    val opml : Opml = response.body()!!
                    browseAdapter.updateList( opml.body!! )

                    activity?.setTitle( opml.head?.title )
                }
                else
                {
                    Log.e("API Call Failed","Response: ${response.toString()}")
                }
            }
            override fun onFailure(call: Call<Opml?>, t: Throwable) {
                Log.e("API Call Failed",""+t.message)
            }
        })

        return view
    }
}
