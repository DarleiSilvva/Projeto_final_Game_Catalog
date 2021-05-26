package caatsoft.studio.com.gamecatalog

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import caatsoft.studio.com.gamecatalog.DefaultCurrentActivityListener

class ActivityRetriever(val defaultCurrentActivityListener: DefaultCurrentActivityListener) {

    val layoutInflater: LayoutInflater =
        LayoutInflater.from(defaultCurrentActivityListener.currentActivity)

    val context: Context = defaultCurrentActivityListener.context

    fun getActivity(): Activity? {
        return defaultCurrentActivityListener.currentActivity
    }
}