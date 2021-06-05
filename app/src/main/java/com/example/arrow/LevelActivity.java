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

public class LevelActivity extends AppCompatActivity {
    private GridView levelGrid;
    private List<LevelModel> levelList= new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    public static  String CatNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Toolbar toolbar = findViewById(R.id.toolbar_lev);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Number System");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        levelGrid = findViewById(R.id.levelGridview);


         CatNum=getIntent().getStringExtra("CATNUM");


        LevelGridAdapter adapter = new LevelGridAdapter(levelList);
        levelGrid.setAdapter(adapter);

        myRef.child("Categories").child("CAT"+CatNum).child("Level").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot1:snapshot.getChildren())
                {
                    levelList.add(dataSnapshot1.getValue(LevelModel.class));
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LevelActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            LevelActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}