package CargoDescription.CargoAction;

import CargoDescription.Cargo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class CargoActionTest {
    @Test
    void generateCargo() {
        Cargo cargo=CargoAction.GenerateCargo();
        Assert.assertNotNull(cargo);
        Assert.assertTrue(cargo.getExplosive()>=0);
        Assert.assertTrue(cargo.getSensetive()>=0);
        Assert.assertTrue(cargo.getOrdinary()>=0);
        Assert.assertTrue(cargo.getFlammable()>=0);
        Assert.assertTrue(cargo.getPoisonous()>=0);
    }


}