package org.firstinspires.ftc.twenty403.subsystem;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;

public class OdoSubsystem implements Subsystem {

    DistanceSensor dLeft;
    DistanceSensor dRight;

    RevColorSensorV3 cLeft;
    ColorRangeSensor cMiddle;
    RevColorSensorV3 cRight;

    double leftDistance;
    double rightDistance;
    int leftColor;
    int middleColor;
    int rightColor;

    public OdoSubsystem(
        DistanceSensor dl,
        DistanceSensor dr,
        RevColorSensorV3 cl,
        ColorRangeSensor cm,
        RevColorSensorV3 cr
    ) {
        dLeft = dl;
        dRight = dr;
        cLeft = cl;
        cMiddle = cm;
        cRight = cr;
    }

    public double WallDistance() {
        // does not need to constantly check distance if stored in variable
        double leftDistance1 = dLeft.getDistance(DistanceUnit.CM);
        double rightDistance1 = dRight.getDistance(DistanceUnit.CM);

        // cone stack is on the left
        if (leftDistance1 + 9.5 < rightDistance1) {
            return leftDistance1 + rightDistance1 / 2;
        }
        // cone stack is on the right
        else if (leftDistance1 > rightDistance1 + 9.5) {
            return leftDistance1 + rightDistance1 / 2;
        }
        return 123.4;
    }

    @Override
    public void periodic() {
        // Read the sensors and squirrel away the value
        leftDistance = dLeft.getDistance(DistanceUnit.CM);
        rightDistance = dRight.getDistance(DistanceUnit.CM);
        leftColor = cLeft.argb();
        middleColor = cMiddle.argb();
        rightColor = cRight.argb();
    }
}
