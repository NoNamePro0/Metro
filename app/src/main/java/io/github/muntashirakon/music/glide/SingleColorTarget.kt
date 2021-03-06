package io.github.muntashirakon.music.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView
import code.name.monkey.appthemehelper.util.ATHUtil
import io.github.muntashirakon.music.R
import io.github.muntashirakon.music.glide.palette.BitmapPaletteTarget
import io.github.muntashirakon.music.glide.palette.BitmapPaletteWrapper
import io.github.muntashirakon.music.util.ColorUtil
import com.bumptech.glide.request.animation.GlideAnimation


abstract class SingleColorTarget(view: ImageView) : BitmapPaletteTarget(view) {

    protected val defaultFooterColor: Int
        get() = ATHUtil.resolveColor(view.context, R.attr.colorControlNormal)

    abstract fun onColorReady(color: Int)

    override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
        super.onLoadFailed(e, errorDrawable)
        onColorReady(defaultFooterColor)
    }

    override fun onResourceReady(
        resource: BitmapPaletteWrapper?,
        glideAnimation: GlideAnimation<in BitmapPaletteWrapper>?
    ) {
        super.onResourceReady(resource, glideAnimation)
        resource?.let {
            onColorReady(
                ColorUtil.getColor(
                    it.palette,
                    ATHUtil.resolveColor(view.context, R.attr.colorPrimary)
                )
            )
        }
    }
}