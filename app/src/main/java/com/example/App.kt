package com.example

import android.app.Application
import com.example.repository.Repository

class App: Application(){
    
    companion object{
        val repository: Repository by lazy {
            Repository()
        }
    }
}