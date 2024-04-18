package com.example.indisky;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



public class BookFragment extends Fragment {

    EditText fromEditText, toEditText;

    ImageView reverseImageView, dateImageView, dateImageView2;

    RadioGroup chooseTripTypeRadioGroup;

    RadioButton oneWayRadioButton, roundTripRadioButton, multiCityRadioButton;

    TextView calendarTextView, calendarTextView2, textView43;

    CalendarView calendarView, calendarView2;



    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_book, container, false);

        tempFlightDB tmpFlightDB = new tempFlightDB(getActivity());
        String firstOrigin = tmpFlightDB.getFirstOrigin();
        String firstDest = tmpFlightDB.getFirstDest();

        fromEditText=view.findViewById(R.id.fromEditText);
        toEditText=view.findViewById(R.id.toEditText);
        reverseImageView=view.findViewById(R.id.reverseImageView);
        calendarTextView=view.findViewById(R.id.calendarTextView);
        calendarTextView2=view.findViewById(R.id.calendarTextView2);
        dateImageView=view.findViewById(R.id.dateImageView);
        dateImageView2=view.findViewById(R.id.dateImageView2);
        calendarView=view.findViewById(R.id.calendarView);
        calendarView2=view.findViewById(R.id.calendarView2);
        textView43=view.findViewById(R.id.textView43);

        calendarView.setVisibility(View.INVISIBLE);
        calendarView2.setVisibility(View.INVISIBLE);

        chooseTripTypeRadioGroup=view.findViewById(R.id.chooseTripTypeRadioGroup);
        oneWayRadioButton=view.findViewById(R.id.oneWayRadioButton);
        roundTripRadioButton=view.findViewById(R.id.roundTripRadioButton);

        fromEditText.setText(firstOrigin);
        toEditText.setText(firstDest);



        chooseTripTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.oneWayRadioButton)
                {
                    dateImageView2.setVisibility(View.INVISIBLE);
                    calendarTextView2.setVisibility(View.INVISIBLE);
                    textView43.setVisibility(View.INVISIBLE);
                }
                else if(i==R.id.roundTripRadioButton)
                {
                    dateImageView2.setVisibility(View.VISIBLE);
                    calendarTextView2.setVisibility(View.VISIBLE);
                    textView43.setVisibility(View.VISIBLE);
                }
            }
        });

        reverseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from=fromEditText.getText().toString();
                String to=toEditText.getText().toString();
                fromEditText.setText(to);
                toEditText.setText(from);
            }
        });

        dateImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setVisibility(View.VISIBLE);
                dateImageView2.setVisibility(View.INVISIBLE);
                calendarTextView2.setVisibility(View.INVISIBLE);
                textView43.setVisibility(View.INVISIBLE);
            }
        });

        calendarTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setVisibility(View.VISIBLE);
                dateImageView2.setVisibility(View.INVISIBLE);
                calendarTextView2.setVisibility(View.INVISIBLE);
                textView43.setVisibility(View.INVISIBLE);
            }
        });

        dateImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView2.setVisibility(View.VISIBLE);
                dateImageView2.setVisibility(View.INVISIBLE);
                calendarTextView2.setVisibility(View.INVISIBLE);
                textView43.setVisibility(View.INVISIBLE);
            }
        });

        calendarTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView2.setVisibility(View.VISIBLE);
                dateImageView2.setVisibility(View.INVISIBLE);
                calendarTextView2.setVisibility(View.INVISIBLE);
                textView43.setVisibility(View.INVISIBLE);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                calendarView.setVisibility(View.INVISIBLE);
                String date = Integer.toString(day)+ " " + returnMonth(month);
                calendarTextView.setText(date);
                if(chooseTripTypeRadioGroup.getCheckedRadioButtonId()==R.id.roundTripRadioButton) {
                    dateImageView2.setVisibility(View.VISIBLE);
                    calendarTextView2.setVisibility(View.VISIBLE);
                    textView43.setVisibility(View.VISIBLE);
                }
            }
        });

        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                calendarView.setVisibility(View.INVISIBLE);
                String date = Integer.toString(day)+ " " + returnMonth(month);
                calendarTextView2.setText(date);
                if(chooseTripTypeRadioGroup.getCheckedRadioButtonId()==R.id.roundTripRadioButton) {
                    dateImageView2.setVisibility(View.VISIBLE);
                    calendarTextView2.setVisibility(View.VISIBLE);
                    textView43.setVisibility(View.VISIBLE);
                }
            }
        });

        fromEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseCity.class);
                intent.putExtra("type", "origin");
                startActivity(intent);
            }
        });

        toEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseCity.class);
                intent.putExtra("type", "dest");
                startActivity(intent);
            }
        });

        return view;
    }


    private String returnMonth(int num)
    {
        switch(num)
        {
            case 0:
                return "Jan";

            case 1:
                return "Feb";

            case 2:
                return "Mar";

            case 3:
                return "Apr";

            case 4:
                return "May";

            case 5:
                return "Jun";

            case 6:
                return "Jul";

            case 7:
                return "Aug";

            case 8:
                return "Sep";

            case 9:
                return "Oct";

            case 10:
                return "Nov";

            case 11:
                return "Dec";
        }

        return "Jan";
    }
}