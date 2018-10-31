package com.yangxiong.gisuper.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitManager.loadRecordListData("20181031", "", 1, 20,
                new Subscriber<RecordedListBean>( ) {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(RecordedListBean recordedListBean) {
                        Log.e(TAG, "recordedListBean: " + recordedListBean.getPayload( ).size( ));
                    }
                });



        /*Call<RecordedListBean> recordedListBeanCall = RetrofitHelp.getInstance()
                .createInterface(ServiceInterface.class).loadRecordListData(
                "20181031", "", 1, 20);

        recordedListBeanCall.enqueue(new Callback<RecordedListBean>( ) {
            @Override
            public void onResponse(Call<RecordedListBean> call, Response<RecordedListBean> response) {
                RecordedListBean body = response.body( );
                Toast.makeText(MainActivity.this, "" + body.getPayload( ).size( ), Toast.LENGTH_SHORT).show( );
            }

            @Override
            public void onFailure(Call<RecordedListBean> call, Throwable t) {

            }

        });*/

     /*   RetrofitHelp.getInstance( )
                .createInterface(ServiceInterface.class)
                .loadRecordListData("20181031", "", 1, 20)
                .subscribeOn(Schedulers.io( ))
                .doOnNext( recordedListBean -> {
                    recordedListBean.getPayload().remove(1);
                    recordedListBean.getPayload().remove(2);
                    recordedListBean.getPayload().remove(3);
                    recordedListBean.getPayload().remove(4);
                    recordedListBean.getPayload().remove(5);
                }).observeOn(AndroidSchedulers.mainThread( ))
                .subscribe(new Subscriber<RecordedListBean>( ) {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "onError 2 " + e.getMessage(), Toast.LENGTH_SHORT).show( );
                    }

                    @Override
                    public void onNext(RecordedListBean recordedListBean) {
                        Toast.makeText(MainActivity.this, "onNext 2 : " + recordedListBean.getPayload( ).size( ), Toast.LENGTH_SHORT).show( );
                    }
                });*/

    }
}
