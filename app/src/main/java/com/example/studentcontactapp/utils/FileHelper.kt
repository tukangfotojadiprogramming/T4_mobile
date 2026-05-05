package com.example.studentcontactapp.utils

import android.content.Context
import java.io.File

object FileHelper {

    private fun getFile(context: Context, nim: String): File {
        return File(context.filesDir, "$nim.txt")
    }

    fun saveNote(context: Context, nim: String, content: String) {
        getFile(context, nim).writeText(content)
    }

    fun loadNote(context: Context, nim: String): String {
        val file = getFile(context, nim)
        return if (file.exists()) file.readText() else ""
    }

    fun deleteNote(context: Context, nim: String) {
        val file = getFile(context, nim)
        if (file.exists()) file.delete()
    }

    fun isNoteExists(context: Context, nim: String): Boolean {
        return getFile(context, nim).exists()
    }

    fun getFileSize(context: Context, nim: String): Long {
        val file = getFile(context, nim)
        return if (file.exists()) file.length() else 0
    }

    fun getAllNotes(context: Context): List<File> {
        return context.filesDir.listFiles { file ->
            file.name.endsWith(".txt")
        }?.toList() ?: emptyList()
    }
}
