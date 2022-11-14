package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Controls;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.VisionCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.command.autonomous.blue_home.AutoBlueHomeParkingSelectionJustParkCommand;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@Autonomous(name = "BlueHomeTerminal")
public class BlueHomeTerminal extends CommandOpMode {
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
                        new SequentialCommandGroup(
                                // Kevin: I think this file didn't get added from the laptop :(
                                //   *****************************************
                                //   *** CHECK ON ALL 3 LAPTOPS ON TUESDAY ***
                                //   *****************************************
                                // new AutoBlueHomeParkingSelectionTerminalCommand(
                                new AutoBlueHomeParkingSelectionJustParkCommand(
                                        robot.visionSystem, robot.drivebaseSubsystem),
                                CommandScheduler.getInstance()::terminateOpMode),
                        CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem));
        }
    }
}
