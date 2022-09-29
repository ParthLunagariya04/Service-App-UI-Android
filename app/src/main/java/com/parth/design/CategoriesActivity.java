package com.parth.design;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parth.design.adapter.ConstructionCardsAdaptor;
import com.parth.design.model.ConstructionCardsModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;
    ImageButton backButton;
    SearchView searchView;
    TextView categoryName;
    private List<ConstructionCardsModel> cardsList;
    private ConstructionCardsAdaptor cardsAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        recyclerView = findViewById(R.id.recyclerView_categories);
        backButton = findViewById(R.id.back_category_button);
        searchView = findViewById(R.id.search_view_category);
        categoryName = findViewById(R.id.category_name_textView_category);

        //search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        //category name textView
        categoryName.getText().toString();
        Intent intent = getIntent();
        String s = intent.getStringExtra("key");
        categoryName.setText(s);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        //recyclerView.setLayoutManager(new LinearLayoutManager(CategoriesActivity.this, LinearLayoutManager.VERTICAL, false));

        cardsList = new ArrayList<>();
        cardsList.add(new ConstructionCardsModel(R.drawable.image2, "Category 1"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image3, "Category 2"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image4, "Category 3"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image5, "Category 4"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image6, "Category 5"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image7, "Category 6"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image8, "Category 7"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image9, "Category 8"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image10, "Category 9"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image11, "Category 10"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image12, "Category 11"));
        cardsList.add(new ConstructionCardsModel(R.drawable.image1, "Category 12"));
        cardsList.add(new ConstructionCardsModel(R.drawable.rdj, "Rdj"));
        cardsList.add(new ConstructionCardsModel(R.drawable.elon_musk, "Elon musk"));
        cardsList.add(new ConstructionCardsModel(R.drawable.handmyy, "Handemyy"));

        //Log.d("parth","THIS IS MY LOG "+ cardsList);

        cardsAdaptor = new ConstructionCardsAdaptor(cardsList);
        recyclerView.setAdapter(cardsAdaptor);

        // back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //search construction cards / data text
    private void filterList(String text) {
        List<ConstructionCardsModel> filteredList = new ArrayList<>();

        for (ConstructionCardsModel items : cardsList) {
            if (items.getCategoryName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(items);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
        } else {
            cardsAdaptor.setFilteredList(filteredList);
        }
    }
}