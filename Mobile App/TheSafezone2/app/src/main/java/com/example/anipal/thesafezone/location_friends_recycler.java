package com.example.anipal.thesafezone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class location_friends_recycler extends AppCompatActivity {
    private List<Person> personList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PersonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_friends_recycler);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new PersonAdapter(personList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Person person = personList.get(position);
                Toast.makeText(getApplicationContext(), person.name + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        preparePersonData();
    }

    private void preparePersonData() {
        Person person = new Person("Mad Max: Fury Road", "Action & Adventure", "2015");
        personList.add(person);

        person = new Person("Inside Out", "Animation, Kids & Family", "2015");
        personList.add(person);

        person = new Person("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        personList.add(person);

        person = new Person("Shaun the Sheep", "Animation", "2015");
        personList.add(person);

        person = new Person("The Martian", "Science Fiction & Fantasy", "2015");
        personList.add(person);

        person = new Person("Mission: Impossible Rogue Nation", "Action", "2015");
        personList.add(person);

        person = new Person("Up", "Animation", "2009");
        personList.add(person);

        person = new Person("Star Trek", "Science Fiction", "2009");
        personList.add(person);

        person = new Person("The LEGO Person", "Animation", "2014");
        personList.add(person);

        person = new Person("Iron Man", "Action & Adventure", "2008");
        personList.add(person);

        person = new Person("Aliens", "Science Fiction", "1986");
        personList.add(person);

        person = new Person("Chicken Run", "Animation", "2000");
        personList.add(person);

        person = new Person("Back to the Future", "Science Fiction", "1985");
        personList.add(person);

        person = new Person("Raiders of the Lost Ark", "Action & Adventure", "1981");
        personList.add(person);

        person = new Person("Goldfinger", "Action & Adventure", "1965");
        personList.add(person);

        person = new Person("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        personList.add(person);

        mAdapter.notifyDataSetChanged();
    }
}