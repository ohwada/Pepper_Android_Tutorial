package jp.ohwada.android.peppertutorialhumansaround;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aldebaran.qi.sdk.object.actuation.Actuation;
import com.aldebaran.qi.sdk.object.actuation.Frame;
import com.aldebaran.qi.sdk.object.geometry.Transform;
import com.aldebaran.qi.sdk.object.geometry.TransformTime;
import com.aldebaran.qi.sdk.object.geometry.Vector3;
import com.aldebaran.qi.sdk.object.interaction.Human;
import com.aldebaran.qi.sdk.object.interaction.Interaction;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class HumansAroundActivity extends AppCompatActivity {

    private static final String TAG = "HumansAroundActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humans_around);

        final Actuation actuation = Actuation.get(this);
        Interaction.get(this).setHumansAroundListener(new Interaction
                .HumansAroundListener() {
            @Override
            public void onHumansAroundChanged(List<Human> humansAround) {
                Log.v(TAG, humansAround.size() + " humans around");
                // signals callbacks are called from a qi thread
                displayHumansInfo(actuation, humansAround);
            }
        });
    }

    private void displayHumansInfo(Actuation actuation, List<Human> humans) {
        try {
            Frame robotFrame = actuation.robotFrame();
            int i = 0;
            for (Human human : humans) {
                Frame humanFrame = human.getHeadFrame();
                // currently, lastKnowTransform never returns (the future never finishes)
                TransformTime tf = humanFrame.lastKnownTransform(robotFrame).get();
                double distance = distance(tf.getTransform());
                Log.v(TAG, "human " + i++ + " at " + distance);
            }
        } catch (ExecutionException e) {
            Log.e(TAG, "Cannot display humans infos", e);
        }
    }

    private static double distance(Transform transform) {
        Vector3 t = transform.getT();
        double x = t.getX();
        double y = t.getY();
        return Math.sqrt(x * x + y * y);
    }
}