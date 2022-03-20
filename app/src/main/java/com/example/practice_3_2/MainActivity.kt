package com.example.practice_3_2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editText)
        val radioGroup = findViewById<RadioGroup>(R.id.choise_group)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            var uri:Uri = Uri.EMPTY
            when(radioGroup.checkedRadioButtonId){
                R.id.radioButton_undefined ->{
                    var request = editText.text.toString()
                    val web = request.contains(Regex("[a-z]"))
                    val geolocation = request.contains(',')
                    if (web){
                        if(!(request.startsWith("http://") || request.startsWith("https://")))
                            request= "https://$request"
                        uri = Uri.parse(request)
                    }
                    if (geolocation){
                        uri = Uri.parse("geo:$request?z=10")
                    }
                    if (!(web||geolocation)){
                        uri = Uri.parse("tel:$request")
                    }
                }
                R.id.radioButton_web ->{
                    var request = editText.text.toString()
                    if(!(request.startsWith("http://") || request.startsWith("https://")))
                        request= "https://$request"
                    uri = Uri.parse(request)
                }
                R.id.radioButton_geo ->{
                    val request = editText.text.toString()
                    uri = Uri.parse("geo:$request?z=10")
                }
                R.id.radioButton_phone ->{
                    val request = editText.text.toString()
                    uri = Uri.parse("tel:$request")
                }
            }
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }
    }
}