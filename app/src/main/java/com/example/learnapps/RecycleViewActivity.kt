package com.example.learnapps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecycleViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycle_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val countryList = ArrayList<CountryModel>()
        countryList.add(CountryModel("Afghanistan", R.drawable.flag_afg))
        countryList.add(CountryModel("Algeria", R.drawable.flag_algeria))
        countryList.add(CountryModel("Argentina", R.drawable.flag_argentina))
        countryList.add(CountryModel("Australia", R.drawable.flag_australia))
        countryList.add(CountryModel("Bangladesh", R.drawable.flag_bangladesh))
        countryList.add(CountryModel("Brazil", R.drawable.flag_brazil))
        countryList.add(CountryModel("Canada", R.drawable.flag_canada))
        countryList.add(CountryModel("China", R.drawable.flag_china))
        countryList.add(CountryModel("Egypt", R.drawable.flag_egypt))
        countryList.add(CountryModel("Germany", R.drawable.flag_germany))
        countryList.add(CountryModel("India", R.drawable.flag_india))
        countryList.add(CountryModel("Iraq", R.drawable.flag_iraq))
        countryList.add(CountryModel("Japan", R.drawable.flag_japan))
        countryList.add(CountryModel("Pakistan", R.drawable.flag_pakistan))
        countryList.add(CountryModel("Sri Lanka", R.drawable.flag_srilanka))
        countryList.add(CountryModel("Turkey", R.drawable.flag_turkey))
        countryList.add(CountryModel("USA", R.drawable.flag_usa))

        val adapter = CountryAdapter(countryList)

        recyclerView.adapter = adapter



    }
}