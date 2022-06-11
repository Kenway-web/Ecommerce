package eu.kenway.ecommerce.products.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.databinding.FragmentHelpBinding

// TODO: Rename parameter arguments, choose names that match

class Help : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle("Help")
        val view =inflater.inflate(R.layout.fragment_help, container, false)
        val mWebView: WebView =view?.findViewById<View>(R.id.webview) as WebView
        mWebView.loadUrl("file:///android_asset/help.html")


        return view
    }
}