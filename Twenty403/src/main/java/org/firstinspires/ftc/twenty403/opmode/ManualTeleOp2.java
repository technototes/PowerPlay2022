package org.firstinspires.ftc.twenty403.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.twenty403.Controls2;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

@TeleOp(name = "MeetSpecial TeleOp")
public class ManualTeleOp2 extends CommandOpMode {
    public Robot robot;
    public Controls2 controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.NEUTRAL);
        controls = new Controls2(driverGamepad, robot);
    }
}
