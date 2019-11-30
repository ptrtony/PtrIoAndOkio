package com.zhaofan.javaioandokio;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args) {
        try {
            io5();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void io1() {
        try (OutputStream outputStream = new FileOutputStream("./app/text.txt")) {
            outputStream.write('a');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void io2() {
        try (InputStream inputStream = new FileInputStream("./app/text.txt");
             Reader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader);) {

            System.out.println(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void io3() {
        try (OutputStream outputStream = new FileOutputStream("./app/text.txt");
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
            bufferedOutputStream.write('a');
            bufferedOutputStream.write('b');
            bufferedOutputStream.write('c');
            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void io4() {
        try (InputStream inputStream = new FileInputStream("./app/text.txt");
             OutputStream outputStream = new FileOutputStream("./app/text_copy.txt")) {
            byte[] bytes = new byte[1024];
            int readCount;
            while ((readCount = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, readCount);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void io5() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        while (true) {
            String data = bufferedReader.readLine();
            bufferedWriter.write("我给你输入了：" + data + "\n");
            bufferedWriter.flush();
        }
    }

    private static void io6()  {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("./app/text.txt","r");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
