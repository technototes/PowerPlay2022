package org.firstinspires.ftc.forteaching.TechnoBot.OpModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.forteaching.TechnoBot.Commands.VisionCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Controls;
import org.firstinspires.ftc.forteaching.TechnoBot.Hardware;
import org.firstinspires.ftc.forteaching.TechnoBot.TheBot;

@TeleOp(name = "Device Testing")
public class DeviceTestingControl extends CommandOpMode implements Loggable {
    @Log
    public Hardware hardware;
    public TheBot robot;
    public Controls controls;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new TheBot(hardware);
        controls = new Controls(driverGamepad, robot, Alliance.RED);
    }
}
