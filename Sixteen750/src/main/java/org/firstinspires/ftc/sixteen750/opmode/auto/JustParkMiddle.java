package org.firstinspires.ftc.sixteen750.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.sixteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.sixteen750.command.autonomous.StraightParking;

@Autonomous(name = "JustParkMiddle")
public class JustParkMiddle extends CommandOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DRIVE_ONLY);
        robot = new Robot(hardware, Robot.SubsystemCombo.DRIVE_ONLY, Alliance.BLUE, StartingPosition.AWAY);
        robot.mecanumDriveSubsystem.setPoseEstimate(AutoConstantsBlue.Away.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new SequentialCommandGroup(
                                new StraightParking(robot.mecanumDriveSubsystem),
                                CommandScheduler.getInstance()::terminateOpMode),
                        CommandOpMode.OpModeState.RUN);
        telemetry.addData("PoseEstimate", robot.mecanumDriveSubsystem.getPoseEstimate());
        telemetry.update();
    }

    @Override
    public void runLoop() {
        telemetry.addData("PoseEstimate", robot.mecanumDriveSubsystem.getPoseEstimate());
        telemetry.update();
    }
}
