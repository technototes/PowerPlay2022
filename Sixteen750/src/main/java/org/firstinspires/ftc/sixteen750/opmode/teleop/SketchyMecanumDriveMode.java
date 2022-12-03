package org.firstinspires.ftc.sixteen750.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.hardware2.HardwareBuilder;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.subsystem.SimpleMecanumDriveSubsystem;

@TeleOp(group = "Mecanum")
public class SketchyMecanumDriveMode extends LinearOpMode {
    public static double DEFAULT_POWER = 0.4;

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBuilder.initMap(hardwareMap);
        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DEFAULT);
        SimpleMecanumDriveSubsystem drive = new SimpleMecanumDriveSubsystem(hardware);

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive()) {
            if (gamepad1.dpad_up){
                drive.goStraightForward(DEFAULT_POWER);
            } else if (gamepad1.dpad_down){
                drive.goStraightBackward(DEFAULT_POWER);
            } else if (gamepad1.dpad_left){
                drive.goLeft(DEFAULT_POWER);
            } else if (gamepad1.dpad_right){
                drive.goRight(DEFAULT_POWER);
            } else if (gamepad1.left_trigger > 0.1){
                drive.turnAroundClockwise(DEFAULT_POWER);
            } else if (gamepad1.right_trigger > 0.1){
                drive.turnAroundCounterClockwise(DEFAULT_POWER);
            } else if (gamepad1.triangle){
                drive.goDiagonal45(DEFAULT_POWER);
            } else if (gamepad1.circle){
                drive.goDiagonal135(DEFAULT_POWER);
            } else if (gamepad1.x){
                drive.goDiagonal225(DEFAULT_POWER);
            } else if (gamepad1.square){
                drive.goDiagonal315(DEFAULT_POWER);
            } else {
                drive.stop();
            }
        }

        drive.stop();
    }
}
