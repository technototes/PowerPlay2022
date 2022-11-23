package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Controls.Controls2;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@TeleOp(name = "MeetNew TeleOp")
public class ManualTeleOp2 extends CommandOpMode {
    public Robot robot;
    public Controls2 controlsDriver;
    public Controls2 controlsOperator;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.NEUTRAL);
        controlsDriver = new Controls2(driverGamepad, robot);
        controlsOperator = new Controls2(codriverGamepad, robot);
    }
}
