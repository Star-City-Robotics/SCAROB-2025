package frc.robot.subsystems.intake;

import au.grapplerobotics.LaserCan;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralManipulatorSubsystem extends SubsystemBase {

  private final SparkFlex coralManipulator1 =
      new SparkFlex(Constants.CoralManipulator.CORAL_MANIPULATOR_1_ID, MotorType.kBrushless);
  private final SparkFlex coralManipulator2 =
      new SparkFlex(Constants.CoralManipulator.CORAL_MANIPULATOR_2_ID, MotorType.kBrushless);
  private final LaserCan coralSensor = new LaserCan(Constants.CoralManipulator.CORAL_SENSOR_ID);

  public CoralManipulatorSubsystem() {}

  public void intake() {
    coralManipulator1.set(-0.1);
    coralManipulator2.set(0.1);
  }

  public void stopMotors() {
    coralManipulator1.stopMotor();
    coralManipulator2.stopMotor();
  }

  public boolean coralDetected() {
    LaserCan.Measurement measurement = coralSensor.getMeasurement();
    if (measurement.distance_mm <= 20) {
      return true;
    }
    return false;
  }

  public void resetEncoders() {
    coralManipulator1.getEncoder().setPosition(0);
    coralManipulator2.getEncoder().setPosition(0);
  }

  public boolean reachedPosition() {
    if (coralManipulator1.getEncoder().getPosition() >= 4) {
      return true;
    }
    return false;
  }
}
