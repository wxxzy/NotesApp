package com.nemo.notes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotesApp : Application() {
    companion object {
        const val NOTES_APP_URI = "https://developer.android.com/notes"
    }

}