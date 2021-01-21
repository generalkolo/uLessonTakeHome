package com.ulesson.takehome.presentation.video

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ulesson.core.base.BaseFragment
import com.ulesson.takehome.BR
import com.ulesson.takehome.R
import com.ulesson.takehome.databinding.VideoFragmentBinding
import com.ulesson.takehome.presentation.MainActivity
import com.ulesson.takehome.presentation.MainActivityViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.util.Util
import javax.inject.Inject

class VideoFragment : BaseFragment<VideoFragmentBinding, MainActivityViewModel>() {
    @Inject
    lateinit var exoPlayer: SimpleExoPlayer

    @Inject
    lateinit var progressiveFactory: ProgressiveMediaSource.Factory

    private val getMainViewModel by lazy {
        (requireActivity() as MainActivity).mainActivityViewModel
    }

    private lateinit var binding: VideoFragmentBinding

    override fun getViewModel(): MainActivityViewModel = getMainViewModel

    override fun getLayoutId(): Int = R.layout.video_fragment

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutBinding(binding: VideoFragmentBinding) {
        this.binding = binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPlayer()
    }

    private fun setupPlayer() {
        getMainViewModel.lessonItem.observe(this, Observer {
            it?.let {
                with(binding) {
                    playerView.player = exoPlayer
                    exoPlayer.playWhenReady = false
                    exoPlayer.prepare(buildMediaSource(it.mediaUrl))
                }
            }
        })
    }

    private fun buildMediaSource(videoString: String): MediaSource =
        progressiveFactory.createMediaSource(Uri.parse(videoString))

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23 || binding.playerView.player == null) {
            setupPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        with(binding.playerView) {
            if (player != null) {
                player.release()
                player = null
            }
        }
    }
}

