package com.subhambnikash.roompractice.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.subhambnikash.roompractice.repo.RepositoryClass
import java.lang.IllegalArgumentException

class ViewModelProviderCustom(val repositoryClass: RepositoryClass):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass){
          ViewModelMainActivity::class.java->{
           ViewModelMainActivity(repositoryClass)
          }
            else -> throw IllegalArgumentException("error")
        } as T
    }





}