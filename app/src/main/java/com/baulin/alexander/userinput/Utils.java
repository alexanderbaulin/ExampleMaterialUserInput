package com.baulin.alexander.userinput;

import android.text.Editable;
import android.text.InputType;

import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;


import java.util.regex.Pattern;

import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

class Utils {

    private static final String PHONE_MASK = "+7 (___) ___-__-__";
    private static final int MAX_VIN_STRING_LENGTH = 17;


    static void setPhoneMask(EditText phone) {
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots(PHONE_MASK);
        FormatWatcher formatWatcher = new MaskFormatWatcher(MaskImpl.createTerminated(slots));
        formatWatcher.installOn(phone);
    }

    static boolean validateEmail(Editable txt) {
        String s = txt.toString().trim();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(s).matches();
    }

    static boolean isEmptyText(Editable text) {
        return text.toString().trim().isEmpty();
    }

    static boolean validatePhone(Editable text) {
        return text.toString().trim().length() == PHONE_MASK.length();
    }

    static boolean validateVIN(Editable text) {
        return text.toString().trim().length() == MAX_VIN_STRING_LENGTH;
    }
}
