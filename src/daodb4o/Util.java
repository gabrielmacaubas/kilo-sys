package daodb4o;


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.Bebida;
import modelo.Consumo;
import modelo.Refeicao;

public class Util {
	private static ObjectContainer manager=null;

	
	public static ObjectContainer conectarBanco(){
		if (manager != null)
			return manager;

		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);
		config.common().objectClass(Consumo.class).cascadeOnDelete(false);;
		config.common().objectClass(Consumo.class).cascadeOnUpdate(true);;
		config.common().objectClass(Consumo.class).cascadeOnActivate(true);
		config.common().objectClass(Bebida.class).cascadeOnDelete(false);;
		config.common().objectClass(Bebida.class).cascadeOnUpdate(true);;
		config.common().objectClass(Bebida.class).cascadeOnActivate(true);
		config.common().objectClass(Refeicao.class).cascadeOnDelete(false);;
		config.common().objectClass(Refeicao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Refeicao.class).cascadeOnActivate(true);
		//	config.common().objectClass(Carro.class).objectField("placa").indexed(true);
		//	config.common().objectClass(Cliente.class).objectField("nome").indexed(true);

		manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}

	public static void desconectar() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
}
