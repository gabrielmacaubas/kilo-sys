package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Pesagem;


public class DAOPesagem extends DAO<Pesagem> {

    public Pesagem read(Object chave) {
        int id = (int) chave;
        Query q = manager.query();
        q.constrain(Pesagem.class);
        q.descend("id").constrain(id);
        List<Pesagem> resultados = q.execute();
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }

	public void create(Pesagem obj){
		int novoid = super.gerarId();
		obj.setId(novoid);
		manager.store( obj );
	}
}
