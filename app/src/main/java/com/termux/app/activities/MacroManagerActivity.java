package com.termux.app.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.termux.R;

public class MacroManagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro_manager);
        findViewById(R.id.button_close_macro_manager).setOnClickListener(v -> finish());
    }
}
