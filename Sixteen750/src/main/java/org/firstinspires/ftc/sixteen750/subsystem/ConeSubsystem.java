package org.firstinspires.ftc.sixteen750.subsystem;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.technototes.library.subsystem.Subsystem;

public class ConeSubsystem implements Subsystem {
    private ClawSubsystem claw;
    private LiftSubsystem lift;
    private DistanceSensor distanceSensor;

    public ConeSubsystem(ClawSubsystem c, LiftSubsystem l, DistanceSensor d) {
        claw = c;
        lift = l;
        distanceSensor = d;
    }

    public void readyIntake() {
        claw.open();
        claw.carry();
        lift.intake();
    }

    public void readyToScore() {
        claw.release();
        lift.carry();
    }

    public void readyToScoreHigh() {
        claw.release();
        lift.highPole();
    }

    public void scoreMediumJunction() {
        lift.midPole();
        claw.release();
        claw.open();
        claw.carry();
        lift.carry();
    }

    public void scoreLowJunction() {
        lift.lowPole();
        claw.release();
        claw.open();
        claw.carry();
        lift.carry();
    }
}
