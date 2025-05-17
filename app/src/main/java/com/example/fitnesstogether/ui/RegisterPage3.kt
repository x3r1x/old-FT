package com.example.fitnesstogether.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fitnesstogether.FTApplication
import com.example.fitnesstogether.R
import com.example.fitnesstogether.domain.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPage3 : Fragment() {
    private val vm: RegistrationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.register_screen_3, container, false)

        // Находим вьюхи
        val coachBtn    = view.findViewById<TextView>(R.id.coachButton)
        val customerBtn = view.findViewById<TextView>(R.id.customerButton)
        val nextBtn     = view.findViewById<Button>(R.id.Next)
        val loginLink   = view.findViewById<TextView>(R.id.login)

        // Цвета
        val colorSelected = Color.parseColor("#FF8C00")
        val colorNormal   = Color.parseColor("#000000")

        // Выбор роли
        coachBtn.setOnClickListener {
            vm.role.value = "Coach"
            coachBtn.setTextColor(colorSelected)
            customerBtn.setTextColor(colorNormal)
        }
        customerBtn.setOnClickListener {
            vm.role.value = "Client"
            customerBtn.setTextColor(colorSelected)
            coachBtn.setTextColor(colorNormal)
        }

        // Нажали "Next"
        nextBtn.setOnClickListener {
            // Проверяем, что роль выбрана
            val role = vm.role.value
            if (role.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Пожалуйста, выберите роль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Собираем все поля из ViewModel
            val userName  = vm.userName.value.orEmpty()
            val password  = vm.password.value.orEmpty()
            val birthday  = vm.birthday.value.orEmpty()
            val firstName = vm.firstName.value.orEmpty()
            val lastName  = vm.lastName.value.orEmpty()

            // Проверяем, что нужные поля не пустые
//            if (userName.isEmpty() || password.isEmpty() || birthday.isEmpty()
//                || firstName.isEmpty() || lastName.isEmpty()
//            ) {
//                Toast.makeText(requireContext(),
//                    "Часть данных не заполнена, вернитесь назад и заполните все поля",
//                    Toast.LENGTH_LONG).show()
//                return@setOnClickListener
//            }

            // Формируем DTO и отправляем на сервер
            val request = RegisterRequest(
                userName  = userName,
                password  = password,
                birthday  = birthday,
                firstName = firstName,
                lastName  = lastName,
                role      = role
            )

            Toast.makeText(requireContext(),
                request.toString(),
                Toast.LENGTH_LONG).show()

            FTApplication.apiService.register(request)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(requireContext(),
                                "Регистрация прошла успешно!",
                                Toast.LENGTH_SHORT).show()
                            // Переходим на экран логина
                            findNavController()
                                .navigate(R.id.action_registerPage3_to_loginPage)
                        } else {
                            Toast.makeText(requireContext(),
                                "Ошибка ${response.code()}: ${response.message()}",
                                Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(requireContext(),
                            "Сбой сети: ${t.message}",
                            Toast.LENGTH_LONG).show()
                    }
                })
        }

        // Ссылка "Войти"
        loginLink.setOnClickListener {
            findNavController()
                .navigate(R.id.action_registerPage3_to_loginPage)
        }

        return view
    }
}