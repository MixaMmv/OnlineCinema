package com.example.onlinecinema

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.onlinecinema.features.movies_list_screen.ui.MoviesListFragment

class MainActivity : AppCompatActivity() {

//    private val navigator = AppNavigator(this, R.id.moviesContainer)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragment(MoviesListFragment())



    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment).commit()
    }
}