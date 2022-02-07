package kg.geektech.youtubeapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kg.geektech.youtubeapi.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (intent != null) {
            Toast.makeText(this, intent.getStringExtra("channelId"), Toast.LENGTH_SHORT).show()
        }
    }
}