package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Controls.Controls;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.VisionCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.autonomous.left.AutoLeftTest;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@Disabled
@Autonomous(name = "Drivebase Test Auto")
@SuppressWarnings("unused")
public class DrivebaseTestAutoOpMode extends CommandOpMode {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.BLUE, StartingPosition.RIGHT);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.Right.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new AutoLeftTest(robot.drivebaseSubsystem, robot.clawSubsystem, robot.liftSubsystem),
                        CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem));
        }
    }
}
