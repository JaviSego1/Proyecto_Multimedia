package com.example.pruebaintent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebaintent.databinding.FirstActivityExplicitBinding


class ConfActivity : AppCompatActivity() {

    private lateinit var firstActivityExplicitBinding: FirstActivityExplicitBinding
    private lateinit var sharedFich : SharedPreferences
    private lateinit var nameSharedPhone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstActivityExplicitBinding = FirstActivityExplicitBinding.inflate(layoutInflater)
        setContentView(firstActivityExplicitBinding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(firstActivityExplicitBinding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initPreferentShared()
        start()
    }

    private fun initPreferentShared() {
        val nameSharedFich = "shared fich"
        this.nameSharedPhone = "phone_number"

        this.sharedFich = getSharedPreferences(nameSharedFich, Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        val ret = intent.getBooleanExtra("back", false)
        if (ret){
            firstActivityExplicitBinding.editPhone.setText("")
            Toast.makeText(this,"Proporcione un número de teléfono", Toast.LENGTH_LONG).show()
            intent.removeExtra("back")
        }
    }



    private fun start() {
        val sharedPhone : String? = sharedFich.getString(nameSharedPhone, null)

        sharedPhone?.let {
            startMainActivty(it)
        }

        firstActivityExplicitBinding.btnConf.setOnClickListener {
            val numberPhone = firstActivityExplicitBinding.textView.text.toString()
            if (numberPhone.isEmpty())
                Toast.makeText(this, "Introduce un numero de teléfono válido", Toast.LENGTH_LONG).show()
            else
                if (!isValidPhoneNumber(numberPhone, "ES"))
                    Toast.makeText(this, "Teléfono no válido", Toast.LENGTH_SHORT).show()
            else {
                val edit = sharedFich.edit()
                    edit.putString(nameSharedPhone, numberPhone)

                }
        }

    }

    private fun startMainActivty(phone: String) {
        val intent = Intent(this@ConfActivity, MainActivity::class.java)
        intent.apply {
            putExtra("phone", phone)
            addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        }
        startActivity(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    fun isValidPhoneNumber(phoneNumber: String, countryCode: String): Boolean {

        return TODO("Provide the return value")
    }
}
