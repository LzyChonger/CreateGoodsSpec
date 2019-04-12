package com.lzy.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lzy.
 */
public class MainActivity extends AppCompatActivity {
    private static final int SELECT_SPEC = 102;

    private Button mBtn;
    private TextView mTv_goods_spec;
    private List<StoreManagerListEntity.GuigesEntity> mGuiges;
    private List<StoreManagerListEntity.SkuListEntity> mGood_spec;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.btn);
        mTv_goods_spec = (TextView) findViewById(R.id.tv_goods_spec);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoodsSpecActicity.class);
                if (mGood_spec != null) {
                    intent.putExtra("good_spec", (Serializable) mGood_spec);
                }
                if(mGuiges !=null){
                    intent.putExtra("good_guige", (Serializable) mGuiges);
                }
                startActivityForResult(intent, SELECT_SPEC);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case SELECT_SPEC:
                mGood_spec = (List<StoreManagerListEntity.SkuListEntity>) data.getSerializableExtra("good_spec");
                mGuiges = (List<StoreManagerListEntity.GuigesEntity>) data.getSerializableExtra("good_guige");
                if (mGood_spec == null || mGood_spec.size() == 0) {
                    mTv_goods_spec.setHint("未填写");
                } else {
                    ;
                    mTv_goods_spec.setText("Guiges:" + JSON.toJSONString(mGuiges)
                            + "\nGood_spec:" + JSON.toJSONString(mGood_spec));
                }
                break;
            default:
        }
    }
}
