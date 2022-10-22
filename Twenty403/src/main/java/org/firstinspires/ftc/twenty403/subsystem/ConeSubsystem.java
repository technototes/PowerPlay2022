package org.firstinspires.ftc.twenty403.subsystem;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.technototes.library.subsystem.Subsystem;

public class ConeSubsystem implements Subsystem {
    private ClawSubsystem claw;
    private LiftSubsystem lift;
    private DistanceSensor distanceSensor;

    /*
    Note from Kevin:
        Due to the way the command scheduler works, we probably don't want a
        'composite' subsystem, but instead just need to make what this is doing as
        'compound' commands. The command scheduler wants to make sure there are no
        subsystem requirement conflicts, and this composite subsystem hides those
        conflicts
     */
    public ConeSubsystem(ClawSubsystem c, LiftSubsystem l, DistanceSensor d) {
        claw = c;
        lift = l;
        distanceSensor = d;
    }

    public void readyIntake() {
        claw.open();
        claw.carry();
        //lift.intake();
    }

    public void readyToScore() {
        claw.release();
        //lift.carry();
    }

    public void readyToScoreHigh() {
        claw.release();
        lift.highPole();
    }

    public void scoreMediumJuction() {
        lift.midPole();
        claw.release();
        claw.open();
        claw.carry();
        //lift.carry();
    }

    public void scoreLowJunction() {
        lift.lowPole();
        claw.release();
        claw.open();
        claw.carry();
        //lift.carry();
    }
}
