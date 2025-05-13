package com.example.fitnesstogether.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.fitnesstogether.R

class RegisterPage4 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_screen_4, container, false)

        view.findViewById<Button>(R.id.Next).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerPage4_to_loginPage)
        }

        view.findViewById<TextView>(R.id.login).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerPage4_to_loginPage)
        }

        return view
    }
}