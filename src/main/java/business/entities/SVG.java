package business.entities;

import java.util.Locale;

public class SVG {
    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;

//    private final String headerTemplate = "<svg height=\"%d%%\" " +
//            "width=\"%d%%\" " +
//            "viewBox=\"%s\" " +
//            "x=\"%d\"   " +
//            "y=\"%d\"   " +
//            " preserveAspectRatio=\"xMinYMin\">";

    private final String headerTemplate = "<svg viewBox=\"%s\" " +
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String rectTemplateDashed = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" stroke-dasharray=\"5, 5\"/>";
    private final String rectTemplateRotate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" transform=\"rotate(1)\" />";

    private final String lineTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:black\" />";
    private final String dashLineTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:black\" stroke-dasharray=\"5, 5\" />";
    private final String lineForArrow = "<line x1=\"%f\"  y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke: #000000; " +
            "marker-start: url(#beginArrow);" +
            "marker-end: url(#endArrow);\"/>";

    private final String arrowTextHorizontal = "<text style=\"text-anchor: middle\" x=\"%f\" y=\"%f\">%d cm</text>";
    private final String arrowTextVertical = "<text style=\"text-anchor: middle\" transform=\"translate(%f,%f) rotate(-90)\">%d cm</text>";

    private final String rectShedTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; stroke-width: 3px; fill: none\" stroke-dasharray=\"5, 5\"/>";


    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;

//        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));
        svg.append(String.format(headerTemplate, viewBox, x, y));
    }

    public void addRect(double x, double y, double height, double width) {
        svg.append(String.format(Locale.US, rectTemplate, x, y, height, width));
    }

    public void addRectDash(double x, double y, double height, double width) {
        svg.append(String.format(Locale.US, rectTemplateDashed, x, y, height, width));
    }

    public void addRectShed(double x, double y, double height, double width) {
        svg.append(String.format(Locale.US, rectShedTemplate, x, y, height, width));
    }


    public void addRectRotate(double x, double y, double height, double width) {
        svg.append(String.format(Locale.US, rectTemplateRotate, x, y, height, width));
    }

    public void addLine(double x1, double y1, double x2, double y2) {
        svg.append(String.format(Locale.US, lineTemplate, x1, y1, x2, y2));
    }

    public void addDashLine(double x1, double y1, double x2, double y2) {
        svg.append(String.format(Locale.US, dashLineTemplate, x1, y1, x2, y2));
    }

    public void addSvg(SVG innerSVG) {
        svg.append(innerSVG.toString());
    }

    public void addHorizontalArrowWithText(double width, double height, int margin) {
        svg.append(arrowHead());
        svg.append(String.format(Locale.US,lineForArrow, 0.0 + margin, height + margin / 2 + margin, width + margin, height + margin / 2 + margin));
        svg.append(String.format(Locale.US,arrowTextHorizontal, width / 2 + margin, height + margin / 2 - 4 + margin, (int) width));

    }

    public void addVerticalArrowWithText(double height, int margin) {
        svg.append(arrowHead());
        svg.append(String.format(Locale.US,lineForArrow, (double) margin / 2, (double)margin, (double) margin / 2, height + margin));
        svg.append(String.format(Locale.US,arrowTextVertical, (double) margin / 2 - 4, height / 2 + margin, (int) height));

    }

    public void addHorizontalMeasurement(double x1, double y1, double x2, double y2, int input ){
        svg.append(arrowHead());
        svg.append(String.format(Locale.US, lineForArrow, x1, y1, x2, y2));
        svg.append(String.format(Locale.US, arrowTextHorizontal, (x1 + x2)/2, y1 - 7, input ));

    }

    public void addVerticalMeasurement(double x1, double y1, double x2, double y2, int input ){
        svg.append(arrowHead());
        svg.append(String.format(Locale.US, lineForArrow, x1, y1, x2, y2));
        svg.append(String.format(Locale.US, arrowTextVertical, x1 - 7, (y1 + y2)/2, input ));

    }

    private String arrowHead() {
        return "<defs>\n" +
                "<marker\n" +
                "                id=\"beginArrow\"\n" +
                "                        markerWidth=\"12\"\n" +
                "                        markerHeight=\"12\"\n" +
                "                        refX=\"0\"\n" +
                "                        refY=\"6\"\n" +
                "                        orient=\"auto\">\n" +
                "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\"/>\n" +
                "</marker>\n" +
                "<marker\n" +
                "                id=\"endArrow\"\n" +
                "                        markerWidth=\"12\"\n" +
                "                        markerHeight=\"12\"\n" +
                "                        refX=\"12\"\n" +
                "                        refY=\"6\"\n" +
                "                        orient=\"auto\">\n" +
                "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\"/>\n" +
                "</marker>\n" +
                "</defs>";
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }

}

