package ejercicio1a;

public class CuentaCorriente extends Cuenta {
	private long descubiertoAcordado;

	public CuentaCorriente(long numeroCuenta, String titular, long descubiertoAcordado) {
		super(numeroCuenta, titular);
		this.descubiertoAcordado = descubiertoAcordado;
	}
	
	protected boolean hayDineroSuficiente(long monto) {
		return monto >= this.getSaldo() + descubiertoAcordado;
	}

}
