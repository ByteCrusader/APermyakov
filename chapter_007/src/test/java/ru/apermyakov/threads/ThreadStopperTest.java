package ru.apermyakov.threads;

import org.junit.Test;

/**
 * Class for test threads stop.
 *
 * @author apermyakov
 * @version 1.0
 * @since 15.11.2017
 */
public class ThreadStopperTest {

    /**
     * Test when app pass time ex then take number of char.
     */
    @Test
    public void whenAppPassTimeExThenTakeNumberOfChar() {
        ThreadStopper.initialize("Hi, Petr, lets check this text - " +
                        "ghfhfgfgjkhjhsdjkfhjkdshjkfhjdhfkshfhdjffhjhfdshfhfsdfjhsdhf" +
                        "fhgfjfhsdfhjksdhfksdjhfskjdfhksjdfhksjdfhksdjfhkjdfhjsdfhkjs" +
                        "jkdshfjkhfhdskjhfjhjfhjsdhfhjdfhkhdkfhdhfkdfdjfhksfkjdjfsdfh" +
                        "skdjfhkjdsfhdsjfhsdkufjdfhjksdfhjkdsfhdjfhjskhfjdhjdshfkjshj" +
                        "kjsdhjkdfhkjsdhfkjhfjhfjkdshfjkhfsdjfhjskdhfjksdhfjfhsdfsdfs" +
                        "kjsdhfkjdshfkjshkjsdhfdjshfjsdhfjdhfjksdhfksjhfjkdshfjskdhfs" +
                        "kjdshfkjsdhfksdjhfskdjhfksjfhskdjhfskjdhskjhfkjsdhsdkjhsdjkh" +
                        "kjdshfskdjhfkjshfjksdhfjkdhfshfkjsdhfjksdfhjdkshfjkdshfjksdh",
                10000);
    }

    /**
     * Test when app don't pass time ex then stop app.
     */
    @Test
    public void whenAppDontPassTimeExThenStopApp() {
        ThreadStopper.initialize("Hi, Petr, lets check this text - " +
                        "ghfhfgfgjkhjhsdjkfhjkdshjkfhjdhfkshfhdjffhjhfdshfhfsdfjhsdhf" +
                        "fhgfjfhsdfhjksdhfksdjhfskjdfhksjdfhksjdfhksdjfhkjdfhjsdfhkjs" +
                        "jkdshfjkhfhdskjhfjhjfhjsdhfhjdfhkhdkfhdhfkdfdjfhksfkjdjfsdfh" +
                        "skdjfhkjdsfhdsjfhsdkufjdfhjksdfhjkdsfhdjfhjskhfjdhjdshfkjshj" +
                        "kjsdhjkdfhkjsdhfkjhfjhfjkdshfjkhfsdjfhjskdhfjksdhfjfhsdfsdfs" +
                        "kjsdhfkjdshfkjshkjsdhfdjshfjsdhfjdhfjksdhfksjhfjkdshfjskdhfs" +
                        "kjdshfkjsdhfksdjhfskdjhfksjfhskdjhfskjdhskjhfkjsdhsdkjhsdjkh" +
                        "kjdshfskdjhfkjshfjksdhfjkdhfshfkjsdhfjksdfhjdkshfjkdshfjksdh",
                100);
    }
}