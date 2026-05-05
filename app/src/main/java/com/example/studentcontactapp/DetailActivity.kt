package com.example.studentcontactapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.studentcontactapp.utils.FileHelper

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvAvatar = findViewById<TextView>(R.id.tvAvatar)
        val tvInfo = findViewById<TextView>(R.id.tvInfo)

        val etNote = findViewById<EditText>(R.id.etNote)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnLoad = findViewById<Button>(R.id.btnLoad)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        val name = intent.getStringExtra("name") ?: ""
        val nim = intent.getStringExtra("nim") ?: "default"
        val prodi = intent.getStringExtra("prodi") ?: ""

        tvName.text = name
        tvInfo.text = "$nim - $prodi"

        val initials = name.split(" ")
            .filter { it.isNotEmpty() }
            .map { it.first().uppercase() }
            .take(2)
            .joinToString("")
        tvAvatar.text = initials

        btnBack.setOnClickListener { finish() }


        refreshNoteStatus(nim, etNote, tvStatus)

        btnSave.setOnClickListener {
            val content = etNote.text.toString()
            FileHelper.saveNote(this, nim, content)
            refreshNoteStatus(nim, etNote, tvStatus)
            Toast.makeText(this, "Catatan disimpan", Toast.LENGTH_SHORT).show()
        }

        btnLoad.setOnClickListener {
            if (FileHelper.isNoteExists(this, nim)) {
                etNote.setText(FileHelper.loadNote(this, nim))
                tvStatus.text = "✔ Catatan dimuat"
            } else {
                tvStatus.text = "Belum ada catatan"
            }
        }
    }

    private fun refreshNoteStatus(nim: String, etNote: EditText, tvStatus: TextView) {
        if (FileHelper.isNoteExists(this, nim)) {
            val savedText = FileHelper.loadNote(this, nim)
            if (etNote.text.isEmpty()) etNote.setText(savedText)
            val size = FileHelper.getFileSize(this, nim)
            tvStatus.text = "Tersimpan ($size bytes)"
        } else {
            tvStatus.text = "Belum ada catatan"
        }
    }
}
