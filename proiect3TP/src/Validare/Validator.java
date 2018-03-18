package Validare;

public interface Validator<T> {
/**
 * Metoda care asigura verosimilitatea obiectlor
 * @param t obiectul verificat
 */
	public void validate(T t) throws IllegalArgumentException;
}
