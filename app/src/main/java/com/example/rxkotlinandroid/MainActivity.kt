package com.example.rxkotlinandroid

import android.annotation.SuppressLint
import android.database.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.rxkotlinandroid.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createObservableObject()
            .subscribe({
                binding.tv.text = it
            },{
                binding.tv.text = it.message
            })
    }

    fun createObservableObject(): io.reactivex.rxjava3.core.Observable<String> {
        //observableni yaratish uchun create func. use
        return io.reactivex.rxjava3.core.Observable.create(object : ObservableOnSubscribe<String>{
            override fun subscribe(emitter: ObservableEmitter<String>) {
                binding.edit.addTextChangedListener {
                    emitter.onNext(it.toString())
                }
            }
        })
    }
}