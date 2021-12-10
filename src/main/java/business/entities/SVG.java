package business.entities;

import java.util.Locale;

public class SVG {
    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;

    private final String headerTemplate = "<svg height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" " +
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";

    private final String dashLineTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:black\" stroke-dasharray=\"5, 5\" />";

    private final String lineForArrow = "<line x1=\"%f\"  y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke: #000000; " +
            "marker-start: url(#beginArrow);" +
            "marker-end: url(#endArrow);\"/>";

    private final String arrowTextHorizontal = "<text style=\"text-anchor: middle\" x=\"%f\" y=\"%f\">%d cm</text>";
    private final String arrowTextVertical = "<text style=\"text-anchor: middle\" transform=\"translate(%f,%f) rotate(-90)\">%d cm</text>";


    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;

        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));
    }

    public void addRect(double x, double y, double height, double width) {

//        String rectTemplate = "<rect x=\"" + x + "\" y=\"" + y + "\" height=\"" + height + "\" width=\"" + width + "\" style=\"stroke:#000000; fill: #ffffff\" />";
//        svg.append(rectTemplate);
        svg.append(String.format(Locale.US, rectTemplate, x, y, height, width));
    }

    public void addDashLine(double x1, double y1, double x2, double y2) {
        svg.append(String.format(Locale.US, dashLineTemplate, x1, y1, x2, y2));
    }

    public void addSvg(SVG innerSVG) {
        svg.append(innerSVG.toString());
    }

    public void addHorizontalArrowWithText(double width, double height, int margin) {
        svg.append(arrowHead());
        svg.append(String.format(Locale.US,lineForArrow, 0.0 + margin, height + margin / 2, width + margin, height + margin / 2));
        svg.append(String.format(Locale.US,arrowTextHorizontal, width / 2 + margin, height + margin / 2 - 2, (int) width));

    }

    public void addVerticalArrowWithText(double height, int margin) {
        svg.append(arrowHead());
        svg.append(String.format(Locale.US,lineForArrow, (double) margin / 2, 0.0, (double) margin / 2, height));
        svg.append(String.format(Locale.US,arrowTextVertical, (double) margin / 2 - 2, height / 2, (int) height));


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

