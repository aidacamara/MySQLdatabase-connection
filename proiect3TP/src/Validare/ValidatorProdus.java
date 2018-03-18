package Validare;

import Model.Produs;

public class ValidatorProdus implements Validator<Produs>{

	
	@Override
	public void validate(Produs t) throws IllegalArgumentException {
		if(t.getCantitate()<=0 || t.getPret()<=0){
			throw new IllegalArgumentException("Cantitate sau pret negativ sau egal cu zero");
		}
		
	}
	
	

}
