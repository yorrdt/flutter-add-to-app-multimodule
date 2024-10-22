package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.flutter.FlutterInjector
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.dart.DartExecutor


class MainActivity : AppCompatActivity() {

    private lateinit var engineGroup: FlutterEngineGroup

    companion object Factory {
        const val firstModuleEngineId = "id_of_first_flutter_engine"
        const val firstModuleEntrypoint = "startFirstModule"

        const val secondModuleEngineId = "id_of_second_flutter_engine"
        const val secondModuleEntrypoint = "startSecondModule"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val engineGroup = FlutterEngineGroup(this)
        val loader = FlutterInjector.instance().flutterLoader()

        val firstEngine = engineGroup.createAndRunEngine(
            this,
            DartExecutor.DartEntrypoint(
                loader.findAppBundlePath(),
                firstModuleEntrypoint,
            ),
        )
        val secondEngine = engineGroup.createAndRunEngine(
            this,
            DartExecutor.DartEntrypoint(
                loader.findAppBundlePath(),
                secondModuleEntrypoint,
            ),
        )

        FlutterEngineCache.getInstance().put(
            firstModuleEngineId,
            firstEngine,
        )
        FlutterEngineCache.getInstance().put(
            secondModuleEngineId,
            secondEngine,
        )


        Log.d("FlutterEngine", "Start")

        val firstModuleButton = findViewById<Button>(R.id.open_module1_button)
        firstModuleButton.setOnClickListener {
            val intent = FlutterActivity
                .withCachedEngine(firstModuleEngineId)
                .build(this)

            Log.d("FlutterEngine", "Run first module")
            startActivity(intent)
        }

        val secondModuleButton = findViewById<Button>(R.id.open_module2_button)
        secondModuleButton.setOnClickListener {
            val intent = FlutterActivity
                .withCachedEngine(secondModuleEngineId)
                .build(this)

            Log.d("FlutterEngine", "Run second module")
            startActivity(intent)
        }
    }
}