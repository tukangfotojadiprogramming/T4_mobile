package com.example.studentcontactapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private lateinit var adapter: StudentAdapter
    private lateinit var db: AppDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getDB(requireContext())
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerStudents)
        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAdd)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        adapter = StudentAdapter(
            list = listOf(),
            isAdminMode = true,
            onEdit = { student ->
                val intent = Intent(requireContext(), AddEditStudentActivity::class.java).apply {
                    putExtra("id", student.id)
                    putExtra("name", student.name)
                    putExtra("nim", student.nim)
                    putExtra("prodi", student.prodi)
                    putExtra("email", student.email)
                    putExtra("semester", student.semester)
                }
                startActivity(intent)
            },
            onDelete = { student ->
                showDeleteDialog(student)
            }
        )
        recyclerView.adapter = adapter

        fabAdd.setOnClickListener {
            startActivity(Intent(requireContext(), AddEditStudentActivity::class.java))
        }

        // Step 9: Swipe to Delete
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder): Boolean = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val student = adapter.getStudentAt(position)
                showDeleteDialog(student)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            val data = db.studentDao().getAllStudents()
            adapter.updateData(data)
        }
    }

    private fun showDeleteDialog(student: StudentEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Data")
            .setMessage("Apakah Anda yakin ingin menghapus ${student.name}?")
            .setPositiveButton("Hapus") { _, _ ->
                lifecycleScope.launch {
                    db.studentDao().deleteById(student.id)
                    loadData()
                }
            }
            .setNegativeButton("Batal") { _, _ ->
                loadData() // Refresh to bring back swiped item
            }
            .setOnCancelListener {
                loadData()
            }
            .show()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}
