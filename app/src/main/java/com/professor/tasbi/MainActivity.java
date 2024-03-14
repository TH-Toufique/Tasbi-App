package com.professor.tasbi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private int number = 0;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.count);
        mediaPlayer = MediaPlayer.create(this, R.raw.tone);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            // Increase the number
            number++;
            updateNumber();
            return true; // Consume the event to prevent volume change
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            // Decrease the number
            number--;
            updateNumber();
            return true; // Consume the event to prevent volume change
        }
        return super.onKeyDown(keyCode, event);
    }
    private void updateNumber() {
        textView.setText(String.valueOf(number));
        if (number % 100 == 0 && number > 0)
        {
            playTone();
        }
    }
    private void playTone() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}