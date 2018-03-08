package com.vit.vitwanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.vit.vitwanandroid.bean.RxHomeBannerItem;
import com.vit.vitwanandroid.modul.TestFragmentActivity;
import com.vit.vitwanandroid.net.ApiWrapper;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn01, R.id.btn02})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn01:
                getHomeBanner();
                break;

            case R.id.btn02:
                startActivity(new Intent(this, TestFragmentActivity.class));
                break;

            default:
                break;
        }
    }

    private void getHomeBanner() {

        ApiWrapper.getInstance().getHomeBanner().subscribe(new Consumer<List<RxHomeBannerItem>>() {
            @Override
            public void accept(List<RxHomeBannerItem> rxHomeBannerItems) throws Exception {
                Toast.makeText(MainActivity.this, rxHomeBannerItems.get(0).getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}
