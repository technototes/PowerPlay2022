package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Controls.ControlOperator;
import org.firstinspires.ftc.twenty403.Controls.ControlsDriver;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@TeleOp(name = "Dual Red TeleOp")
public class DualRedTeleOp extends CommandOpMode {
    public Robot robot;
    public ControlsDriver controlsDriver;
    public ControlOperator controlsOperator;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.RED, StartingPosition.NEUTRAL);
        controlsDriver = new ControlsDriver(driverGamepad, robot);
        controlsOperator = new ControlOperator(codriverGamepad, robot);
    }
}
