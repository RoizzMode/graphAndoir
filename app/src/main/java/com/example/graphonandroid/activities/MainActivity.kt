package com.example.graphonandroid.activities

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.graphonandroid.R
import com.example.graphonandroid.fragments.MainFragment
import com.example.graphonandroid.routers.MainActivityRouter


class MainActivity : AppCompatActivity() {

    private lateinit var activityRouter: MainActivityRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRouter = MainActivityRouter(this)
        if (isLarge()) {
            setContentView(R.layout.tablet_layout)
            activityRouter.initLargeScreen()
        } else {
            setContentView(R.layout.phone_layout)
            activityRouter.initSmallScreen()
        }
    }

    private fun isLarge(): Boolean {
        if ((resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) > Configuration.SCREENLAYOUT_SIZE_LARGE)
            return true
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.clear_all -> {
            if (!isLarge()) {
                activityRouter.returnToFirstScreen()
                activityRouter.getMainFragment().clearAll()
            }
            else{
                (supportFragmentManager.findFragmentById(R.id.container_left) as MainFragment).clearAll()
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
