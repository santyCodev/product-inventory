package com.santicodev.gestorinventarioproductos.security.Service;

import com.santicodev.gestorinventarioproductos.security.model.User;
import com.santicodev.gestorinventarioproductos.security.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca el usuario en la base de datos
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con nombre: " + username));

        // Construye y devuelve el objeto UserDetails de Spring Security a partir de mi User
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(), // La contraseña ya está hasheada
                user.getRoles().stream() // Mapea los roles del modelo a SimpleGrantedAuthority de Spring Security
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toSet())
        );
    }
}
