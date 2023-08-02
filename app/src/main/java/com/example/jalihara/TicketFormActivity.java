package com.example.jalihara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TicketFormActivity extends AppCompatActivity {

    private Button order_btn;

    // four text fields
    private EditText etName, etQty;
    private TextView title;
    private RadioButton option1, option2, option3, option4;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_form);

        String order_title = getIntent().getStringExtra("title");
        title = findViewById(R.id.ticket_title);
        title.setText(order_title);

        Button back = findViewById(R.id.back_btn);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(TicketFormActivity.this, TicketsActivity.class);
            startActivity(intent);
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
            }
        });
        // register buttons with their proper IDs.
        order_btn = findViewById(R.id.order_btn);

        // handle the PROCEED button
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields();

                // the boolean variable turns to be true then
                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {
                    Intent i = new Intent(TicketFormActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private boolean CheckAllFields() {
        // register all the EditText fields with their IDs.
        TextView error = findViewById(R.id.alert_txt);
        etName = findViewById(R.id.name_et);
        etQty = findViewById(R.id.qty_et);
        option1 = findViewById(R.id.radio_option_1);
        option2 = findViewById(R.id.radio_option_2);
        option3 = findViewById(R.id.radio_option_3);
        option4 = findViewById(R.id.radio_option_4);

        if (etName.length() == 0) {
            //etName.setError("customer name must be filled");
            // globally
            error.setText("customer name must be filled");
            error.setVisibility(TextView.VISIBLE);
            return false;
        }
        else if (etQty.length() == 0) {
            error.setText("quantity name must be filled");
            error.setVisibility(TextView.VISIBLE);
            return false;
        }
        else if(etQty.length() > 0) {
            String etQtyStr = etQty.getText().toString();
            int etQtyInt = Integer.parseInt(etQtyStr);
            if(etQtyInt <= 0) {
                error.setText("quantity must be more than 0");
                error.setVisibility(TextView.VISIBLE);
                return false;
            }
            else if(!option1.isChecked() & !option2.isChecked() & !option3.isChecked() & !option4.isChecked()){
                error.setText("booth type must be selected");
                error.setVisibility(TextView.VISIBLE);
                return false;
            }
        }
        return true;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
    }
}