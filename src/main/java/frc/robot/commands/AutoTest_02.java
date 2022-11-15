// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class AutoTest_02 extends CommandBase {

  private final DriveBase driveBase;
  private int counter = 0;
  private int target;
  private int firstForwardMove;
  private int firstStop;
  private int firstBackwardsMove;
  private double distance; 
  private double throttleValue_01;
  private double aiden;
  /** Creates a new AutoTest_02. */
  public AutoTest_02(DriveBase driveBase) {
    this.driveBase = driveBase;
    target = (int)(3*50);

    //1668x^4.7	-- First Test formula, just need to reverse it to go from distance to speed
    distance = 60;
    aiden = distance/1668;
    throttleValue_01 = Math.pow(aiden, (1/4.7));
    
    
    addRequirements(driveBase);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    firstForwardMove = 50;
    firstStop = 50 + firstForwardMove;
    firstBackwardsMove = 50 + firstStop;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(counter == 0){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter > 0 && counter < firstForwardMove){
      driveBase.arcadeDrive(-throttleValue_01, 0.0);
    }
    if(counter >= firstForwardMove && counter < firstStop){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= firstStop && counter < firstBackwardsMove){
      driveBase.arcadeDrive(throttleValue_01, 0.0);
    }
    counter++;
    SmartDashboard.putNumber("COUNTER", counter);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveBase.setCoastMode();
    counter = 0;
    driveBase.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return counter >= target;
  }
}
