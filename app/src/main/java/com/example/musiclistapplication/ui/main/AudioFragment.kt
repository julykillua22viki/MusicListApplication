package com.example.musiclistapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclistapplication.R
import com.example.musiclistapplication.model.Audio
import com.example.musiclistapplication.persistence.OpmlApi
import com.example.musiclistapplication.persistence.RetrofitService
import com.example.musiclistapplication.ui.main.adapter.AudioAdapter
import java.util.ArrayList


class AudioFragment : Fragment() {

    companion object {

        val CHILDREN = "children"
        val TITLE = "string"

        fun newInstance(children: ArrayList<Audio>?, title: String?): AudioFragment
        {
            val f = AudioFragment()
            val args = Bundle()
            args.putParcelableArrayList(CHILDREN, children)
            args.putString(TITLE, title)
            f.setArguments(args)
            return f
        }

    }

    private lateinit var audioAdapter: AudioAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.audio_fragment, container, false)
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        audioAdapter = AudioAdapter(context!!)


        recyclerView.adapter = audioAdapter
        recyclerView.layoutManager = LinearLayoutManager( context!! )

        audioAdapter.updateList( arguments?.getParcelableArrayList<Audio>( CHILDREN ) )
        activity?.setTitle( arguments?.getString( TITLE )  )
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
