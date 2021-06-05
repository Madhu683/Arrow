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

import static com.example.arrow.LevelActivity.CatNum;

public class SetsActivity extends AppCompatActivity {

    private GridView sets_grid;
    public static List<SetModel> setsIDs = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    public static String LevelNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        Toolbar toolbar = findViewById(R.id.set_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Level 1");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LevelNum = getIntent().getStringExtra("LEVELNUM");
        sets_grid = findViewById(R.id.sets_gridview);
        setsIDs.clear();
       SetsAdapter adapter = new SetsAdapter(setsIDs);
       sets_grid.setAdapter(adapter);
        myRef.child("Categories").child("CAT"+ CatNum).child("Level").child("Level"+LevelNum).child("SETS").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot1:snapshot.getChildren())
                {
                    setsIDs.add(dataSnapshot1.getValue(SetModel.class));
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SetsActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            SetsActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}