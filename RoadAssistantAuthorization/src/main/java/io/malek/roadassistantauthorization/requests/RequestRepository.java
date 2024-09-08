package io.malek.roadassistantauthorization.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RequestRepository extends JpaRepository<Request, Long> { }
