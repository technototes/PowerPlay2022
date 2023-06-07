package org.firstinspires.ftc.swerveteen750.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.swerveteen750.ControlsCoDriver;
import org.firstinspires.ftc.swerveteen750.ControlsDriver;
import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

@TeleOp(group = "Swerve")
@SuppressWarnings("unused")
public class StraightSwerveDrive extends CommandOpMode {
    Robot robot;
    Hardware hardware;
    ConfigurableSwerveDriveSubsystem drive;
    ControlsDriver driverControls;
    ControlsCoDriver coDriverControls;
    PIDFController headingCorrectionController;
    public static PIDCoefficients headingCorrectionControllerCoef = new PIDCoefficients(0.1, 0, 0);

    double driverIntendedHeading = 0;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DEFAULT);
        robot = new Robot(hardwareMap, hardware, Robot.SubsystemCombo.DEFAULT, Alliance.NONE, StartingPosition.NEUTRAL);
        drive = robot.swerveDriveSubsystem;
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driverControls = new ControlsDriver(driverGamepad, robot, Robot.SubsystemCombo.DEFAULT);
        coDriverControls = new ControlsCoDriver(codriverGamepad, robot, Robot.SubsystemCombo.DEFAULT);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        headingCorrectionController = new PIDFController(headingCorrectionControllerCoef);
        driverIntendedHeading = drive.getExternalHeading();
    }

    @Override
    public void uponStart() {
        drive.startIMUThread(this);
    }

    @Override
    public void runLoop() {
        // TODO: check for button binding conflict
        if (gamepad1.square) {
            driverIntendedHeading = drive.getExternalHeading();
        }

        double y = Math.pow(gamepad1.left_stick_y, 3);
        double x = Math.pow(gamepad1.left_stick_x, 3);
        double r = 0;
        drive.setWeightedDrivePower(
                new Pose2d(
                        new Vector2d(-y, -x).rotated(-drive.getExternalHeading()),
                        -r * 0.5
                )
        );
        if (gamepad1.right_stick_button) drive.setExternalHeading(0);

        drive.update();

        Pose2d poseEstimate = drive.getPoseEstimate();

        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("LeftStick-X", gamepad1.left_stick_x);
        telemetry.addData("LeftStick-Y", gamepad1.left_stick_y);
        telemetry.addData("RightStick-R", r);
        telemetry.addData("ExternalHeading", robot.swerveDriveSubsystem.getExternalHeading());
//        telemetry.addData("LF - Target Orientation", Math.toDegrees(robot.swerveDriveSubsystem.leftFrontModuleTargetOrientation));
//        telemetry.addData("LR - Target Orientation", Math.toDegrees(robot.swerveDriveSubsystem.leftRearModuleTargetOrientation));
//        telemetry.addData("RF - Target Orientation", Math.toDegrees(robot.swerveDriveSubsystem.rightFrontModuleTargetOrientation));
//        telemetry.addData("RR - Target Orientation", Math.toDegrees(robot.swerveDriveSubsystem.rightRearModuleTargetOrientation));
        telemetry.addData("LF - Motor Power", Math.toDegrees(robot.swerveDriveSubsystem.leftFrontMotorPower));
        telemetry.addData("LR - Motor Power", Math.toDegrees(robot.swerveDriveSubsystem.leftRearMotorPower));
        telemetry.addData("RF - Motor Power", Math.toDegrees(robot.swerveDriveSubsystem.rightFrontMotorPower));
        telemetry.addData("RR - Motor Power", Math.toDegrees(robot.swerveDriveSubsystem.rightRearMotorPower));
        telemetry.addData("LF - Wheel Velocity", Math.toDegrees(robot.swerveDriveSubsystem.leftFrontModule.getWheelVelocity()));
        telemetry.addData("LR - Wheel Velocity", Math.toDegrees(robot.swerveDriveSubsystem.leftRearModule.getWheelVelocity()));
        telemetry.addData("RF - Wheel Velocity", Math.toDegrees(robot.swerveDriveSubsystem.rightFrontModule.getWheelVelocity()));
        telemetry.addData("RR - Wheel Velocity", Math.toDegrees(robot.swerveDriveSubsystem.rightRearModule.getWheelVelocity()));
        telemetry.addData("STICK_X_SCALAR", ConfigurableSwerveDriveSubsystem.STICK_X_SCALAR);
        telemetry.addData("STICK_Y_SCALAR", ConfigurableSwerveDriveSubsystem.STICK_Y_SCALAR);
        telemetry.addData("Driver Intended Heading", driverIntendedHeading);

        if (robot.liftSubsystem != null) {
            telemetry.addData("is lift high", robot.liftSubsystem.isLiftHigh());
            if (robot.liftSubsystem.isLiftHigh()) {

            } else if (robot.liftSubsystem.isLiftMedium()) {

            } else {

            }
        }
        telemetry.update();
    }
}
