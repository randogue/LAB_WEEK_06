package com.example.lab_week_06

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender
import com.example.lab_week_06.model.GlideImageLoader

class MainActivity : AppCompatActivity() {
    //get ref for recyclerview
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    //instantiate cat adapter
    private val catAdapter by lazy {
        CatAdapter(
            layoutInflater,
            GlideImageLoader(this),
            object:
                CatAdapter.OnClickListener {
                //when called, pop up dialog appear
                override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
            }
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //setup the adapter for the recycler view
        recyclerView.adapter = catAdapter

        //setup layout manager (layoutmanager is used to set structure of item views)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        //instantiate ItemTouchHelper for the swipe to "delete callback" and
        //attach it to the recycler view
        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        //add data to list
        catAdapter.setData(
            listOf(
                CatModel(
                    gender = Gender.Male,
                    breed = CatBreed.BalineseJavanese,
                    name = "Fred",
                    biography ="Silent and deadly",
                    imageUrl = "https://cdn2.thecatapi.com/images/7dj.jpg"
                ),
                CatModel(
                    gender = Gender.Female,
                    breed = CatBreed.ExoticShorthair,
                    name = "Wilma",
                    biography ="Cuddly assasin",
                    imageUrl = "https://cdn2.thecatapi.com/images/egv.jpg"
                ),
                CatModel(
                    gender = Gender.Unknown,
                    breed = CatBreed.AmericanCurl,
                    name = "Curious George",
                    biography ="Award winning investigator",
                    imageUrl = "https://cdn2.thecatapi.com/images/bar.jpg"
                ),
                CatModel(
                    gender = Gender.Female,
                    breed = CatBreed.BalineseJavanese,
                    name = "Jack",
                    biography ="ore no namae wa jack",
                    imageUrl = "https://cdn2.thecatapi.com/images/car.jpg"
                ),
                CatModel(
                    gender = Gender.Male,
                    breed = CatBreed.AmericanCurl,
                    name = "Cat E",
                    biography ="E (meme)",
                    imageUrl = "https://cdn2.thecatapi.com/images/eee.jpg"
                ),
                CatModel(
                    gender = Gender.Unknown,
                    breed = CatBreed.ExoticShorthair,
                    name = "Cat F",
                    biography ="Feline",
                    imageUrl = "https://cdn2.thecatapi.com/images/cat.jpg"
                ),
                CatModel(
                    gender = Gender.Unknown,
                    breed = CatBreed.AmericanCurl,
                    name = "Cat G",
                    biography ="Undercover George",
                    imageUrl = "https://cdn2.thecatapi.com/images/bar.jpg"
                ),
                CatModel(
                    gender = Gender.Female,
                    breed = CatBreed.ExoticShorthair,
                    name = "Cat H",
                    biography ="Hairy goofball",
                    imageUrl = "https://cdn2.thecatapi.com/images/3dp.jpg"
                ),
                CatModel(
                    gender = Gender.Unknown,
                    breed = CatBreed.AmericanCurl,
                    name = "Cat I",
                    biography ="Willing to stare into the abyss",
                    imageUrl = "https://cdn2.thecatapi.com/images/dar.jpg"
                ),
                CatModel(
                    gender = Gender.Male,
                    breed = CatBreed.BalineseJavanese,
                    name = "Cat J",
                    biography ="Laughing villain",
                    imageUrl = "https://cdn2.thecatapi.com/images/7kr.jpg"
                )
            )
        )
    }

    //will create a pop up dialog when recycler item is clicked
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK") {
                    _, _ ->
            }.show()
    }
}