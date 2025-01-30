package com.u063.paymentsmaster;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

public class Load {
    private Context context;
    etxListener etxListener;
    private ArrayList<LinearLayout> ll = new ArrayList<>();
    private ArrayList<EditText> etx = new ArrayList<>();
    private ArrayList<Integer> pays = new ArrayList<>();
    public void setContext(Context c){
        context = c;
    }
    public void setEtxListener(etxListener etxl){
        etxListener = etxl;
    }
    public void load(){
        File f = new File(context.getFilesDir().getAbsolutePath()+"/payments");
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f.getAbsolutePath()));
            String s;
            int num=0;
            while((s=bf.readLine())!=null){
                pays.add(Integer.parseInt(s));
                LinearLayout payment = new LinearLayout(context);

                EditText etx = new EditText(context);
                etx.setText(s);

                Button b = new Button(context);
                b.setText("Delete");
                num+=1;
                b.setId(num);
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
                etxListener.addEditText(etx);
                etxListener.addLinearLayout(payment);
            }
            bf.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Integer> getPays(){
        return pays;
    }
}
