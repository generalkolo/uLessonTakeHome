package com.gokada.core.utils.transformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.eftimoff.viewpagertransformers.BaseTransformer

class WalletSetupPagerTransformer<TRANSFORMER: BaseTransformer> (
    private val transformer: TRANSFORMER
): ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        transformer.transformPage(page, position)
    }
}