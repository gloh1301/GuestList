package com.bignerdranch.android.guestlist

import androidx.lifecycle.ViewModel

class GuestListViewModel: ViewModel() {
    private val guestNames = mutableListOf<String>()

    fun addGuest(name: String) {
        guestNames.add(name)
    }

    fun getSortedGuestName(): List<String> {
        return guestNames.sorted()
    }

    fun clearGuestList() {
        guestNames.clear()
    }
}