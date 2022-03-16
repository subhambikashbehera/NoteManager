package com.subhambnikash.roompractice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.subhambnikash.roompractice.databinding.ActivityMainBinding
import com.subhambnikash.roompractice.db.DataBaseOperation
import com.subhambnikash.roompractice.db.NoteTable
import com.subhambnikash.roompractice.repo.RepositoryClass
import com.subhambnikash.roompractice.viewmodel.ViewModelMainActivity
import com.subhambnikash.roompractice.viewmodel.ViewModelProviderCustom

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PoetAdapter
    private lateinit var viewModelMainActivity: ViewModelMainActivity
    lateinit var viewModelProviderCustom: ViewModelProviderCustom
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao=DataBaseOperation.getInstance(application).daoObj
        val repositoryClass=RepositoryClass(dao)
        viewModelProviderCustom = ViewModelProviderCustom(repositoryClass)

        viewModelMainActivity = ViewModelProvider(this, viewModelProviderCustom)[ViewModelMainActivity::class.java]
        binding.viewModel=viewModelMainActivity
        binding.lifecycleOwner=this

        viewModelMainActivity.messageEvent.observe(this){
            it.getContentIfNotHandled().let { it1->
                Toast.makeText(this,it1.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        setUpData()








    }

    private fun setUpData() {
        val layoutManager=LinearLayoutManager(this)
        binding.dataRecyclerView.layoutManager=layoutManager
        adapter= PoetAdapter{onItemSelected:NoteTable->selectedItem(onItemSelected)}
        binding.dataRecyclerView.adapter=adapter
        getData()
    }

   private fun getData(){
       viewModelMainActivity.allNotes.observe(this){
           adapter.setList(it)
           adapter.notifyDataSetChanged()
       }
   }

    private fun selectedItem(noteTable: NoteTable){
        viewModelMainActivity.deleteUpdateInit(noteTable)
    }
}