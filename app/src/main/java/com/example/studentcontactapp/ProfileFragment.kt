package com.example.studentcontactapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.studentcontactapp.utils.PrefManager
import com.example.studentcontactapp.utils.SettingsManager

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    lateinit var pref: PrefManager
    lateinit var settings: SettingsManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = PrefManager(requireContext())
        settings = SettingsManager(requireContext())

        val tvProfileName = view.findViewById<TextView>(R.id.tvProfileName)
        val switchDark = view.findViewById<Switch>(R.id.switchDark)
        val switchNotif = view.findViewById<Switch>(R.id.switchNotif)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        // Tampilkan nama user (Langkah 4)
        tvProfileName.text = pref.getUsername()

        switchDark.isChecked = settings.isDarkMode()
        switchNotif.isChecked = settings.isNotification()

        switchDark.setOnCheckedChangeListener { _, isChecked ->
            settings.setDarkMode(isChecked)
        }

        switchNotif.setOnCheckedChangeListener { _, isChecked ->
            settings.setNotification(isChecked)
        }

        btnLogout.setOnClickListener {
            pref.logout()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }
}
