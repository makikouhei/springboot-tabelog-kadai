package com.example.nagoyamesi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyamesi.entity.Role;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.form.SignupForm;
import com.example.nagoyamesi.form.UserEditForm;
import com.example.nagoyamesi.repository.RoleRepository;
import com.example.nagoyamesi.repository.UserRepository;

@Service
public class UserService {
	 private final UserRepository userRepository;
     private final RoleRepository roleRepository;
     private final PasswordEncoder passwordEncoder;
     
     public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
         this.userRepository = userRepository;
         this.roleRepository = roleRepository;        
         this.passwordEncoder = passwordEncoder;
     }    
     
     @Transactional
     public User create(SignupForm signupForm) {
         User user = new User();
         Role role = roleRepository.findByName("ROLE_GENERAL");
         
         user.setName(signupForm.getName());
         user.setFurigana(signupForm.getFurigana());
         user.setPostalCode(signupForm.getPostalCode());
         user.setAddress(signupForm.getAddress());
         user.setPhoneNumber(signupForm.getPhoneNumber());
         user.setEmail(signupForm.getEmail());
         user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
         user.setRole(role);
         user.setEnabled(false);        
         
         return userRepository.save(user);
     }  
     
     @Transactional
     public void update(UserEditForm userEditForm) {
         User user = userRepository.getReferenceById(userEditForm.getId());
         
         user.setName(userEditForm.getName());
         user.setFurigana(userEditForm.getFurigana());
         user.setPostalCode(userEditForm.getPostalCode());
         user.setAddress(userEditForm.getAddress());
         user.setPhoneNumber(userEditForm.getPhoneNumber());
         user.setEmail(userEditForm.getEmail());      
         
         userRepository.save(user);
     }    
     
     // メールアドレスが登録済みかどうかをチェックする
     public boolean isEmailRegistered(String email) {
         User user = userRepository.findByEmail(email);  
         return user != null;
     }    
     
     // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
     public boolean isSamePassword(String password, String passwordConfirmation) {
         return password.equals(passwordConfirmation);
     }    
     // ユーザーを有効にする
     @Transactional
     public void enableUser(User user) {
         user.setEnabled(true); 
         userRepository.save(user);
     }    
     
  // メールアドレスが変更されたかどうかをチェックする
     public boolean isEmailChanged(UserEditForm userEditForm) {
         User currentUser = userRepository.getReferenceById(userEditForm.getId());
         return !userEditForm.getEmail().equals(currentUser.getEmail());      
     }  
     
     @Transactional
     public void updateRole(Map<String, String> paymentIntentObject) {
         String userId = paymentIntentObject.get("userId");
         System.out.println("Updating role for user ID: " + userId);  // ログ追加

         Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
         
         if (!userOptional.isPresent()) {
             throw new RuntimeException("指定されたユーザーが見つかりません。");
         }

         User user = userOptional.get();
         System.out.println("User found: " + user.getEmail());  // ログ追加

         String roleName = paymentIntentObject.get("roleName");
         System.out.println("Role name to be updated: " + roleName);  // ログ追加

         Role role = roleRepository.findByName(roleName);
         if (role == null) {
             throw new IllegalArgumentException("Role not found: " + roleName);
         }

         user.setRole(role);
         userRepository.save(user);
         System.out.println("Role updated successfully.");  // ログ追加

         refreshAuthenticationByRole(roleName);
     }

     public void refreshAuthenticationByRole(String newRole) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

         List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         authorities.add(new SimpleGrantedAuthority(newRole));
         Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);

         SecurityContextHolder.getContext().setAuthentication(newAuth);
     }
    

}
