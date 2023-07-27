package com.example.jalihara;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int image[] = {R.drawable.slideone, R.drawable.slidetwo, R.drawable.slidethree};

        viewFlipper = findViewById(R.id.viewFlipperHome);

//        for(int i = 0; i < image.length; i++){
//            flipperImages(image[i]);
//        }

        for(int images: image){
            flipperImages(images);
        }


    }

    public void flipperImages(int images){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(1000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }
}
