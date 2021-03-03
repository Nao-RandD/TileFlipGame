package com.naorandd.tileslipgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ClearActivity extends Activity implements View.OnClickListener{

    Button backToTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);

        backToTitle = findViewById(R.id.back_to_title_bt);
        backToTitle.setOnClickListener(this);

        ImageView imageView = findViewById(R.id.imageview);
        imageView.setImageResource(R.drawable.congraturation_image);
    }

    @Override
    //ボタンが押された時の処理
    public void onClick(View view){
        //ここに遷移するための処理を追加する
        Intent intent = new Intent(this, TopActivity.class);
        startActivity(intent);
    }
}