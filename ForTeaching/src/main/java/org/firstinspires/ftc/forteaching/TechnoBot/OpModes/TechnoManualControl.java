package org.firstinspires.ftc.forteaching.TechnoBot.OpModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.forteaching.TechnoBot.Commands.VisionCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Controls;
import org.firstinspires.ftc.forteaching.TechnoBot.Hardware;
import org.firstinspires.ftc.forteaching.TechnoBot.TheBot;

@TeleOp(name = "← Red: \uD83D\uDFE5 → Blue: \uD83D\uDFE6")
public class TechnoManualControl extends CommandOpMode {
    public Hardware hardware;
    public TheBot robot;
    public Controls controls;

    @Config
    public static class OpModeConfig {
        public static boolean TestVision = true;
    }

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new TheBot(hardware);
        controls = new Controls(driverGamepad, robot, Alliance.RED);

        if (OpModeConfig.TestVision)
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSystem, Alliance.RED));
    }
}
