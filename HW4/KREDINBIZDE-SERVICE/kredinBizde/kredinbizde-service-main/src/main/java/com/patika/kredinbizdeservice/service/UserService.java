package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.entity.User;
import com.patika.kredinbizdeservice.exceptions.ExceptionMessages;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import com.patika.kredinbizdeservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserRepository userRepository;
    
    private final NotificationProducer notificationProducer;


    @Transactional
    public User save(User user) {
       System.out.println("userRepository: " + userRepository.hashCode());
        user.setIsActive(true);
        userRepository.save(user);
        
        notificationProducer.sendNotification(prepareNotificationDTO((NotificationType.MOBILE_NOTIFICATION),user.getEmail()));
        return user;
    }

    private NotificationDTO prepareNotificationDTO(NotificationType notificationType, String email) {
        return NotificationDTO.builder()
                .message("user kaydedildi")
                .notificationType(notificationType)
                .userEmail(email)
                .build();
    }


    public List<User> getAll() {
        System.out.println("userRepository: " + userRepository.hashCode());
        return userRepository.findAll();
    }


    public User getByEmail(String email) {
      Optional<User> foundUser = userRepository.findByEmail(email);

      User user = foundUser.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.USER_NOT_FOUND));
      return foundUser.orElse(null);

    }


    public User update(String email, User user) {
        Optional<User> userToUpdate = userRepository.findByEmail(email);

            if(userToUpdate.isPresent()){
                User updatedUser = userToUpdate.get();
                updatedUser.setEmail(user.getEmail());
                updatedUser.setName(user.getName());
                updatedUser.setSurname(user.getSurname());
                updatedUser.setPhoneNumber(user.getPhoneNumber());
                userRepository.delete(user);
                userRepository.save(updatedUser);
                return updatedUser;
            }
            return null;


    }

    public User getById(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
