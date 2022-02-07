package com.nads.landfind.ui.progressviews

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import com.nads.landfind.R

import android.os.Handler
import androidx.core.app.SharedElementCallback


class CustomProgressFrag:AbstractProgressFragment(R.layout.fragment_progress) {

    private var message: TextView? = null
    private var progressBar: ProgressBar? = null
    override fun onStart() {
        super.onStart()
        val handler = Handler()
        handler.postDelayed(
            Runnable {  },
            3000
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            message = findViewById(R.id.message)
            progressBar = findViewById(R.id.progressBar)
        }

    }

    override fun onProgress(status: Int, bytesDownloaded: Long, bytesTotal: Long) {
        progressBar?.progress = (bytesDownloaded.toDouble() * 100 / bytesTotal).toInt()
    }

    override fun onFailed(errorCode: Int) {
        message?.text = getString(R.string.install_failed)
    }

    override fun onCancelled() {
        message?.text = getString(R.string.install_cancelled)
    }
}