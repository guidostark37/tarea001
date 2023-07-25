package com.guido.tarea001

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Video.Media
import java.util.regex.Pattern
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.guido.tarea001.databinding.ActivityMainBinding
import config.SharedPreferentApplication.Companion.preferencia
import config.prefs

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mp: MediaPlayer
    lateinit var mpfondo: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sonido("intro",true)
        binding.button.setOnClickListener{

            binding.button.animate().setDuration(2000).scaleX(1.5f).scaleY(1.5f).withEndAction {
                if(validar()){
                    sonido("dbzgrito")
                    mpfondo.stop()
                    guardar()
                    startActivity(Intent(this,mostrar::class.java))
                }else {
                    sonido("estupido")
                    mpfondo.stop()
                    mpfondo.release()
                    binding.button.animate().setDuration(2000).scaleX(1f).scaleY(1f).start()
                }
            }.start()



        }
       binding.txtemail.addTextChangedListener {
           var esValido = Pattern.matches("^[A-Za-z]{1}[A-Za-z0-9]{3,}([-._]||)[A-Za-z0-9]{4,}@[a-z]{3,}[.][a-z]{2,}", binding.txtemail.text)
           if (esValido){
              binding.txtemail.setTextColor(Color.BLUE)
           }else{
               binding.txtemail.setTextColor(Color.RED)

           }

       }


    }




    private fun guardar() {
        preferencia.guardarUser(binding.txtnombre.text.toString())
        preferencia.guardarlast_name(binding.txtapellido.text.toString())
        preferencia.guardarmail(binding.txtemail.text.toString())
        preferencia.guardarphone(binding.txttelefono.text.toString())
        Toast.makeText(this, "se guardo correctamente", Toast.LENGTH_SHORT).show()


    }


    private fun validar(): Boolean {
        var esValido = true
        binding.apply {
            var lista_validar = mutableListOf<EditText>(txtnombre,txtapellido,txtemail,txttelefono)
            lista_validar.forEach {
                if (it.text.isNullOrEmpty()) {
                    it.error = "Requerido"
                    esValido = false
                } else it.error = null
            }
        }
        return esValido
    }

    private fun sonido(nombre_sonido: String, loop: Boolean=false) {
        var resID=resources.getIdentifier(
            nombre_sonido,"raw",packageName
        )
        if (nombre_sonido=="intro"){
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