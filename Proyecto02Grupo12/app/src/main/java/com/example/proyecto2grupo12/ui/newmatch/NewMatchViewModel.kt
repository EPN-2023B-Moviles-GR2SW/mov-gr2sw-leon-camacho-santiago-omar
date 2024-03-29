package com.example.proyecto2grupo12.ui.newmatch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto2grupo12.domain.message.MessageRepository
import com.example.proyecto2grupo12.domain.profilecard.entity.NewMatch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewMatchViewModel(private val messageRepository: MessageRepository): ViewModel() {
    private val _match: MutableStateFlow<NewMatch?> = MutableStateFlow(null)
    val match = _match.asStateFlow()

    fun sendMessage(text: String){
        val matchId = _match.value?.id ?: return
        viewModelScope.launch {
            try {
                messageRepository.sendMessage(matchId, text)
            }catch (e: Exception){
                //Show the message as unsent?
            }
        }
    }

    fun setMatch(match: NewMatch){
        _match.value = match
    }
}