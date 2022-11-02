package org.firstinspires.ftc.twenty403.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftHeightCommand implements Command {
    LiftSubsystem lift;
    JunctionHeight height;
    public LiftHeightCommand(LiftSubsystem ls, JunctionHeight ht) {
        lift = ls;
        height = ht;
        addRequirements(ls);
    }

    @Override
    public void execute() {
        switch (height) {
            case Ground:
                lift.groundJunction();
                break;
            case Low:
                lift.lowPole();
                break;
            case Medium:
                lift.midPole();
                break;
            case High:
                lift.highPole();
                break;
        }
    }
}
