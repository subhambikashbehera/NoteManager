package com.subhambnikash.roompractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.subhambnikash.roompractice.databinding.RecyclerItemBinding
import com.subhambnikash.roompractice.db.NoteTable

class PoetAdapter(private val listener: (NoteTable) -> Unit) : RecyclerView.Adapter<PoetAdapter.MyViewHolder>() {
    var listData= arrayListOf<NoteTable>()

    inner class MyViewHolder(var binding:RecyclerItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun setData(noteTable: NoteTable,listener: (NoteTable) -> Unit) {
            binding.userId.text=noteTable.noteTittle
            binding.password.text=noteTable.noteDescription
            binding.itemLayout.setOnClickListener {
                listener(noteTable)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding:RecyclerItemBinding=DataBindingUtil.inflate(inflater,R.layout.recycler_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(listData[position],listener)
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    fun setList(newList:List<NoteTable>){
        listData.clear()
        listData.addAll(newList)
    }

}