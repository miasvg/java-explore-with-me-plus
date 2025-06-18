package ru.practicum.compilation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.compilation.model.Compilation;

import java.util.List;

@Repository
public interface CompilationRepository extends JpaRepository<Compilation, Long> {
    @Query(value = """
            SELECT c FROM compilations
            WHERE (:pinned IS NULL OR c.pinned = :pinned)
            LIMIT :size
            OFFSET :from
            """, nativeQuery = true)
    List<Compilation> findAllWithFilter(Boolean pinned, int from, int size);
}
