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
import org.firstinspires.ftc.twenty403.command.autonomous.red_away.AutoRedAwayParkingSelectionCommand;

@Autonomous(name = "RedAwayParkOnly")
public class RedAwayParkOpMode extends CommandOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.RED, StartingPosition.AWAY);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstantsRed.Away.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new AutoRedAwayParkingSelectionCommand(robot.drivebaseSubsystem, robot.visionSystem),
                        CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem));
        }
    }
}
