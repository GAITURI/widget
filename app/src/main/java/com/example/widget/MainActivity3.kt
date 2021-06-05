package com.example.widget

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity3 : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //identification
        drawer = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)

        navView = findViewById(R.id.nav_drawer)
        navView.setNavigationItemSelectedListener(this)
        //new instance of the action bar drawer toggle class so that we can get the hamburger icon
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        //sync state
        toggle.syncState()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        R.id.background->{
           val intentBack=Intent(this@MainActivity3,BackgroundLogic::class.java)
            startActivity(intentBack)
        }
            R.id.item1->{
                val intentMain=Intent(this@MainActivity3,MainActivity::class.java)
                startActivity(intentMain)
            }
            R.id.item2->{
                val intentb=Intent(this@MainActivity3,MainActivity2::class.java)
                startActivity(intentb)
            }
            R.id.item3->{
                val intentsql=Intent(this@MainActivity3,Crud::class.java)
                startActivity(intentsql)
            }
        }
        return true
    }

    override fun onBackPressed() {


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}