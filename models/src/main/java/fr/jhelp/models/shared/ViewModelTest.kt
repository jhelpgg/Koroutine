package fr.jhelp.models.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelTest : ViewModel() {
    private val nameMutable: MutableLiveData<String> = MutableLiveData("")
    val name: LiveData<String> get() = this.nameMutable

    fun name(name: String) {
        if (name.isNotEmpty()) {
            this.nameMutable.value = name
        }
    }
}