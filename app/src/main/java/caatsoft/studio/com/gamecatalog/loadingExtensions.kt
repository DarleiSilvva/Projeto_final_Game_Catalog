package caatsoft.studio.com.gamecatalog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment

fun Activity.startLoading():AlertDialog {
    val alertDialog: AlertDialog
    val builder = AlertDialog.Builder(this)
    val inflater = this.layoutInflater
    builder.setView(inflater.inflate(R.layout.popup_loading, null))
    builder.setCancelable(true)
    alertDialog = builder.create()
    alertDialog.show()
    return alertDialog
}

fun Activity.dismissDialog(alertDialog: AlertDialog?) {
    alertDialog?.dismiss()
}

fun Fragment.startLoading():AlertDialog {
    val alertDialog: AlertDialog
    val builder = AlertDialog.Builder(requireContext())
    val inflater = requireActivity().layoutInflater
    builder.setView(inflater.inflate(R.layout.popup_loading, null))
    builder.setCancelable(true)
    alertDialog = builder.create()
    alertDialog.show()
    return alertDialog
}

fun Fragment.dismissDialog(alertDialog: AlertDialog?) {
    alertDialog?.dismiss()
}