package org.firstinspires.ftc.twenty403.command.drive;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.MathUtils;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionPipeline;

public class DriveCommand implements Command, Loggable {


    static double STRAIGHTEN_DEAD_ZONE = 0.08;
    public DrivebaseSubsystem subsystem;
    public DoubleSupplier x, y, r;
    public BooleanSupplier straight;
    public BooleanSupplier watchTrigger;
    public VisionPipeline visionPipeline;

    public DriveCommand(
            DrivebaseSubsystem sub,
            Stick stick1,
            Stick stick2,
            BooleanSupplier straighten,
            BooleanSupplier watchAndAlign,
            VisionPipeline vp
    ) {
        addRequirements(sub, sub.odometry);
        subsystem = sub;
        x = stick1.getXSupplier();
        y = stick1.getYSupplier();
        r = stick2.getXSupplier();
        straight = straighten;
        watchTrigger = watchAndAlign;
        visionPipeline = vp;
    }

    public DriveCommand(
            DrivebaseSubsystem sub,
            Stick stick1,
            Stick stick2,
            BooleanSupplier straighten
    ) {
        this(sub, stick1, stick2, straighten, null, null);
    }

    public DriveCommand(DrivebaseSubsystem sub, Stick stick1, Stick stick2) {
        this(sub, stick1, stick2, null, null, null);
    }

    private double getRotation(double headingInRads) {
        // Check to see if we're trying to straighten the robot
        if (straight == null || straight.getAsBoolean() == false) {
            // No straighten override: return the stick value
            // (with some adjustment...)
            return -Math.pow(r.getAsDouble(), 3) * subsystem.speed;
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

    private double getRotationClosest45(double headingInRads) {
        // headingInRads is [0-2pi]
        double heading = -Math.toDegrees(headingInRads);
        // Snap to the closest 90 or 270 degree angle (for going through the depot)
        double close = MathUtils.closestTo(heading, 0, 45, 90, 135, 180, 225, 270, 315, 360);
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

    public static double idk = 1.0, max_of_jy = 1.0, max_of_jx = 1.0;

    public static double xEdgeAngle = idk; // angles of the edge points seen by the camera relative to pixel
    public double yEdgeAngle = idk;
    public double camHeight = idk;

    private void autoAlign45() {
        double curHeading = -subsystem.getExternalHeading();
        double rotPower = getRotationClosest45(curHeading);
        if (Math.abs(rotPower) > .000001) {
            subsystem.setWeightedDrivePower(new Pose2d(0, 0, rotPower));
        } else {
            // Look for the current junctionX/junctionY
            double jx = visionPipeline.getJunctionX();
            double jy = visionPipeline.getJunctionY();
            if (jx == 0.0 && jy == 0.0) {
                return;
            }
            double yDistance;
            double xDistance;
            yDistance = camHeight / Math.tan(jy / (max_of_jy) * yEdgeAngle);
            xDistance = yDistance * Math.tan(jx / (max_of_jx) * xEdgeAngle);
            // set the drive power to get us to that location
            subsystem.setWeightedDrivePower(
                    new Pose2d(xDistance, yDistance, getRotationClosest45(curHeading))
            );
        }
    }

    @Override
    public void execute() {
       // If subsystem is busy it is running a trajectory.
        if (!subsystem.isBusy()) {
            if (watchTrigger != null && watchTrigger.getAsBoolean()) {
                // do the auto-align stuff
                autoAlign45();
            } else {
                double curHeading = -subsystem.getExternalHeading();
                // The math & signs looks wonky, because this makes things field-relative
                // (Recall that "3 O'Clock" is zero degrees)
                Vector2d input = new Vector2d(
                        -y.getAsDouble() * subsystem.speed,
                        -x.getAsDouble() * subsystem.speed
                )
                        .rotated(curHeading);
                subsystem.setWeightedDrivePower(
                        new Pose2d(input.getX(), input.getY(), getRotation(curHeading))
                );
            }
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
