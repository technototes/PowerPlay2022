package org.firstinspires.ftc.twenty403.opmode.OldAutos;

import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.VisionCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsRed;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.command.autonomous.red_home.AutoRedHomeParkingSelectionCycleCommand;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;
@Disabled
@Autonomous(name = "RedHomeCycle")
public class RedHomeCycle extends CommandOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.RED, StartingPosition.HOME);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstantsRed.Home.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new SequentialCommandGroup(
                                new AutoRedHomeParkingSelectionCycleCommand(
                                        robot.visionSystem,
                                        robot.drivebaseSubsystem,
                                        robot.clawSubsystem,
                                        robot.liftSubsystem),
                                CommandScheduler.getInstance()::terminateOpMode),
                        CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem));
        }
    }
}
