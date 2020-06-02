package com.tanay.thundercipher.fragmentfragmentcommunication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MessageFragment.OnMessageSendListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragmentContainer) != null)
        {
            if(savedInstanceState != null)
            {
                return;
            }

            MessageFragment messageFragment = new MessageFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, messageFragment, null).commit();
        }
    }

    @Override
    public void onMessageSend(String message)
    {
        //first we create an object of the DisplayFragment class
        DisplayFragment displayFragment = new DisplayFragment();

        //now, to send the data to it, we create an object of the Bundle class
        Bundle bundle = new Bundle();

        //now add the data to this bundle and set this into the arguments of the displayFragment object
        bundle.putString("message", message);
        displayFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, displayFragment, null);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
