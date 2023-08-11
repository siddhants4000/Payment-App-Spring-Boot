package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.enums.StatusCode;
import com.example.demo.enums.UserStatus;
import com.example.demo.model.Status;
import com.example.demo.model.WrapperResponse;
import com.example.demo.repo.UserRepository;
import com.example.demo.request.UserRequest;
import com.example.demo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public WrapperResponse<UserResponse> addUser(UserRequest userRequest) {
        if(Objects.isNull(userRepository.findByUserContact(userRequest.getUserContact()))){
            User newUser= User.builder()
                    .name(userRequest.getName())
                    .userContact(userRequest.getUserContact())
                    .userAddress(userRequest.getUserAddress())
                    .userEmail(userRequest.getUserEmail())
                    .userPassword(userRequest.getUserPassword())
                    .userName(userRequest.getUserName())
                    .userStatus(UserStatus.ACTIVE)
                    .build();

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("User has been added successfully.")
                    .success(Boolean.TRUE)
                    .build();

            userRepository.save(newUser);

            return WrapperResponse.<UserResponse>builder()
                    .data(UserResponse.builder()
                            .userId(newUser.getUserId())
                            .name(newUser.getName())
                            .userContact(newUser.getUserContact())
                            .userAddress(newUser.getUserAddress())
                            .userEmail(newUser.getUserEmail())
                            .userPassword(newUser.getUserPassword())
                            .userName(newUser.getUserName())
                            .userStatus(newUser.getUserStatus())
                            .build())
                    .status(resultStatus)
                    .build();
        } else {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("User already exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        }
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public WrapperResponse<UserResponse> deleteUser(UUID userId) {
        User user=userRepository.findByUserId(userId);
        if(Objects.isNull(user)) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("User does not exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (UserStatus.INACTIVE.equals(user.getUserStatus())) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("User is already deleted")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            user.setUserStatus(UserStatus.INACTIVE);

            userRepository.save(user);

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("User has been deleted successfully.")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .data(UserResponse.builder()
                            .userId(user.getUserId())
                            .name(user.getName())
                            .userContact(user.getUserContact())
                            .userAddress(user.getUserAddress())
                            .userEmail(user.getUserEmail())
                            .userPassword(user.getUserPassword())
                            .userName(user.getUserName())
                            .userStatus(user.getUserStatus())
                            .build())
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<UserResponse> blockUser(UUID userId) {
        User user=userRepository.findByUserId(userId);
        if(Objects.isNull(user)) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("User does not exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (UserStatus.BLOCKED.equals(user.getUserStatus())) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("User is already blocked")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            user.setUserStatus(UserStatus.BLOCKED);

            userRepository.save(user);

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("User has been blocked successfully.")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .data(UserResponse.builder()
                            .userId(user.getUserId())
                            .name(user.getName())
                            .userContact(user.getUserContact())
                            .userAddress(user.getUserAddress())
                            .userEmail(user.getUserEmail())
                            .userPassword(user.getUserPassword())
                            .userName(user.getUserName())
                            .userStatus(user.getUserStatus())
                            .build())
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<UserResponse> activateUser(UUID userId) {
        User user=userRepository.findByUserId(userId);
        if(Objects.isNull(user)) {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("User does not exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if (UserStatus.ACTIVE.equals(user.getUserStatus())) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("User is already active")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            user.setUserStatus(UserStatus.ACTIVE);

            userRepository.save(user);

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("User has been activated successfully.")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .data(UserResponse.builder()
                            .userId(user.getUserId())
                            .name(user.getName())
                            .userContact(user.getUserContact())
                            .userAddress(user.getUserAddress())
                            .userEmail(user.getUserEmail())
                            .userPassword(user.getUserPassword())
                            .userName(user.getUserName())
                            .userStatus(user.getUserStatus())
                            .build())
                    .status(resultStatus)
                    .build();
        }
    }
}
