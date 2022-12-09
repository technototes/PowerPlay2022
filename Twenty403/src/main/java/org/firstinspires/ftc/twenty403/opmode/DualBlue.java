package org.firstinspires.ftc.twenty403.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
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
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.BLUE, StartingPosition.NEUTRAL);
        controlsDriver = new ControlDriver(driverGamepad, robot);
        controlsOperator = new ControlOperator(codriverGamepad, robot);
    }
}
