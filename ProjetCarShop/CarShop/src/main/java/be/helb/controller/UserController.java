package be.helb.controller;

import be.helb.DAO.UserDao;
import be.helb.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDao =userDao ;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ApiOperation(value = "Opération pour créer un compte")
    @PostMapping("/signup")
    public void singUp(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @ApiOperation(value = "Opération pour check si on est connecter")
    @PostMapping("/singupTest")
    public String getSi(){
        return "ok";
    }

    @ApiOperation(value = "Opération pour se connecter")
    @PostMapping("/login")
    public void theFakeLogin(@RequestBody User loginRequestModel){
        throw new IllegalStateException("Error");
    }
}
