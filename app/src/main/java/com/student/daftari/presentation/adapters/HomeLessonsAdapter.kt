package com.student.daftari.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.student.daftari.R
import com.student.daftari.data.model.LessonItem
import com.student.daftari.presentation.home.HomeScreen
import kotlinx.android.synthetic.main.daftar_item.view.*

class HomeLessonsAdapter :
    androidx.recyclerview.widget.ListAdapter<LessonItem, HomeLessonsAdapter.HomeLessonsVh>(
        HomeLessonsItemCallback
    ) {

    inner class HomeLessonsVh(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun onBind(data: LessonItem) {
            itemView.apply {
                element_count_txt.text ="${data.elementCount}+ta element"
                title.text =data.title
                Glide.with(context).load(data.imgUrl).into(daftar_img)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeLessonsVh {
        return HomeLessonsVh(
            LayoutInflater.from(parent.context).inflate(R.layout.daftar_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeLessonsVh, position: Int) {
        holder.onBind(getItem(position))
    }

    object HomeLessonsItemCallback : DiffUtil.ItemCallback<LessonItem>() {
        override fun areItemsTheSame(oldItem: LessonItem, newItem: LessonItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LessonItem, newItem: LessonItem): Boolean {
            return oldItem == newItem
        }

    }
}