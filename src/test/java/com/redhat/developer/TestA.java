//package com.redhat.developer;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//
//import org.junit.Test;
//
//public class TestA {
//    @Test
//    public void testA() throws IOException, InterruptedException {
//        String filename = "/A.txt";
//        String outputFilename = "/tmp/A.out";
//        testApplication(filename, outputFilename);
//    }
//
//    @Test
//    public void testB() throws IOException, InterruptedException {
//        String filename = "/B.txt";
//        String outputFilename = "/tmp/B.out";
//        testApplication(filename, outputFilename);
//    }
//
//    @Test
//    public void testC() throws IOException, InterruptedException {
//        String filename = "/C.txt";
//        String outputFilename = "/tmp/C.out";
//        testApplication(filename, outputFilename);
//    }
//
//    @Test
//    public void testD() throws IOException, InterruptedException {
//        String filename = "/D.txt";
//        String outputFilename = "/tmp/D.out";
//        testApplication(filename, outputFilename);
//    }
//
//    @Test
//    public void testE() throws IOException, InterruptedException {
//        String filename = "/E.txt";
//        String outputFilename = "/tmp/E.out";
//        testApplication(filename, outputFilename);
//    }
//
//    private void testApplication(String filename, String outputFilename ) throws IOException {
//        InputStream is = TestA.class.getResourceAsStream(filename);
//        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(outputFilename), "UTF-8");
//        Application.solve(new InputStreamReader(is), outputStreamWriter);
//    }
//
//}
