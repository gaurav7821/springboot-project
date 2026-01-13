package com.gogos.spring_project.service.impl;

import com.gogos.spring_project.entities.User;
import com.gogos.spring_project.exceptions.ResourceNotFoundException;
import com.gogos.spring_project.payloads.UserDto;
import com.gogos.spring_project.repositories.UserRepo;
import com.gogos.spring_project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userdto) {

        User user = this.dtoToUser(userdto);
        User savedUser = this.userRepo.save(user);
        return this.userToUserdto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user=this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser=this.userRepo.save(user);

        return this.userToUserdto(updateUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user=this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));


        return this.userToUserdto(user);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users=this.userRepo.findAll();

        return users.stream().map(user-> this.userToUserdto(user)).collect(Collectors.toList());
    }

        @Override
        public void deleteUser(Integer userId) {

            User user=this.userRepo.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
    //        System.out.println( userRepo.findByName("gogo"));
                userRepo.delete(user);
        }

    public User dtoToUser(UserDto userdto){

//        User user = this.modelMapper.map(userdto, User.class);

//        user.setName(userdto.getName());
//        user.setEmail(userdto.getEmail());
//        user.setPassword(userdto.getPassword());
//        user.setAbout(userdto.getAbout());
        return this.modelMapper.map(userdto, User.class);
    }

    public UserDto userToUserdto(User user){

//        UserDto userDto = new UserDto();

//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return modelMapper.map(user, UserDto.class);
    }
}
