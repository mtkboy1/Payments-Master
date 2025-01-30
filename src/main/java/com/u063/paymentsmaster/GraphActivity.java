package com.u063.paymentsmaster;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {
    Bitmap bm;
    Load load = new Load();
    int result=0;
    etxListener etxListener = new etxListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_graphic);
        bm = Bitmap.createBitmap(640,1000, Bitmap.Config.ARGB_8888);
        load.setContext(this);
        load.setEtxListener(etxListener);
        load.load();
        draw();
        ImageView img = findViewById(R.id.graph);
        img.setImageBitmap(bm);
        TextView tx = findViewById(R.id.result);
        tx.setText(""+result);
    }
    void draw(){
        ArrayList<Integer> pays = load.getPays();
        int biggest=1000;
        for(int i=0; i<pays.size(); i++){
            if(pays.get(i)>biggest){
                biggest=pays.get(i);
            }
            result += pays.get(i);
        }
        for(int i=0; i<pays.size(); i++){
            Log.e("",""+((float) 1000 / biggest) * pays.get(i));
            for(float y = Math.abs(1000-(float) 1000 / biggest * pays.get(i)); y<1000; y++) {
                for (int x = i*(640 / pays.size()); x < (i+1)*(640 / pays.size()); x++) {
                    if (x > 0) {
                        bm.setPixel(x, (int) y, Color.GRAY);
                    }
                }
            }
        }
    }
    public void leave(View v){
        finish();
    }
}
