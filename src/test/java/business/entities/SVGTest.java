package business.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SVGTest {

    SVG svg;

    @Test
    public void testStringbuilderFormat(){
        svg = new SVG(0,0,"500   600", 100, 100);
        svg.addRect(5.5,50.5, 10.6, 80.3);
    }

}