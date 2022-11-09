package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@TeleOp(name = "IMU Test")
public class IMUDebugger extends CommandOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.NEUTRAL);
    }

    @Override
    public void runLoop() {
        telemetry.addData("ExternalHeading", robot.drivebaseSubsystem.getExternalHeading());
        telemetry.addData("ExternalHeadingVelocity", robot.drivebaseSubsystem.getExternalHeadingVelocity());

        telemetry.update();
    }
}
