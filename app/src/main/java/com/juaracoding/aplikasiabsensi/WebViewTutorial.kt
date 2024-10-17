package com.juaracoding.aplikasiabsensi

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WebViewTutorial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view_tutorial)
        val txtAndroid = findViewById<EditText>(R.id.txtAndroid)
        val btnSendToWeb = findViewById<Button>(R.id.btnSendToWeb)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(WebAppInterface(), "AndroidInterface")

        webView.loadUrl("https://3f50-103-165-222-114.ngrok-free.app/webview/")

        btnSendToWeb.setOnClickListener{
            val message = txtAndroid.text.toString()
            webView.evaluateJavascript("javascript:recieveFromAndroid('$message');", null)
        }

    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(applicationContext, toast, Toast.LENGTH_SHORT).show()
        }
    }
}