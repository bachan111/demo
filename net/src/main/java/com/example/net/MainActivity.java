ngpackage com.example.net;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.net.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebview;
    private TextView mNetAuth;
    private TextView mPingResult;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebview = findViewById(R.id.mWebview);
        mNetAuth = findViewById(R.id.mNetAuth);
        mPingResult = findViewById(R.id.mPingResult);
        mNetAuth.setOnClickListener(this);
        mHandler = new Handler();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mNetAuth:
                mWebview.loadUrl("http://172.22.132.247/user/login");
                break;
            case R.id.ping:
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ping("csis.infosky.com.cn");
                    }
                });
                break;
        }
    }

    private void ping(String url){
        StringBuilder buffer = new StringBuilder();
        String result = ""; // 结果集
        Process ping;
        try {
            /*常用格式
              ping 网址 -n 次数 -l 大小 -w 超时时间(毫秒) -t 不停运行
              ping www.baidu.com -n 3 -l 32 -w 60
              但是实测这样并不行，于是找了其他的方法
              ping -c 3 -s 32 www.baibu.com
            */
            // ping = Runtime.getRuntime().exec("ping www.baidu.com -n 3 -l 32 ");
            ping = Runtime.getRuntime().exec("ping -c 1"+ url);
            int status = ping.waitFor(); // 状态
            if (status == 0){
                InputStream input = ping.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                String line = "";   // 长度
                while ((line = in.readLine()) != null) {
                    buffer.append(line).append("\n");

                }
                result = buffer.toString();
                // 输出结果
                System.out.println("============" + buffer.toString());
            }else {
                result = "test error";
            }



        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}