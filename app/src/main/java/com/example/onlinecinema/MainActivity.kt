package com.example.onlinecinema

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.onlinecinema.base.Screens
import com.example.onlinecinema.features.movies_list_screen.ui.MoviesListFragment
import com.example.onlinecinema.features.movies_player_screen.ui.MoviesYoutubePlayerFragment
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject

const val YOUTUBE_VIDEO_ID = "5snjgB9PO48"
const val DEVELOPER_KEY = "AIzaSyAX2id1z2UQPn91CJCQNaRUu_oFGame1rU"

class MainActivity : AppCompatActivity() {

    private val navigatorHolder by inject<NavigatorHolder>()
    private val navigator = AppNavigator(this, android.R.id.content)
    private val router by inject<Router>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigatorHolder.setNavigator(navigator)
        router.newRootScreen(Screens.moviesListScreen())

    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment).commit()
    }

}


//
//class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.fragment_movies_player_youtube)
//
//        val player = findViewById<YouTubePlayerView>(R.id.pvYoutube)
//        Log.d("test", player.toString())
//
//        player.initialize(DEVELOPER_KEY, this)
//
//    }
//
//    override fun onInitializationSuccess(
//        provider: YouTubePlayer.Provider?,
//        player: YouTubePlayer?,
//        wasRestored: Boolean
//    ) {
//        Log.d("Youtube", "onInitializationSuccess")
//        if (player == null) return
//        if (wasRestored) {
//            player.play()
//        } else {
//            player.cueVideo(YOUTUBE_VIDEO_ID)
//            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//        }
//    }
//
//    override fun onInitializationFailure(
//        p0: YouTubePlayer.Provider?,
//        p1: YouTubeInitializationResult?
//    ) {
//        Log.d("Youtube", "onInitializationFailure")
//        Log.d("Youtube", p1.toString())
//    }
//
//
//}

