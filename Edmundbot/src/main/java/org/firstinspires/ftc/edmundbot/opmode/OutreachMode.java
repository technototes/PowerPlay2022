package org.firstinspires.ftc.edmundbot.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.edmundbot.Hardware;
import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.autonomous.AutoConstants;
import org.firstinspires.ftc.edmundbot.command.autonomous.StartingPosition;
import org.firstinspires.ftc.edmundbot.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.edmundbot.controls.NewControl;

@TeleOp(name = "Outreach")
@SuppressWarnings("unused")
public class OutreachMode extends CommandOpMode {
    public Robot robot;
    public Hardware hardware;

    public NewControl controls;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.BLUE, StartingPosition.NEUTRAL);
        controls = new NewControl(robot, driverGamepad, true, true);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.Right.TELESTART.toPose());
        CommandScheduler
                .getInstance()
                .scheduleForState(new ResetGyroCommand(robot.drivebaseSubsystem), OpModeState.INIT);
    }
}
