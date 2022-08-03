package org.wecancodeit.serverside.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.serverside.models.Role;
import org.wecancodeit.serverside.models.User;
import org.wecancodeit.serverside.repositories.RoleRepository;
import org.wecancodeit.serverside.repositories.UserRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Resource
    private UserRepository userRepo;

    @Resource
    private RoleRepository roleRepo;


    @GetMapping("/api/user/phone/{phone}")
    public Collection<User> getUserByPhone(@PathVariable String phone)  throws JSONException{
        Collection<User> findUsers = userRepo.findAllByPhone(phone.replace("%20", " "));

        return   findUsers;
    }

    @GetMapping("/api/user/email/{email}")
    public User getOneUserByEmail(@PathVariable String email)  throws JSONException{
        Optional<User> findOneUser = userRepo.findByEmail(email);
        return  findOneUser.get();
    }

    @GetMapping("/api/user/{id}")
    public User getOneUser(@PathVariable Long id)  throws JSONException{
        Optional<User> findOneUser = userRepo.findById(id);
        return  findOneUser.get();
    }

    @GetMapping("/api/user")
    public Collection<User> getAllUsers(){
        return(Collection<User>) userRepo.findAll();
    }

    @PutMapping ("/api/user/{id}/update-user")
    public Collection<User> UpdateOneUser(@PathVariable Long id, @RequestBody String body) throws JSONException {
        JSONObject newUser = new JSONObject(body);
        String firstName = newUser.getString("firstName");
        String lastName = newUser.getString("lastName");
        String email = newUser.getString("email");
        String phone = newUser.getString("phone");
        String avatar = newUser.getString("avatar");
        String description = newUser.getString("description");
        String password= newUser.getString("password");

        Optional<User> userSelectedOpt = userRepo.findById(id);

        if (userSelectedOpt.isPresent()) {
            userSelectedOpt.get().setUserAll(firstName, lastName, email, phone, avatar, description, password);

            String rolesId = newUser.getString("roleId");
            String[] roleS = rolesId.split(",");
            for (String roleIdString : roleS) {
                Long roleId = Long.parseLong(roleIdString);
                userSelectedOpt.get().addRole(roleRepo.findById(roleId).get());
            }

            userRepo.save(userSelectedOpt.get());
        }
        return (Collection<User>) userRepo.findAll();
    }

    @DeleteMapping("/api/user/{id}/delete-user")
    public Collection<User> deleteOneUser(@PathVariable Long id) throws JSONException {
        Optional<User> userToRemoveOpt = userRepo.findById(id);
        if(userToRemoveOpt.isPresent()){
            userRepo.delete(userToRemoveOpt.get());
        }
        //System.out.println(id);
        return (Collection<User>) userRepo.findAll();
    }

    @PostMapping("/api/user/add-user")
    public Collection<User> addUser(@RequestBody String body) throws JSONException{
        JSONObject newUser = new JSONObject(body);
        String firstName = newUser.getString("firstName");
        String lastName = newUser.getString("lastName");
        String email = newUser.getString("email");
        String phone = newUser.getString("phone");
        String avatar = newUser.getString("avatar");
        String description = newUser.getString("description");
        String password = newUser.getString("password");

        Optional<User> userToAddOpt = userRepo.findByEmail(email);
        //add user if not already in the database
        if (userToAddOpt.isEmpty()) {

            User userToAdd = new User(firstName, lastName, email, phone, avatar, description, password);
            String rolesId = newUser.getString("roleId");

            //ArrayList<Role> RoleList = new ArrayList<>();
            String[] roleS = rolesId.split(",");
            for (String roleIdString : roleS) {
                Long roleId = Long.parseLong(roleIdString);
                userToAdd.addRole(roleRepo.findById(roleId).get());
            }
            userRepo.save(userToAdd);
        }
        return(Collection<User>) userRepo.findAll();
    }


    ///operative

    @PostMapping("/api/user/signup")
    public String signUp(@RequestBody String body) throws JSONException{
        JSONObject newUser = new JSONObject(body);
        String firstName = newUser.getString("firstName");
        String lastName = newUser.getString("lastName");
        String email = newUser.getString("email");
        String phone = newUser.getString("phone");
        String avatar = newUser.getString("avatar");
        String description = newUser.getString("description");
        String password = newUser.getString("password");

        Optional<User> userToAddOpt = userRepo.findByEmail(email);
        //add user if not already in the database
        if (userToAddOpt.isEmpty()) {

            User userToAdd = new User(firstName, lastName, email, phone, avatar, description, password);
            String rolesId = newUser.getString("roleId");

            //ArrayList<Role> RoleList = new ArrayList<>();
            String[] roleS = rolesId.split(",");
            for (String roleIdString : roleS) {
                Long roleId = Long.parseLong(roleIdString);
                userToAdd.addRole(roleRepo.findById(roleId).get());
            }
            userRepo.save(userToAdd);
        }
        return (email!="" && password!="") ? "Successfully" : "Error";
    }

    @PostMapping("/api/user/login")
    public User login(@RequestBody String body) throws JSONException{
        JSONObject newUser = new JSONObject(body);
        String email = newUser.getString("email");
        String password = newUser.getString("password");

        Optional<User> userToCheck = userRepo.findByEmail(email);

        //add user if not already in the database
        boolean isLogin = false;
        if (userToCheck.get().getEmail() == email &&
                userToCheck.get().getPassword() == password &&
                userToCheck.get().getStatus() != 0 ) {
            isLogin = true;
        }
        return userToCheck.get(); //email +' '+ password; //isLogin==true? "deCode:"+email+":deCodeXYX" : "none";
    }

    @PostMapping("/api/user/status/{id}")
    public User setStatus(@PathVariable Long id) throws JSONException{

        Optional<User> findOneUser = userRepo.findById(id);
        int status = (findOneUser.get().getStatus() == 1) ? 0 : 1;
        findOneUser.get().setStatus(status);
        return  findOneUser.get();
    }








}
