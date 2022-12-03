package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Controls.Controls;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.VisionCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.command.autonomous.blue_home.AutoBlueHomeParkingSelectionFullCycleCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.helpers.ElapsedTimeHelper;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@Autonomous(name = "Right Full Cycle")
@SuppressWarnings("unused")
public class RightFullCycle extends CommandOpMode {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.HOME);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstantsBlue.Home.START.toPose());
        // ElapsedTimeHelper timeout = new ElapsedTimeHelper(() -> this.getOpModeRuntime(), 25);
        CommandScheduler.getInstance()
                .scheduleForState(
                        new SequentialCommandGroup(
                                new AutoBlueHomeParkingSelectionFullCycleCommand(robot),
                                CommandScheduler.getInstance()::terminateOpMode),
                        CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance()
                    .scheduleInit(
                            new VisionCommand(robot.visionSystem).alongWith(new ClawCloseCommand(robot.clawSubsystem)));
        }
    }
}
