package org.firstinspires.ftc.twenty403.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.VisionCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.command.autonomous.left.AutoLeftParkingSelectionFullCycleCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.right.AutoRightParkingSelectionFullCycleCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.drive.AutoSpeedCommand;
import org.firstinspires.ftc.twenty403.command.drive.TurboCommand;
import org.firstinspires.ftc.twenty403.controls.ControlSingle;

@Autonomous(name = "LeftFullCycle")
@SuppressWarnings("unused")
public class LeftFullCycle extends CommandOpMode {

    public Robot robot;
    public ControlSingle controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.LEFT);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.Left.START.toPose());
        // ElapsedTimeHelper timeout = new ElapsedTimeHelper(() -> this.getOpModeRuntime(), 25);
        CommandScheduler

            .scheduleForState(
                new SequentialCommandGroup(
                    //new TurboCommand(robot.drivebaseSubsystem),
                    new ClawCloseCommand(robot.clawSubsystem),
                    new AutoLeftParkingSelectionFullCycleCommand(
                        robot,
                        () -> this.getOpModeRuntime()
                    ),
                    CommandScheduler::terminateOpMode
                ),
                CommandOpMode.OpModeState.RUN
            );
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler
                .scheduleInit(
                    new ClawCloseCommand(robot.clawSubsystem)
                        .andThen(new VisionCommand(robot.visionSystem))
                );
        }
    }
}
