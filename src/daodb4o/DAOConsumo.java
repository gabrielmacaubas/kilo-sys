package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Consumo;



public class DAOConsumo extends DAO<Consumo> {

    public Consumo read(Object chave) {
    	int id = (int) chave;
        Query q = manager.query();
        q.constrain(Consumo.class);
        q.descend("id").constrain(id);
        List<Consumo> resultados = q.execute();
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }
    
	public void create(Consumo obj){
		int novoid = super.gerarId();
		obj.setId(novoid);
		manager.store( obj );
	}
	
	public List<Consumo> buscarTodos(){
        Query q = manager.query();
        q.constrain(Consumo.class);
        return q.execute();
    }
	
}


