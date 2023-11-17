package yiu.aisl.carpool.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import yiu.aisl.carpool.domain.User;

//UserDetails를 사용하면 아래의 메소드명을 변경하면 안됌
public class CustomUserDetails implements UserDetails {
  private final User user;
  public CustomUserDetails(User user) {
    this.user = user;
  }
  public final User getUser() {
    return user;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return user.getPwd();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }

  //이하 4개의 메소드는 jwt를 사용하기에 true로 설정
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
