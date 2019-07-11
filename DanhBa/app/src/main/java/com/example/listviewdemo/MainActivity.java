package com.example.listviewdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvContact;
    Contact contact;
    List<Contact> lstContact;
    AdapterContact adapterContact;
    ImageView imgAdd;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = findViewById(R.id.lvContact);
        imgAdd = findViewById(R.id.imgAdd);
        lstContact = new ArrayList<>();

        Intent intentTranfer = getIntent();

        lstContact.add(new  Contact("Linh1","0985096825",true));
        lstContact.add(new  Contact("Linh2","0985096825",false));
        lstContact.add(new  Contact("Linh3","0985096825",true));
        lstContact.add(new  Contact("Linh4","0985096825",false));
        lstContact.add(new  Contact("Linh5","0985096825",true));
        lstContact.add(new  Contact("Linh11","0985096825",true));
        lstContact.add(new  Contact("Linh21","0985096825",false));
        lstContact.add(new  Contact("Linh31","0985096825",true));
        lstContact.add(new  Contact("Linh41","0985096825",false));
        lstContact.add(new  Contact("Linh51","0985096825",true));
        lstContact.add(new  Contact("Linh12","0985096825",true));
        lstContact.add(new  Contact("Linh22","0985096825",false));
        lstContact.add(new  Contact("Linh32","0985096825",true));
        lstContact.add(new  Contact("Linh42","0985096825",false));
        lstContact.add(new  Contact("Linh52","0985096825",true));
        lstContact.add(new  Contact("Linh13","0985096825",true));
        lstContact.add(new  Contact("Linh23","0985096825",false));
        lstContact.add(new  Contact("Linh33","0985096825",true));
        lstContact.add(new  Contact("Linh43","0985096825",false));
        lstContact.add(new  Contact("Linh53","0985096825",true));

        adapterContact = new AdapterContact(lstContact);
        lvContact.setAdapter(adapterContact);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddContact.class);

                startActivityForResult(intent,1);

            }
        });
        adapterContact.notifyDataSetChanged();
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Contact conTactemp = lstContact.get(i);
//                Intent intent = new Intent(getBaseContext(),AddContact.class);
//                intent.putExtra("name",conTactemp.getName());
//                intent.putExtra("phone",conTactemp.getPhone());
//                startActivity(intent);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode == RESULT_OK){
                //contact = (Contact)data.getSerializableExtra("data");
                contact = new Contact(data.getStringExtra("name"),data.getStringExtra("phone"),data.getBooleanExtra("icon",false));
                lstContact.add(contact);
                adapterContact = new AdapterContact(lstContact);
                lvContact.setAdapter(adapterContact);
            }
        }
    }
}
