package com.vahner.airticketsapp.security;


//@Getter
//@Setter
//public class JwtAuthentication implements Authentication {
//
//    private boolean authenticated;
//    private String login;
//    private Set<Role> roles;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
//    }
//
//    @Override
//    public Object getCredentials() {
//        return null;
//    }
//
//    @Override
//    public Object getDetails() {
//        return null;
//    }
//
//    @Override
//    public Object getPrincipal() {
//        return getName();
//    }
//
//    @Override
//    public boolean isAuthenticated() {
//        return authenticated;
//    }
//
//    @Override
//    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//        this.authenticated = isAuthenticated;
//    }
//
//    @Override
//    public String getName() {
//        return login;
//    }
//
//    @Override
//    public boolean implies(Subject subject){
//        return Authentication.super.implies(subject);
//    }
//}