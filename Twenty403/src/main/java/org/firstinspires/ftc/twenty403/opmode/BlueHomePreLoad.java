package org.firstinspires.ftc.twenty403.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.twenty403.Controls;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.VisionCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.command.autonomous.blue_away.AutoBlueAwayParkingSelectionPreLoadCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.blue_home.AutoBlueHomeParkingSelectionPreLoadCommand;

@Autonomous(name = "BlueHomePreLoad")
public class BlueHomePreLoad extends CommandOpMode {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.BLUE, StartingPosition.HOME);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstantsBlue.Home.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new AutoBlueHomeParkingSelectionPreLoadCommand(robot.visionSystem, robot.drivebaseSubsystem, robot.clawSubsystem, robot.liftSubsystem),
                        CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem));
        }
    }
}