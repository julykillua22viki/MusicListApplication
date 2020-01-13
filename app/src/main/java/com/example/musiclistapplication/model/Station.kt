package com.example.musiclistapplication.model

data class Station(
    val element: String?,
    val text: String?,
    val key: String?,
    val children: List<Audio> )
{
    fun getChildren() : ArrayList<Audio>
    {
        if( children == null )
            return ArrayList()
        return ArrayList(children)
    }
}