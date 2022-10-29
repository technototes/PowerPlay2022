package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Controls;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoBlueHomeGroup;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;

@Autonomous(name = "KBHA")
public class KevinAutoOpMode extends CommandOpMode {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstantsBlue.Home.START);

        CommandScheduler.getInstance()
                .scheduleForState(new AutoBlueHomeGroup(robot.drivebaseSubsystem /*,
                                robot.liftSubsystem,
                                robot.clawSubsystem*/), CommandOpMode.OpModeState.RUN);
    }
}
