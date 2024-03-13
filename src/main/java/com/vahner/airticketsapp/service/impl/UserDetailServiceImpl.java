package com.vahner.airticketsapp.service.impl;

//@Service
//@RequiredArgsConstructor
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    private final AccountRepository accountRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = accountRepository.findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Account with login " + username + " not found"));
//
//        return User.builder()
//                .username(account.getLogin())
//                .password(account.getPassword())
//                .authorities(getAuthorities(account.getRole()))
//                .build();
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
//                .collect(Collectors.toList());
//    }
//}