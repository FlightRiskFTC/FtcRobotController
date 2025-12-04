package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test (Blocks to Java)")
public class Test extends LinearOpMode {

  private DcMotor front_left_motor;
  private DcMotor front_right_motor;
  private DcMotor launcher_wheel;
  private CRServo right_launch_servo;
  private CRServo left_launch_servo;

  /**
   * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
   * Comment Blocks show where to place Initialization code (runs once, after touching the
   * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
   * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
   * Stopped).
   */
  @Override
  public void runOpMode() {
    double move_speed;
    int counter;

    front_left_motor = hardwareMap.get(DcMotor.class, "front_left_motor");
    front_right_motor = hardwareMap.get(DcMotor.class, "front_right_motor");
    launcher_wheel = hardwareMap.get(DcMotor.class, "launcher_wheel");
    right_launch_servo = hardwareMap.get(CRServo.class, "right_launch_servo");
    left_launch_servo = hardwareMap.get(CRServo.class, "left_launch_servo");

    move_speed = 1;
    front_left_motor.setDirection(DcMotor.Direction.FORWARD);
    front_right_motor.setDirection(DcMotor.Direction.FORWARD);
    launcher_wheel.setDirection(DcMotor.Direction.FORWARD);
    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        telemetry.addLine("text");
        telemetry.addData("Power", launcher_wheel.getPower());
        telemetry.addData("Move Speed", move_speed);
        front_left_motor.setPower(gamepad2.left_stick_y * 0.75 * move_speed - gamepad2.right_stick_x * -0.7 * move_speed);
        front_right_motor.setPower(gamepad2.left_stick_y * 0.75 * move_speed + gamepad2.right_stick_x * -0.7 * move_speed);
        if (gamepad2.right_bumper) {
          launcher_wheel.setPower(0.4475);
          telemetry.update();
          counter += 1;
          if (counter == 200) {
            right_launch_servo.setPower(-1);
            left_launch_servo.setPower(1);
          }
          if (counter == 255) {
            right_launch_servo.setPower(0);
            left_launch_servo.setPower(0);
          }
          if (counter == 325) {
            right_launch_servo.setPower(-1);
            left_launch_servo.setPower(1);
            counter = 201;
          }
        } else if (gamepad2.left_bumper) {
          launcher_wheel.setPower(0.6);
          telemetry.update();
          // Vincent, I made the counter 1 because with decimals it wont be even enough to equal 200, 290, etc. DO NOT CHANGE IT, IT WORKS
          counter += 1;
          if (counter == 200) {
            right_launch_servo.setPower(-1);
            left_launch_servo.setPower(1);
          }
          if (counter == 290) {
            right_launch_servo.setPower(0);
            left_launch_servo.setPower(0);
          }
          if (counter == 340) {
            right_launch_servo.setPower(-1);
            left_launch_servo.setPower(1);
            counter = 201;
          }
        } else {
          counter = 0;
          launcher_wheel.setPower(0);
          right_launch_servo.setPower(0);
          left_launch_servo.setPower(0);
        }
        if (gamepad2.circle_was_released()) {
          if (move_speed == 1) {
            move_speed = 0.2;
            telemetry.addData("Down", 0.2);
          } else {
            move_speed = 1;
            telemetry.addData("Up", 1);
          }
        }
        telemetry.update();
      }
    }
  }
}
