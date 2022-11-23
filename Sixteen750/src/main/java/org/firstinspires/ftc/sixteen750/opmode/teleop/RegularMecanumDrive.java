package org.firstinspires.ftc.sixteen750.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.ControlsDriver;
import org.firstinspires.ftc.sixteen750.ControlsOperator;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.subsystem.MecanumDriveSubsystem;

@Config
@TeleOp(group = "Mecanum")
@SuppressWarnings("unused")
public class RegularMecanumDrive extends CommandOpMode {
    Robot robot;
    Hardware hardware;
    MecanumDriveSubsystem drive;
    ControlsDriver driverControls;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        driverControls = new ControlsDriver(driverGamepad, robot);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void uponStart() {

    }

    @Override
    public void runLoop() {

    }
}
