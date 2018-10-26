package com.example.anipal.thesafezone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private List<Person> personList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,number,safezone;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            number = (TextView) view.findViewById(R.id.number);
            safezone = (TextView) view.findViewById(R.id.safezone);
        }
    }


    public PersonAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.name.setText(person.name);
        holder.number.setText(person.phone);
        holder.safezone.setText(person.safezone);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}