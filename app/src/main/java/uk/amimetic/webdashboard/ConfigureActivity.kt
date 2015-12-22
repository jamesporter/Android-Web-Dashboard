package uk.amimetic.webdashboard

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import org.jetbrains.anko.*
import uk.amimetic.webdashboard.*

class ConfigureActivity : AppCompatActivity() {

    var prefs:SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        ConfigureActivityUI().setContentView(this)
    }

    fun openUrl(url:String) {
        prefs?.edit {
            set("lastUrl" to url)
        }
        startActivity<DashboardActivity>("url" to url)
    }
}

class ConfigureActivityUI: AnkoComponent<ConfigureActivity> {
    override fun createView(ui: AnkoContext<ConfigureActivity>) = ui.apply {
        verticalLayout {
            padding = dip(24)
            val editUrl = editText(ui.owner.prefs?.getString("lastUrl", "")) {
                hint = "url"
                inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
            }
            button("Open") {
                textSize = 18f
                onClick {
                    ctx.toast("Loading!")
                    ui.owner.openUrl(editUrl.text.toString())
                }
            }
            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                textView("Page will open full screen. To get back here swipe up from the bottom of the screen.")
            }
        }
    }.view
}
