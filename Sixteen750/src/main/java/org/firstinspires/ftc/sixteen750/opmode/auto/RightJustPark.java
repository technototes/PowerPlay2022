package org.firstinspires.ftc.sixteen750.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.VisionCommand;
import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.sixteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.sixteen750.command.autonomous.right.RightParkingSelectionCommandJustPark;

@Autonomous(name = "RightJustPark")
public class RightJustPark extends CommandOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.BLUE, StartingPosition.HOME);
        robot.mecanumDriveSubsystem.setPoseEstimate(AutoConstantsBlue.Home.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new SequentialCommandGroup(
                                new RightParkingSelectionCommandJustPark(
                                        robot.visionSubsystem, robot.mecanumDriveSubsystem),
                                CommandScheduler.getInstance()::terminateOpMode),
                        CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_ENABLED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSubsystem));
        }
    }
}
