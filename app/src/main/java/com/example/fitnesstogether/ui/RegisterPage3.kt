package com.example.fitnesstogether.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.fitnesstogether.R

class RegisterPage3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_screen_3, container, false)

        val button1 = view.findViewById<TextView>(R.id.coachButton)
        val button2 = view.findViewById<TextView>(R.id.customerButton)

        button1.setOnClickListener {
            if (button2.currentTextColor == Color.parseColor("#FF8C00")) {
                button2.setTextColor(Color.parseColor("#000000"))
            }

            button1.setTextColor(Color.parseColor("#FF8C00"))
        }

        button2.setOnClickListener {
            if (button1.currentTextColor == Color.parseColor("#FF8C00")) {
                button1.setTextColor(Color.parseColor("#000000"))
            }

            button2.setTextColor(Color.parseColor("#FF8C00"))
        }

        view.findViewById<Button>(R.id.Next).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerPage3_to_registerPage4)
        }

        view.findViewById<TextView>(R.id.login).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerPage3_to_loginPage)
        }

        return view
    }
}