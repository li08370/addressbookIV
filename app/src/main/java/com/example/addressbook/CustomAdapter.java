package com.example.addressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ContactHolder> {
    private ArrayList<Address> AddressList;
    private Context mContext;
    public CustomAdapter(ArrayList<Address> contactsList, Context context) {
        this.AddressList = contactsList;
        this.mContext = context;
    }

    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.text_row_item, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public int getItemCount() {
        return AddressList == null? 0: AddressList.size();
    }

    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, final int position) {
        final Address address = AddressList.get(position);

        // Set the data to the views here
        holder.setFirstName(address.getFirst_name());
        holder.setLastName(address.getLast_name());

        // You can set click listners to indvidual items in the viewholder here
        // make sure you pass down the listner or make the Data members of the viewHolder public

    }

    // This is your ViewHolder class that helps to populate data to the view
    public class ContactHolder extends RecyclerView.ViewHolder {

        private TextView txtFirstName;
        private TextView txtLastName;

        public ContactHolder(View itemView) {
            super(itemView);

            txtFirstName = itemView.findViewById(R.id.fname);
            txtLastName = itemView.findViewById(R.id.lname);
        }

        public void setFirstName(String fName) {
            txtFirstName.setText(fName);
        }

        public void setLastName(String lName) {
            txtLastName.setText(lName);
        }
    }
}
