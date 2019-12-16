package com.example.getbatteryinfodemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(mBroadcastReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private val mBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, info: Intent?) {
            if (Intent.ACTION_BATTERY_CHANGED == info?.action) {

                val level = info.getIntExtra("level", 0)
                val scale = info.getIntExtra("scale", 0)
                val status = info.getIntExtra("status", 0)
                val health = info.getIntExtra("health", 0)
                val temperature = info.getIntExtra("temperature", 0)
                val technology = info.getStringExtra("technology")

                val information = StringBuilder()
                information.append("Level:$level\n")
                information.append("Scale:$scale\n")
                information.append("Battery:$level%\n")
                when (status) {
                    BatteryManager.BATTERY_STATUS_CHARGING -> information.append("Status:" + "CHARGING" + "\n")
                    BatteryManager.BATTERY_STATUS_DISCHARGING -> information.append("Status:" + "DISCHARGING" + "\n")
                    BatteryManager.BATTERY_STATUS_FULL -> information.append("Status:" + "FULL" + "\n")
                    BatteryManager.BATTERY_STATUS_NOT_CHARGING -> information.append("Status:" + "NOT_CHARGING" + "\n")
                    BatteryManager.BATTERY_STATUS_UNKNOWN -> information.append("Status:" + "UNKNOWN" + "\n")
                }
                when (health) {
                    BatteryManager.BATTERY_HEALTH_DEAD -> information.append("Health:" + "DEAD" + "\n")
                    BatteryManager.BATTERY_HEALTH_GOOD -> information.append("Health:" + "GOOD" + "\n")
                    BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> information.append("Health:" + "OVER_VOLTAGE" + "\n")
                    BatteryManager.BATTERY_HEALTH_OVERHEAT -> information.append("Health:" + "OVERHEAT" + "\n")
                    BatteryManager.BATTERY_STATUS_UNKNOWN -> information.append("Health:" + "UNKNOWN" + "\n")
                    BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> information.append("Health:" + "UNSPECIFIED_FAILURE" + "\n")
                }

                information.append("Temperature:" + (temperature * 0.1).toString() + "\n")
                information.append("Technology:$technology\n")
                info_text.text = information
            }

        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(mBroadcastReceiver)
    }
}
