package com.example.selectmultipleimages;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int PICK_IMAGE = 100;
    Uri imagenUri;
    Button btnGaleria;
    GridView gvImages;

    List<Uri> listaimagenes = new ArrayList<>();
    GridViewAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGaleria = findViewById(R.id.btnGaleria);
        gvImages = findViewById(R.id.gvImages);

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalery();
            }
        });
    }

    public void openGalery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select images"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        ClipData clipdata = data.getClipData();

        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            Toast.makeText(this, "---", Toast.LENGTH_LONG).show();
            //one
            if(clipdata == null){
                imagenUri = data.getData();
                listaimagenes.add(imagenUri);
            }
            else{
                //multi
                Toast.makeText(this, "here", Toast.LENGTH_LONG).show();
                for(int i = 0; i< clipdata.getItemCount(); i++){
                    listaimagenes.add(clipdata.getItemAt(i).getUri());
                }
            }
        }
        /*else{
            //multi
            Toast.makeText(this, "here", Toast.LENGTH_LONG).show();
            for(int i = 0; i< clipdata.getItemCount(); i++){
                listaimagenes.add(clipdata.getItemAt(i).getUri());
            }
        }*/

        baseAdapter = new GridViewAdapter(MainActivity.this, listaimagenes);
        gvImages.setAdapter(baseAdapter);
    }
}