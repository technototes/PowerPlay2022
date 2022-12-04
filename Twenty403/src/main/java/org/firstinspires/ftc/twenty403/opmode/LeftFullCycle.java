package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Controls.Controls;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.VisionCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.command.autonomous.left.AutoLeftParkingSelectionFullCycleCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@Autonomous(name = "LeftFullCycle")
@SuppressWarnings("unused")
public class LeftFullCycle extends CommandOpMode {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.LEFT);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.Left.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new SequentialCommandGroup(
                                new ClawCloseCommand(robot.clawSubsystem),
                                new AutoLeftParkingSelectionFullCycleCommand(robot.visionSystem, robot),
                                CommandScheduler.getInstance()::terminateOpMode),
                        CommandOpMode.OpModeState.RUN);
        // Claw close on Init doesn't work yet
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance()
                    .scheduleInit(
                            new VisionCommand(robot.visionSystem).alongWith(new ClawCloseCommand(robot.clawSubsystem)));
        }
    }
}
