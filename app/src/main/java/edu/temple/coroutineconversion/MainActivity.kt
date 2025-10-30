package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import kotlin.coroutines.CoroutineContext
import kotlin.div

class MainActivity : AppCompatActivity() {

    //TODO (Refactor to replace Thread code with coroutines)

    private val cakeImageView: ImageView by lazy {
        findViewById(R.id.imageView)
    }

    private val currentTextView: TextView by lazy {
        findViewById(R.id.currentTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.revealButton).setOnClickListener{
            CoroutineScope(Dispatchers.Default).launch{
                //where to run the couroutine is in the Default Dispatcher,
                //launch the following code
                repeat(100) {
                    //loop for 100 times
                    withContext(Dispatchers.Main){
                        //swap the Dispatcher to the UI
                        currentTextView.text = String.format(Locale.getDefault(), "Current opacity: %d", it)
                        cakeImageView.alpha = it / 100f
                    }
                    //swap back to delay
                    delay(40)
                }
            }
        }
    }
}