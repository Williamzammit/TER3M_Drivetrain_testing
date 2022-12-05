// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class DriveBase extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private final WPI_TalonSRX leftLeader = new WPI_TalonSRX(FRONT_LEFT_DRIVE);
  private final WPI_TalonSRX rightLeader = new WPI_TalonSRX(FRONT_RIGHT_DRIVE);
  private final WPI_TalonSRX leftFollower = new WPI_TalonSRX(REAR_LEFT_DRIVE);
  private final WPI_TalonSRX rightFollower = new WPI_TalonSRX(REAR_RIGHT_DRIVE);
  
  MotorControllerGroup m_left = new MotorControllerGroup(leftLeader, leftFollower);
  MotorControllerGroup m_right = new MotorControllerGroup(rightLeader, rightFollower);

  private final DifferentialDrive drive = new DifferentialDrive(m_left, m_right);
  public DriveBase() {

    leftLeader.configFactoryDefault();
    rightLeader.configFactoryDefault();
    leftFollower.configFactoryDefault();
    rightFollower.configFactoryDefault();

    setCoastMode();

    SupplyCurrentLimitConfiguration supplyLimit = new SupplyCurrentLimitConfiguration(true, 30, 35, 1.0);
    leftLeader.configSupplyCurrentLimit(supplyLimit);
    rightLeader.configSupplyCurrentLimit(supplyLimit);
    leftFollower.configSupplyCurrentLimit(supplyLimit);
    rightFollower.configSupplyCurrentLimit(supplyLimit);

    leftLeader.setInverted(true);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void setBrakeMode(){
    leftLeader.setNeutralMode(NeutralMode.Brake);
    leftFollower.setNeutralMode(NeutralMode.Brake);
    rightLeader.setNeutralMode(NeutralMode.Brake);    
    rightFollower.setNeutralMode(NeutralMode.Brake);

  }
  public void setCoastMode(){
    leftLeader.setNeutralMode(NeutralMode.Coast);
    leftFollower.setNeutralMode(NeutralMode.Coast);
    rightLeader.setNeutralMode(NeutralMode.Coast);    
    rightFollower.setNeutralMode(NeutralMode.Coast);
  }
  
  public void arcadeDrive(double throttle, double rotation) {
    rotation *= -1;

    SmartDashboard.putNumber("Throttle", throttle);
    //remember rotation is multiplied by -1 
    //so the rotation value being passed to arcade drive is actually the opposite of what is displayed on shuffleboard
    SmartDashboard.putNumber("Rotation", rotation);
    drive.arcadeDrive(throttle, rotation);
  }
}