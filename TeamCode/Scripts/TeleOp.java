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
    double power = 1;
    double flywheelVel = 0;
    double targetFlywheelSpeed = .5

    public void init(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
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

        if(gamepad1.right_bumper && flywheelVel < targetFlywheelSpeed){
            
            double motorSpeedTowardsTarget;

            if(flywheelSpeed > (targetFlywheelSpeed / 2)){
                double slowRange = flywheelSpeed / 2;
                motorSpeedTowardsTarget = 1 - (2 * (slowRange / 0.5));
            }
            else{
                motorSpeedTowardsTarget = 1;
            }            
            flywheel.setPower(motorSpeedTowardsTarget)
        }
    }
}