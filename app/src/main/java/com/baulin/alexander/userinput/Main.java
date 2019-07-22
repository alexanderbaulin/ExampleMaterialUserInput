package com.baulin.alexander.userinput;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Toast;


import java.util.Arrays;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;


public class Main extends AppCompatActivity implements View.OnClickListener {

    ScrollView scrollView;
    TextInputLayout name, surname, patronymic, phone, email, vin;
    FloatingActionButton send;
    MaterialSpinner appeal, year, city, dealer, carClass;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_form);

        scrollView = findViewById(R.id.scrollView);
        appeal = findViewById(R.id.spinAppeal);
        name = findViewById(R.id.txtInputLayoutName);
        surname = findViewById(R.id.txtInputLayoutSurname);
        patronymic = findViewById(R.id.txtInputLayoutPatronymic);
        phone = findViewById(R.id.txtInputLayoutPhone);
        email = findViewById(R.id.txtInputLayoutMail);
        vin = findViewById(R.id.txtInputLayoutVIN);
        year = findViewById(R.id.spinYear);
        city = findViewById(R.id.spinCity);
        carClass = findViewById(R.id.spinClass);
        dealer = findViewById(R.id.spinDealer);
        send = findViewById(R.id.floatBtnSend);

        send.setOnClickListener(this);

        setSpinnerAdapter(appeal, getAppealMenuItems());
        setSpinnerAdapter(year, getYearMenuItems());
        setSpinnerAdapter(city, getCityMenuItems());
        setSpinnerAdapter(carClass, getCarClassItems());
        setSpinnerAdapter(dealer, getDealerItems());

        Utils.setPhoneMask(phone.getEditText());
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        boolean fieldsOk = validateFields();
        if (fieldsOk) {
            showMessage("User input correct " + appeal.getSelectedItem());
        }
        else
            scrollToInvalidField();
    }



    private void setSpinnerAdapter(MaterialSpinner spinner, List<String> menuItems) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, menuItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private List<String> getAppealMenuItems() {
        String[] items = new String[]{
                getResources().getString(R.string.appeal_man),
                getResources().getString(R.string.appeal_women)
        };
        return Arrays.asList(items);
    }

    private List<String> getDealerItems() {
        String[] items = getResources().getStringArray(R.array.list_dealers);
        return Arrays.asList(items);
    }

    private List<String> getCarClassItems() {
        String[] items = getResources().getStringArray(R.array.list_class);
        return Arrays.asList(items);
    }

    private List<String> getCityMenuItems() {
        String[] items = getResources().getStringArray(R.array.list_city);
        return Arrays.asList(items);
    }

    private List<String> getYearMenuItems() {
        String[] items = getResources().getStringArray(R.array.list_year);
        return Arrays.asList(items);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean validateFields() {
        return  validateTextField(surname) &
                validateTextField(name) &
                validateTextField(patronymic) &
                validateTextField(phone) &
                validateTextField(email) &
                validateTextField(vin) &

                validateSpinnerField(appeal) &
                validateSpinnerField(year) &
                validateSpinnerField(carClass) &
                validateSpinnerField(city) &
                validateSpinnerField(dealer);
    }

    private boolean validateTextField(TextInputLayout field) {
        Editable text = field.getEditText().getText();

        if (Utils.isEmptyText(text)) {
            field.setError(getResources().getString(R.string.necessarily_field));
            return false;
        }

        boolean validField = true;
        int id = field.getId();
        switch (id) {
            case R.id.txtInputLayoutPhone:
                validField = Utils.validatePhone(text);
                if (!validField) field.setError(getString(R.string.incorrect_phone));
                break;
            case R.id.txtInputLayoutMail:
                validField = Utils.validateEmail(text);
                if (!validField) field.setError(getResources().getString(R.string.incorrect_email));
                break;
            case R.id.txtInputLayoutVIN:
                validField = Utils.validateVIN(text);
                if (!validField) field.setError(getResources().getString(R.string.incorrect_vin));
                break;
        }

        if (validField) field.setError(null);

        return validField;
    }

    private boolean validateSpinnerField(MaterialSpinner spinner) {
        if (spinner.getSelectedItemPosition() == 0) {
            if (spinner.getError() == null) spinner.setError(getResources().getString(R.string.field_not_selected));
            return false;
        } else {
            if (spinner.getError() != null) spinner.setError(null);
            return true;
        }
    }

    private void scrollToInvalidField() {
        if (appeal.getError() != null) {
            scrollView.fullScroll(View.FOCUS_UP);
            scrollView.fullScroll(View.FOCUS_UP);
            return;
        }
        if (surname.getError() != null) {
            surname.clearFocus();
            surname.requestFocus();
            return;
        }
        if (name.getError() != null) {
            name.clearFocus();
            name.requestFocus();
            return;
        }
        if (patronymic.getError() != null) {
            patronymic.clearFocus();
            patronymic.requestFocus();
            return;
        }
        if (phone.getError() != null) {
            phone.clearFocus();
            phone.requestFocus();
            return;
        }
        if (email.getError() != null) {
            email.clearFocus();
            email.requestFocus();
            return;
        }
        if (vin.getError() != null) {
            vin.clearFocus();
            vin.requestFocus();
            return;
        }
        if (year.getError() != null) {
            scrollView.fullScroll(View.FOCUS_DOWN);
            scrollView.fullScroll(View.FOCUS_DOWN);
            return;
        }
        if (carClass.getError() != null) {
            scrollView.fullScroll(View.FOCUS_DOWN);
            scrollView.fullScroll(View.FOCUS_DOWN);
            return;
        }
        if (city.getError() != null) {
            scrollView.fullScroll(View.FOCUS_DOWN);
            scrollView.fullScroll(View.FOCUS_DOWN);
            return;
        }
        if (dealer.getError() != null) {
            scrollView.fullScroll(View.FOCUS_DOWN);
            scrollView.fullScroll(View.FOCUS_DOWN);
        }
    }
}
