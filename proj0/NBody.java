class NBody {
    /** Read the radius of the universe  */
    public static double readRadius(String path) {
        In in = new In(path);
        in.readInt();
        return in.readDouble();
    }

    /** Read an array of Bodys from the specific file */
    public static Body[] readallBodys(String path) {
        In in = new In(path);
        int n = in.readInt();
        in.readDouble();

        Body[] res = new Body[5];

        for(int i = 0;i < n;i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFilePath = in.readString();

            res[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFilePath);
        }
        return res;
    }

    public static void main(String args[]) {
        /** Collecting all needed input */
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] allBodys = readallBodys(filename);

        /** Creating an animation */
        double time = 0;
        while(time <= T) {
            double[] xForces = new double[allBodys.length];
            double[] yForces = new double[allBodys.length];
            for(int i = 0;i < allBodys.length;i++) {
                xForces[i] = allBodys[i].calcNetForceExertedByX(allBodys);
                yForces[i] = allBodys[i].calcNetForceExertedByY(allBodys);
            }
            for(int i = 0;i < allBodys.length;i++) {
                allBodys[i].update(dt, xForces[i], yForces[i]);
            }
            /** Drawing the background */
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            

            /** Drawing all bodys */
            for(int i = 0;i < allBodys.length;i++) {
                allBodys[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }
        StdOut.printf("%d\n", allBodys.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodys.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        allBodys[i].xxPos, allBodys[i].yyPos, allBodys[i].xxVel,
                        allBodys[i].yyVel, allBodys[i].mass, allBodys[i].imgFileName);   
        }
    }
}