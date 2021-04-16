package com.mkhairulramadhan.githubuser.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.net.toUri
import com.mkhairulramadhan.githubuser.R
import com.mkhairulramadhan.githubuser.view.MainActivity

/**
 * Implementation of App Widget functionality.
 */
class FavoriteWidget : AppWidgetProvider() {

    companion object{

        private fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            //intent to load data
            val intent = Intent(context, StackWidgetService::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()

            //onClick
            val oclickIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val onClickPendingIntent = PendingIntent.getActivity(context, 0, oclickIntent, 0)

            //set view
            val views = RemoteViews(context.packageName, R.layout.favorite_widget)
            views.setRemoteAdapter(R.id.stack_view, intent)
            views.setEmptyView(R.id.stack_view, R.id.userWidget_empty_view)
            views.setTextViewText(R.id.banner_text, context.getString(R.string.favorite_user))
            views.setTextViewText(R.id.userWidget_tv_tittle, context.getString(R.string.favorite_user))
            views.setTextViewText(R.id.userWidget_tv_message, context.getString(R.string.favorite_user))
            views.setOnClickPendingIntent(R.id.banner_text, onClickPendingIntent)

            //to update widget
            appWidgetManager.updateAppWidget(appWidgetId, views)

        }

        fun refreshDataWidget(context: Context) {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE).apply {
                component = ComponentName(context, FavoriteWidget::class.java)
            }
            context.sendBroadcast(intent)
        }

    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        intent.let{
            if (it.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE){
                val component = ComponentName(context, FavoriteWidget::class.java)
                AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(
                        AppWidgetManager.getInstance(context).getAppWidgetIds(component),
                        R.id.stack_view
                )
            }
        }
        super.onReceive(context, intent)
    }
}
