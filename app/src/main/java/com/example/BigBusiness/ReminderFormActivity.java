package com.example.BigBusiness;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.TimeZone;

import static com.example.BigBusiness.R.id.dateEdt;

public class ReminderFormActivity extends AppCompatActivity {
    private TextInputLayout titleInput;
    private TextInputLayout amountInput;
    private RadioGroup radioGroup;
    TextInputEditText date_view , timeEdt , timeInput;
    private MaterialButton submit , cancel;
    private int mDate ,mMonth , mYear , thour , tminute;
    ReminderCardsManager reminderCardsManager = ReminderCardsManager.getInstance();

    private Reminder createCard()
    {
        final TextInputLayout titleET = (TextInputLayout) findViewById(R.id.titleInplay);
        String title = titleET.getEditText().getText().toString();

        final TextInputLayout amountInput = (TextInputLayout) findViewById(R.id.Amount);
        String amount = amountInput.getEditText().getText().toString();

        final RadioGroup paymentTypeGroup = (RadioGroup) findViewById(R.id.radioGrup);
        RadioButton selectedRadioButton = (RadioButton)findViewById((int)paymentTypeGroup.getCheckedRadioButtonId());
        String selectedType = selectedRadioButton.getText().toString();

        final EditText dateInput = (EditText) findViewById(R.id.dateEdt);
        String date = dateInput.getText().toString();

        final EditText timeInput = (EditText) findViewById(R.id.timeEdt);
        String time = timeInput.getText().toString();

        Reminder cardToBeHandled = reminderCardsManager.createCard(title, amount, selectedType, date , time);
        return cardToBeHandled;
    }
    private Reminder createCardWithSameId(int id)
    {
        final TextInputLayout titleET = (TextInputLayout) findViewById(R.id.titleInplay);
        String title = titleET.getEditText().getText().toString();

        final TextInputLayout amountInput = (TextInputLayout) findViewById(R.id.Amount);
        String amount = amountInput.getEditText().getText().toString();

        final RadioGroup paymentTypeGroup = (RadioGroup) findViewById(R.id.radioGrup);
        RadioButton selectedRadioButton = (RadioButton)findViewById((int)paymentTypeGroup.getCheckedRadioButtonId());
        String selectedType = selectedRadioButton.getText().toString();

        final EditText dateInput = (EditText) findViewById(R.id.dateEdt);
        String date = dateInput.getText().toString();

        final EditText timeInput = (EditText) findViewById(R.id.timeEdt);
        String time = timeInput.getText().toString();

        Reminder cardToBeEdited = reminderCardsManager.getCardById(id); //createCard(title, amount, selectedType, date , time);
        cardToBeEdited.setTitle(title);
        cardToBeEdited.setAmount(amount);
        cardToBeEdited.setPaymentType(selectedType);
        cardToBeEdited.setDate(date);
        cardToBeEdited.setTime(time);
        return cardToBeEdited;
    }

    public void addRemainderCard(View Button)
    {
        //cardToBeHandled = new Cards(title, amount, selectedType, date , time );
        Reminder cardToBeHandled = createCard();
        reminderCardsManager.AddCard(cardToBeHandled);
        Intent i = new Intent(ReminderFormActivity.this, Transaction_ReminderandDues.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
    }

    //Done
    public void editRemainderCard(Reminder cardToBeEdited){
        Reminder editedCard = createCardWithSameId(cardToBeEdited.getCardId());
        boolean status = reminderCardsManager.replaceEditedCard(editedCard);
        if(!status)
        {
            status = true;
        }
        Intent i = new Intent(ReminderFormActivity.this, RemindersAndDuesFragment.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminderform);

    }

    private void handleTimeButton(){
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        boolean is24Hourformat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute ) {

                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(Calendar.HOUR , hour);
                        calendar1.set(Calendar.MINUTE , minute);
                        calendar1.setTimeZone(TimeZone.getDefault());

                        CharSequence charSequence = DateFormat.format("hh:mm aa" , calendar1);

                        timeEdt.setText(charSequence);
                    }
                } , HOUR , MINUTE , is24Hourformat);
        timePickerDialog.show();
    }

    private void handleDateButton(){
         Calendar calendar = Calendar.getInstance();
            int Year = calendar.get(Calendar.YEAR);
            int Month = calendar.get(Calendar.MONTH);
            int Date = calendar.get(Calendar.DATE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.set(Calendar.YEAR , year);
                    calendar1.set(Calendar.MONTH, month);
                    calendar1.set(Calendar.DATE , date);

                    CharSequence dateCharSequence = DateFormat.format("MMM d, yyyy" , calendar1);
                    date_view.setText(dateCharSequence);
                }
            } , Year , Month , Date);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }
        @Override
        protected void onStart()
        {
            super.onStart();
            Intent i = getIntent();
            Reminder card = (Reminder)i.getSerializableExtra("editCard");
            displayCardForm(card);
        }

        @Override
        protected void onRestart()
        {
            super.onRestart();
        }

        public void displayCardForm(Reminder cardToBeEdited)
        {
            final TextInputLayout titleET = (TextInputLayout) findViewById(R.id.titleInplay);

            final TextInputLayout amountInput = (TextInputLayout) findViewById(R.id.Amount);

            final RadioGroup paymentTypeGroup = (RadioGroup) findViewById(R.id.radioGrup);

            final EditText dateInput = (EditText) findViewById(R.id.dateEdt);

            final EditText timeInput = (EditText) findViewById(R.id.timeEdt);


            submit = (MaterialButton) findViewById(R.id.submit);

           titleET.getEditText().addTextChangedListener(submitTextWatcher);
            amountInput.getEditText().addTextChangedListener(submitTextWatcher);
            dateInput.addTextChangedListener(submitTextWatcher);
            timeInput.addTextChangedListener(submitTextWatcher);

            if(cardToBeEdited != null)
            {
                titleET.getEditText().setText(cardToBeEdited.getTitle());
                amountInput.getEditText().setText(cardToBeEdited.getAmount());
                cardToBeEdited.setPaymentType(paymentTypeGroup.getCheckedRadioButtonId() + "");
                dateInput.setText(cardToBeEdited.getDate());
                timeInput.setText(cardToBeEdited.getTime());
                //Implement this
                //fill the remainining
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        editRemainderCard(cardToBeEdited);
                    }}
                );
            }

            else
            {
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        addRemainderCard(v);
                    }}
                );
            }
            radioGroup = findViewById(R.id.radioGrup);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId){
                        case R.id.radioRecieve:
                            break;
                        case R.id.radioPay:
                            break;
                    }
                }
            });

            date_view = findViewById(dateEdt);

            date_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleDateButton();
                }
            });

            timeEdt = findViewById(R.id.timeEdt);

            timeEdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleTimeButton();
                }
            });

        }
        public void ReturnHome(View view){
         super.onBackPressed();
        }

        private TextWatcher submitTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final TextInputLayout titleET = (TextInputLayout) findViewById(R.id.titleInplay);
                String title = titleET.getEditText().getText().toString().trim();

                final TextInputLayout amountInput = (TextInputLayout) findViewById(R.id.Amount);
                String amount = amountInput.getEditText().getText().toString().trim();

                final EditText dateInput = (EditText) findViewById(R.id.dateEdt);
                String date = dateInput.getText().toString().trim();

                final EditText timeInput = (EditText) findViewById(R.id.timeEdt);
                String time = timeInput.getText().toString().trim();

                submit.setEnabled(!title.isEmpty() && !amount.isEmpty() && !date.isEmpty() && !time.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

}