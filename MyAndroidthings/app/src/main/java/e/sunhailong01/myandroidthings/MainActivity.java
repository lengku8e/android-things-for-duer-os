package e.sunhailong01.myandroidthings;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.baidu.iot.sdk.IoTSDKManager;
import com.baidu.iot.sdk.iam.AccessToken;
import com.baidu.iot.sdk.iam.IAMWebView;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends Activity {
    private static final String CLIENT_ID = "it2tQ28M4s17ej3RmZMQ30ru31esigTV";
    private static final String CLIENT_SC = "QRKygYkxstrdHb8Z6aBU1cn3XvfcGs0G";
    private LinearLayout mTopLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化sdk
        IoTSDKManager.getInstance().initSDK(this);
//        IoTSDKManager.getInstance().setAccessToken(AccessToken);
        mTopLinearLayout = (LinearLayout) findViewById(R.id.topLinearLayout);

        Log.d("====", "android things 启动成功");
        IAMWebView iamWebView = new IAMWebView(this);
//        iamWebView.setAkSk(CLIENT_ID, CLIENT_SC);
        iamWebView.login(new IAMWebView.LoginCallback() {
            @Override
            public void onFinish(boolean b) {
                if (b) {
                    Log.d("====", "登录成功");
                } else {
                    Log.d("====", "登录失败");
                }

            }
        });
        mTopLinearLayout.addView(iamWebView);
        AccessToken a = IoTSDKManager.getInstance().getAccessToken();
        Log.d("====","AccessToken"+a.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("====", "android things onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("====", "android things onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("====", "android things onDestroy");
    }
}
