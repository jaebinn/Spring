package com.kh.demo.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{userid}")
@Controller
public class ChatController {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	private static Map<Session, String> client_id = Collections.synchronizedMap(new HashMap<Session, String>());
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException{
		synchronized (clients) {
			for(Session client : clients) {
				if(!client.equals(session)) {
					System.out.println("Send '"+message+"' To "+client_id.get(client));
					client.getBasicRemote().sendText(message);
				}
			}
		}
	}
	@OnOpen
	public void onOpen(Session session, @PathParam(value="userid")String userid) {
		System.out.println("접속 : "+session+" / 아이디 : "+userid);
		clients.add(session);
		client_id.put(session, userid);
	}
	@OnClose
	public void onClose(Session session) {
		System.out.println("종료 : "+session+" / 아이디 : "+client_id.get(session));
		clients.remove(session);
		client_id.remove(session);
	}
}
