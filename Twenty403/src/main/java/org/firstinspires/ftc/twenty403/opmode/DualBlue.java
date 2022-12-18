package org.firstinspires.ftc.twenty403.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.twenty403.controls.ControlDriver;
import org.firstinspires.ftc.twenty403.controls.ControlOperator;

@TeleOp(name = "Dual Blue")
@SuppressWarnings("unused")
public class DualBlue extends CommandOpMode {

    public Robot robot;
    public ControlDriver controlsDriver;
    public ControlOperator controlsOperator;
    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.BLUE, StartingPosition.NEUTRAL);
        controlsDriver = new ControlDriver(driverGamepad, robot);
        controlsOperator = new ControlOperator(codriverGamepad, robot);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.Right.TELESTART.toPose());
        CommandScheduler.getInstance().scheduleForState(
                new ResetGyroCommand(robot.drivebaseSubsystem), OpModeState.INIT);
    }
}
