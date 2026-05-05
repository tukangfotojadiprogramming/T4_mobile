package com.example.studentcontactapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddEditStudentActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private var studentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_student)

        db = AppDatabase.getDB(this)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val etName = findViewById<EditText>(R.id.etName)
        val etNim = findViewById<EditText>(R.id.etNim)
        val spProdi = findViewById<Spinner>(R.id.spProdi)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etSemester = findViewById<EditText>(R.id.etSemester)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        val prodiList = arrayOf("Pilih Prodi", "Teknik Informatika", "Sistem Informasi", "Manajemen", "Akuntansi")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, prodiList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spProdi.adapter = adapter

        studentId = intent.getIntExtra("id", 0)
        if (studentId != 0) {
            tvTitle.text = "Edit Mahasiswa"
            etName.setText(intent.getStringExtra("name"))
            etNim.setText(intent.getStringExtra("nim"))
            etEmail.setText(intent.getStringExtra("email"))
            etSemester.setText(intent.getIntExtra("semester", 1).toString())
            
            val prodi = intent.getStringExtra("prodi")
            val pos = prodiList.indexOf(prodi)
            if (pos >= 0) spProdi.setSelection(pos)
        }

        btnBack.setOnClickListener { finish() }

        btnSimpan.setOnClickListener {
            val name = etName.text.toString()
            val nim = etNim.text.toString()
            val prodi = spProdi.selectedItem.toString()
            val email = etEmail.text.toString()
            val semester = etSemester.text.toString().toIntOrNull() ?: 1

            if (name.isEmpty() || nim.isEmpty() || prodi == "Pilih Prodi") {
                Toast.makeText(this, "Harap lengkapi data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val student = StudentEntity(
                id = studentId,
                name = name,
                nim = nim,
                prodi = prodi,
                email = email,
                semester = semester
            )

            lifecycleScope.launch {
                if (studentId == 0) {
                    db.studentDao().insert(student)
                } else {
                    db.studentDao().update(student)
                }
                finish()
            }
        }
    }
}
