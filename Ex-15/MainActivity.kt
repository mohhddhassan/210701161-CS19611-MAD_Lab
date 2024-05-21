package org.rajalakshmi.camera

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var img : ImageView
    private val REQUEST_CODE_CAMERA = 1001;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img  = findViewById(R.id.img)
        val btpic : Button = findViewById(R.id.btpic)
        btpic.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,1001)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1001 && resultCode == RESULT_OK){
            val photo = data!!.extras!!["data"] as Bitmap
            img.setImageBitmap(photo)
        }
    }
}
