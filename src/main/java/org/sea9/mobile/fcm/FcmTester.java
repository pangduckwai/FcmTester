package org.sea9.mobile.fcm;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

public class FcmTester {
	public boolean init() {
		try {
			FileInputStream serviceAccount = new FileInputStream("C:\\HOME\\__tools\\VDDK\\wheres-our-car-firebase-adminsdk-swq5c-6b91c19357.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://wheres-our-car.firebaseio.com")
				.build();

			FirebaseApp.initializeApp(options);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		FcmTester tester = new FcmTester();

		if (tester.init()) {
			System.out.println(tester.sendMessage());
		}
	}

	public String sendMessage() {
		String token = "";

		Message message = Message.builder()
			.putData("json", "{'id':-1,'name':'e-Golf','parking':'Parking','floor':'5','lot':'25','current':'false'}")
			.setToken(token)
			.build();

		try {
			String response = FirebaseMessaging.getInstance().send(message);
			System.out.println("Successfully sent message: " + response);
			return response;
		} catch (FirebaseMessagingException e) {
			throw new RuntimeException(e);
		}
	}
}