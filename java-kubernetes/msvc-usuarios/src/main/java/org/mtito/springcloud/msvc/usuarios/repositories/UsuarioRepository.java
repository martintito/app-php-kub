package org.mtito.springcloud.msvc.usuarios.repositories;

import org.mtito.springcloud.msvc.usuarios.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
