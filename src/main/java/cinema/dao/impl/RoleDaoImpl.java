package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.RoleDao;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import java.util.Optional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> getByRoleName = session.createQuery(
                    "FROM Role WHERE role = :roleName", Role.class);
            getByRoleName.setParameter("roleName", Role.RoleName.valueOf(roleName));
            return getByRoleName.uniqueResultOptional();
        } catch (HibernateException e) {
            throw new DataProcessingException("Not found Role by role Name " + roleName, e);
        }
    }
}
