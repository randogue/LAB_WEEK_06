package com.example.lab_week_06

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender


private val FEMALE_SYMBOL = "\u2640"
private val MALE_SYMBOL = "\u2642"
private val UNKNOWN_SYMBOL = "?"


//onClickListener gained from View->ViewHolder->Adapter->MainActivity.
//karena MainActivity instantiate Adapter (jadi Main Activity pass OnClickListener ke Adapter lewat instantiation argument)
//dan seterusnya sampai ViewHolder, baru ViewHolder nge-instantiate view individual.
class CatViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: CatAdapter.OnClickListener): RecyclerView.ViewHolder(containerView)
{
    //containerView is the view/container layout for layout of each item list


    //get all ref to xml's views
    private val catBiographyView: TextView by lazy {
        containerView.findViewById(R.id.cat_biography)
    }
    private val catBreedView: TextView by lazy {
        containerView.findViewById(R.id.cat_breed)
    }
    private val catGenderView: TextView by lazy {
        containerView.findViewById(R.id.cat_gender)
    }
    private val catNameView: TextView by lazy {
        containerView.findViewById(R.id.cat_name)
    }
    private val catPhotoView: ImageView by lazy {
        containerView.findViewById(R.id.cat_photo)
    }

    //function is callled in the adapter to provide the binding function
    fun bindData(cat: CatModel) {
        //override onClickListener
        containerView.setOnClickListener {
            //using onClickListener passed from CatAdapter
            onClickListener.onItemClick(cat)
        }

        //binding text with cat data
        imageLoader.loadImage(cat.imageUrl,catPhotoView)
        catNameView.text = cat.name
        catBreedView.text = when (cat.breed) {
            CatBreed.AmericanCurl -> "Amerian Curl"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.ExoticShorthair -> "Exotic Shorthair"
        }
        catBiographyView.text = cat.biography
        catGenderView.text = when (cat.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            else -> UNKNOWN_SYMBOL
        }
    }
    //binding data is an act of filling in the template with it's supposed data

    //declare interface
    interface OnClickListener {
        fun onItemClick(cat: CatModel)
    }
}