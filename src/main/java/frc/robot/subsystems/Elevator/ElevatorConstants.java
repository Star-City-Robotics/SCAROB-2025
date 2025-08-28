package frc.robot.subsystems.Elevator;

import com.ctre.phoenix6.signals.InvertedValue;

/** */
public final class ElevatorConstants {
  public static final int leaderMotorid = 32;
  public static final int followerMotorid = 31;
  public static final double gearRatioModifier = (1);
  // Outputs
  public static final double idleOutput = 0;

  // Motor Direction
  public static final InvertedValue elevatorMotor1Direction =
      InvertedValue.CounterClockwise_Positive;
  public static final InvertedValue elevatorMotor2Direction =
      InvertedValue.CounterClockwise_Positive;

  // Positions
  public static final double elevatorHomePosition = 0;
  public static final double minElevatorPosition = 0;
  public static final double maxElevatorPosition = 37 * gearRatioModifier;
}
