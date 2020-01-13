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
import com.example.musiclistapplication.model.OpmlStation
import com.example.musiclistapplication.persistence.OpmlApi
import com.example.musiclistapplication.persistence.RetrofitService
import com.example.musiclistapplication.ui.main.adapter.StationAdapter
import retrofit2.Call
import retrofit2.Response


class StationFragment : Fragment() {

    companion object {

        val URL = "url"

        fun newInstance(url: String ): StationFragment
        {
            val f = StationFragment()
            val args = Bundle()
            args.putString(URL, url)
            f.setArguments(args)
            return f
        }
    }

    private lateinit var service: OpmlApi
    private lateinit var audioAdapter: StationAdapter

    init {
        service = RetrofitService.createService(OpmlApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.link_fragment, container, false)
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        audioAdapter = StationAdapter(context!!)


        recyclerView.adapter = audioAdapter
        recyclerView.layoutManager = LinearLayoutManager( context!! )

        val call = service.getListByAudioItems( arguments?.getString( URL ), "json" );

        call.enqueue(object : retrofit2.Callback<OpmlStation?> {

            override fun onResponse(call: Call<OpmlStation?>,
                                    response: Response<OpmlStation?>
            ) {
                if (response.isSuccessful){
                    Log.d("API Call Success","Response: ${response.toString()}")

                    val opml : OpmlStation = response.body()!!
                    audioAdapter.updateList( opml.body!! )
                    activity?.setTitle( opml.head?.title )
                }
                else
                {
                    Log.e("API Call Failed","Response: ${response.toString()}")
                }
            }
            override fun onFailure(call: Call<OpmlStation?>, t: Throwable) {
                Log.e("API Call Failed",""+t.message)
            }
        })

        return view
    }
}
