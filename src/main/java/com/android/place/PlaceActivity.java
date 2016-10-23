package com.android.place;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class PlaceActivity extends AppCompatActivity
{
    private TextView get_place;
    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        get_place = (TextView) findViewById(R.id.text_view);
        get_place.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                try {
                    PlacePicker.IntentBuilder intentBuilder =
                            new PlacePicker.IntentBuilder();
                    Intent intent = intentBuilder.build(this);
                    // Start the intent by requesting a result,
                    // identified by a request code.
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);

                } catch (GooglePlayServicesRepairableException e) {
                    // ...
                } catch (GooglePlayServicesNotAvailableException e) {
                    // ...
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PLACE_PICKER_REQUEST)
        {
            if (requestCode == RESULT_OK)
            {
                Place place = PlacePicker.getPlace(data, this);
                String direccion = String.format("Place: %s", place.getAddress());
                get_place.setText(direccion);

            }
        }
    }
}
