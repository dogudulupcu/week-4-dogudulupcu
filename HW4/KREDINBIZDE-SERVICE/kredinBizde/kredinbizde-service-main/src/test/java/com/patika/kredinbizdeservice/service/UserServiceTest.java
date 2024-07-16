package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.entity.User;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationProducer notificationProducer;

    @Test
    public void should_create_user_successfully() {

        // Given
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(prepareUser());

        //when
        User userResponse = userService.save(prepareUser());

        //then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getName()).isEqualTo(prepareUser().getName());
        assertThat(userResponse.getSurname()).isEqualTo(prepareUser().getSurname());
        assertThat(userResponse.getEmail()).isEqualTo(prepareUser().getEmail());
        assertThat(userResponse.getPassword()).isEqualTo(prepareUser().getPassword());
        assertThat(userResponse.getIsActive()).isTrue();

        verify(userRepository,times(1)).save(Mockito.any(User.class));
        verify(notificationProducer,times(1)).sendNotification(Mockito.any(NotificationDTO.class));
    }

    @Test
    public void should_return_user_by_email_successfully(){
        // Given
        Mockito.when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(prepareUser()));



        //when
        User userResponse=userService.getByEmail("test@gmail.com");


        //then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getName()).isEqualTo(prepareUser().getName());
        assertThat(userResponse.getSurname()).isEqualTo(prepareUser().getSurname());
        assertThat(userResponse.getEmail()).isEqualTo(prepareUser().getEmail());
        assertThat(userResponse.getPassword()).isEqualTo(prepareUser().getPassword());
        assertThat(userResponse.getIsActive()).isTrue();

        verify(userRepository,times(1)).findByEmail("test@gmail.com");

    }


    @Test
    public void should_throw_kredinbizdeException_when_user_not_found(){
        // Given

        //when
        Assertions.assertThrows(KredinbizdeException.class,()->userService.getByEmail("test2@gmail.com"), "User not found");

        //then
      

    }

    @Test
    public void should_update_user_successfully() {
        // Given
        Mockito.when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(prepareUser()));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(prepareUser());

        //when
        User userResponse = userService.update("test@gmail.com", prepareUser());

        //then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getName()).isEqualTo(prepareUser().getName());
        assertThat(userResponse.getSurname()).isEqualTo(prepareUser().getSurname());
        assertThat(userResponse.getEmail()).isEqualTo(prepareUser().getEmail());
        assertThat(userResponse.getPassword()).isEqualTo(prepareUser().getPassword());
        assertThat(userResponse.getIsActive()).isTrue();

        verify(userRepository, times(1)).findByEmail("test@gmail.com");
        verify(userRepository, times(1)).save(Mockito.any(User.class));


    }

     @Test
     public void should_return_all_users_successfully(){
        // Given
        Mockito.when(userRepository.findAll()).thenReturn(prepareUserList());

        //when
        List<User> userListResponse = userService.getAll();

        //then
        assertThat(userListResponse).isNotNull();
        assertThat(userListResponse.size()).isEqualTo(prepareUserList().size());
        for(int i = 0;i<userListResponse.size();i++){
            assertThat(userListResponse.get(i).getName()).isEqualTo(prepareUserList().get(i).getName());
            assertThat(userListResponse.get(i).getSurname()).isEqualTo(prepareUserList().get(i).getSurname());
            assertThat(userListResponse.get(i).getEmail()).isEqualTo(prepareUserList().get(i).getEmail());
            assertThat(userListResponse.get(i).getPassword()).isEqualTo(prepareUserList().get(i).getPassword());
            assertThat(userListResponse.get(i).getIsActive()).isTrue();
        }

        verify(userRepository,times(1)).findAll();

    }

    @Test
    public void should_return_user_by_id_successfully(){
        // Given
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(prepareUser()));

        //when
        User userResponse = userService.getById(1L);

        //then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isEqualTo(prepareUser().getId());
        assertThat(userResponse.getName()).isEqualTo(prepareUser().getName());
        assertThat(userResponse.getSurname()).isEqualTo(prepareUser().getSurname());
        assertThat(userResponse.getEmail()).isEqualTo(prepareUser().getEmail());
        assertThat(userResponse.getPassword()).isEqualTo(prepareUser().getPassword());
        assertThat(userResponse.getIsActive()).isTrue();

        verify(userRepository,times(1)).findById(1L);

    }

    private List<User> prepareUserList() {
        return List.of(prepareUser(),prepareUser2(),prepareUser3());

    }


    private User prepareUser() {
        User user = new User();
        user.setName("test");
        user.setSurname("test");
        user.setEmail("test@gmail.com");
        user.setPassword("123456");
        user.setIsActive(true);
        user.setId(1L);
        return user;

    }

    private User prepareUser2() {
        User user = new User();
        user.setName("test2");
        user.setSurname("test2");
        user.setEmail("test2@gmail.com");
        user.setPassword("123456");
        user.setIsActive(true);

        return user;

    }

    private User prepareUser3() {
        User user = new User();
        user.setName("test3");
        user.setSurname("test3");
        user.setEmail("test3@gmail.com");
        user.setPassword("123456");
        user.setIsActive(true);

        return user;

    }
}
