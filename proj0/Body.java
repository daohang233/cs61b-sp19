public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67 * 10e-12;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    /** Calculate square root of input a */
    private static double sqrtRoot(double c){
        if(c < 0) 
            return Double.NaN;
        double err = 1e-15;
        double t = c ;
        while (Math.abs(t - c / t) > err * t){
          t = (c/t + t) / 2.0;
        }
        return t;
      }

    /** Calculate distance between self and the input Body */
    public double calcDistance(Body b) {
        double dx = xxPos - b.xxPos;
        double dy = yyPos - b.yyPos;

        return sqrtRoot(dx * dx + dy * dy);
    }

    /** Calculate the force exerted on this body by the given body */
    public double calcForceExertedBy(Body b) {
        return (G * mass * b.mass) / (calcDistance(b) * calcDistance(b));
    }

    /** Calculate the force exerted in the X direction */
    public double calcForceExertedByX(Body b) {
        return calcForceExertedBy(b) * ((b.xxPos - xxPos) / calcDistance(b));
    }

    /** Calculate the force exerted in the Y direction */
    public double calcForceExertedByY(Body b) {
        return calcForceExertedBy(b) * ((b.yyPos - yyPos) / calcDistance(b));
    }

    /** Calculate the net X force exerted by all bodies */
    public double calcNetForceExertedByX(Body allBodys[]) {
        double sum = 0;
        for(int i = 0;i < allBodys.length;i++) {
            if(this.equals(allBodys[i])) {
                continue;
            }
            sum = sum + calcForceExertedByX(allBodys[i]);
        }
        return sum;
    }

    /** Calculate the net Y force exerted by all bodies */
    public double calcNetForceExertedByY(Body allBodys[]) {
        double sum = 0;
        for(int i = 0;i < allBodys.length;i++) {
            if(this.equals(allBodys[i])) {
                continue;
            }
            sum += calcForceExertedByY(allBodys[i]);
        }
        return sum;
    }

    /** Update the body's position and velocity */
    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;

        xxVel += dt * aX;
        yyVel += dt * aY;

        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    /** Draw this body */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        //StdDraw.show();
    }
}