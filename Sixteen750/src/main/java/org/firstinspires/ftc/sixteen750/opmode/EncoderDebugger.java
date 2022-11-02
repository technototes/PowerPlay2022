package org.firstinspires.ftc.sixteen750.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.subsystem.SwerveDrivebaseSubsystem;

@Config
@TeleOp(group = "drive")
public class EncoderDebugger extends CommandOpMode {
    SwerveDrivebaseSubsystem drive;

    ElapsedTime t;

    @Override
    public void uponInit() {
        drive = new SwerveDrivebaseSubsystem(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void uponStart() {
        drive.enableDiagnoseTelemetry(telemetry, false);
        drive.startIMUThread(this);
        t = new ElapsedTime();
    }

    @Override
    public void runLoop(){
        drive.update();
        double loopSeconds = t.seconds();
        t.reset();
        telemetry.addData("looptime",1/loopSeconds);
        //  TelemetryPacket packet = new TelemetryPacket();
        //  Canvas fieldOverlay = packet.fieldOverlay();
        //  DashboardUtil.drawRobot(fieldOverlay, poseEstimate);
        //  FtcDashboard.getInstance().sendTelemetryPacket(packet);
        telemetry.addData("LeftFront-AbsoluteEncoder", drive.leftFrontModule.getModuleRotation());
        telemetry.addData("LeftFront-MotorEncoder", drive.leftFrontModule.getWheelPosition());

        telemetry.addData("LeftRear-AbsoluteEncoder", drive.leftRearModule.getModuleRotation());
        telemetry.addData("LeftRear-MotorEncoder", drive.leftRearModule.getWheelPosition());

        telemetry.addData("RightFront-AbsoluteEncoder", drive.rightFrontModule.getModuleRotation());
        telemetry.addData("RightFront-MotorEncoder", drive.rightFrontModule.getWheelPosition());

        telemetry.addData("RightRear-AbsoluteEncoder", drive.rightRearModule.getModuleRotation());
        telemetry.addData("RightRear-MotorEncoder", drive.rightRearModule.getWheelPosition());

        telemetry.addData("IMU-AngularOrientation-FirstAngle", drive.imuAngularOrientation.firstAngle);
        telemetry.addData("IMU-AngularOrientation-SecondAngle", drive.imuAngularOrientation.secondAngle);
        telemetry.addData("IMU-AngularOrientation-ThirdAngle", drive.imuAngularOrientation.thirdAngle);
        telemetry.addData("IMU-AngularVelocity-X", drive.imuAngularVelocity.xRotationRate);
        telemetry.addData("IMU-AngularVelocity-Y", drive.imuAngularVelocity.yRotationRate);
        telemetry.addData("IMU-AngularVelocity-Z", drive.imuAngularVelocity.zRotationRate);

        telemetry.addData("imuAngle", drive.imuAngle);
        telemetry.addData("imuAngleVelocity", drive.imuAngleVelocity);

        telemetry.addData("ExternalHeading", drive.getExternalHeading());
        telemetry.addData("ExternalHeadingVelocity", drive.getExternalHeadingVelocity());

        telemetry.update();
    }
}
