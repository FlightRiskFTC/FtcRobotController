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
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

@TeleOp(name = "TeleOp")

public class vincent_opmode extends OpMode {

    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
    FtcDashboard dashboard = FtcDashboard.getInstance();
    TelemetryPacket packet = new TelemetryPacket();

    public void init(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        packet.put("x", pose.getX());
        packet.put("y", pose.getY());
        packet.put("heading", pose.getHeading());
        dashboard.sendTelemetryPacket(packet);
    };

    public void init_loop() {};

    public void start() {}

    @Override

    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rot = -gamepad1.right_stick_x;

        frontLeft.setPower((y + x + rot) * power);
        frontRight.setPower((y - x + rot) * power);
        backLeft.setPower((y - x - rot) * power);
        backRight.setPower((y + x - rot) * power);
    }
}
