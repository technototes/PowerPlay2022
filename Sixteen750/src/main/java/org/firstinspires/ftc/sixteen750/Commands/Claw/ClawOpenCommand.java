<<<<<<<< HEAD:Sixteen750/src/main/java/org/firstinspires/ftc/sixteen750/Commands/Claw/ClawOpenCommand.java
package org.firstinspires.ftc.sixteen750.Commands.Claw;
========
package org.firstinspires.ftc.sixteen750.command.claw;
>>>>>>>> main:Sixteen750/src/main/java/org/firstinspires/ftc/sixteen750/command/claw/ClawOpenCommand.java

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class ClawOpenCommand implements Command {
    private ClawSubsystem subsystem;

    public ClawOpenCommand(ClawSubsystem s) {
        this.subsystem = s;
        addRequirements(this.subsystem); //Keeps robot from breaking
    }

    @Override
    public void execute() {
        this.subsystem.open();

    }
}
