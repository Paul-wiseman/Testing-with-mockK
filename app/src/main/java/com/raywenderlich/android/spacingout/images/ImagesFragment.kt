/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.spacingout.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.raywenderlich.android.spacingout.R
import com.raywenderlich.android.spacingout.SpacingAnalytics
import com.raywenderlich.android.spacingout.createViewModel
import com.raywenderlich.android.spacingout.databinding.FragmentImagesBinding
import com.raywenderlich.android.spacingout.network.SpacingOutApi

class ImagesFragment : Fragment() {

  private var _binding:FragmentImagesBinding? = null
  private val binding get() = _binding!!
  private val viewModel by lazy {
    createViewModel { ImagesViewModel(ImageListProvider(SpacingOutApi.create()), SpacingAnalytics()) }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentImagesBinding.inflate(inflater, container,false)
    val adapter = ImagesAdapter()
    binding.list.layoutManager = LinearLayoutManager(container?.context)
    binding.list.adapter = adapter
    viewModel.imageLiveData.observe(this, Observer {
      adapter.items = it
    })
    viewModel.errorLiveData.observe(this, Observer {
      Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
    })

    return binding.root
  }
}