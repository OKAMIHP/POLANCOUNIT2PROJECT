import java.util.Scanner;

public class LinearEquation {
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        public LinearEquation(int x1, int y1, int x2, int y2) {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
        }

        public double distance() {
                return roundedToHundredth(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
        }

        public double yIntercept() {
                double slope = slope();
                return roundedToHundredth(y1 - slope * x1);
        }

        public double slope() {
                if (x1 == x2) {
                        return 0;
                }
                return roundedToHundredth((double) (y2 - y1) / (x2 - x1));
        }

        public String equation() {
                double slope = slope();
                double yIntercept = yIntercept();

                String slopeStr = formatSlope(slope);
                String yInterceptStr = formatYIntercept(yIntercept);

                if (slope == 0) {
                        return "y = " + yInterceptStr;
                } else if (slope > 0) {
                        return "y = " + slopeStr + "x + " + yInterceptStr;
                } else {
                        return "y = " + slopeStr + "x - " + yInterceptStr;
                }
        }

        public String coordinateForX(double xValue) {
                double yValue = roundedToHundredth(slope() * xValue + yIntercept());
                return "(" + xValue + ", " + yValue + ")";
        }

        public double roundedToHundredth(double toRound) {
                return Math.round(toRound * 100.0) / 100.0;
        }

        private String formatSlope(double slope) {
                if (slope == 1 || slope == -1) {
                        return "";
                } else if (slope % 1 == 0) {
                        return Integer.toString((int) slope);
                } else {
                        return formatFraction(slope);
                }
        }

        private String formatFraction(double fraction) {
                int numerator = (int) fraction;
                int denominator = 1;
                while (fraction % 1 != 0) {
                        fraction *= 10;
                        denominator *= 10;
                }
                int gcd = gcd(numerator, denominator);
                numerator /= gcd;
                denominator /= gcd;
                return numerator + "/" + denominator;
        }

        private int gcd(int a, int b) {
                if (b == 0) {
                        return a;
                }
                return gcd(b, a % b);
        }

        private String formatYIntercept(double yIntercept) {
                String yInterceptStr = Double.toString(yIntercept);
                if (yInterceptStr.endsWith(".0")) {
                        return yInterceptStr.substring(0, yInterceptStr.length() - 2);
                }
                return yInterceptStr;
        }

        public String lineInfo() {
                String points = "The original points: (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")";
                String equation = "The equation of the line: " + equation();
                String slope = "The slope of the line: " + slope();
                String yIntercept = "The y-intercept of the line: " + yIntercept();
                String distance = "The distance between the two points: " + distance();

                return points + "\n" + equation + "\n" + slope + "\n" + yIntercept + "\n" + distance;
        }

        public static void main(String[] args) {
                LinearEquationRunner runner = new LinearEquationRunner();
                runner.start();
        }
}
