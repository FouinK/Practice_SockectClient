package com.example.WebSocketClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

@SpringBootApplication
public class WebSocketClientApplication {

	public static void main(String[] args) {

		Socket socket = null;            //서버 통신 Socket
		BufferedReader in = null;        //서버에서부터 데이터 읽기(입력스트림)
		BufferedReader in2 = null;        //키보드로 데이터 읽기(입력스트림)
		PrintWriter out = null;            //서버로 데이터 전송(출력스트림)
		InetAddress ia = null;

		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("서버 주소 입력 (WebSocketServer가 열린 컴퓨터의 ip주소를 입력해주세요) : ");
			String ipAddrress = scanner.next();
			System.out.println();
			ia = InetAddress.getByName(ipAddrress);    //서버 접속
			System.out.print("서버와 동일한 포트 번호를 입력해주세요 (ex.8080) : ");
			int port = scanner.nextInt();
			System.out.println();
			socket = new Socket(ia,port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			in2 = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

			System.out.println(socket.toString());
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("서버와 소켓 통신이 시작 되었습니다. ");
		while (true) {

			try {
				System.out.print("서버 요청할 메세지 : ");
				String data = in2.readLine();            //키보드 입력

				out.println(data+"\n");                        //서버로 데이터 전송
				out.flush();
				System.out.println("서버에 메세지 전송 중 ...");
				String str2 = in.readLine();            //서버 응답 데이터 읽기
				System.out.println("서버에서부터 받은 응답 메세지 : " + str2);


			}catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

}
