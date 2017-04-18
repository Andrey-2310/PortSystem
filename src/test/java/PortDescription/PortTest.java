package PortDescription;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;


public class PortTest {
    @Test
    public void CheckPortData() throws Exception {
        Port port = new Port("Rotterdam", new Point2D(10, 20));
        Assert.assertEquals("Rotterdam", port.getPortName());
        Assert.assertEquals(10, port.getCoord().getX());
        Assert.assertEquals(20, port.getCoord().getY());
        Assert.assertEquals("Rotterdam", port.getMapPoint().getPointName());
    }

}