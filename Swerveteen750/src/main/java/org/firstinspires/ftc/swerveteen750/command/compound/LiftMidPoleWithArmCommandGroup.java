package org.firstinspires.ftc.swerveteen750.command.compound;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.swerveteen750.command.lift.LiftMidPoleCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class LiftMidPoleWithArmCommandGroup extends ChoiceCommand {
    public LiftMidPoleWithArmCommandGroup(LiftSubsystem liftSubsystem, ArmSubsystem armSubsystem) {
        super(
                new Pair<>(armSubsystem::isArmAtIntakePosition, new LiftMidPoleCommand(liftSubsystem).alongWith(new ArmIntakeToScoreCommand(armSubsystem))),
                new Pair<>(armSubsystem::isArmAtUpwardPosition, new LiftMidPoleCommand(liftSubsystem).alongWith(new ArmScoreCommand(armSubsystem))),
                new Pair<>(armSubsystem::isArmAtScorePosition, new LiftMidPoleCommand(liftSubsystem))
        );
    }
}
