package org.firstinspires.ftc.twenty403.helpers;

import java.util.function.BooleanSupplier;

// This is a helper that returns true if both of the buttons are pressed
// You can also use it for true if *either* button is pressed.
public class TwoButtons implements BooleanSupplier {
    public enum Combination {
        AND,
        OR
    };

    private BooleanSupplier a;
    private BooleanSupplier b;
    private Combination combine;

    public TwoButtons(BooleanSupplier btn1, BooleanSupplier btn2, Combination c) {
        a = btn1;
        b = btn2;
        combine = c;
    }

    public TwoButtons(BooleanSupplier btn1, BooleanSupplier btn2) {
        this(btn1, btn2, Combination.AND);
    }

    @Override
    public boolean getAsBoolean() {
        switch (combine) {
            case AND:
                return a.getAsBoolean() && b.getAsBoolean();
            case OR:
                return a.getAsBoolean() || b.getAsBoolean();
        }
        return false;
    }
}
