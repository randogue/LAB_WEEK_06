package com.example.lab_week_06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel

class CatAdapter(
    private val layoutInflater: LayoutInflater, private val imageLoader: ImageLoader
): RecyclerView.Adapter<CatViewHolder>()
{
    //mutable list
    private val cats = mutableListOf<CatModel>()

    //function for setting mutable list
    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll(newCats)

        //tells Observer/Adapter that there is a data change in mutable list
        notifyDataSetChanged()
        //this is done so that layout manager are forced to fully rebind data and relay it's layout
    }

    //onCreateViewHolder will instantiate the view holder
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CatViewHolder
    {
        val view = layoutInflater.inflate(R.layout.item_list,parent, false)
        return CatViewHolder(view, imageLoader)
    }

    override fun getItemCount() = cats.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bindData(cats[position])
    }


}