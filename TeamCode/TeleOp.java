‎TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeacherExamples/opmode.java‎
-28
Lines changed: 0 additions & 28 deletions
This file was deleted.
‎TeamCode/src/main/java/org/firstinspires/ftc/teamcode/vincent_opmode.java‎
+54
Lines changed: 54 additions & 0 deletions
Original file line number	Diff line number	Diff line change
@@ -0,0 +1,54 @@
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TeleOp")

public class vincent_opmode extends OpMode {

    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
    DcMotor flywheel = null;
    CRServo right_launch_servo = null;
    CRServo left_launch_servo = null;
    int counter = 0
    double power = 1;
    double flywheelVel = 0;
    double targetFlywheelSpeed = .5


    public void init(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        right_launch_servo = hardwareMap.get(CRServo.class, "rightServo");
        left_launch_servo = hardwareMap.get(CRServo.class, "leftServo");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
    };

    public void init_loop() {};

    public void start() {}

    @Override

    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rot = -gamepad1.right_stick_x;

        flywheelVel = motor.getVelocity();

        telemetry.addLine("Running");
        telemetry.addLine("Flywheel Speed: " + Double.toString(vel))
        telemetry.update();

        frontLeft.setPower((y + x + rot) * power);
        frontRight.setPower((y - x + rot) * power);
        backLeft.setPower((y - x - rot) * power);
        backRight.setPower((y + x - rot) * power);

        //TEST CODE
        if(gamepad1.right_bumper && flywheelVel < targetFlywheelSpeed) {

            double motorSpeedTowardsTarget;

            if (flywheelVel > (targetFlywheelSpeed / 2)) {
                double slowRange = flywheelSpeed / 2;
                motorSpeedTowardsTarget = 1 - (2 * (slowRange / 0.5));
            } else {
                motorSpeedTowardsTarget = 1;
            }
            flywheel.setPower(motorSpeedTowardsTarget);
        }

        //REAL CODE
        if(gamepad1.right_bumper && false)
            
            double motorSpeedTowardsTarget;

            if(flywheelVel < targetFlywheelSpeed) {
                if (flywheelSpeed > (targetFlywheelSpeed / 2)) {
                    double slowRange = flywheelSpeed / 2;
                    motorSpeedTowardsTarget = 1 - (2 * (slowRange / 0.5));
                } else {
                    motorSpeedTowardsTarget = 1;
                }
                flywheel.setPower(motorSpeedTowardsTarget)
            }
            else {
                flywheel.setPower(0)
            }


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
        } else {
            counter = 0;
            launcher_wheel.setPower(0);
            right_launch_servo.setPower(0);
            left_launch_servo.setPower(0);
        }
    }
}
