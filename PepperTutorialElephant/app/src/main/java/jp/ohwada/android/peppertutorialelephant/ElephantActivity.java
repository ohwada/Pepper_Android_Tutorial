package jp.ohwada.android.peppertutorialelephant;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;


public class ElephantActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elephant);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer = MediaPlayer.create(this, R.raw.elephant_sound);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, R.raw.elephant_sound);
        }
        Animation animation = Animation.fromResources(this, R.raw.elephant);
        Animate animate = new Animate(this);
        animate.setOnStartedListener(new Animate.OnStartedListener() {
            @Override
            public void onStarted() {
                mediaPlayer.start();
            }
        });
        animate.run(animation);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}