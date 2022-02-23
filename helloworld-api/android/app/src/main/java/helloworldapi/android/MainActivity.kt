package helloworldapi.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
    }

    private fun loadData() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.alternative.me/fng/"

        val scoreButton = findViewById<Button>(R.id.score)
        val valueTextView = findViewById<TextView>(R.id.value)
        val classificationTextView = findViewById<TextView>(R.id.classification)
        val timestampTextView = findViewById<TextView>(R.id.timestamp)
        val nextUpdateTextView = findViewById<TextView>(R.id.nextUpdate)

        val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    val json = JSONObject(response)
                    val data = json.getJSONArray("data")

                    scoreButton.text = data.getJSONObject(0).getString("value")
                    valueTextView.text = data.getJSONObject(0).getString("value")
                    classificationTextView.text = data.getJSONObject(0).getString("value_classification")
                    timestampTextView.text = data.getJSONObject(0).getString("timestamp")
                    nextUpdateTextView.text = data.getJSONObject(0).getString("time_until_update")

                },
                {
                    val text = "Getting data failed!"
                    val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG)
                    toast.show()
                })

        queue.add(stringRequest)
    }

    fun moveToEasterEggActivity(view: View) {
        val intent = Intent(this, EasterEggActivity::class.java)
        startActivity(intent)
    }
}