package org.firstinspires.ftc.twenty403.opmode.testing;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.twenty403.controls.ControlDriver;
import org.firstinspires.ftc.twenty403.controls.ControlOperator;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@Disabled
@TeleOp(name = "Debug Helper: Dual Controls")
@SuppressWarnings("unused")
public class DebugHelper extends CommandOpMode {
    public Robot robot;
    public Hardware hardware;
    public ControlOperator oper;
    public ControlDriver drv;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.RED, StartingPosition.NEUTRAL);
        oper = new ControlOperator(codriverGamepad, robot);
        drv = new ControlDriver(driverGamepad, robot);
    }

    @Override
    public void runLoop() {
        /*
        telemetry.addData("ExternalHeading", robot.drivebaseSubsystem.getExternalHeading());
        telemetry.addData("ExternalHeadingVelocity", robot.drivebaseSubsystem.getExternalHeadingVelocity());
        */
        telemetry.addData("Cone Close:", robot.clawSubsystem.isConeClose());
        telemetry.addData("Cone Alliance:", robot.clawSubsystem.isAllianceCone());
        telemetry.addData("isClawClosed:", robot.clawSubsystem.isClawClosed());
        telemetry.addData("Cone Distance:", hardware.clawDistance.getDistance(DistanceUnit.CM));
        telemetry.addData("Cone Color:", hardware.clawDistance.argb());
        telemetry.addData("VOLTAGE:", robot.initialVoltage);
        // telemetry.update();
    }
}
