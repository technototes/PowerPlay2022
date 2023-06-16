package org.firstinspires.ftc.edmundbot.opmode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.edmundbot.Hardware;
import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.VisionCommand;
import org.firstinspires.ftc.edmundbot.command.autonomous.AutoConstants;
import org.firstinspires.ftc.edmundbot.command.autonomous.StartingPosition;
import org.firstinspires.ftc.edmundbot.command.autonomous.left.AutoLeftTest;
import org.firstinspires.ftc.edmundbot.controls.ControlSingle;

@Disabled
@Autonomous(name = "Auto Tester")
@SuppressWarnings("unused")
public class AutoTester extends CommandOpMode {

    public Robot robot;
    public ControlSingle controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.BLUE, StartingPosition.RIGHT);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.Right.START.toPose());
        CommandScheduler
            .getInstance()
            .scheduleForState(new AutoLeftTest(robot), CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem));
        }
    }
}
