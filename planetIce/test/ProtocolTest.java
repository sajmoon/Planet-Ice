package planetIce.test;

import junit.framework.TestCase;
import planetIce.Game.Game;
import planetIce.network.PlanetIceServerProtocol;

public class ProtocolTest extends TestCase {

    protected void setUp() throws Exception {
	super.setUp();
    }

    protected void tearDown() throws Exception {
	super.tearDown();
    }

    public void test1() {
	PlanetIceServerProtocol prot = new PlanetIceServerProtocol(new Game());
	assertEquals( prot.act("Ping?", 0),"Pong!");
    }
}
