package daodb4o;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Consumo;
import modelo.Refeicao;
import modelo.Pesagem;
import modelo.Bebida;

public class DAORefeicao extends DAO<Refeicao> {

    public Refeicao read(Object chave) {
        int id = (int) chave;
        Query q = manager.query();
        q.constrain(Refeicao.class);
        q.descend("id").constrain(id);
        List<Refeicao> resultados = q.execute();
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }
    
	public void create(Refeicao obj){
		int novoid = super.gerarId();
		obj.setId(novoid);
		manager.store( obj );
	}

    public List<Refeicao> refeicoesPorData(String data) {
        Query q = manager.query();
        q.constrain(Refeicao.class);
        q.descend("data").constrain(data);
        return q.execute();
    }

    public List<Refeicao> refeicoesAcimaDeNKg(int n) {
        Query q = manager.query();
        q.constrain(Refeicao.class);
        q.descend("consumos").constrain(new Evaluation() {
        	@Override
            public void evaluate(Candidate candidate) {
                Consumo consumo = (Consumo) candidate.getObject();
                if (consumo instanceof Pesagem) {
                    Pesagem pesagem = (Pesagem) consumo;
                    if (pesagem.getPeso() > n) {
                        candidate.include(true);
                    }
                } else {
                    candidate.include(false);
                }
            }
        });

        return q.execute();
    }
    
    public List<Refeicao> refeicoesMaisNBebidas(int N) {
        Query q = manager.query();
        q.constrain(Refeicao.class);
        q.descend("consumos").constrain(new Evaluation() {
            @Override
            public void evaluate(Candidate candidate) {
                List<Consumo> consumos = (List<Consumo>) candidate.getObject();
                int bebidaCount = 0;

                for (Consumo consumo : consumos) {
                    if (consumo instanceof Bebida) {
                        bebidaCount++;
                    }
                }

                if (bebidaCount > N) {
                    candidate.include(true);
                } else {
                    candidate.include(false);
                }
            }
        });

        return q.execute();
    }

}