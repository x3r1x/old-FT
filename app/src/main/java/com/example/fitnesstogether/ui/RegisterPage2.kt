package com.example.fitnesstogether.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.fitnesstogether.R
import com.google.android.material.textfield.TextInputEditText

class RegisterPage2 : Fragment() {
    private val vm: RegistrationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_screen_2, container, false)

        val userLogin = view.findViewById<TextInputEditText>(R.id.LoginInput)
        val userPassword = view.findViewById<TextInputEditText>(R.id.Password);
        val userPasswordConfirm = view.findViewById<TextInputEditText>(R.id.PasswordField);

        view.findViewById<Button>(R.id.Next).setOnClickListener {
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()
            val password2 = userPasswordConfirm.text.toString().trim()
            if (login.isEmpty() || password.isEmpty() || password2.isEmpty() || password  != password2) {
                Toast.makeText(requireContext(), "Введите логин и пароль!", Toast.LENGTH_SHORT).show()
                if (password  != password2){
                    Toast.makeText(requireContext(), "Пароли должны совпадать!", Toast.LENGTH_SHORT).show()
                }
                return@setOnClickListener
            }
            vm.userName.value = login
            vm.password.value = password
            println(vm.userName)
            Navigation.findNavController(view)
                .navigate(R.id.action_registerPage1_to_registerPage2)
        }


        view.findViewById<Button>(R.id.Next).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerPage2_to_registerPage3)
        }

        view.findViewById<TextView>(R.id.login).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerPage2_to_loginPage)
        }

        return view
    }
}