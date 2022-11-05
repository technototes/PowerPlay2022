package org.firstinspires.ftc.twenty403.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.VisionCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsRed;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.command.autonomous.red_home.AutoRedHomeParkingSelectionCommand;

@Autonomous(name = "RedHomeParkOnly")
public class RedHomeParkOpMode extends CommandOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit(){
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.RED, StartingPosition.HOME);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstantsRed.Home.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new AutoRedHomeParkingSelectionCommand(robot.drivebaseSubsystem, robot.visionSystem), OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem));
        }
    }
}
