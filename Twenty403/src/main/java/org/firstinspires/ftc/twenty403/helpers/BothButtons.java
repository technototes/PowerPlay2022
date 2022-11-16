package org.firstinspires.ftc.twenty403.helpers;

import com.technototes.library.control.CommandButton;

import java.util.function.BooleanSupplier;

// This is a helper that returns true of both of the buttons are pressed
// We're using it so you can pull both triggers for "override" behavior
public class BothButtons implements BooleanSupplier {
    CommandButton a, b;

    public BothButtons(CommandButton btn1, CommandButton btn2) {
        a = btn1;
        b = btn2;
    }

    @Override
    public boolean getAsBoolean() {
        return a.getAsBoolean() && b.getAsBoolean();
    }
}
