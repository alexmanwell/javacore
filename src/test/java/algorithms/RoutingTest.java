package algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RoutingTest {

  @Test
  public void testSimpleTwoCompsRouting() throws Routing.IllegalMaskSubnetException {
    /*
            2
            1
            1.1.1.1 255.255.255.0
            1
            1.1.1.2 255.255.255.0
            1 2
    */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("1.1.1.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("1.1.1.2", "255.255.255.0")
            )
            )
    );

    int startComp = 1;
    int endComp = 2;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(1, 2);
    List<Integer> actuals = routing.buildPath();

    assertEquals(expected, actuals);
  }

  @Test
  public void testSimpleReverseTwoCompsRouting() throws Routing.IllegalMaskSubnetException {
    /*
            2
            1
            1.1.1.1 255.255.255.0
            1
            1.1.1.2 255.255.255.0
            2 1
    */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("1.1.1.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("1.1.1.2", "255.255.255.0")
            )
            )
    );

    int startComp = 2;
    int endComp = 1;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(2, 1);
    List<Integer> actuals = routing.buildPath();

    assertEquals(expected, actuals);
  }

  @Test(expected = Routing.IllegalMaskSubnetException.class)
  public void testInvalidSubnet() throws Routing.IllegalMaskSubnetException {
    /*
            2
            1
            1.1.1.1 0.0.0
            1
            1.1.1.2 0.0.0.0
            2 1

    */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("1.1.1.1", "0.0.0")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("1.1.1.2", "0.0.0.0")
            )
            )
    );
  }

  @Test(expected = Routing.IllegalMaskSubnetException.class)
  public void testInvalidSubnetZeroOne() throws Routing.IllegalMaskSubnetException {
    /*
            2
            1
            1.1.1.1 0.27.0.63
            1
            1.1.1.2 0.0.0.0
            2 1
    */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("1.1.1.1", "0.27.0.63")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("1.1.1.2", "0.0.0.0")
            )
            )
    );
  }

  @Test(expected = Routing.IllegalMaskSubnetException.class)
  public void testInvalidSubnetZeroZeroZeroOne() throws Routing.IllegalMaskSubnetException {
    /*
            2
            1
            1.1.1.1 0.0.0.255
            1
            1.1.1.2 0.0.0.0
            2 1
    */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("1.1.1.1", "0.0.0.255")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("1.1.1.2", "0.0.0.0")
            )
            )
    );
  }

  @Test(expected = NullPointerException.class)
  public void testInvalidTwoCompsRouting() throws Routing.IllegalMaskSubnetException {
    /*
            2
            1
            195.38.54.65 255.0.0.0
            1
            1.1.1.1 255.0.0.0
            1 2
    */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("195.38.54.65", "255.0.0.0")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("1.1.1.1", "255.0.0.0")
            )
            )
    );

    int startComp = 1;
    int endComp = 2;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(1, 2);
    List<Integer> actuals = routing.buildPath();

    assertTrue(actuals.equals(expected));
  }

  @Test
  public void testMinimialTwoCompsRouting() throws Routing.IllegalMaskSubnetException {
    /*
            2
            1
            195.38.54.65 255.255.255.224
            1
            195.38.54.94 255.255.255.224
            1 2
    */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("195.38.54.65", "255.255.255.224")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("195.38.54.94", "255.255.255.224")
            )
            )
    );

    int startComp = 1;
    int endComp = 2;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(1, 2);
    List<Integer> actuals = routing.buildPath();

    assertEquals(expected, actuals);
  }

  @Test
  public void testMinimialReverseTwoCompsRouting() throws Routing.IllegalMaskSubnetException {
    /*
            2
            1
            195.38.54.65 255.255.255.224
            1
            195.38.54.94 255.255.255.224
            2 1
    */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("195.38.54.65", "255.255.255.224")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("195.38.54.94", "255.255.255.224")
            )
            )
    );

    int startComp = 2;
    int endComp = 1;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(2, 1);
    List<Integer> actuals = routing.buildPath();

    assertEquals(expected, actuals);
  }

  @Test
  public void testInvalidRouting() throws Exception {
    /*
            2
            1
            195.38.54.65 255.255.255.224
            1
            195.38.54.0 255.255.0.0
            1 2
    */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("195.38.54.65", "255.255.255.224")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("195.38.54.0", "255.255.0.0")
            )
            )
    );

    int startComp = 1;
    int endComp = 2;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> actuals = routing.buildPath();

    assertEquals("No", routing.printMessage(actuals));
  }

  @Test
  public void testRoutingSixComp() throws Routing.IllegalMaskSubnetException {
        /*
            6
            2
            10.0.0.1 255.0.0.0
            192.168.0.1 255.255.255.0
            1
            10.0.0.2 255.0.0.0
            3
            192.168.0.2 255.255.255.0
            212.220.31.1 255.255.255.0
            212.220.35.1 255.255.255.0
            1
            212.220.31.2 255.255.255.0
            2
            212.220.35.2 255.255.255.0
            195.38.54.65 255.255.255.224
            1
            195.38.54.94 255.255.255.224

            1 6
     */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Arrays.asList(
                    Routing.Interface.create("10.0.0.1", "255.0.0.0"),
                    Routing.Interface.create("192.168.0.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("10.0.0.2", "255.0.0.0")
            )
            ),
            new Routing.Comp(3, Arrays.asList(
                    Routing.Interface.create("192.168.0.2", "255.255.255.0"),
                    Routing.Interface.create("212.220.31.1", "255.255.255.0"),
                    Routing.Interface.create("212.220.35.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(4, Collections.singletonList(
                    Routing.Interface.create("212.220.31.2", "255.255.255.0")
            )
            ),
            new Routing.Comp(5, Arrays.asList(
                    Routing.Interface.create("212.220.35.2", "255.255.255.0"),
                    Routing.Interface.create("195.38.54.65", "255.255.255.224")
            )
            ),
            new Routing.Comp(6, Collections.singletonList(
                    Routing.Interface.create("195.38.54.94", "255.255.255.224")
            )
            )
    );
    int startComp = 1;
    int endComp = 6;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(1, 3, 5, 6);
    List<Integer> actuals = routing.buildPath();

    assertEquals(expected, actuals);
  }

  @Test
  public void testSixCompReverseSecondCompRoutingFirstComp() throws Routing.IllegalMaskSubnetException {
        /*
            6
            2
            10.0.0.1 255.0.0.0
            192.168.0.1 255.255.255.0
            1
            10.0.0.2 255.0.0.0
            3
            192.168.0.2 255.255.255.0
            212.220.31.1 255.255.255.0
            212.220.35.1 255.255.255.0
            1
            212.220.31.2 255.255.255.0
            2
            212.220.35.2 255.255.255.0
            195.38.54.65 255.255.255.224
            1
            195.38.54.94 255.255.255.224

            2 1
     */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Arrays.asList(
                    Routing.Interface.create("10.0.0.1", "255.0.0.0"),
                    Routing.Interface.create("192.168.0.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("10.0.0.2", "255.0.0.0")
            )
            ),
            new Routing.Comp(3, Arrays.asList(
                    Routing.Interface.create("192.168.0.2", "255.255.255.0"),
                    Routing.Interface.create("212.220.31.1", "255.255.255.0"),
                    Routing.Interface.create("212.220.35.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(4, Collections.singletonList(
                    Routing.Interface.create("212.220.31.2", "255.255.255.0")
            )
            ),
            new Routing.Comp(5, Arrays.asList(
                    Routing.Interface.create("212.220.35.2", "255.255.255.0"),
                    Routing.Interface.create("195.38.54.65", "255.255.255.224")
            )
            ),
            new Routing.Comp(6, Collections.singletonList(
                    Routing.Interface.create("195.38.54.94", "255.255.255.224")
            )
            )
    );
    int startComp = 2;
    int endComp = 1;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(2, 1);
    List<Integer> actuals = routing.buildPath();

    assertEquals(expected, actuals);
  }

  @Test
  public void testRoutingSixCompReverse() throws Routing.IllegalMaskSubnetException {
        /*
            6
            2
            10.0.0.1 255.0.0.0
            192.168.0.1 255.255.255.0
            1
            10.0.0.2 255.0.0.0
            3
            192.168.0.2 255.255.255.0
            212.220.31.1 255.255.255.0
            212.220.35.1 255.255.255.0
            1
            212.220.31.2 255.255.255.0
            2
            212.220.35.2 255.255.255.0
            195.38.54.65 255.255.255.224
            1
            195.38.54.94 255.255.255.224

            6 1
     */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Arrays.asList(
                    Routing.Interface.create("10.0.0.1", "255.0.0.0"),
                    Routing.Interface.create("192.168.0.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("10.0.0.2", "255.0.0.0")
            )
            ),
            new Routing.Comp(3, Arrays.asList(
                    Routing.Interface.create("192.168.0.2", "255.255.255.0"),
                    Routing.Interface.create("212.220.31.1", "255.255.255.0"),
                    Routing.Interface.create("212.220.35.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(4, Collections.singletonList(
                    Routing.Interface.create("212.220.31.2", "255.255.255.0")
            )
            ),
            new Routing.Comp(5, Arrays.asList(
                    Routing.Interface.create("212.220.35.2", "255.255.255.0"),
                    Routing.Interface.create("195.38.54.65", "255.255.255.224")
            )
            ),
            new Routing.Comp(6, Collections.singletonList(
                    Routing.Interface.create("195.38.54.94", "255.255.255.224")
            )
            )
    );
    int startComp = 6;
    int endComp = 1;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(6, 5, 3, 1);
    List<Integer> actuals = routing.buildPath();

    assertEquals(expected, actuals);
  }


  @Test
  public void testTenCompsRouting() throws Routing.IllegalMaskSubnetException {
    /*
            10
            2
            10.0.0.1 255.0.0.0
            192.168.0.1 255.255.255.0
            1
            10.0.0.2 255.0.0.0
            3
            192.168.0.2 255.255.255.0
            212.220.31.1 255.255.255.0
            212.220.35.1 255.255.255.0
            1
            212.220.31.2 255.255.255.0
            2
            212.220.35.2 255.255.255.0
            195.38.54.65 255.255.255.224
            2
            128.178.0.1 255.255.255.0
            10.10.10.4  255.255.255.0
            1
            212.220.31.7 255.255.255.0
            2
            128.178.0.3 255.255.255.0
            195.38.54.64 255.255.255.224
            2
            10.10.10.3  255.255.255.0
            192.168.0.3 255.255.255.0
            1
            195.38.54.66 255.255.255.224

            1 10
     */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Arrays.asList(
                    Routing.Interface.create("10.0.0.1", "255.0.0.0"),
                    Routing.Interface.create("192.168.0.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("10.0.0.2", "255.0.0.0")
            )
            ),
            new Routing.Comp(3, Arrays.asList(
                    Routing.Interface.create("192.168.0.2", "255.255.255.0"),
                    Routing.Interface.create("212.220.31.1", "255.255.255.0"),
                    Routing.Interface.create("212.220.35.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(4, Collections.singletonList(
                    Routing.Interface.create("212.220.31.2", "255.255.255.0")
            )
            ),
            new Routing.Comp(5, Arrays.asList(
                    Routing.Interface.create("212.220.35.2", "255.255.255.0"),
                    Routing.Interface.create("195.38.54.65", "255.255.255.224")
            )
            ),
            new Routing.Comp(6, Arrays.asList(
                    Routing.Interface.create("128.178.0.1", "255.255.255.0"),
                    Routing.Interface.create("10.10.10.4", "255.255.255.0")
            )
            ),
            new Routing.Comp(7, Collections.singletonList(
                    Routing.Interface.create("212.220.31.3", "255.255.255.0")
            )
            ),
            new Routing.Comp(8, Arrays.asList(
                    Routing.Interface.create("128.178.0.3", "255.255.255.0"),
                    Routing.Interface.create("195.38.54.64", "255.255.255.224")
            )
            ),
            new Routing.Comp(9, Arrays.asList(
                    Routing.Interface.create("10.10.10.3", "255.255.255.0"),
                    Routing.Interface.create("192.168.0.3", "255.255.255.0")
            )
            ),
            new Routing.Comp(10, Collections.singletonList(
                    Routing.Interface.create("195.38.54.66", "255.255.255.224")
            )
            )
    );
    int startComp = 1;
    int endComp = 10;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(1, 3, 5, 10);
    List<Integer> actuals = routing.buildPath();
    assertEquals(expected, actuals);
  }

  @Test
  public void testReverseTenCompsRouting() throws Routing.IllegalMaskSubnetException {
    /*
            10
            2
            10.0.0.1 255.0.0.0
            192.168.0.1 255.255.255.0
            1
            10.0.0.2 255.0.0.0
            3
            192.168.0.2 255.255.255.0
            212.220.31.1 255.255.255.0
            212.220.35.1 255.255.255.0
            1
            212.220.31.2 255.255.255.0
            2
            212.220.35.2 255.255.255.0
            195.38.54.65 255.255.255.224
            2
            128.178.0.1 255.255.255.0
            10.10.10.1  255.255.255.0
            1
            212.220.31.5 255.255.255.0
            2
            128.178.0.3  255.255.255.0
            195.38.54.65 255.255.255.224
            2
            10.0.0.1 255.0.0.0
            192.168.0.1 255.255.255.0
            1
            195.38.54.94 255.255.255.224

            10 1
     */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Arrays.asList(
                    Routing.Interface.create("10.0.0.1", "255.0.0.0"),
                    Routing.Interface.create("192.168.0.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(2, Collections.singletonList(
                    Routing.Interface.create("10.0.0.2", "255.0.0.0")
            )
            ),
            new Routing.Comp(3, Arrays.asList(
                    Routing.Interface.create("192.168.0.2", "255.255.255.0"),
                    Routing.Interface.create("212.220.31.1", "255.255.255.0"),
                    Routing.Interface.create("212.220.35.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(4, Collections.singletonList(
                    Routing.Interface.create("212.220.31.2", "255.255.255.0")
            )
            ),
            new Routing.Comp(5, Arrays.asList(
                    Routing.Interface.create("212.220.35.2", "255.255.255.0"),
                    Routing.Interface.create("195.38.54.65", "255.255.255.224")
            )
            ),
            new Routing.Comp(6, Arrays.asList(
                    Routing.Interface.create("128.178.0.1", "255.255.255.0"),
                    Routing.Interface.create("10.10.10.4", "255.255.255.0")
            )
            ),
            new Routing.Comp(7, Collections.singletonList(
                    Routing.Interface.create("212.220.31.3", "255.255.255.0")
            )
            ),
            new Routing.Comp(8, Arrays.asList(
                    Routing.Interface.create("128.178.0.3", "255.255.255.0"),
                    Routing.Interface.create("195.38.54.64", "255.255.255.224")
            )
            ),
            new Routing.Comp(9, Arrays.asList(
                    Routing.Interface.create("10.10.10.3", "255.255.255.0"),
                    Routing.Interface.create("192.168.0.3", "255.255.255.0")
            )
            ),
            new Routing.Comp(10, Collections.singletonList(
                    Routing.Interface.create("195.38.54.94", "255.255.255.224")
            )
            )
    );
    int startComp = 10;
    int endComp = 1;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(10, 5, 3, 1);
    List<Integer> actuals = routing.buildPath();
    assertEquals(expected, actuals);
  }

  @Test
  public void testFourCompsRouting() throws Routing.IllegalMaskSubnetException {
           /*
            4
            1
            10.0.0.1 255.0.0.0
            2
            10.0.0.2 255.0.0.0
            192.168.0.1 255.255.255.0
            2
            212.220.31.1 255.255.255.0
            192.168.0.2 255.255.255.0
            1
            212.220.31.1 255.255.255.0
            1 4
            */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("10.0.0.1", "255.0.0.0")
            )
            ),
            new Routing.Comp(2, Arrays.asList(
                    Routing.Interface.create("10.0.0.2", "255.0.0.0"),
                    Routing.Interface.create("192.168.0.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(3, Arrays.asList(
                    Routing.Interface.create("212.220.31.1", "255.255.255.0"),
                    Routing.Interface.create("192.168.0.1", "255.255.255.0")
            )
            ),
            new Routing.Comp(4, Collections.singletonList(
                    Routing.Interface.create("212.220.31.1", "255.255.255.0")
            )
            )
    );

    int startComp = 1;
    int endComp = 4;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(1, 2, 3, 4);
    List<Integer> actuals = routing.buildPath();

    assertEquals(expected, actuals);
  }

  @Test(expected = Routing.IllegalMaskSubnetException.class)
  public void testInvalidMaskOneZeroZeroOne() throws Routing.IllegalMaskSubnetException {
           /*
            1
            1
            212.220.31.1 255.128.128.255
            1 1
            */
    List<Routing.Comp> comps = Collections.singletonList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("212.220.31.1", "255.128.128.255")
            )
            )
    );
  }

  @Test(expected = Routing.IllegalMaskSubnetException.class)
  public void testInvalidMaskOneOneOneOne() throws Routing.IllegalMaskSubnetException {
           /*
            1
            1
            212.220.31.1 255.255.255.255
            1 1
            */
    List<Routing.Comp> comps = Collections.singletonList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("212.220.31.1", "255.255.255.255")
            )
            )
    );
  }

  @Test
  public void testInvalidMaskOneOneOneNull() throws Routing.IllegalMaskSubnetException {
           /*
            1
            1
            212.220.31.1 255.255.255.0
            1 1
            */
    List<Routing.Comp> comps = Collections.singletonList(
            new Routing.Comp(1, Collections.singletonList(
                    Routing.Interface.create("212.220.31.1", "255.255.255.0")
            )
            )
    );
  }

  @Test
  public void testFiveCompsRouting() throws Routing.IllegalMaskSubnetException {
           /*
            5
            2
            192.168.0.10 255.255.255.0
            192.168.0.11 255.255.255.0
            2
            212.220.31.1 255.255.255.0
            192.168.0.8 255.255.255.0
            2
            192.168.0.5 255.255.255.0
            128.178.0.6 255.255.255.0
            2
            192.168.0.2 255.255.255.0
            212.220.31.2 255.255.255.0
            2
            192.168.0.3 255.255.255.0
            128.178.0.1 255.255.255.0
            1 5
            */
    List<Routing.Comp> comps = Arrays.asList(
            new Routing.Comp(1, Arrays.asList(
                    Routing.Interface.create("192.168.0.10", "255.255.255.0"),
                    Routing.Interface.create("192.168.0.11", "255.255.255.0")
            )
            ),
            new Routing.Comp(2, Arrays.asList(
                    Routing.Interface.create("212.220.31.1", "255.255.255.0"),
                    Routing.Interface.create("192.168.0.8", "255.255.255.0")
            )
            ),
            new Routing.Comp(3, Arrays.asList(
                    Routing.Interface.create("192.168.0.5", "255.255.255.0"),
                    Routing.Interface.create("128.178.0.6", "255.255.255.0")
            )
            ),
            new Routing.Comp(4, Arrays.asList(
                    Routing.Interface.create("192.168.0.2", "255.255.255.0"),
                    Routing.Interface.create("212.220.31.2", "255.255.255.0")
            )
            ),
            new Routing.Comp(5, Arrays.asList(
                    Routing.Interface.create("192.168.0.3", "255.255.255.0"),
                    Routing.Interface.create("128.178.0.1", "255.255.255.0")
            )
            )
    );

    int startComp = 1;
    int endComp = 5;

    Routing routing = new Routing(comps, startComp, endComp);

    List<Integer> expected = Arrays.asList(1, 5);
    List<Integer> actuals = routing.buildPath();
    assertEquals(expected, actuals);
  }
}
