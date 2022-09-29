package com.parth.design.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.parth.design.R;
import com.parth.design.adapter.OrderCardAdapter;
import com.parth.design.model.OrderCardModel;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {
    private RecyclerView orderRecyclerView;
    ImageButton backButton;

    public OrdersFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        orderRecyclerView = view.findViewById(R.id.order_recyclerView);
        backButton = view.findViewById(R.id.back_orders_button);

        // order recycler view
        orderRecyclerView.setHasFixedSize(true);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        List<OrderCardModel> orderCardList = new ArrayList<>();
        orderCardList.add(new OrderCardModel(R.drawable.rdj, "Tony Stark", "Parlour Service", "Pedicure", "25/3/2022", "completed"));
        orderCardList.add(new OrderCardModel(R.drawable.emma_watson, "Emma watson", "Parlour Service", "Pedicure", "30/4/2022", "in progress"));
        orderCardList.add(new OrderCardModel(R.drawable.tome_holland, "Tom Holland", "Parlour Service", "Pedicure", "1/5/2022", "completed"));
        orderCardList.add(new OrderCardModel(R.drawable.tome_cruse, "Tom Cruise", "Parlour Service", "Pedicure", "2/6/2022", "completed"));
        orderCardList.add(new OrderCardModel(R.drawable.tome_holland, "Tom Holland", "Parlour Service", "Pedicure", "1/5/2022", "completed"));
        orderCardList.add(new OrderCardModel(R.drawable.jennifer, "Jennifer Lawrence", "Parlour Service", "Pedicure", "3/6/2022", "in progress"));
        orderCardList.add(new OrderCardModel(R.drawable.mark_zuckerberg, "Mark Zuckerberg", "Parlour Service", "Pedicure", "9/6/2022", "in progress"));
        orderCardList.add(new OrderCardModel(R.drawable.elon_musk, "Elon Musk", "Parlour Service", "Pedicure", "20/6/2022", "in progress"));
        orderCardList.add(new OrderCardModel(R.drawable.jeff_bezos, "Jeff Bezos", "Parlour Service", "Pedicure", "10/3/2022", "in progress"));
        orderCardList.add(new OrderCardModel(R.drawable.black_widow, "Scarlett Johansson", "Parlour Service", "Pedicure", "30/6/2022", "in progress"));
        orderCardList.add(new OrderCardModel(R.drawable.lucifer, "Lucifer", "Parlour Service", "Pedicure", "22/6/2022", "completed"));
        orderCardList.add(new OrderCardModel(R.drawable.rdj, "Tony Stark", "Parlour Service", "Pedicure", "25/3/2022", "completed"));
        orderCardList.add(new OrderCardModel(R.drawable.tome_holland, "Tom Holland", "Parlour Service", "Pedicure", "1/5/2022", "completed"));
        orderCardList.add(new OrderCardModel(R.drawable.tome_holland, "Tom Holland", "Parlour Service", "Pedicure", "1/5/2022", "completed"));
        orderCardList.add(new OrderCardModel(R.drawable.mark_zuckerberg, "Mark Zuckerberg", "Parlour Service", "Pedicure", "9/6/2022", "in progress"));
        orderCardList.add(new OrderCardModel(R.drawable.emma_watson, "Emma watson", "Parlour Service", "Pedicure", "30/4/2022", "in progress"));
        orderCardList.add(new OrderCardModel(R.drawable.tome_cruse, "Tom Cruise", "Parlour Service", "Pedicure", "2/6/2022", "completed"));

        OrderCardAdapter orderCardAdapter = new OrderCardAdapter(orderCardList);
        orderRecyclerView.setAdapter(orderCardAdapter);

        // back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}