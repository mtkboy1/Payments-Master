package com.u063.paymentsmaster;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class etxListener {
    private ArrayList<EditText> etx = new ArrayList<>();
    private ArrayList<LinearLayout> linear = new ArrayList<>();
    private Context context;
    public void deleteEditText(int i){
        linear.get(i-1).setVisibility(View.GONE);
        etx.get(i-1).setText("");
    }
    public void setContext(Context c){
        context=c;
    }
    public ArrayList<LinearLayout> getLinear(){
        return linear;
    }
    public void addEditText(EditText e){
        etx.add(e);
    }
    public void addLinearLayout(LinearLayout e){
        linear.add(e);
    }
    public void start(){
        Thread th = new Thread(new Runnable() {
            @Override
            public void run(){
                Timer thread = new Timer();

                thread.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        File f = new File(context.getFilesDir().getAbsolutePath()+"/payments");
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        OutputStream outF = null;
                        BufferedOutputStream out = null;
                        try {
                            outF = new FileOutputStream(f.getAbsolutePath());
                            out = new BufferedOutputStream(outF);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        for(int i=0; i<etx.size(); i++){
                            if(!etx.get(i).getText().toString().isEmpty()) {
                                try {
                                    out.write((etx.get(i).getText().toString()+"\n").getBytes());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        try {
                            out.close();
                            outF.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },0,300);
            }
        });
        th.start();
    }
    public void save(){
        Thread th = new Thread(new Runnable() {
            @Override
            public void run(){
                File f = new File(context.getFilesDir().getAbsolutePath()+"/payments");
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                OutputStream outF = null;
                BufferedOutputStream out = null;
                try {
                    outF = new FileOutputStream(f.getAbsolutePath());
                    out = new BufferedOutputStream(outF);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                for(int i=0; i<etx.size(); i++){
                    if(!etx.get(i).getText().toString().isEmpty()) {
                        try {
                            out.write((etx.get(i).getText().toString()+"\n").getBytes());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                try {
                    out.close();
                    outF.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        th.start();
    }
}
