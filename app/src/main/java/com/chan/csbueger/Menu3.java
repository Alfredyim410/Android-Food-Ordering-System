package com.chan.csbueger;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Menu3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageButton Btn1;
    ImageButton Btn2;
    ImageButton Btn3;
    ImageButton Btn4;
    ImageButton Btn5;
    ImageButton Btn6;
    ImageButton Btn7;
    ImageButton Btn8;
    ImageButton Btn9;
    ImageButton Btn10;

    public Menu3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu3.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu3 newInstance(String param1, String param2) {
        Menu3 fragment = new Menu3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu3, container, false);

        Btn1 = (ImageButton) view.findViewById(R.id.imgItem1);
        Btn2 = (ImageButton) view.findViewById(R.id.imgItem2);
        Btn3 = (ImageButton) view.findViewById(R.id.imgItem3);
        Btn4 = (ImageButton) view.findViewById(R.id.imgItem4);
        Btn5 = (ImageButton) view.findViewById(R.id.imgItem5);
        Btn6 = (ImageButton) view.findViewById(R.id.imgItem6);
        Btn7 = (ImageButton) view.findViewById(R.id.imgItem7);
        Btn8 = (ImageButton) view.findViewById(R.id.imgItem8);
        Btn9 = (ImageButton) view.findViewById(R.id.imgItem9);
        Btn10 = (ImageButton) view.findViewById(R.id.imgItem10);

        Btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DrinksCode1));
                intent.putExtra("MenuName", getString(R.string.DrinksName1));
                intent.putExtra("MenuPrice", getString(R.string.DrinksPrice1));
                startActivity(intent);
            }
        });

        Btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DrinksCode2));
                intent.putExtra("MenuName", getString(R.string.DrinksName2));
                intent.putExtra("MenuPrice", getString(R.string.DrinksPrice2));
                startActivity(intent);
            }
        });

        Btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DrinksCode3));
                intent.putExtra("MenuName", getString(R.string.DrinksName3));
                intent.putExtra("MenuPrice", getString(R.string.DrinksPrice3));
                startActivity(intent);
            }
        });

        Btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DrinksCode4));
                intent.putExtra("MenuName", getString(R.string.DrinksName4));
                intent.putExtra("MenuPrice", getString(R.string.DrinksPrice4));
                startActivity(intent);
            }
        });

        Btn5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DrinksCode5));
                intent.putExtra("MenuName", getString(R.string.DrinksName5));
                intent.putExtra("MenuPrice", getString(R.string.DrinksPrice5));
                startActivity(intent);
            }
        });

        Btn6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DrinksCode6));
                intent.putExtra("MenuName", getString(R.string.DrinksName6));
                intent.putExtra("MenuPrice", getString(R.string.DrinksPrice6));
                startActivity(intent);
            }
        });

        Btn7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DrinksCode7));
                intent.putExtra("MenuName", getString(R.string.DrinksName7));
                intent.putExtra("MenuPrice", getString(R.string.DrinksPrice7));
                startActivity(intent);
            }
        });

        Btn8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DrinksCode8));
                intent.putExtra("MenuName", getString(R.string.DrinksName8));
                intent.putExtra("MenuPrice", getString(R.string.DrinksPrice8));
                startActivity(intent);
            }
        });

        Btn9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DrinksCode9));
                intent.putExtra("MenuName", getString(R.string.DrinksName9));
                intent.putExtra("MenuPrice", getString(R.string.DrinksPrice9));
                startActivity(intent);
            }
        });

        Btn10.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DrinksCode10));
                intent.putExtra("MenuName", getString(R.string.DrinksName10));
                intent.putExtra("MenuPrice", getString(R.string.DrinksPrice10));
                startActivity(intent);
            }
        });

        return view;

    }
}