package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Bebida;

public class DAOBebida extends DAO<Bebida> {

    public Bebida read(Object chave) {
        int id = (int) chave;
        Query q = manager.query();
        q.constrain(Bebida.class);
        q.descend("id").constrain(id);
        List<Bebida> resultados = q.execute();
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }

	public void create(Bebida obj){
		int novoid = super.gerarId();
		obj.setId(novoid);
		manager.store( obj );
	}
}