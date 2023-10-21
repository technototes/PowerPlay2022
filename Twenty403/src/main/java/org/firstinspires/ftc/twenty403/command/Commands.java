package org.firstinspires.ftc.twenty403.command;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.SimpleCommand;
import com.technototes.library.command.SimpleRequiredCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
// This one has been converted to a local command as an example
// import org.firstinspires.ftc.twenty403.command.autonomous.right.AutoRightCycleLeft;
import org.firstinspires.ftc.twenty403.command.autonomous.right.AutoRightCycleMiddle;
import org.firstinspires.ftc.twenty403.command.autonomous.right.AutoRightCycleRight;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class Commands {

    // If you prefer, we could name commands ClawOpen, instead of Claw.open (or Claw.Open)
    // In addition, every autonomous command that just extends a command type, and only
    // has a constructor could be moved into this format of command.
    // The onlyk problem I see is that this file would become problematic for merge conflicts
    // For an example:
    public static class Auto {

        public static class Right {

            public static Command ParkingSelectionCycle(Robot r) {
                return new ChoiceCommand(
                    // Note that by putting these things in their respective static classes,
                    // much of the auto code may be identical, as this "CycleLeft" function
                    // is calling the Auto.Right.CycleLeft function. We could call it that
                    // way if we wanted. I could also moving the Auto.Right into a completely
                    // separate file, so they're invoked as "AutoCmds.Right.ParkingSelectionCycle"
                    new Pair<>(r.visionSystem::left, CycleLeft(r)),
                    new Pair<>(r.visionSystem::middle, new AutoRightCycleMiddle(r)),
                    new Pair<>(r.visionSystem::right, new AutoRightCycleRight(r))
                );
            }

            public static Command CycleLeft(Robot r) {
                // This is instead of the AutoRightCycleLeft command
                // (it's literally a copy of the constructor of that thing)
                return new SequentialCommandGroup(
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Right.START_TO_W_JUNCTION
                    )
                        .alongWith(Commands.Lift.highJunction(r.liftSubsystem)),
                    Commands.Claw.open(r.clawSubsystem),
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Right.W_JUNCTION_TO_STACK_ONE
                    )
                        .alongWith(Commands.Lift.collect(r.liftSubsystem)),
                    Commands.Claw.close(r.clawSubsystem),
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Right.STACK_TO_W_JUNCTION_ONE
                    )
                        .alongWith(Commands.Lift.highJunction(r.liftSubsystem)),
                    Commands.Claw.open(r.clawSubsystem),
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Right.W_JUNCTION_TO_LEFT_PARK
                    )
                        .alongWith(Commands.Lift.collect(r.liftSubsystem))
                );
            }
        }
    }

    public static class Claw {

        public static Command open(ClawSubsystem claw) {
            return new SimpleRequiredCommand<>(claw, ClawSubsystem::open);
        }

        public static Command close(ClawSubsystem claw) {
            return new SimpleRequiredCommand<>(claw, ClawSubsystem::close);
        }

        public static Command toggleAutoClose(ClawSubsystem claw) {
            return new SimpleRequiredCommand<>(claw, ClawSubsystem::toggleAutoClose);
        }
    }

    public static class Drive {

        public static Command fast(DrivebaseSubsystem drive) {
            return new SimpleCommand(drive::fast);
        }

        public static Command slow(DrivebaseSubsystem drive) {
            return new SimpleCommand(drive::slow);
        }

        public static Command zeroHeading(DrivebaseSubsystem drive) {
            return new SimpleCommand(drive::setExternalHeading, 0.0);
        }

        public static Command cancelTileRequest(DrivebaseSubsystem drive) {
            return new SimpleCommand(drive::requestCancelled);
        }
    }

    public static class Lift {

        public static Command moveUp(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::moveUp);
        }

        public static Command moveDown(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::moveDown);
        }

        public static Command moveUp_OVERRIDE(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::moveUp_OVERRIDE);
        }

        public static Command moveDown_OVERRIDE(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::moveDown_OVERRIDE);
        }

        public static Command collect(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::collect);
        }

        public static Command highJunction(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::highPole);
        }

        public static Command midJunction(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::midPole);
        }

        public static Command lowJunction(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::lowPole);
        }

        public static Command groundJunction(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::groundJunction);
        }

        public static Command intake(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::intakePos);
        }

        public static Command setNewZero(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::setNewZero);
        }
    }
}
