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
    ImageButton btnAdd2;
    Spinner spin;
    String[] type = {"Friend", "Family", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_contact);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd2 = findViewById(R.id.btnAdd2);
        spin = findViewById(R.id.spinSelect);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog dialog = new AlertDialog.Builder(AddContact.this)
                        .setTitle("Alert")
                        //.setMessage("Click OK")
                        .setSingleChoiceItems(type, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getBaseContext(),type[i],Toast.LENGTH_LONG).show();
                                etPhone.setText(type[i]);
                                etName.setText(type[i]);
                            }
                        })
                        .setMu
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                contac.setIcon(true);
                                contac.setName(etName.getText().toString());
                                contac.setPhone(etPhone.getText().toString());
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                intent.putExtra("name", contac.getName());
                                intent.putExtra("phone", contac.getPhone());
                                intent.putExtra("icon", contac.isIcon());
                                onBackPressed();
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getBaseContext(),"refesh",Toast.LENGTH_LONG).show();
                                etName.setText("");
                                etPhone.setText("");
                            }
                        })
                        .create();
                dialog.show();


            }
        });
        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(getBaseContext(),view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_contact,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.mnSave :
                                Toast.makeText(getBaseContext(),"SAVEEEEEEEEEE",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.mnReload :
                                Toast.makeText(getBaseContext(),"RELOADDDDDDDDDD",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.mnExit :
                                Toast.makeText(getBaseContext(),"EXITTTTTTTTTTT",Toast.LENGTH_LONG).show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, type);
        spin.setAdapter(arrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnSave:
                Toast.makeText(getBaseContext(), "Save", Toast.LENGTH_LONG).show();
                break;
            case R.id.mnReload:
                Toast.makeText(getBaseContext(), "Reload", Toast.LENGTH_LONG).show();
                break;
            case R.id.mnExit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
