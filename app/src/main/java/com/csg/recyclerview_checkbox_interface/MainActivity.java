package com.csg.recyclerview_checkbox_interface;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Contact> dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            dataList.add(new Contact("이름" + (i + 1)));
        }

        ContactRecyclerAdapter adapter = new ContactRecyclerAdapter();
        adapter.setItems(dataList);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
    }

    private static class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder> {

        interface OnContactListener {
            void setOnClicked(Contact model);
        }

        private OnContactListener mListener;

        private List<Contact> mItems = new ArrayList<>();

        public ContactRecyclerAdapter() {
        }

        public ContactRecyclerAdapter(OnContactListener listener) {
            mListener = listener;
        }

        public void setItems(List<Contact> items) {
            this.mItems = items;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_contact, parent, false);
            final ContactViewHolder viewHolder = new ContactViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        final Contact item = mItems.get(viewHolder.getAdapterPosition());
                        mListener.setOnClicked(item);
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            Contact contact = mItems.get(position);
            holder.pictureImageView.setImageResource(R.mipmap.ic_launcher);
            holder.nameTextView.setText(contact.getName());
            // TODO : 데이터를 뷰홀더에 표시하시오
            //holder.binding.setItemPhoto(item)
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public static class ContactViewHolder extends RecyclerView.ViewHolder {
            // TODO : 뷰홀더 완성하시오
            //ItemPhotoBinding binding;
            ImageView pictureImageView;
            TextView nameTextView;
            CheckBox checkBox;

            public ContactViewHolder(@NonNull View itemView) {
                super(itemView);
                pictureImageView = itemView.findViewById(R.id.imageView);
                nameTextView = itemView.findViewById(R.id.name_text_view);
                checkBox = itemView.findViewById(R.id.checkBox);

                // TODO : 뷰홀더 완성하시오
                //binding = DataBindingUtil.bind(itemView);
            }
        }
    }
}
