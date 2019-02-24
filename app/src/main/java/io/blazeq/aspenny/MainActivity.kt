package io.blazeq.aspenny

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kizitonwose.time.days
import io.blazeq.aspenny.model.Source
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add_user.setOnClickListener {
            val item = Source.cigarette(10.days, 10.0, 1000.0)
            Database.addItem(item)
        }
    }
}
