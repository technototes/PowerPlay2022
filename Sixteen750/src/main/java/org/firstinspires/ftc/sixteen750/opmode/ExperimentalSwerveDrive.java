package org.firstinspires.ftc.sixteen750.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.subsystem.SwerveDrivebaseSubsystem;

@SuppressWarnings("unused")
@TeleOp(group = "ExperimentalSwerveDrive")
public class ExperimentalSwerveDrive extends CommandOpMode {
    /// Standard Code
//    public Robot robot;
//    public Controls controls;
//    public Hardware hardware;

    /// Before waitForStart()
    SwerveDrivebaseSubsystem drive;

    ElapsedTime t;

    @Override
    public void uponInit() {
        /// Standard Code
//        hardware = new Hardware();
//        robot = new Robot(hardware);
//        controls = new Controls(robot);

        /// Before waitForStart()
        drive = new SwerveDrivebaseSubsystem(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void uponStart() {
        /// Right after waitForStart()
        drive.startIMUThread(this);
        t = new ElapsedTime();
    }

    // TODO: Implement these inside the periodic() of Subsystem
    @Override
    public void runLoop(){
        /// Inside while(!isStopRequested())
        drive.setWeightedDrivePower(
                new Pose2d(
                        new Vector2d(
                                -gamepad1.left_stick_y,
                                -gamepad1.left_stick_x
                        ).rotated(-drive.getExternalHeading()),
                        -gamepad1.right_stick_x
                )
        );
        if (gamepad1.right_stick_button) drive.setExternalHeading(0);

        drive.update();

        Pose2d poseEstimate = drive.getPoseEstimate();

        double loopSeconds = t.seconds();
        t.reset();
        telemetry.addData("looptime",1/loopSeconds);

        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
//        telemetry.addData("heading", poseEstimate.getHeading());
//        telemetry.addData("current", drive.rightFrontModule.getModuleRotation());
//        telemetry.addData("target", drive.rightFrontModule.getTargetRotation());
//        telemetry.addData("imuuuu", drive.getRawExternalHeading());
    //  TelemetryPacket packet = new TelemetryPacket();
    //  Canvas fieldOverlay = packet.fieldOverlay();
    //  DashboardUtil.drawRobot(fieldOverlay, poseEstimate);
    //  FtcDashboard.getInstance().sendTelemetryPacket(packet);
        telemetry.addData("LeftFrontTargetOrientation", drive.leftFrontModuleTargetOrientation);
        telemetry.addData("LeftFrontCurrentOrientation", drive.leftFrontModuleCurrentOrientation);
        telemetry.addData("LeftRearTargetOrientation", drive.leftRearModuleTargetOrientation);
        telemetry.addData("LeftRearCurrentOrientation", drive.leftRearModuleCurrentOrientation);
        telemetry.addData("RightFrontTargetOrientation", drive.rightFrontModuleTargetOrientation);
        telemetry.addData("RightFrontCurrentOrientation", drive.rightFrontModuleCurrentOrientation);
        telemetry.addData("RightRearTargetOrientation", drive.rightRearModuleTargetOrientation);
        telemetry.addData("RightRearCurrentOrientation", drive.rightRearModuleCurrentOrientation);
        telemetry.update();
    }
}
