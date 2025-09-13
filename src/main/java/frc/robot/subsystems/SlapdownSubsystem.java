package frc.robot.subsystems;

import com.google.flatbuffers.Constants;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Slapdown;

public class SlapdownSubsystem extends SubsystemBase {
  private final SparkFlex slapdownAngleMotor =
      new SparkFlex(Slapdown.SLAPDOWN_ANGLE_ID, MotorType.kBrushless);
  private final SparkFlex slapdownRoller1Motor =
      new SparkFlex(Slapdown.SLAPDOWN_ROLLER_1_ID, MotorType.kBrushless);
  private final SparkFlex slapdownRoller2Motor =
      new SparkFlex(Slapdown.SLAPDOWN_ROLLER_2_ID, MotorType.kBrushless);
  private final LaserCan slapdownSensor = new LaserCan(Constants.Slapdown.SLAPDOWN_SENSOR_ID);
  private final SparkClosedLoopController slapdown_controller =
      slapdownAngleMotor.getClosedLoopController();
  private final RelativeEncoder slapdown_encoder;
}
