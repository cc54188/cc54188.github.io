package game.utils;

public class Vector {

    private double x;
    private double y;

    public Vector() { // 預設靜止
        this(0, 0);
    }

    public Vector(double x, double y) {  // 外面給x,y
        this.x = x;
        this.y = y;
    }

    public Vector(Vector vector) {
        this(vector.x, vector.y);
    }

    // 360度 = 2PI
    public final double getRadian() {  // 取得弧度
        return Math.atan2(y, x);
    }

    public final double getAngle() {  // 取得角度
        return getRadian() / Math.PI * 180;
    }

    public final double length() { // 長度
        return Math.sqrt(lengthSqaure()); // 平方開根
    }

    public final double lengthSqaure() { // 長度平方
        return x * x + y * y;
    }
    public void setVx(double x){  
        this.x=x;
    }
    public void setVy(double y ){
        this.y=y;
    }

    public final Vector zero() {   // 0向量?
        this.x = this.y = 0;
        return this;
    }

    public final boolean isZero() { // 判斷是否為0向量
        return x == 0 && y == 0;
    }

    public final Vector setLength(double value) {  // 由長度設定角度
        double angle = getAngle();       // 角度
        x = Math.cos(angle) * value;     
        y = Math.sin(angle) * value;
        return this;
    }

    public final Vector normalize() {  // (單位向量)
        double length = length();    // 算出長度
        x = x / length;            // cos
        y = y / length;            // sin
        return this;
    }

    public final boolean isNormalized() { // 判斷是否單位向量
        return length() == 1.0;          // 長度是否為1
    }

    public Vector reverse() {           // 相反
        x = -x;
        y = -y;
        return this;
    }

    // 求兩向量的dot product  內積??????????
    public double dotP(Vector v) {
        return x * v.x + y * v.y;
    }

    // 求兩向量的cross product  外積????????????
    public double crossP(Vector v) {
        return x * v.y - y * v.x;
    }

    // 求兩向量間夾角
    public static double radianBetween(Vector v1, Vector v2) {
        if (!v1.isNormalized()) {              // 若v1不是單位向量
            v1 = new Vector(v1).normalize(); // |v1| = 1
        }
        if (!v2.isNormalized()) {             // 若v2不是單位向量
            v2 = new Vector(v2).normalize(); // |v2| = 1
        }
        return Math.acos(v1.dotP(v2));
    }

    public double vx() {
        return x;
    }

    public double vy() {
        return y;
    }
    public void changeY(double vy){   // 改變y大小
        this.y = y+vy;
    }
    public void changeX(double vx){  // 改變x大小
        this.y = y+vx;
    }

    public Vector add(Vector v) {   // 向量相加
        return new Vector(x + v.x, y + v.y);
    }

    public Vector sub(Vector v) {  // 向量相減
        return new Vector(x - v.x, y - v.y);
    }

    public Vector multiply(double value) { // 向量放大(乘)
        return new Vector(x * value, y * value);
    }
    
    public Vector divide(double value) {  // 向量縮小(除)
        return new Vector(x / value, y / value);
    }
}
