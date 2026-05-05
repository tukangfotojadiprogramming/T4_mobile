package com.example.studentcontactapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentcontactapp.utils.PrefManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var adapter: StudentAdapter
    private lateinit var db: AppDatabase
    private lateinit var pref: PrefManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = PrefManager(requireContext())
        db = AppDatabase.getDB(requireContext())
        
        val tvWelcome = view.findViewById<TextView>(R.id.tvWelcome)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAdd)

        fabAdd?.visibility = View.GONE

        val username = pref.getUsername()
        tvWelcome.text = "Welcome, $username!"

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = StudentAdapter(
            list = listOf(),
            isAdminMode = false
        )

        recyclerView.adapter = adapter
        
        checkAndSeedData()
    }

    private fun checkAndSeedData() {
        lifecycleScope.launch {
            val count = db.studentDao().getStudentCount()
            if (count == 0) {
                val initialData = listOf(
                    StudentEntity(name = "Ahmad Fauzi", nim = "123001", prodi = "Teknik Informatika", email = "ahmad@mail.com", semester = 5),
                    StudentEntity(name = "Budi Santoso", nim = "123002", prodi = "Sistem Informasi", email = "budi@mail.com", semester = 3),
                    StudentEntity(name = "Clara Wijaya", nim = "123003", prodi = "Manajemen", email = "clara@mail.com", semester = 1)
                )
                db.studentDao().insertAll(initialData)
            }
            loadData()
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            val data = db.studentDao().getAllStudents()
            adapter.updateData(data)
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}
