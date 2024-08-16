package com.round.dash.libs

import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.graphics.drawable.toDrawable

fun View.setRoundedDashBackground(
    @Px dashThickness: Int,
    @Px dashLength: Int,
    @Px dashGap: Int,
    @Px cornerRadius: Int,
    @ColorInt dashColorInt: Int,
    @ColorInt backgroundColorInt: Int = Color.TRANSPARENT
) {
    runRound(
        width = this.width,
        height = this.height,
        dashThickness = dashThickness,
        dashLength = dashLength,
        dashGap = dashGap,
        cornerRadius = cornerRadius,
        dashColorInt = dashColorInt,
        backgroundColorInt = backgroundColorInt
    )
}

fun View.setRoundedDashBackground(
    @Px width: Int,
    @Px height: Int,
    @Px dashThickness: Int,
    @Px dashLength: Int,
    @Px dashGap: Int,
    @Px cornerRadius: Int,
    @ColorInt dashColorInt: Int,
    @ColorInt backgroundColorInt: Int = Color.TRANSPARENT
) {
    runRound(
        width = width,
        height = height,
        dashThickness = dashThickness,
        dashLength = dashLength,
        dashGap = dashGap,
        cornerRadius = cornerRadius,
        dashColorInt = dashColorInt,
        backgroundColorInt = backgroundColorInt
    )
}


private fun View.runRound(
    @Px width: Int,
    @Px height: Int,
    @Px dashThickness: Int,
    @Px dashLength: Int,
    @Px dashGap: Int,
    @Px cornerRadius: Int,
    @ColorInt dashColorInt: Int,
    @ColorInt backgroundColorInt: Int = Color.TRANSPARENT
) {
    if (width <= 0 && height <= 0) {
        return
    }

    val thickness = dashThickness.toFloat()
    val halfThickness = thickness / 2f
    val gap = dashGap.toFloat()
    val length = dashLength.toFloat()
    val corner = cornerRadius.toFloat()

    val paintDash: Paint = Paint().apply {
        color = dashColorInt
        style = Paint.Style.STROKE
        strokeWidth = thickness
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
        isDither = true
        pathEffect = DashPathEffect(
            floatArrayOf(
                length, gap
            ), gap
        )
    }

    val paintBackground: Paint = Paint().apply {
        color = backgroundColorInt
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    val path = Path()
    val rectF = RectF()

    this.background = createBitmap(width, height).applyCanvas {
        rectF.set(
            halfThickness,
            halfThickness,
            width.toFloat() - halfThickness,
            height.toFloat() - halfThickness
        )

        path.addRoundRect(rectF, corner, corner, Path.Direction.CW)
        drawRoundRect(rectF, corner, corner, paintBackground)
        drawPath(path, paintDash)
    }.toDrawable(this.resources)
}