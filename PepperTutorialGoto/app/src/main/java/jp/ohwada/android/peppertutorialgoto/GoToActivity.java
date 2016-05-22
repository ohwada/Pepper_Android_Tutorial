package jp.ohwada.android.peppertutorialgoto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aldebaran.qi.QiCallback;
import com.aldebaran.qi.sdk.object.actuation.Actuation;
import com.aldebaran.qi.sdk.object.actuation.Frame;
import com.aldebaran.qi.sdk.object.actuation.GoTo;
import com.aldebaran.qi.sdk.object.geometry.Quaternion;
import com.aldebaran.qi.sdk.object.geometry.Transform;
import com.aldebaran.qi.sdk.object.geometry.Vector3;

public class GoToActivity extends AppCompatActivity {

    private static final String TAG = "GoToActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to);

        Quaternion r = new Quaternion(0, 0, 0, 1);
        Vector3 t = new Vector3(1, 0, 0);
        Transform tf = new Transform(r, t);
        Frame robotFrame = Actuation.get(this).robotFrame();
        Frame robotAtStart = robotFrame.makeDetachedFrame(System.currentTimeMillis());
        Frame targetFrame = robotAtStart.makeStaticChildFrame(tf);

        Log.d(TAG, "running goto...");
        new GoTo(this).run(targetFrame).then(new QiCallback<Void>() {
            @Override
            public void onResult(Void ignore) {
                Log.d(TAG, "goto done");
            }

            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "goto error", error);
            }

            @Override
            public void onCancel() {
                Log.w(TAG, "goto cancel");
            }
        });
    }
}