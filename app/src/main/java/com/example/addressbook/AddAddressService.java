package com.example.addressbook;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.RequiresApi;
import okhttp3.*;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

public class AddAddressService extends Service {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    static ArrayList<Address> storage  = new ArrayList<>();
    private final IBinder mBinder = new AddressBinder();


    public class AddressBinder extends Binder {
        AddAddressService getService() {
            return AddAddressService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void addAddress(Address a) {
        storage.add(a);
        storage.sort(Address::compareTo);
    }

    public static Address getAddress(int position){
        return storage.get(position);
    }

    public static ArrayList<Address> getStorage(){
        return storage;
    }


    public static ArrayList<String> getStringStorage(){
        ArrayList<String> temp = new ArrayList<>();
        String s;
        for(int i = 0; i < storage.size(); i++){
            s = "First name: " + storage.get(i).getFirst_name() + ", Last name: " + storage.get(i).getLast_name() + ", \nPhone Number: " + storage.get(i).getPhone_number();
            temp.add(s);
        }
        return temp;
    }

    public static int size(){
        return storage.size();
    }

}
