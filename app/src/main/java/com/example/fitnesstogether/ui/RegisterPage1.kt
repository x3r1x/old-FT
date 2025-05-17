package com.example.fitnesstogether.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.fitnesstogether.R
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class RegisterPage1: Fragment() {
    private val vm: RegistrationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_screen_1, container, false)

        val userFirstName = view.findViewById<TextInputEditText>(R.id.LoginInput)
        val userLastName = view.findViewById<TextInputEditText>(R.id.Password)
        val userBirthday = view.findViewById<TextInputEditText>(R.id.PasswordField);

        userBirthday.setOnClickListener {
            val c = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    val iso = String.format("%04d-%02d-%02d", year, month+1, day)
                    userBirthday.setText(iso)
                    vm.birthday.value = iso
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        view.findViewById<Button>(R.id.Next).setOnClickListener {
            val name = userFirstName.text.toString().trim()
            val lastName = userLastName.text.toString().trim()
            val birthday = userBirthday.text.toString().trim()
            if (name.isEmpty() || birthday.isEmpty() ) {
                Toast.makeText(requireContext(), "Введите имя и дату рождения!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            vm.firstName.value = name
            vm.lastName.value = lastName
            Navigation.findNavController(view)
                .navigate(R.id.action_registerPage1_to_registerPage2)
        }

        view.findViewById<TextView>(R.id.login).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerPage1_to_loginPage)
        }

        return view
    }
}