package com.example.mymuseum.LogIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mymuseum.Museu.MuseuMainActivity
import com.example.mymuseum.R
import com.example.mymuseum.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth


class ActivityLogIn : AppCompatActivity() {

    lateinit var binding : ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_log_in)

        supportActionBar?.hide()

        binding.login.setOnClickListener {
            login()
        }

        binding.registar.setOnClickListener {
            registar()
        }

    }


    private fun registar(){

        if (checkMail() && checkPass()){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailEdit.text.toString(),
                binding.passEdit.text.toString()
            ).addOnCompleteListener { 
                
                if(it.isSuccessful) {
                    Toast.makeText(baseContext, "Registado com sucesso", Toast.LENGTH_SHORT).show()
                    /*val museuIntent = Intent(this, MuseuMainActivity::class.java).apply {
                        putExtra("Email", binding.emailEdit.text)
                        putExtra("Senha", binding.passEdit.text)
                    }
                    startActivity(museuIntent)
                    finish()*/
                }
                else Toast.makeText(baseContext, "Ocorreu um erro a registar o utilizador", Toast.LENGTH_SHORT).show()
                
            }

        }
        else if(!checkMail()) Toast.makeText(baseContext, "Insira um email válido", Toast.LENGTH_SHORT).show()
        else Toast.makeText(baseContext, "Insira uma password válida", Toast.LENGTH_SHORT).show()

    }

    private fun login(){

        if (checkMail() && checkPass()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.emailEdit.text.toString(),
                binding.passEdit.text.toString()
            ).addOnCompleteListener {

                if(it.isSuccessful) {
                    Toast.makeText(baseContext, "Entrou com sucesso", Toast.LENGTH_SHORT).show()
                    val bundle = Bundle()
                    val museuIntent = Intent(this, MuseuMainActivity::class.java)

                    bundle.putString("email", binding.emailEdit.text.toString())
                    bundle.putString("senha", binding.passEdit.text.toString())

                    museuIntent.putExtras(bundle)

                    startActivity(museuIntent)
                    finish()
                }
                else Toast.makeText(baseContext, "Ocorreu um erro a entrar o utilizador", Toast.LENGTH_SHORT).show()

            }

        }
        else {
            if(!checkMail()) binding.emailEdit.setError("Insira um email válido")
            if(!checkPass()) binding.passEdit.setError("Insira uma password válida")
        }

    }


    private fun checkMail() :Boolean {
        return binding.emailEdit.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(binding.emailEdit.text).matches()
    }

    private fun checkPass() : Boolean {
        return binding.passEdit.text.length > 7
    }




}