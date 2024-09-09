package moduleAssistant

import GTP.app.databinding.MainActivityBinding
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var Binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        Binding.navigateToAssistantActivityButton.setOnClickListener {
            val intent = Intent(this, AssistantActivity::class.java)
            startActivity(intent)
        }
    }
}