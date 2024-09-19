package com.fara.bookexplorer.ui.adapter

import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fara.bookexpolorer.databinding.LoadStateItemBinding

class BookLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<BookLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: LoadStateItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Loading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.errorMsg.visibility = View.GONE
                binding.retryButton.visibility = View.GONE
                // Set loading text
                binding.loadingText.text = "Please wait..."
            } else {
                binding.progressBar.visibility = View.GONE
                if (loadState is LoadState.Error) {
                    binding.errorMsg.text = loadState.error.localizedMessage
                    binding.errorMsg.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.VISIBLE
                } else {
                    binding.errorMsg.visibility = View.GONE
                    binding.retryButton.visibility = View.GONE
                }
            }
            binding.retryButton.setOnClickListener { retry() }
        }
    }
}

