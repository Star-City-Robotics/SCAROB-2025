package frc.robot.State;

import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.SlapdownSubsystem;

public class SequenceCommand {

    private ElevatorSubsystem elevatorSubsystem;
    private SlapdownSubsystem slapdownSubsystem;
    
    public Sequence.Input raiseElevator(Double position, Sequence.Input currentInput, Sequence.Input newInput) {
        elevatorSubsystem.moveElevator(position);
        if(elevatorSubsystem.getElevatorPosition() == position) {
            SequenceFunctions.setState(Sequence.State.ElEVATOR_RAISED);
            return newInput;
        }
        return currentInput;
    }

    // public static Sequence.Input moveSlapdownOut(Sequence.Input currentInput, Sequence.Input newInput) {
    //     slapdownSubsystem.angleIntake(SequenceConstants.Slapdown.SLAPDOWN_UP_POSITION);
    //     if(slapdownSubsystem.getIntakePosition() == SequenceConstants.Slapdown.SLAPDOWN_UP_POSITION) {
    //         SequenceFunctions.setState(Sequence.State.SLAPDOWN_OUT);
    //         return newInput;
    //     }
    //     return currentInput;
    // }

}
