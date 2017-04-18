package CargoDescription;

import org.junit.Assert;
import org.junit.Test;


public class CargoTest {

    @Test
    public void getCargoInfo() throws Exception {
        Cargo cargo = new Cargo(2, 10, 100, 200, 300, 400, 500);
        Assert.assertEquals(2, cargo.getPriority());
        Assert.assertEquals(100, cargo.getOrdinary());
        Assert.assertEquals(200, cargo.getExplosive());
        Assert.assertEquals(300, cargo.getPoisonous());
        Assert.assertEquals(400, cargo.getSensetive());
        Assert.assertEquals(500, cargo.getFlammable());
    }


}