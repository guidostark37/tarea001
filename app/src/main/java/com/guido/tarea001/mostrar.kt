package com.guido.tarea001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.graphics.ImageFormat
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import com.guido.tarea001.databinding.ActivityMostrarBinding
import config.SharedPreferentApplication.Companion.preferencia
import config.prefs

class mostrar : AppCompatActivity() {
    lateinit var binding: ActivityMostrarBinding
    lateinit var mp: MediaPlayer
    lateinit var mpfondo: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMostrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mostrar_contenido()

        binding.button2.setOnClickListener {
            borrar()
            binding.imageView2.setImageResource(R.drawable.adios)
            sonido("despedida")
            Handler(Looper.myLooper()!!).postDelayed(6000){
                startActivity(Intent(this,MainActivity::class.java))
                mpfondo.stop()
                mpfondo.release()
            }

        }
    }

    private fun borrar() {
        preferencia.guardarUser("")
        preferencia.guardarlast_name("")
        preferencia.guardarmail("")
        preferencia.guardarphone("")
        Toast.makeText(this, "se borro correctamente", Toast.LENGTH_SHORT).show()
    }

    private fun mostrar_contenido() {
     binding.apply {
         textView2.setText(preferencia.GetUser())
         textView3.setText(preferencia.Getlast_name())
         textView4.setText(preferencia.Getemail())
         textView5.setText(preferencia.Getphone())
     }
    }

    private fun sonido(nombre_sonido: String, loop: Boolean=false) {
        var resID=resources.getIdentifier(
            nombre_sonido,"raw",packageName
        )
        if (nombre_sonido=="despedida"){
            mpfondo= MediaPlayer.create(this,resID)
            mpfondo.isLooping=loop
            mpfondo.setVolume(0.10F,0.10F)
            if (!mpfondo.isPlaying){
                mpfondo.start()
            }
        }else{
            mp= MediaPlayer.create(this,resID)
            mp.setOnCompletionListener(MediaPlayer.OnCompletionListener {mediaPlayer ->
                mediaPlayer.stop()
                mediaPlayer.release()
            })
            if (!mp.isPlaying){
                mp.start()
            }

        }
    }

}