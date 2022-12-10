package org.firstinspires.ftc.twenty403.command.drive;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.util.MathUtils;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

public class DriveCommand implements Command {
    public enum DriveState{
        Normal,
        TrajectoryStart,
        TrajectoryRun,
    }

    static double STRAIGHTEN_DEAD_ZONE = 0.08;
    public DrivebaseSubsystem subsystem;
    public DoubleSupplier x, y, r;
    public BooleanSupplier straight;
    public DriveState currentDriveState;



    public DriveCommand(
        DrivebaseSubsystem sub,
        Stick stick1,
        Stick stick2,
        BooleanSupplier straighten
    ) {
        addRequirements(sub);
        subsystem = sub;
        x = stick1.getXSupplier();
        y = stick1.getYSupplier();
        r = stick2.getXSupplier();
        straight = straighten;
        currentDriveState = DriveState.Normal;
    }

    public DriveCommand(DrivebaseSubsystem sub, Stick stick1, Stick stick2) {
        this(sub, stick1, stick2, null);
    }

    private double getRotation(double headingInRads) {
        // Check to see if we're trying to straighten the robot
        if (straight == null || straight.getAsBoolean() == false) {
            // No straighten override: return the stick value
            // (with some adjustment...)
            return -Math.pow(r.getAsDouble() * subsystem.speed, 3);
        } else {
            // headingInRads is [0-2pi]
            double heading = -Math.toDegrees(headingInRads);
            // Snap to the closest 90 or 270 degree angle (for going through the depot)
            double close = MathUtils.closestTo(heading, 0, 90, 180, 270, 360);
            double offBy = close - heading;
            // Normalize the error to -1 to 1
            double normalized = Math.max(Math.min(offBy / 45, 1.), -1.);
            // Dead zone of 5 degreesLiftHighJunctionCommand(liftSubsystem)
            if (Math.abs(normalized) < STRAIGHTEN_DEAD_ZONE) {
                return 0.0;
            }
            // Scale it by the cube root, the scale that down by 30%
            // .9 (about 40 degrees off) provides .96 power => .288
            // .1 (about 5 degrees off) provides .46 power => .14
            return Math.cbrt(normalized) * 0.3;
        }
    }

    @Override
    public void execute() {
        // TODO for TileByTile: Check to see if we're running a trajectory sequence.
        // TS is running:
        //   Is there an override?
        //     Yes: abandon the trajectory sequence, and resume manual control
        //     No: Just update the subsystem and return
        // TS is not running:
        // Check to see if we're supposed to *start* a trajectory sequence
        //   Yes: Start it
        //   No: resume manual control
        switch (currentDriveState) {

            case Normal:
            double curHeading = -subsystem.getExternalHeading();
            Vector2d input = new Vector2d(
                    -y.getAsDouble() * subsystem.speed,
                    -x.getAsDouble() * subsystem.speed
            )
                    .rotated(curHeading);
            subsystem.setWeightedDrivePower(
                    new Pose2d(input.getX(), input.getY(), getRotation(curHeading))
            );
            break;
            case TrajectoryStart:

                break;
            case TrajectoryRun:
                if (!subsystem.isBusy()) {
                    currentDriveState = DriveState.Normal;
                }
                break;
        }
        subsystem.update();

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        if (cancel) subsystem.setDriveSignal(new DriveSignal());
    }
}
