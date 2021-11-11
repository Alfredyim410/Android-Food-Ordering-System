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
 * Use the {@link Menu1#newInstance} factory method to
 * create an instance of this fragment.
 */


public class Menu1 extends Fragment {

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
    ImageButton Btn11;
    ImageButton Btn12;
    ImageButton Btn13;
    ImageButton Btn14;
    ImageButton Btn15;
    ImageButton Btn16;


    public Menu1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu1.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu1 newInstance(String param1, String param2) {
        Menu1 fragment = new Menu1();
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

        View view = inflater.inflate(R.layout.fragment_menu1, container, false);

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
        Btn11 = (ImageButton) view.findViewById(R.id.imgItem11);
        Btn12 = (ImageButton) view.findViewById(R.id.imgItem12);
        Btn13 = (ImageButton) view.findViewById(R.id.imgItem13);
        Btn14 = (ImageButton) view.findViewById(R.id.imgItem14);
        Btn15 = (ImageButton) view.findViewById(R.id.imgItem15);
        Btn16 = (ImageButton) view.findViewById(R.id.imgItem16);

        Btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode1));
                intent.putExtra("MenuName", getString(R.string.DealName1));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice1));
                startActivity(intent);
            }
        });

        Btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode2));
                intent.putExtra("MenuName", getString(R.string.DealName2));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice2));
                startActivity(intent);
            }
        });

        Btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode3));
                intent.putExtra("MenuName", getString(R.string.DealName3));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice3));
                startActivity(intent);
            }
        });

        Btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode4));
                intent.putExtra("MenuName", getString(R.string.DealName4));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice4));
                startActivity(intent);
            }
        });

        Btn5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode5));
                intent.putExtra("MenuName", getString(R.string.DealName5));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice5));
                startActivity(intent);
            }
        });

        Btn6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode6));
                intent.putExtra("MenuName", getString(R.string.DealName6));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice6));
                startActivity(intent);
            }
        });

        Btn7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode7));
                intent.putExtra("MenuName", getString(R.string.DealName7));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice7));
                startActivity(intent);
            }
        });

        Btn8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode8));
                intent.putExtra("MenuName", getString(R.string.DealName8));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice8));
                startActivity(intent);
            }
        });

        Btn9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode9));
                intent.putExtra("MenuName", getString(R.string.DealName9));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice9));
                startActivity(intent);
            }
        });

        Btn10.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode10));
                intent.putExtra("MenuName", getString(R.string.DealName10));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice10));
                startActivity(intent);
            }
        });

        Btn11.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode11));
                intent.putExtra("MenuName", getString(R.string.DealName11));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice11));
                startActivity(intent);
            }
        });

        Btn12.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode12));
                intent.putExtra("MenuName", getString(R.string.DealName12));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice12));
                startActivity(intent);
            }
        });

        Btn13.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode13));
                intent.putExtra("MenuName", getString(R.string.DealName13));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice13));
                startActivity(intent);
            }
        });

        Btn14.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode14));
                intent.putExtra("MenuName", getString(R.string.DealName14));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice14));
                startActivity(intent);
            }
        });

        Btn15.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode15));
                intent.putExtra("MenuName", getString(R.string.DealName15));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice15));
                startActivity(intent);
            }
        });

        /*Btn16.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PreviewMenu.class);
                intent.putExtra("MenuID", getString(R.string.DealCode16));
                intent.putExtra("MenuName", getString(R.string.DealName16));
                intent.putExtra("MenuPrice", getString(R.string.DealPrice16));
                startActivity(intent);
            }
        });*/

        return view;
    }
}