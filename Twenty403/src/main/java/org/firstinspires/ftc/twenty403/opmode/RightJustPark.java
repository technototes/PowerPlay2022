package org.firstinspires.ftc.twenty403.opmode;

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
import org.firstinspires.ftc.twenty403.command.autonomous.right.AutoRightParkingSelectionJustParkCommand;
import org.firstinspires.ftc.twenty403.controls.ControlSingle;

@Autonomous(name = "Right Just Park")
@SuppressWarnings("unused")
public class RightJustPark extends CommandOpMode {

    public Robot robot;
    public ControlSingle controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.RIGHT);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.Right.START.toPose());
        CommandScheduler
            .getInstance()
            .scheduleForState(
                new SequentialCommandGroup(
                    new AutoRightParkingSelectionJustParkCommand(robot),
                    CommandScheduler.getInstance()::terminateOpMode
                ),
                CommandOpMode.OpModeState.RUN
            );
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem));
        }
    }
}
