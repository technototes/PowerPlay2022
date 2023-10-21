package org.firstinspires.ftc.swerveteen750.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.VisionCommand;
import org.firstinspires.ftc.swerveteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.swerveteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.swerveteen750.command.autonomous.right.RightParkingSelectionCommandJustPark;

@Disabled
@Autonomous(name = "RightJustPark")
public class RightJustPark extends CommandOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.VISION_M_DRIVE);
        robot = new Robot(hardwareMap, hardware, Robot.SubsystemCombo.VISION_M_DRIVE, Alliance.BLUE, StartingPosition.HOME);
        robot.mecanumDriveSubsystem.setPoseEstimate(AutoConstantsBlue.Home.START.toPose());
        CommandScheduler
                .scheduleForState(
                        new SequentialCommandGroup(
                                new RightParkingSelectionCommandJustPark(
                                        robot.visionSubsystem, robot.mecanumDriveSubsystem),
                                CommandScheduler::terminateOpMode),
                        CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_ENABLED) {
            CommandScheduler.scheduleInit(new VisionCommand(robot.visionSubsystem));
        }
    }
}
