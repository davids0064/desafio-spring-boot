package com.nuevospa.gestion.service.implent;

import com.nuevospa.gestion.exception.GestionException;
import com.nuevospa.gestion.jpa.entity.UsuariosEntity;
import com.nuevospa.gestion.jpa.repository.UsuariosRepository;
import com.nuevospa.gestion.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuariosEntity usuariosEntity = usuariosRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new User(usuariosEntity.getUsername(), usuariosEntity.getPassword(), new ArrayList<>());
    }

}
