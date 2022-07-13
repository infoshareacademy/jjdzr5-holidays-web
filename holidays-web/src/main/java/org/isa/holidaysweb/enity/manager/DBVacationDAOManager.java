package org.isa.holidaysweb.enity.manager;

import org.isa.holidaysweb.enity.VacationDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
@Transactional
public class DBVacationDAOManager implements VacationDAOManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(VacationDAO vacation) {
        entityManager.persist(vacation);
    }

    @Override
    public VacationDAO find(UUID id) {
        return entityManager.find(VacationDAO.class, id);
    }
}
