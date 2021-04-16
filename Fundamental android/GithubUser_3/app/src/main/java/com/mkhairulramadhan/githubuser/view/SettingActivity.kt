package com.mkhairulramadhan.githubuser.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mkhairulramadhan.githubuser.R
import com.mkhairulramadhan.githubuser.alarm.AlarmReceiver
import com.mkhairulramadhan.githubuser.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val PREFERENCE = "Settings"
        private const val DAILY = "Daily"
    }

    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //button back
        binding.buttonBack.setOnClickListener(this)

        //button language
        binding.languageSetting.setOnClickListener(this)

        alarmReceiver = AlarmReceiver()
        sharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)

        checkReminder()
    }

    private fun checkReminder() {
        binding.reminderSetting.isChecked = sharedPreferences.getBoolean(DAILY, false)
        binding.reminderSetting.setOnCheckedChangeListener { _, checked ->
            if (checked){
                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEAT, getString(R.string.reminder_message))
            }else{
                alarmReceiver.cancelAlarm(this)
            }
            saveChangeReminder(checked)
        }
    }

    private fun saveChangeReminder(checked: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(DAILY, checked)
        editor.apply()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.language_setting -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
            R.id.button_back -> {
                onBackPressed()
            }
        }
    }


}