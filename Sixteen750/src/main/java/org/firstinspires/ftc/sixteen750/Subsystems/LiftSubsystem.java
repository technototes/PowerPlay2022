package org.firstinspires.ftc.sixteen750.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.technototes.library.subsystem.Subsystem;

public class LiftSubsystem implements Subsystem {
    private DcMotor liftMotor;
    public LiftSubsystem (DcMotor l) {
        liftMotor = l;
    }

}
