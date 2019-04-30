package ejercicio1a;

public abstract class Cuenta {
	private long numeroCuenta;
	private String titular;
	private long saldo;
	
	public Cuenta(long numeroCuenta, String titular) {
		this.numeroCuenta = numeroCuenta;
		this.titular = titular;
		this.saldo = 0;
	}
	
	public void depositar(long monto) {
		saldo += monto;
	}
	
	public void extraer (long monto) throws RuntimeException{
		if (!this.hayDineroSuficiente(monto)) {
			throw new RuntimeException("No hay dinero suficiente");
		}
		saldo -= monto;
	}
	
	public long getSaldo() {
		return saldo;
	}

	protected boolean hayDineroSuficiente(long monto) {
		return monto >= saldo;
	}
}
