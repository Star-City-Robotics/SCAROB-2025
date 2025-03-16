package frc.robot.subsystems.intake;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TestSubsystem extends SubsystemBase {

  private final SparkFlex slapdownAngleMotor =
      new SparkFlex(Constants.Slapdown.SLAPDOWN_ANGLE_ID, MotorType.kBrushless);

  public TestSubsystem() {}
}
