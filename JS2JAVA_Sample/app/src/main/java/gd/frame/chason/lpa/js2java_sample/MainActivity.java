package gd.frame.chason.lpa.js2java_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private  WebSettings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView= (WebView) findViewById(R.id.webView);
         settings = mWebView.getSettings();

        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/javaInterface.html");
        mWebView.addJavascriptInterface(MainActivity.this,"javaInterface");

        mWebView.setWebChromeClient(new WebChromeClient() {});

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mWebView.loadUrl("javascript:changeColor()");
                mWebView.evaluateJavascript("javascript:changeColor('hello')", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.e("chason",value);
                    }
                });
            }
        });
    }

    @JavascriptInterface
    public String  onSumResult(int number ){
        Log.e("chason","js 调用 java"+number);

        return "chason" ;
    }



}


