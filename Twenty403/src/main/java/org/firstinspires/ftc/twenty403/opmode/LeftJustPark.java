package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Controls.Controls;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.VisionCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.command.autonomous.blue_away.AutoBlueAwayParkingSelectionJustParkCommand;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@Autonomous(name = "Left Just Park")
public class LeftJustPark extends CommandOpMode {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.BLUE, StartingPosition.AWAY);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstantsBlue.Away.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new SequentialCommandGroup(
                                new AutoBlueAwayParkingSelectionJustParkCommand(
                                        robot.drivebaseSubsystem, robot.visionSystem),
                                CommandScheduler.getInstance()::terminateOpMode),
                        CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem));
        }
    }
}
