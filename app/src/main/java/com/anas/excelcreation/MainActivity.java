package com.anas.excelcreation;

import static java.nio.file.StandardOpenOption.APPEND;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class MainActivity extends AppCompatActivity {


    EditText eName, eFileName;
    Button btnCreate;

    File filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.eName);
        eFileName = findViewById(R.id.eFileName);
        btnCreate = findViewById(R.id.btnCreate);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}
                , PackageManager.PERMISSION_GRANTED);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + eFileName.getText().toString() + ".xls");

                HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
                HSSFSheet hssfSheet = hssfWorkbook.createSheet("MySheet");

                HSSFRow hssfRow = hssfSheet.createRow(1);
                HSSFCell hssfCell = hssfRow.createCell(0);

                hssfCell.setCellValue(eName.getText().toString());


                try {
                    if (!filePath.exists()) {
                        filePath.createNewFile();
                    }

                    FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                    hssfWorkbook.write(fileOutputStream);
                    Toast.makeText(MainActivity.this, "File Createda", Toast.LENGTH_SHORT).show();

                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }
}