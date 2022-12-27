package org.firstinspires.ftc.twenty403.subsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.Localizer;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class OverrideLocalizer implements Localizer {

    Localizer orig;
    OdoSubsystem odometry;
    DrivebaseSubsystem driveSubsys;

    public OverrideLocalizer(Localizer original, OdoSubsystem odo, DrivebaseSubsystem drive) {
        orig = original;
        odometry = odo;
        driveSubsys = drive;
    }

    private boolean odometryIsUseful(Pose2d position) {
        double x = position.getX();
        double y = position.getY();
        double heading = position.getHeading();

        if (Math.abs(x) < 45) {
            // driveSubsys.locState = "x out of range";
            return false;
        }
        if (y > -10 || y < -30) {
            // driveSubsys.locState = "y out of range";
            return false;
        }
        if (Math.abs(heading) > 20 && Math.abs(heading - 180) > 20) {
            // driveSubsys.locState = "angle out of range";
            return false;
        }
        // driveSubsys.locState = "useful";
        return true;
    }

    private Pose2d adjustPose(Pose2d cur) {
        double x = cur.getX();
        double y = cur.getY();
        double h = cur.getHeading();
        double dist = odometry.WallDistance(h);
        StringBuilder adjustment = new StringBuilder();
        if (dist > 0) {
            // Wall distance is good: Update x
            double newX = x;
            if (x < 0) {
                newX = -60 + dist;
            } else {
                newX = 65 - dist;
            }
            // Let's only update it's "worth" doing:
            if (Math.abs(newX - x) > .5) {
                adjustment.append(String.format("X: %f -> %f ", x, newX));
                x = newX;
            }
        }
        OdoSubsystem.GraySensorCombo sensorRead = odometry.ReadSensors();

        boolean left = x < 0;
        double newY = y;
        switch (sensorRead) {
            case LeftCenter:
                //two inches off
                newY = left ? -19 : -14;
                break;
            case LeftRight:
                //centered
                newY = left ? -17 : -16;
                break;
            case JustLeft:
                //one inch off
                newY = left ? -18 : -15;
                break;
            case JustRight:
                //one inch off
                newY = left ? -16 : -17;
                break;
            case RightCenter:
                //two inches off
                newY = left ? -15 : -18;
                break;
            default:
                // Don't change for all gray or weird
                newY = y;
                break;
        }
        if (Math.abs(newY - y) > .5) {
            adjustment.append(String.format("Y: %f -> %f ", y, newY));
            y = newY;
        }
        count++;
        if (adjustment.length() > 0) {
            driveSubsys.locState = String.format("%d: %s", count, adjustment.toString());
            System.out.println("Gabriel:" + adjustment);
        } else {
            driveSubsys.locState = String.format("%d: No odo adjustment", count);
        }
        return new Pose2d(x, y, h);
    }

    public int count = 0;

    @NonNull
    @Override
    public Pose2d getPoseEstimate() {
        // TODO: If the sensors are in accurate range, use them instead of orig for the pose
        Pose2d curEst = orig.getPoseEstimate();
        if (odometryIsUseful(curEst)) {
            // Check to see if we have better ODO values
            return adjustPose(curEst);
        } else {
            return curEst;
        }
    }

    @Override
    public void setPoseEstimate(@NonNull Pose2d pose2d) {
        orig.setPoseEstimate(pose2d);
    }

    @Nullable
    @Override
    public Pose2d getPoseVelocity() {
        return orig.getPoseVelocity();
    }

    @Override
    public void update() {
        orig.update();
    }
}
