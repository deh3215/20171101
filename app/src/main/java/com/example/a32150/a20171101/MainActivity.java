package com.example.a32150.a20171101;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    ImageView iv,iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView)findViewById(R.id.imageView);
        iv2 = (ImageView)findViewById(R.id.imageView2);

        tv = (TextView) findViewById(R.id.textview);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        final StringRequest request = new UTF8StringRequest("https://udn.com/rssfeed/news/2/6638?ch=news",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NET", response);
                        tv.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

       final ImageRequest imgRequest = new ImageRequest("https://6.share.photo.xuite.net/daftbear851/1635080/6955188/270852556_m.jpg", new Response.Listener<Bitmap>(){

           @Override
           public void onResponse(Bitmap response) {
               iv.setImageBitmap(response);
           }
       },0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
        queue.add(imgRequest);
        queue.start();
        //Picasso.with(MainActivity.this).load("http://square.github.io/picasso/static/sample.png").into(iv2);
        Glide.with(MainActivity.this).load("https://vignette.wikia.nocookie.net/jjba/images/8/84/Oraoraora_on_Dan.gif").asGif().into(iv2);
    }
}
