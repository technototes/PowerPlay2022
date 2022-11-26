package org.firstinspires.ftc.twenty403.helpers;

import java.util.function.BooleanSupplier;

import com.technototes.library.control.CommandButton;

// This is a helper that returns true of both of the buttons are pressed
// We're using it so you can pull both triggers for "override" behavior
public class BothButtons implements BooleanSupplier {
    CommandButton a;

    public BothButtons(CommandButton btn1) {
        a = btn1;
    }

    @Override
    public boolean getAsBoolean() {
        return a.getAsBoolean();
    }
}
