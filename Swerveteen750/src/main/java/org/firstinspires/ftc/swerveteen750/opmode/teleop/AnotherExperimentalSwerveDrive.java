package org.firstinspires.ftc.swerveteen750.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
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
import org.firstinspires.ftc.swerveteen750.subsystem.drive.SwerveDriveSubsystem;

@Config
@TeleOp(group = "Swerve")
@SuppressWarnings("unused")
public class AnotherExperimentalSwerveDrive extends CommandOpMode {
    Robot robot;
    Hardware hardware;
    ConfigurableSwerveDriveSubsystem drive;
    ControlsDriver driverControls;
    ControlsCoDriver coDriverControls;
    private double desiredRotation = 0;
    private PIDFController rotationController;
    public static PIDCoefficients rotationCoefficients = new PIDCoefficients(.5, 0, 0);
    private boolean useAutoRotation = false;
    public static double GAS_PADDLE_BASE_SPEED = 0.2;
    public static double GAS_PADDLE_BASE_MULTIPLIER = 0.5;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DEFAULT);
        robot = new Robot(hardwareMap, hardware, Robot.SubsystemCombo.DEFAULT, Alliance.NONE, StartingPosition.NEUTRAL);
        rotationController = new PIDFController(rotationCoefficients);
        drive = robot.swerveDriveSubsystem;
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driverControls = new ControlsDriver(driverGamepad, robot, Robot.SubsystemCombo.DEFAULT);
        coDriverControls = new ControlsCoDriver(codriverGamepad, robot, Robot.SubsystemCombo.DEFAULT);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

    }

    @Override
    public void uponStart() {
        drive.startIMUThread(this);
    }

    @Override
    public void runLoop() {
        double x = Math.pow(Math.abs(gamepad1.left_stick_x) > 0.03 ? gamepad1.left_stick_x : 0, 3);
        double y = Math.pow(Math.abs(gamepad1.left_stick_y) > 0.03 ? gamepad1.left_stick_y : 0, 3);
        double r = Math.pow(gamepad1.right_stick_x, 3);
        double gasPaddle = gamepad1.right_trigger * GAS_PADDLE_BASE_MULTIPLIER;
        x *= GAS_PADDLE_BASE_SPEED + gasPaddle;
        y *= GAS_PADDLE_BASE_SPEED + gasPaddle;
        r *= GAS_PADDLE_BASE_SPEED + gasPaddle;

        if (Math.abs(r) < 0.1){
//            double curHeading = drive.getExternalHeading();
//            if (!useAutoRotation){
//                desiredRotation = curHeading;
//                rotationController.reset();
//                rotationController.setTargetPosition(desiredRotation);
//                useAutoRotation = true;
//            }
//            r = -rotationController.update(curHeading);
        }
        else{
            useAutoRotation = false;
        }

        drive.setWeightedDrivePower(
                new Pose2d(
                        new Vector2d(-y, -x).rotated(-drive.getExternalHeading()),
                        -r
                )
        );
        if (gamepad1.right_stick_button) drive.setExternalHeading(0);

        drive.update();

        double possibleTurretPosition = 0;
        double actualTurretPosition = 0;
        if (gamepad2.left_stick_button){
//            if (gamepad2.left_stick_x < 0 || gamepad2.left_stick_y != 0) {
//                possibleTurretPosition = Math.toDegrees(Math.atan2(gamepad2.left_stick_y, gamepad2.left_stick_x));
//                possibleTurretPosition += 90;
////            possibleTurretPosition = -possibleTurretPosition;
//            }
            possibleTurretPosition = Math.toDegrees(Math.atan2(gamepad2.left_stick_y, gamepad2.left_stick_x));
            possibleTurretPosition += 90;
            actualTurretPosition = robot.liftSubsystem.controlTurretByDegrees(possibleTurretPosition);
        }
        // use gamepad2.left_stick_x and gamepad2.left_stick_y to control the turret


        Pose2d poseEstimate = drive.getPoseEstimate();

        telemetry.addData("X Coordinate", poseEstimate.getX());
        telemetry.addData("Y Coordinate", poseEstimate.getY());
        telemetry.addData("LeftStick-X", gamepad1.left_stick_x);
        telemetry.addData("LeftStick-Y", gamepad1.left_stick_y);
        telemetry.addData("CoDriverStick-X", gamepad2.left_stick_x);
        telemetry.addData("CoDriverStick-Y", gamepad2.left_stick_y);
        telemetry.addData("Weighted X", x);
        telemetry.addData("Weighted Y", y);
        telemetry.addData("Possible Turret Position", possibleTurretPosition);
        telemetry.addData("Actual Turret Position", actualTurretPosition);
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
        telemetry.addData("LR - Servo Power", robot.swerveDriveSubsystem.leftRearModule.getServoPower());
        telemetry.addData("LF - Servo Power", robot.swerveDriveSubsystem.leftFrontModule.getServoPower());
        telemetry.addData("RR - Servo Power", robot.swerveDriveSubsystem.rightRearModule.getServoPower());
        telemetry.addData("RF - Servo Power", robot.swerveDriveSubsystem.rightFrontModule.getServoPower());
        telemetry.addData("LF - Heading", robot.swerveDriveSubsystem.leftFrontModule.getEncoderVoltage());
        telemetry.addData("LR - Heading", robot.swerveDriveSubsystem.leftRearModule.getEncoderVoltage());
        telemetry.addData("RF - Heading", robot.swerveDriveSubsystem.rightFrontModule.getEncoderVoltage());
        telemetry.addData("RR - Heading", robot.swerveDriveSubsystem.rightRearModule.getEncoderVoltage());
        telemetry.addData("Drive Speed Multiplier", GAS_PADDLE_BASE_SPEED + gasPaddle);

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
