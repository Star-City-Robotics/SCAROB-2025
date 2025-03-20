package frc.robot.subsystems.intake;

import au.grapplerobotics.LaserCan;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralManipulatorSubsystem extends SubsystemBase {

  private final SparkFlex coralManipulator1 =
      new SparkFlex(Constants.CoralManipulator.CORAL_MANIPULATOR_1_ID, MotorType.kBrushless);
  private final SparkFlex coralManipulator2 =
      new SparkFlex(Constants.CoralManipulator.CORAL_MANIPULATOR_2_ID, MotorType.kBrushless);
  private final SparkClosedLoopController coralManipulator1_controller =
      coralManipulator1.getClosedLoopController();
  private final SparkClosedLoopController coralManipulator2_controller =
      coralManipulator2.getClosedLoopController();
  private final LaserCan coralSensor = new LaserCan(Constants.CoralManipulator.CORAL_SENSOR_ID);

  public CoralManipulatorSubsystem() {}

  public void intakeAfterDetection() {
    coralManipulator1_controller.setReference(
        coralManipulator1.getEncoder().getPosition() + 100, ControlType.kPosition);
    coralManipulator2_controller.setReference(
        coralManipulator2.getEncoder().getPosition() + 100, ControlType.kPosition);
  }

  public void intake() {
    coralManipulator1.set(-0.25);
    coralManipulator2.set(0.25);
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

  public boolean reachedPosition(double positionInput) {
    return coralManipulator2.getEncoder().getPosition() >= positionInput;
  }
}
