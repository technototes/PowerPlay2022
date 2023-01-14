package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

@TeleOp(group = "Swerve")
@SuppressWarnings("unused")
public class SwerveJustTurning extends CommandOpMode {
    ConfigurableSwerveDriveSubsystem drive;

    @Override
    public void uponInit() {
        drive = new ConfigurableSwerveDriveSubsystem(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drive.enableMotor = false;
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void uponStart() {
        drive.startIMUThread(this);
    }

    public double curAngle = 0.0;
    @Override
    public void runLoop(){
        /*
        drive.setWeightedDrivePower(
                new Pose2d(
                        new Vector2d(
                                -gamepad1.left_stick_y,
                                -gamepad1.left_stick_x
                        ).rotated(-drive.getExternalHeading()),
                        -gamepad1.right_stick_x
                )
        );
        */
        double value = curAngle > 2 * Math.PI ? (4 * Math.PI - curAngle) : curAngle;
        drive.setWeightedDrivePower(new Pose2d(Math.cos(value), Math.sin(value)));
        curAngle = curAngle + .1;
        if (curAngle > 4 * Math.PI) {
            curAngle = 0;
        }
        if (gamepad1.right_stick_button) drive.setExternalHeading(0);

        drive.update();

        Pose2d poseEstimate = drive.getPoseEstimate();

        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("LeftStick-X", gamepad1.left_stick_x);
        telemetry.addData("LeftStick-Y", gamepad1.left_stick_y);
        telemetry.addData("lf", drive.leftFrontModule.getModuleRotation() % Math.PI);
        telemetry.addData("lr", drive.leftRearModule.getModuleRotation() % Math.PI);
        telemetry.addData("rf", drive.rightFrontModule.getModuleRotation() % Math.PI);
        telemetry.addData("rr", drive.rightRearModule.getModuleRotation() % Math.PI);
        telemetry.update();
    }
}
