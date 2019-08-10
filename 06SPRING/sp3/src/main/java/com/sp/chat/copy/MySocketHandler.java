/*package com.sp.chat.copy;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MySocketHandler extends TextWebSocketHandler {
   
   private final Logger logger=LoggerFactory.getLogger(MySocketHandler.class);
   
   private Map<String, User> sessionMap=new Hashtable<>();

   @PostConstruct
   public void init() throws Exception{
	   TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			Calendar cal = Calendar.getInstance();
			int h = cal.get(Calendar.HOUR_OF_DAY);
			int m = cal.get(Calendar.MINUTE);
			int s = cal.get(Calendar.SECOND);
			JSONObject job = new JSONObject();
			job.put("cmd","time");
			job.put("hour", h);
			job.put("minute", m);
			job.put("second", s);
			sendAllMessage(job.toString(), null);
		}
	};
	
	Timer timer = new Timer();
	// 10초마다 1번씩 실행
	timer.schedule(task, new Date(System.currentTimeMillis()+10000),10000);
   }
   
   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      super.afterConnectionEstablished(session);
      
      // WebSocket 연결이 열리고 사용할 준비가 된경우 호출
   }

   @Override
   public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
      super.handleMessage(session, message);
      
      // 클라이언트로부터 메시지가 도착된 경우 호출
      JSONObject jsonReceive=null;
      try {
         // 클라이언트가 보낸 메시지 받기
         jsonReceive=new JSONObject(message.getPayload().toString());
      } catch (Exception e) {
      }
      if(jsonReceive==null) return;
      
      String cmd=jsonReceive.getString("cmd");
      if(cmd==null) return;
      
      if(cmd.equals("connect")) {
         //처음 접속을 요청한 경우
         String userId=jsonReceive.getString("userId");
         String nickName=jsonReceive.getString("nickName");
         
         User user=new User();
         user.setUserId(userId);
         user.setNickName(nickName);
         user.setSession(session);
         
         // 서버에 접속자 정보 저장
         sessionMap.put(userId, user);
         
         // 현재 접속자 명단을 전송해줌
         Iterator<String> it=sessionMap.keySet().iterator();
         while(it.hasNext()) {
            String key=it.next();
            if(userId.equals(key)) continue;
            
            User vo=sessionMap.get(key);
            
            JSONObject job=new JSONObject();
            job.put("cmd", "connectList");
            job.put("userId", vo.getUserId());
            job.put("nickName", vo.getNickName());
            
            sendMessage(job.toString(), session);
         }
         
         // 다른 사용자에게 접속 사실을 알림
         JSONObject job=new JSONObject();
         job.put("cmd", "connect");
         job.put("userId", userId);
         job.put("nickName", nickName);
         sendAllMessage(job.toString(), userId);
         
      } else if(cmd.equals("message")) {
         // 채팅 메시지를 전송 받은 경우
         User vo=getUser(session);
         String msg=jsonReceive.getString("chatMsg");
         
         // 다른 사용자에게 메세지 전송
         JSONObject job=new JSONObject();
         job.put("cmd", "connect");
         job.put("chatMsg", msg);
         job.put("userId", vo.getUserId());
         job.put("nickName", vo.getNickName());
         sendAllMessage(job.toString(), vo.getUserId());
      }
   }

   @Override
   protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
      super.handleTextMessage(session, message);
   }

   
   @Override
   public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
      // session에서 에러가 발생한 경우
      removeUser(session);
   }

   @Override
   public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
      super.afterConnectionClosed(session, status);
      
      // WebSocket 연결이 닫힌 경우
      removeUser(session);
      
      logger.info("remove session!!");
   }
   
   // 모든 사용자에게 메시지 전송
   protected void sendAllMessage(String message, String out) {
      Iterator<String> it=sessionMap.keySet().iterator();
      
      while(it.hasNext()) {
         String key=it.next();
         if(out!=null&&out.equals(key))   // 자기자신
            continue;
         
         User user=sessionMap.get(key);
         WebSocketSession session=user.getSession();
         try {
            if(session.isOpen())
               session.sendMessage(new TextMessage(message));
         } catch (IOException e) {
            // 클라이언트에게 메세지를 보내지 못한 경우
            removeUser(session);
         }
      }
   }
   
   // 특정 사용자에게 메세지 전송
   protected void sendMessage(String message, WebSocketSession session) {
      if(session.isOpen()) {
         try {
            session.sendMessage(new TextMessage(message));
         } catch (Exception e) {
            logger.error("fail to send message - "+e.toString());
         }
      }
   }

   protected User getUser(WebSocketSession session) {
      User dto=null;
      Iterator<String> it=sessionMap.keySet().iterator();
      while(it.hasNext()) {
         String key=it.next();
         User u=sessionMap.get(key);
         if(u.getSession()==session) {
            dto=u;
            break;
         }
      }
      return dto;
   }
   
   // 접속한 사용자가 나간 경우
   protected void removeUser(WebSocketSession session) {
      User user=getUser(session);
      if(user!=null) {
         //접속한 사용자가 나간 사실을 모든 접속 사용자에게 알림
         JSONObject job=new JSONObject();
         job.put("cmd", "disconnect");
         job.put("userId", user.getUserId());
         job.put("nickName", user.getNickName());
         
         sendAllMessage(job.toString(), user.getUserId());
         
         //나간 접속자 종료
         try {
            user.getSession().close();
         } catch (Exception e) {
         }
         //서버에 저장된 나간 접속자 정보 삭제
         sessionMap.remove(user.getUserId());
      }
   }
}
*/