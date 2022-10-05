package org.firstinspires.ftc.forteaching.TechnoBot.OpModes;

import org.firstinspires.ftc.forteaching.TechnoBot.Controls;
import org.firstinspires.ftc.forteaching.TechnoBot.Hardware;
import org.firstinspires.ftc.forteaching.TechnoBot.TheBot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@TeleOp(name = "Device Testing")
@SuppressWarnings("unused")
public class DeviceTestingControl extends CommandOpMode implements Loggable {
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
