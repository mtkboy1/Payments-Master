package com.u063.paymentsmaster;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    etxListener etxListener = new etxListener();
    Load load = new Load();
    int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etxListener.setContext(this);
        etxListener.start();
        load.setContext(this);
        load.setEtxListener(etxListener);
        load.load();

        LinearLayout payments = findViewById(R.id.payments);
        ArrayList<LinearLayout> ll = etxListener.getLinear();
        for(int i=0; i<ll.size(); i++){
            payments.addView(ll.get(i));
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        etxListener.save();
    }
    public void addPayment(View v){
        LinearLayout payments = findViewById(R.id.payments);
        LinearLayout payment = new LinearLayout(this);
        EditText etx = new EditText(this);
        Button b = new Button(this);

        b.setText("Delete");
        num+=1;
        b.setId(num);
        etxListener.addEditText(etx);
        etxListener.addLinearLayout(payment);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etxListener.deleteEditText(b.getId());
            }
        });
        etx.setLayoutParams(new ViewGroup.LayoutParams(475,ViewGroup.LayoutParams.WRAP_CONTENT));
        etx.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        payment.setLayoutParams(l);
        payment.addView(etx);
        payment.addView(b);
        payments.addView(payment);
    }
    public void graph(View v){
        etxListener.save();
        Intent i = new Intent(MainActivity.this, GraphActivity.class);
        //i.putExtra()
        startActivity(i);
    }
}