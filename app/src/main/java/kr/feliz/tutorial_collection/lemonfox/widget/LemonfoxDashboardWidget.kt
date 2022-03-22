package kr.feliz.tutorial_collection.lemonfox.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.appwidget.AppWidgetProviderInfo
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.SizeF
import android.view.View
import android.view.View.MeasureSpec.EXACTLY
import android.view.WindowManager
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.doOnAttach
import androidx.core.view.doOnLayout
import androidx.core.view.drawToBitmap
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.feliz.tutorial_collection.R
import kr.feliz.tutorial_collection.lemonfox.widget.LemonfoxDashboardWidget.Companion.TAG
import kr.feliz.tutorial_collection.lemonfox.widget.net.*
import kr.feliz.tutorial_collection.lemonfox.widget.utils.ChartInterval
import kr.feliz.tutorial_collection.lemonfox.widget.utils.ChartPeriod
import java.lang.Math.round

/**
 * Implementation of App Widget functionality.
 */
class LemonfoxDashboardWidget : AppWidgetProvider() {
    companion object{
        val TAG: String = "LemonfoxDashboardWidget"
        var screenWidth: Int = 0
        var screenHeight: Int = 0
        var dpi: Int = 0
        var density: Float = 0f
    }

    lateinit var context: Context
    
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        this.context = context

        val displayMetrics = context!!.resources.displayMetrics
        screenWidth = displayMetrics.widthPixels
        screenHeight = displayMetrics.heightPixels
        dpi = displayMetrics.densityDpi
        density = displayMetrics.density

        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        id: Int,
        newOptions: Bundle?
    ) {
        val option = newOptions!!
        super.onAppWidgetOptionsChanged(context, appWidgetManager, id, newOptions)
        val width = option.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH)
        val height = option.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT)
        Log.d(TAG, "LemonfoxDashboardWidget - 위젯 ${width} ${height}")

        val widgetWidth = round(width * density)
        val widgetHeight = round(height * density)
        Log.d(TAG, "LemonfoxDashboardWidget - 위젯 pixel ${widgetWidth} ${widgetHeight}")
    }
}



internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.widget_lemonfox_dashboard)

    val widgetText = context.getString(R.string.lemonfox_app_widget)
    views.setTextViewText(R.id.titleWidgetTextView, widgetText)

    val chartRepository: ChartRepository =
        ChartRepositoryImpl(
            ChartDataSource(
                RetrofitClient
                    .chart
                    .create(ChartApiService::class.java)
            )
        )

    CoroutineScope(Dispatchers.Main).launch{
        val response = chartRepository.getChartData("BTC","KRW", ChartPeriod.PERIOD_ONE_DAY,
            ChartInterval.INTERVAL_ONE_HOUR)
        Log.d(TAG, "updateAppWidget: ${response.responseData?.t}")
    }

    val view = LineChart(context)
    view.measure(View.MeasureSpec.makeMeasureSpec(500, EXACTLY), View.MeasureSpec.makeMeasureSpec(500, EXACTLY))
    view.layout(0,0,500,500)
    val bitmap = view.drawToBitmap()
    // val bitmap: Bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
    // val canvas: Canvas = Canvas(bitmap)
    // val result = view.draw(canvas)
    view.apply {
        setBackgroundColor(Color.WHITE)
        description.isEnabled = false
        setScaleEnabled(false)
        legend.isEnabled = false
        setNoDataText("")
        setViewPortOffsets(0f, 0f, 0f, 0f)

        val xAxis = this.xAxis
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        axisRight.isEnabled = false
        val yAxis = this.axisLeft
        yAxis.setDrawAxisLine(false)
        yAxis.setDrawGridLines(false)
        yAxis.setDrawTopYLabelEntry(false)
        yAxis.setDrawLabels(false)
        yAxis.setDrawZeroLine(false)
        yAxis.setDrawLimitLinesBehindData(false)
    }
    views.setImageViewBitmap(R.id.lineChart,bitmap)
    Toast.makeText(context, "레이아웃이 생성되었습니다.", Toast.LENGTH_SHORT).show()

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}