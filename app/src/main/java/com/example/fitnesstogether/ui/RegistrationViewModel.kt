// ui/RegistrationViewModel.kt
package com.example.fitnesstogether.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    val userName  = MutableLiveData<String>()
    val password  = MutableLiveData<String>()
    val birthday = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val lastName  = MutableLiveData<String>()
    val role      = MutableLiveData<String>()
}