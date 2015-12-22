package uk.amimetic.webdashboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class DashboardActivity : AppCompatActivity(), AnkoLogger {

    var webView:WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val wv = WebView(this)
        wv.dashboardise()
        setContentView(wv)
        webView = wv
    }

    override fun onStart() {
        super.onStart()
        webView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        val url = intent.extras.getString("url")
        webView?.loadUrl(url)
        debug("Loading: $url")
    }
}

inline fun WebView.dashboardise(){
    setWebViewClient(SimpleWebViewClient())
    settings.javaScriptEnabled = true //enable js
    settings.domStorageEnabled = true //enable local storage
}

class SimpleWebViewClient: WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return false
    }
}