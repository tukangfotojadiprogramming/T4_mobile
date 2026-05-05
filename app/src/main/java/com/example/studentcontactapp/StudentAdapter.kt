package com.example.studentcontactapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentcontactapp.utils.FileHelper

class StudentAdapter(
    private var list: List<StudentEntity>,
    private val isAdminMode: Boolean = false,
    private val onEdit: ((StudentEntity) -> Unit)? = null,
    private val onDelete: ((StudentEntity) -> Unit)? = null
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardStudent)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvAvatar: TextView = view.findViewById(R.id.tvAvatar)
        val tvNim: TextView = view.findViewById(R.id.tvNim)
        val btnEdit: TextView = view.findViewById(R.id.btnEdit)
        val btnDel: TextView = view.findViewById(R.id.btnDel)
        val layoutAction: View = view.findViewById(R.id.layoutAction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        val context = holder.itemView.context

        holder.cardView.setCardBackgroundColor(Color.WHITE)

        holder.tvName.text = data.name
        holder.tvName.setTextColor(Color.parseColor("#333333"))
        
        holder.tvNim.text = data.nim
        holder.tvNim.visibility = if (isAdminMode) View.VISIBLE else View.GONE

        val initials = data.name.split(" ")
            .filter { it.isNotEmpty() }
            .map { it.first().uppercase() }
            .take(2)
            .joinToString("")
        holder.tvAvatar.text = initials
        holder.tvAvatar.setTextColor(Color.WHITE)

        val colors = listOf("#3F51B5", "#E91E63", "#009688", "#FF9800", "#673AB7")
        val color = Color.parseColor(colors[position % colors.size])
        val drawable = holder.tvAvatar.background as GradientDrawable
        drawable.setColor(color)

        if (isAdminMode) {
            holder.layoutAction.visibility = View.VISIBLE
            holder.btnEdit.setOnClickListener { onEdit?.invoke(data) }
            holder.btnDel.setOnClickListener { onDelete?.invoke(data) }
        } else {
            holder.layoutAction.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("name", data.name)
                putExtra("nim", data.nim)
                putExtra("prodi", data.prodi)
            }
            context.startActivity(intent)
        }
    }

    fun updateData(newList: List<StudentEntity>) {
        list = newList
        notifyDataSetChanged()
    }

    fun getStudentAt(position: Int): StudentEntity {
        return list[position]
    }
}
