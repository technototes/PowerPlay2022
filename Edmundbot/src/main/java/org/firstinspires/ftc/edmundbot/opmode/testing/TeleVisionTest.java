package org.firstinspires.ftc.edmundbot.opmode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.edmundbot.Hardware;
import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.VisionCommand;
import org.firstinspires.ftc.edmundbot.command.autonomous.StartingPosition;
import org.firstinspires.ftc.edmundbot.controls.ControlDriver;
import org.firstinspires.ftc.edmundbot.controls.ControlOperator;
import org.firstinspires.ftc.edmundbot.subsystem.DrivebaseSubsystem;

@Config
@TeleOp(group = "TeleVision Test")
public class TeleVisionTest extends CommandOpMode {

    Robot robot;
    Hardware hardware;
    DrivebaseSubsystem drive;
    ControlDriver driverControls;
    // ControlsOperator operatorControls;
    ControlOperator coDriverControls;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.BLUE, StartingPosition.NEUTRAL);
        driverControls = new ControlDriver(driverGamepad, robot);
        // operatorControls = new ControlsOperator(codriverGamepad, robot, Robot.SubsystemCombo.DEFAULT);
        coDriverControls = new ControlOperator(codriverGamepad, robot);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        // I don't think we want to do this, as it stops the pipeline 'after init' and that
        // may be triggered *after* we start the pipeline when we 'upon start'.
        if (false && Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem));
        }
    }

    @Override
    public void uponStart() {}

    @Override
    public void runLoop() {
        telemetry.addData("External Heading", robot.drivebaseSubsystem.getExternalHeading());
        telemetry.addData(
            "Left Stick",
            String.format("%f, %f", gamepad1.left_stick_x, gamepad1.left_stick_y)
        );
        telemetry.addData(
            "Left Stick",
            String.format("%f, %f", gamepad1.right_stick_x, gamepad1.right_stick_y)
        );
        telemetry.addData("PoseEstimate", robot.drivebaseSubsystem.getPoseEstimate());
        telemetry.addData("Vis State: ", robot.visionSystem.visionPipeline.activeMode.toString());
        telemetry.addData("Junction X: ", robot.visionSystem.visionPipeline.junctionX);
        telemetry.addData("Junction Y: ", robot.visionSystem.visionPipeline.junctionY);
    }
}
