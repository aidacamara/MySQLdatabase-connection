package Validare;

import DAO.AbstractDAO;
import DAO.ClientDAO;
import DAO.ProdusDAO;
import Model.Client;
import Model.Comanda;
import Model.Produs;

public class ComandaValidator implements Validator<Comanda> {

	@Override
	public void validate(Comanda t) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		int clientId=t.getIdClient();
		int produsId=t.getIdProdus();
		int cantitate=t.getCantitate();
		AbstractDAO<Client> clientDAO=new ClientDAO();
		ProdusDAO produsDAO=new ProdusDAO();
		if(clientDAO.findById(clientId)==null || produsDAO.findById(produsId)==null || cantitate<=0){
			throw new IllegalArgumentException("Nu exista clientul sau produsul sau cantitate mai mica sau egala cu zero!");

		}
		else{
			Produs produs=produsDAO.findById(produsId);
			int cantitateTotal=produs.getCantitate();
			if(cantitateTotal<cantitate){
				throw new IllegalArgumentException("Cantitate prea mare");
			}
			else{
				produsDAO.updateById(produs.getId(),produs.getNume(),produs.getPret(),cantitateTotal-cantitate);
			}
		}
	}

}
