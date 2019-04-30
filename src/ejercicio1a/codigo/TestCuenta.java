package ejercicio1a.codigo;

public class TestCuenta {

	public static void main(String[] args) {
		Cuenta cuenta1 = new Cuenta(2, 123, "XXX");
		cuenta1.depositar(100);
		cuenta1.extraer(200);
	}

}
