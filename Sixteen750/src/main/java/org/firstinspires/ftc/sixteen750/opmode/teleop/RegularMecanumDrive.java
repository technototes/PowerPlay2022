package org.firstinspires.ftc.sixteen750.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.sixteen750.ControlsCoDriver;
import org.firstinspires.ftc.sixteen750.ControlsDriver;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.sixteen750.subsystem.drive.MecanumDriveSubsystem;

@Config
@TeleOp(group = "Mecanum", name = "Regular Mecanum Drive")
@SuppressWarnings("unused")
public class RegularMecanumDrive extends CommandOpMode {
    Robot robot;
    Hardware hardware;
    MecanumDriveSubsystem drive;
    ControlsDriver driverControls;
    ControlsCoDriver coDriverControls;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DEFAULT);
        robot = new Robot(hardware, Robot.SubsystemCombo.DEFAULT, Alliance.NONE, StartingPosition.NEUTRAL);
        driverControls = new ControlsDriver(driverGamepad, robot, Robot.SubsystemCombo.DEFAULT);
    }

    @Override
    public void uponStart() {

    }

    @Override
    public void runLoop() {
        telemetry.addData("External Heading", robot.mecanumDriveSubsystem.getExternalHeading());
        telemetry.addData("Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Right Stick Y", gamepad1.right_stick_y);
        telemetry.addData("PoseEstimate", robot.mecanumDriveSubsystem.getPoseEstimate());
        telemetry.addData("IMU Heading", hardware.imu.getAngularOrientation().firstAngle);
    }
}
