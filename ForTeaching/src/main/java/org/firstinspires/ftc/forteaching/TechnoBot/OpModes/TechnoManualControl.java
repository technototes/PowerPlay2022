package org.firstinspires.ftc.forteaching.TechnoBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.forteaching.TechnoBot.Commands.VisionCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Controls;
import org.firstinspires.ftc.forteaching.TechnoBot.Hardware;
import org.firstinspires.ftc.forteaching.TechnoBot.TheBot;

// "Away: \uD83D\uDEE9 Red: \uD83D\uDFE5 Home: \uD83C\uDFE0 Blue: \uD83D\uDFE6"
@TeleOp(name = "\uD83D\uDEE9 \uD83D\uDFE5 \uD83C\uDFE0 \uD83D\uDFE6")
@SuppressWarnings("unused")
public class TechnoManualControl extends CommandOpMode {
    public Hardware hardware;
    public TheBot robot;
    public Controls controls;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new TheBot(hardware);
        controls = new Controls(driverGamepad, robot, Alliance.RED);

        if (TheBot.Connected.Camera)
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem, Alliance.RED));
    }
}
