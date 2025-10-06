package com.example.lab_week_06

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel

class CatAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
): RecyclerView.Adapter<CatViewHolder>()
{
    //"Delete Callback" Instantiation
    val swipeToDeleteCallback = SwipeToDeleteCallback()

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

    //removing item by swiping
    fun removeItem(position: Int) {
        cats.removeAt(position)
        notifyItemRemoved(position)
    }

    //onCreateViewHolder will instantiate the view holder
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CatViewHolder
    {
        val view = layoutInflater.inflate(R.layout.item_list,parent, false)
        return CatViewHolder(view, imageLoader, onClickListener)
    }

    override fun getItemCount() = cats.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bindData(cats[position])
    }


    //declare onClickListeneer interface
    interface OnClickListener {
        fun onItemClick(cat: CatModel)
    }


    //Adding swipe delete feature
    //"inner" keyword can be used to declare class in a class.
    inner class SwipeToDeleteCallback: ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        //onMove is used when itemlist can be moved
        //in this case, we don't need it, so set it to false
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        //this is used to determine which directions are allowed
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ) =
            if (viewHolder is CatViewHolder) {
                makeMovementFlags(
                    ItemTouchHelper.ACTION_STATE_IDLE,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) or makeMovementFlags(
                    ItemTouchHelper.ACTION_STATE_SWIPE,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                )
            }
            //other gesture are not allowed (Drag, etc...)
            else {
                0
            }

        //this is used for swipe detection
        //if a valid swipe is detected, then remove item
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.bindingAdapterPosition //viewHolder.adapterPosition is deprecated, use viewHolder.bindingAdapterPosition instead
            removeItem(position)
        }
    }
}