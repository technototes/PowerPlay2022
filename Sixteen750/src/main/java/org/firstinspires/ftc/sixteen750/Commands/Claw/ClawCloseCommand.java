<<<<<<<< HEAD:Sixteen750/src/main/java/org/firstinspires/ftc/sixteen750/Commands/Claw/ClawCloseCommand.java
package org.firstinspires.ftc.sixteen750.Commands.Claw;
========
package org.firstinspires.ftc.sixteen750.command.claw;
>>>>>>>> main:Sixteen750/src/main/java/org/firstinspires/ftc/sixteen750/command/claw/ClawCloseCommand.java

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class ClawCloseCommand implements Command {
    private ClawSubsystem subsystem;
    public ClawCloseCommand(ClawSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.close();

    }
}
