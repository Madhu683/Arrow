package com.example.arrow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private GridView catGrid;
    private List<CategoryModel> catList= new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        catGrid = findViewById(R.id.catGridview);




        CatGridAdapter adapter = new CatGridAdapter(catList);
        catGrid.setAdapter(adapter);

        myRef.child("Categories").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot1:snapshot.getChildren())
                {
                    catList.add(dataSnapshot1.getValue(CategoryModel.class));
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(CategoryActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            CategoryActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}