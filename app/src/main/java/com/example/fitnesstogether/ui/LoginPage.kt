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
import com.example.fitnesstogether.FTApplication
import com.example.fitnesstogether.R

import com.example.fitnesstogether.domain.LoginRequest
import com.example.fitnesstogether.domain.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.google.android.material.textfield.TextInputEditText

class LoginPage : Fragment() {

    private lateinit var userNameField: TextInputEditText
    private lateinit var passwordField: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var view: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.login_screen, container, false)

        userNameField = view.findViewById(R.id.LoginInput)
        passwordField = view.findViewById(R.id.PasswordField)

        loginButton = view.findViewById(R.id.Next)

        loginButton.setOnClickListener {
            performLogin()
        }

        view.findViewById<TextView>(R.id.registerButton).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginPage_to_registerPage1)
        }

        return view
    }

    private fun performLogin() {
        val userName = userNameField.text.toString().trim()
        val password = passwordField.text.toString().trim()
        val context = requireContext()

        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Пожалуйста, введите UserName и Password", Toast.LENGTH_SHORT).show()
            return
        }

        val loginRequest = LoginRequest(userName, password = password)

        FTApplication.apiService.login(loginRequest)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { tokens ->
                            // сохраняем токены
                            TokenStorage.saveTokens(context, tokens.accessToken, tokens.refreshToken)
                            // навигация
                            Navigation.findNavController(view)
                                .navigate(R.id.action_loginPage_to_mainMenu)
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val message = "Ошибка ${response.code()}: ${errorBody ?: response.message()}"
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(context, "Ошибка сети: ${t.message}", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            })
    }
}