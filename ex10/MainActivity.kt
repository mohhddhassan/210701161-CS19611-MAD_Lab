package com.example.app10

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.example.app10.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var READ_PHONE_STATE_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetData.setOnClickListener {
            val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_PHONE_STATE),
                    READ_PHONE_STATE_PERMISSION_CODE)
            }

            val networkOperatorName = telephonyManager.networkOperatorName
            val phoneType = telephonyManager.phoneType
            val networkCountryISO = telephonyManager.networkCountryIso
            val SIMCountryISO = telephonyManager.simCountryIso
            val deviceSoftwareVersion = telephonyManager.deviceSoftwareVersion

            binding.tvNetworkOperatorName.text = networkOperatorName.toString()
            binding.tvPhoneType.text = phoneType.toString()
            binding.tvNetworkCountryISO.text = networkCountryISO.toString()
            binding.tvSIMCountryISO.text = SIMCountryISO.toString()
            binding.tvDeviceSoftwareVersion.text = deviceSoftwareVersion.toString()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == READ_PHONE_STATE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Code when permission is granted
                //val telephoneManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                //val deviceSoftwareVersion = telephoneManager.deviceSoftwareVersion
                //binding.tvDeviceSoftwareVersion.text = deviceSoftwareVersion.toString()

            }
            else {
                // Code when permission is denied
                Toast.makeText(applicationContext, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}