package com.serhiiromanchuk.spendless.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Box
import androidx.glance.layout.wrapContentSize
import com.serhiiromanchuk.spendless.MainActivity
import com.serhiiromanchuk.spendless.R


object SpendLessWidget : GlanceAppWidget() {
    const val KEY_WIDGET_INTENT = "widget_intent_key"

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            WidgetContent()
        }
    }
}

class EchoWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = SpendLessWidget

}

@Composable
fun WidgetContent() {
    val widgetKey = ActionParameters.Key<Boolean>(SpendLessWidget.KEY_WIDGET_INTENT)
    Box (
        modifier = GlanceModifier
            .wrapContentSize()
            .clickable(actionStartActivity<MainActivity>(
                actionParametersOf(widgetKey to true)
            ))
    ) {
        Image(
            provider = ImageProvider(R.drawable.ic_widget),
            contentDescription = null,
        )
    }
}