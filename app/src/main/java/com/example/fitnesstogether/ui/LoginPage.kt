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

import com.example.fitnesstogether.domain.LoginRequest
import com.example.fitnesstogether.domain.LoginResponse
import com.example.fitnesstogether.domain.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.google.android.material.textfield.TextInputEditText

class LoginPage : Fragment() {

    private lateinit var userNameField: TextInputEditText
    private lateinit var passwordField: TextInputEditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_screen, container, false)

        userNameField = view.findViewById(R.id.LoginInput) // Использование ID из вашего XML
        passwordField = view.findViewById(R.id.PasswordField) // Использование ID из вашего XML

        loginButton = view.findViewById(R.id.Next) // Использование ID из вашего XML

        loginButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginPage_to_mainMenu)
            //performLogin()
        }

        view.findViewById<TextView>(R.id.registerButton).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginPage_to_registerPage1)
        }

        return view
    }

    private fun performLogin() {
        val userName = userNameField.text.toString().trim()
        val password = passwordField.text.toString().trim()

        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Пожалуйста, введите UserName и Password", Toast.LENGTH_SHORT).show()
            return
        }

        val loginRequest = LoginRequest(userName, password = password)

        RetrofitClient.apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.accessToken != null) {
                        Toast.makeText(context, "Вход успешен!", Toast.LENGTH_LONG).show()
                        // TODO: Сохраните loginResponse.token (например, в SharedPreferences)
                        // TODO: Перейдите на следующий экран
                    } else {
                        Toast.makeText(context, "Ошибка входа! Укажите верные UserName и Password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        "Ошибка HTTP ${response.code()}: ${errorBody ?: response.message()}"
                    } catch (e: Exception) {
                        "Ошибка HTTP ${response.code()}: ${response.message()}"
                    }
                    Toast.makeText(context, "Ошибка сервера: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(context, "Ошибка сети: ${t.message}", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }
}