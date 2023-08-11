package eight.java.spring.data.controller.api;

import eight.java.spring.data.request.user.UserRequest;
import eight.java.spring.data.responses.GeneralResponse;
import eight.java.spring.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAPIController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok( userService.signInUser(userRequest));
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody UserRequest userRequest){
        GeneralResponse genResp = userService.signUpUser(userRequest);
        if(genResp.status){
            return ResponseEntity.ok(genResp);
        }else{
            return  ResponseEntity.badRequest().body(genResp);
        }
    }

    @GetMapping("/logout/{token}")
    public ResponseEntity logout(@PathVariable("token")String token){
        return ResponseEntity.ok(userService.logOut(token));
    }

    @GetMapping("/user-datail/{token}")
    public ResponseEntity userDetail(@PathVariable("token")String token){
        return ResponseEntity.ok(userService.detailUser(token));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody UserRequest userRequest){
        GeneralResponse genResp = userService.forgotPassword(userRequest);
        if(genResp.status){
            return ResponseEntity.ok(genResp);
        }else{
            return  ResponseEntity.badRequest().body(genResp);
        }
    }




}
