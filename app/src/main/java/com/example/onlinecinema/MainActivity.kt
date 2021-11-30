package com.example.onlinecinema

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.onlinecinema.features.movies_list_screen.ui.MoviesListFragment
import java.util.*

class MainActivity : AppCompatActivity() {

//    private val navigator = AppNavigator(this, R.id.moviesContainer)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragment(MoviesListFragment())


//        testTime()
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment).commit()
    }




/*    private fun testTime(){

        Log.i("TIMESTAMP Today:", today().toString())
        Log.i("TIMESTAMP Today:", getPreviousDate().toString())

        Log.i("TIMESTAMP twoWeeks:", twoWeeks().toString())
        Log.i("TIMESTAMP 14 days:", getPreviousDate(days = 14).toString())

        Log.i("TIMESTAMP oneMonth:", oneMonth().toString())
        Log.i("TIMESTAMP 1 months:", getPreviousDate(months = 1).toString())

        Log.i("TIMESTAMP threeMonths:", threeMonths().toString())
        Log.i("TIMESTAMP 3 months:", getPreviousDate(months = 3).toString())

        Log.i("TIMESTAMP sixMonths:", sixMonths().toString())
        Log.i("TIMESTAMP 6 months:", getPreviousDate(months = 6).toString())
    }

    fun getPreviousDate(days: Int = 0, months: Int = 0): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        calendar.add(Calendar.MONTH, -months)
        return calendar.time
    }




    fun today(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun twoWeeks(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -14)
        return calendar.time
    }

    fun oneMonth(): Date = getMonths(-1)

    fun threeMonths(): Date = getMonths(-3)

    fun sixMonths(): Date = getMonths(-6)

    private fun getMonths(offset: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, offset)
//        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar.time
    }*/
}

