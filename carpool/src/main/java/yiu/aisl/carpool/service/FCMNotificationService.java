package yiu.aisl.carpool.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yiu.aisl.carpool.Dto.FCMNotificationRequestDto;
import yiu.aisl.carpool.domain.User;
import yiu.aisl.carpool.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {
    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository userRepository;

    public String sendNotificationByToken(FCMNotificationRequestDto requestDto) {
        Optional<User> user = userRepository.findByEmail(requestDto.getTargetUserId());

        if(user.isPresent()) {
            if(user.get().getFcmToken() != null) {
                Notification notification = Notification.builder()
                        .setTitle(requestDto.getTitle())
                        .setBody(requestDto.getBody())
                        .build();

                Message message = Message.builder()
                        .setToken(user.get().getFcmToken())
                        .setNotification(notification)
                        .build();

                try {
                    firebaseMessaging.send(message);
                    System.out.println(message);
                    System.out.println(user.get().getFcmToken());
                    return "알림을 성공적으로 전송했습니다. targetUserId = " +requestDto.getTargetUserId();
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                    return "알림 보내기를 실패하였습니다. targetUserId = " +requestDto.getTargetUserId();
                }
            } else {
                return "서버에 저장된 해당 유저의 fcmToken이 존재하지 않습니다. targetUserId = " +requestDto.getTargetUserId();
            }
        } else {
            return "해당 유저가 존재하지 않습니다. targetUserId = " +requestDto.getTargetUserId();
        }
    }
}
