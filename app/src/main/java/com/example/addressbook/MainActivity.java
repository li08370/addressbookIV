package com.example.addressbook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.addressbook.AddAddressService.addAddress;

public class MainActivity extends AppCompatActivity {

    private static boolean isBound;
    private static final String TAG = "RecyclerViewFragment";
    protected RecyclerView mRecyclerView;
    EditText inputAddress;
    private CustomAdapter addressAdapter;
    DeleteService deleteService;
    RecyclerView addressListView;
    Button addAddress;
    ToggleButton status;

    private static Address address;
    private static boolean adding = false;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addAddress = (Button) findViewById(R.id.addButton);
        status = (ToggleButton) findViewById(R.id.toggleButton);
        addressListView = (RecyclerView) findViewById(R.id.addressListView);
        addressAdapter = new CustomAdapter(AddAddressService.getStorage(),this);
        addressListView.setAdapter(addressAdapter);
        for(int i = 0; i < 4; i++){
            Address a = new Address(String.valueOf(i), String.valueOf(i), String.valueOf(i));
            addAddress(a);
        }
        addressAdapter.notifyDataSetChanged();
        /*
        final ArrayAdapter<String> arrayAdapter;
        final ArrayList<String> list = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        addressListView.setAdapter(arrayAdapter);
        list.add("String");
        arrayAdapter.notifyDataSetChanged();*/

        //if their is information to draw on from addPage
        if (adding || AddingPage.getEditing()) {
            Bundle extras = getIntent().getExtras();
            address = extras.getParcelable("Address");
            try {
                onButtonClick();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       /*addressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           String value;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(status.isChecked()) {
                //deleting
                    remove(position);
                }else {
                    //editing
                    AddingPage.setEditing(true);
                    Intent i = new Intent(getApplicationContext(), AddingPage.class);
                    i.putExtra("position", position);
                    startActivity(i);
                }
            }
        } );*/

        //opens AddingPage
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddingPage.class);
                startActivity(i);
            }
        });
    }
   /* public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        final ArrayAdapter<String> arrayAdapter;
        final ArrayList<String> list = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        mRecyclerView.setAdapter(arrayAdapter);
        list.add("String");
        arrayAdapter.notifyDataSetChanged();

        return rootView;
    }*/
    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DeleteService.AddressBinder binder = (DeleteService.AddressBinder) service;
            deleteService = binder.getService();
            isBound = true;
        }
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, DeleteService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Intent intent = new Intent(MainActivity.this, DeleteService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(mConnection);
            isBound = false;
        }
    }
    public static void setAdding(boolean b){
        adding = b;
    }
    //updates the listView addressList
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onButtonClick() throws Exception {
        addAddress(address);
        //arrayAdapter.clear();
       //arrayAdapter.addAll(AddAddressService.getStringStorage());
    }
    public void remove(int position){
        DeleteService.removeAddress(position);
        //arrayAdapter.clear();
        //arrayAdapter.addAll(AddAddressService.getStringStorage());
    }
}