package eight.java.spring.data.service;
import eight.java.spring.data.entity.User;
import eight.java.spring.data.request.user.UserRequest;
import eight.java.spring.data.responses.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import eight.java.spring.data.respository.UserRepository;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public GeneralResponse signUpUser(UserRequest userRequest){
        User userFind = userRepository.findByPhoneNumber(userRequest.getPhoneNumber());

        Date tglLahir ;
        try {
            SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
            tglLahir = sd.parse(userRequest.getDateBirth());
        }catch (Exception e){
            tglLahir =  null;
        }

        GeneralResponse generalResponse = new GeneralResponse();
        try{
            userFind.getPhoneNumber();
            generalResponse.setMessage("User with "+ userFind.getPhoneNumber()+ " has registered");
            generalResponse.setStatus(false);
            return generalResponse;
        }catch (NullPointerException e){
            User user = new User();
            user.setName(userRequest.getName());
            user.setAddress(user.getAddress());
            user.setActive(true);
            user.setDateBirth(tglLahir);
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setAddress(userRequest.getAddress());
            user.setPassword(BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            generalResponse.setMessage("Success created user");
            generalResponse.setStatus(true);
            return generalResponse;
        }
    }

    public GeneralResponse signInUser(UserRequest userRequest){
        User userFind = userRepository.findByPhoneNumber(userRequest.getPhoneNumber());
        GeneralResponse generalResponse = new GeneralResponse();
        if(userFind!=null){
            SecureRandom secureRandom = new SecureRandom();
            byte[] randomBytes = new byte[24];
            secureRandom.nextBytes(randomBytes);
            Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
            userFind.setToken(base64Encoder.encodeToString(randomBytes));
            userRepository.save(userFind);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("token", userFind.getToken());
            generalResponse.setMessage("Success sign in user");
            generalResponse.setStatus(true);
            generalResponse.setData(data);
            return generalResponse;
        }else{
            generalResponse.setMessage("Failed sign in user");
            generalResponse.setStatus(false);
            return generalResponse;
        }
    }

    public GeneralResponse logOut(String token){
        User userFind = userRepository.findByToken(token);
        if(userFind==null){
            GeneralResponse generalResponse = new GeneralResponse();
            generalResponse.setMessage("User not found");
            generalResponse.setStatus(true);
            return generalResponse;
        }else {
            userFind.setToken("");
            userRepository.save(userFind);
            GeneralResponse generalResponse = new GeneralResponse();
            generalResponse.setMessage("Success logout");
            generalResponse.setStatus(true);
            return generalResponse;
        }
    }

    public User detailUser(String token){
        User user = userRepository.findByToken(token);
        return user;
    }

    public GeneralResponse forgotPassword(UserRequest userRequest){
        User userFind = userRepository.findByPhoneNumber(userRequest.getPhoneNumber());
        GeneralResponse generalResponse = new GeneralResponse();
        if(userFind==null) {
            generalResponse.setMessage("No user with phone number"+userRequest.getPhoneNumber());
            generalResponse.setStatus(false);
            return generalResponse;
        }else{
            userFind.setToken("");
            userFind.setPassword(BCrypt.hashpw(userRequest.getNewPassword(), BCrypt.gensalt()));
            userRepository.save(userFind);
            generalResponse.setMessage("success reset password");
            generalResponse.setStatus(true);
            return generalResponse;
        }
    }
}
