package com.tanay.thundercipher.fragmentfragmentcommunication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private Button button;
    private EditText editText;
    OnMessageSendListener messageSendListener;    //interface instance

    //we need to send the message from this class to the DisplayFragment class
    public interface OnMessageSendListener
    {
        public void onMessageSend(String message);
    }

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        button = view.findViewById(R.id.button);
        editText = view.findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String message = editText.getText().toString();
                messageSendListener.onMessageSend(message);
            }
        });

        return view;
    }

    //to initialize the instance of the interface
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try                     //to verify if the interface method is implemented
        {
            messageSendListener = (OnMessageSendListener) activity;
        }

        catch(ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement OnMessageSend()");
        }
    }

    //when we go back to the MessageFragment from the DisplayFragment using addToBackStack(), the onResume() method is called

    @Override
    public void onResume() {
        super.onResume();

        editText.setText("");               //so that we can type a new message into it each time we come back
    }
}
