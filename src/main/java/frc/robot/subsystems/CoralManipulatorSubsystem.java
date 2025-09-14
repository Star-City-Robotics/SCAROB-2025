package frc.robot.subsystems;

import au.grapplerobotics.LaserCan;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CoralManipulator;

public class CoralManipulatorSubsystem extends SubsystemBase {

  private final SparkFlex coralManipulator1 =
      new SparkFlex(CoralManipulator.CORAL_MANIPULATOR_1_ID, MotorType.kBrushless);
  private final SparkFlex coralManipulator2 =
      new SparkFlex(CoralManipulator.CORAL_MANIPULATOR_2_ID, MotorType.kBrushless);
  private final LaserCan coralSensor = new LaserCan(CoralManipulator.CORAL_SENSOR_ID);

  public CoralManipulatorSubsystem() {}

  public void intake() {
    coralManipulator1.set(-0.15);
    coralManipulator2.set(0.15);
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
}
