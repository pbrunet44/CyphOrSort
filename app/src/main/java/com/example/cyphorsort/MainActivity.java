package com.example.cyphorsort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final static int RESULT_LOAD_IMAGE = 1;
    private Uri imageUri = null;
    ImageView imgPreview;
    Button btnUploadImage;
    Button btnStartDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgPreview = findViewById(R.id.imgPreview);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnStartDemo = findViewById(R.id.btnStartDemo);
        Spinner spnAlgorithm = findViewById(R.id.spnAlgorithm);
        spnAlgorithm.setOnItemSelectedListener(this);

        List<String> algorithms = new ArrayList<>();
        algorithms.add("Caesar");
        algorithms.add("Atbash");
        algorithms.add("Vignere");
        algorithms.add("DES");
        algorithms.add("AES");
        algorithms.add("Bubble Sort");
        algorithms.add("Merge Sort");
        algorithms.add("Bogo Sort");
        ArrayAdapter<String> algorithmAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, algorithms);
        spnAlgorithm.setAdapter(algorithmAdapter);

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

        btnStartDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String algorithm = ((Spinner)findViewById(R.id.spnAlgorithm)).getSelectedItem().toString();
                if(imageUri != null)
                {
                    Bitmap imageBitmap = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();
                    
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            imageUri = selectedImage;
            imgPreview.setImageURI(selectedImage);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        //conditionSelected = parent.getItemAtPosition(position).toString();
        // For debugging purposes only
        //Toast.makeText(parent.getContext(), "Selected: " + conditionSelected, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
