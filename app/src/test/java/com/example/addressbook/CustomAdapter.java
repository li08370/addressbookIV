package com.example.addressbook;


/*
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstName;
        private final TextView lastName;
        private final TextView pNumber;
        private ToggleButton status;
        boolean isChecked;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });

            firstName = (TextView) v.findViewById(R.id.fname);
            lastName = (TextView) v.findViewById(R.id.lname);
            pNumber = (TextView) v.findViewById(R.id.phoneNumber);
            status = (ToggleButton) v.findViewById(R.id.editButton);
        }

        public TextView getFirstName() {
            return firstName;
        }

        public TextView getLastName() {
            return lastName;
        }

        public TextView getpNumber() {
            return pNumber;
        }

        public ToggleButton getEdit() {
            return status;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
       /* Log.d(TAG, "Element " + position +" "+ mDatatSet.get(position).getTask() + " set.");
        boolean ToggleButtonState = false;
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getFirstName().setText(getStorage().get(position).getTask());
        viewHolder.getdate().setText(getStorage());
        if (mDataSet.get(position).getStatus()){
            viewHolder.getStatus().setChecked(true);
        }else {
            viewHolder.getStatus().setChecked(false);
        }
    }
}*/

