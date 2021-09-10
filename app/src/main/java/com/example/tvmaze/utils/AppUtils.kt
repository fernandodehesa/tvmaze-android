package com.example.tvmaze.utils

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.text.Html
import androidx.appcompat.app.AlertDialog
import com.example.tvmaze.App
import com.example.tvmaze.R

class AppUtils {

    companion object {
        fun openBrowser(url: String){
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(url)
            App.getContext().startActivity(openURL)
        }

        fun HtmlToText(html: String?): String {

            if(html.isNullOrEmpty()) return ""

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
            } else {
                return Html.fromHtml(html).toString()
            }
        }

        fun showSpinnerDialog(context: Context): AlertDialog {
            val builder = AlertDialog.Builder(context)
            builder.setView(R.layout.dialog_spinner)

            val dialog = builder.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            return dialog
        }

        fun showSpinner(spinner: AlertDialog, isLoading: Boolean){
            if(isLoading) spinner.show()
            else spinner.dismiss()
        }
    }
}