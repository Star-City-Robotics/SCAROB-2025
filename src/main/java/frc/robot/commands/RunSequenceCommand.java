package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.State.SequenceCommand;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.SlapdownSubsystem;

public class RunSequenceCommand extends Command{

    private ElevatorSubsystem elevatorSubsystem;
    private SlapdownSubsystem slapdownSubsystem;

    private SequenceCommand sequenceCommand = new SequenceCommand(elevatorSubsystem, slapdownSubsystem);
    
    public RunSequenceCommand(ElevatorSubsystem elevatorSubsystem, SlapdownSubsystem slapdownSubsystem, SequenceCommand sequenceCommand) {
        this.elevatorSubsystem = elevatorSubsystem;
        this. slapdownSubsystem = slapdownSubsystem;
        addRequirements(elevatorSubsystem, slapdownSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interupted) {

    }

}
