package org.mtito.springcloud.msvc.cursos.msvccursos.repositories;

import org.mtito.springcloud.msvc.cursos.msvccursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}
