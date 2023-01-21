package org.firstinspires.ftc.swerveteen750.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.hardware2.HardwareBuilder;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.swerveteen750.ControlsCoDriver;
import org.firstinspires.ftc.swerveteen750.ControlsDriver;
import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.autonomous.DriveStraight;
import org.firstinspires.ftc.swerveteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.swerveteen750.command.autonomous.right.RightJustParkMiddle;
import org.firstinspires.ftc.swerveteen750.opmode.teleop.AnotherExperimentalSwerveDrive;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.SimpleMecanumDriveSubsystem;

@Config
@Autonomous(name = "JustParkMiddle")
public class JustParkMiddle extends CommandOpMode {
    public static double DEFAULT_POWER = 0.5;
    public static int goForwardTicks = 1000;
    Hardware hardware;
    Robot robot;
    ConfigurableSwerveDriveSubsystem drive;

    @Override
    public void uponInit() {

        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DEFAULT);
        robot = new Robot(hardwareMap, hardware, Robot.SubsystemCombo.DEFAULT, Alliance.NONE, StartingPosition.NEUTRAL);
        drive = robot.swerveDriveSubsystem;
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        CommandScheduler
                .getInstance()
                .scheduleForState(
                        new SequentialCommandGroup(
                                new RightJustParkMiddle(robot),
                                CommandScheduler.getInstance()::terminateOpMode
                        ),
                        CommandOpMode.OpModeState.RUN
                );
    }

}
