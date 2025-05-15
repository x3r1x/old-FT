package com.example.fitnesstogether.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fitnesstogether.R
import com.google.android.material.navigation.NavigationView


class MainMenu : Fragment() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_page, container, false)

        drawerLayout = view.findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = view.findViewById<NavigationView>(R.id.nav_view)
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        toggle = ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close)
        toggle.drawerArrowDrawable.color = Color.parseColor("#000000")
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.bringToFront()

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_trainees -> loadFragment(WorkoutPage())
                R.id.nav_account -> loadFragment(ProfilePage())
            }

            drawerLayout.closeDrawers()
            true
        }

        return view
    }

    private fun loadFragment(fragment: Fragment) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.commit()
    }
}