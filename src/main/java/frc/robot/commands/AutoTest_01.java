// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class AutoTest_01 extends CommandBase {

  private final DriveBase driveBase;
  private int counter = 0;
  private int target;
  private int firstTurn;
  private int firstStop;
  private int secondTurn;
  private int secondStop;
  private int finalTurn;
  private double angle;
  private double rotationValue01;
  private double david;

  /** Creates a new AutoTest_01. */
  public AutoTest_01(DriveBase driveBase) {
    this.driveBase = driveBase;
    
    target = (int)(12*50);
    
    //1760x - 713 -- old formula
    angle = 90;
    david = angle/10635;
    rotationValue01 = Math.pow(david, (1/6.05));
    
    addRequirements(driveBase);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    firstTurn = 100;
    firstStop = 100 + firstTurn;
    secondTurn = 200 + firstStop;
    secondStop = 100 + secondTurn;
    finalTurn = 100 + secondStop;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(counter == 0){
      driveBase.arcadeDrive(0, 0);
    }
    if(counter < firstTurn && counter > 0){
      driveBase.arcadeDrive(0, rotationValue01);
    }
    if(counter < firstStop && counter > firstTurn){
      driveBase.arcadeDrive(0, 0);
    }
    if(counter < secondTurn && counter > firstStop){
      driveBase.arcadeDrive(0, -rotationValue01);
    }
    if(counter < secondStop && counter > secondTurn){
      driveBase.arcadeDrive(0, 0);
    }
    if(counter < finalTurn && counter > secondStop){
      driveBase.arcadeDrive(0, rotationValue01);
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