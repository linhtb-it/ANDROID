package com.example.listviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    Contact contac;
    EditText etName;
    EditText etPhone;
    ImageButton btnAdd;
    Spinner spin;
    String[] type = {"Friend", "Family", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_contact);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnAdd = findViewById(R.id.btnAdd);
        spin = findViewById(R.id.spinSelect);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1, type);
        spin.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = spin.getSelectedItem().toString().equals(type[2]);
                contac = new Contact(etName.getText().toString(),etPhone.getText().toString(),!check);
                Intent intent = new Intent(AddContact.this, MainActivity.class);
                //intent.putExtra("data",contac);
                intent.putExtra("name",etName.getText().toString());
                intent.putExtra("phone",etPhone.getText().toString());
                intent.putExtra("icon",!check);
                setResult(RESULT_OK,intent);
                Toast.makeText(getBaseContext(),"Thêm thành công !",Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

}
