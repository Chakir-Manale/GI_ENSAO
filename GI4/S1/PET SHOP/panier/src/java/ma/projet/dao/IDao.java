
package ma.projet.dao;

import java.util.List;

/**
 *
 * @author lachgar
 */
public interface IDao <T>{
    boolean create(T o);

    boolean update(T o);

    boolean delete(T o);

    List<T> getAll();

    T getById(int id);
}
