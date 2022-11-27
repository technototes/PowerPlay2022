package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import org.firstinspires.ftc.sixteen750.subsystem.SwerveDriveSubsystem;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.technototes.library.structure.CommandOpMode;

@Disabled
@Config
@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class AllInOneDebug extends CommandOpMode {
    SwerveDriveSubsystem drive;

    ElapsedTime t;

    public static boolean showEncoders = true;
    public static boolean showIMUDetails = true;
    public static boolean showIMUBasics = true;

    @Override
    public void uponInit() {
        drive = new SwerveDriveSubsystem(hardwareMap);
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
    public void runLoop() {
        drive.update();
        double loopSeconds = t.seconds();
        t.reset();
        telemetry.addLine("Visit 192.168.43.1:8080/dash to see the FTC-Dashboard");
        telemetry.addData("looptime", 1 / loopSeconds);

        //  TelemetryPacket packet = new TelemetryPacket();
        //  Canvas fieldOverlay = packet.fieldOverlay();
        //  DashboardUtil.drawRobot(fieldOverlay, poseEstimate);
        //  FtcDashboard.getInstance().sendTelemetryPacket(packet);

        if (showEncoders) {
            telemetry.addData("LeftFront-AnalogEncoder", drive.leftFrontModule.getModuleRotation());
            telemetry.addData("LeftFront-MotorEncoder", drive.leftFrontModule.getWheelPosition());

            telemetry.addData("LeftRear-AnalogEncoder", drive.leftRearModule.getModuleRotation());
            telemetry.addData("LeftRear-MotorEncoder", drive.leftRearModule.getWheelPosition());

            telemetry.addData("RightFront-AnalogEncoder", drive.rightFrontModule.getModuleRotation());
            telemetry.addData("RightFront-MotorEncoder", drive.rightFrontModule.getWheelPosition());

            telemetry.addData("RightRear-AnalogEncoder", drive.rightRearModule.getModuleRotation());
            telemetry.addData("RightRear-MotorEncoder", drive.rightRearModule.getWheelPosition());
        }

        if (showIMUDetails) {
            telemetry.addData("IMU-AngularOrientation-FirstAngle", drive.imuAngularOrientation.firstAngle);
            telemetry.addData("IMU-AngularOrientation-SecondAngle", drive.imuAngularOrientation.secondAngle);
            telemetry.addData("IMU-AngularOrientation-ThirdAngle", drive.imuAngularOrientation.thirdAngle);
            telemetry.addData("IMU-AngularVelocity-X", drive.imuAngularVelocity.xRotationRate);
            telemetry.addData("IMU-AngularVelocity-Y", drive.imuAngularVelocity.yRotationRate);
            telemetry.addData("IMU-AngularVelocity-Z", drive.imuAngularVelocity.zRotationRate);
        }

        if (showIMUBasics) {
            telemetry.addData("imuAngle", drive.imuAngle);
            telemetry.addData("imuAngleVelocity", drive.imuAngleVelocity);

            telemetry.addData("ExternalHeading", drive.getExternalHeading());
            telemetry.addData("ExternalHeadingVelocity", drive.getExternalHeadingVelocity());
        }

        telemetry.update();
    }
}
