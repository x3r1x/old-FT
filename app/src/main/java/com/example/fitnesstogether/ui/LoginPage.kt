package com.example.fitnesstogether.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.fitnesstogether.R

class LoginPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_screen, container, false)

        view.findViewById<Button>(R.id.Next).setOnClickListener {
            Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<TextView>(R.id.registerButton).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginPage_to_registerPage1)
        }

        return view
    }
}