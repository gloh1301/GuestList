package com.bignerdranch.android.guestlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

const val LAST_GUEST_NAME_KEY = "last-guest-name-bundle-key"

class MainActivity : AppCompatActivity() {

    private  lateinit var addGuestButton: Button
    private lateinit var newGuestEditText: EditText
    private lateinit var guestList: TextView
    private lateinit var lastGuestAdded: TextView
    private lateinit var clearGuestListButton: Button

    // val guestNames = mutableListOf<String>()

    private val guestListViewModel: GuestListViewModel by lazy {
        ViewModelProvider(this).get(GuestListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addGuestButton = findViewById(R.id.add_guest_button)
        newGuestEditText = findViewById(R.id.new_guest_input)
        guestList = findViewById(R.id.list_of_guests)
        lastGuestAdded = findViewById(R.id.last_guest_added)
        clearGuestListButton = findViewById(R.id.clear_guests_button)

        addGuestButton.setOnClickListener {
            addNewGuest()
        }

        clearGuestListButton.setOnClickListener {
            guestListViewModel.clearGuestList()
            updateGuestList()
            lastGuestAdded.text = ""
        }

        val savedLastGuestMessage = savedInstanceState?.getString(LAST_GUEST_NAME_KEY)
        lastGuestAdded.text = savedLastGuestMessage

        updateGuestList()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_GUEST_NAME_KEY, lastGuestAdded.text.toString())
    }

    private fun addNewGuest() {
        val newGuestName = newGuestEditText.text.toString()
        if (newGuestName.isNotBlank()) {
            // guestNames.add(newGuestName)
            guestListViewModel.addGuest(newGuestName)
            updateGuestList()
            newGuestEditText.text.clear()
            lastGuestAdded.text =  getString(R.string.last_guest_message, newGuestName)
        }
    }

    private  fun updateGuestList() {
        val guests = guestListViewModel.getSortedGuestName()
        val guestDisplay = guests.joinToString(separator = "\n")
        guestList.text = guestDisplay
    }
}