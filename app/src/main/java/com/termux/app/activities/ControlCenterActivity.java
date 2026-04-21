package com.termux.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.termux.R;
import com.termux.app.TermuxActivity;
import com.termux.app.TermuxService;
import com.termux.shared.activity.ActivityUtils;

public class ControlCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_center);

        findViewById(R.id.card_wakelock).setOnClickListener(v -> toggleWakelock());
        findViewById(R.id.card_screen_on).setOnClickListener(v -> toggleScreenOn());
        findViewById(R.id.card_styling).setOnClickListener(v -> openStyling());
        findViewById(R.id.card_settings).setOnClickListener(v -> openSettings());
        findViewById(R.id.card_macros).setOnClickListener(v -> openMacros());
        findViewById(R.id.button_close_control_center).setOnClickListener(v -> finish());
    }

    private void toggleWakelock() {
        // Logic to toggle wakelock via TermuxService
        Intent intent = new Intent(this, TermuxService.class);
        intent.setAction(com.termux.shared.termux.TermuxConstants.TERMUX_SERVICE.ACTION_WAKE_LOCK);
        startService(intent);
    }

    private void toggleScreenOn() {
        // This is usually handled via preferences in TermuxActivity
        // For simplicity, we can just notify the user or try to update prefs
        // In a real scenario, we'd bind to TermuxActivity or use a shared event bus
    }

    private void openStyling() {
        // Termux styling is usually a dialog in TermuxActivity
        // We'll just point to settings for now or trigger the reload style broadcast
        TermuxActivity.updateTermuxActivityStyling(this, true);
    }

    private void openSettings() {
        ActivityUtils.startActivity(this, new Intent(this, SettingsActivity.class));
    }

    private void openMacros() {
        ActivityUtils.startActivity(this, new Intent(this, MacroManagerActivity.class));
    }
}
